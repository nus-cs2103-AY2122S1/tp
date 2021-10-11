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
import static seedu.tracker.testutil.TypicalIndexes.INDEX_SECOND_MODULE;

import org.junit.jupiter.api.Test;

import seedu.tracker.commons.core.index.Index;
import seedu.tracker.logic.commands.TakeCommand;
import seedu.tracker.model.calendar.AcademicCalendar;
import seedu.tracker.model.calendar.AcademicYear;
import seedu.tracker.model.calendar.Semester;

public class TakeCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, TakeCommand.MESSAGE_USAGE);

    private TakeCommandParser parser = new TakeCommandParser();

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_MODULE;
        String userInput = targetIndex.getOneBased() + ACADEMIC_YEAR_DESC + SEMESTER_DESC;

        AcademicYear year = new AcademicYear(VALID_ACADEMIC_YEAR);
        Semester semester = new Semester(VALID_SEMESTER);
        AcademicCalendar academicCalendar = new AcademicCalendar(year, semester);
        TakeCommand expectedCommand = new TakeCommand(targetIndex, academicCalendar);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, ACADEMIC_YEAR_DESC + SEMESTER_DESC, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + ACADEMIC_YEAR_DESC + SEMESTER_DESC, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + ACADEMIC_YEAR_DESC + SEMESTER_DESC, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string"
                + ACADEMIC_YEAR_DESC + SEMESTER_DESC, MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string"
                + ACADEMIC_YEAR_DESC + SEMESTER_DESC, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid academic year, missing semester
        assertParseFailure(parser, "1" + INVALID_ACADEMIC_YEAR_DESC, MESSAGE_INVALID_FORMAT);
        // invalid semester, missing academic year
        assertParseFailure(parser, "1" + INVALID_SEMESTER_DESC, MESSAGE_INVALID_FORMAT);

        // invalid academic year followed by valid semester
        assertParseFailure(parser, "1" + INVALID_ACADEMIC_YEAR_DESC + SEMESTER_DESC,
                AcademicYear.MESSAGE_CONSTRAINTS);

        //invalid academic year (signed integer) followed by valid semester
        assertParseFailure(parser, "1" + INVALID_ACADEMIC_YEAR_SIGNED_DESC + SEMESTER_DESC,
                AcademicYear.MESSAGE_CONSTRAINTS);

        // valid academic year followed by invalid semester (signed integer)
        assertParseFailure(parser, "1" + ACADEMIC_YEAR_DESC + INVALID_SEMESTER_SIGNED_DESC,
                Semester.MESSAGE_CONSTRAINTS);

        // valid academic year followed by invalid semester
        assertParseFailure(parser, "1" + ACADEMIC_YEAR_DESC + INVALID_SEMESTER_DESC,
                Semester.MESSAGE_CONSTRAINTS);

        // both invalid values, but only the first invalid value is captured
        assertParseFailure(parser,
                "1" + INVALID_ACADEMIC_YEAR_DESC + INVALID_SEMESTER_DESC, AcademicYear.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_prefixMissing_failure() {

        // missing academic year prefix
        assertParseFailure(parser, "1" + VALID_ACADEMIC_YEAR + SEMESTER_DESC, MESSAGE_INVALID_FORMAT);

        // missing semester prefix
        assertParseFailure(parser, "1" + ACADEMIC_YEAR_DESC + VALID_SEMESTER, MESSAGE_INVALID_FORMAT);

        // all prefixes missing
        assertParseFailure(parser, "1" + VALID_ACADEMIC_YEAR + VALID_SEMESTER, MESSAGE_INVALID_FORMAT);
    }
}
