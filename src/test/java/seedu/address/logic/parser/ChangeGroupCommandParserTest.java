package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_NAME_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_NAME_CS2103T_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ChangeGroupCommand;
import seedu.address.model.group.GroupName;
import seedu.address.model.student.Name;

public class ChangeGroupCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangeGroupCommand.MESSAGE_USAGE);

    private ChangeGroupCommandParser parser = new ChangeGroupCommandParser();

    @Test
    public void parse_missingParts_failure() {
        //no student name specified
        assertParseFailure(parser, VALID_GROUP_NAME_CS2103T_DESC, MESSAGE_INVALID_FORMAT);

        //no group name specified
        assertParseFailure(parser, NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        //no fields specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid prefix being parsed as preamble
        assertParseFailure(parser, " i/invalid", MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "invalid arguments", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = " " + NAME_DESC_AMY + VALID_GROUP_NAME_CS2103T_DESC;
        ChangeGroupCommand expectedCommand = new ChangeGroupCommand(new Name(VALID_NAME_AMY),
                new GroupName(VALID_GROUP_NAME_CS2103T));

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
