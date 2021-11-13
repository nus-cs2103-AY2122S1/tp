package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CLIENTS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.BENSON;
import static seedu.address.testutil.TypicalClients.CARL;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.LogicManager;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.model.client.ClientContainsKeywordsPredicate;
import seedu.address.model.client.ClientHasId;
import seedu.address.model.client.ClientId;
import seedu.address.model.tag.Tag;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.ui.ThemeType;

public class ModelManagerTest {

    @TempDir
    public Path temporaryFolder;

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AddressBook(), new AddressBook(modelManager.getAddressBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
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
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAddressBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setAddressBookFilePath(path);
        assertEquals(path, modelManager.getAddressBookFilePath());
    }

    @Test
    public void getTag_nullTagName_throwsNullPoinnterException() {
        assertThrows(NullPointerException.class, () -> modelManager.getTag(null));
    }

    @Test
    public void getTag_validTag_returnsCorrectTag() {
        Tag tagA = new Tag("hi");
        AddressBook addressBook = new AddressBook();
        addressBook.addTag(tagA);
        UserPrefs userPrefs = new UserPrefs();
        modelManager = new ModelManager(addressBook, userPrefs);

        assertEquals(modelManager.getTag("hi"), tagA);
    }

    @Test
    public void getThemeList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getThemeList().remove(0));
    }

    @Test
    public void getFilteredClientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredClientList().remove(0));
    }

    @Test
    public void getFilteredTagList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredTagList().remove(0));
    }

    @Test
    public void test_isClientExistToView() {
        // predicate returns empty list -> false
        AddressBook addressBook = new AddressBookBuilder().withClient(ALICE).withClient(BENSON).build();
        UserPrefs userPrefs = new UserPrefs();
        modelManager = new ModelManager(addressBook, userPrefs);
        modelManager.updateClientToView(new ClientHasId(CARL.getClientId()));
        assertFalse(modelManager.isClientExistToView(CARL.getClientId()));

        // predicate returns 1 client in list -> true
        modelManager.updateClientToView(new ClientHasId(ALICE.getClientId()));
        assertTrue(modelManager.isClientExistToView(ALICE.getClientId()));
    }

    @Test
    public void getClientToView_viewFirstClient_returnsTrue() {
        AddressBook addressBook = new AddressBookBuilder().withClient(ALICE).withClient(BENSON).build();
        UserPrefs userPrefs = new UserPrefs();
        modelManager = new ModelManager(addressBook, userPrefs);
        modelManager.updateClientToView(new ClientHasId(ALICE.getClientId()));
        assertTrue(modelManager.isClientExistToView(ALICE.getClientId()));
        assertEquals(Arrays.asList(ALICE), modelManager.getClientToView());
        assertEquals(ALICE.getName().toString(), modelManager.getNameOfClientToView());
    }

    @Test
    public void getClientToView_viewNonExistingClient_returnsFalse() {
        AddressBook addressBook = new AddressBookBuilder().withClient(ALICE).withClient(BENSON).build();
        UserPrefs userPrefs = new UserPrefs();
        modelManager = new ModelManager(addressBook, userPrefs);
        modelManager.updateClientToView(new ClientHasId(CARL.getClientId()));
        assertFalse(modelManager.isClientExistToView(CARL.getClientId()));
    }

    @Test
    public void getTheme_success() {
        JsonAddressBookStorage addressBookStorage =
            new JsonAddressBookStorage(temporaryFolder.resolve("addressBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        LogicManager expectedLogic = new LogicManager(modelManager, storage);
        assertEquals(modelManager.getTheme(), expectedLogic.getTheme());
    }

    @Test
    public void setTheme_success() {
        ThemeType theme = ThemeType.of("BookTheme").get();
        modelManager.setTheme(theme);
        JsonAddressBookStorage addressBookStorage =
            new JsonAddressBookStorage(temporaryFolder.resolve("addressBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        LogicManager expectedLogic = new LogicManager(modelManager, storage);
        assertEquals(modelManager.getTheme(), expectedLogic.getTheme());
    }

    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withClient(ALICE).withClient(BENSON).build();
        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs)));

        // different filteredList -> returns false
        ArgumentMultimap aMM = ArgumentTokenizer.tokenize(BENSON.getName().fullName);
        modelManager.updateFilteredClientList(new ClientContainsKeywordsPredicate(aMM));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));

        // different clientToView -> returns false
        ClientId clientId = ALICE.getClientId();
        modelManager.updateClientToView(new ClientHasId(clientId));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));
    }
}
