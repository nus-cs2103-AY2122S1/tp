package seedu.fast.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_APPOINTMENT_AMY;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_APPOINTMENT_TIME_AMY;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_APPOINTMENT_VENUE_AMY;
import static seedu.fast.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.fast.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.fast.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.fast.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.fast.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.fast.testutil.TypicalIndexes.INDEX_SEVENTH_PERSON;
import static seedu.fast.testutil.TypicalIndexes.INDEX_SIXTH_PERSON;
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

public class UnmarkAppointmentCommandTest {
    private final Model model = new ModelManager(getTypicalFast(), new UserPrefs());

    @Test
    public void equals() {
        final UnmarkAppointmentCommand standardCommand = new UnmarkAppointmentCommand(INDEX_THIRD_PERSON,
                new Appointment(VALID_APPOINTMENT_AMY, VALID_APPOINTMENT_TIME_AMY, VALID_APPOINTMENT_VENUE_AMY));

        // same values -> returns true
        UnmarkAppointmentCommand commandWithSameValues = new UnmarkAppointmentCommand(INDEX_THIRD_PERSON,
                new Appointment(VALID_APPOINTMENT_AMY, VALID_APPOINTMENT_TIME_AMY, VALID_APPOINTMENT_VENUE_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new UnmarkAppointmentCommand(INDEX_SECOND_PERSON,
                new Appointment(VALID_APPOINTMENT_AMY, VALID_APPOINTMENT_TIME_AMY, VALID_APPOINTMENT_VENUE_AMY))));
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        UnmarkAppointmentCommand apptCommand = new UnmarkAppointmentCommand(outOfBoundIndex,
                new Appointment(VALID_APPOINTMENT_AMY, VALID_APPOINTMENT_TIME_AMY, VALID_APPOINTMENT_VENUE_AMY));

        assertCommandFailure(apptCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_unmarkAppointmentUnfilteredList_success() {
        Person seventhPerson = model.getFilteredPersonList().get(INDEX_SEVENTH_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(seventhPerson)
                .withAppointment(VALID_APPOINTMENT_AMY, VALID_APPOINTMENT_TIME_AMY, VALID_APPOINTMENT_VENUE_AMY)
                .withAppointmentCount("2")
                .build();
        Appointment editedAppt = editedPerson.getAppointment();

        UnmarkAppointmentCommand appointmentCommand = new UnmarkAppointmentCommand(INDEX_SEVENTH_PERSON,
                new Appointment(editedAppt.getDate(), editedAppt.getTimeFormatted(), editedAppt.getVenue()));

        String expectedMessage = String.format(UnmarkAppointmentCommand.MESSAGE_UNMARK_APPOINTMENT_SUCCESS,
                seventhPerson.getName().fullName);

        Model expectedModel = new ModelManager(new Fast(model.getFast()), new UserPrefs());
        expectedModel.setPerson(seventhPerson, editedPerson);

        assertCommandSuccess(appointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_unmarkAppointmentUnfilteredListZeroCount_failure() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson)
                .withAppointment(VALID_APPOINTMENT_AMY, VALID_APPOINTMENT_TIME_AMY, VALID_APPOINTMENT_VENUE_AMY)
                .withAppointmentCount("0")
                .build();
        Appointment editedAppt = editedPerson.getAppointment();

        UnmarkAppointmentCommand appointmentCommand = new UnmarkAppointmentCommand(INDEX_FIRST_PERSON,
                new Appointment(editedAppt.getDate(), editedAppt.getTimeFormatted(), editedAppt.getVenue()));

        String expectedMessage = String.format(UnmarkAppointmentCommand.MESSAGE_UNMARK_APPOINTMENT_FAILURE_ZERO_COUNT);

        assertCommandFailure(appointmentCommand, model, expectedMessage);
    }

    @Test
    public void execute_unmarkAppointmentUnfilteredListAppointmentExist_failure() {
        Person sixthPerson = model.getFilteredPersonList().get(INDEX_SIXTH_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(sixthPerson)
                .withAppointment(VALID_APPOINTMENT_AMY, VALID_APPOINTMENT_TIME_AMY, VALID_APPOINTMENT_VENUE_AMY)
                .withAppointmentCount("1")
                .build();
        Appointment editedAppt = editedPerson.getAppointment();

        UnmarkAppointmentCommand appointmentCommand = new UnmarkAppointmentCommand(INDEX_SIXTH_PERSON,
                new Appointment(editedAppt.getDate(), editedAppt.getTimeFormatted(), editedAppt.getVenue()));

        String expectedMessage = String.format(UnmarkAppointmentCommand.MESSAGE_UNMARK_APPOINTMENT_FAILURE_APPT_EXIST,
                sixthPerson.getName().fullName);

        assertCommandFailure(appointmentCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of FAST list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFast().getPersonList().size());

        UnmarkAppointmentCommand apptCommand = new UnmarkAppointmentCommand(outOfBoundIndex,
                new Appointment(VALID_APPOINTMENT_AMY, VALID_APPOINTMENT_TIME_AMY, VALID_APPOINTMENT_VENUE_AMY));
        assertCommandFailure(apptCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_unmarkAppointmentFilteredList_success() {
        showPersonAtIndex(model, INDEX_SEVENTH_PERSON);
        Person seventhPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(seventhPerson)
                .withAppointment(VALID_APPOINTMENT_AMY, VALID_APPOINTMENT_TIME_AMY, VALID_APPOINTMENT_VENUE_AMY)
                .withAppointmentCount("2")
                .build();
        Appointment editedAppt = editedPerson.getAppointment();

        UnmarkAppointmentCommand appointmentCommand = new UnmarkAppointmentCommand(INDEX_FIRST_PERSON,
                new Appointment(editedAppt.getDate(), editedAppt.getTimeFormatted(), editedAppt.getVenue()));

        String expectedMessage = String.format(UnmarkAppointmentCommand.MESSAGE_UNMARK_APPOINTMENT_SUCCESS,
                seventhPerson.getName().fullName);

        Model expectedModel = new ModelManager(new Fast(model.getFast()), new UserPrefs());
        expectedModel.setPerson(seventhPerson, editedPerson);

        assertCommandSuccess(appointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_unmarkAppointmentFilteredListZeroCount_failure() {
        showPersonAtIndex(model, INDEX_THIRD_PERSON);
        Person thirdPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(thirdPerson)
                .withAppointment(VALID_APPOINTMENT_AMY, VALID_APPOINTMENT_TIME_AMY, VALID_APPOINTMENT_VENUE_AMY)
                .withAppointmentCount("0")
                .build();
        Appointment editedAppt = editedPerson.getAppointment();

        UnmarkAppointmentCommand appointmentCommand = new UnmarkAppointmentCommand(INDEX_FIRST_PERSON,
                new Appointment(editedAppt.getDate(), editedAppt.getTimeFormatted(), editedAppt.getVenue()));

        String expectedMessage = String.format(UnmarkAppointmentCommand.MESSAGE_UNMARK_APPOINTMENT_FAILURE_ZERO_COUNT);

        assertCommandFailure(appointmentCommand, model, expectedMessage);
    }

    @Test
    public void execute_unmarkAppointmentFilteredListAppointmentExist_failure() {
        showPersonAtIndex(model, INDEX_SIXTH_PERSON);
        Person sixthPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(sixthPerson)
                .withAppointment(VALID_APPOINTMENT_AMY, VALID_APPOINTMENT_TIME_AMY, VALID_APPOINTMENT_VENUE_AMY)
                .withAppointmentCount("1")
                .build();
        Appointment editedAppt = editedPerson.getAppointment();

        UnmarkAppointmentCommand appointmentCommand = new UnmarkAppointmentCommand(INDEX_FIRST_PERSON,
                new Appointment(editedAppt.getDate(), editedAppt.getTimeFormatted(), editedAppt.getVenue()));

        String expectedMessage = String.format(UnmarkAppointmentCommand.MESSAGE_UNMARK_APPOINTMENT_FAILURE_APPT_EXIST,
                sixthPerson.getName().fullName);

        assertCommandFailure(appointmentCommand, model, expectedMessage);
    }
}
