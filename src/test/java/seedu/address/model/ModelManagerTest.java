package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Name;
import seedu.address.model.person.Period;
import seedu.address.model.person.Person;
import seedu.address.model.person.Slot;
import seedu.address.model.person.exceptions.DuplicateShiftException;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.PersonBuilder;

public class ModelManagerTest {
    private static final LocalDate START_DATE = LocalDate.of(1, 1, 1);
    private static final LocalDate END_DATE = START_DATE.plusDays(7);
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
    public void getPersonTest() {
        Person alice = new PersonBuilder().withName("Alice Pauline")
                .withEmail("alice@example.com").withPhone("94351253").withRoles("floor").withSalary("1000000")
                .withStatus("fulltime").withTags("friends").build();

        modelManager.addPerson(alice);
        assertEquals(alice, modelManager.findPersonByName(alice.getName()));
        assertNull(modelManager.findPersonByName(new Name("random name")));
    }

    @Test
    public void addShift_throwsDuplicateShiftException() {
        Person alice = new PersonBuilder().withName("Alice Pauline")
                .withEmail("alice@example.com").withPhone("94351253").withRoles("floor").withSalary("1000000")
                .withStatus("fulltime").withTags("friends").build();
        modelManager.addPerson(alice);
        modelManager.addShift(alice, DayOfWeek.MONDAY, Slot.AFTERNOON, START_DATE, END_DATE);
        assertThrows(DuplicateShiftException.class, () ->
                modelManager.addShift(alice, DayOfWeek.MONDAY, Slot.AFTERNOON, START_DATE, END_DATE));
    }

    @Test
    public void addShift_success() {
        Person alice = new PersonBuilder().withName("Alice Pauline")
                .withEmail("alice@example.com").withPhone("94351253").withRoles("floor").withSalary("1000000")
                .withStatus("fulltime").withTags("friends").build();
        modelManager.addPerson(alice);
        modelManager.addShift(alice, DayOfWeek.MONDAY, Slot.AFTERNOON, START_DATE, END_DATE);
        assertTrue(alice.getSchedule().isWorking(DayOfWeek.MONDAY, Slot.AFTERNOON,
                new Period(START_DATE, END_DATE)));
    }

    @Test
    public void deleteShift_success() {
        LocalDate testDate = END_DATE.plusDays(7);
        Person alice = new PersonBuilder().withName("Alice Pauline")
                .withEmail("alice@example.com").withPhone("94351253").withRoles("floor").withSalary("1000000")
                .withStatus("fulltime").withTags("friends").build();
        modelManager.addPerson(alice);
        modelManager.addShift(alice, DayOfWeek.MONDAY, Slot.AFTERNOON, START_DATE, END_DATE);
        modelManager.deleteShift(alice, DayOfWeek.MONDAY, Slot.AFTERNOON, START_DATE, END_DATE);
        assertFalse(alice.getSchedule().isWorking(DayOfWeek.MONDAY, Slot.AFTERNOON, new Period(START_DATE, END_DATE)));

        modelManager.addShift(alice, DayOfWeek.MONDAY, Slot.AFTERNOON, START_DATE, testDate);
        modelManager.deleteShift(alice, DayOfWeek.MONDAY, Slot.AFTERNOON, START_DATE, END_DATE);
        assertFalse(alice.getSchedule().isWorking(DayOfWeek.MONDAY, Slot.AFTERNOON, new Period(START_DATE, END_DATE)));
        assertTrue(alice.getSchedule().isWorking(DayOfWeek.MONDAY, Slot.AFTERNOON, new Period(END_DATE.plusDays(1),
                testDate)));
        assertTrue(alice.getSchedule().isWorking(DayOfWeek.MONDAY, Slot.AFTERNOON, new Period(END_DATE,
                testDate)));

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

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));
    }
}
