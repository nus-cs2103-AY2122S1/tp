package seedu.notor.logic.executors.person;

import static seedu.notor.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.notor.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Optional;

import seedu.notor.commons.core.index.Index;
import seedu.notor.commons.util.CollectionUtil;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.model.common.Name;
import seedu.notor.model.person.Email;
import seedu.notor.model.person.Person;
import seedu.notor.model.person.Phone;

/**
 * Executor for a PersonEditCommand.
 */
public class PersonEditExecutor extends PersonExecutor {
    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists!";
    private static final String MESSAGE_NO_FIELDS_CHANGED = "All parameters are the same; no changes were made";

    private final PersonEditDescriptor personEditDescriptor;

    /**
     * Constructor for a PersonEditExecutor instance.
     *
     * @param index                Index of the person to be edited.
     * @param personEditDescriptor Descriptor for the edited person.
     */
    public PersonEditExecutor(Index index, PersonEditDescriptor personEditDescriptor) {
        super(index);
        requireAllNonNull(index, personEditDescriptor);
        this.personEditDescriptor = personEditDescriptor;
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        checkPersonList();
        Person person = super.getPerson();
        Person editedPerson = createEditedPerson(person, personEditDescriptor);

        // checks that name has not been changed to that of another person in Notor
        if (!person.isSame(editedPerson) && model.hasPerson(editedPerson)) {
            throw new ExecuteException(MESSAGE_DUPLICATE_PERSON);
        }

        // check that fields are actually edited
        if (person.equals(editedPerson)) {
            throw new ExecuteException(MESSAGE_NO_FIELDS_CHANGED);
        }

        model.setPerson(person, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, PersonEditDescriptor personEditDescriptor) {
        assert personToEdit != null;

        Name updatedName = personEditDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = personEditDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = personEditDescriptor.getEmail().orElse(personToEdit.getEmail());
        return new Person(updatedName, updatedPhone, updatedEmail, personToEdit.getNote(), personToEdit.getTags(),
                personToEdit.getDisplaySuperGroups(), personToEdit.getDisplaySubGroups());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonEditExecutor)) {
            return false;
        }

        PersonEditExecutor e = (PersonEditExecutor) other;

        // state check
        return super.equals(other) && personEditDescriptor.equals(e.personEditDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class PersonEditDescriptor {
        private Name name;
        private Phone phone;
        private Email email;

        public PersonEditDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public PersonEditDescriptor(PersonEditDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email);
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

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof PersonEditDescriptor)) {
                return false;
            }

            // state check
            PersonEditDescriptor e = (PersonEditDescriptor) other;
            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail());
        }
    }
}
