package seedu.notor.logic.executors.person;

import static seedu.notor.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.notor.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.notor.commons.core.index.Index;
import seedu.notor.commons.util.CollectionUtil;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.model.person.Email;
import seedu.notor.model.person.Name;
import seedu.notor.model.person.Note;
import seedu.notor.model.person.Person;
import seedu.notor.model.person.Phone;
import seedu.notor.model.tag.Tag;

/**
 * Executor for a PersonEditCommand.
 */
public class PersonEditExecutor extends PersonExecutor {
    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final PersonEditDescriptor personEditDescriptor;

    /**
     * Constructor for a PersonEditExecutor instance.
     *
     * @param index Index of the person to be edited.
     * @param personEditDescriptor Descriptor for the edited person.
     */
    public PersonEditExecutor(Index index, PersonEditDescriptor personEditDescriptor) {
        super(index);
        requireAllNonNull(index, personEditDescriptor);
        this.personEditDescriptor = personEditDescriptor;
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        Person person = super.getPerson();
        Person editedPerson = createEditedPerson(person, personEditDescriptor);

        if (!person.isSame(editedPerson) && model.hasPerson(editedPerson)) {
            throw new ExecuteException(MESSAGE_DUPLICATE_PERSON);
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
        Set<Tag> updatedTags = personEditDescriptor.getTags().orElse(personToEdit.getTags());
        Note updatedNote = personEditDescriptor.getNote().orElse(personToEdit.getNote());

        return new Person(updatedName, updatedPhone, updatedEmail, updatedNote, updatedTags);
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
        private Set<Tag> tags;
        private Note note;

        public PersonEditDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public PersonEditDescriptor(PersonEditDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setTags(toCopy.tags);
            setNote(toCopy.note);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, tags);
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

        public void setNote(Note note) {
            this.note = note;
        }
        public Optional<Note> getNote() {
            return Optional.ofNullable(note);
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
            if (!(other instanceof PersonEditDescriptor)) {
                return false;
            }

            // state check
            PersonEditDescriptor e = (PersonEditDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getTags().equals(e.getTags());
        }
    }
}
