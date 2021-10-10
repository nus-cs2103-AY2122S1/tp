package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ImportCommand;

import java.nio.file.Path;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;


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
        assertParseSuccess(parser, FILE_DESC_VALID_FILE +
                GROUP_COUNT_DESC_TYPICAL + ASSESSMENT_COUNT_DESC_TYPICAL + TAG_COUNT_DESC_TYPICAL, expectedCommand);

        // with whitespace preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + FILE_DESC_VALID_FILE +
                GROUP_COUNT_DESC_TYPICAL + ASSESSMENT_COUNT_DESC_TYPICAL + TAG_COUNT_DESC_TYPICAL, expectedCommand);

        // multiple arguments provided, only last one used
        assertParseSuccess(parser, INVALID_FILE_DESC + FILE_DESC_VALID_FILE
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
        assertParseSuccess(parser, FILE_DESC_VALID_FILE +
                ASSESSMENT_COUNT_DESC_TYPICAL + TAG_COUNT_DESC_TYPICAL, expectedCommand);

        expectedCommand = new ImportCommand(
                VALID_TYPICAL_PERSONS_GROUP_COUNT,
                0,
                VALID_TYPICAL_PERSONS_TAG_COUNT,
                Path.of(VALID_TYPICAL_PERSONS_CSV_PATH));

        // assessment count not provided
        assertParseSuccess(parser, FILE_DESC_VALID_FILE +
                GROUP_COUNT_DESC_TYPICAL + TAG_COUNT_DESC_TYPICAL, expectedCommand);

        expectedCommand = new ImportCommand(
                VALID_TYPICAL_PERSONS_GROUP_COUNT,
                VALID_TYPICAL_PERSONS_ASSESSMENT_COUNT,
                0,
                Path.of(VALID_TYPICAL_PERSONS_CSV_PATH));

        // tag count not provided
        assertParseSuccess(parser, FILE_DESC_VALID_FILE +
                GROUP_COUNT_DESC_TYPICAL + ASSESSMENT_COUNT_DESC_TYPICAL, expectedCommand);

        expectedCommand = new ImportCommand(0, 0, 0,
                Path.of(VALID_TYPICAL_PERSONS_CSV_PATH));

        // only file provided
        assertParseSuccess(parser, FILE_DESC_VALID_FILE, expectedCommand);
    }

    @Test
    public void parse_fileNotPresent_failure() {
        assertParseFailure(parser,GROUP_COUNT_DESC_TYPICAL + ASSESSMENT_COUNT_DESC_TYPICAL +
                TAG_COUNT_DESC_TYPICAL, MESSAGE_INVALID_COMMAND_FORMAT + ImportCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_tooManyColumnsSpecified_failure() {
        assertParseFailure(parser, FILE_DESC_VALID_FILE + GROUP_COUNT_DESC_TYPICAL +
                ASSESSMENT_COUNT_DESC_TYPICAL + TAG_COUNT_DESC_ABOVE_TYPICAL, ImportCommand.MESSAGE_OUT_OF_BOUNDS);
    }

    @Test
    public void parse_invalidArguments_failure() {
        // Provided path cannot be converted into a Path
        assertParseFailure(parser, INVALID_FILE_DESC + GROUP_COUNT_DESC_TYPICAL +
                ASSESSMENT_COUNT_DESC_TYPICAL + TAG_COUNT_DESC_ABOVE_TYPICAL, ImportCommand.MESSAGE_INVALID_FILE);

        assertParseFailure(parser, FILE_DESC_VALID_FILE + INVALID_GROUP_COUNT_DESC +
                ASSESSMENT_COUNT_DESC_TYPICAL + TAG_COUNT_DESC_TYPICAL, ImportCommand.MESSAGE_INVALID_NUMBER);

        assertParseFailure(parser, FILE_DESC_VALID_FILE + GROUP_COUNT_DESC_TYPICAL +
                INVALID_ASSESSMENT_COUNT_DESC + TAG_COUNT_DESC_TYPICAL, ImportCommand.MESSAGE_INVALID_NUMBER);

        assertParseFailure(parser, FILE_DESC_VALID_FILE + GROUP_COUNT_DESC_TYPICAL +
                ASSESSMENT_COUNT_DESC_TYPICAL + INVALID_TAG_COUNT_DESC, ImportCommand.MESSAGE_INVALID_NUMBER);
        // todo: check why this isn't working -- the invalid number isnt being thrown
    }

    @Test
    public void parse_validWrongFile_success() {
        ImportCommand expectedCommand = new ImportCommand(
                VALID_TYPICAL_PERSONS_GROUP_COUNT,
                VALID_TYPICAL_PERSONS_ASSESSMENT_COUNT,
                VALID_TYPICAL_PERSONS_TAG_COUNT,
                Path.of(VALID_NON_EXISTENT_PATH));

        // path leads to a file that doesn't exist (it's the command's job to check for existence)
        assertParseSuccess(parser, FILE_DESC_NON_EXISTENT_FILE +
                GROUP_COUNT_DESC_TYPICAL + ASSESSMENT_COUNT_DESC_TYPICAL + TAG_COUNT_DESC_TYPICAL, expectedCommand);

        expectedCommand = new ImportCommand(
                VALID_TYPICAL_PERSONS_GROUP_COUNT,
                VALID_TYPICAL_PERSONS_ASSESSMENT_COUNT,
                VALID_TYPICAL_PERSONS_TAG_COUNT,
                Path.of(VALID_WRONG_CSV_PATH));

        // path leads to a file that has the wrong format (it's the command's job to read the file)
        assertParseSuccess(parser, FILE_DESC_INVALID_FILE +
                GROUP_COUNT_DESC_TYPICAL + ASSESSMENT_COUNT_DESC_TYPICAL + TAG_COUNT_DESC_TYPICAL, expectedCommand);
    }
}
