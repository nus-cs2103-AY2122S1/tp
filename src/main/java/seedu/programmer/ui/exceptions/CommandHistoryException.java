package seedu.programmer.ui.exceptions;

import seedu.programmer.logic.commands.exceptions.CommandException;

public class CommandHistoryException extends Exception {
    public CommandHistoryException(String msg) {
        super(msg);
    }
}
