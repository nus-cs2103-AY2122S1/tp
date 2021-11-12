package seedu.notor.logic.executors.person;

import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;

public class PersonArchiveShowExecutor extends PersonExecutor {
    public static final String MESSAGE_SUCCESS = "Listed all archived persons!";

    public PersonArchiveShowExecutor() {
        super(null);
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        model.displayPersonArchive();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
