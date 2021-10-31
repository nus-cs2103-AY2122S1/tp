package seedu.notor.logic.executors.person;

import seedu.notor.commons.core.Messages;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.ui.WarningWindow;

public class PersonArchiveAllExecutor extends PersonExecutor {
    public static final String MESSAGE_SUCCESS = "Archived all displayed persons!";
    public static final String MESSAGE_CANCEL = "Archiving cancelled!";
    public static final String CONFIRMATION_MESSAGE = "Do you want to proceed with archiving all Persons?";

    public PersonArchiveAllExecutor() {
        super(null);
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        if (!model.isPersonList() || model.isArchiveList()) {
            throw new ExecuteException(Messages.MESSAGE_PERSON_ARCHIVE_NOT_LISTED);
        }
        WarningWindow warningWindow = new WarningWindow(CONFIRMATION_MESSAGE);
        warningWindow.show();
        if (warningWindow.canContinue()) {
            model.archiveAllPersons();
            return new CommandResult(MESSAGE_SUCCESS);
        }
        return new CommandResult(MESSAGE_CANCEL);
    }
}
