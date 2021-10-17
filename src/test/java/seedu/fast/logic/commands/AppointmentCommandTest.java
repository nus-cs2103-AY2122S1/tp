package seedu.fast.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_APPOINTMENT_AMY;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_APPOINTMENT_BOB;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_APPOINTMENT_TIME_AMY;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_APPOINTMENT_TIME_BOB;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_APPOINTMENT_VENUE_AMY;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_APPOINTMENT_VENUE_BOB;
import static seedu.fast.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.fast.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.fast.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.fast.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.fast.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.fast.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.fast.testutil.TypicalPersons.getTypicalFast;

import org.junit.jupiter.api.Test;

import seedu.fast.commons.core.Messages;
import seedu.fast.commons.core.index.Index;
import seedu.fast.model.Fast;
import seedu.fast.model.Model;
import seedu.fast.model.ModelManager;
import seedu.fast.model.UserPrefs;
import seedu.fast.model.person.Appointment;
import seedu.fast.model.person.Person;
import seedu.fast.testutil.PersonBuilder;

public class AppointmentCommandTest {
    private final Model model = new ModelManager(getTypicalFast(), new UserPrefs());

    @Test
    public void equals() {
        final AppointmentCommand standardCommand = new AppointmentCommand(INDEX_FIRST_PERSON,
                new Appointment(VALID_APPOINTMENT_AMY, VALID_APPOINTMENT_TIME_AMY, VALID_APPOINTMENT_VENUE_AMY));

        // same values -> returns true
        AppointmentCommand commandWithSameValues = new AppointmentCommand(INDEX_FIRST_PERSON,
                new Appointment(VALID_APPOINTMENT_AMY, VALID_APPOINTMENT_TIME_AMY, VALID_APPOINTMENT_VENUE_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AppointmentCommand(INDEX_SECOND_PERSON,
                new Appointment(VALID_APPOINTMENT_AMY, VALID_APPOINTMENT_TIME_AMY, VALID_APPOINTMENT_VENUE_AMY))));

        // different appointment -> returns false
        assertFalse(standardCommand.equals(new AppointmentCommand(INDEX_FIRST_PERSON,
                new Appointment(VALID_APPOINTMENT_BOB, VALID_APPOINTMENT_TIME_BOB, VALID_APPOINTMENT_VENUE_BOB))));

        // different appointment date -> return false
        assertFalse(standardCommand.equals(new AppointmentCommand(INDEX_FIRST_PERSON,
                new Appointment(VALID_APPOINTMENT_BOB, VALID_APPOINTMENT_TIME_AMY, VALID_APPOINTMENT_VENUE_AMY))));

        //different appointment time -> return false
        assertFalse(standardCommand.equals(new AppointmentCommand(INDEX_FIRST_PERSON,
                new Appointment(VALID_APPOINTMENT_AMY, VALID_APPOINTMENT_TIME_BOB, VALID_APPOINTMENT_VENUE_AMY))));

        // different appointment venue -> return false
        assertFalse(standardCommand.equals(new AppointmentCommand(INDEX_FIRST_PERSON,
                new Appointment(VALID_APPOINTMENT_AMY, VALID_APPOINTMENT_TIME_AMY, VALID_APPOINTMENT_VENUE_BOB))));
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        AppointmentCommand apptCommand = new AppointmentCommand(outOfBoundIndex,
                new Appointment(VALID_APPOINTMENT_BOB, VALID_APPOINTMENT_TIME_BOB, VALID_APPOINTMENT_VENUE_BOB));

        assertCommandFailure(apptCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    // without time, without venue
    @Test
    public void execute_addAppointmentWithoutAdditionUnfilteredList_success() {
        Person thirdPerson = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(thirdPerson)
                .withAppointment(VALID_APPOINTMENT_BOB, VALID_APPOINTMENT_TIME_AMY, VALID_APPOINTMENT_VENUE_AMY)
                .build();
        Appointment editedAppt = editedPerson.getAppointment();

        AppointmentCommand appointmentCommand = new AppointmentCommand(INDEX_THIRD_PERSON,
                new Appointment(editedAppt.getDate(), editedAppt.getTimeFormatted(), editedAppt.getVenue()));

        String expectedMessage = String.format(AppointmentCommand.MESSAGE_ADD_APPOINTMENT_SUCCESS,
                editedPerson.getName().fullName, editedAppt.getDate(), editedAppt.getTimeFormatted(),
                editedAppt.getVenue());

        Model expectedModel = new ModelManager(new Fast(model.getFast()), new UserPrefs());
        expectedModel.setPerson(thirdPerson, editedPerson);

        assertCommandSuccess(appointmentCommand, model, expectedMessage, expectedModel);
    }

    // with time, with venue
    @Test
    public void execute_addAppointmentWithAdditionUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson)
                .withAppointment(VALID_APPOINTMENT_BOB, VALID_APPOINTMENT_TIME_BOB, VALID_APPOINTMENT_VENUE_BOB)
                .build();
        Appointment editedAppt = editedPerson.getAppointment();

        AppointmentCommand appointmentCommand = new AppointmentCommand(INDEX_FIRST_PERSON,
                new Appointment(editedAppt.getDate(), editedAppt.getTime(), editedAppt.getVenue()));

        String expectedMessage = String.format(AppointmentCommand.MESSAGE_ADD_APPOINTMENT_SUCCESS,
                editedPerson.getName().fullName, editedAppt.getDate(), editedAppt.getTimeFormatted(),
                editedAppt.getVenue());

        Model expectedModel = new ModelManager(new Fast(model.getFast()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(appointmentCommand, model, expectedMessage, expectedModel);
    }


    // with time, without venue
    @Test
    public void execute_addAppointmentWithTimeUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson)
                .withAppointment(VALID_APPOINTMENT_BOB, VALID_APPOINTMENT_TIME_BOB, VALID_APPOINTMENT_VENUE_AMY)
                .build();
        Appointment editedAppt = editedPerson.getAppointment();

        AppointmentCommand appointmentCommand = new AppointmentCommand(INDEX_FIRST_PERSON,
                new Appointment(editedAppt.getDate(), editedAppt.getTime(), editedAppt.getVenue()));

        String expectedMessage = String.format(AppointmentCommand.MESSAGE_ADD_APPOINTMENT_SUCCESS,
                editedPerson.getName().fullName, editedAppt.getDate(), editedAppt.getTimeFormatted(),
                editedAppt.getVenue());

        Model expectedModel = new ModelManager(new Fast(model.getFast()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(appointmentCommand, model, expectedMessage, expectedModel);
    }

    //without time, with venue
    @Test
    public void execute_addAppointmentWithVenueUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson)
                .withAppointment(VALID_APPOINTMENT_BOB, VALID_APPOINTMENT_TIME_AMY, VALID_APPOINTMENT_VENUE_BOB)
                .build();
        Appointment editedAppt = editedPerson.getAppointment();

        AppointmentCommand appointmentCommand = new AppointmentCommand(INDEX_FIRST_PERSON,
                new Appointment(editedAppt.getDate(), editedAppt.getTimeFormatted(), editedAppt.getVenue()));

        String expectedMessage = String.format(AppointmentCommand.MESSAGE_ADD_APPOINTMENT_SUCCESS,
                editedPerson.getName().fullName, editedAppt.getDate(), editedAppt.getTimeFormatted(),
                editedAppt.getVenue());

        Model expectedModel = new ModelManager(new Fast(model.getFast()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(appointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addAppointmentWithExistingUnfilteredList_failure() {
        Person secondPerson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(secondPerson)
                .withAppointment(VALID_APPOINTMENT_BOB, VALID_APPOINTMENT_TIME_BOB, VALID_APPOINTMENT_VENUE_BOB)
                .build();
        Appointment editedAppt = editedPerson.getAppointment();

        AppointmentCommand appointmentCommand = new AppointmentCommand(INDEX_SECOND_PERSON,
                new Appointment(editedAppt.getDate(), editedAppt.getTime(), editedAppt.getVenue()));

        String expectedMessage = AppointmentCommand.MESSAGE_ADD_APPOINTMENT_FAILURE;

        assertCommandFailure(appointmentCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of FAST list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFast().getPersonList().size());

        AppointmentCommand apptCommand = new AppointmentCommand(outOfBoundIndex,
                new Appointment(VALID_APPOINTMENT_BOB, VALID_APPOINTMENT_TIME_BOB, VALID_APPOINTMENT_VENUE_BOB));
        assertCommandFailure(apptCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    // without time, without venue
    @Test
    public void execute_addAppointmentWithoutAdditionFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(model.getFilteredPersonList()
                .get(INDEX_FIRST_PERSON.getZeroBased()))
                .withAppointment(VALID_APPOINTMENT_BOB, VALID_APPOINTMENT_TIME_AMY, VALID_APPOINTMENT_VENUE_AMY)
                .build();
        Appointment editedAppt = editedPerson.getAppointment();

        AppointmentCommand appointmentCommand = new AppointmentCommand(INDEX_FIRST_PERSON,
                new Appointment(editedAppt.getDate(), editedAppt.getTimeFormatted(), editedAppt.getVenue()));

        String expectedMessage = String.format(AppointmentCommand.MESSAGE_ADD_APPOINTMENT_SUCCESS,
                editedPerson.getName().fullName, editedAppt.getDate(), editedAppt.getTimeFormatted(),
                editedAppt.getVenue());

        Model expectedModel = new ModelManager(new Fast(model.getFast()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(appointmentCommand, model, expectedMessage, expectedModel);
    }

    // with time, with venue
    @Test
    public void execute_addAppointmentWithAdditionFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(model.getFilteredPersonList()
                .get(INDEX_FIRST_PERSON.getZeroBased()))
                .withAppointment(VALID_APPOINTMENT_BOB, VALID_APPOINTMENT_TIME_BOB, VALID_APPOINTMENT_VENUE_BOB)
                .build();
        Appointment editedAppt = editedPerson.getAppointment();

        AppointmentCommand appointmentCommand = new AppointmentCommand(INDEX_FIRST_PERSON,
                new Appointment(editedAppt.getDate(), editedAppt.getTime(), editedAppt.getVenue()));

        String expectedMessage = String.format(AppointmentCommand.MESSAGE_ADD_APPOINTMENT_SUCCESS,
                editedPerson.getName().fullName, editedAppt.getDate(), editedAppt.getTimeFormatted(),
                editedAppt.getVenue());

        Model expectedModel = new ModelManager(new Fast(model.getFast()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(appointmentCommand, model, expectedMessage, expectedModel);
    }

    // with time, without venue
    @Test
    public void execute_addAppointmentWithTimeFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(model.getFilteredPersonList()
                .get(INDEX_FIRST_PERSON.getZeroBased()))
                .withAppointment(VALID_APPOINTMENT_BOB, VALID_APPOINTMENT_TIME_BOB, VALID_APPOINTMENT_VENUE_AMY)
                .build();
        Appointment editedAppt = editedPerson.getAppointment();

        AppointmentCommand appointmentCommand = new AppointmentCommand(INDEX_FIRST_PERSON,
                new Appointment(editedAppt.getDate(), editedAppt.getTime(), editedAppt.getVenue()));

        String expectedMessage = String.format(AppointmentCommand.MESSAGE_ADD_APPOINTMENT_SUCCESS,
                editedPerson.getName().fullName, editedAppt.getDate(), editedAppt.getTimeFormatted(),
                editedAppt.getVenue());

        Model expectedModel = new ModelManager(new Fast(model.getFast()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(appointmentCommand, model, expectedMessage, expectedModel);
    }

    // without time, with venue
    @Test
    public void execute_addAppointmentWithVenueFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(model.getFilteredPersonList()
                .get(INDEX_FIRST_PERSON.getZeroBased()))
                .withAppointment(VALID_APPOINTMENT_BOB, VALID_APPOINTMENT_TIME_AMY, VALID_APPOINTMENT_VENUE_BOB)
                .build();
        Appointment editedAppt = editedPerson.getAppointment();

        AppointmentCommand appointmentCommand = new AppointmentCommand(INDEX_FIRST_PERSON,
                new Appointment(editedAppt.getDate(), editedAppt.getTimeFormatted(), editedAppt.getVenue()));

        String expectedMessage = String.format(AppointmentCommand.MESSAGE_ADD_APPOINTMENT_SUCCESS,
                editedPerson.getName().fullName, editedAppt.getDate(), editedAppt.getTimeFormatted(),
                editedAppt.getVenue());

        Model expectedModel = new ModelManager(new Fast(model.getFast()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(appointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addAppointmentWithExistingFilteredList_failure() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Person secondPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(secondPerson)
                .withAppointment(VALID_APPOINTMENT_BOB, VALID_APPOINTMENT_TIME_BOB, VALID_APPOINTMENT_VENUE_BOB)
                .build();
        Appointment editedAppt = editedPerson.getAppointment();

        AppointmentCommand appointmentCommand = new AppointmentCommand(INDEX_FIRST_PERSON,
                new Appointment(editedAppt.getDate(), editedAppt.getTime(), editedAppt.getVenue()));

        String expectedMessage = AppointmentCommand.MESSAGE_ADD_APPOINTMENT_FAILURE;

        assertCommandFailure(appointmentCommand, model, expectedMessage);
    }
}
