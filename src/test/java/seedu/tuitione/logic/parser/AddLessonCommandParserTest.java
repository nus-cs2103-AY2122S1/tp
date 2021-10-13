package seedu.tuitione.logic.parser;

import static seedu.tuitione.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tuitione.logic.commands.CommandTestUtil.DAYOFWEEK_DESC_MATHS;
import static seedu.tuitione.logic.commands.CommandTestUtil.DAYOFWEEK_DESC_SCIENCE;
import static seedu.tuitione.logic.commands.CommandTestUtil.GRADE_DESC_MATHS;
import static seedu.tuitione.logic.commands.CommandTestUtil.GRADE_DESC_SCIENCE;
import static seedu.tuitione.logic.commands.CommandTestUtil.INVALID_DAYOFWEEK_DESC;
import static seedu.tuitione.logic.commands.CommandTestUtil.INVALID_GRADE_DESC;
import static seedu.tuitione.logic.commands.CommandTestUtil.INVALID_LESSONTIME_DESC;
import static seedu.tuitione.logic.commands.CommandTestUtil.INVALID_LESSONTIME_ODD_TIME_DESC;
import static seedu.tuitione.logic.commands.CommandTestUtil.INVALID_PRICE_DESC;
import static seedu.tuitione.logic.commands.CommandTestUtil.INVALID_SUBJECT_DESC;
import static seedu.tuitione.logic.commands.CommandTestUtil.LESSONTIME_DESC_MATHS;
import static seedu.tuitione.logic.commands.CommandTestUtil.LESSONTIME_DESC_SCIENCE;
import static seedu.tuitione.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.tuitione.logic.commands.CommandTestUtil.PRICE_DESC_MATHS;
import static seedu.tuitione.logic.commands.CommandTestUtil.PRICE_DESC_SCIENCE;
import static seedu.tuitione.logic.commands.CommandTestUtil.SUBJECT_DESC_MATHS;
import static seedu.tuitione.logic.commands.CommandTestUtil.SUBJECT_DESC_SCIENCE;
import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_DAYOFWEEK_MATHS;
import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_GRADE_MATHS;
import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_LESSONTIME_MATHS;
import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_PRICE_MATHS;
import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_SUBJECT_MATHS;
import static seedu.tuitione.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.tuitione.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.tuitione.testutil.TypicalLessons.SCIENCE;

import org.junit.jupiter.api.Test;

import seedu.tuitione.logic.commands.AddLessonCommand;
import seedu.tuitione.model.lesson.Lesson;
import seedu.tuitione.model.lesson.LessonTime;
import seedu.tuitione.model.lesson.Price;
import seedu.tuitione.model.lesson.Subject;
import seedu.tuitione.model.student.Grade;
import seedu.tuitione.testutil.LessonBuilder;

