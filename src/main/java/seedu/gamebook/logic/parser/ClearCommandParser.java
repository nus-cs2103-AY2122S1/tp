package seedu.gamebook.logic.parser;

import static seedu.gamebook.logic.commands.ClearCommand.COMMAND_CONFIRMATION;

import seedu.gamebook.logic.commands.ClearCommand;
/**
 * Parses input arguments and creates a new ClearCommandParser object
 */
public class ClearCommandParser implements Parser<ClearCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ClearCommand
     * and returns a ClearCommand object for execution.
     */
    public ClearCommand parse(String args) {
        if (args == null) {
            return new ClearCommand();
        }
        String trimmedArgs = args.trim();
        if (trimmedArgs.equals(COMMAND_CONFIRMATION)) {
            return new ClearCommand(trimmedArgs);
        }
        return new ClearCommand();
    }
}
