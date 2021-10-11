package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ASSESSMENT_COUNT_DESC_TYPICAL;
import static seedu.address.logic.commands.CommandTestUtil.FILE_DESC_INVALID_FILE;
import static seedu.address.logic.commands.CommandTestUtil.FILE_DESC_NON_EXISTENT_FILE;
import static seedu.address.logic.commands.CommandTestUtil.FILE_DESC_VALID_FILE;
import static seedu.address.logic.commands.CommandTestUtil.GROUP_COUNT_DESC_TYPICAL;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ASSESSMENT_COUNT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GROUP_COUNT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_COUNT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_COUNT_DESC_ABOVE_TYPICAL;
import static seedu.address.logic.commands.CommandTestUtil.TAG_COUNT_DESC_TYPICAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NON_EXISTENT_PATH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPICAL_PERSONS_ASSESSMENT_COUNT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPICAL_PERSONS_CSV_PATH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPICAL_PERSONS_GROUP_COUNT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPICAL_PERSONS_TAG_COUNT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WRONG_CSV_PATH;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ImportCommand;

public class ImportCommandParserTest {
    private ImportCommandParser parser = new ImportCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        ImportCommand expectedCommand = new ImportCommand(
                VALID_TYPICAL_PERSONS_GROUP_COUNT,
                VALID_TYPICAL_PERSONS_ASSESSMENT_COUNT,
                VALID_TYPICAL_PERSONS_TAG_COUNT,
                Path.of(VALID_TYPICAL_PERSONS_CSV_PATH));

        // standard usage
        assertParseSuccess(parser, FILE_DESC_VALID_FILE
                + GROUP_COUNT_DESC_TYPICAL + ASSESSMENT_COUNT_DESC_TYPICAL + TAG_COUNT_DESC_TYPICAL, expectedCommand);