public class AddLessonCommandParserTest {
    private AddLessonCommandParser parser = new AddLessonCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Lesson expectedLesson = new LessonBuilder(SCIENCE).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + SUBJECT_DESC_SCIENCE
                + GRADE_DESC_SCIENCE + DAYOFWEEK_DESC_SCIENCE + LESSONTIME_DESC_SCIENCE
                + PRICE_DESC_SCIENCE, new AddLessonCommand(expectedLesson));

        // multiple subjects - last subject accepted
        assertParseSuccess(parser, SUBJECT_DESC_MATHS + SUBJECT_DESC_SCIENCE
                + GRADE_DESC_SCIENCE + DAYOFWEEK_DESC_SCIENCE + LESSONTIME_DESC_SCIENCE
                + PRICE_DESC_SCIENCE, new AddLessonCommand(expectedLesson));

        // multiple grades - last grade accepted
        assertParseSuccess(parser, SUBJECT_DESC_SCIENCE + GRADE_DESC_MATHS
                + GRADE_DESC_SCIENCE + DAYOFWEEK_DESC_SCIENCE + LESSONTIME_DESC_SCIENCE
                + PRICE_DESC_SCIENCE, new AddLessonCommand(expectedLesson));

        // multiple dayOfWeeks - last dayOfWeek accepted
        assertParseSuccess(parser, SUBJECT_DESC_SCIENCE + GRADE_DESC_SCIENCE
                + DAYOFWEEK_DESC_MATHS + DAYOFWEEK_DESC_SCIENCE + LESSONTIME_DESC_SCIENCE
                + PRICE_DESC_SCIENCE, new AddLessonCommand(expectedLesson));

        // multiple lesson times - last lesson time accepted
        assertParseSuccess(parser, SUBJECT_DESC_SCIENCE + GRADE_DESC_SCIENCE
                + DAYOFWEEK_DESC_SCIENCE + LESSONTIME_DESC_MATHS + LESSONTIME_DESC_SCIENCE
                + PRICE_DESC_SCIENCE, new AddLessonCommand(expectedLesson));

        // multiple prices - last price accepted
        assertParseSuccess(parser, SUBJECT_DESC_SCIENCE + GRADE_DESC_SCIENCE
                + DAYOFWEEK_DESC_SCIENCE + LESSONTIME_DESC_SCIENCE + PRICE_DESC_MATHS
                + PRICE_DESC_SCIENCE, new AddLessonCommand(expectedLesson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLessonCommand.MESSAGE_USAGE);

        // missing subject prefix
        assertParseFailure(parser, VALID_SUBJECT_MATHS + GRADE_DESC_MATHS
                + DAYOFWEEK_DESC_MATHS + LESSONTIME_DESC_MATHS + PRICE_DESC_MATHS, expectedMessage);

        // missing grade prefix
        assertParseFailure(parser, SUBJECT_DESC_MATHS + VALID_GRADE_MATHS
                + DAYOFWEEK_DESC_MATHS + LESSONTIME_DESC_MATHS + PRICE_DESC_MATHS, expectedMessage);

        // missing dayOfWeek prefix
        assertParseFailure(parser, SUBJECT_DESC_MATHS + GRADE_DESC_MATHS
                + VALID_DAYOFWEEK_MATHS + LESSONTIME_DESC_MATHS + PRICE_DESC_MATHS, expectedMessage);

        // missing lessonTime prefix
        assertParseFailure(parser, SUBJECT_DESC_MATHS + GRADE_DESC_MATHS
                + DAYOFWEEK_DESC_MATHS + VALID_LESSONTIME_MATHS + PRICE_DESC_MATHS, expectedMessage);

        // missing price prefix
        assertParseFailure(parser, SUBJECT_DESC_MATHS + GRADE_DESC_MATHS
                + DAYOFWEEK_DESC_MATHS + LESSONTIME_DESC_MATHS + VALID_PRICE_MATHS, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {

        // invalid subject
        assertParseFailure(parser, INVALID_SUBJECT_DESC + GRADE_DESC_MATHS
                + DAYOFWEEK_DESC_MATHS + LESSONTIME_DESC_MATHS + PRICE_DESC_MATHS,
                Subject.SUBJECT_MESSAGE_CONSTRAINTS);

        // invalid grade
        assertParseFailure(parser, SUBJECT_DESC_MATHS + INVALID_GRADE_DESC
                + DAYOFWEEK_DESC_MATHS + LESSONTIME_DESC_MATHS + PRICE_DESC_MATHS,
                Grade.GRADE_MESSAGE_CONSTRAINTS);

        // invalid dayOfWeek
        assertParseFailure(parser, SUBJECT_DESC_MATHS + GRADE_DESC_MATHS
                + INVALID_DAYOFWEEK_DESC + LESSONTIME_DESC_MATHS + PRICE_DESC_MATHS,
                ParserUtil.MESSAGE_INVALID_DAY);

        // invalid lessonTime
        assertParseFailure(parser, SUBJECT_DESC_MATHS + GRADE_DESC_MATHS
                + DAYOFWEEK_DESC_MATHS + INVALID_LESSONTIME_DESC + PRICE_DESC_MATHS,
                ParserUtil.MESSAGE_INVALID_TIME);

        // invalid lessonTime - odd timing
        assertParseFailure(parser, SUBJECT_DESC_MATHS + GRADE_DESC_MATHS
                + DAYOFWEEK_DESC_MATHS + INVALID_LESSONTIME_ODD_TIME_DESC + PRICE_DESC_MATHS,
                LessonTime.TIME_MESSAGE_CONSTRAINTS);

        // invalid price
        assertParseFailure(parser, SUBJECT_DESC_MATHS + GRADE_DESC_MATHS
                + DAYOFWEEK_DESC_MATHS + LESSONTIME_DESC_MATHS + INVALID_PRICE_DESC,
                Price.PRICE_MESSAGE_CONSTRAINT);
    }
}
