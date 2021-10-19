package seedu.notor.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.notor.logic.commands.Command;
import seedu.notor.logic.parser.exceptions.ParseException;

/**
 * Contains helper methods for testing command parsers.
 */
public class CommandParserTestUtil {

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} is successful and the command created
     * equals to {@code expectedCommand}.
     */
    public static void assertParseSuccess(Command commandCreated, Command expectedCommand) {
        assertEquals(expectedCommand, commandCreated);
    }

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} is unsuccessful and the error message
     * equals to {@code expectedMessage}.
     */
    public static void assertParseFailure(NotorParser parser, String input, String expectedMessage) {
        try {
            parser.parseCommand(input);
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (ParseException pe) {
            assertEquals(expectedMessage, pe.getMessage());
        }
    }
}
