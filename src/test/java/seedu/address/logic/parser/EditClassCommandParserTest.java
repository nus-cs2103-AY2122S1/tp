package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_TIMESLOT_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CLASSLIMIT_DESC_FIVE;
import static seedu.address.logic.commands.CommandTestUtil.CLASSLIMIT_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.CLASSNAME_DESC_CHEM;
import static seedu.address.logic.commands.CommandTestUtil.CLASSNAME_DESC_PHYSICS;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LIMIT_NEGATIVE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LIMIT_POSITIVE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIMESLOT_DAY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIMESLOT_END;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIMESLOT_EQUAL;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIMESLOT_HOUR;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIMESLOT_MINUTE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIMESLOT_START;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TIMESLOT_DESC_MONDAY;
import static seedu.address.logic.commands.CommandTestUtil.TIMESLOT_DESC_TUESDAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASS_CHEM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASS_MON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASS_PHY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASS_TUE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LIMIT_FIVE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditClassCommand;
import seedu.address.model.tuition.ClassLimit;
import seedu.address.testutil.EditClassDescriptorBuilder;

public class EditClassCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditClassCommand.MESSAGE_USAGE);

    private EditClassCommandParser parser = new EditClassCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_CLASS_PHY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "2", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        //invalid positive limit
        assertParseFailure(parser, "1" + INVALID_LIMIT_POSITIVE, ClassLimit.MESSAGE_CONSTRAINTS);

        //invalid negative limit
        assertParseFailure(parser, "1" + INVALID_LIMIT_NEGATIVE, ClassLimit.MESSAGE_CONSTRAINTS);

        // invalid limit followed by valid class name
        assertParseFailure(parser, "1" + INVALID_LIMIT_NEGATIVE + CLASSNAME_DESC_PHYSICS,
                ClassLimit.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_LIMIT_POSITIVE + INVALID_LIMIT_NEGATIVE
                + VALID_CLASS_CHEM, ClassLimit.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + CLASSLIMIT_DESC_FIVE
                + CLASSNAME_DESC_PHYSICS + TIMESLOT_DESC_MONDAY;

        EditClassCommand.EditClassDescriptor descriptor = new EditClassDescriptorBuilder().withLimit(VALID_LIMIT_FIVE)
                .withName(VALID_CLASS_PHY)
                .withTimeslot(VALID_CLASS_MON)
                .build();

        EditClassCommand expectedCommand = new EditClassCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + CLASSLIMIT_DESC_FIVE + CLASSNAME_DESC_PHYSICS;

        EditClassCommand.EditClassDescriptor descriptor = new EditClassDescriptorBuilder()
                .withLimit(VALID_LIMIT_FIVE)
                .withName(VALID_CLASS_PHY).build();

        EditClassCommand expectedCommand = new EditClassCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + CLASSNAME_DESC_CHEM;
        EditClassCommand.EditClassDescriptor descriptor = new EditClassDescriptorBuilder()
                .withName(VALID_CLASS_CHEM).build();
        EditClassCommand expectedCommand = new EditClassCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // limit
        userInput = targetIndex.getOneBased() + CLASSLIMIT_DESC_FIVE;
        descriptor = new EditClassDescriptorBuilder().withLimit(VALID_LIMIT_FIVE).build();
        expectedCommand = new EditClassCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // timeslot
        userInput = targetIndex.getOneBased() + TIMESLOT_DESC_TUESDAY;
        descriptor = new EditClassDescriptorBuilder().withTimeslot(VALID_CLASS_TUE).build();
        expectedCommand = new EditClassCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + CLASSLIMIT_DESC_FIVE + TIMESLOT_DESC_MONDAY
                + CLASSNAME_DESC_PHYSICS
                + TIMESLOT_DESC_TUESDAY + CLASSLIMIT_DESC_ONE
                + TIMESLOT_DESC_MONDAY + CLASSLIMIT_DESC_FIVE + CLASSNAME_DESC_PHYSICS;

        EditClassCommand.EditClassDescriptor descriptor = new EditClassDescriptorBuilder()
                .withLimit(VALID_LIMIT_FIVE)
                .withName(VALID_CLASS_PHY).withTimeslot(VALID_CLASS_MON)
                .build();

        EditClassCommand expectedCommand = new EditClassCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueWithValidValue_failure() {
        // one other valid value specified
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + INVALID_LIMIT_NEGATIVE + VALID_LIMIT_FIVE;

        assertParseFailure(parser, userInput, ClassLimit.MESSAGE_CONSTRAINTS);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_TIMESLOT_DAY + VALID_CLASS_MON
                + VALID_LIMIT_FIVE;

        assertParseFailure(parser, userInput, Messages.MESSAGE_TIMESLOT_FORMAT);
    }

    @Test
    public void parse_invalidTimeSlots_failure() {
        Index targetIndex = INDEX_FIRST;

        // day does not follow "EEE" format
        String userInput = targetIndex.getOneBased() + INVALID_TIMESLOT_DAY;
        assertParseFailure(parser, userInput, MESSAGE_TIMESLOT_FORMAT);

        //start is later than end time
        userInput = targetIndex.getOneBased() + INVALID_TIMESLOT_START;
        assertParseFailure(parser, userInput, MESSAGE_TIMESLOT_FORMAT);

        // start is equal to end time
        userInput = targetIndex.getOneBased() + INVALID_TIMESLOT_EQUAL;
        assertParseFailure(parser, userInput, MESSAGE_TIMESLOT_FORMAT);

        //end time does not fall on the specified day
        userInput = targetIndex.getOneBased() + INVALID_TIMESLOT_END;
        assertParseFailure(parser, userInput, MESSAGE_TIMESLOT_FORMAT);

        //invalid hour
        userInput = targetIndex.getOneBased() + INVALID_TIMESLOT_HOUR;
        assertParseFailure(parser, userInput, MESSAGE_TIMESLOT_FORMAT);

        //invalid minute
        userInput = targetIndex.getOneBased() + INVALID_TIMESLOT_MINUTE;
        assertParseFailure(parser, userInput, MESSAGE_TIMESLOT_FORMAT);
    }
}

