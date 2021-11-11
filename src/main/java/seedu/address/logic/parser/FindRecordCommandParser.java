package seedu.address.logic.parser;

import java.util.Arrays;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.FindRecordCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.residency.exceptions.ResidencyContainsKeywordsPredicate;


/**
 * Parses input arguments and creates a new FindRecordCommand object.
 */
public class FindRecordCommandParser implements Parser<FindRecordCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindRecordCommand
     * returns a FindRoomCommand object for execution.
     *
     * @param args takes in a String args in the context of the FindRecordCommand
     * @return returns a FindRecordCommand object for execution
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindRecordCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    FindRecordCommand.MESSAGE_USAGE));
        }

        String[] recordKeywords = trimmedArgs.split("\\s+");

        return new FindRecordCommand(new ResidencyContainsKeywordsPredicate(Arrays.asList(recordKeywords)));
    }
}
