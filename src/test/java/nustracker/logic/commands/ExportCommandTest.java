package nustracker.logic.commands;

import static nustracker.logic.commands.CommandTestUtil.VALID_EXPORT_FILE_NAME;

import org.junit.jupiter.api.Test;

import nustracker.testutil.Assert;
import nustracker.ui.MainWindow.CurrentlyShownList;

class ExportCommandTest {

    @Test
    public void constructor_nullPath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new ExportCommand(null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        Assert.assertThrows(
                NullPointerException.class, () ->
                        new ExportCommand(VALID_EXPORT_FILE_NAME).execute(null,
                                CurrentlyShownList.STUDENTS_LIST));
    }


}
