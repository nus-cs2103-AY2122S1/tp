package seedu.notor.logic.executors.person;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.model.person.Person;
import seedu.notor.ui.WarningWindow;
import seedu.notor.ui.note.NoteWindow;

public class PersonDeleteExecutor extends PersonExecutor {
    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
    public static final String MESSAGE_DELETE_PERSON_CANCEL = "Cancelled Deleting of Person: %1$s";
    public static final String MESSAGE_DELETE_PERSON_FAILURE =
            "Unable to delete Person: %1$s as note window for the person is currently opened.";
    public static final String CONFIRMATION_MESSAGE = "Do you want to proceed with deleting Person: %1$s?";


    public PersonDeleteExecutor(Index index) {
        super(index);
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        checkPersonList();
        Person toBeDeletedPerson = super.getPerson();
        if (NoteWindow.contains(toBeDeletedPerson)) {
            throw new ExecuteException(String.format(MESSAGE_DELETE_PERSON_FAILURE, toBeDeletedPerson));
        }
        WarningWindow warningWindow = new WarningWindow(String.format(CONFIRMATION_MESSAGE,
                toBeDeletedPerson.getName()));
        warningWindow.show();
        if (warningWindow.canContinue()) {
            model.deletePerson(toBeDeletedPerson);
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, toBeDeletedPerson));
        }
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_CANCEL, toBeDeletedPerson));
    }
}
