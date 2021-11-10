package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.GroupCommand;

public class GroupCommandParserTest {
    private GroupCommandParser parser = new GroupCommandParser();

    @Test
    public void groupParse_allNamesAndTagValid_createGroupCommand() {
        assertParseSuccess(parser, "group t/test n/Shui Jie, Tester",
                new GroupCommand("test", "Shui Jie, Tester"));
    }

    @Test
    public void groupParse_validNamesAndEmptyTag_messageFailErrorTag() {
        assertParseFailure(parser, "group t/ n/Shui Jie, Tester",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupCommand.MESSAGE_ERROR_TAG));
    }

    @Test
    public void groupParse_emptyNamesAndValidTag_messageFailErrorName() {
        assertParseFailure(parser, "group t/test n/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupCommand.MESSAGE_ERROR_NAMES));
    }

    @Test
    public void groupParse_emptyNamesAndEmptyTag_messageFailErrorTagAndName() {
        assertParseFailure(parser, "group t/ n/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupCommand.MESSAGE_ERROR_TAG
                        + "\n"
                        + GroupCommand.MESSAGE_ERROR_NAMES));
    }
}
