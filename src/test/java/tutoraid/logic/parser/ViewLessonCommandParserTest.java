package tutoraid.logic.parser;

import static tutoraid.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tutoraid.testutil.TypicalIndexes.INDEX_FIRST_ITEM;

import org.junit.jupiter.api.Test;

import tutoraid.logic.commands.ViewLessonCommand;

public class ViewLessonCommandParserTest {

    private ViewLessonCommandParser parser = new ViewLessonCommandParser();

    @Test
    public void parse_validArgs_returnsViewCommand() {
        CommandParserTestUtil.assertParseSuccess(parser, "1", new ViewLessonCommand(INDEX_FIRST_ITEM));
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ViewLessonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ViewLessonCommand.MESSAGE_USAGE));
    }
}
