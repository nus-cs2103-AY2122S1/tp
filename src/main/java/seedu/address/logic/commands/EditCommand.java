package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMPLOYMENT_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPECTED_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPERIENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL_OF_EDUCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

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
import seedu.address.model.done.Done;
import seedu.address.model.interview.Interview;
import seedu.address.model.notes.Notes;
import seedu.address.model.person.Email;
import seedu.address.model.person.EmploymentType;
import seedu.address.model.person.ExpectedSalary;
import seedu.address.model.person.Experience;
import seedu.address.model.person.LevelOfEducation;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ROLE + "ROLE] "
            + "[" + PREFIX_EMPLOYMENT_TYPE + "EMPLOYMENT TYPE] "
            + "[" + PREFIX_EXPECTED_SALARY + "EXPECTED_SALARY]"
            + "[" + PREFIX_LEVEL_OF_EDUCATION + "LEVEL OF EDUCATION] "
            + "[" + PREFIX_EXPERIENCE + "YEARS_OF_EXPERIENCE] "
            + "[" + PREFIX_TAG + "TAG]... "
            + "[" + PREFIX_INTERVIEW + "INTERVIEW] "
            + "[" + PREFIX_NOTES + "NOTES]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com ";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * Constructor for EditCommand.
     *
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
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

        // An existing person that is not the person being edited is a disallowed duplicate if it is a duplicate of
        // the person being edited.
        checkDisallowedDuplicates(model, personToEdit, editedPerson);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Checks if {@code editedPerson} is a duplicate of any existing Persons in {@code model} other than the
     * {@code personToEdit} and throws an exception if true.
     *
     * @param model Model object to be checked for duplicate Persons
     * @param personToEdit Person being edited
     * @param editedPerson Person with edits made
     * @throws CommandException if {@code editedPerson} is a duplicate of any existing Persons in {@code model}
     * other than the {@code personToEdit}
     */
    private void checkDisallowedDuplicates(Model model, Person personToEdit, Person editedPerson)
            throws CommandException {
        // check if editing this person will lead to duplicates in the addressbook
        if (model.hasPerson(editedPerson)) {
            List<Person> duplicates = model.getDuplicate(editedPerson);
            assert !duplicates.isEmpty() : "There should be at least 1 duplicate.";
            ArrayList<Person> disallowedDuplicates = new ArrayList<>();
            // allow changes only if it is to the same person
            // disallow changes that may affect other applicants that is not being edited
            for (Person duplicate : duplicates) {
                if (!duplicate.isSamePerson(personToEdit)) {
                    disallowedDuplicates.add(duplicate);
                }
            }
            if (!disallowedDuplicates.isEmpty()) {
                throw new CommandException(createDuplicateMessage(disallowedDuplicates, editedPerson));
            }
        }
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     *
     * @param personToEdit Person to be edited
     * @param editPersonDescriptor EditPersonDescriptor describing how {@code personToEdit} should be edited
     * @return Person edited based on {@code editPersonDescriptor}
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Role updatedRole = editPersonDescriptor.getRole().orElse(personToEdit.getRole());
        EmploymentType updatedEmploymentType = editPersonDescriptor
                .getEmploymentType().orElse(personToEdit.getEmploymentType());
        ExpectedSalary updatedExpectedSalary = editPersonDescriptor.getExpectedSalary()
                .orElse(personToEdit.getExpectedSalary());
        LevelOfEducation updatedLevelOfEducation = editPersonDescriptor
                .getLevelOfEducation().orElse(personToEdit.getLevelOfEducation());
        Experience updatedExperience = editPersonDescriptor.getExperience().orElse(personToEdit.getExperience());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());
        Optional<Interview> updatedInterview = editPersonDescriptor.getInterview().orElse(personToEdit.getInterview());
        Optional<Notes> updatedNotes = editPersonDescriptor.getNotes().orElse(personToEdit.getNotes());
        Done updatedDone = personToEdit.getDone();

        return new Person(updatedName, updatedPhone, updatedEmail, updatedRole,
                updatedEmploymentType, updatedExpectedSalary, updatedLevelOfEducation,
                updatedExperience, updatedTags, updatedInterview, updatedNotes, updatedDone);
    }

    /**
     * Creates a UI message informing user of existing duplicate applicants.
     * {@code duplicates} provided must contain at least 1 applicant.
     *
     * @param duplicates List of applicants who share the same phone number and email with {@code editedPerson}
     * @param toCheck applicant to be checked for duplicates with
     * @return String accumulation of all duplicate applicants
     */
    private String createDuplicateMessage(List<Person> duplicates, Person toCheck) {
        assert toCheck != null;
        assert duplicates != null;
        assert !duplicates.isEmpty() : "There should be at least 1 duplicate";
        StringBuilder stringBuilder = new StringBuilder();
        for (Person duplicate : duplicates) {
            stringBuilder.append(duplicate);
        }
        return "Your edited applicant " + toCheck
                + " shares either the same phone number or email as the following applicant(s):\n"
                + stringBuilder;
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
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Role role;
        private EmploymentType employmentType;
        private ExpectedSalary expectedSalary;
        private LevelOfEducation levelOfEducation;
        private Experience experience;
        private Set<Tag> tags;
        private Optional<Interview> interview;
        private Optional<Notes> notes;
        private Done done;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setRole(toCopy.role);
            setEmploymentType(toCopy.employmentType);
            setExpectedSalary(toCopy.expectedSalary);
            setLevelOfEducation(toCopy.levelOfEducation);
            setExperience(toCopy.experience);
            setTags(toCopy.tags);
            setInterview(toCopy.interview);
            setNotes(toCopy.notes);
            setDone(toCopy.done);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, role, employmentType,
                    expectedSalary, levelOfEducation, experience, tags, interview, notes);
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

        public void setRole(Role role) {
            this.role = role;
        }

        public Optional<Role> getRole() {
            return Optional.ofNullable(role);
        }

        public void setEmploymentType(EmploymentType employmentType) {
            this.employmentType = employmentType;
        }

        public Optional<EmploymentType> getEmploymentType() {
            return Optional.ofNullable(employmentType);
        }

        public void setExpectedSalary(ExpectedSalary expectedSalary) {
            this.expectedSalary = expectedSalary;
        }

        public Optional<ExpectedSalary> getExpectedSalary() {
            return Optional.ofNullable(expectedSalary);
        }

        public void setLevelOfEducation(LevelOfEducation levelOfEducation) {
            this.levelOfEducation = levelOfEducation;
        }

        public Optional<LevelOfEducation> getLevelOfEducation() {
            return Optional.ofNullable(levelOfEducation);
        }

        public void setExperience(Experience experience) {
            this.experience = experience;
        }

        public Optional<Experience> getExperience() {
            return Optional.ofNullable(experience);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         *
         * @param tags Tags to be set
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

        public void setInterview(Optional<Interview> interview) {
            this.interview = interview;
        }

        public Optional<Optional<Interview>> getInterview() {
            return Optional.ofNullable(interview);
        }

        public void setNotes(Optional<Notes> notes) {
            this.notes = notes;
        }

        public Optional<Optional<Notes>> getNotes() {
            return Optional.ofNullable(notes);
        }

        public void setDone(Done done) {
            this.done = done;
        }

        public Optional<Done> getDone() {
            return Optional.ofNullable(done);
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
                    && getRole().equals(e.getRole())
                    && getEmploymentType().equals(e.getEmploymentType())
                    && getExpectedSalary().equals(e.getExpectedSalary())
                    && getLevelOfEducation().equals(e.getLevelOfEducation())
                    && getExperience().equals(e.getExperience())
                    && getTags().equals(e.getTags())
                    && getInterview().equals(e.getInterview())
                    && getNotes().equals(e.getNotes())
                    && getDone().equals(e.getDone());
        }
    }
}
