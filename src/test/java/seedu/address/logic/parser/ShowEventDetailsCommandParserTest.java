package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ShowEventDetailsCommand;

public class ShowEventDetailsCommandParserTest {

    private final ShowEventDetailsCommandParser parser = new ShowEventDetailsCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowEventDetailsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsShowDetailsCommand() {
        // exact event name
        ShowEventDetailsCommand expectedShowEventDetailsCommand =
                new ShowEventDetailsCommand(Index.fromOneBased(1));
        assertParseSuccess(parser, "1", expectedShowEventDetailsCommand);

        // event name with extra whitespaces and newlines
        assertParseSuccess(parser, " \n \n \t 1  \t", expectedShowEventDetailsCommand);
    }

}
