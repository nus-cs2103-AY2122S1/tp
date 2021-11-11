package nustracker.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import nustracker.model.Model;
import nustracker.model.ModelManager;


public class SettingsCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_settings_success() {
        CommandResult expectedCommandResult = new CommandResult(SettingsCommand.SHOWING_SETTINGS_MESSAGE,
                false, false, true, false, false, false, false);
        CommandTestUtil.assertCommandSuccess(new SettingsCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    void equals() {
        StudentsCommand studentsCommandCommand = new StudentsCommand();

        assertTrue(studentsCommandCommand.equals(studentsCommandCommand));
        assertTrue(studentsCommandCommand.equals(new StudentsCommand()));

        assertFalse(studentsCommandCommand.equals(null));
        assertFalse(studentsCommandCommand.equals(new EventsCommand()));
    }
}
