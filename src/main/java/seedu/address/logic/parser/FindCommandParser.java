package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CASE_SENSITIVE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.FindPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (extractSearchTerms(args).contains("c/")) {
            throw new ParseException(FindCommand.CASE_SENSITIVE_FLAG_FORMAT_MESSAGE);
        }

        boolean isCaseSensitive = false;
        if (trimmedArgs.contains(PREFIX_CASE_SENSITIVE.toString())) {
            isCaseSensitive = true;
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TAG);

        if ((!arePrefixesPresent(argMultimap, PREFIX_NAME) && !arePrefixesPresent(argMultimap, PREFIX_TAG))) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        List<String> nameStringList = argMultimap.getAllValues(PREFIX_NAME);
        if (areBlanksPresent(nameStringList)) {
            String nameFormatRequirementMessage = "There should not be any blanks in name.\n" + "For example, "
                    + "if you are " + "searching for " + "'n/John Doe', split them into 'n/John' "
                    + "and 'n/Doe' instead.";
            throw new ParseException(nameFormatRequirementMessage);
        }

        List<String> tagStringList = argMultimap.getAllValues(PREFIX_TAG);

        List<Name> nameKeywords;
        List<Tag> tagList;
        try {
            nameKeywords = nameStringList.stream().map(Name::new).collect(Collectors.toList());
            tagList = tagStringList.stream().map(Tag::new).collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            throw new ParseException(e.getMessage());
        }

        FindPredicate findpredicate = new FindPredicate(nameKeywords, tagList, isCaseSensitive);

        return new FindCommand(findpredicate);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private static boolean areBlanksPresent(List<String> stringList) {
        for (String s : stringList) {
            if (s.contains(" ")) {
                return true;
            }
        }
        return false;
    }

    private static String extractSearchTerms(String args) {
        int indexOfN = args.indexOf("n/");
        int indexOfT = args.indexOf("t/");
        int finalIndex;
        if (indexOfN == -1) {
            finalIndex = indexOfT;
        } else if (indexOfT == -1) {
            finalIndex = indexOfN;
        } else {
            finalIndex = Math.min(indexOfN, indexOfT);
        }
        return args.substring(finalIndex);
    }
}
