package safeforhall.logic.commands.add;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static safeforhall.logic.commands.CommandTestUtil.assertCommandFailure;
import static safeforhall.testutil.Assert.assertThrows;
import static safeforhall.testutil.TypicalEvents.getTypicalAddressBook;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import safeforhall.commons.core.GuiSettings;
import safeforhall.logic.commands.exceptions.CommandException;
import safeforhall.model.AddressBook;
import safeforhall.model.Model;
import safeforhall.model.ModelManager;
import safeforhall.model.ReadOnlyAddressBook;
import safeforhall.model.ReadOnlyUserPrefs;
import safeforhall.model.UserPrefs;
import safeforhall.model.event.Event;
import safeforhall.model.event.EventName;
import safeforhall.model.event.ResidentList;
import safeforhall.model.person.Person;
import safeforhall.testutil.EventBuilder;
import safeforhall.testutil.PersonBuilder;
import safeforhall.testutil.TypicalPersons;

public class AddEventCommandTest {

    @Test
    public void constructor_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddEventCommand(null));
    }

    @Test
    public void execute_duplicateEvent_throwsCommandException() {
        Event validEvent = new EventBuilder().build();
        AddEventCommand addEventCommand = new AddEventCommand(validEvent);
        ModelStub modelStub = new ModelStubWithEvent(validEvent);

        assertThrows(CommandException.class, AddEventCommand.MESSAGE_DUPLICATE_EVENT, ()
            -> addEventCommand.execute(modelStub));
    }

    @Test
    public void execute_invalidResident_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        Event invalidEvent = new EventBuilder().withEventName("invalid")
                .withEventDate("02-10-2021")
                .withEventTime("1240")
                .withVenue("gym")
                .withCapacity("3")
                .withResidentList(TypicalPersons.ALICE.getName().toString()
                                + ", " + TypicalPersons.BOB.getName().toString(),
                        TypicalPersons.ALICE.toString()
                                + ", " + TypicalPersons.BOB.toString())
                .build();
        AddEventCommand addEventCommand = new AddEventCommand(invalidEvent);

        assertCommandFailure(addEventCommand, model, String.format(AddEventCommand.MESSAGE_INVALID_RESIDENT,
                TypicalPersons.ALICE.getName().toString()));
    }

    @Test
    public void execute_exceedCapacityEvent_throwsCommandException() throws Exception {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Person alice = new PersonBuilder().withName(TypicalPersons.ALICE.getName().toString()).build();
        Person bob = new PersonBuilder().withName(TypicalPersons.BOB.getName().toString()).withRoom("E401").build();
        AddPersonCommand addAliceCommand = new AddPersonCommand(alice);
        addAliceCommand.execute(model);
        AddPersonCommand addBobCommand = new AddPersonCommand(bob);
        addBobCommand.execute(model);

        Event invalidEvent = new EventBuilder().withEventName("invalid")
                .withEventDate("02-10-2021")
                .withEventTime("1240")
                .withVenue("gym")
                .withCapacity("1")
                .withResidentList(TypicalPersons.ALICE.getName().toString()
                                + ", " + TypicalPersons.BOB.getName().toString(),
                        TypicalPersons.ALICE.toString()
                                + ", " + TypicalPersons.BOB.toString())
                .build();
        AddEventCommand addEventCommand = new AddEventCommand(invalidEvent);

        assertCommandFailure(addEventCommand, model, AddEventCommand.MESSAGE_EXCEED_CAPACITY);
    }

    @Test
    public void execute_personListToString() {
        Event validEvent = new EventBuilder().build();
        AddEventCommand addEventCommand = new AddEventCommand(validEvent);
        ArrayList<Person> personList = new ArrayList<>();
        personList.add(TypicalPersons.ALICE);
        personList.add(TypicalPersons.ELLE);
        String result = addEventCommand.personListToString(personList);
        String expectedResult = TypicalPersons.ALICE.getName() + ", " + TypicalPersons.ELLE.getName();
        assertEquals(result, expectedResult);
    }

    @Test
    public void equals() {
        Event swimTraining = new EventBuilder().withEventName("Swim Training").build();
        Event basketballTraining = new EventBuilder().withEventName("Basketball Training").build();
        AddEventCommand addSwimTrainingCommand = new AddEventCommand(swimTraining);
        AddEventCommand addBasketballTrainingCommand = new AddEventCommand(basketballTraining);

        // same object -> returns true
        assertTrue(addSwimTrainingCommand.equals(addSwimTrainingCommand));

        // same values -> returns true
        AddEventCommand addSwimTrainingCommandCopy = new AddEventCommand(swimTraining);
        assertTrue(addSwimTrainingCommand.equals(addSwimTrainingCommandCopy));

        // different types -> returns false
        assertFalse(addSwimTrainingCommand.equals(1));

        // null -> returns false
        assertFalse(addSwimTrainingCommand.equals(null));

        // different event -> returns false
        assertFalse(addSwimTrainingCommand.equals(addBasketballTrainingCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEvent(Event target, Event editedEvent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasExactPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ArrayList<Person> toPersonList(ResidentList residentList) throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ArrayList<Person> getCurrentEventResidents(ResidentList residentList) throws CommandException {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public String getInvalidResident(Event event) throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Event getEvent(EventName eventName) throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEvent(Event target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Event> getFilteredEventList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredEventList(Predicate<Event> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getSortedPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Event> getSortedEventList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedPersonList(Comparator<Person> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedEventList(Comparator<Event> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ArrayList<Event> getPersonEvents(Person person, Predicate<Event> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getSinglePerson() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSinglePerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Event> getSingleEvent() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSingleEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setNoPerson() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setNoEvent() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single event.
     */
    private class ModelStubWithEvent extends ModelStub {
        private final Event event;

        ModelStubWithEvent(Event event) {
            requireNonNull(event);
            this.event = event;
        }

        @Override
        public boolean hasEvent(Event event) {
            requireNonNull(event);
            return this.event.isSameEvent(event);
        }
    }

    /**
     * A Model stub that always accept the event being added.
     */
    private class ModelStubAcceptingEventAdded extends ModelStub {
        final ArrayList<Event> eventsAdded = new ArrayList<>();

        @Override
        public boolean hasEvent(Event event) {
            requireNonNull(event);
            return eventsAdded.stream().anyMatch(event::isSameEvent);
        }

        @Override
        public void addEvent(Event event) {
            requireNonNull(event);
            eventsAdded.add(event);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
