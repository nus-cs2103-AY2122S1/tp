package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_INTERVIEW_DATE_AND_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_REQUIREMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INTERVIEW_DATE_AND_TIME_DESC_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_POSITION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRIORITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.POSITION_DESC_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.POSITION_DESC_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_DESC_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.REQUIREMENTS_DESC_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INTERVIEW_DATE_AND_TIME_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REQUIREMENTS_AMAZON;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_APPLICATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_APPLICATION;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditApplicationDescriptor;
import seedu.address.model.application.Company;
import seedu.address.model.application.Position;
import seedu.address.model.application.Priority;
import seedu.address.testutil.EditApplicationDescriptorBuilder;

public class EditCommandParserTest {

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {

        // nothing specified
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX + "\n%1$s", EditCommand.MESSAGE_USAGE));

        //no index specified
        assertParseFailure(parser, POSITION_DESC_BYTEDANCE,
                String.format(MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX + "\n%1$s", EditCommand.MESSAGE_USAGE));

        //no field specified
        assertParseFailure(parser, "1",
                String.format(EditCommand.MESSAGE_NO_FILED_PROVIDED + "\n%1$s", EditCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMAZON,
                String.format(
                        MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX + "\n%1$s",
                                EditCommand.MESSAGE_USAGE));

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMAZON,
                String.format(
                        MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX + "\n%1$s",
                                EditCommand.MESSAGE_USAGE));

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string",
                String.format(
                        MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX + "\n%1$s",
                                EditCommand.MESSAGE_USAGE));

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 z/ string",
                String.format(
                        MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX + "\n%1$s",
                                EditCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Company.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_POSITION_DESC, Position.MESSAGE_CONSTRAINTS); // invalid position
        assertParseFailure(parser, "1" + INVALID_PRIORITY_DESC, Priority.MESSAGE_CONSTRAINTS); // invalid priority

        // invalid position followed by valid deadline
        assertParseFailure(parser, "1" + INVALID_POSITION_DESC + DEADLINE_DESC_AMAZON,
                Position.MESSAGE_CONSTRAINTS);

        // valid position followed by invalid position. The test case for invalid position followed by valid position
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + POSITION_DESC_BYTEDANCE + INVALID_POSITION_DESC,
                Position.MESSAGE_CONSTRAINTS);


        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_DEADLINE_DESC + VALID_DEADLINE_AMAZON,
                Company.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_APPLICATION;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMAZON + POSITION_DESC_BYTEDANCE + DEADLINE_DESC_AMAZON
                + PRIORITY_DESC_AMAZON + REQUIREMENTS_DESC_AMAZON + INTERVIEW_DATE_AND_TIME_DESC_AMAZON;

        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder().withCompany(VALID_NAME_AMAZON)
                .withPosition(VALID_POSITION_BYTEDANCE).withDeadline(VALID_DEADLINE_AMAZON)
                .withPriority(VALID_PRIORITY_AMAZON).withRequirements(VALID_REQUIREMENTS_AMAZON)
                .withInterviewDateAndTime(VALID_INTERVIEW_DATE_AND_TIME_AMAZON).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_APPLICATION;
        String userInput = targetIndex.getOneBased() + POSITION_DESC_BYTEDANCE + DEADLINE_DESC_AMAZON;

        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withPosition(VALID_POSITION_BYTEDANCE)
                .withDeadline(VALID_DEADLINE_AMAZON).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // company
        Index targetIndex = INDEX_THIRD_APPLICATION;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMAZON;
        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withCompany(VALID_NAME_AMAZON).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // position
        userInput = targetIndex.getOneBased() + POSITION_DESC_AMAZON;
        descriptor = new EditApplicationDescriptorBuilder().withPosition(VALID_POSITION_AMAZON).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // deadline
        userInput = targetIndex.getOneBased() + DEADLINE_DESC_AMAZON;
        descriptor = new EditApplicationDescriptorBuilder().withDeadline(VALID_DEADLINE_AMAZON).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // priority
        userInput = targetIndex.getOneBased() + PRIORITY_DESC_AMAZON;
        descriptor = new EditApplicationDescriptorBuilder().withPriority(VALID_PRIORITY_AMAZON).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        //requirement
        userInput = targetIndex.getOneBased() + REQUIREMENTS_DESC_AMAZON;
        descriptor = new EditApplicationDescriptorBuilder().withRequirements(VALID_REQUIREMENTS_AMAZON).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        //interview date and time
        userInput = targetIndex.getOneBased() + INTERVIEW_DATE_AND_TIME_DESC_AMAZON;
        descriptor = new EditApplicationDescriptorBuilder()
                .withInterviewDateAndTime(VALID_INTERVIEW_DATE_AND_TIME_AMAZON).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void emptyOptionalField_success() {

        //empty requirement
        Index targetIndex = INDEX_THIRD_APPLICATION;
        String userInput = targetIndex.getOneBased() + EMPTY_REQUIREMENT_DESC;
        EditApplicationDescriptor emptyRequirementDescriptor = new EditApplicationDescriptor();
        emptyRequirementDescriptor.setRequirements(new HashSet<>());
        EditCommand expectedCommand = new EditCommand(targetIndex, emptyRequirementDescriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        //empty interview date and time
        userInput = targetIndex.getOneBased() + EMPTY_INTERVIEW_DATE_AND_TIME_DESC;
        EditApplicationDescriptor emptyInterviewTimeDescriptor = new EditApplicationDescriptor();
        emptyInterviewTimeDescriptor.setInterviewDateAndTimes(new HashSet<>());
        expectedCommand = new EditCommand(targetIndex, emptyInterviewTimeDescriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        //empty requirements and interview date and time
        userInput = targetIndex.getOneBased() + EMPTY_REQUIREMENT_DESC + EMPTY_INTERVIEW_DATE_AND_TIME_DESC;
        EditApplicationDescriptor emptyRequirementAndInterviewTimeDescriptor = new EditApplicationDescriptor();
        emptyRequirementAndInterviewTimeDescriptor.setRequirements(new HashSet<>());
        emptyRequirementAndInterviewTimeDescriptor.setInterviewDateAndTimes(new HashSet<>());
        expectedCommand = new EditCommand(targetIndex, emptyRequirementAndInterviewTimeDescriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void noFieldSpecified_failure() {
        Index targetIndex = INDEX_FIRST_APPLICATION;
        String userInput = targetIndex.getOneBased() + "";
        String errorMessage =
                String.format(EditCommand.MESSAGE_NO_FILED_PROVIDED + "\n%1$s", EditCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, errorMessage);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_APPLICATION;
        String userInput = targetIndex.getOneBased() + POSITION_DESC_AMAZON + DEADLINE_DESC_AMAZON
                + POSITION_DESC_AMAZON + DEADLINE_DESC_AMAZON
                + POSITION_DESC_BYTEDANCE + DEADLINE_DESC_BYTEDANCE;

        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withPosition(VALID_POSITION_BYTEDANCE)
                .withDeadline(VALID_DEADLINE_BYTEDANCE)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_APPLICATION;
        String userInput = targetIndex.getOneBased() + INVALID_POSITION_DESC + POSITION_DESC_BYTEDANCE;
        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withPosition(VALID_POSITION_BYTEDANCE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + DEADLINE_DESC_BYTEDANCE + INVALID_POSITION_DESC
                + POSITION_DESC_BYTEDANCE;
        descriptor = new EditApplicationDescriptorBuilder().withPosition(VALID_POSITION_BYTEDANCE)
                .withDeadline(VALID_DEADLINE_BYTEDANCE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
