package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_GUESTS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.guest.TypicalGuests.ALICE_GUEST;
import static seedu.address.testutil.guest.TypicalGuests.BENSON_GUEST;
import static seedu.address.testutil.guest.TypicalGuests.CARL_GUEST;
import static seedu.address.testutil.guest.TypicalGuests.JEONGYEON_GUEST;
import static seedu.address.testutil.vendor.TypicalVendors.DANIEL_VENDOR;
import static seedu.address.testutil.vendor.TypicalVendors.ELLE_VENDOR;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.guest.Archive;
import seedu.address.model.guest.GuestBook;
import seedu.address.model.guest.PassportNumberContainsKeywordsPredicate;
import seedu.address.model.vendor.VendorBook;
import seedu.address.testutil.guest.ArchiveBuilder;
import seedu.address.testutil.guest.GuestBookBuilder;
import seedu.address.testutil.vendor.VendorBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new GuestBook(), new GuestBook(modelManager.getGuestBook()));
        assertEquals(new VendorBook(), new VendorBook(modelManager.getVendorBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setGuestBookFilePath(Paths.get("PH/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setGuestBookFilePath(Paths.get("new/PH/book/file/path"));
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
    public void setGuestBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuestBookFilePath(null));
    }

    @Test
    public void setGuestBookFilePath_validPath_setsGuestBookFilePath() {
        Path path = Paths.get("PH/book/file/path");
        modelManager.setGuestBookFilePath(path);
        assertEquals(path, modelManager.getGuestBookFilePath());
    }

    @Test
    public void hasGuest_nullGuest_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasGuest(null));
    }

    @Test
    public void hasGuest_guestNotInGuestBook_returnsFalse() {
        assertFalse(modelManager.hasGuest(ALICE_GUEST));
    }

    @Test
    public void hasGuest_guestInAddressBook_returnsTrue() {
        modelManager.addGuest(ALICE_GUEST);
        assertTrue(modelManager.hasGuest(ALICE_GUEST));
    }

    @Test
    public void getFilteredGuestList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredGuestList().remove(0));
    }

    @Test
    public void equals() {
        GuestBook guestBook = new GuestBookBuilder().withGuest(ALICE_GUEST).withGuest(BENSON_GUEST).build();
        GuestBook differentGuestBook = new GuestBook();
        VendorBook vendorBook = new VendorBookBuilder().withVendor(DANIEL_VENDOR).withVendor(ELLE_VENDOR).build();
        VendorBook differentVendorBook = new VendorBook();
        UserPrefs userPrefs = new UserPrefs();
        Archive archive = new ArchiveBuilder().withArchivedGuest(CARL_GUEST).withArchivedGuest(JEONGYEON_GUEST).build();
        Archive differentArchive = new Archive();

        // same values -> returns true
        modelManager = new ModelManager(guestBook, vendorBook, userPrefs, archive);
        ModelManager modelManagerCopy = new ModelManager(guestBook, vendorBook, userPrefs, archive);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different guestBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentGuestBook, vendorBook, userPrefs, archive)));

        // different vendorBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(guestBook, differentVendorBook, userPrefs, archive)));

        // different filteredList -> returns false
        String[] keywords = ALICE_GUEST.getPassportNumber().value.split("\\s+");
        modelManager.updateFilteredGuestList(new PassportNumberContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(guestBook, vendorBook, userPrefs, archive)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredGuestList(PREDICATE_SHOW_ALL_GUESTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setGuestBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(guestBook, vendorBook, differentUserPrefs, archive)));

        // different archive -> returns false
        assertFalse(modelManager.equals(new ModelManager(guestBook, vendorBook, userPrefs, differentArchive)));
    }
}
