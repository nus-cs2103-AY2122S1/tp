package seedu.address.logic.candidate;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.position.Position.MESSAGE_POSITION_CLOSED;
import static seedu.address.model.position.Position.MESSAGE_POSITION_DOES_NOT_EXIST;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.Command;
import seedu.address.logic.CommandResult;
import seedu.address.logic.candidate.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Status;
import seedu.address.model.position.Position;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing candidate in the HR Manager.
 */
public class EditCandidateCommand extends Command {

    public static final String COMMAND_WORD = "edit_c";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the candidate identified "
            + "by the index number used in the displayed candidate list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_STATUS + "STATUS] "
            + "[" + PREFIX_TAG + "TAG]... "
            + "[" + PREFIX_POSITION + "POSITION]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Candidate: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "Candidate with Email:"
            + " [ %1$s ] already exists in the HR Manager";
    public static final String MESSAGE_ILLEGAL_PERSON_STATUS = "Unable to change Status of %1$s to APPLIED.\n"
            + "Candidate %1$s already has scheduled interview(s).";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index                of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCandidateCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_PERSON, editedPerson.getEmail()));
        }

        if (editPersonDescriptor.getStatus().isPresent()) {
            Status editedPersonStatus = editPersonDescriptor.getStatus().get();
            if (personToEdit.getInterviews().size() > 0 && editedPersonStatus == Status.APPLIED) {
                throw new CommandException(String.format(MESSAGE_ILLEGAL_PERSON_STATUS, personToEdit.getName()));
            }
        }

        Set<Position> newPositions = editedPerson.getPositions();
        Set<Position> positionReferences = new HashSet<>();

        for (Position p : newPositions) {
            if (!model.hasPosition(p)) {
                throw new CommandException(String.format(MESSAGE_POSITION_DOES_NOT_EXIST, p.getTitle()));
            }
            if (model.isPositionClosed(p)) {
                throw new CommandException(String.format(MESSAGE_POSITION_CLOSED,
                        model.getPositionReference(p).getTitle()));
            }
            positionReferences.add(model.getPositionReference(p));
        }
        editedPerson.setPositions(positionReferences);

        Set<Interview> interviews = personToEdit.getInterviews();

        newPositions = positionReferences;

        // Checks if positions was edited, remove from interviews for positions that candidate no longer applies to.
        if (editPersonDescriptor.isPositionEdited()) {
            for (Interview i : interviews) {
                i.deleteCandidate(personToEdit);

                if (!newPositions.contains(i.getPosition())) {
                    // delete interview from candidate if they no longer apply to the position.
                    editedPerson.deleteInterview(i);
                } else {
                    // add edited person to interview, if edited candidate still applies to the position.
                    i.addCandidate(editedPerson);
                }
            }
        } else {
            //Remove the old person and add the new one
            for (Interview i : interviews) {
                i.deleteCandidate(personToEdit);
                i.addCandidate(editedPerson);
            }
        }

        model.setPerson(personToEdit, editedPerson);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson),
                CommandResult.CommandType.CANDIDATE);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    public static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Remark updatedRemark = personToEdit.getRemark(); // edit command does not allow editing remarks
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());
        Status updatedStatus = editPersonDescriptor.getStatus().orElse(personToEdit.getStatus());
        Set<Position> updatedPositions = editPersonDescriptor.getPositions().orElse(personToEdit.getPositions());
        Set<Interview> interviews = personToEdit.getInterviews();

        Person updatedPerson = new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedRemark,
                updatedTags, updatedStatus, updatedPositions);

        for (Interview i : interviews) {
            updatedPerson.addInterview(i);
        }

        return updatedPerson;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCandidateCommand)) {
            return false;
        }

        // state check
        EditCandidateCommand e = (EditCandidateCommand) other;
        return index.equals(e.index)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the candidate with. Each non-empty field value will replace the
     * corresponding field value of the candidate.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;
        private Status status;
        private Set<Position> positions;
        private Set<Interview> interviews;

        public EditPersonDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
            setStatus(toCopy.status);
            setPositions(toCopy.positions);
            setInterviews(toCopy.interviews);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags, status, positions, interviews);
        }

        /**
         * Returns true if position field is edited.
         */
        public boolean isPositionEdited() {
            return CollectionUtil.isAnyNonNull(positions);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public Optional<Status> getStatus() {
            return Optional.ofNullable(status);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        /**
         * Sets {@code positions} to this object's {@code positions}.
         * A defensive copy of {@code positions} is used internally.
         */
        public void setPositions(Set<Position> positions) {
            this.positions = (positions != null) ? new HashSet<>(positions) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code positions} is null.
         */
        public Optional<Set<Position>> getPositions() {
            return (positions != null) ? Optional.of(Collections.unmodifiableSet(positions)) : Optional.empty();
        }

        /**
         * Sets {@code positions} to this object's {@code positions}.
         * A defensive copy of {@code positions} is used internally.
         */
        public void setInterviews(Set<Interview> interviews) {
            this.interviews = (interviews != null) ? new HashSet<>(interviews) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code positions} is null.
         */
        public Optional<Set<Interview>> getInterviews() {
            return (interviews != null) ? Optional.of(Collections.unmodifiableSet(interviews)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonDescriptor e = (EditPersonDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags())
                    && getStatus().equals(e.getStatus())
                    && getPositions().equals(e.getPositions())
                    && getInterviews().equals(e.getInterviews());
        }
    }
}
