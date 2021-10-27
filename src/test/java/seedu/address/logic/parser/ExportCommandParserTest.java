package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPICAL_PERSONS_CSV_PATH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ExportCommand;


public class ExportCommandParserTest {
    private final ExportCommandParser parser = new ExportCommandParser();

    @Test
    public void parse_noArguments_success() throws Exception {
        ExportCommand expectedCommand =
                new ExportCommand(ParserUtil.parsePath(String.format(ExportCommand.BASE_PATH, ""), ".csv"));
        assertParseSuccess(parser, "", expectedCommand);
    }

    @Test
    public void parse_providedFile_success() throws Exception {
        ExportCommand expectedCommand = new ExportCommand(ParserUtil.parsePath(
                VALID_TYPICAL_PERSONS_CSV_PATH, ".csv"));
        assertParseSuccess(parser, " " + PREFIX_FILE + VALID_TYPICAL_PERSONS_CSV_PATH, expectedCommand);
    }

    @Test
    public void parse_emptyFile_failure() {
        assertParseFailure(parser, " " + PREFIX_FILE, ParserUtil.MESSAGE_EMPTY_PATH);
    }
}
