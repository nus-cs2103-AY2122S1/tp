package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.SetTablesCommandParser.MESSAGE_INVALID_SIZE_OR_COUNT;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SetTablesCommand;

class SetTablesCommandParserTest {
    private SetTablesCommandParser parser = new SetTablesCommandParser();

    @Test
    public void parse_allSingleNumbers_success() {
        SetTablesCommand expected = new SetTablesCommand(List.of(1, 2, 3, 4, 5));

        assertParseSuccess(parser, "1, 2, 3, 4, 5", expected);
    }

    @Test
    public void parse_allNumbersWithCount_success() {
        SetTablesCommand expected = new SetTablesCommand(List.of(1, 1, 2, 2, 3, 3, 4, 4, 5, 5));

        assertParseSuccess(parser, "1x2, 2x2, 3x2, 4x2, 5x2", expected);
    }

    @Test
    public void parse_combinationOfNumbersOnlyAndNumbersWithCount_success() {
        SetTablesCommand expected = new SetTablesCommand(List.of(1, 1, 2, 3, 3, 4, 5, 5));

        assertParseSuccess(parser, "1x2, 2, 3x2, 4, 5x2", expected);
    }

    @Test
    public void parse_combinationOfNumbersOnlyAndNumbersWithCountWithSpaces_success() {
        SetTablesCommand expected = new SetTablesCommand(List.of(1, 1, 2, 3, 3, 4, 5, 5));

        assertParseSuccess(parser, "  1  x  2,      2, 3\n   x2, 4   , 5x2    \n", expected);
    }

    @Test
    public void parse_invalidValue_failure() {
        String message = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetTablesCommand.MESSAGE_USAGE);
        // invalid table size, no count: 0
        assertParseFailure(
                parser,
                "0",
                MESSAGE_INVALID_SIZE_OR_COUNT
        );

        // invalid table size, no count : negative
        assertParseFailure(
                parser,
                "-1",
                MESSAGE_INVALID_SIZE_OR_COUNT
        );

        // invalid table size, with valid count: 0
        assertParseFailure(
                parser,
                "0x2",
                MESSAGE_INVALID_SIZE_OR_COUNT
        );

        // invalid table size, with valid count : negative
        assertParseFailure(
                parser,
                "-1x2",
                MESSAGE_INVALID_SIZE_OR_COUNT
        );

        // valid table size, invalid count  : 0
        assertParseFailure(
                parser,
                "1x0",
                MESSAGE_INVALID_SIZE_OR_COUNT
        );

        // valid table size, invalid count  : negative
        assertParseFailure(
                parser,
                "1x-1",
                MESSAGE_INVALID_SIZE_OR_COUNT
        );

        // valid table size, invalid count  : blank count
        assertParseFailure(
                parser,
                "1x",
                message
        );

        // invalid table size, valid count  : blank size
        assertParseFailure(
                parser,
                "x2",
                message
        );

        // invalid table size, invalid count  : blank size and count
        assertParseFailure(
                parser,
                "x",
                message
        );

        // valid table size, valid count  : multiple x
        assertParseFailure(
                parser,
                "1xx2",
                message
        );

        // invalid table size, invalid count
        assertParseFailure(
                parser,
                "-1x0",
                MESSAGE_INVALID_SIZE_OR_COUNT
        );

        // invalid table size : non-integer
        assertParseFailure(
                parser,
                "abc",
                message
        );

        // invalid table size : non-integer
        assertParseFailure(
                parser,
                "-",
                message
        );

        // invalid table size : blank
        assertParseFailure(
                parser,
                " ",
                message
        );
    }
}
