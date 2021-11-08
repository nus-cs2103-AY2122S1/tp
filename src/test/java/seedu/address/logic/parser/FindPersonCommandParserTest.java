package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.model.person.NameContainsKeywordsPredicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.model.person.Module.MESSAGE_CONSTRAINTS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class FindPersonCommandParserTest {

    private FindPersonCommandParser parser = new FindPersonCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {

        // no leading and trailing whitespaces
        FindPersonCommand expectedFindCommand =
                new FindPersonCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "n/Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "n/ \n Alice \n \t Bob  \t", expectedFindCommand);

        //  finding a valid module
        FindPersonCommand expectedFindCommandWithModule =
                new FindPersonCommand(new NameContainsKeywordsPredicate(List.of("CS1101S")));
        assertParseSuccess(parser, "m/CS1101S", expectedFindCommandWithModule);

        // finding a valid module with different case sensitivity
        expectedFindCommandWithModule = new FindPersonCommand(new NameContainsKeywordsPredicate(List.of("cS1101s")));
        assertParseSuccess(parser, "m/Cs1101S", expectedFindCommandWithModule);

        // finding a valid name
        FindPersonCommand expectedFindCommandWithName =
                new FindPersonCommand(new NameContainsKeywordsPredicate(List.of("Becky")));
        assertParseSuccess(parser, "n/Becky", expectedFindCommandWithName);

        // finding a valid name with different case sensitivity
        assertParseSuccess(parser, "n/BECKY", expectedFindCommandWithName);

        // finding both name and valid module
        FindPersonCommand expectedFindCommandWithBoth =
                new FindPersonCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob", "CS1101S")));
        assertParseSuccess(parser, "n/Alice Bob m/CS1101S", expectedFindCommandWithBoth);

        // finding both name and valid module with different case sensitivity and white spaces
        assertParseSuccess(parser, "n/ \t AlICe \n\n\nBOB m/cs1101s", expectedFindCommandWithBoth);

        // inverted prefixes
        FindPersonCommand expectedFindCommandInverted =
                new FindPersonCommand(new NameContainsKeywordsPredicate(Arrays.asList("MA1521", "Alice")));
        assertParseSuccess(parser, "m/MA1521 n/Alice", expectedFindCommandInverted);

        // finding a valid tag
        FindPersonCommand expectedFindCommandWithTag =
                new FindPersonCommand(new NameContainsKeywordsPredicate(List.of("Group10")));
        assertParseSuccess(parser, "t/Group10", expectedFindCommandWithTag);

        // finding a valid tag with different case sensitivity
        assertParseSuccess(parser, "t/group10", expectedFindCommandWithTag);

        // finding all three prefixes
        FindPersonCommand expectedFindCommandAllThree =
                new FindPersonCommand(new NameContainsKeywordsPredicate(List.of("Alice", "MA1521", "lab15")));
        assertParseSuccess(parser, "n/alice m/ma1521 t/LAB15", expectedFindCommandAllThree);

    }

    @Test
    public void parse_emptyArgsTags_success() {

        FindPersonCommand expectedFindCommandNone =
                new FindPersonCommand(new NameContainsKeywordsPredicate(new ArrayList<>()));

        // finding an empty prefix m/
        assertParseSuccess(parser, "m/", expectedFindCommandNone);

        // finding an empty prefix n/
        assertParseSuccess(parser, "n/", expectedFindCommandNone);

        // finding an empty prefix t/
        assertParseSuccess(parser, "t/", expectedFindCommandNone);

        // finding multiple empty prefixes m/ t/
        assertParseSuccess(parser, "m/ t/", expectedFindCommandNone);

        // finding multiple empty prefixes m/ n/
        assertParseSuccess(parser, "m/ n/", expectedFindCommandNone);

        // finding multiple empty prefixes m/ n/ t/
        assertParseSuccess(parser, "m/ n/ t/", expectedFindCommandNone);
    }

    @Test
    public void parse_invalidArgs_returnsError() {
        // no prefix n/ or m/
        assertParseFailure(parser, "Alice Bob CS1101S",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));

        // invalid module codes
        assertParseFailure(parser, "m/Alice", MESSAGE_CONSTRAINTS);

        // invalid module codes with valid name
        assertParseFailure(parser, "n/Alice m/Bob", MESSAGE_CONSTRAINTS);

        // empty
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));

        // whitespace
        assertParseFailure(parser, "\n\t\n\t\n\t\n\t",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));

        // random prefixes
        assertParseFailure(parser, "a/assignment",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));

        // invalid module input
        assertParseFailure(parser, "m/CsSsSSsssSSsssSsSSSSS1000",
                MESSAGE_CONSTRAINTS);

        // invalid module input
        assertParseFailure(parser, "m/GER1000000000000000000000000000",
                MESSAGE_CONSTRAINTS);

        // invalid module input with multiple valid tags
        assertParseFailure(parser, "n/Alice t/Lab10 m/GEQqQQ100000EEE",
                MESSAGE_CONSTRAINTS);
    }
}