        // with whitespace preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + FILE_DESC_VALID_FILE
                + GROUP_COUNT_DESC_TYPICAL + ASSESSMENT_COUNT_DESC_TYPICAL + TAG_COUNT_DESC_TYPICAL, expectedCommand);

        // multiple arguments provided, only last one used
        assertParseSuccess(parser, FILE_DESC_NON_EXISTENT_FILE + FILE_DESC_VALID_FILE
                + INVALID_GROUP_COUNT_DESC + GROUP_COUNT_DESC_TYPICAL
                + INVALID_ASSESSMENT_COUNT_DESC + ASSESSMENT_COUNT_DESC_TYPICAL
                + INVALID_TAG_COUNT_DESC + TAG_COUNT_DESC_TYPICAL, expectedCommand);
    }

    @Test
    public void parse_someFieldsPresent_success() {
        ImportCommand expectedCommand = new ImportCommand(
                0,
                VALID_TYPICAL_PERSONS_ASSESSMENT_COUNT,
                VALID_TYPICAL_PERSONS_TAG_COUNT,
                Path.of(VALID_TYPICAL_PERSONS_CSV_PATH));

        // group count not provided
        assertParseSuccess(parser, FILE_DESC_VALID_FILE
                + ASSESSMENT_COUNT_DESC_TYPICAL + TAG_COUNT_DESC_TYPICAL, expectedCommand);

        expectedCommand = new ImportCommand(
                VALID_TYPICAL_PERSONS_GROUP_COUNT,
                0,
                VALID_TYPICAL_PERSONS_TAG_COUNT,
                Path.of(VALID_TYPICAL_PERSONS_CSV_PATH));

        // assessment count not provided
        assertParseSuccess(parser, FILE_DESC_VALID_FILE
                + GROUP_COUNT_DESC_TYPICAL + TAG_COUNT_DESC_TYPICAL, expectedCommand);

        expectedCommand = new ImportCommand(
                VALID_TYPICAL_PERSONS_GROUP_COUNT,
                VALID_TYPICAL_PERSONS_ASSESSMENT_COUNT,
                0,
                Path.of(VALID_TYPICAL_PERSONS_CSV_PATH));

        // tag count not provided
        assertParseSuccess(parser, FILE_DESC_VALID_FILE
                + GROUP_COUNT_DESC_TYPICAL + ASSESSMENT_COUNT_DESC_TYPICAL, expectedCommand);

        expectedCommand = new ImportCommand(0, 0, 0,
                Path.of(VALID_TYPICAL_PERSONS_CSV_PATH));

        // only file provided
        assertParseSuccess(parser, FILE_DESC_VALID_FILE, expectedCommand);
    }

    @Test
    public void parse_fileNotPresent_failure() {
        assertParseFailure(parser, GROUP_COUNT_DESC_TYPICAL + ASSESSMENT_COUNT_DESC_TYPICAL
                + TAG_COUNT_DESC_TYPICAL, String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_tooManyColumnsSpecified_success() {
        ImportCommand expectedCommand = new ImportCommand(
                VALID_TYPICAL_PERSONS_GROUP_COUNT,
                VALID_TYPICAL_PERSONS_ASSESSMENT_COUNT,
                VALID_TYPICAL_PERSONS_TAG_COUNT + 1,
                Path.of(VALID_TYPICAL_PERSONS_CSV_PATH));

        // It's the command's job to read the file and validate column counts
        assertParseSuccess(parser, FILE_DESC_VALID_FILE + GROUP_COUNT_DESC_TYPICAL
                + ASSESSMENT_COUNT_DESC_TYPICAL + TAG_COUNT_DESC_ABOVE_TYPICAL, expectedCommand);
    }

    @Test
    public void parse_invalidArguments_failure() {
        // Not a number
        assertParseFailure(parser, FILE_DESC_VALID_FILE + INVALID_GROUP_COUNT_DESC
                + ASSESSMENT_COUNT_DESC_TYPICAL + TAG_COUNT_DESC_TYPICAL, ImportCommand.MESSAGE_INVALID_NUMBER);

        assertParseFailure(parser, FILE_DESC_VALID_FILE + GROUP_COUNT_DESC_TYPICAL
                + INVALID_ASSESSMENT_COUNT_DESC + TAG_COUNT_DESC_TYPICAL, ImportCommand.MESSAGE_INVALID_NUMBER);

        assertParseFailure(parser, FILE_DESC_VALID_FILE + GROUP_COUNT_DESC_TYPICAL
                + ASSESSMENT_COUNT_DESC_TYPICAL + INVALID_TAG_COUNT_DESC, ImportCommand.MESSAGE_INVALID_NUMBER);
    }

    @Test
    public void parse_badFile_success() {
        ImportCommand expectedCommand = new ImportCommand(
                VALID_TYPICAL_PERSONS_GROUP_COUNT,
                VALID_TYPICAL_PERSONS_ASSESSMENT_COUNT,
                VALID_TYPICAL_PERSONS_TAG_COUNT,
                Path.of(VALID_NON_EXISTENT_PATH));

        // path leads to a file that doesn't exist (it's the command's job to check for existence)
        assertParseSuccess(parser, FILE_DESC_NON_EXISTENT_FILE
                + GROUP_COUNT_DESC_TYPICAL + ASSESSMENT_COUNT_DESC_TYPICAL + TAG_COUNT_DESC_TYPICAL, expectedCommand);

        expectedCommand = new ImportCommand(
                VALID_TYPICAL_PERSONS_GROUP_COUNT,
                VALID_TYPICAL_PERSONS_ASSESSMENT_COUNT,
                VALID_TYPICAL_PERSONS_TAG_COUNT,
                Path.of(VALID_WRONG_CSV_PATH));

        // path leads to a file that has the wrong format (it's the command's job to read the file)
        assertParseSuccess(parser, FILE_DESC_INVALID_FILE
                + GROUP_COUNT_DESC_TYPICAL + ASSESSMENT_COUNT_DESC_TYPICAL + TAG_COUNT_DESC_TYPICAL, expectedCommand);
    }
}
