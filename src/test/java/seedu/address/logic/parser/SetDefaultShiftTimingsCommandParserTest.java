package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SetDefaultShiftTimingsCommand;
import seedu.address.storage.DefaultShiftTimingsStorage;

public class SetDefaultShiftTimingsCommandParserTest {

    private SetDefaultShiftTimingsCommandParser parser = new SetDefaultShiftTimingsCommandParser();


    @Test
    public void parse_emptyArg_throwsParseException() throws IOException {
        DefaultShiftTimingsStorage.load();
        DefaultShiftTimingsStorage.reset();
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetDefaultShiftTimingsCommand.HELP_MESSAGE));
    }
}
