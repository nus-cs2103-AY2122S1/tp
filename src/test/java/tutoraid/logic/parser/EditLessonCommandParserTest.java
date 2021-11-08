package tutoraid.logic.parser;

import static tutoraid.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tutoraid.logic.commands.CommandTestUtil.CAPACITY_DESC_MATH;
import static tutoraid.logic.commands.CommandTestUtil.CAPACITY_DESC_SCIENCE;
import static tutoraid.logic.commands.CommandTestUtil.INVALID_CAPACITY_DESC;
import static tutoraid.logic.commands.CommandTestUtil.INVALID_LESSON_NAME_DESC;
import static tutoraid.logic.commands.CommandTestUtil.INVALID_PRICE_DESC;
import static tutoraid.logic.commands.CommandTestUtil.LESSON_NAME_DESC_MATH;
import static tutoraid.logic.commands.CommandTestUtil.LESSON_NAME_DESC_SCIENCE;
import static tutoraid.logic.commands.CommandTestUtil.PRICE_DESC_MATH;
import static tutoraid.logic.commands.CommandTestUtil.PRICE_DESC_SCIENCE;
import static tutoraid.logic.commands.CommandTestUtil.TIMING_DESC_MATH;
import static tutoraid.logic.commands.CommandTestUtil.TIMING_DESC_SCIENCE;
import static tutoraid.logic.commands.CommandTestUtil.VALID_CAPACITY_MATHS_TWO;
import static tutoraid.logic.commands.CommandTestUtil.VALID_CAPACITY_SCIENCE_TWO;
import static tutoraid.logic.commands.CommandTestUtil.VALID_LESSON_NAME_MATHS_TWO;
import static tutoraid.logic.commands.CommandTestUtil.VALID_LESSON_NAME_SCIENCE_TWO;
import static tutoraid.logic.commands.CommandTestUtil.VALID_PRICE_MATHS_TWO;
import static tutoraid.logic.commands.CommandTestUtil.VALID_TIMING_MATHS_TWO;
import static tutoraid.logic.commands.CommandTestUtil.VALID_TIMING_SCIENCE_TWO;
import static tutoraid.logic.parser.CommandParserTestUtil.assertParseFailure;
import static tutoraid.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static tutoraid.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static tutoraid.testutil.TypicalIndexes.INDEX_THIRD_ITEM;

import org.junit.jupiter.api.Test;

import tutoraid.commons.core.index.Index;
import tutoraid.logic.commands.EditLessonCommand;
import tutoraid.logic.commands.EditLessonCommand.EditLessonDescriptor;
import tutoraid.model.lesson.Capacity;
import tutoraid.model.lesson.LessonName;
import tutoraid.model.lesson.Price;
import tutoraid.testutil.EditLessonDescriptorBuilder;

public class EditLessonCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditLessonCommand.MESSAGE_USAGE);

    private EditLessonCommandParser parser = new EditLessonCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_LESSON_NAME_MATHS_TWO, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditLessonCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + LESSON_NAME_DESC_MATH, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + LESSON_NAME_DESC_SCIENCE, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_LESSON_NAME_DESC, LessonName.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_CAPACITY_DESC, Capacity.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_PRICE_DESC, Price.MESSAGE_CONSTRAINTS);

        // invalid capacity followed by valid timing
        assertParseFailure(parser, "1" + INVALID_CAPACITY_DESC + TIMING_DESC_MATH,
                Capacity.MESSAGE_CONSTRAINTS);

        // valid capacity followed by invalid price
        assertParseFailure(parser, "1" + CAPACITY_DESC_MATH + INVALID_PRICE_DESC,
                Price.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_ITEM;
        String userInput = targetIndex.getOneBased()
                + LESSON_NAME_DESC_MATH
                + CAPACITY_DESC_MATH
                + PRICE_DESC_MATH
                + TIMING_DESC_MATH;

        EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder()
                .withLessonName(VALID_LESSON_NAME_MATHS_TWO)
                .withCapacity(VALID_CAPACITY_MATHS_TWO)
                .withPrice(VALID_PRICE_MATHS_TWO)
                .withTiming(VALID_TIMING_MATHS_TWO)
                .build();
        EditLessonCommand expectedCommand = new EditLessonCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_ITEM;
        String userInput = targetIndex.getOneBased() + CAPACITY_DESC_SCIENCE + TIMING_DESC_MATH;

        EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder()
                .withCapacity(VALID_CAPACITY_SCIENCE_TWO)
                .withTiming(VALID_TIMING_MATHS_TWO)
                .build();
        EditLessonCommand expectedCommand = new EditLessonCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_ITEM;
        String userInput = targetIndex.getOneBased() + LESSON_NAME_DESC_SCIENCE;
        EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder()
                .withLessonName(VALID_LESSON_NAME_SCIENCE_TWO)
                .build();
        EditLessonCommand expectedCommand = new EditLessonCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // capacity
        userInput = targetIndex.getOneBased() + CAPACITY_DESC_MATH;
        descriptor = new EditLessonDescriptorBuilder()
                .withCapacity(VALID_CAPACITY_MATHS_TWO)
                .build();
        expectedCommand = new EditLessonCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // timing
        userInput = targetIndex.getOneBased() + TIMING_DESC_SCIENCE;
        descriptor = new EditLessonDescriptorBuilder()
                .withTiming(VALID_TIMING_SCIENCE_TWO)
                .build();
        expectedCommand = new EditLessonCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // pricing
        userInput = targetIndex.getOneBased() + PRICE_DESC_MATH;
        descriptor = new EditLessonDescriptorBuilder()
                .withPrice(VALID_PRICE_MATHS_TWO)
                .build();
        expectedCommand = new EditLessonCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_ITEM;
        String userInput = targetIndex.getOneBased()
                + LESSON_NAME_DESC_MATH + CAPACITY_DESC_SCIENCE + PRICE_DESC_SCIENCE
                + LESSON_NAME_DESC_SCIENCE + CAPACITY_DESC_MATH + PRICE_DESC_SCIENCE
                + LESSON_NAME_DESC_MATH + CAPACITY_DESC_MATH + PRICE_DESC_MATH;

        EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder()
                .withLessonName(VALID_LESSON_NAME_MATHS_TWO)
                .withCapacity(VALID_CAPACITY_MATHS_TWO)
                .withPrice(VALID_PRICE_MATHS_TWO)
                .build();
        EditLessonCommand expectedCommand = new EditLessonCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_ITEM;
        String userInput = targetIndex.getOneBased() + INVALID_CAPACITY_DESC + CAPACITY_DESC_MATH;
        EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder()
                .withCapacity(VALID_CAPACITY_MATHS_TWO)
                .build();
        EditLessonCommand expectedCommand = new EditLessonCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased()
                + PRICE_DESC_MATH
                + INVALID_CAPACITY_DESC
                + CAPACITY_DESC_MATH
                + TIMING_DESC_MATH;
        descriptor = new EditLessonDescriptorBuilder()
                .withPrice(VALID_PRICE_MATHS_TWO)
                .withTiming(VALID_TIMING_MATHS_TWO)
                .withCapacity(VALID_CAPACITY_MATHS_TWO)
                .build();
        expectedCommand = new EditLessonCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
