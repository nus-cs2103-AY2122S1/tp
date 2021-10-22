package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.IDA;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

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
        ImportCommand expectedImportCommand = new ImportCommand(new ArrayList<Person>(Arrays.asList(BOB, IDA)));
        assertParseSuccess(parser, "src/test/data/ImportCommandParserTest/typicalImportFile.csv",
                expectedImportCommand);
    }

    @Test
    public void parse_InvalidNameFile_throwsParseException() {
        String filePath = "src/test/data/ImportCommandParserTest/InvalidNameImportFile.csv";
        assertThrows(ParseException.class, Name.MESSAGE_CONSTRAINTS,
                () -> parser.parse(filePath));
    }

    @Test
    public void parse_InvalidPhoneFile_throwsParseException() {
        String filePath = "src/test/data/ImportCommandParserTest/InvalidPhoneImportFile.csv";
        assertThrows(ParseException.class, Phone.MESSAGE_CONSTRAINTS,
                () -> parser.parse(filePath));
    }

    @Test
    public void parse_FileNotFound_throwsParseException() {
        String filePath = "src/test/data/ImportCommandParserTest/FileNotFound.csv";
        assertThrows(ParseException.class,
                String.format(ImportCommandParser.MESSAGE_FILE_NOT_FOUND, filePath),
                () -> parser.parse(filePath));
    }
}
