package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlySchedule;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Address;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.Appointment;
import seedu.address.model.schedule.TimePeriod;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.PersonBuilder;

public class AddAppCommandTest {

    @Test
    public void constructor_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
            -> new AddAppCommand(null, null, null, null));
    }

    @Test
    public void execute_validAppointmentSinglePerson_returnSuccess() throws Exception {
        ArrayList<Index> indexes = new ArrayList<>();
        indexes.add(Index.fromZeroBased(0));
        ModelStubAcceptingAppointmentAdded modelStub = new ModelStubAcceptingAppointmentAdded();
        Appointment validAppointment = new AppointmentBuilder().build();
        modelStub.addPerson(new PersonBuilder().withName("ALICE").build());
        CommandResult commandResult = new AddAppCommand(
                indexes,
                new Address("vivocity"),
                new TimePeriod(LocalDateTime.of(2021, 1, 1, 10, 0),
                        LocalDateTime.of(2021, 1, 2, 10, 0)),
                "Halloween Sales").execute(modelStub);

        assertEquals(String.format(AddAppCommand.MESSAGE_SUCCESS, validAppointment), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validAppointment), modelStub.appointmentAdded);
    }

    @Test
    public void execute_validAppointmentTwoPerson_returnSuccess() throws Exception {
        ArrayList<Index> indexes = new ArrayList<>();
        indexes.add(Index.fromZeroBased(0));
        indexes.add(Index.fromZeroBased(1));
        ModelStubAcceptingAppointmentAdded modelStub = new ModelStubAcceptingAppointmentAdded();

        Appointment validAppointment = new AppointmentBuilder().addClient("BOB").build();
        modelStub.addPerson(new PersonBuilder().withName("ALICE").build());
        modelStub.addPerson(new PersonBuilder().withName("BOB").build());
        CommandResult commandResult = new AddAppCommand(
                indexes,
                new Address("vivocity"),
                new TimePeriod(LocalDateTime.of(2021, 1, 1, 10, 0),
                        LocalDateTime.of(2021, 1, 2, 10, 0)),
                "Halloween Sales").execute(modelStub);

        assertEquals(String.format(AddAppCommand.MESSAGE_SUCCESS, validAppointment), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validAppointment), modelStub.appointmentAdded);
    }

    @Test
    public void execute_invalidAppointment_returnInvalid() {
        ArrayList<Index> indexes = new ArrayList<>();
        indexes.add(Index.fromZeroBased(2));
        ModelStubAcceptingAppointmentAdded modelStub = new ModelStubAcceptingAppointmentAdded();
        modelStub.addPerson(new PersonBuilder().withName("ALICE").build());
        Command commandResult = new AddAppCommand(
                indexes,
                new Address("vivocity"),
                new TimePeriod(LocalDateTime.of(2021, 1, 1, 10, 0),
                        LocalDateTime.of(2021, 1, 2, 10, 0)),
                "Halloween Sales");

        assertThrows(CommandException.class, ()
            -> commandResult.execute(modelStub));
    }

    @Test
    public void execute_duplicatePerson_returnInvalid() {
        ArrayList<Index> indexes = new ArrayList<>();
        indexes.add(Index.fromZeroBased(0));
        indexes.add(Index.fromZeroBased(0));
        ModelStubAcceptingAppointmentAdded modelStub = new ModelStubAcceptingAppointmentAdded();
        modelStub.addPerson(new PersonBuilder().withName("ALICE").build());
        modelStub.addPerson(new PersonBuilder().withName("BOB").build());
        Command commandResult = new AddAppCommand(
                indexes,
                new Address("vivocity"),
                new TimePeriod(LocalDateTime.of(2021, 1, 1, 10, 0),
                        LocalDateTime.of(2021, 1, 2, 10, 0)),
                "Halloween Sales");

        assertThrows(CommandException.class, ()
            -> commandResult.execute(modelStub));
    }

    @Test
    public void execute_duplicateAppointmentTime_returnInvalid() {
        ArrayList<Index> indexOne = new ArrayList<>();
        indexOne.add(Index.fromZeroBased(0));
        ArrayList<Index> indexTwo = new ArrayList<>();
        indexTwo.add(Index.fromZeroBased(1));
        ModelStubAcceptingAppointmentAdded modelTester = new ModelStubAcceptingAppointmentAdded();
        modelTester.addPerson(new PersonBuilder().withName("ALICE").build());
        modelTester.addPerson(new PersonBuilder().withName("BOB").build());
        Command initialCommand = new AddAppCommand(
                indexOne,
                new Address("vivocity"),
                new TimePeriod(LocalDateTime.of(2021, 1, 1, 10, 0),
                        LocalDateTime.of(2021, 1, 2, 10, 0)),
                "Halloween Sales");
        Command commandResult = new AddAppCommand(
                indexTwo,
                new Address("vivocity"),
                new TimePeriod(LocalDateTime.of(2021, 1, 1, 10, 0),
                        LocalDateTime.of(2021, 1, 2, 10, 0)),
                "Halloween Sales");
        try {
            initialCommand.execute(modelTester);
        } catch (CommandException e) {
            throw new AssertionError("This method should not be called.");
        }

        assertThrows(CommandException.class, ()
            -> commandResult.execute(modelTester));
    }

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
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlySchedule getSchedule() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
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
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAppointment(Appointment a) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAppointment(Appointment a) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Appointment> getFilteredAppointmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortFilteredAppointmentList(String sortBy) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String getRelatedAppointmentsAsString(Person client) {
            return "";
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingAppointmentAdded extends AddAppCommandTest.ModelStub {
        final ObservableList<Appointment> appointmentAdded = FXCollections.observableArrayList();
        final ObservableList<Person> personsAdded = FXCollections.observableArrayList();

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
        public ObservableList<Person> getFilteredPersonList() {
            return personsAdded;
        }

        @Override
        public void addAppointment(Appointment a) {
            requireNonNull(a);
            appointmentAdded.add(a);
        }

        @Override
        public ObservableList<Appointment> getFilteredAppointmentList() {
            return appointmentAdded;
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
