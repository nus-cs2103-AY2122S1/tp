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
        boolean isAllNumbers = checkAllNumbers(fields);
        if (checkNegativeId(fields)) {
            throw new ParseException(String.format(MESSAGE_INVALID_ID_LENGTH_AND_SIGN,
                    FindCommand.MESSAGE_USAGE));
        }
        if (!isAllNumbers) {
            return new FindCommand(new NameContainsKeywordsPredicate(fields));
        }
        if (!checkSixDigits(fields)) {
            throw new ParseException(String.format(MESSAGE_INVALID_ID_LENGTH_AND_SIGN,
                    FindCommand.MESSAGE_USAGE));
        }
        return new FindCommand((new IdContainsNumberPredicate(fields)));
    }

    /**
     * Checks whether the given {@code String} of arguments is negative.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public boolean checkNegativeId (List<String> args) {
        boolean isNegative = false;
        for (int i = 0; i < args.size(); i = i + 1) {
            if (args.get(i).charAt(0) == 45) {
                isNegative = true;
                break;
            }
        }
        return isNegative;
    }

    /**
     * Checks whether the given {@code String} of arguments are all numbers.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public boolean checkAllNumbers (List<String> args) {
        boolean isAllNumbers = true;
        for (int i = 0; i < args.size(); i = i + 1) {
            for (int j = 0; j < args.get(i).length(); j = j + 1) {
                if (((int) (args.get(i).charAt(j)) >= 48) & ((int) (args.get(i).charAt(j)) <= 57)) {
                } else {
                    isAllNumbers = false;
                    break;
                }
            }
        }
        return isAllNumbers;
    }

    /**
     * Checks whether the given {@code String} of id is 6 digits.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public boolean checkSixDigits (List<String> args) {
        boolean isSixDigits = true;
        for (int i = 0; i < args.size(); i = i + 1) {
            if (args.get(i).length() != 6) {
                isSixDigits = false;
                break;
            }
        }
        return isSixDigits;
    }



}
