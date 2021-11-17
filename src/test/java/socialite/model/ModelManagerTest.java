package socialite.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import socialite.commons.core.GuiSettings;
import socialite.model.person.ContainsKeywordsPredicate;
import socialite.testutil.Assert;
import socialite.testutil.ContactListBuilder;
import socialite.testutil.TypicalPersons;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        Assertions.assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ContactList(), new ContactList(modelManager.getContactList()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setContactListFilePath(Paths.get("contact/list/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setContactListFilePath(Paths.get("new/contact/list/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setContactListFilePath_nullPath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> modelManager.setContactListFilePath(null));
    }

    @Test
    public void setContactListFilePath_validPath_setsContactListFilePath() {
        Path path = Paths.get("contact/list/file/path");
        modelManager.setContactListFilePath(path);
        assertEquals(path, modelManager.getContactListFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInContactList_returnsFalse() {
        assertFalse(modelManager.hasPerson(TypicalPersons.ALICE));
    }

    @Test
    public void hasPerson_personInContactList_returnsTrue() {
        modelManager.addPerson(TypicalPersons.ALICE);
        assertTrue(modelManager.hasPerson(TypicalPersons.ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void setDeleteProfilePicture_returnsTrue() {
        Path fileToDelete = Paths.get("data", "profilepictures", "dummyFile");
        modelManager.deleteProfilePicture(fileToDelete);
        assertTrue(modelManager.getProfilePictureEditDescriptor().toDelete.equals(fileToDelete));
    }

    @Test
    public void setSaveProfilePicture_returnsTrue() {
        Path fileSource = Paths.get("path", "to", "dummySource");
        String dest = "dummyFileName";
        modelManager.saveProfilePicture(fileSource.toFile(), dest);
        assertTrue(modelManager.getProfilePictureEditDescriptor().dest.equals(dest));
        assertTrue(modelManager.getProfilePictureEditDescriptor().source.equals(fileSource.toFile()));
    }

    @Test
    public void clearSaveProfilePicture_returnsTrue() {
        Path fileSource = Paths.get("path", "to", "dummySource");
        String dest = "dummyFileName";
        modelManager.saveProfilePicture(fileSource.toFile(), dest);
        Path fileToDelete = Paths.get("data", "profilepictures", "dummyFile");
        modelManager.deleteProfilePicture(fileToDelete);
        assertTrue(modelManager.getProfilePictureEditDescriptor().toDelete.equals(fileToDelete));
        assertTrue(modelManager.getProfilePictureEditDescriptor().dest.equals(dest));
        assertTrue(modelManager.getProfilePictureEditDescriptor().source.equals(fileSource.toFile()));
        modelManager.clearProfilePictureModel();
        assertTrue(modelManager.getProfilePictureEditDescriptor().source == null);
        assertTrue(modelManager.getProfilePictureEditDescriptor().dest == null);
        assertTrue(modelManager.getProfilePictureEditDescriptor().toDelete == null);
    }

    @Test
    public void equals() {
        ContactList contactList =
                new ContactListBuilder().withPerson(TypicalPersons.ALICE).withPerson(TypicalPersons.BENSON).build();
        ContactList differentContactList = new ContactList();
        UserPrefs userPrefs = new UserPrefs();
        CommandHistory commandHistory = new CommandHistory();

        // same values -> returns true
        modelManager = new ModelManager(contactList, userPrefs, commandHistory);
        ModelManager modelManagerCopy = new ModelManager(contactList, userPrefs, commandHistory);
        assertEquals(modelManager, modelManagerCopy);

        // same object -> returns true
        assertEquals(modelManager, modelManager);

        // null -> returns false
        assertNotEquals(null, modelManager);

        // different types -> returns false
        assertNotEquals(5, modelManager);

        // different contactList -> returns false
        assertNotEquals(modelManager, new ModelManager(
                differentContactList, userPrefs, commandHistory));

        // different filteredList -> returns false
        String[] keywords = TypicalPersons.ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new ContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(contactList, userPrefs, commandHistory)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setContactListFilePath(Paths.get("differentFilePath"));
        assertNotEquals(modelManager, new ModelManager(
                contactList, differentUserPrefs, commandHistory));
    }
}
