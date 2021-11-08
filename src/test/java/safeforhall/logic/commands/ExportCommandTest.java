package safeforhall.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import safeforhall.logic.commands.exceptions.CommandException;
import safeforhall.model.AddressBook;
import safeforhall.model.Model;
import safeforhall.model.ModelManager;
import safeforhall.model.UserPrefs;
import safeforhall.testutil.AddressBookBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code ExportCommand}.
 */
public class ExportCommandTest {
    private static final String TEST_DATA_FOLDER = "src/test/data/ExportTest/";

    private AddressBook emptyAddressBook = new AddressBookBuilder().build();
    private Model emptyModel = new ModelManager(emptyAddressBook, new UserPrefs());


    @Test
    public void equals() {
        ExportCommand export1 = new ExportCommand(TEST_DATA_FOLDER, "exportFile1");
        ExportCommand export2 = new ExportCommand(TEST_DATA_FOLDER, "exportFile2");

        // same object -> returns true
        assertTrue(export1.equals(export1));

        // same values -> returns true
        ExportCommand export1Copy = new ExportCommand(TEST_DATA_FOLDER, "exportFile1");
        assertTrue(export1.equals(export1Copy));

        // different types -> returns false
        assertFalse(export1.equals(1));

        // null -> returns false
        assertFalse(export1.equals(null));

        // different object -> returns false
        assertFalse(export1.equals(export2));
    }

    @Test
    public void getEmailArr_emptyFilteredList_returnsEmptyArray() {
        ExportCommand exportCommand = new ExportCommand(TEST_DATA_FOLDER, "");
        ArrayList<String[]> emailArr = exportCommand.getEmailArr(emptyModel.getFilteredPersonList());
        ArrayList<String[]> expectedEmailArr = new ArrayList<>();

        assertEquals(emailArr, expectedEmailArr);
    }

    @Test
    public void writeCsv_duplicateFile_throwsException() {
        try {
            ExportCommand exportCommand1 = new ExportCommand(TEST_DATA_FOLDER, "emptyArrTest");
            ExportCommand exportCommand2 = new ExportCommand(TEST_DATA_FOLDER, "emptyArrTest");
            ArrayList<String[]> emptyEmailArr = exportCommand1.getEmailArr(emptyModel.getFilteredPersonList());
            ArrayList<String[]> emptyEmailArr2 = exportCommand2.getEmailArr(emptyModel.getFilteredPersonList());
            exportCommand1.writeCsv(emptyEmailArr);
            exportCommand2.writeCsv(emptyEmailArr2);
            assertEquals(1, 2);
        } catch (CommandException e) {
            assertEquals(1, 1);
        }
    }
}
