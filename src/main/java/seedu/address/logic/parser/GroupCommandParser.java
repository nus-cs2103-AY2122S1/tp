package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.GroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;


public class GroupCommandParser implements Parser<GroupCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the GroupCommand
     * and returns a GroupCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GroupCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG, PREFIX_NAME);

        String nameKeywords = argMultimap.getValue(PREFIX_NAME).get();
        String tag = argMultimap.getValue(PREFIX_TAG).get();

        if (nameKeywords.equals("") && tag.equals("")) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupCommand.MESSAGE_ERROR_TAG
                            + "\n"
                            + GroupCommand.MESSAGE_ERROR_NAMES));
        }

        if (tag.equals("")) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupCommand.MESSAGE_ERROR_TAG));
        }

        if (nameKeywords.equals("")) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupCommand.MESSAGE_ERROR_NAMES));
        }

        return new GroupCommand(tag, nameKeywords);
    }
}
