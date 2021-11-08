package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.interview.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.interview.CommandTestUtil.INVALID_DURATION_TIME;
import static seedu.address.logic.interview.CommandTestUtil.INVALID_POSITION_DESC;
import static seedu.address.logic.interview.CommandTestUtil.INVALID_STATUS_DESC;
import static seedu.address.logic.interview.CommandTestUtil.INVALID_TIME_DESC;
import static seedu.address.logic.interview.CommandTestUtil.VALID_DATE;
import static seedu.address.logic.interview.CommandTestUtil.VALID_DATE_DESC;
import static seedu.address.logic.interview.CommandTestUtil.VALID_DATE_OTHER_DATE;
import static seedu.address.logic.interview.CommandTestUtil.VALID_DATE_OTHER_DATE_DESC;
import static seedu.address.logic.interview.CommandTestUtil.VALID_DURATION_DESC;
import static seedu.address.logic.interview.CommandTestUtil.VALID_DURATION_TIME;
import static seedu.address.logic.interview.CommandTestUtil.VALID_POSITION_ADMIN_ASSISTANT;
import static seedu.address.logic.interview.CommandTestUtil.VALID_POSITION_ADMIN_ASST_DESC;
import static seedu.address.logic.interview.CommandTestUtil.VALID_STATUS_COMPLETED;
import static seedu.address.logic.interview.CommandTestUtil.VALID_STATUS_COMPLETED_DESC;
import static seedu.address.logic.interview.CommandTestUtil.VALID_STATUS_PENDING;
import static seedu.address.logic.interview.CommandTestUtil.VALID_STATUS_PENDING_DESC;
import static seedu.address.logic.interview.CommandTestUtil.VALID_TIME;
import static seedu.address.logic.interview.CommandTestUtil.VALID_TIME_DESC;
import static seedu.address.logic.interview.CommandTestUtil.VALID_TIME_OTHER_TIME;
import static seedu.address.logic.interview.CommandTestUtil.VALID_TIME_OTHER_TIME_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INTERVIEW;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_POSITION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_INTERVIEW;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.interview.EditInterviewCommand;
import seedu.address.model.interview.Interview;
import seedu.address.model.position.Position;
import seedu.address.testutil.EditInterviewDescriptorBuilder;

