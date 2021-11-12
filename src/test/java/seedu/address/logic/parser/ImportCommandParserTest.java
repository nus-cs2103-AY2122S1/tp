package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ImportCommand;

public class ImportCommandParserTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "ImportCommandParserTest");
    private static final Path TYPICAL_IMPORT_FILE = TEST_DATA_FOLDER.resolve("typicalImportFile.csv");
    private static final Path INVALID_FORMAT_FILE = TEST_DATA_FOLDER.resolve("typicalImportFile.csv");

    private final ImportCommandParser parser = new ImportCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE));
    }
}
