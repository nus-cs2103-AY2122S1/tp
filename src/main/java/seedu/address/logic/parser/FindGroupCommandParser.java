package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.GroupContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindGroupCommand object
 */
public class FindGroupCommandParser implements Parser<FindGroupCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindGroupCommand
     * and returns a FindGroupCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindGroupCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        trimmedArgs = trimmedArgs.replaceAll("[^a-zA-Z0-9\\s_-]", "");
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindGroupCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");;
        String[] filteredKeywords = Arrays.stream(nameKeywords).filter(x -> !x.isBlank()).toArray(String[]::new);

        if (filteredKeywords.length == 0) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindGroupCommand.MESSAGE_USAGE));
        }

        return new FindGroupCommand(new GroupContainsKeywordsPredicate(Arrays.asList(filteredKeywords)));
    }

}
