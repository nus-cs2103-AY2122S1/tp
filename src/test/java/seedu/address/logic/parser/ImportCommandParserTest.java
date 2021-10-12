package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FILENAME_FRIENDS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ImportCommand;

public class ImportCommandParserTest {

    private ImportCommandParser parser = new ImportCommandParser();

    @Test
    public void parse_fileNamePresent_success() {
        // file name present
        assertParseSuccess(parser, VALID_FILENAME_FRIENDS, new ImportCommand(VALID_FILENAME_FRIENDS));
    }

    @Test void parse_fileNameMissing_failure() {
        // file name missing
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE));
    }
}
