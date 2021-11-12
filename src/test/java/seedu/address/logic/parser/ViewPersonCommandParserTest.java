package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.INVALID_COMMAND_INVALID_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.persons.ViewPersonCommand;
import seedu.address.logic.parser.persons.ViewPersonCommandParser;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the ViewPersonCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the ViewPersonCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class ViewPersonCommandParserTest {

    private ViewPersonCommandParser parser = new ViewPersonCommandParser();

    @Test
    public void parse_validArgs_returnsViewCommand() {
        assertParseSuccess(parser, "1", new ViewPersonCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // empty
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ViewPersonCommand.MESSAGE_USAGE));

        // invalid index
        assertParseFailure(parser, "a", INVALID_COMMAND_INVALID_INDEX);
    }
}
