package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.FUTURE_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.HOMEWORK_DESC_POETRY;
import static seedu.address.logic.commands.CommandTestUtil.HOMEWORK_DESC_TEXTBOOK;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_HOMEWORK_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SUBJECT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIME_RANGE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PAST_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.SUBJECT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TIME_RANGE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_FUTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOMEWORK_POETRY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOMEWORK_TEXTBOOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBJECT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_RANGE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalLessons.PAST_MAKEUP_LESSON;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.LessonAddCommand;
import seedu.address.model.lesson.Date;
import seedu.address.model.lesson.Homework;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.Subject;
import seedu.address.model.lesson.TimeRange;
import seedu.address.testutil.LessonBuilder;

public class LessonAddCommandParserTest {

    private LessonAddCommandParser parser = new LessonAddCommandParser();
    private int FIRST_PERSON = INDEX_FIRST_PERSON.getOneBased();

    @Test
    public void parse_allFieldsPresent_success() {
        Lesson expectedLesson = new LessonBuilder(PAST_MAKEUP_LESSON)
            .withHomeworkSet(VALID_HOMEWORK_POETRY).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + FIRST_PERSON
                + PAST_DATE_DESC + TIME_RANGE_DESC + SUBJECT_DESC
                + HOMEWORK_DESC_POETRY, new LessonAddCommand(INDEX_FIRST_PERSON, expectedLesson));

        // multiple date - last date accepted
        assertParseSuccess(parser, " " + FIRST_PERSON + FUTURE_DATE_DESC
            + PAST_DATE_DESC + TIME_RANGE_DESC + SUBJECT_DESC
            + HOMEWORK_DESC_POETRY, new LessonAddCommand(INDEX_FIRST_PERSON, expectedLesson));

        // multiple subject - last subject accepted
        assertParseSuccess(parser, " " + FIRST_PERSON + PAST_DATE_DESC
            + TIME_RANGE_DESC + SUBJECT_DESC + SUBJECT_DESC
            + HOMEWORK_DESC_POETRY, new LessonAddCommand(INDEX_FIRST_PERSON, expectedLesson));

        // multiple time ranges - last time range accepted
        assertParseSuccess(parser, " " + FIRST_PERSON + PAST_DATE_DESC
            + TIME_RANGE_DESC + TIME_RANGE_DESC + SUBJECT_DESC
            + HOMEWORK_DESC_POETRY, new LessonAddCommand(INDEX_FIRST_PERSON, expectedLesson));

        // multiple homework - all accepted
        Lesson expectedLessonMultipleHomework = new LessonBuilder(PAST_MAKEUP_LESSON)
            .withHomeworkSet(VALID_HOMEWORK_POETRY, VALID_HOMEWORK_TEXTBOOK).build();

        assertParseSuccess(parser, " " + FIRST_PERSON + PAST_DATE_DESC
            + TIME_RANGE_DESC + SUBJECT_DESC
            + HOMEWORK_DESC_POETRY + HOMEWORK_DESC_TEXTBOOK,
            new LessonAddCommand(INDEX_FIRST_PERSON, expectedLessonMultipleHomework));

    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // No homework
        Lesson expectedLesson = new LessonBuilder(PAST_MAKEUP_LESSON)
            .withHomeworkSet().build();

        assertParseSuccess(parser, " " + FIRST_PERSON + PAST_DATE_DESC
            + TIME_RANGE_DESC + SUBJECT_DESC, new LessonAddCommand(INDEX_FIRST_PERSON, expectedLesson));
    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            LessonAddCommand.MESSAGE_USAGE);

        // missing date
        assertParseFailure(parser, " " + FIRST_PERSON + TIME_RANGE_DESC + SUBJECT_DESC
                + HOMEWORK_DESC_POETRY, expectedMessage);

        // missing time range
        assertParseFailure(parser, " " + FIRST_PERSON + FUTURE_DATE_DESC + SUBJECT_DESC
            + HOMEWORK_DESC_POETRY, expectedMessage);

        // missing subject
        assertParseFailure(parser, " " + FIRST_PERSON + FUTURE_DATE_DESC + TIME_RANGE_DESC
            + HOMEWORK_DESC_POETRY, expectedMessage);

        // missing all fields
        assertParseFailure(parser, " " + FIRST_PERSON, expectedMessage);

        // missing date prefix
        assertParseFailure(parser, " " + FIRST_PERSON + VALID_DATE_FUTURE + TIME_RANGE_DESC
            + SUBJECT_DESC + HOMEWORK_DESC_POETRY, expectedMessage);

        // missing time range prefix
        assertParseFailure(parser, " " + FIRST_PERSON + FUTURE_DATE_DESC + VALID_TIME_RANGE
            + SUBJECT_DESC + HOMEWORK_DESC_POETRY, expectedMessage);

        // missing subject prefix
        assertParseFailure(parser, " " + FIRST_PERSON + FUTURE_DATE_DESC + TIME_RANGE_DESC
            + VALID_SUBJECT + HOMEWORK_DESC_POETRY, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, " " + FIRST_PERSON + VALID_DATE_FUTURE + VALID_TIME_RANGE
                + VALID_SUBJECT, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid date
        assertParseFailure(parser, " " + FIRST_PERSON + INVALID_DATE_DESC + TIME_RANGE_DESC
            + SUBJECT_DESC + HOMEWORK_DESC_POETRY, Date.MESSAGE_CONSTRAINTS);

        // invalid time range
        assertParseFailure(parser, " " + FIRST_PERSON + FUTURE_DATE_DESC + INVALID_TIME_RANGE_DESC
            + SUBJECT_DESC + HOMEWORK_DESC_POETRY, TimeRange.MESSAGE_CONSTRAINTS);

        // invalid subject
        assertParseFailure(parser, " " + FIRST_PERSON + FUTURE_DATE_DESC + TIME_RANGE_DESC
            + INVALID_SUBJECT_DESC + HOMEWORK_DESC_POETRY, Subject.MESSAGE_CONSTRAINTS);

        // invalid homework
        assertParseFailure(parser, " " + FIRST_PERSON + FUTURE_DATE_DESC + TIME_RANGE_DESC
            + SUBJECT_DESC + INVALID_HOMEWORK_DESC, Homework.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, " " + FIRST_PERSON + INVALID_DATE_DESC + TIME_RANGE_DESC
            + INVALID_SUBJECT_DESC + HOMEWORK_DESC_POETRY, Date.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + " " + FIRST_PERSON
                + FUTURE_DATE_DESC + TIME_RANGE_DESC + SUBJECT_DESC
                + HOMEWORK_DESC_POETRY + HOMEWORK_DESC_TEXTBOOK,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, LessonAddCommand.MESSAGE_USAGE));
    }
}
