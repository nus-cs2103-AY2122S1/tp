package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ExportEmailCommand;

public class ExportEmailCommandParserTest {

    private ExportEmailCommandParser parser = new ExportEmailCommandParser();

    private String validPath = "src/test/data/ExportEmailTest/test.json";

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, ExportEmailCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "    ", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, ExportEmailCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "//\0.21.3?:", String.format(
            MESSAGE_INVALID_COMMAND_FORMAT, ExportEmailCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsExportEmailCommand() {
        assertParseSuccess(parser, validPath, new ExportEmailCommand(Paths.get(validPath)));
    }
}
