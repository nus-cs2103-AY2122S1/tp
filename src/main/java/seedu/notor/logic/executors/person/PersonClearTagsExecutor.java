package seedu.notor.logic.executors.person;

import static seedu.notor.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.notor.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.model.person.Person;
import seedu.notor.model.tag.Tag;
import seedu.notor.ui.WarningWindow;

/**
 * Executor for a PersonClearTagsCommand.
 */
public class PersonClearTagsExecutor extends PersonExecutor {
    public static final String MESSAGE_CLEARTAG_PERSON_SUCCESS = "Removed all tags from %1$s";
    public static final String MESSAGE_TAGS_NOT_FOUND = "The person does not have any tags";
    public static final String CONFIRMATION_MESSAGE = "Clear %d tag(s) of Person: %2$s?";
    public static final String MESSAGE_CLEAR_TAGS_CANCEL = "Cancelled clearing of tags of %1$s";
    private static final String MESSAGE_CLEAR_TAGS_SUCCESS = "Successfully cleared all tags";

    /**
     * Constructor for a PersonClearTagsExecutor instance.
     *
     * @param index Index of the person to be edited.
     */
    public PersonClearTagsExecutor(Index index) {
        super(index);
        requireAllNonNull(index);
    }

    /**
     * Generates a command execution success message based on whether
     * the note is added.
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        return String.format(MESSAGE_CLEAR_TAGS_SUCCESS, personToEdit);
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        checkPersonList();
        Person person = super.getPerson();

        if (person.getTags().equals(new HashSet<Tag>())) {
            throw new ExecuteException(MESSAGE_TAGS_NOT_FOUND);
        }

        WarningWindow warningWindow = new WarningWindow(String.format(CONFIRMATION_MESSAGE,
                person.getTags().size(),
                person.getName()));
        warningWindow.show();

        if (warningWindow.canContinue()) {
            Person noTagsPerson = new Person(person.getName(), person.getPhone(), person.getEmail(),
                    person.getNote(), new HashSet<Tag>());
            model.setPerson(person, noTagsPerson);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(String.format(MESSAGE_CLEARTAG_PERSON_SUCCESS, noTagsPerson));
        }

        return new CommandResult(String.format(MESSAGE_CLEAR_TAGS_CANCEL, person));
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
