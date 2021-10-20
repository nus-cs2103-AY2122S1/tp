package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

/**
 * Contains helper methods for testing command parsers.
 */
public class CommandParserTestUtil {

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} is successful and the command created
     * equals to {@code expectedCommand}.
     */
    public static void assertParseSuccess(Parser parser, String userInput, Command expectedCommand) {
        // XXX: use stub?
        Model model = new ModelManager();
        try {
            Command command = parser.parse(userInput, model);
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
        Model model = new ModelManager();
        try {
            parser.parse(userInput, model);
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (ParseException | RuntimeException e) {
            if (e instanceof RuntimeException && !(e.getCause() instanceof ParseException)) {
                throw new AssertionError("The expected ParseException was not thrown.");
            }
            assertEquals(expectedMessage, e.getMessage());
        }
    }
}
