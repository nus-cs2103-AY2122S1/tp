package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.SetDefaultShiftTimingsCommand;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

public class SetDefaultShiftTimingsCommandParserTest {

    private SetDefaultShiftTimingsCommandParser parser = new SetDefaultShiftTimingsCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetDefaultShiftTimingsCommand.HELP_MESSAGE));
    }
}
