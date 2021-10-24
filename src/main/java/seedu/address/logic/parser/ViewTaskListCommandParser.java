package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewTaskListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code ViewTaskListCommand} object.
 */
public class ViewTaskListCommandParser implements Parser<ViewTaskListCommand>{
    private final static String VALID_INPUT_WITHOUT_FLAGS_REGEX = "[0-9]+";

    private final static String VALID_INPUT_WITH_FLAGS_REGEX = "[0-9]\\s-f\\s[\\w\\s]+";

    /**
     * Parses the given {@code String} of arguments in the context of the
     * {@code ViewTaskListCommand} and returns a ViewTaskListCommand object
     * for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewTaskListCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewTaskListCommand.MESSAGE_USAGE));
        } else {
            Pattern validPatternWithoutFlags = Pattern.compile(VALID_INPUT_WITHOUT_FLAGS_REGEX);
            Pattern validPatternWithFlags = Pattern.compile(VALID_INPUT_WITH_FLAGS_REGEX);
            Matcher matcherWithoutFlags = validPatternWithoutFlags.matcher(trimmedArgs);
            Matcher matcherWithFlags = validPatternWithFlags.matcher(trimmedArgs);
            if (matcherWithoutFlags.matches()) {
                Index index = ParserUtil.parseIndex(trimmedArgs);
                return new ViewTaskListCommand(index);
            } else if (matcherWithFlags.matches()) {
                String[] flagAndKeywords = trimmedArgs.split(" ");
                Index index = ParserUtil.parseIndex(flagAndKeywords[0]);
                List<String> keywords = Arrays.asList(flagAndKeywords[2].split(" "));
                return new ViewTaskListCommand(index, keywords);
            } else {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewTaskListCommand.MESSAGE_USAGE));
            }
        }
    }
}
