package safeforhall.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.*;

import safeforhall.model.*;
import safeforhall.model.person.*;
import safeforhall.testutil.*;

/**
 * Contains integration tests (interaction with the Model) for {@code ImportCommand}.
 */
public class ImportCommandTest {
    private Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        ImportCommand imp = new ImportCommand("safeforhall");
        ImportCommand imp2 = new ImportCommand("residents");

        // same object -> returns true
        assertTrue(imp.equals(imp));

        // same values -> returns true
        ImportCommand impCopy = new ImportCommand("safeforhall");
        assertTrue(imp.equals(impCopy));

        // different types -> returns false
        assertFalse(imp.equals(1));

        // null -> returns false
        assertFalse(imp.equals(null));

        // different person -> returns false
        assertFalse(imp.equals(imp2));
    }

    @Test
    public void execute_readCsv_success() {
        
    }
}
