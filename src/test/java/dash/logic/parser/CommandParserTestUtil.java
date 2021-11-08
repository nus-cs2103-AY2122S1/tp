package dash.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dash.logic.commands.Command;
import dash.logic.parser.exceptions.ParseException;
import dash.model.person.Person;
import javafx.collections.ObservableList;

/**
 * Contains helper methods for testing command parsers.
 */
public class CommandParserTestUtil {

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
     * Asserts that the parsing of {@code userInput} by {@code parser} is successful and the command created
     * equals to {@code expectedCommand}.
     * For use with Commands that require a personList.
     */
    public static void assertParseSuccessWithPersonList(ParserRequiringPersonList parser,
                                                        String userInput, ObservableList<Person> personList,
                                                        Command expectedCommand) {
        try {
            Command command = parser.parse(userInput, personList);
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

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} is unsuccessful and the error message
     * equals to {@code expectedMessage}.
     * For use with Commands that require a personList.
     */
    public static void assertParseFailureWithPersonList(ParserRequiringPersonList parser,
                                                        String userInput, ObservableList<Person> personList,
                                                        String expectedMessage) {
        try {
            parser.parse(userInput, personList);
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (ParseException pe) {
            assertEquals(expectedMessage, pe.getMessage());
        }
    }
}
