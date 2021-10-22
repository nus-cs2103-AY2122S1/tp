package seedu.tracker.logic.parser;

import static seedu.tracker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tracker.logic.commands.CommandTestUtil.ACADEMIC_YEAR_DESC;
import static seedu.tracker.logic.commands.CommandTestUtil.INVALID_ACADEMIC_YEAR_DESC;
import static seedu.tracker.logic.commands.CommandTestUtil.INVALID_ACADEMIC_YEAR_SIGNED_DESC;
import static seedu.tracker.logic.commands.CommandTestUtil.INVALID_SEMESTER_DESC;
import static seedu.tracker.logic.commands.CommandTestUtil.INVALID_SEMESTER_SIGNED_DESC;
import static seedu.tracker.logic.commands.CommandTestUtil.SEMESTER_DESC;
import static seedu.tracker.logic.commands.CommandTestUtil.VALID_ACADEMIC_YEAR;
import static seedu.tracker.logic.commands.CommandTestUtil.VALID_SEMESTER;
import static seedu.tracker.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.tracker.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.tracker.logic.commands.ViewCommand;
import seedu.tracker.model.calendar.AcademicCalendar;
import seedu.tracker.model.calendar.AcademicYear;
import seedu.tracker.model.calendar.Semester;
import seedu.tracker.model.module.ModuleInSpecificSemesterPredicate;

class ViewCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE);

    private ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = ACADEMIC_YEAR_DESC + SEMESTER_DESC;

        AcademicYear year = new AcademicYear(VALID_ACADEMIC_YEAR);
        Semester semester = new Semester(VALID_SEMESTER);
        AcademicCalendar academicCalendar = new AcademicCalendar(year, semester);
        ModuleInSpecificSemesterPredicate predicate =
                new ModuleInSpecificSemesterPredicate(academicCalendar);
        ViewCommand expectedCommand = new ViewCommand(predicate);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingParts_failure() {
        // no year specified
        assertParseFailure(parser, SEMESTER_DESC, MESSAGE_INVALID_FORMAT);

        //no semester specified
        assertParseFailure(parser, ACADEMIC_YEAR_DESC, MESSAGE_INVALID_FORMAT);

        //no year and no semester specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid academic year followed by valid semester
        assertParseFailure(parser, INVALID_ACADEMIC_YEAR_DESC + SEMESTER_DESC,
                AcademicYear.MESSAGE_CONSTRAINTS);

        //invalid academic year (signed integer) followed by valid semester
        assertParseFailure(parser, INVALID_ACADEMIC_YEAR_SIGNED_DESC + SEMESTER_DESC,
                AcademicYear.MESSAGE_CONSTRAINTS);

        // valid academic year followed by invalid semester (signed integer)
        assertParseFailure(parser, ACADEMIC_YEAR_DESC + INVALID_SEMESTER_SIGNED_DESC,
                Semester.MESSAGE_CONSTRAINTS);

        // valid academic year followed by invalid semester
        assertParseFailure(parser, ACADEMIC_YEAR_DESC + INVALID_SEMESTER_DESC,
                Semester.MESSAGE_CONSTRAINTS);

        // both invalid values, but only the first invalid value is captured
        assertParseFailure(parser,
                INVALID_ACADEMIC_YEAR_DESC + INVALID_SEMESTER_DESC,
                AcademicYear.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_prefixMissing_failure() {
        // missing academic year prefix
        assertParseFailure(parser, VALID_ACADEMIC_YEAR + SEMESTER_DESC, MESSAGE_INVALID_FORMAT);

        // missing semester prefix
        assertParseFailure(parser, ACADEMIC_YEAR_DESC + VALID_SEMESTER, MESSAGE_INVALID_FORMAT);

        // all prefixes missing
        assertParseFailure(parser, "" + VALID_ACADEMIC_YEAR + VALID_SEMESTER, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_otherString_failure() {
        assertParseFailure(parser, "HELLO WORLD!", MESSAGE_INVALID_FORMAT);
    }

}
