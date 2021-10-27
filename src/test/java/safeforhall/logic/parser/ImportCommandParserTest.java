package safeforhall.logic.parser;

import static safeforhall.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import safeforhall.logic.commands.ExportCommand;
import safeforhall.logic.commands.ImportCommand;

public class ImportCommandParserTest {

    private ImportCommandParser parser = new ImportCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, " ", ExportCommand.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validArgs_returnsImportCommand() {
        ImportCommand expectedImportCommand = new ImportCommand("safeforhall");
        CommandParserTestUtil.assertParseSuccess(parser,
                " safeforhall", expectedImportCommand);
    }
}
