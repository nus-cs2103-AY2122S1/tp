package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;


public class ImportCommandTest {
    private static final String TEST_DATA_FOLDER = "src/test/data/ImportCommandTest/";
    private static final String ERROR_FORMAT = "Failed! Entries at following rows are wrongly formatted:\n"
            + "Row2 : ";

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new AddressBook(), new UserPrefs());
    }

    @Test
    public void execute_typicalAddressBook_importSuccessful() throws CommandException {
        ImportCommand importCommand = new ImportCommand(new File(TEST_DATA_FOLDER + "validCsv.csv"));
        importCommand.execute(model);
        assertEquals(model, new ModelManager(getTypicalAddressBook(), new UserPrefs()));
    }

    @Test
    public void execute_emptyCsvFile_throwsCommandException() {
        ImportCommand importCommand = new ImportCommand(new File(TEST_DATA_FOLDER + "emptyCsv.csv"));

        assertThrows(CommandException.class,
                ImportCommand.MESSAGE_CSV_FILE_IS_EMPTY, () -> importCommand.execute(model));
    }

    @Test
    public void execute_headerOnlyCsvFile_throwsCommandException() {
        ImportCommand importCommand = new ImportCommand(new File(TEST_DATA_FOLDER + "headerOnlyCsv.csv"));

        assertThrows(CommandException.class,
                ImportCommand.MESSAGE_CSV_FILE_IS_EMPTY, () -> importCommand.execute(model));
    }

    @Test
    public void execute_tooManyColumns_throwsCommandException() {
        ImportCommand importCommand = new ImportCommand(new File(TEST_DATA_FOLDER + "tooManyColumnsCsv.csv"));

        assertThrows(CommandException.class,
                ERROR_FORMAT + ImportCommand.MESSAGE_TOO_MANY_COLUMNS, () -> importCommand.execute(model));
    }

    @Test
    public void execute_missingColumns_throwsCommandException() {
        ImportCommand importCommand = new ImportCommand(new File(TEST_DATA_FOLDER + "missingColumnsCsv.csv"));

        assertThrows(CommandException.class,
                ERROR_FORMAT + ImportCommand.MESSAGE_MISSING_COLUMNS, () -> importCommand.execute(model));
    }

    @Test
    public void execute_nonCsvFile_throwsCommandException() {
        ImportCommand importCommand = new ImportCommand(new File(TEST_DATA_FOLDER + "nonCsvFile.txt"));

        assertThrows(CommandException.class,
                ImportCommand.MESSAGE_WRONG_FILE_TYPE, () -> importCommand.execute(model));
    }

    @Test
    public void execute_phoneNumberWronglyFormatted_throwsCommandException() {
        ImportCommand importCommand = new ImportCommand(new File(TEST_DATA_FOLDER
                + "phoneNumberWronglyFormattedCsv.csv"));

        assertThrows(CommandException.class,
                ERROR_FORMAT + Phone.MESSAGE_CONSTRAINTS, () -> importCommand.execute(model));
    }

    @Test
    public void execute_emailWronglyFormatted_throwsCommandException() {
        ImportCommand importCommand = new ImportCommand(new File(TEST_DATA_FOLDER
                + "emailWronglyFormattedCsv.csv"));

        assertThrows(CommandException.class,
                ERROR_FORMAT + Email.MESSAGE_CONSTRAINTS, () -> importCommand.execute(model));
    }

    @Test
    public void execute_addressWronglyFormatted_throwsCommandException() {
        ImportCommand importCommand = new ImportCommand(new File(TEST_DATA_FOLDER
                + "addressWronglyFormattedCsv.csv"));

        assertThrows(CommandException.class,
                ERROR_FORMAT + Address.MESSAGE_CONSTRAINTS, () -> importCommand.execute(model));
    }

    @Test
    public void execute_tagsWronglyFormatted_throwsCommandException() {
        ImportCommand importCommand = new ImportCommand(new File(TEST_DATA_FOLDER
                + "tagsWronglyFormattedCsv.csv"));

        assertThrows(CommandException.class,
                ERROR_FORMAT + Tag.MESSAGE_CONSTRAINTS, () -> importCommand.execute(model));
    }
}
