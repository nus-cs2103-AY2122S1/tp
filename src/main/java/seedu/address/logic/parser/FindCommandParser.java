package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ID_LENGTH_AND_SIGN;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.IdContainsNumberPredicate;
import seedu.address.model.item.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");
        List<String> fields = Arrays.asList(nameKeywords);

        if (((int) (fields.get(0).charAt(0)) >= 65) & ((int) (fields.get(0).charAt(0)) <= 122)) {
            return new FindCommand(new NameContainsKeywordsPredicate(fields));
        }
        for (int i = 0; i < fields.size(); i = i + 1) {
            if (fields.get(i).length() != 6 || fields.get(i).charAt(0) == 45) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_ID_LENGTH_AND_SIGN, FindCommand.MESSAGE_USAGE));
            }
        }
        return new FindCommand(new IdContainsNumberPredicate(fields));
    }

}
