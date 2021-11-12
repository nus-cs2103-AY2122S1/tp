package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.comparators.PersonComparator.MESSAGE_INVALID_CATEGORY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_COUNT;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.category.Category;

public class FilterCommandParserTest {
    private static final String INVALID_CATEGORY = "phone";
    private static final String INVALID_COUNT = "-2";
    private static final String NON_EMPTY_CATEGORY = "called";
    private static final String NON_ZERO_COUNT = "4";

    private final Category nonEmptyCategory = new Category("called");
    private final Integer nonZeroCount = 4;
    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_categorySpecified_success() {
        // have count
        String userInput = NON_EMPTY_CATEGORY + " " + NON_ZERO_COUNT;
        FilterCommand expectedCommand = new FilterCommand(nonEmptyCategory, nonZeroCount);
        assertParseSuccess(parser, userInput, expectedCommand);

        // no count
        userInput = NON_EMPTY_CATEGORY + " " + "";
        expectedCommand = new FilterCommand(nonEmptyCategory, Integer.MAX_VALUE);
        assertParseSuccess(parser, userInput, expectedCommand);

        // no count
        userInput = NON_EMPTY_CATEGORY + " ";
        expectedCommand = new FilterCommand(nonEmptyCategory, Integer.MAX_VALUE);
        assertParseSuccess(parser, userInput, expectedCommand);

        // multiple parameters, last parameter is count
        userInput = NON_ZERO_COUNT + " " + NON_EMPTY_CATEGORY + " " + NON_ZERO_COUNT;
        expectedCommand = new FilterCommand(nonEmptyCategory, nonZeroCount);
        assertParseSuccess(parser, userInput, expectedCommand);

        // multiple parameters, last parameter is category
        userInput = NON_ZERO_COUNT + " " + NON_ZERO_COUNT + " " + NON_EMPTY_CATEGORY;
        expectedCommand = new FilterCommand(nonEmptyCategory, Integer.MAX_VALUE);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MESSAGE_INVALID_CATEGORY + FilterCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));

        // null input
        assertThrows(NullPointerException.class, () -> parser.parse(null));

        // wrong category
        assertParseFailure(parser, NON_ZERO_COUNT, expectedMessage);

        // no category
        String userInput = " " + NON_ZERO_COUNT;
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_invalidCount_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MESSAGE_INVALID_COUNT + FilterCommand.MESSAGE_USAGE);
        String userInput = NON_EMPTY_CATEGORY + " " + INVALID_COUNT;
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_invalidCategory_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MESSAGE_INVALID_CATEGORY + FilterCommand.MESSAGE_USAGE);
        String userInput = INVALID_CATEGORY + " " + NON_ZERO_COUNT;
        assertParseFailure(parser, userInput, expectedMessage);
    }
}
