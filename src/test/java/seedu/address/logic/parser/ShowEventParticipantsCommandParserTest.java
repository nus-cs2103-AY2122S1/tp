package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ShowEventParticipantsCommand;
import seedu.address.model.event.EventNamePredicate;

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
                new ShowEventParticipantsCommand(new EventNamePredicate("240Km Marathon"));
        assertParseSuccess(parser, "240Km Marathon", expectedShowEventParticipantsCommand);

        // event name with extra whitespaces and newlines
        assertParseSuccess(parser, " \n 240Km \n \t Marathon  \t", expectedShowEventParticipantsCommand);
    }
}
