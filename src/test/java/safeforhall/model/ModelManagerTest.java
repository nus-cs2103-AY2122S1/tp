package safeforhall.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static safeforhall.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static safeforhall.testutil.Assert.assertThrows;
import static safeforhall.testutil.TypicalEvents.BASKETBALL;
import static safeforhall.testutil.TypicalEvents.VOLLEYBALL;
import static safeforhall.testutil.TypicalPersons.ALICE;
import static safeforhall.testutil.TypicalPersons.BENSON;
import static safeforhall.testutil.TypicalPersons.CARL;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import javafx.collections.transformation.FilteredList;
import safeforhall.commons.core.GuiSettings;
import safeforhall.logic.commands.exceptions.CommandException;
import safeforhall.model.event.Event;
import safeforhall.model.event.EventName;
import safeforhall.model.event.ResidentList;
import safeforhall.model.person.NameContainsKeywordsPredicate;
import safeforhall.model.person.Person;
import safeforhall.testutil.AddressBookBuilder;
import safeforhall.testutil.EventBuilder;

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
    public void execute_validViewSinglePersonCommand_success() throws Exception {
        AddressBook addressBook = new AddressBook();
        FilteredList<Person> singlePerson = new FilteredList<>(addressBook.getPersonList());
        singlePerson.setPredicate(ALICE::equals);
        modelManager.setSinglePerson(ALICE);
        assertEquals(modelManager.getSinglePerson(), singlePerson);
    }

    @Test
    public void execute_validViewSingleEventCommand_success() throws Exception {
        AddressBook addressBook = new AddressBook();
        FilteredList<Event> singleEvent = new FilteredList<>(addressBook.getEventList());
        singleEvent.setPredicate(BASKETBALL::equals);
        modelManager.setSingleEvent(BASKETBALL);
        assertEquals(modelManager.getSingleEvent(), singleEvent);
    }

    @Test
    public void execute_validViewNoPersonCommand_success() throws Exception {
        modelManager.setNoPerson();
        AddressBook addressBook = new AddressBook();
        FilteredList<Person> noPerson = new FilteredList<>(addressBook.getPersonList());
        noPerson.setPredicate(person -> false);
        assertEquals(modelManager.getSinglePerson(), noPerson);
    }

    @Test
    public void execute_validViewNoEventCommand_success() throws Exception {
        modelManager.setNoEvent();
        AddressBook addressBook = new AddressBook();
        FilteredList<Event> noEvent = new FilteredList<>(addressBook.getEventList());
        noEvent.setPredicate(event -> false);
        assertEquals(modelManager.getSingleEvent(), noEvent);
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

    @Test
    public void toPersonListTest() throws CommandException {
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE)
                .withPerson(BENSON).withPerson(CARL).build();
        UserPrefs userPrefs = new UserPrefs();
        modelManager = new ModelManager(addressBook, userPrefs);

        ArrayList<Person> expected = new ArrayList<>();
        expected.add(ALICE);
        expected.add(BENSON);

        ResidentList listWithName = new ResidentList("Alice Pauline, Benson Meier");
        ArrayList<Person> personListWithName = modelManager.toPersonList(listWithName);
        assertEquals(personListWithName, expected);

        ResidentList listWithRoom = new ResidentList("A100, A101");
        ArrayList<Person> personListWithRoom = modelManager.toPersonList(listWithRoom);
        assertEquals(personListWithRoom, expected);
    }

    @Test
    public void toPersonListTest_fails() throws CommandException {
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE)
                .withPerson(BENSON).withPerson(CARL).build();
        UserPrefs userPrefs = new UserPrefs();
        modelManager = new ModelManager(addressBook, userPrefs);

        ArrayList<Person> expected = new ArrayList<>();
        expected.add(ALICE);
        expected.add(BENSON);

        ResidentList listWithName = new ResidentList("Alice Pauline, Benson Meier, Jackson");
        assertThrows(CommandException.class, () -> modelManager.toPersonList(listWithName));
    }

    @Test
    public void getCurrentResidentsTest() throws CommandException {
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE)
                .withPerson(BENSON).withPerson(CARL).build();
        UserPrefs userPrefs = new UserPrefs();
        modelManager = new ModelManager(addressBook, userPrefs);

        ArrayList<Person> expected = new ArrayList<>();
        expected.add(ALICE);
        expected.add(BENSON);

        ResidentList list = new ResidentList("Alice Pauline, Benson Meier",
                ALICE.toString() + ", " + BENSON.toString());
        ArrayList<Person> personList = modelManager.getCurrentEventResidents(list);
        assertEquals(personList, expected);
    }

    @Test
    public void getCurrentResidentsTest_fails() throws CommandException {
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE)
                .withPerson(BENSON).withPerson(CARL).build();
        UserPrefs userPrefs = new UserPrefs();
        modelManager = new ModelManager(addressBook, userPrefs);

        ArrayList<Person> expected = new ArrayList<>();
        expected.add(ALICE);
        expected.add(BENSON);

        ResidentList list = new ResidentList("Alice Pauline, Benson Meier, Jackson");
        assertThrows(CommandException.class, () -> modelManager.getCurrentEventResidents(list));
    }

    @Test
    public void getEventSuccess() throws CommandException {
        AddressBook addressBook = new AddressBookBuilder().withEvent(BASKETBALL).withEvent(VOLLEYBALL).build();
        UserPrefs userPrefs = new UserPrefs();
        modelManager = new ModelManager(addressBook, userPrefs);

        EventName basketballEvent = new EventName("basketball");
        Event foundBasketball = modelManager.getEvent(basketballEvent);
        assertEquals(foundBasketball, BASKETBALL);

        EventName volleyballEvent = new EventName("volleyball");
        Event foundVolleyball = modelManager.getEvent(volleyballEvent);
        assertEquals(foundVolleyball, VOLLEYBALL);
    }

    @Test
    public void getEventFailure() {
        AddressBook addressBook = new AddressBookBuilder().withEvent(BASKETBALL).build();
        UserPrefs userPrefs = new UserPrefs();
        modelManager = new ModelManager(addressBook, userPrefs);

        EventName volleyballEvent = new EventName("volleyball");
        assertThrows(CommandException.class, () -> modelManager.getEvent(volleyballEvent));
    }

    @Test
    public void getInvalidResident_invalidFound() throws CommandException {
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE)
                .withPerson(BENSON).withPerson(CARL).build();
        UserPrefs userPrefs = new UserPrefs();
        modelManager = new ModelManager(addressBook, userPrefs);

        EventBuilder eventBuilder = new EventBuilder();
        Event event = eventBuilder.withResidentList(ALICE.getName().toString(), ALICE.toString()).build();
        String result = modelManager.getInvalidResident(event);
        assertEquals(result, "");
    }

    @Test
    public void getInvalidResident_invalidNotFound() throws CommandException {
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE)
                .withPerson(BENSON).withPerson(CARL).build();
        UserPrefs userPrefs = new UserPrefs();
        modelManager = new ModelManager(addressBook, userPrefs);

        EventBuilder eventBuilder = new EventBuilder();
        Event event = eventBuilder.withResidentList("John", "John; "
                + "Room: C111; Phone: 91031280; "
                + "Email: lijohn@example.com; Vaccinated: T; "
                + "Faculty: SDE; Last Fet Date: 02-10-2021; "
                + "Last Collection Date: 01-10-2021").build();
        String result = modelManager.getInvalidResident(event);
        assertEquals(result, "John");
    }
}
