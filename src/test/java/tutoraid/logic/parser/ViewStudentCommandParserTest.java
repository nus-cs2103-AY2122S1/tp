package tutoraid.logic.parser;

import static tutoraid.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tutoraid.testutil.TypicalIndexes.INDEX_FIRST_ITEM;

import org.junit.jupiter.api.Test;

import tutoraid.logic.commands.ViewStudentCommand;

public class ViewStudentCommandParserTest {

    private ViewStudentCommandParser parser = new ViewStudentCommandParser();

    @Test
    public void parse_validArgs_returnsViewCommand() {
        CommandParserTestUtil.assertParseSuccess(parser, "1", new ViewStudentCommand(INDEX_FIRST_ITEM));
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ViewStudentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ViewStudentCommand.MESSAGE_USAGE));
    }
}
