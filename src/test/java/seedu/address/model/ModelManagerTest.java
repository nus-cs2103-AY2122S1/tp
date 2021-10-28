package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.BENSON_NO_BIRTHDAY;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.HANNAH_NO_BIRTHDAY;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
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
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void setPrefixStore_modifySet_differentSet() {
        Set<Prefix> prefixSet = new HashSet<>();
        prefixSet.add(PREFIX_NAME);
        modelManager.setPrefixes(prefixSet);

        prefixSet.add(PREFIX_PHONE);
        assertNotEquals(modelManager.getPrefixes(), prefixSet);
    }

    @Test
    public void getPrefixStore_modifySet_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getPrefixes().add(PREFIX_PHONE));
    }

    public void birthdayReminderList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getBirthdayReminderList().remove(0));
    }

    @Test
    public void addPerson_personWithoutBirthday_birthdayReminderListRemainsUnchanged() {
        modelManager.addPerson(BENSON_NO_BIRTHDAY);
        assertTrue(modelManager.getBirthdayReminderList().isEmpty());
    }

    @Test
    public void addPerson_personWithBirthday_birthdayReminderListRemainSorted() {
        modelManager.addPerson(CARL); // Earliest birthday
        modelManager.addPerson(ALICE);
        modelManager.addPerson(BENSON); // Latest birthday
        // Person sorted by birthday in this order: CARL -- ALICE -- BENSON.
        // List starts from x read rightwards.
        MonthDay currentMonthDay = MonthDay.now();
        ArrayList<Person> expectedBirthdayReminderList = new ArrayList<>(3);
        MonthDay aliceMonthDay = MonthDay.from(ALICE.getBirthday().get().birthdate);
        MonthDay bensonMonthDay = MonthDay.from(BENSON.getBirthday().get().birthdate);
        MonthDay carlMonthDay = MonthDay.from(CARL.getBirthday().get().birthdate);

        if (currentMonthDay.isBefore(carlMonthDay)
                || (currentMonthDay.isAfter(bensonMonthDay)
                        && !currentMonthDay.equals(bensonMonthDay))) {
            // x CARL -- ALICE -- BENSON x
            expectedBirthdayReminderList.add(CARL);
            expectedBirthdayReminderList.add(ALICE);
            expectedBirthdayReminderList.add(BENSON);
        } else if (currentMonthDay.isBefore(aliceMonthDay)) {
            // CARL -x- ALICE -- BENSON
            expectedBirthdayReminderList.add(ALICE);
            expectedBirthdayReminderList.add(BENSON);
            expectedBirthdayReminderList.add(CARL);
        } else {
            // CARL -- ALICE -x- BENSON
            expectedBirthdayReminderList.add(BENSON);
            expectedBirthdayReminderList.add(CARL);
            expectedBirthdayReminderList.add(ALICE);
        }
        assertEquals(expectedBirthdayReminderList.get(0), modelManager.getBirthdayReminderList().get(0));
        assertEquals(expectedBirthdayReminderList.get(1), modelManager.getBirthdayReminderList().get(1));
        assertEquals(expectedBirthdayReminderList.get(2), modelManager.getBirthdayReminderList().get(2));
    }

    @Test
    public void setPerson_personWithNoBirthdayToPersonWithNoBirthday_birthdayReminderListUnchanged() {
        modelManager.addPerson(ALICE);
        modelManager.addPerson(BENSON_NO_BIRTHDAY);

        // Only ALICE in birthday reminder list
        assertEquals(modelManager.getBirthdayReminderList().size(), 1);
        assertEquals(ALICE, modelManager.getBirthdayReminderList().get(0));

        modelManager.setPerson(BENSON_NO_BIRTHDAY, HANNAH_NO_BIRTHDAY);

        // Only ALICE and HANNAH in filtered person list
        assertEquals(modelManager.getFilteredPersonList().size(), 2);
        assertTrue(modelManager.getFilteredPersonList().contains(ALICE));
        assertTrue(modelManager.getFilteredPersonList().contains(HANNAH_NO_BIRTHDAY));

        // Only ALICE in birthday reminder list
        assertEquals(modelManager.getBirthdayReminderList().size(), 1);
        assertEquals(ALICE, modelManager.getBirthdayReminderList().get(0));
    }

    @Test
    public void setPerson_modifyPersonDetails_birthdayReminderListReplacesTargetWithEditedPerson() {
        modelManager.addPerson(ALICE);
        // Only ALICE in birthday reminder list
        assertEquals(modelManager.getBirthdayReminderList().size(), 1);
        assertEquals(ALICE, modelManager.getBirthdayReminderList().get(0));

        modelManager.setPerson(ALICE, BENSON);
        // Only BENSON in birthday reminder list
        assertEquals(modelManager.getBirthdayReminderList().size(), 1);
        assertEquals(BENSON, modelManager.getBirthdayReminderList().get(0));
    }

    @Test
    public void setPerson_initiallyNoBirthdayToEditedWithBirthday_birthdayReminderListIncludesEditedPerson() {
        modelManager.addPerson(BENSON_NO_BIRTHDAY);

        // Birthday reminder list is empty
        assertTrue(modelManager.getBirthdayReminderList().isEmpty());

        modelManager.setPerson(BENSON_NO_BIRTHDAY, BENSON);
        // BENSON added to birthday reminder list
        assertEquals(modelManager.getBirthdayReminderList().size(), 1);
        assertEquals(BENSON, modelManager.getBirthdayReminderList().get(0));
    }

    @Test
    public void deletePerson_personWithBirthday_personRemovedFromBirthdayReminderList() {
        modelManager.addPerson(BENSON);

        // Benson added to birthday reminder list
        assertEquals(BENSON, modelManager.getBirthdayReminderList().get(0));

        modelManager.deletePerson(BENSON);
        assertTrue(modelManager.getBirthdayReminderList().isEmpty());
    }

    @Test
    public void deletePerson_personWithNoBirthday_birthdayReminderListUnchanged() {
        modelManager.addPerson(ALICE);
        modelManager.addPerson(BENSON_NO_BIRTHDAY);

        // ALICE is the only person in the birthday reminder list
        assertFalse(modelManager.getBirthdayReminderList().isEmpty());
        assertEquals(ALICE, modelManager.getBirthdayReminderList().get(0));

        modelManager.deletePerson(BENSON_NO_BIRTHDAY);

        // ALICE remains in birthday reminder list
        assertFalse(modelManager.getBirthdayReminderList().isEmpty());
        assertEquals(ALICE, modelManager.getBirthdayReminderList().get(0));
    }

    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
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
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // different prefixStore -> return false
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        Set<Prefix> prefixSet = Set.of(PREFIX_NAME);
        modelManager.setPrefixes(prefixSet);
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        modelManager.setPrefixes(Set.of());

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));
    }
}
