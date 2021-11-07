package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventNameEqualKeywordPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameEqualKeywordPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class AddPersonToEventCommandTest {

    private final EventNameEqualKeywordPredicate eventPredicate =
            new EventNameEqualKeywordPredicate(new EventName("Dinner Event"));
    private final NameEqualKeywordPredicate personPredicate =
            new NameEqualKeywordPredicate(new Name("Alice Pauline"));

    @Test
    public void constructor_nullPersonNullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPersonToEventCommand(null, null));
    }

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
            -> new AddPersonToEventCommand(null, eventPredicate));
    }

    @Test
    public void constructor_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
            -> new AddPersonToEventCommand(personPredicate, null));
    }

    @Test
    public void execute_personAcceptedByEvent_addSuccessful() throws Exception {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        Person validPerson = new PersonBuilder().build();
        EventStub validEvent = new EventStub(new EventName("Very nice event"));

        model.addPerson(validPerson);
        model.addEvent(validEvent);

        AddPersonToEventCommand command = new AddPersonToEventCommand(
                new NameEqualKeywordPredicate(validPerson.getName()),
                new EventNameEqualKeywordPredicate(validEvent.getEventName()));

        CommandResult commandResult = command.execute(model);

        assertEquals(String.format(AddPersonToEventCommand.MESSAGE_SUCCESS,
                validPerson.getName(), validEvent.getEventName()),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), validEvent.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        Person validPerson = new PersonBuilder().build();
        EventStub validEvent = new EventStub(new EventName("Very nice event"));
        validEvent.addPerson(validPerson);

        model.addPerson(validPerson);
        model.addEvent(validEvent);

        AddPersonToEventCommand command = new AddPersonToEventCommand(
                new NameEqualKeywordPredicate(validPerson.getName()),
                new EventNameEqualKeywordPredicate(validEvent.getEventName()));

        assertThrows(CommandException.class, AddPersonToEventCommand.MESSAGE_DUPLICATE_PERSON, ()
            -> command.execute(model));
    }

    private class EventStub extends Event {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        public EventStub(EventName eventName) {
            super(eventName);
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }
    }

    @Test
    public void equals() {
        NameEqualKeywordPredicate alice = new NameEqualKeywordPredicate(new Name("Alice"));
        NameEqualKeywordPredicate bob = new NameEqualKeywordPredicate(new Name("Bob"));
        EventNameEqualKeywordPredicate lunch = new EventNameEqualKeywordPredicate(new EventName("Lunch"));
        EventNameEqualKeywordPredicate dinner = new EventNameEqualKeywordPredicate(new EventName("Dinner"));
        AddPersonToEventCommand aliceLunch = new AddPersonToEventCommand(alice, lunch);
        AddPersonToEventCommand bobLunch = new AddPersonToEventCommand(bob, lunch);
        AddPersonToEventCommand aliceDinner = new AddPersonToEventCommand(alice, dinner);
        AddPersonToEventCommand bobDinner = new AddPersonToEventCommand(bob, dinner);

        // equivalence partitions testing
        // same object -> returns true
        assertTrue(aliceLunch.equals(aliceLunch));

        // same values -> returns true
        AddPersonToEventCommand aliceLunchCopy = new AddPersonToEventCommand(alice, lunch);
        assertTrue(aliceLunch.equals(aliceLunchCopy));

        // different types -> returns false
        assertFalse(aliceLunch.equals(1));

        // null -> returns false
        assertFalse(aliceLunch.equals(null));

        // different event -> returns false
        assertFalse(aliceLunch.equals(bobLunch));
        assertFalse(aliceLunch.equals(aliceDinner));
        assertFalse(aliceLunch.equals(bobDinner));
    }
}
