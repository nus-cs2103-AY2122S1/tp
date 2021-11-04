package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class AccessCacheCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AccessCacheCommand(null));
    }

    @Test
    public void execute_validKey_success() throws Exception {
        AccessCacheCommand accessCacheCommandOne = new AccessCacheCommand("UP");
        AccessCacheCommand accessCacheCommandTwo = new AccessCacheCommand("DOWN");
        String expectedMessage = "";
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        CommandResult expectedResultOne = accessCacheCommandOne.execute(expectedModel);
        CommandResult expectedResultTwo = accessCacheCommandTwo.execute(expectedModel);
        CommandResult actualResult =
                new CommandResult("", false, false, true, "");

        assertEquals(expectedResultOne, actualResult);
        assertEquals(expectedResultTwo, actualResult);
    }

    @Test
    public void execute_invalidKey_failure() throws Exception {
        AccessCacheCommand accessCacheCommand = new AccessCacheCommand("asdf");
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        String expectedMessage = "Unknown Error in AccessCacheCommand#Execute!";
        assertCommandFailure(accessCacheCommand, expectedModel, expectedMessage);
    }

    @Test
    public void equals() {
        AccessCacheCommand accessCacheCommandOne = new AccessCacheCommand("UP");
        AccessCacheCommand accessCacheCommandTwo = new AccessCacheCommand("DOWN");

        // same object -> returns true
        assertEquals(accessCacheCommandOne, accessCacheCommandOne);

        // same values -> returns true
        AccessCacheCommand accessCacheCommandOneCopy = new AccessCacheCommand("UP");
        assertEquals(accessCacheCommandOne, accessCacheCommandOneCopy);

        // different types -> returns false
        Command otherCommand = new DeleteCommand();
        assertNotEquals(otherCommand, accessCacheCommandOne);

        // null -> returns false
        assertNotEquals(null, accessCacheCommandOne);

        // different key -> returns false
        assertNotEquals(accessCacheCommandOne, accessCacheCommandTwo);
    }
}
