package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
    private Folder TestFolder1 = new Folder(new FolderName("TestFolder1"));
    private Folder TestFolder2 = new Folder(new FolderName("TestFolder2"));
    private Folder TestFolder3 = new Folder(new FolderName("TestFolder3"));
    private Folder TestFolder4 = new Folder(new FolderName("TestFolder4"));

    @Test
    public void execute_renameNonExistingFolder_failure() {
        model.addFolder(TestFolder1);
        model.addFolder(TestFolder2);
        EditFolderNameCommand editFolderNameCommand = new EditFolderNameCommand(TestFolder3, TestFolder4);
        assertThrows(CommandException.class, () -> editFolderNameCommand.execute(model));
    }

    @Test
    public void execute_renameExistingFolderToAnotherExistingFolder_failure() {
        model.addFolder(TestFolder1);
        model.addFolder(TestFolder2);
        EditFolderNameCommand editFolderNameCommand = new EditFolderNameCommand(TestFolder1, TestFolder2);
        assertThrows(CommandException.class, () -> editFolderNameCommand.execute(model));
    }

    @Test
    public void execute_renameExistingFolderToNewFolder_success() throws CommandException {
        model.addFolder(TestFolder1);
        model.addFolder(TestFolder2);
        EditFolderNameCommand editFolderNameCommand = new EditFolderNameCommand(TestFolder1,
                TestFolder3);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        String expectedMessage = String.format(EditFolderNameCommand
                .MESSAGE_SUCCESS_EDIT_FOLDER_NAME, TestFolder3);
        expectedModel.addFolder(TestFolder3);
        expectedModel.addFolder(TestFolder2);

        assertCommandSuccess(editFolderNameCommand, model,
                expectedMessage, expectedModel);
    }

}
