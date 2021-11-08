package safeforhall.logic.parser;

import static safeforhall.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import safeforhall.logic.commands.ExportCommand;

public class ExportCommandParserTest {

    private ExportCommandParser parser = new ExportCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, " ", ExportCommand.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validArgs_returnsImportCommand() {
        ExportCommand expectedExportCommand = new ExportCommand("safeforhall");
        CommandParserTestUtil.assertParseSuccess(parser,
                " safeforhall", expectedExportCommand);
    }
}
