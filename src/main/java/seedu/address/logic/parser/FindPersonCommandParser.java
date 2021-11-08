package seedu.address.logic.parser;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ModelManager;
import seedu.address.model.person.NameContainsKeywordsPredicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.model.person.Module.MESSAGE_CONSTRAINTS;
import static seedu.address.model.person.Module.VALIDATION_REGEX;

/**
 * Parses input arguments and creates a new FindPersonCommand object
 */
public class FindPersonCommandParser implements Parser<FindPersonCommand> {

    // Regex for matching valid prefixes or white spaces
    private static final String PREFIXES_SPACE = "([nmt]/)|(\\s)";

    // Regex for matching valid prefixes ONLY
    private static final String VALID_PREFIXES = "[nmt]/";

    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    /**
     * Parses the given {@code String} of arguments in the context of the FindPersonCommand
     * and returns a FindPersonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindPersonCommand parse(String args) throws ParseException {

        String trimmedArgs = args.trim();

        logger.info("Finding: " + trimmedArgs);

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
        }

        validPrefixes(trimmedArgs);

        Pattern modulePattern = Pattern.compile(PREFIX_MODULE.getPrefix());
        Matcher moduleMatcher = modulePattern.matcher(trimmedArgs);
        ArrayList<String> store;

        if (!moduleMatcher.find()) {
            logger.info("No module tags");
            return new FindPersonCommand(new NameContainsKeywordsPredicate(generateKeywords((trimmedArgs))));
        } else {
            logger.info("Module tags with validation on " + trimmedArgs);
            store = generateKeywordsWithModuleChecking(trimmedArgs);
            return new FindPersonCommand(new NameContainsKeywordsPredicate(store));
        }

    }

    /**
     * Generates the keywords based on the given user input.
     *
     * @param args user input in command line
     * @return a String array that contains the generated keywords
     * @throws ParseException if the user input does not match defined command usage
     */
    private static ArrayList<String> generateKeywords(String args) {

        String[] split = args.split(PREFIXES_SPACE);
        ArrayList<String> keywords = new ArrayList<>();

        for (String s : split) {
            if (s != null && !s.equals("")) {
                keywords.add(s);
            }
        }
        logger.info("keywords found: " + keywords);
        return keywords;
    }

    /**
     * Generates the keywords based on the given user input. This method is called with the
     * guarantee that there is a module prefix included. Module validity checking is
     * enforced.
     *
     * @param args user input in command line
     * @return a String array that contains the generated keywords
     * @throws ParseException if the user input does not match defined command usage
     */
    private ArrayList<String> generateKeywordsWithModuleChecking(String args) throws ParseException {
        String[] splitByModule = args.split(PREFIX_MODULE.getPrefix());

        logger.info("Split args: " + Arrays.toString(splitByModule));

        if (splitByModule.length < 2 || allEmptyTags(splitByModule)) {
            return new ArrayList<>();
        }

        String moduleHalf = splitByModule[1];
        performValidation(moduleHalf.split(PREFIXES_SPACE)[0]);

        return generateKeywords(args);
    }

    private void validPrefixes(String args) throws ParseException {
        Pattern prefixesPattern = Pattern.compile(VALID_PREFIXES);
        Matcher prefixesMatcher = prefixesPattern.matcher(args);
        boolean hasPrefixes = prefixesMatcher.find();

        if (!hasPrefixes) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Checks if the user inputs all empty tags.
     *
     * @param args user input arguments after splitting by m/ tag
     * @return true if all tags are empty, false otherwise.
     */
    private boolean allEmptyTags(String[] args) {
        String[] checkTags;
        Pattern prefixesPattern = Pattern.compile(VALID_PREFIXES);
        Matcher moduleMatcher;

        for (String s : args) {
            if (!s.equals("")) {
                checkTags = s.trim().split(" ");
                logger.info("Examining Tags in Arr: " + Arrays.toString(checkTags));
                for (String tags : checkTags) {
                    logger.info("Examining Tags: " + tags);
                    moduleMatcher = prefixesPattern.matcher(tags);
                    if (!moduleMatcher.find()) {
                        logger.info("All tags are NOT empty");
                        return false;
                    }
                }
            }
        }
        logger.info("All tags are empty");
        return true;
    }

    /**
     * Obtains the module string separated by white spaces and performs module
     * validity checking on them.
     *
     * @param args module string separated by white spaces
     * @throws ParseException input module is invalid
     */
    private void performValidation(String args) throws ParseException {
        String[] modules = args.split(" ");
        checkModuleValidity(modules);
    }

    /**
     * Checks if the provided modules are valid.
     *
     * @param modules an array of modules to be checked for validity
     * @throws ParseException if any one of the modules do not match a valid module code
     */
    private void checkModuleValidity(String[] modules) throws ParseException {

        logger.info("Checking module validity");

        Pattern pattern;
        Matcher matcher;

        pattern = Pattern.compile(VALIDATION_REGEX);

        for (String s : modules) {
            logger.info("Validating " + s);
            matcher = pattern.matcher(s.toUpperCase(Locale.ROOT));
            if (!matcher.find() && !Objects.equals(s, "")) {
                throw new ParseException(MESSAGE_CONSTRAINTS);
            }
        }
    }

}
