package seedu.tuitione.logic.parser;

import static seedu.tuitione.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tuitione.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.tuitione.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.tuitione.logic.commands.FilterCommand;
import seedu.tuitione.model.student.Grade;

public class FilterCommandParserTest {
    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterCommand expectedFilterCommand =
                new FilterCommand(new Grade("S2"));
        assertParseSuccess(parser, "S2", expectedFilterCommand);

        // multiple whitespaces
        assertParseSuccess(parser, " S2   ", expectedFilterCommand);
    }

    @Test
    public void parse_invalidGrade_throwsParseException() {
        assertParseFailure(parser, "A5", Grade.GRADE_MESSAGE_CONSTRAINTS);
    }
}
