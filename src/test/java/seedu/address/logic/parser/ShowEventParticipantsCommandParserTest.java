package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ShowEventParticipantsCommand;

public class ShowEventParticipantsCommandParserTest {
    private final ShowEventParticipantsCommandParser parser = new ShowEventParticipantsCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowEventParticipantsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsShowParticipantsCommand() {
        // exact event name
        ShowEventParticipantsCommand expectedShowEventParticipantsCommand =
                new ShowEventParticipantsCommand(Index.fromOneBased(1));
        assertParseSuccess(parser, "1", expectedShowEventParticipantsCommand);

        // event name with extra whitespaces and newlines
        assertParseSuccess(parser, " \n\n \t 1  \t", expectedShowEventParticipantsCommand);
    }
}
