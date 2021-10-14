package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.COUNT_DESC_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BAGEL;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalItems.BAGEL;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.model.item.Name;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "apple" and "apple t/fruit" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_nameNoId_returnsDeleteCommand() {
        // name only
        assertParseSuccess(parser, VALID_NAME_BAGEL, new DeleteCommand(new Name(VALID_NAME_BAGEL), -1));
        // name and count
        assertParseSuccess(parser, VALID_NAME_BAGEL + " " + COUNT_DESC_BAGEL,
                new DeleteCommand(new Name(VALID_NAME_BAGEL), BAGEL.getCount()));
    }

    @Test
    public void parse_idNoName_returnsDeleteCommand() {
        // id only
        assertParseSuccess(parser, ID_DESC_BAGEL, new DeleteCommand(VALID_ID_BAGEL, -1));
        // id and count
        assertParseSuccess(parser, ID_DESC_BAGEL + " " + COUNT_DESC_BAGEL,
                new DeleteCommand(VALID_ID_BAGEL, BAGEL.getCount()));
    }


    @Test
    public void parse_bothNameOrId_throwsParseException() {
        assertParseFailure(parser, VALID_NAME_BAGEL + " " + ID_DESC_BAGEL,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }
}
