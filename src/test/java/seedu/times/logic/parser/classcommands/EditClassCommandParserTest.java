package seedu.times.logic.parser.classcommands;

import static seedu.times.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.times.logic.commands.CommandTestUtil.CLASSTIMING_DESC_MATHS;
import static seedu.times.logic.commands.CommandTestUtil.CLASSTIMING_DESC_PHYSICS;
import static seedu.times.logic.commands.CommandTestUtil.CLASS_NAME_DESC_MATHS;
import static seedu.times.logic.commands.CommandTestUtil.CLASS_NAME_DESC_PHYSICS;
import static seedu.times.logic.commands.CommandTestUtil.LOCATION_DESC_MATHS;
import static seedu.times.logic.commands.CommandTestUtil.LOCATION_DESC_PHYSICS;
import static seedu.times.logic.commands.CommandTestUtil.RATE_DESC_MATHS;
import static seedu.times.logic.commands.CommandTestUtil.RATE_DESC_PHYSICS;
import static seedu.times.logic.commands.CommandTestUtil.VALID_CLASSNAME_IB_MATHS;
import static seedu.times.logic.commands.CommandTestUtil.VALID_CLASSNAME_IB_PHYSICS;
import static seedu.times.logic.commands.CommandTestUtil.VALID_CLASSTIMING_IB_MATHS;
import static seedu.times.logic.commands.CommandTestUtil.VALID_CLASSTIMING_IB_PHYSICS;
import static seedu.times.logic.commands.CommandTestUtil.VALID_LOCATION_IB_MATHS;
import static seedu.times.logic.commands.CommandTestUtil.VALID_LOCATION_IB_PHYSICS;
import static seedu.times.logic.commands.CommandTestUtil.VALID_RATE_IB_MATHS;
import static seedu.times.logic.commands.CommandTestUtil.VALID_RATE_IB_PHYSICS;
import static seedu.times.logic.commands.classcommands.EditClassCommand.EditClassDescriptor;
import static seedu.times.logic.commands.classcommands.EditClassCommand.MESSAGE_NO_FIELD_PROVIDED;
import static seedu.times.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.times.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.times.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.times.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.times.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import seedu.times.commons.core.index.Index;
import seedu.times.logic.commands.classcommands.EditClassCommand;
import seedu.times.logic.parser.classcommandparsers.EditClassCommandParser;
import seedu.times.testutil.EditClassDescriptorBuilder;

public class EditClassCommandParserTest {

    private EditClassCommandParser parser = new EditClassCommandParser();

    @Test
    public void parse_invalidIndex_failure() {
        //zero index
        assertParseFailure(parser, "0 n/classname", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditClassCommand.MESSAGE_USAGE));

        //negative index
        assertParseFailure(parser, "-1 n/classname", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditClassCommand.MESSAGE_USAGE));

    }

    @Test
    public void parse_noMatchingPrefix_failure() {
        //invalid prefix
        assertParseFailure(parser, "1 bn/", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditClassCommand.MESSAGE_USAGE));

        //random string
        assertParseFailure(parser, "1 asdoiaudouidskhja", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditClassCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingParts_failure() {
        //empty args
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditClassCommand.MESSAGE_USAGE));

        //whitespaces
        assertParseFailure(parser, "   ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditClassCommand.MESSAGE_USAGE));

        //index provided but no field is provided
        assertParseFailure(parser, "1", MESSAGE_NO_FIELD_PROVIDED);

        //index provided but no field is provided, with trailing spaces
        assertParseFailure(parser, "1     ", MESSAGE_NO_FIELD_PROVIDED);
    }

    @Test
    public void parse_singleFieldsSpecified_success() {
        //class name
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + CLASS_NAME_DESC_MATHS;
        EditClassDescriptor descriptor = new EditClassDescriptorBuilder().withClassName(VALID_CLASSNAME_IB_MATHS)
                .build();
        EditClassCommand expectedCommand = new EditClassCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        //class timing
        userInput = targetIndex.getOneBased() + CLASSTIMING_DESC_MATHS;
        descriptor = new EditClassDescriptorBuilder().withClassTiming(VALID_CLASSTIMING_IB_MATHS)
                .build();
        expectedCommand = new EditClassCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        //rate
        userInput = targetIndex.getOneBased() + RATE_DESC_MATHS;
        descriptor = new EditClassDescriptorBuilder().withRate(VALID_RATE_IB_MATHS)
                .build();
        expectedCommand = new EditClassCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        //location
        userInput = targetIndex.getOneBased() + LOCATION_DESC_MATHS;
        descriptor = new EditClassDescriptorBuilder().withLocation(VALID_LOCATION_IB_MATHS)
                .build();
        expectedCommand = new EditClassCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        //note that studentNameList is not included because you can't edit studentNameList with editclass command

        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + RATE_DESC_PHYSICS + CLASSTIMING_DESC_PHYSICS
                + CLASS_NAME_DESC_PHYSICS + LOCATION_DESC_PHYSICS;

        EditClassDescriptor descriptor = new EditClassDescriptorBuilder()
                .withClassName(VALID_CLASSNAME_IB_PHYSICS).withClassTiming(VALID_CLASSTIMING_IB_PHYSICS)
                .withRate(VALID_RATE_IB_PHYSICS).withLocation(VALID_LOCATION_IB_PHYSICS).build();
        EditClassCommand expectedCommand = new EditClassCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + RATE_DESC_MATHS + RATE_DESC_PHYSICS + LOCATION_DESC_MATHS
                + LOCATION_DESC_PHYSICS + CLASS_NAME_DESC_MATHS + CLASS_NAME_DESC_PHYSICS + CLASSTIMING_DESC_MATHS
                + CLASSTIMING_DESC_PHYSICS;
        EditClassDescriptor descriptor = new EditClassDescriptorBuilder()
                .withClassName(VALID_CLASSNAME_IB_PHYSICS).withClassTiming(VALID_CLASSTIMING_IB_PHYSICS)
                .withRate(VALID_RATE_IB_PHYSICS).withLocation(VALID_LOCATION_IB_PHYSICS).build();
        EditClassCommand expectedCommand = new EditClassCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