public class EditInterviewCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ParserUtil.MESSAGE_INVALID_INDEX) + EditInterviewCommand.MESSAGE_USAGE;

    private static final String MESSAGE_INVALID_FORMAT_EMPTY_INDEX =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditInterviewCommand.MESSAGE_USAGE);

    private EditInterviewCommandParser parser = new EditInterviewCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified, followed by field name=value
        assertParseFailure(parser, VALID_DATE_DESC, MESSAGE_INVALID_FORMAT_EMPTY_INDEX);

        // no index specified, immediately followed by field value
        assertParseFailure(parser, VALID_DATE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditInterviewCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT_EMPTY_INDEX);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_DATE_DESC, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_DATE_DESC, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        //invalid position
        assertParseFailure(parser, "1" + INVALID_POSITION_DESC, Position.MESSAGE_CONSTRAINTS);

        //invalid date
        assertParseFailure(parser, "1" + INVALID_DATE_DESC, Interview.MESSAGE_DATE_CONSTRAINTS);

        //invalid time
        assertParseFailure(parser, "1" + INVALID_TIME_DESC, Interview.MESSAGE_TIME_CONSTRAINTS);

        //invalid duration
        assertParseFailure(parser, "1" + INVALID_DURATION_TIME, Interview.MESSAGE_DURATION_CONSTRAINTS_INVALID_NUMBER);

        //invalid status
        assertParseFailure(parser, "1" + INVALID_STATUS_DESC, Interview.InterviewStatus.MESSAGE_CONSTRAINTS);

        // invalid date followed by valid time
        assertParseFailure(parser,
                "1" + INVALID_DATE_DESC + VALID_TIME_DESC, Interview.MESSAGE_DATE_CONSTRAINTS);

        // valid date followed by invalid time.
        assertParseFailure(parser,
                "1" + VALID_DATE_DESC + INVALID_TIME_DESC, Interview.MESSAGE_TIME_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_DATE_DESC + INVALID_TIME_DESC,
                Interview.MESSAGE_DATE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_INTERVIEW;
        String userInput = targetIndex.getOneBased() + VALID_POSITION_ADMIN_ASST_DESC
                + VALID_DATE_DESC + VALID_TIME_DESC + VALID_DURATION_DESC + VALID_STATUS_PENDING_DESC;

        EditInterviewCommand.EditInterviewDescriptor descriptor =
                new EditInterviewDescriptorBuilder()
                        .withPosition(VALID_POSITION_ADMIN_ASSISTANT)
                        .withDate(VALID_DATE)
                        .withStartTime(VALID_TIME)
                        .withDuration(VALID_DURATION_TIME)
                        .withStatus(VALID_STATUS_PENDING).build();
        EditInterviewCommand expectedCommand = new EditInterviewCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_INTERVIEW;
        String userInput = targetIndex.getOneBased() + VALID_TIME_DESC + VALID_DURATION_DESC;

        EditInterviewCommand.EditInterviewDescriptor descriptor =
                new EditInterviewDescriptorBuilder()
                        .withStartTime(VALID_TIME).withDuration(VALID_DURATION_TIME).build();
        EditInterviewCommand expectedCommand = new EditInterviewCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // position
        Index targetIndex = INDEX_SECOND_INTERVIEW;
        String userInput = targetIndex.getOneBased() + VALID_POSITION_ADMIN_ASST_DESC;
        EditInterviewCommand.EditInterviewDescriptor descriptor =
                new EditInterviewDescriptorBuilder().withPosition(VALID_POSITION_ADMIN_ASSISTANT).build();
        EditInterviewCommand expectedCommand = new EditInterviewCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // date
        userInput = targetIndex.getOneBased() + VALID_DATE_DESC;
        descriptor = new EditInterviewDescriptorBuilder().withDate(VALID_DATE).build();
        expectedCommand = new EditInterviewCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // time
        userInput = targetIndex.getOneBased() + VALID_TIME_DESC;
        descriptor = new EditInterviewDescriptorBuilder().withStartTime(VALID_TIME).build();
        expectedCommand = new EditInterviewCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // duration
        userInput = targetIndex.getOneBased() + VALID_DURATION_DESC;
        descriptor = new EditInterviewDescriptorBuilder().withDuration(VALID_DURATION_TIME).build();
        expectedCommand = new EditInterviewCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // status
        userInput = targetIndex.getOneBased() + VALID_STATUS_COMPLETED_DESC;
        descriptor = new EditInterviewDescriptorBuilder().withStatus(VALID_STATUS_COMPLETED).build();
        expectedCommand = new EditInterviewCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_POSITION;
        String userInput = targetIndex.getOneBased() + VALID_DATE_DESC + VALID_DATE_OTHER_DATE_DESC
                + VALID_TIME_DESC + VALID_TIME_OTHER_TIME_DESC;

        EditInterviewCommand.EditInterviewDescriptor descriptor =
                new EditInterviewDescriptorBuilder().withDate(VALID_DATE_OTHER_DATE)
                        .withStartTime(VALID_TIME_OTHER_TIME).build();
        EditInterviewCommand expectedCommand = new EditInterviewCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_INTERVIEW;
        String userInput = targetIndex.getOneBased() + INVALID_DATE_DESC + VALID_DATE_DESC;
        EditInterviewCommand.EditInterviewDescriptor descriptor =
                new EditInterviewDescriptorBuilder().withDate(VALID_DATE).build();
        EditInterviewCommand expectedCommand = new EditInterviewCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + VALID_DATE_DESC + INVALID_TIME_DESC + VALID_TIME_DESC;
        descriptor = new EditInterviewDescriptorBuilder()
                .withDate(VALID_DATE).withStartTime(VALID_TIME).build();
        expectedCommand = new EditInterviewCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
