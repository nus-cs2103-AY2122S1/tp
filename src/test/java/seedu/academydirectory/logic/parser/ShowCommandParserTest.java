package seedu.academydirectory.logic.parser;

import static seedu.academydirectory.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.academydirectory.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.academydirectory.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.academydirectory.logic.commands.ShowCommand;

public class ShowCommandParserTest {
    private final ShowCommandParser parser = new ShowCommandParser();
    private final String validAssessmentName = "RA1";
    private final String invalidAssessmentName = "QUIZ";
    private final String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowCommand.MESSAGE_USAGE);

    @Test
    public void parse_validAssessmentName_success() {
        assertParseSuccess(parser, validAssessmentName, new ShowCommand(validAssessmentName));
    }

    @Test
    public void parse_invalidAssessmentName_failure() {
        assertParseFailure(parser, invalidAssessmentName, expectedMessage);
    }
}
