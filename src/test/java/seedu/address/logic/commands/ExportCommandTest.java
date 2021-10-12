package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.FileUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ExportCommandTest {
    private static final String PATH_EMPTY_FOLDER = "src/test/data/ExportImportCommandTest/EmptyFolder/";
    private static final String PATH_FOLDER_WITH_JSON = "src/test/data/ExportImportCommandTest/FolderWithJson/";
    private static final String TEST_FILE_NAME = "testFile";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        ExportCommand exportFirstCommand = new ExportCommand("first");
        ExportCommand exportSecondCommand = new ExportCommand("second");

        // same object -> returns true
        assertTrue(exportFirstCommand.equals(exportFirstCommand));

        // same filename -> returns true
        assertTrue(exportFirstCommand.equals(new ExportCommand("first")));

        // different types -> returns false
        assertFalse(exportFirstCommand.equals(1));

        // null -> returns false
        assertFalse(exportFirstCommand.equals(null));

        // different filename -> returns false
        assertFalse(exportFirstCommand.equals(exportSecondCommand));
    }

    /**
     * Export command creates a file when there is no existing file with the given name.
     * Test file is deleted after creation.
     * Test occurs in src/test/data/ExportImportCommandTest/EmptyFolder.
     */
    @Test
    public void execute_fileName_fileCreated() {
        String expectedMessage = String.format(ExportCommand.MESSAGE_EXPORT_SUCCESS, TEST_FILE_NAME);

        ExportCommand exportCommand = new ExportCommand(PATH_EMPTY_FOLDER, TEST_FILE_NAME);
        assertCommandSuccess(exportCommand, model, expectedMessage, expectedModel);
        try {
            FileUtil.deleteFileIfExists(Path.of(PATH_EMPTY_FOLDER + TEST_FILE_NAME + ".json"));
        } catch (IOException ioe) {
            System.out.println("Error deleting testFile.json");
        }
    }

    /**
     * Export command fails when there is an existing file with the given name.
     * Test occurs in src/test/data/ExportImportCommandTest/FolderWithJson.
     */
    @Test
    public void execute_fileName_fileExists() {
        String expectedMessage = String.format(ExportCommand.MESSAGE_EXPORT_FAILURE, TEST_FILE_NAME);
        ExportCommand exportCommand = new ExportCommand(PATH_FOLDER_WITH_JSON, TEST_FILE_NAME);
        assertCommandFailure(exportCommand, model, expectedMessage);
    }
}
