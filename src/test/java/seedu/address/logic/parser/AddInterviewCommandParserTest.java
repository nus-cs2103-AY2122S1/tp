package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.interview.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.interview.CommandTestUtil.INVALID_DURATION_TIME;
import static seedu.address.logic.interview.CommandTestUtil.INVALID_STATUS_DESC;
import static seedu.address.logic.interview.CommandTestUtil.INVALID_TIME_DESC;
import static seedu.address.logic.interview.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.interview.CommandTestUtil.VALID_CANDIDATE_DESC_ALICE;
import static seedu.address.logic.interview.CommandTestUtil.VALID_CANDIDATE_DESC_BOB;
import static seedu.address.logic.interview.CommandTestUtil.VALID_DATE;
import static seedu.address.logic.interview.CommandTestUtil.VALID_DATE_DESC;
import static seedu.address.logic.interview.CommandTestUtil.VALID_DURATION_DESC;
import static seedu.address.logic.interview.CommandTestUtil.VALID_DURATION_TIME;
import static seedu.address.logic.interview.CommandTestUtil.VALID_NAME_ALICE;
import static seedu.address.logic.interview.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.interview.CommandTestUtil.VALID_POSITION_ADMIN;
import static seedu.address.logic.interview.CommandTestUtil.VALID_POSITION_ADMIN_ASST_DESC;
import static seedu.address.logic.interview.CommandTestUtil.VALID_POSITION_ADMIN_DESC;
import static seedu.address.logic.interview.CommandTestUtil.VALID_STATUS_COMPLETED_DESC;
import static seedu.address.logic.interview.CommandTestUtil.VALID_STATUS_PENDING_DESC;
import static seedu.address.logic.interview.CommandTestUtil.VALID_TIME;
import static seedu.address.logic.interview.CommandTestUtil.VALID_TIME_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPositions.ADMIN_ASSISTANT;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.interview.AddInterviewCommand;
import seedu.address.model.interview.Interview;
import seedu.address.testutil.InterviewBuilder;



class AddInterviewCommandParserTest {
    private AddInterviewCommandParser parser = new AddInterviewCommandParser();


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddInterviewCommand.MESSAGE_USAGE);

        // missing position prefix
        assertParseFailure(parser, VALID_POSITION_ADMIN.getTitle().fullTitle
                        + VALID_CANDIDATE_DESC_ALICE + VALID_CANDIDATE_DESC_BOB + VALID_DATE_DESC
                        + VALID_TIME_DESC + VALID_DURATION_DESC + VALID_STATUS_COMPLETED_DESC,
                expectedMessage);

        // missing date prefix
        assertParseFailure(parser, VALID_POSITION_ADMIN_DESC + VALID_CANDIDATE_DESC_ALICE + VALID_CANDIDATE_DESC_BOB
                        + " " + VALID_DATE + VALID_TIME_DESC + VALID_DURATION_DESC,
                expectedMessage);

        // missing time prefix
        assertParseFailure(parser, VALID_POSITION_ADMIN_DESC + VALID_CANDIDATE_DESC_ALICE + VALID_CANDIDATE_DESC_BOB
                        + VALID_DATE_DESC + " " + VALID_TIME + VALID_DURATION_DESC,
                expectedMessage);

        // missing duration prefix
        assertParseFailure(parser, VALID_POSITION_ADMIN_DESC + VALID_CANDIDATE_DESC_ALICE + VALID_CANDIDATE_DESC_BOB
                        + VALID_DATE_DESC + VALID_TIME_DESC + " " + VALID_DURATION_TIME,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_POSITION_ADMIN.getTitle() + " " + VALID_NAME_ALICE + " "
                        + VALID_NAME_BOB + " " + VALID_DATE + " " + VALID_TIME + " "
                        + VALID_DURATION_TIME,
                expectedMessage);

    }

    @Test
    public void parse_invalidValue_failure() {

        // invalid date
        assertParseFailure(parser, VALID_POSITION_ADMIN_DESC + VALID_CANDIDATE_DESC_ALICE
                + VALID_CANDIDATE_DESC_BOB + INVALID_DATE_DESC
                + VALID_TIME_DESC + VALID_DURATION_DESC, Interview.MESSAGE_DATE_CONSTRAINTS);

        // invalid time
        assertParseFailure(parser, VALID_POSITION_ADMIN_DESC + VALID_CANDIDATE_DESC_ALICE
                + VALID_CANDIDATE_DESC_BOB + VALID_DATE_DESC
                + INVALID_TIME_DESC + VALID_DURATION_DESC, Interview.MESSAGE_TIME_CONSTRAINTS);

        // invalid duration
        assertParseFailure(parser, VALID_POSITION_ADMIN_DESC + VALID_CANDIDATE_DESC_ALICE
                + VALID_CANDIDATE_DESC_BOB + VALID_DATE_DESC
                + VALID_TIME_DESC + INVALID_DURATION_TIME, Interview.MESSAGE_DURATION_CONSTRAINTS_INVALID_NUMBER);

        // invalid status
        assertParseFailure(parser, VALID_POSITION_ADMIN_DESC + VALID_CANDIDATE_DESC_ALICE
                        + VALID_CANDIDATE_DESC_BOB + VALID_DATE_DESC
                        + VALID_TIME_DESC + VALID_DURATION_DESC + INVALID_STATUS_DESC,
                Interview.InterviewStatus.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, VALID_POSITION_ADMIN_DESC + VALID_CANDIDATE_DESC_ALICE
                + VALID_CANDIDATE_DESC_BOB + INVALID_DATE_DESC
                + VALID_TIME_DESC + VALID_DURATION_DESC + INVALID_STATUS_DESC, Interview.MESSAGE_DATE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + VALID_POSITION_ADMIN_DESC + VALID_CANDIDATE_DESC_ALICE
                        + VALID_CANDIDATE_DESC_BOB
                        + VALID_DATE_DESC + VALID_TIME_DESC + VALID_DURATION_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddInterviewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_allFieldsPresent_success() {
        Interview expectedInterview = new InterviewBuilder().withPosition(ADMIN_ASSISTANT)
                //at the time when AddInterviewCommand is created, the candidate couldn't have been added before
                //execute is called, hence testing with no candidates
                .withCandidates(new HashSet<>())
                .withDate(LocalDate.of(2021, 10, 18))
                .withStartTime(LocalTime.of(12, 0)).withDuration(Duration.ofMinutes(180))
                .build();
        String userInput = VALID_POSITION_ADMIN_ASST_DESC + VALID_CANDIDATE_DESC_ALICE
                + VALID_DATE_DESC + VALID_TIME_DESC + VALID_DURATION_DESC + VALID_STATUS_PENDING_DESC;
        assertParseSuccess(parser, userInput, new AddInterviewCommand(expectedInterview,
                Set.of(Index.fromZeroBased(0))));
    }

}
