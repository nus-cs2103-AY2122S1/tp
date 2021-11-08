package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUITION_CLASS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Contains helper methods for testing command parsers.
 */
public class CommandParserTestUtil {

    public static final String VALID_INDICES = "1 2";
    public static final String DUPLICATE_INDICES = "1 2 1";
    public static final String VALID_INDEX = "1";
    public static final String INVALID_INDEX = "-1";
    public static final String INVALID_INDEX_ZERO = "0";
    public static final String SPACE = " ";
    public static final String RANDOM_STRING_ARG = "aPTgH";
    public static final String RANDOM_SYMBOL = "$$";

    public static final String VALID_STUDENT_INDEX = " " + PREFIX_STUDENT_INDEX + VALID_INDEX;
    public static final String VALID_STUDENT_INDICES = " " + PREFIX_STUDENT_INDEX + VALID_INDICES;

    public static final String VALID_CLASS_INDEX = " " + PREFIX_TUITION_CLASS + VALID_INDEX;
    public static final String VALID_CLASS_INDICES = " " + PREFIX_TUITION_CLASS + VALID_INDICES;

    public static final String INVALID_CLASS_INDEX = " " + PREFIX_TUITION_CLASS + INVALID_INDEX;
    public static final String INVALID_STUDENT_INDEX = " " + PREFIX_STUDENT_INDEX + INVALID_INDEX;
    public static final String INVALID_PREFIX = "x/";

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} is successful and the command created
     * equals to {@code expectedCommand}.
     */
    public static void assertParseSuccess(Parser parser, String userInput, Command expectedCommand) {
        try {
            Command command = parser.parse(userInput);
            assertEquals(expectedCommand, command);
        } catch (ParseException pe) {
            throw new IllegalArgumentException("Invalid userInput.", pe);
        }
    }

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} is unsuccessful and the error message
     * equals to {@code expectedMessage}.
     */
    public static void assertParseFailure(Parser parser, String userInput, String expectedMessage) {
        try {
            parser.parse(userInput);
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (ParseException pe) {
            assertEquals(expectedMessage, pe.getMessage());
        }
    }
}
