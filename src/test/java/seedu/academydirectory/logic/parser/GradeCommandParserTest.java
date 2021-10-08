package seedu.academydirectory.logic.parser;

import static seedu.academydirectory.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_ASSESSMENT;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.academydirectory.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.academydirectory.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.academydirectory.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.academydirectory.logic.commands.GradeCommand;

public class GradeCommandParserTest {
    private final GradeCommandParser parser = new GradeCommandParser();
    private final String validAssessmentName = "RA1";
    private final String invalidAssessmentName = "QUIZ";
    private final String validGrade = "10";
    private final String invalidGrade = "999";
    private final String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, GradeCommand.MESSAGE_USAGE);

    @Test
    public void parse_validArgument_success() {
        String input = INDEX_FIRST_STUDENT.getOneBased() + " "
                + PREFIX_ASSESSMENT + validAssessmentName + " "
                + PREFIX_GRADE + validGrade;
        GradeCommand expectedCommand =
                new GradeCommand(INDEX_FIRST_STUDENT, validAssessmentName, Integer.parseInt(validGrade));

        assertParseSuccess(parser, input, expectedCommand);
    }

    @Test
    public void parse_invalidArgument_failure() {
        String inputInvalidAssessment = INDEX_FIRST_STUDENT.getOneBased() + " "
                + PREFIX_ASSESSMENT + invalidAssessmentName + " "
                + PREFIX_GRADE + validGrade;
        String inputInvalidGrade = INDEX_FIRST_STUDENT.getOneBased() + " "
                + PREFIX_ASSESSMENT + validAssessmentName + " "
                + PREFIX_GRADE + invalidGrade;

        assertParseFailure(parser, inputInvalidAssessment, expectedMessage);
        assertParseFailure(parser, inputInvalidGrade, expectedMessage);
    }
}
