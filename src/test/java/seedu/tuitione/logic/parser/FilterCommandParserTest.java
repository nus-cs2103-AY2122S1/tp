package seedu.tuitione.logic.parser;

import static seedu.tuitione.commons.core.Messages.HEADER_ALERT;
import static seedu.tuitione.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tuitione.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.tuitione.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.tuitione.logic.commands.FilterCommand;
import seedu.tuitione.model.lesson.Subject;
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
        FilterCommand expectedFilterCommandTest1 = new FilterCommand(new Grade("S2"), null);
        FilterCommand expectedFilterCommandTest2 = new FilterCommand(null, new Subject("English"));
        FilterCommand expectedFilterCommandTest3 = new FilterCommand(new Grade("S2"), new Subject("English"));

        // no leading and trailing whitespaces, only grade used to filter
        assertParseSuccess(parser, " g/S2", expectedFilterCommandTest1);

        // multiple whitespaces, only grade used to filter
        assertParseSuccess(parser, "     g/S2    ", expectedFilterCommandTest1);

        // no leading and trailing whitespaces, only subject used to filter
        assertParseSuccess(parser, " s/English", expectedFilterCommandTest2);

        // multiple whitespaces, only subject used to filter
        assertParseSuccess(parser, "    s/English   ", expectedFilterCommandTest2);

        // no leading and trailing whitespaces, both grade and student used to filter
        assertParseSuccess(parser, " g/S2 s/English", expectedFilterCommandTest3);

        // multiple whitespaces, both grade and student used to filter
        assertParseSuccess(parser, "   g/S2    s/English  ", expectedFilterCommandTest3);
    }

    @Test
    public void parse_invalidGrade_throwsParseException() {
        // without input for subject field
        assertParseFailure(parser, " g/A5", HEADER_ALERT + Grade.GRADE_MESSAGE_CONSTRAINTS);

        // with input for subject field
        assertParseFailure(parser, " g/A5 s/English",
                HEADER_ALERT + Grade.GRADE_MESSAGE_CONSTRAINTS);
    }
}
