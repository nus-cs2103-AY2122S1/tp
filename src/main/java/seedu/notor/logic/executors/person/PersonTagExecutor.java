package seedu.notor.logic.executors.person;

import static seedu.notor.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.notor.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collection;
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
 * Executor for a PersonTagCommand.
 */
public class PersonTagExecutor extends PersonExecutor {
    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final Set<Tag> tags;

    /**
     * Constructor for a PersonTagExecutor instance.
     *
     * @param index Index of the person to be edited.
     * @param tags Tags to add.
     */
    public PersonTagExecutor(Index index, Set<Tag> tags) {
        super(index);
        requireAllNonNull(index, tags);
        this.tags = tags;
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        Person person = super.getPerson();
        Person editedPerson = createEditedPerson(person, tags);

        if (!person.isSame(editedPerson) && model.hasPerson(editedPerson)) {
            throw new ExecuteException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(person, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit} with tags added
     */
    private static Person createEditedPerson(Person personToEdit, Set<Tag> tags) {
        assert personToEdit != null;

        // todo: abstract + add errors that are appropriate
        Set<Tag> updatedTags = new HashSet<>(personToEdit.getTags());
        updatedTags.addAll(tags);

        return new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getNote(), updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonTagExecutor)) {
            return false;
        }

        PersonTagExecutor e = (PersonTagExecutor) other;

        // state check
        return super.equals(other) && tags.equals(e.tags);
    }
}
