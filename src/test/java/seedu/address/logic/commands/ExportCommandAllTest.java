package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ExportCommand.MESSAGE_EXPORT_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.storage.ExportStorage;

public class ExportCommandAllTest {

    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    private UserPrefs userPrefs = new UserPrefs();

    private final ExportStorage exportStorageModel = new ExportStorage(userPrefs.getExportFilePath());

    @Test
    public void execute_exportCommandAll_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXPORT_SUCCESS, false, false);
        assertCommandSuccess(new ExportCommandAll(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {

        ExportCommandAll exportFirstCommand = new ExportCommandAll();
        ExportCommandAll exportSecondCommand = new ExportCommandAll();

        // same object -> returns true
        assertTrue(exportFirstCommand.equals(exportFirstCommand));

        // different types -> returns false
        assertFalse(exportFirstCommand.equals(1));

        // null -> returns false
        assertFalse(exportFirstCommand.equals(null));

        // same -> returns true
        assertTrue(exportFirstCommand.equals(exportSecondCommand));
    }
}
