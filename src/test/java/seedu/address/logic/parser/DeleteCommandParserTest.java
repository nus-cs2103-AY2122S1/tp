package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_TOO_MANY_FIELDS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.model.client.ClientId;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validClientId_returnsDeleteCommand() {
        ClientId clientId = new ClientId("1");
        assertParseSuccess(parser, " 1", new DeleteCommand(List.of(clientId)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPrefix_throwsParseException() {
        assertParseFailure(parser, "2 t/ff", String.format(MESSAGE_TOO_MANY_FIELDS, DeleteCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "4 5 i/4 p/33 c/next", String.format(MESSAGE_TOO_MANY_FIELDS,
                DeleteCommand.MESSAGE_USAGE));
    }

}
