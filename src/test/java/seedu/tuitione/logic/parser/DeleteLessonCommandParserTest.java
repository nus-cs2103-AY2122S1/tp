package seedu.tuitione.logic.parser;

import static seedu.tuitione.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tuitione.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.tuitione.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.tuitione.logic.commands.DeleteLessonCommand;

public class DeleteLessonCommandParserTest {

    private DeleteLessonCommandParser parser = new DeleteLessonCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteLessonCommand(INDEX_FIRST_STUDENT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteLessonCommand.MESSAGE_USAGE));
    }
}
