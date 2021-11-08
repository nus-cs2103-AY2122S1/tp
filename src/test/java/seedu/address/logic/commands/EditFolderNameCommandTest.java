package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.folder.Folder;
import seedu.address.model.folder.FolderName;

public class EditFolderNameCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Folder testFolder1 = new Folder(new FolderName("TestFolder1"));
    private Folder testFolder2 = new Folder(new FolderName("TestFolder2"));
    private Folder testFolder3 = new Folder(new FolderName("TestFolder3"));
    private Folder testFolder4 = new Folder(new FolderName("TestFolder4"));

    @Test
    public void execute_renameNonExistingFolder_failure() {
        model.addFolder(testFolder1);
        model.addFolder(testFolder2);
        EditFolderNameCommand editFolderNameCommand = new EditFolderNameCommand(testFolder3, testFolder4);
        assertThrows(CommandException.class, () -> editFolderNameCommand.execute(model));
    }

    @Test
    public void execute_renameExistingFolderToAnotherExistingFolder_failure() {
        model.addFolder(testFolder1);
        model.addFolder(testFolder2);
        EditFolderNameCommand editFolderNameCommand = new EditFolderNameCommand(testFolder1, testFolder2);
        assertThrows(CommandException.class, () -> editFolderNameCommand.execute(model));
    }

    @Test
    public void execute_renameExistingFolderToNewFolder_success() {
        model.addFolder(testFolder1);
        model.addFolder(testFolder2);
        EditFolderNameCommand editFolderNameCommand = new EditFolderNameCommand(testFolder1,
                testFolder3);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        String expectedMessage = String.format(EditFolderNameCommand
                .MESSAGE_SUCCESS_EDIT_FOLDER_NAME, testFolder3);
        expectedModel.addFolder(testFolder3);
        expectedModel.addFolder(testFolder2);

        assertCommandSuccess(editFolderNameCommand, model,
                expectedMessage, expectedModel);
    }

    @Test
    public void execute_renameExistingFolderWithSameName_failure() {
        model.addFolder(testFolder1);
        EditFolderNameCommand editFolderNameCommand = new EditFolderNameCommand(testFolder1, testFolder1);
        assertThrows(CommandException.class, () -> editFolderNameCommand.execute(model));
    }

    @Test
    public void execute_folderWithLongName_failure() {
        String longFolderName = "Some Super Strange Long Folder Name";
        Folder longFolder = new Folder(new FolderName(longFolderName));
        model.addFolder(testFolder1);
        EditFolderNameCommand editFolderCommand = new EditFolderNameCommand(testFolder1, longFolder);
        assertCommandFailure(editFolderCommand, model, EditFolderNameCommand.MESSAGE_FOLDER_NAME_TOO_LONG);
    }

}
