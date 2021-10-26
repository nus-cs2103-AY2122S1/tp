package safeforhall.logic.commands;

//import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
//import static safeforhall.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static safeforhall.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import safeforhall.model.AddressBook;
import safeforhall.model.Model;
import safeforhall.model.ModelManager;
import safeforhall.model.UserPrefs;
import safeforhall.testutil.AddressBookBuilder;
import safeforhall.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) for {@code ExportCommand}.
 */
public class ExportCommandTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "ExportTest");

    private Model expectedModel = new ModelManager(TypicalPersons.getTypicalImportedAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        ExportCommand export1 = new ExportCommand("exportFile1");
        ExportCommand export2 = new ExportCommand("exportFile2");

        // same object -> returns true
        assertTrue(export1.equals(export1));

        // same values -> returns true
        ExportCommand export1Copy = new ExportCommand("exportFile1");
        assertTrue(export1.equals(export1Copy));

        // different types -> returns false
        assertFalse(export1.equals(1));

        // null -> returns false
        assertFalse(export1.equals(null));

        // different object -> returns false
        assertFalse(export1.equals(export2));
    }

    private Path getTestDataFilePath(String csvFileInTestDataFolder) {
        return csvFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(csvFileInTestDataFolder)
                : null;
    }

    @Test
    public void execute_emptyFilteredList_success() {
        AddressBook emptyAddressBook = new AddressBookBuilder().build();
        Model emptyModel = new ModelManager(emptyAddressBook, new UserPrefs());

    }

    @Test
    public void execute_typicalFilteredList_success() {
        Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
    }
}
