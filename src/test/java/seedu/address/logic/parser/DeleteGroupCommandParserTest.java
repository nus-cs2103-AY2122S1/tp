package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.GROUP_NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GROUP_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_NAME_CS2103T;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteGroupCommand;
import seedu.address.model.group.GroupName;

public class DeleteGroupCommandParserTest {

    private DeleteGroupCommandParser parser = new DeleteGroupCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, GROUP_NAME_DESC_AMY,
                new DeleteGroupCommand(new GroupName(VALID_GROUP_NAME_CS2103T)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Invalid format
        assertParseFailure(parser, "%",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteGroupCommand.MESSAGE_USAGE));

        // Missing g/ prefix
        assertParseFailure(parser, VALID_GROUP_NAME_CS2103T,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteGroupCommand.MESSAGE_USAGE));

        // Missing argument, only prefix available
        assertParseFailure(parser, " " + PREFIX_GROUP_NAME,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteGroupCommand.MESSAGE_USAGE));

        // Invalid group name
        assertParseFailure(parser, INVALID_GROUP_NAME_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteGroupCommand.MESSAGE_USAGE));

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + VALID_GROUP_NAME_CS2103T,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteGroupCommand.MESSAGE_USAGE));
    }
}
