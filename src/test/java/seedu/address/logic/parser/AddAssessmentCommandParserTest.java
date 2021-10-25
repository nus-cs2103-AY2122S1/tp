package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ASSESSMENT_NAME_DESC_LAB5;
import static seedu.address.logic.commands.CommandTestUtil.ASSESSMENT_NAME_DESC_QUIZ1;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ASSESSMENT_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SCORE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.SCORE_DESC_LAB5;
import static seedu.address.logic.commands.CommandTestUtil.SCORE_DESC_QUIZ1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ACTUAL_SCORE_LAB5;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ACTUAL_SCORE_QUIZ1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSESSMENT_NAME_LAB5;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSESSMENT_NAME_QUIZ1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCORE_QUIZ1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TOTAL_SCORE_LAB5;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TOTAL_SCORE_QUIZ1;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddAssessmentCommand;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.assessment.AssessmentName;
import seedu.address.model.assessment.Score;


public class AddAssessmentCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAssessmentCommand.MESSAGE_USAGE);

    private AddAssessmentCommandParser parser = new AddAssessmentCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_ASSESSMENT_NAME_QUIZ1 + VALID_SCORE_QUIZ1, MESSAGE_INVALID_FORMAT);

        // no assessment name specified
        assertParseFailure(parser, "1" + VALID_SCORE_QUIZ1, MESSAGE_INVALID_FORMAT);

        // no score specified
        assertParseFailure(parser, "1" + VALID_ASSESSMENT_NAME_QUIZ1, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_ASSESSMENT_NAME_QUIZ1 + VALID_SCORE_QUIZ1, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_ASSESSMENT_NAME_QUIZ1 + VALID_SCORE_QUIZ1, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid assessment name followed by valid score
        assertParseFailure(parser, "1" + INVALID_ASSESSMENT_NAME_DESC + SCORE_DESC_QUIZ1,
                AssessmentName.MESSAGE_CONSTRAINTS);

        // valid assessment name followed by invalid score
        assertParseFailure(parser, "1" + ASSESSMENT_NAME_DESC_QUIZ1 + INVALID_SCORE_DESC,
                Score.MESSAGE_CONSTRAINTS);

        // invalid assessment name and invalid score
        assertParseFailure(parser, "1" + INVALID_ASSESSMENT_NAME_DESC + INVALID_SCORE_DESC,
                AssessmentName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_STUDENT;
        String userInput = targetIndex.getOneBased() + ASSESSMENT_NAME_DESC_QUIZ1 + SCORE_DESC_QUIZ1;

        Assessment assessment = new Assessment(new AssessmentName(VALID_ASSESSMENT_NAME_QUIZ1),
                new Score(VALID_ACTUAL_SCORE_QUIZ1, VALID_TOTAL_SCORE_QUIZ1));
        AddAssessmentCommand expectedCommand = new AddAssessmentCommand(targetIndex, assessment);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + ASSESSMENT_NAME_DESC_QUIZ1 + SCORE_DESC_QUIZ1
                + ASSESSMENT_NAME_DESC_QUIZ1 + SCORE_DESC_QUIZ1 + ASSESSMENT_NAME_DESC_LAB5 + SCORE_DESC_LAB5;

        Assessment assessment = new Assessment(new AssessmentName(VALID_ASSESSMENT_NAME_LAB5),
                new Score(VALID_ACTUAL_SCORE_LAB5, VALID_TOTAL_SCORE_LAB5));
        AddAssessmentCommand expectedCommand = new AddAssessmentCommand(targetIndex, assessment);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + ASSESSMENT_NAME_DESC_QUIZ1 + INVALID_SCORE_DESC
                + SCORE_DESC_QUIZ1;
        Assessment assessment = new Assessment(new AssessmentName(VALID_ASSESSMENT_NAME_QUIZ1),
                new Score(VALID_ACTUAL_SCORE_QUIZ1, VALID_TOTAL_SCORE_QUIZ1));
        AddAssessmentCommand expectedCommand = new AddAssessmentCommand(targetIndex, assessment);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
