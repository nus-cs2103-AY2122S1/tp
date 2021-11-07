package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE_REQUIREMENTS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

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
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetRoleReqCommand.getHelpMessage()));
    }

    @Test
    public void parse_validArgs_returnsSetRoleReqCommand() {
        // Normal input
        Set<String> expectedRoleReqSet1 = new HashSet<>(){};
        expectedRoleReqSet1.add("kitchen-1");
        expectedRoleReqSet1.add("bartender-1");
        SetRoleReqCommand expectedRoleReqCommand1 = new SetRoleReqCommand(expectedRoleReqSet1);
        assertParseSuccess(parser, " " + PREFIX_ROLE_REQUIREMENTS + "kitchen-1 "
                        + PREFIX_ROLE_REQUIREMENTS + "bartender-1",
                expectedRoleReqCommand1);

        // Input with trailing spaces
        Set<String> expectedRoleReqSet2 = new HashSet<>(){};
        expectedRoleReqSet2.add("floor-2");
        SetRoleReqCommand expectedRoleReqCommand2 = new SetRoleReqCommand(expectedRoleReqSet2);
        assertParseSuccess(parser, "  " + PREFIX_ROLE_REQUIREMENTS + "floor-2  \n",
                expectedRoleReqCommand2);
    }

    @Test
    public void parse_invalidArgs_returnsHelpMessage() {
        // Invalid inputs
        assertParseFailure(parser, " " + PREFIX_ROLE_REQUIREMENTS + "kitchen ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetRoleReqCommand.getHelpMessage()));
        assertParseFailure(parser, " " + PREFIX_ROLE_REQUIREMENTS + "1 ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetRoleReqCommand.getHelpMessage()));

        // Role that does not exist
        assertParseFailure(parser, " " + PREFIX_ROLE_REQUIREMENTS + "cashier-1 ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetRoleReqCommand.getHelpMessage()));
    }
}
