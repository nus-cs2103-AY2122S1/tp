package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.FindFoldersCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.folder.FolderNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindFoldersCommand object
 */
public class FindFoldersCommandParser implements Parser<FindFoldersCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindFoldersCommand
     * and returns a FindFoldersCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindFoldersCommand parse(String args) throws ParseException {
        String trimmedArgs = StringUtil.stripFlags(args.trim());
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindFoldersCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindFoldersCommand(new FolderNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
