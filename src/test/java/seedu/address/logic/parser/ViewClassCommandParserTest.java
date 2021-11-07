package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_CLASS_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewClassCommand;

class ViewClassCommandParserTest {
    private ViewClassCommandParser parser = new ViewClassCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "xxx",
                String.format(MESSAGE_INVALID_CLASS_DISPLAYED_INDEX + MESSAGE_INVALID_COMMAND_FORMAT,
                ViewClassCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsViewClassCommand() {
        assertParseSuccess(parser, "1", new ViewClassCommand(INDEX_FIRST_STUDENT));
    }

}
