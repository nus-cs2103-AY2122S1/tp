package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.comparators.PersonComparator.MESSAGE_INVALID_CATEGORY;
import static seedu.address.logic.comparators.PersonComparator.VALIDATION_REGEX;
import static seedu.address.logic.parser.CliSyntax.EMPTY_PREFIX;

import java.util.List;
import java.util.Locale;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.category.Category;

/**
 * Parses input arguments and creates a new {@code FilterCommand} object
 */
public class FilterCommandParser implements Parser<FilterCommand> {
    private Category category;
    private Integer count;

    /**
     * Parses the given {@code String} of arguments in the context of the {@code FilterCommand}
     * and returns a {@code FilterCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(trimmedArgs, EMPTY_PREFIX);

        List<String> argList = argMultimap.getAllValues(EMPTY_PREFIX);

        if (!areArgumentsPresent(argList)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        try {
            processArguments(argList);
        } catch (ParseException e) {
            // for formatting of error output
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT,
                    e.getMessage() + FilterCommand.MESSAGE_USAGE));
        }

        return new FilterCommand(category, count);
    }

    /**
     * Parses the arguments in the given {@code List<String>} for filter.
     * @throws ParseException if the category or count is invalid.
     */
    private void processArguments(List<String> argList) throws ParseException {
        if (moreThanOneArgumentPresent(argList)) {
            // category and count arguments are specified
            String lastArgument = extractLastArgument(argList);
            String secondLastArgument = extractSecondLastArgument(argList);
            try {
                // Try to parse last argument as integer
                Integer.parseInt(lastArgument);
                count = ParserUtil.parseInteger(lastArgument);
                category = checkAndParseCategory(secondLastArgument);
            } catch (NumberFormatException e) {
                // Parse last argument as category
                checkIfNumberTooLarge(lastArgument);
                category = checkAndParseCategory(lastArgument);
                count = Integer.MAX_VALUE;
            }
        } else {
            // only category argument specified
            String lastArgument = extractLastArgument(argList);
            category = checkAndParseCategory(lastArgument);
            count = Integer.MAX_VALUE;
        }
    }

    /**
     * Returns true if arguments are present in the given
     * {@code List<String>}.
     */
    private boolean areArgumentsPresent(List<String> argList) {
        if (argList == null) {
            return false;
        }
        if (argList.size() == 1) {
            if (argList.get(0).equals("")) {
                return false;
            }
        }
        return argList.size() >= 1;
    }

    /**
     * Returns true if there are more than one argument present in the given
     * {@code List<String>}.
     */
    private boolean moreThanOneArgumentPresent(List<String> argList) {
        if (argList == null) {
            return false;
        }
        return argList.size() >= 2;
    }

    /**
     * Extracts the last {@code argument} from {@code List<String> argList}.
     */
    private String extractLastArgument(List<String> argList) {
        String last = argList.get(argList.size() - 1); // category is the first argument
        return last;
    }

    /**
     * Extracts the second last {@code argument} from {@code List<String> argList}.
     */
    private String extractSecondLastArgument(List<String> argList) {
        String secondLast = argList.get(argList.size() - 2); // category is the first argument
        return secondLast;
    }

    /**
     * Checks if the {@code argument} is a valid {@code Category} for Comparator.
     */
    private boolean isValidComparatorCategory(String arg) {
        requireNonNull(arg);
        String trimmedArg = arg.trim();
        String argUpperCase = trimmedArg.toUpperCase(Locale.ROOT);
        return argUpperCase.matches(VALIDATION_REGEX);
    }

    /**
     * Returns Category if the {@code argument} is a valid {@code Category} for Comparator.
     * @throws ParseException if the argument is not a valid category for Comparator.
     */
    private Category checkAndParseCategory(String arg) throws ParseException {
        if (isValidComparatorCategory(arg)) {
            return ParserUtil.parseCategory(arg);
        } else {
            throw new ParseException(MESSAGE_INVALID_CATEGORY);
        }
    }

    /**
     * Checks if the {@code argument} is a number larger than {@code Integer.MAX_VALUE}.
     * @throws ParseException if {@code argument} is a number is larger than {@code Integer.MAX_VALUE}
     */
    private void checkIfNumberTooLarge(String arg) throws ParseException {
        try {
            for (char c : arg.toCharArray()) {
                Integer.parseInt(String.valueOf(c));
            }
        } catch (NumberFormatException e) {
            return;
        }
        ParserUtil.parseInteger(arg);
    }
}
