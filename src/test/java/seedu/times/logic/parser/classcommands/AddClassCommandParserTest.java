package seedu.times.logic.parser.classcommands;

import static seedu.times.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.times.logic.commands.CommandTestUtil.CLASSNAME_DESC_MATHS;
import static seedu.times.logic.commands.CommandTestUtil.CLASSNAME_DESC_PHYSICS;
import static seedu.times.logic.commands.CommandTestUtil.CLASSTIMING_DESC_MATHS;
import static seedu.times.logic.commands.CommandTestUtil.CLASSTIMING_DESC_PHYSICS;
import static seedu.times.logic.commands.CommandTestUtil.INVALID_CLASSNAME_DESC;
import static seedu.times.logic.commands.CommandTestUtil.INVALID_CLASSTIMING_DESC;
import static seedu.times.logic.commands.CommandTestUtil.INVALID_LOCATION_DESC;
import static seedu.times.logic.commands.CommandTestUtil.INVALID_RATE_DESC;
import static seedu.times.logic.commands.CommandTestUtil.LOCATION_DESC_MATHS;
import static seedu.times.logic.commands.CommandTestUtil.LOCATION_DESC_PHYSICS;
import static seedu.times.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.times.logic.commands.CommandTestUtil.RATE_DESC_MATHS;
import static seedu.times.logic.commands.CommandTestUtil.RATE_DESC_PHYSICS;
import static seedu.times.logic.commands.CommandTestUtil.VALID_CLASSNAME_IB_PHYSICS;
import static seedu.times.logic.commands.CommandTestUtil.VALID_CLASSTIMING_IB_PHYSICS;
import static seedu.times.logic.commands.CommandTestUtil.VALID_EMPTY_STUDENTLIST;
import static seedu.times.logic.commands.CommandTestUtil.VALID_LOCATION_IB_PHYSICS;
import static seedu.times.logic.commands.CommandTestUtil.VALID_RATE_IB_PHYSICS;
import static seedu.times.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.times.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.times.logic.commands.classcommands.AddClassCommand;
import seedu.times.logic.parser.classcommandparsers.AddClassCommandParser;
import seedu.times.model.tuitionclass.ClassName;
import seedu.times.model.tuitionclass.ClassTiming;
import seedu.times.model.tuitionclass.Location;
import seedu.times.model.tuitionclass.Rate;
import seedu.times.model.tuitionclass.TuitionClass;
import seedu.times.testutil.TuitionClassBuilder;

public class AddClassCommandParserTest {
    private AddClassCommandParser parser = new AddClassCommandParser();

    @Test
    public void parse_allFieldsPresentAndValid_success() {
        TuitionClass expectedTuitionClass =
                new TuitionClassBuilder().withClassName(VALID_CLASSNAME_IB_PHYSICS)
                        .withClassTiming(VALID_CLASSTIMING_IB_PHYSICS).withLocation(VALID_LOCATION_IB_PHYSICS)
                        .withRate(VALID_RATE_IB_PHYSICS).withStudentList(VALID_EMPTY_STUDENTLIST).build();

        //whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + CLASSNAME_DESC_PHYSICS + CLASSTIMING_DESC_PHYSICS
                + LOCATION_DESC_PHYSICS + RATE_DESC_PHYSICS, new AddClassCommand(expectedTuitionClass));

        //multiple className - last name accepted
        assertParseSuccess(parser, CLASSNAME_DESC_MATHS + CLASSNAME_DESC_PHYSICS + CLASSTIMING_DESC_PHYSICS
                + LOCATION_DESC_PHYSICS + RATE_DESC_PHYSICS, new AddClassCommand(expectedTuitionClass));

        //multiple classTiming - last timing accepted
        assertParseSuccess(parser, CLASSNAME_DESC_PHYSICS + CLASSTIMING_DESC_MATHS + CLASSTIMING_DESC_PHYSICS
                + LOCATION_DESC_PHYSICS + RATE_DESC_PHYSICS, new AddClassCommand(expectedTuitionClass));

        //multiple location - last location accepted
        assertParseSuccess(parser, CLASSNAME_DESC_PHYSICS + CLASSTIMING_DESC_PHYSICS + LOCATION_DESC_MATHS
                + LOCATION_DESC_PHYSICS + RATE_DESC_PHYSICS, new AddClassCommand(expectedTuitionClass));

        //multiple rate - last rate accepted
        assertParseSuccess(parser, CLASSNAME_DESC_PHYSICS + CLASSTIMING_DESC_PHYSICS + LOCATION_DESC_PHYSICS
                + RATE_DESC_MATHS + RATE_DESC_PHYSICS, new AddClassCommand(expectedTuitionClass));
    }

    @Test
    public void parse_emptyArgs_failure() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddClassCommand.MESSAGE_USAGE)); //nothing
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddClassCommand.MESSAGE_USAGE)); //whitespace
    }

    @Test
    public void parse_missingFieldS_failure() {
        //missing className
        assertParseFailure(parser, CLASSTIMING_DESC_PHYSICS + RATE_DESC_PHYSICS + LOCATION_DESC_PHYSICS,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddClassCommand.MESSAGE_USAGE));
        //missing classTiming
        assertParseFailure(parser, CLASSNAME_DESC_PHYSICS + RATE_DESC_PHYSICS + LOCATION_DESC_PHYSICS,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddClassCommand.MESSAGE_USAGE));
        //missing location
        assertParseFailure(parser, CLASSNAME_DESC_PHYSICS + CLASSTIMING_DESC_PHYSICS + RATE_DESC_PHYSICS,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddClassCommand.MESSAGE_USAGE));
        //missing rate
        assertParseFailure(parser, CLASSNAME_DESC_PHYSICS + CLASSTIMING_DESC_PHYSICS + LOCATION_DESC_PHYSICS,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddClassCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidFields_failure() {
        //invalid className
        assertParseFailure(parser, INVALID_CLASSNAME_DESC + CLASSTIMING_DESC_PHYSICS + LOCATION_DESC_PHYSICS
                + RATE_DESC_PHYSICS, String.format(ClassName.MESSAGE_CONSTRAINTS));
        //invalid classTiming
        assertParseFailure(parser, CLASSNAME_DESC_PHYSICS + INVALID_CLASSTIMING_DESC + LOCATION_DESC_PHYSICS
                + RATE_DESC_PHYSICS, String.format(ClassTiming.MESSAGE_CONSTRAINTS));
        //invalid location
        assertParseFailure(parser, CLASSNAME_DESC_PHYSICS + CLASSTIMING_DESC_PHYSICS + INVALID_LOCATION_DESC
                + RATE_DESC_PHYSICS, String.format(Location.MESSAGE_CONSTRAINTS));
        //invalid rate
        assertParseFailure(parser, CLASSNAME_DESC_PHYSICS + CLASSTIMING_DESC_PHYSICS + LOCATION_DESC_PHYSICS
                + INVALID_RATE_DESC, String.format(Rate.MESSAGE_CONSTRAINTS));
    }

}
