package seedu.address.logic.candidate;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.position.Position.MESSAGE_POSITION_CLOSED;
import static seedu.address.model.position.Position.MESSAGE_POSITION_DOES_NOT_EXIST;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPositions.ADMIN_ASSISTANT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.CommandResult;
import seedu.address.logic.candidate.exceptions.CommandException;
import seedu.address.model.HrManager;
import seedu.address.model.ReadOnlyHrManager;
import seedu.address.model.person.Person;
import seedu.address.model.position.Position;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.PersonBuilder;

public class AddCandidateCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCandidateCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = new AddCandidateCommand(validPerson).execute(modelStub);

        assertEquals(String.format(AddCandidateCommand.MESSAGE_SUCCESS, validPerson),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        AddCandidateCommand addCandidateCommand = new AddCandidateCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class,
                String.format(AddCandidateCommand.MESSAGE_DUPLICATE_PERSON, validPerson.getEmail()), () ->
                addCandidateCommand.execute(modelStub));
    }

    @Test
    public void execute_positionDoesNotExist_throwsCommandException() {
        Person validPerson = new PersonBuilder(ALICE).withPositions("Admin").build();
        ModelStubWithSomePositionsAndBob modelStub = new ModelStubWithSomePositionsAndBob();
        AddCandidateCommand addCandidateCommand = new AddCandidateCommand(validPerson);
        String expectedMessage = String.format(MESSAGE_POSITION_DOES_NOT_EXIST, "Admin");

        assertThrows(CommandException.class, expectedMessage, () ->
                addCandidateCommand.execute(modelStub));
    }

    @Test
    public void execute_positionIsClosed_throwsCommandException() {
        Person validPerson = new PersonBuilder(ALICE).withPositions(ADMIN_ASSISTANT.getTitle().fullTitle).build();
        ModelStubWithSomePositionsAndBob modelStub = new ModelStubWithSomePositionsAndBob();
        AddCandidateCommand addCandidateCommand = new AddCandidateCommand(validPerson);

        String expectedMessage = String.format(MESSAGE_POSITION_CLOSED, ADMIN_ASSISTANT.getTitle().fullTitle);

        assertThrows(CommandException.class, expectedMessage, () ->
                addCandidateCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        AddCandidateCommand addAliceCommand = new AddCandidateCommand(alice);
        AddCandidateCommand addBobCommand = new AddCandidateCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCandidateCommand addAliceCommandCopy = new AddCandidateCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }


    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }
    }

    /**
     * A Model stub that has position ADMIN_ASSISTANT and a person BOB.
     */
    private class ModelStubWithSomePositionsAndBob extends ModelStub {
        private final Set<Position> positions = new HashSet<>();
        private final Set<Person> persons = new HashSet<>();

        ModelStubWithSomePositionsAndBob() {
            persons.add(BOB);
            positions.add(ADMIN_ASSISTANT);
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.persons.contains(person);
        }

        @Override
        public boolean hasPosition(Position position) {
            for (Position p : positions) {
                if (p.isSamePosition(position)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public Position getPositionReference(Position position) {
            return position;
        }

        //For some reason p.getStatus() is always open even when building with closed
        @Override
        public boolean isPositionClosed(Position p) {
            return true;
        }

        @Override
        public void addPerson(Person person) {
            persons.add(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

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

        @Override
        public ReadOnlyHrManager getHrManager() {
            return new HrManager();
        }

        @Override
        public boolean hasPosition(Position position) {
            return true;
        }

        @Override
        public boolean isPositionClosed(Position p) {
            return false;
        }

        @Override
        public Position getPositionReference(Position position) {
            return position;
        }
    }
}
