package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PartialKeyContainsKeywordsPredicate;
import seedu.address.model.person.TagContainsKeywordsPredicate;
import seedu.address.model.person.TutorialIdOrRoleContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    public static final String NAME_TYPE = "n/";
    public static final String STUDENT_ID_TYPE = "s/";
    public static final String NUSNET_ID_TYPE = "N/";
    public static final String EMAIL_TYPE = "e/";
    public static final String GITHUB_ID_TYPE = "g/";
    public static final String PHONE_TYPE = "p/";
    public static final String ADDRESS_TYPE = "a/";
    public static final String TUTORIAL_ID_TYPE = "T/";
    public static final String ROLE_TYPE = "r/";
    public static final String TAG_TYPE = "t/";
    public static final String TAG_TYPE_FULL = "t/full";

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not to conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        boolean containsTypeCommand = trimmedArgs.contains(NAME_TYPE)
                || trimmedArgs.contains(STUDENT_ID_TYPE)
                || trimmedArgs.contains(NUSNET_ID_TYPE)
                || trimmedArgs.contains(EMAIL_TYPE)
                || trimmedArgs.contains(GITHUB_ID_TYPE)
                || trimmedArgs.contains(PHONE_TYPE)
                || trimmedArgs.contains(ADDRESS_TYPE)
                || trimmedArgs.contains(TUTORIAL_ID_TYPE)
                || trimmedArgs.contains(ROLE_TYPE)
                || trimmedArgs.contains(TAG_TYPE)
                || trimmedArgs.contains(TAG_TYPE_FULL);

        String[] typeKeywords = trimmedArgs.split("\\s+");
        // if the length of trimmedArgs is 2, only the command e.g. 'n/' has been provided, with no keys to search
        if (trimmedArgs.length() <= 2 || !trimmedArgs.contains("/") || !containsTypeCommand
                || trimmedArgs.equals("t/full/") || typeKeywords[0].length() == 2
                || (typeKeywords[0].length() == 7 && typeKeywords[0].contains("t/full/"))) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        //check that there is a key inputted for the command after the attribute type
        assert(typeKeywords[0].length() > 2);

        //check that the attribute type is inputted as a 2-character string, e.g. "n/" and not "n/alex"
        //assert(typeKeywords[0].length() == 2);
        String type = typeKeywords[0].substring(0, 2);
        typeKeywords[0] = typeKeywords[0].substring(2);
        List<String> keywords = Arrays.asList(typeKeywords);

        if (type.equals(TUTORIAL_ID_TYPE) || type.equals(ROLE_TYPE)) {
            return new FindCommand(new TutorialIdOrRoleContainsKeywordsPredicate(keywords, type));
        } else if (type.equals(TAG_TYPE)) {
            return new FindCommand(new TagContainsKeywordsPredicate(keywords, type));
        }
        //search partial keys for the other types
        return new FindCommand(new PartialKeyContainsKeywordsPredicate(keywords, type));
    }
}
