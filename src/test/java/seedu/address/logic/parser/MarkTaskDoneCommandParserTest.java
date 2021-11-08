package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.MarkTaskDoneCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside the MarkTaskDoneCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the MarkTaskDoneCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class MarkTaskDoneCommandParserTest {

    private final MarkTaskDoneCommandParser parser = new MarkTaskDoneCommandParser();

    @Test
    public void parse_validArgs_returnsMarkTaskDoneCommand() {
        String userInput = String.valueOf(INDEX_FIRST_TASK.getOneBased());

        assertParseSuccess(parser, userInput,
                new MarkTaskDoneCommand(List.of(INDEX_FIRST_TASK)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", ParserUtil.MESSAGE_INVALID_INDEX);
    }
}
