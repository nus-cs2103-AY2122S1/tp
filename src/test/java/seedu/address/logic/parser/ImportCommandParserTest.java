package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.CHARLIE;
import static seedu.address.testutil.TypicalPersons.IDA;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Availability;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

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

    @Test
    public void parse_typicalImportFile_returnsFindCommand() {
        ImportCommand expectedImportCommand = new ImportCommand(
                new ArrayList<Person>(Arrays.asList(BOB, IDA, AMY, CHARLIE)));
        assertParseSuccess(parser, "src/test/data/ImportCommandParserTest/typicalImportFile.csv",
                expectedImportCommand);
    }

    @Test
    public void parse_invalidNameImportFile_throwsParseException() {
        String filePath = "src/test/data/ImportCommandParserTest/InvalidNameImportFile.csv";
        assertThrows(ParseException.class, Name.MESSAGE_CONSTRAINTS, () -> parser.parse(filePath));
    }

    @Test
    public void parse_invalidPhoneImportFile_throwsParseException() {
        String filePath = "src/test/data/ImportCommandParserTest/InvalidPhoneImportFile.csv";
        assertThrows(ParseException.class, Phone.MESSAGE_CONSTRAINTS, () -> parser.parse(filePath));
    }

    @Test
    public void parse_invalidAvailabilityImportFile_throwsParseException() {
        String filePath = "src/test/data/ImportCommandParserTest/InvalidAvailabilityImportFile.csv";
        assertThrows(ParseException.class, Availability.MESSAGE_CONSTRAINTS, () -> parser.parse(filePath));
    }

    @Test
    public void parse_invalidTagsImportFile_throwsParseException() {
        String filePath = "src/test/data/ImportCommandParserTest/InvalidTagsImportFile.csv";
        assertThrows(ParseException.class, Tag.MESSAGE_CONSTRAINTS, () -> parser.parse(filePath));
    }

    @Test
    public void parse_invalidFormatImportFile_throwsParseExecption() {
        String filepath = "src/test/data/ImportCommandParserTest/InvalidFormatImportFile.csv";
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ImportCommand.MESSAGE_USAGE), () -> parser.parse(filepath));
    }

    @Test
    public void parse_fileNotFound_throwsParseException() {
        String filePath = "src/test/data/ImportCommandParserTest/FileNotFound.csv";
        assertThrows(ParseException.class,
                String.format(ImportCommandParser.MESSAGE_FILE_NOT_FOUND, filePath), () -> parser.parse(filePath));
    }
}
