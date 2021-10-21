package seedu.notor.logic.executors.person;

import static seedu.notor.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.notor.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.model.person.Person;
import seedu.notor.model.tag.Tag;

/**
 * Executor for a PersonClearTagsCommand.
 */
public class PersonClearTagsExecutor extends PersonExecutor {
    public static final String MESSAGE_TAG_PERSON_SUCCESS = "Removed Tags from %1$s";
    public static final String MESSAGE_TAGS_NOT_FOUND = "The person does not have any tags";
    /**
     * Constructor for a PersonClearTagsExecutor instance.
     *
     * @param index Index of the person to be edited.
     */
    public PersonClearTagsExecutor(Index index) {
        super(index);
        requireAllNonNull(index);
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        Person person = super.getPerson();
        Person noTagsPerson = new Person(person.getName(), person.getPhone(), person.getEmail(),
                person.getNote(), new HashSet<Tag>());

        if (!person.isSame(noTagsPerson) && model.hasPerson(noTagsPerson)) {
            throw new ExecuteException(MESSAGE_TAGS_NOT_FOUND);
        }

        model.setPerson(person, noTagsPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_TAG_PERSON_SUCCESS, noTagsPerson));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        return other instanceof PersonClearTagsExecutor;
    }
}
