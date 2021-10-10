package seedu.plannermd.logic.parser;

import static seedu.plannermd.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.plannermd.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.plannermd.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.plannermd.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.plannermd.logic.commands.deletecommand.DeleteDoctorCommand;

public class DeleteDoctorCommandParserTest {

    private DeleteDoctorCommandParser parser = new DeleteDoctorCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteDoctorCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteDoctorCommand.MESSAGE_USAGE));
    }
}
