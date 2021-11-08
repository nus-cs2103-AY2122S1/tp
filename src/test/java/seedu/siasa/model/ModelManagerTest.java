package seedu.siasa.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.siasa.testutil.Assert.assertThrows;
import static seedu.siasa.testutil.TypicalContacts.ALICE;
import static seedu.siasa.testutil.TypicalPolicies.FULL_LIFE;
import static seedu.siasa.testutil.TypicalSiasa.getTypicalSiasa;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.siasa.commons.core.GuiSettings;
import seedu.siasa.model.contact.NameContainsKeywordsPredicate;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Siasa(), new Siasa(modelManager.getSiasa()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        //TODO: Change the path
        userPrefs.setSiasaFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        //TODO: Change the path
        userPrefs.setSiasaFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setSiasaFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setSiasaFilePath(null));
    }

    @Test
    public void setSiasaFilePath_validPath_setsSiasaFilePath() {
        //TODO: Change the path
        Path path = Paths.get("address/book/file/path");
        modelManager.setSiasaFilePath(path);
        assertEquals(path, modelManager.getSiasaFilePath());
    }

    @Test
    public void hasContact_nullContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasContact(null));
    }

    @Test
    public void hasContact_contactNotInSiasa_returnsFalse() {
        assertFalse(modelManager.hasContact(ALICE));
    }

    @Test
    public void hasContact_contactInSiasa_returnsTrue() {
        modelManager.addContact(ALICE);
        assertTrue(modelManager.hasContact(ALICE));
    }

    @Test
    public void getFilteredContactList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredContactList().remove(0));
    }

    @Test
    public void hasPolicy_nullPolicy_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPolicy(null));
    }

    @Test
    public void hasPolicy_policyNotInSiasa_returnsFalse() {
        assertFalse(modelManager.hasPolicy(FULL_LIFE));
    }

    @Test
    public void hasPolicy_policyInSiasa_returnsTrue() {
        modelManager.addPolicy(FULL_LIFE);
        assertTrue(modelManager.hasPolicy(FULL_LIFE));
    }

    @Test
    public void getFilteredPolicyList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPolicyList().remove(0));
    }

    @Test
    public void equals() {
        Siasa siasa = getTypicalSiasa();
        Siasa differentSiasa = new Siasa();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(siasa, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(siasa, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different siasa -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentSiasa, userPrefs)));

        // different filteredPersonList -> returns false
        String[] personKeywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredContactList(new NameContainsKeywordsPredicate(Arrays.asList(personKeywords)));
        assertFalse(modelManager.equals(new ModelManager(siasa, userPrefs)));

        // different filteredPolicyList -> returns false
        modelManager.updateFilteredPolicyList(p -> p.equals(FULL_LIFE));
        assertFalse(modelManager.equals(new ModelManager(siasa, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.removeAllFilters();

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setSiasaFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(siasa, differentUserPrefs)));
    }
}
