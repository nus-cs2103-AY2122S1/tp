package seedu.tracker.logic.parser;

import static seedu.tracker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_ACADEMIC_YEAR;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_MC;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_SEMESTER;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.tracker.logic.commands.FindCommand;
import seedu.tracker.logic.parser.exceptions.ParseException;
import seedu.tracker.model.module.ModuleContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    private final List<Prefix> prefixList = new ArrayList<>(
        Arrays.asList(PREFIX_CODE, PREFIX_TITLE, PREFIX_DESCRIPTION, PREFIX_MC, PREFIX_TAG, PREFIX_ACADEMIC_YEAR,
            PREFIX_SEMESTER));

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_EMPTY_KEYWORD));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        if (!arePrefixesEmpty(Arrays.asList(nameKeywords))) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        if (isKeywordEmpty(Arrays.asList(nameKeywords))) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        assert nameKeywords.length != 0;
        return new FindCommand(new ModuleContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

    private boolean arePrefixesEmpty(List<String> nameKeywords) {
        boolean allPrefixesEmpty = true;
        for (String keyword : nameKeywords) {
            for (Prefix prefix : prefixList) {
                if (keyword.contains(prefix.getPrefix()) && !keyword.equals(prefix.getPrefix())) {
                    allPrefixesEmpty = false;
                }
            }
        }
        return allPrefixesEmpty;
    }

    private boolean isKeywordEmpty(List<String> nameKeywords) {
        ArrayList<String> listOfKeywords = new ArrayList<>(nameKeywords);
        for (Prefix prefix : prefixList) {
            listOfKeywords.remove(prefix.getPrefix());
        }
        return listOfKeywords.isEmpty();
    }
}
