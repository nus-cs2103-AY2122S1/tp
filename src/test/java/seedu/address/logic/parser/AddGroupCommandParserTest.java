package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_DESCRIPTION_CS1231S;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_DESCRIPTION_CS1231S_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_NAME_CS1231S;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_NAME_CS1231S_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddGroupCommand;
import seedu.address.model.group.Description;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;

public class AddGroupCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddGroupCommand.MESSAGE_USAGE);

    private AddGroupCommandParser parser = new AddGroupCommandParser();

    @Test
    public void parse_missingParts_failure() {
        //no group name specified
        assertParseFailure(parser, VALID_GROUP_DESCRIPTION_CS1231S_DESC, MESSAGE_INVALID_FORMAT);

        //no group description specified
        assertParseFailure(parser, VALID_GROUP_NAME_CS1231S_DESC, MESSAGE_INVALID_FORMAT);

        //no field specified
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
        String userInput = " " + VALID_GROUP_NAME_CS1231S_DESC + VALID_GROUP_DESCRIPTION_CS1231S_DESC;
        Group expectedGroup = new Group(new GroupName(VALID_GROUP_NAME_CS1231S),
                new Description(VALID_GROUP_DESCRIPTION_CS1231S));
        AddGroupCommand expectedCommand = new AddGroupCommand(expectedGroup);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}

