package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private final DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validIndex_returnsDeleteCommand() {
        assertParseSuccess(parser, " -i 1", new DeleteCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_validName_returnsDeleteCommand() {
        assertParseSuccess(parser, " -n Alice Pauline", new DeleteCommand(ALICE.getName()));
    }

    @Test
    public void parse_invalidName_throwsParseException() {
        String blankName = " -n ";
        String whitespaceName = " -n  ";
        assertParseFailure(parser, blankName, String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        assertParseFailure(parser, whitespaceName, String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        String zeroIndex = " -i 0";
        String negativeIndex = " -i -1";
        assertParseFailure(parser, zeroIndex, ParserUtil.MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, negativeIndex, ParserUtil.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidRole_throwsParseException() {
        String emptyRole = " -r ";
        String wrongRole = " -r cashiers";
        assertParseFailure(parser, emptyRole, String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        assertParseFailure(parser, wrongRole, String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidStatus_throwsParseException() {
        String emptyStatus = " -s ";
        String wrongStatus = " -s intern";
        assertParseFailure(parser, emptyStatus, String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        assertParseFailure(parser, wrongStatus, String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_moreThanOneAcceptedPrefix_throwsParseException() {
        assertParseFailure(parser, " -n Alice Pauline -i 1", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }
}
