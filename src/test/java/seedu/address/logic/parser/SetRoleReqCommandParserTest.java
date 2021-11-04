package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SetRoleReqCommand;
import seedu.address.storage.RoleReqStorage;

public class SetRoleReqCommandParserTest {

    private SetRoleReqCommandParser parser = new SetRoleReqCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() throws IOException {
        RoleReqStorage.load();
        RoleReqStorage.reset();
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetRoleReqCommand.HELP_MESSAGE));
    }

    @Test
    public void parse_validArgs_returnsSetRoleReqCommand() {

    }
}
