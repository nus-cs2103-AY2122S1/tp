package seedu.times.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.times.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.times.logic.commands.SortCommand;
import seedu.times.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    private final String[] validSortByKeywords = new String[]{"name", "timing"};
    private final String[] validDirectionOfSortKeywords = new String[]{"asc", "desc"};

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand.
     * and returns an SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public SortCommand parse(String args) throws ParseException {

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        String[] splitArgs = trimmedArgs.split(" ");
        if (splitArgs.length != 2) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        String sortBy = splitArgs[0];
        checkSortBy(sortBy);

        // sort by asc or desc
        String directionOfSort = splitArgs[1];
        checkDirectionOfSort(directionOfSort);

        return new SortCommand(sortBy, directionOfSort);
    }

    /**
     * Checks the string to see if it is a valid sortBy keyword.
     *
     * @param sortBy String to check.
     * @throws ParseException If it is not a valid sortBy keyword.
     */
    public void checkSortBy(String sortBy) throws ParseException {
        requireNonNull(sortBy);
        for (String validSortByKeyword : validSortByKeywords) {
            if (sortBy.equals(validSortByKeyword)) {
                return;
            }
        }

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.INVALID_SORTBY));
    }

    /**
     * Checks the string to see if its a valid direction of sort keyword.
     *
     * @param directionOfSort String to check.
     * @throws ParseException If it is not a valid directionOfSort keyword.
     */
    public void checkDirectionOfSort(String directionOfSort) throws ParseException {
        requireNonNull(directionOfSort);
        for (String validDirectionOfSortKeyword : validDirectionOfSortKeywords) {
            if (directionOfSort.equals(validDirectionOfSortKeyword)) {
                return;
            }
        }

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.INVALID_DIRECTIONOFSORT));
    }
}
