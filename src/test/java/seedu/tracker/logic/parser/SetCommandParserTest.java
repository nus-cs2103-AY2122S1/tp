package seedu.tracker.logic.parser;

import static seedu.tracker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tracker.logic.commands.CommandTestUtil.ACADEMIC_YEAR_DESC;
import static seedu.tracker.logic.commands.CommandTestUtil.INVALID_ACADEMIC_YEAR_DESC;
import static seedu.tracker.logic.commands.CommandTestUtil.INVALID_ACADEMIC_YEAR_SIGNED_DESC;
import static seedu.tracker.logic.commands.CommandTestUtil.INVALID_MC_DESC;
import static seedu.tracker.logic.commands.CommandTestUtil.INVALID_SEMESTER_DESC;
import static seedu.tracker.logic.commands.CommandTestUtil.INVALID_SEMESTER_SIGNED_DESC;
import static seedu.tracker.logic.commands.CommandTestUtil.MC_DESC_GOAL;
import static seedu.tracker.logic.commands.CommandTestUtil.SEMESTER_DESC;
import static seedu.tracker.logic.commands.CommandTestUtil.VALID_ACADEMIC_YEAR;
import static seedu.tracker.logic.commands.CommandTestUtil.VALID_MC_GOAL;
import static seedu.tracker.logic.commands.CommandTestUtil.VALID_SEMESTER;
import static seedu.tracker.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.tracker.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.tracker.logic.commands.SetCommand;
import seedu.tracker.model.calendar.AcademicCalendar;
import seedu.tracker.model.calendar.AcademicYear;
import seedu.tracker.model.calendar.Semester;
import seedu.tracker.model.module.Mc;

public class SetCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetCommand.MESSAGE_USAGE);

    private SetCommandParser parser = new SetCommandParser();

    @Test
    public void parse_academicCalendarSpecified_success() {
        String userInput = ACADEMIC_YEAR_DESC + SEMESTER_DESC;

        AcademicYear year = new AcademicYear(VALID_ACADEMIC_YEAR);
        Semester semester = new Semester(VALID_SEMESTER);
        AcademicCalendar academicCalendar = new AcademicCalendar(year, semester);
        SetCommand expectedCommand = new SetCommand(academicCalendar);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_mcGoalSpecified_success() {
        String userInput = MC_DESC_GOAL;

        Mc mc = new Mc(VALID_MC_GOAL);
        SetCommand expectedCommand = new SetCommand(mc);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_allFieldsSpecified_failure() {
        assertParseFailure(parser,
                MC_DESC_GOAL + ACADEMIC_YEAR_DESC + SEMESTER_DESC, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_someFieldsMissing_failure() {
        //semester field missing
        assertParseFailure(parser, ACADEMIC_YEAR_DESC, MESSAGE_INVALID_FORMAT);

        //academic year field missing
        assertParseFailure(parser, SEMESTER_DESC, MESSAGE_INVALID_FORMAT);

        //no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid mc goal
        assertParseFailure(parser, INVALID_MC_DESC, MESSAGE_INVALID_FORMAT);

        // invalid academic year, missing semester
        assertParseFailure(parser, INVALID_ACADEMIC_YEAR_DESC, MESSAGE_INVALID_FORMAT);

        // invalid semester, missing academic year
        assertParseFailure(parser, INVALID_SEMESTER_DESC, MESSAGE_INVALID_FORMAT);

        // invalid academic year followed by valid semester
        assertParseFailure(parser,  INVALID_ACADEMIC_YEAR_DESC + SEMESTER_DESC,
                AcademicYear.MESSAGE_CONSTRAINTS);

        //invalid academic year (signed integer) followed by valid semester
        assertParseFailure(parser, INVALID_ACADEMIC_YEAR_SIGNED_DESC + SEMESTER_DESC,
                AcademicYear.MESSAGE_CONSTRAINTS);

        // valid academic year followed by invalid semester (signed integer)
        assertParseFailure(parser,  ACADEMIC_YEAR_DESC + INVALID_SEMESTER_SIGNED_DESC,
                Semester.MESSAGE_CONSTRAINTS);

        // valid academic year followed by invalid semester
        assertParseFailure(parser, ACADEMIC_YEAR_DESC + INVALID_SEMESTER_DESC,
                Semester.MESSAGE_CONSTRAINTS);

        // both invalid values, but only the first invalid value is captured
        assertParseFailure(parser,
                "1" + INVALID_ACADEMIC_YEAR_DESC + INVALID_SEMESTER_DESC, AcademicYear.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_prefixMissing_failure() {
        // missing mc prefix
        assertParseFailure(parser, "" + VALID_MC_GOAL, MESSAGE_INVALID_FORMAT);

        // missing academic year prefix
        assertParseFailure(parser, VALID_ACADEMIC_YEAR + SEMESTER_DESC, MESSAGE_INVALID_FORMAT);

        // missing semester prefix
        assertParseFailure(parser, ACADEMIC_YEAR_DESC + VALID_SEMESTER, MESSAGE_INVALID_FORMAT);

        // all prefixes missing
        assertParseFailure(parser, "" + VALID_ACADEMIC_YEAR + VALID_SEMESTER, MESSAGE_INVALID_FORMAT);
    }
}
