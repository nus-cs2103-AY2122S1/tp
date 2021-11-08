package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.interview.CommandTestUtil.VALID_POSITION_ADMIN;
import static seedu.address.logic.interview.CommandTestUtil.VALID_POSITION_ADMIN_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.position.AddPositionCommand;
import seedu.address.model.position.Title;

class AddPositionCommandParserTest {
    private AddPositionCommandParser parser = new AddPositionCommandParser();

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        //missing title prefix
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPositionCommand.MESSAGE_USAGE);
        assertParseFailure(parser, VALID_POSITION_ADMIN_NAME, expectedMessage);
    }

    @Test
    public void parse_validInput_success() {
        AddPositionCommand expectedCommand = new AddPositionCommand(VALID_POSITION_ADMIN);
        String userInput = " " + PREFIX_TITLE + VALID_POSITION_ADMIN_NAME;
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid position name
        assertParseFailure(parser, " " + PREFIX_TITLE + VALID_POSITION_ADMIN_NAME + "@",
                Title.MESSAGE_CONSTRAINTS);
    }
}
