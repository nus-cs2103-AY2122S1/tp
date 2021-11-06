package tutoraid.logic.parser;

import static tutoraid.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tutoraid.logic.commands.CommandTestUtil.CAPACITY_DESC_MATH;
import static tutoraid.logic.commands.CommandTestUtil.CAPACITY_DESC_SCIENCE;
import static tutoraid.logic.commands.CommandTestUtil.INVALID_CAPACITY_DESC;
import static tutoraid.logic.commands.CommandTestUtil.INVALID_LESSON_NAME_DESC;
import static tutoraid.logic.commands.CommandTestUtil.INVALID_PRICE_DESC;
import static tutoraid.logic.commands.CommandTestUtil.LESSON_NAME_DESC_MATH;
import static tutoraid.logic.commands.CommandTestUtil.LESSON_NAME_DESC_SCIENCE;
import static tutoraid.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static tutoraid.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static tutoraid.logic.commands.CommandTestUtil.PRICE_DESC_MATH;
import static tutoraid.logic.commands.CommandTestUtil.PRICE_DESC_SCIENCE;
import static tutoraid.logic.commands.CommandTestUtil.TIMING_DESC_MATH;
import static tutoraid.logic.commands.CommandTestUtil.TIMING_DESC_SCIENCE;
import static tutoraid.logic.commands.CommandTestUtil.VALID_LESSON_NAME_MATHS_TWO;
import static tutoraid.testutil.TypicalLessons.MATHS_TWO;
import static tutoraid.testutil.TypicalLessons.SCIENCE_TWO;

import org.junit.jupiter.api.Test;

import tutoraid.logic.commands.AddLessonCommand;
import tutoraid.model.lesson.Capacity;
import tutoraid.model.lesson.Lesson;
import tutoraid.model.lesson.LessonName;
import tutoraid.model.lesson.Price;
import tutoraid.testutil.LessonBuilder;

public class AddLessonCommandParserTest {
    private final AddLessonCommandParser parser = new AddLessonCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Lesson expectedLesson = new LessonBuilder(SCIENCE_TWO).build();

        // whitespace only preamble
        CommandParserTestUtil.assertParseSuccess(parser, PREAMBLE_WHITESPACE + LESSON_NAME_DESC_SCIENCE
                + CAPACITY_DESC_SCIENCE + PRICE_DESC_SCIENCE + TIMING_DESC_SCIENCE,
                new AddLessonCommand(expectedLesson));

        // multiple lesson names provided - last name accepted
        CommandParserTestUtil.assertParseSuccess(parser, LESSON_NAME_DESC_MATH + LESSON_NAME_DESC_SCIENCE
                        + CAPACITY_DESC_SCIENCE + PRICE_DESC_SCIENCE + TIMING_DESC_SCIENCE,
                new AddLessonCommand(expectedLesson));

        // multiple capacities provided - last capacity accepted
        CommandParserTestUtil.assertParseSuccess(parser, LESSON_NAME_DESC_SCIENCE
                        + CAPACITY_DESC_MATH + CAPACITY_DESC_SCIENCE + PRICE_DESC_SCIENCE + TIMING_DESC_SCIENCE,
                new AddLessonCommand(expectedLesson));

        // multiple prices provided - last price accepted
        CommandParserTestUtil.assertParseSuccess(parser, LESSON_NAME_DESC_SCIENCE
                        + CAPACITY_DESC_SCIENCE + PRICE_DESC_MATH + PRICE_DESC_SCIENCE + TIMING_DESC_SCIENCE,
                new AddLessonCommand(expectedLesson));

        // multiple timings provided - last timing accepted
        CommandParserTestUtil.assertParseSuccess(parser, LESSON_NAME_DESC_SCIENCE
                        + CAPACITY_DESC_SCIENCE + PRICE_DESC_SCIENCE + TIMING_DESC_SCIENCE + TIMING_DESC_SCIENCE,
                new AddLessonCommand(expectedLesson));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        Lesson expectedLessonWithoutCapacity = new LessonBuilder(MATHS_TWO).withCapacity("").build();
        CommandParserTestUtil.assertParseSuccess(parser, LESSON_NAME_DESC_MATH + PRICE_DESC_MATH
                + TIMING_DESC_MATH, new AddLessonCommand(expectedLessonWithoutCapacity));

        Lesson expectedLessonWithoutPrice = new LessonBuilder(MATHS_TWO).withPrice("").build();
        CommandParserTestUtil.assertParseSuccess(parser, LESSON_NAME_DESC_MATH + CAPACITY_DESC_MATH
                + TIMING_DESC_MATH, new AddLessonCommand(expectedLessonWithoutPrice));

        Lesson expectedLessonWithoutTiming = new LessonBuilder(MATHS_TWO).withTiming("").build();
        CommandParserTestUtil.assertParseSuccess(parser, LESSON_NAME_DESC_MATH + PRICE_DESC_MATH
                + CAPACITY_DESC_MATH, new AddLessonCommand(expectedLessonWithoutTiming));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLessonCommand.MESSAGE_USAGE);

        // missing student name prefix
        CommandParserTestUtil.assertParseFailure(parser, VALID_LESSON_NAME_MATHS_TWO + CAPACITY_DESC_MATH
                + PRICE_DESC_MATH + TIMING_DESC_MATH, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid lesson name
        CommandParserTestUtil.assertParseFailure(parser, INVALID_LESSON_NAME_DESC + CAPACITY_DESC_SCIENCE
                + PRICE_DESC_SCIENCE + TIMING_DESC_SCIENCE, LessonName.MESSAGE_CONSTRAINTS);

        // invalid capacity
        CommandParserTestUtil.assertParseFailure(parser, LESSON_NAME_DESC_SCIENCE + INVALID_CAPACITY_DESC
                + PRICE_DESC_SCIENCE + TIMING_DESC_SCIENCE, Capacity.MESSAGE_CONSTRAINTS);

        // invalid price
        CommandParserTestUtil.assertParseFailure(parser, LESSON_NAME_DESC_SCIENCE + CAPACITY_DESC_SCIENCE
                + INVALID_PRICE_DESC + TIMING_DESC_SCIENCE, Price.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        CommandParserTestUtil.assertParseFailure(parser, INVALID_LESSON_NAME_DESC + INVALID_CAPACITY_DESC
                + PRICE_DESC_SCIENCE + TIMING_DESC_SCIENCE, LessonName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        CommandParserTestUtil.assertParseFailure(parser, PREAMBLE_NON_EMPTY + LESSON_NAME_DESC_SCIENCE
                + CAPACITY_DESC_SCIENCE + PRICE_DESC_SCIENCE + TIMING_DESC_SCIENCE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLessonCommand.MESSAGE_USAGE));
    }
}
