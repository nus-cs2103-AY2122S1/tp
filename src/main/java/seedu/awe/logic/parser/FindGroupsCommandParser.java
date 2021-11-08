package seedu.awe.logic.parser;

import static seedu.awe.commons.core.Messages.MESSAGE_FINDGROUPSCOMMAND_USAGE;
import static seedu.awe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.awe.logic.commands.FindGroupsCommand;
import seedu.awe.logic.parser.exceptions.ParseException;
import seedu.awe.model.group.GroupContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindGroupsCommand object
 */
public class FindGroupsCommandParser implements Parser<FindGroupsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindGroupsCommand
     * and returns a FindGroupsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindGroupsCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_FINDGROUPSCOMMAND_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindGroupsCommand(new GroupContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
