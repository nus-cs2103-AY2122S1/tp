package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.File;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.CsvWriter;
import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ExportCommandParserTest {
    private static final String EXPORTS_DIRECTORY_PATH = System.getProperty("user.dir")
            + File.separator
            + "exports";

    @Test
    public void parse_invalidFileName_throwsParseException() {
        ExportCommandParser parser = new ExportCommandParser();
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportCommand.MESSAGE_USAGE), (
                ) -> parser.parse("test@export"));
    }

    @Test
    public void parse_valid_success() throws Exception {
        ExportCommandParser parser = new ExportCommandParser();
        ExportCommand producedCommand = parser.parse("test");
        assertEquals(producedCommand,
                new ExportCommand(EXPORTS_DIRECTORY_PATH + File.separator + "test.csv",
                new CsvWriter()));
    }
}
