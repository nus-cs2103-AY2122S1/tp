package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_SYMBOL;
import static seedu.address.logic.parser.CommandParserTestUtil.DUPLICATE_INDICES;
import static seedu.address.logic.parser.CommandParserTestUtil.INVALID_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.INVALID_INDEX_ZERO;
import static seedu.address.logic.parser.CommandParserTestUtil.RANDOM_STRING_ARG;
import static seedu.address.logic.parser.CommandParserTestUtil.RANDOM_SYMBOL;
import static seedu.address.logic.parser.CommandParserTestUtil.SPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.VALID_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.VALID_INDICES;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteClassCommand;


/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteClassCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteClassCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteClassCommand.MESSAGE_USAGE);
    private DeleteClassCommandParser parser = new DeleteClassCommandParser();

    @Test
    public void parse_invalidArgs_failure() {
        //random string
        assertParseFailure(parser, SPACE + RANDOM_STRING_ARG, MESSAGE_INVALID_FORMAT);

        //random symbol
        assertParseFailure(parser, SPACE + RANDOM_SYMBOL, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        //random string before prefix
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + VALID_INDEX, MESSAGE_INVALID_FORMAT);

        //random symbol before prefix
        assertParseFailure(parser, PREAMBLE_SYMBOL + VALID_INDEX, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidIndex_failure() {
        //negative index
        assertParseFailure(parser, SPACE + INVALID_INDEX, MESSAGE_INVALID_FORMAT);

        //zero index
        assertParseFailure(parser, SPACE + INVALID_INDEX_ZERO, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validIndex_success() {
        List<Index> indices = new ArrayList<>();
        indices.add(INDEX_FIRST);
        DeleteClassCommand deleteClassCommand = new DeleteClassCommand(indices);
        assertParseSuccess(parser, SPACE + VALID_INDEX, deleteClassCommand);
    }

    @Test
    public void parse_invalidIndexWithValidIndex_failure() {
        //negative index with valid index
        assertParseFailure(parser, SPACE + INVALID_INDEX + SPACE + VALID_INDEX, MESSAGE_INVALID_FORMAT);

        //zero index with valid index
        assertParseFailure(parser, SPACE + INVALID_INDEX_ZERO + SPACE + VALID_INDEX, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validMultipleIndex_success() {
        //unique indices
        List<Index> indices = new ArrayList<>();
        indices.add(0, INDEX_SECOND);
        indices.add(1, INDEX_FIRST);
        DeleteClassCommand deleteCommand = new DeleteClassCommand(indices);
        assertParseSuccess(parser, SPACE + VALID_INDICES, deleteCommand);

        //repeated indices, duplicate should be removed in parsing
        List<Index> repeatedIndices = new ArrayList<>();
        repeatedIndices.add(0, INDEX_SECOND);
        repeatedIndices.add(1, INDEX_FIRST);
        assertParseSuccess(parser, SPACE + DUPLICATE_INDICES, new DeleteClassCommand(repeatedIndices));
    }
}
