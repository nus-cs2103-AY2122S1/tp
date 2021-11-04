package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMembers.ALICE;
import static seedu.address.testutil.TypicalMembers.ALICE_DIFFERENT_PHONE;
import static seedu.address.testutil.TypicalMembers.HOON;
import static seedu.address.testutil.TypicalMembers.IDA;
import static seedu.address.testutil.TypicalSportsPa.getTypicalSportsPa;

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
    private final Model model = new ModelManager(getTypicalSportsPa(), new UserPrefs());

    @Test
    public void execute_personNotInAddressBook_listOfPersonAddedWithNoReplacement() {
        String testFilePath = "src/test/data/ImportCommandTest/SameMemberNotInSportsPa.csv";
        Model expectedModel = new ModelManager(model.getSportsPa(), new UserPrefs());
        expectedModel.addMember(HOON);
        expectedModel.addMember(IDA);
        assertCommandSuccess(new ImportCommand(testFilePath), model, ImportCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_personInAddressBook_listOfPersonAddedWithReplacement() {
        String testFilePath = "src/test/data/ImportCommandTest/SameMemberInSportsPa.csv";
        Model expectedModel = new ModelManager(model.getSportsPa(), new UserPrefs());
        expectedModel.addMember(HOON);
        expectedModel.setMember(ALICE, ALICE_DIFFERENT_PHONE);
        assertCommandSuccess(new ImportCommand(testFilePath), model, ImportCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_invalidNameImportFile_throwsCommandException() {
        String testFilePath = "src/test/data/ImportCommandTest/InvalidNameImportFile.csv";
        String expectedMessage = Name.MESSAGE_CONSTRAINTS + "\nPlease fix the error in the CSV file and try again";
        ImportCommand importCommand = new ImportCommand(testFilePath);
        assertThrows(CommandException.class, expectedMessage, () -> importCommand.execute(model));
    }

    @Test
    public void execute_invalidPhoneImportFile_throwsCommandException() {
        String testFilePath = "src/test/data/ImportCommandTest/InvalidPhoneImportFile.csv";
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS + "\nPlease fix the error in the CSV file and try again";
        ImportCommand importCommand = new ImportCommand(testFilePath);
        assertThrows(CommandException.class, expectedMessage, () -> importCommand.execute(model));
    }

    @Test
    public void execute_invalidAvailabilityImportFile_throwsCommandException() {
        String testFilePath = "src/test/data/ImportCommandTest/InvalidAvailabilityImportFile.csv";
        String expectedMessage = Availability.MESSAGE_CONSTRAINTS
                + "\nPlease fix the error in the CSV file and try again";
        ImportCommand importCommand = new ImportCommand(testFilePath);
        assertThrows(CommandException.class, expectedMessage, () -> importCommand.execute(model));
    }

    @Test
    public void execute_invalidTagsImportFile_throwsCommandException() {
        String testFilePath = "src/test/data/ImportCommandTest/InvalidTagsImportFile.csv";
        String expectedMessage = Tag.MESSAGE_CONSTRAINTS + "\nPlease fix the error in the CSV file and try again";
        ImportCommand importCommand = new ImportCommand(testFilePath);
        assertThrows(CommandException.class, expectedMessage, () -> importCommand.execute(model));
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
