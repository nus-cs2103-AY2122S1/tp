package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ShowDetailsCommand;
import seedu.address.model.event.EventNamePredicate;

public class ShowDetailsCommandParserTest {

    private final ShowDetailsCommandParser parser = new ShowDetailsCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowDetailsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsShowDetailsCommand() {
        // exact event name
        ShowDetailsCommand expectedShowDetailsCommand =
                new ShowDetailsCommand(new EventNamePredicate("240Km Marathon"));
        assertParseSuccess(parser, "240Km Marathon", expectedShowDetailsCommand);

        // event name with extra whitespaces and newlines
        assertParseSuccess(parser, " \n 240Km \n \t Marathon  \t", expectedShowDetailsCommand);
    }

}
