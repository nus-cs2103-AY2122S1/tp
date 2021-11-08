package seedu.address.logic.parser.student;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.student.ShowMedicalHistoryCommand;

class ShowMedicalHistoryCommandParserTest {
    private final ShowMedicalHistoryCommandParser parser = new ShowMedicalHistoryCommandParser();

    @Test
    public void parse_validArgs_returnsShowMedicalHistoryCommand() {
        assertParseSuccess(parser, "1", new ShowMedicalHistoryCommand(INDEX_FIRST_STUDENT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ShowMedicalHistoryCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ShowMedicalHistoryCommand.MESSAGE_USAGE));
    }
}
