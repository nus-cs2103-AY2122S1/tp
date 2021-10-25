package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.ALICE_DIFFERENT_PHONE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Availability;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class ImportCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_personNotInAddressBook_listOfPersonAddedWithNoReplacement() {
        String testFilePath = "src/test/data/ImportCommandTest/SamePersonNotInAddressBook.csv";
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(HOON);
        expectedModel.addPerson(IDA);
        assertCommandSuccess(new ImportCommand(testFilePath), model, ImportCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_personInAddressBook_listOfPersonAddedWithReplacement() {
        String testFilePath = "src/test/data/ImportCommandTest/SamePersonInAddressBook.csv";
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(HOON);
        expectedModel.setPerson(ALICE, ALICE_DIFFERENT_PHONE);
        assertCommandSuccess(new ImportCommand(testFilePath), model, ImportCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_invalidNameImportFile_throwsCommandException() {
        String testFilePath = "src/test/data/ImportCommandTest/InvalidNameImportFile.csv";
        ImportCommand importCommand = new ImportCommand(testFilePath);
        assertThrows(CommandException.class, Name.MESSAGE_CONSTRAINTS, () -> importCommand.execute(model));
    }

    @Test
    public void execute_invalidPhoneImportFile_throwsCommandException() {
        String testFilePath = "src/test/data/ImportCommandTest/InvalidPhoneImportFile.csv";
        ImportCommand importCommand = new ImportCommand(testFilePath);
        assertThrows(CommandException.class, Phone.MESSAGE_CONSTRAINTS, () -> importCommand.execute(model));
    }

    @Test
    public void execute_invalidAvailabilityImportFile_throwsCommandException() {
        String testFilePath = "src/test/data/ImportCommandTest/InvalidAvailabilityImportFile.csv";
        ImportCommand importCommand = new ImportCommand(testFilePath);
        assertThrows(CommandException.class, Availability.MESSAGE_CONSTRAINTS, () -> importCommand.execute(model));
    }

    @Test
    public void execute_invalidTagsImportFile_throwsCommandException() {
        String testFilePath = "src/test/data/ImportCommandTest/InvalidTagsImportFile.csv";
        ImportCommand importCommand = new ImportCommand(testFilePath);
        assertThrows(CommandException.class, Tag.MESSAGE_CONSTRAINTS, () -> importCommand.execute(model));
    }

    @Test
    public void execute_invalidFormatImportFile_throwsCommandException() {
        String testFilePath = "src/test/data/ImportCommandTest/InvalidFormatImportFile.csv";
        ImportCommand importCommand = new ImportCommand(testFilePath);
        assertThrows(CommandException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ImportCommand.MESSAGE_USAGE), () -> importCommand.execute(model));
    }

    @Test
    public void execute_fileNotFound_throwsCommandException() {
        String testFilePath = "src/test/data/ImportCommandParserTest/FileNotFound.csv";
        ImportCommand importCommand = new ImportCommand(testFilePath);
        assertThrows(CommandException.class,
                String.format(ImportCommand.MESSAGE_FILE_NOT_FOUND, testFilePath), () -> importCommand.execute(model));
    }

}
