package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.SetRoleReqCommand;
import seedu.address.storage.RoleReqStorage;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

public class SetRoleReqCommandParserTest {

    private SetRoleReqCommandParser parser = new SetRoleReqCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        RoleReqStorage.reset();
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetRoleReqCommand.HELP_MESSAGE));
    }

    @Test
    public void parse_validArgs_returnsSetRoleReqCommand() {

    }
}
