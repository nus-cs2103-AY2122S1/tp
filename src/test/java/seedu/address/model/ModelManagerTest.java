package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEMBERS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.ICEBREAKER;
import static seedu.address.testutil.TypicalEvents.PERFORMANCE;
import static seedu.address.testutil.TypicalMembers.ALICE;
import static seedu.address.testutil.TypicalMembers.BENSON;
import static seedu.address.testutil.TypicalMembers.CARL;
import static seedu.address.testutil.TypicalTasks.PROJECT;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.module.NameContainsKeywordsPredicate;
import seedu.address.model.module.member.Member;
import seedu.address.testutil.AddressBookBuilder;

public class ModelManagerTest {

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
    public void hasMember_nullMember_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasMember(null));
    }

    @Test
    public void hasMember_memberNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasMember(ALICE));

        modelManager.addMember(ALICE);
        modelManager.deleteMember(ALICE);
        assertFalse(modelManager.hasMember(ALICE));
    }

    @Test
    public void hasMember_memberInAddressBook_returnsTrue() {
        modelManager.addMember(ALICE);
        assertTrue(modelManager.hasMember(ALICE));
    }

    @Test
    public void hasEvent_eventNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasEvent(ICEBREAKER));

        modelManager.addEvent(ICEBREAKER);
        modelManager.setCurrentEvent(ICEBREAKER);
        modelManager.deleteEvent(ICEBREAKER);
        assertFalse(modelManager.hasEvent(ICEBREAKER));
    }

    @Test
    public void hasEvent_eventEditedInAddressBook() {
        modelManager.addEvent(ICEBREAKER);
        modelManager.setEvent(ICEBREAKER, PERFORMANCE);
        assertFalse(modelManager.hasEvent(ICEBREAKER));
        assertTrue(modelManager.hasEvent(PERFORMANCE));
    }

    @Test
    public void hasEvent_eventInAddressBook_returnsTrue() {
        modelManager.addEvent(ICEBREAKER);
        assertTrue(modelManager.hasEvent(ICEBREAKER));
    }

    @Test
    public void hasEventMember_eventMemberInAddressBook_returnsTrue() {
        modelManager.addEvent(ICEBREAKER);
        Set<Member> memberSet = new HashSet<>();
        memberSet.add(ALICE);
        modelManager.addEventMembers(ICEBREAKER, memberSet);
        assertTrue(modelManager.hasEvent(ICEBREAKER));
    }

    @Test
    public void hasTask_taskNotInAddressBook_returnsFalse() {
        modelManager.addMember(CARL);
        assertFalse(modelManager.hasTask(CARL, PROJECT));

        modelManager.addTask(CARL, PROJECT);
        modelManager.deleteTask(PROJECT);
        assertFalse(modelManager.hasTask(CARL, PROJECT));
    }

    @Test
    public void hasTask_taskInAddressBook_returnsTrue() {
        modelManager.addMember(ALICE);
        assertTrue(modelManager.hasTask(ALICE, PROJECT));
    }

    @Test
    public void getFilteredMemberList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredMemberList().remove(0));
    }

    @Test
    public void getFilteredTaskList_withoutSelectedMember_returnsEmptyList() {
        assertTrue(modelManager.getFilteredTaskList().isEmpty());
    }

    @Test
    public void getFilteredTaskList_withSelectedMember_returnsTrue() {
        modelManager.addMember(CARL);
        assertTrue(modelManager.getFilteredTaskList(CARL).isEmpty());
    }

    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withMember(ALICE).withMember(BENSON).build();
        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // check address book
        ModelManager modelManagerWithAddressBook = new ModelManager(addressBook, userPrefs);
        modelManagerWithAddressBook.setAddressBook(addressBook);
        assertTrue(modelManager.equals(modelManagerWithAddressBook));
        modelManagerWithAddressBook.setAddressBook(differentAddressBook);
        assertFalse(modelManager.equals(modelManagerWithAddressBook));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredMemberList(new NameContainsKeywordsPredicate<Member>(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredMemberList(PREDICATE_SHOW_ALL_MEMBERS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));
    }
}
