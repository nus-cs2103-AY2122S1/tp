package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ASSESSMENT_NAME_DESC_LAB5;
import static seedu.address.logic.commands.CommandTestUtil.ASSESSMENT_NAME_DESC_QUIZ1;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ASSESSMENT_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSESSMENT_NAME_LAB5;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSESSMENT_NAME_QUIZ1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCORE_QUIZ1;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteAssessmentCommand;
import seedu.address.model.assessment.AssessmentName;


public class DeleteAssessmentCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAssessmentCommand.MESSAGE_USAGE);

    private DeleteAssessmentCommandParser parser = new DeleteAssessmentCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_ASSESSMENT_NAME_QUIZ1, MESSAGE_INVALID_FORMAT);

        // no assessment name specified
        assertParseFailure(parser, "1" + VALID_SCORE_QUIZ1, MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_ASSESSMENT_NAME_QUIZ1, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_ASSESSMENT_NAME_QUIZ1, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid assessment name
        assertParseFailure(parser, "1" + INVALID_ASSESSMENT_NAME_DESC, AssessmentName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_STUDENT;
        String userInput = targetIndex.getOneBased() + ASSESSMENT_NAME_DESC_QUIZ1;

        AssessmentName assessmentName = new AssessmentName(VALID_ASSESSMENT_NAME_QUIZ1);
        DeleteAssessmentCommand expectedCommand = new DeleteAssessmentCommand(targetIndex, assessmentName);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + ASSESSMENT_NAME_DESC_QUIZ1 + ASSESSMENT_NAME_DESC_LAB5;

        AssessmentName assessmentName = new AssessmentName(VALID_ASSESSMENT_NAME_LAB5);
        DeleteAssessmentCommand expectedCommand = new DeleteAssessmentCommand(targetIndex, assessmentName);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + INVALID_ASSESSMENT_NAME_DESC + ASSESSMENT_NAME_DESC_QUIZ1;
        AssessmentName assessmentName = new AssessmentName(VALID_ASSESSMENT_NAME_QUIZ1);
        DeleteAssessmentCommand expectedCommand = new DeleteAssessmentCommand(targetIndex, assessmentName);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
