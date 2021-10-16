package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PARTICIPANTS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.participant.Address;
import seedu.address.model.participant.BirthDate;
import seedu.address.model.participant.Email;
import seedu.address.model.participant.Name;
import seedu.address.model.participant.NextOfKin;
import seedu.address.model.participant.Note;
import seedu.address.model.participant.Participant;
import seedu.address.model.participant.Phone;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing Participant in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the participant identified "
            + "by the index number used in the displayed Participant list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PARTICIPANT_SUCCESS = "Edited Participant: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PARTICIPANT = "This participant already exists in Managera.";

    private final Index index;
    private final EditParticipantDescriptor editParticipantDescriptor;

    /**
     * @param index                     of the Participant in the filtered Participant list to edit
     * @param editParticipantDescriptor details to edit the Participant with
     */
    public EditCommand(Index index, EditParticipantDescriptor editParticipantDescriptor) {
        requireNonNull(index);
        requireNonNull(editParticipantDescriptor);

        this.index = index;
        this.editParticipantDescriptor = new EditParticipantDescriptor(editParticipantDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Participant> lastShownList = model.getFilteredParticipantList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PARTICIPANT_DISPLAYED_INDEX);
        }

        Participant participantToEdit = lastShownList.get(index.getZeroBased());
        Participant editedParticipant = createEditedParticipant(participantToEdit, editParticipantDescriptor);

        if (!participantToEdit.isSameParticipant(editedParticipant) && model.hasParticipant(editedParticipant)) {
            throw new CommandException(MESSAGE_DUPLICATE_PARTICIPANT);
        }

        model.setParticipant(participantToEdit, editedParticipant);
        model.updateFilteredParticipantList(PREDICATE_SHOW_ALL_PARTICIPANTS);
        return new CommandResult(String.format(MESSAGE_EDIT_PARTICIPANT_SUCCESS, editedParticipant));
    }

    /**
     * Creates and returns a {@code Participant} with the details of {@code participantToEdit}
     * edited with {@code editParticipantDescriptor}.
     */
    private static Participant createEditedParticipant(Participant participantToEdit,
                                                       EditParticipantDescriptor editParticipantDescriptor) {
        assert participantToEdit != null;

        Name updatedName = editParticipantDescriptor.getName().orElse(participantToEdit.getName());
        Phone updatedPhone = editParticipantDescriptor.getPhone().orElse(participantToEdit.getPhone());
        Email updatedEmail = editParticipantDescriptor.getEmail().orElse(participantToEdit.getEmail());
        Address updatedAddress = editParticipantDescriptor.getAddress().orElse(participantToEdit.getAddress());
        Set<Tag> updatedTags = editParticipantDescriptor.getTags().orElse(participantToEdit.getTags());
        BirthDate updatedBirthDate = editParticipantDescriptor.getBirthDate().orElse(participantToEdit.getBirthDate());
        Set<Note> updatedNotes = editParticipantDescriptor.getNotes().orElse(participantToEdit.getNotes());
        ArrayList<NextOfKin> updatedNextOfKins =
            editParticipantDescriptor.getNextOfKins().orElse(participantToEdit.getNextOfKins());

        return new Participant(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags, updatedBirthDate,
                updatedNotes, updatedNextOfKins);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
            && editParticipantDescriptor.equals(e.editParticipantDescriptor);
    }

    /**
     * Stores the details to edit the Participant with. Each non-empty field value will replace the
     * corresponding field value of the Participant.
     */
    public static class EditParticipantDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;
        private BirthDate birthDate;
        private Set<Note> notes;
        private ArrayList<NextOfKin> nextOfKins;

        public EditParticipantDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditParticipantDescriptor(EditParticipantDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
            setBirthDate(toCopy.birthDate);
            setNotes(toCopy.notes);
            setNextOfKins(toCopy.nextOfKins);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags);
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

        public void setBirthDate(BirthDate birthDate) {
            this.birthDate = birthDate;
        }


        public Optional<BirthDate> getBirthDate() {
            return Optional.ofNullable(birthDate);
        }

        public void setNotes(Set<Note> notes) {
            this.notes = (notes != null) ? new HashSet<>(notes) : null;

        }

        public Optional<Set<Note>> getNotes() {
            return (notes != null) ? Optional.of(Collections.unmodifiableSet(notes)) : Optional.empty();
        }

        public void setNextOfKins(ArrayList<NextOfKin> nextOfKins) {
            this.nextOfKins = (nextOfKins != null) ? new ArrayList<>(nextOfKins) : null;
        }

        public Optional<ArrayList<NextOfKin>> getNextOfKins() {
            return (nextOfKins != null) ? Optional.of(new ArrayList<>((nextOfKins))) : Optional.empty();

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

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditParticipantDescriptor)) {
                return false;
            }

            // state check
            EditParticipantDescriptor e = (EditParticipantDescriptor) other;

            return getName().equals(e.getName())
                && getPhone().equals(e.getPhone())
                && getEmail().equals(e.getEmail())
                && getAddress().equals(e.getAddress())
                && getTags().equals(e.getTags());
        }
    }
}
