package seedu.address.logic.parser;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.SetRoleReqCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class SetRoleReqCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetRoleReqCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim().replace(SetRoleReqCommand.COMMAND_WORD, "");
        checkTrimmedArgs(trimmedArgs);


    }

    private void checkTrimmedArgs(String trimmedArgs) throws ParseException {
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    //"empty string found");
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetRoleReqCommand.HELP_MESSAGE));
        }
    }
}
