package seedu.fast.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fast.logic.commands.CommandTestUtil.APPT_DES_AMY;
import static seedu.fast.logic.commands.CommandTestUtil.APPT_DES_BOB;
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
import seedu.fast.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.fast.model.Fast;
import seedu.fast.model.Model;
import seedu.fast.model.ModelManager;
import seedu.fast.model.UserPrefs;
import seedu.fast.model.person.Appointment;
import seedu.fast.model.person.Person;
import seedu.fast.testutil.EditAppointmentDescriptorBuilder;
import seedu.fast.testutil.PersonBuilder;

public class EditAppointmentCommandTest {

    private final Model model = new ModelManager(getTypicalFast(), new UserPrefs());

    @Test
    public void equals() {
        final EditAppointmentCommand standardCommand = new EditAppointmentCommand(INDEX_FIRST_PERSON,
                APPT_DES_AMY);

        // same values -> returns true
        EditAppointmentDescriptor copyDescriptor = new EditAppointmentDescriptor(APPT_DES_AMY);
        EditAppointmentCommand commandWithSameValues = new EditAppointmentCommand(INDEX_FIRST_PERSON,
                copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));


        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditAppointmentCommand(INDEX_SECOND_PERSON, APPT_DES_AMY)));

        // different appointment -> returns false
        assertFalse(standardCommand.equals(new EditAppointmentCommand(INDEX_FIRST_PERSON,
                new EditAppointmentDescriptor(APPT_DES_BOB))));
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of FAST list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFast().getPersonList().size());

        EditAppointmentCommand apptCommand = new EditAppointmentCommand(outOfBoundIndex,
                new EditAppointmentDescriptor(APPT_DES_BOB));

        assertCommandFailure(apptCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_allFieldsSpecifiedFilteredList_success() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);
        Person secondPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(secondPerson)
                .withAppointment(VALID_APPOINTMENT_BOB, VALID_APPOINTMENT_TIME_BOB, VALID_APPOINTMENT_VENUE_BOB)
                .build();
        Appointment editedAppt = editedPerson.getAppointment();
        EditAppointmentDescriptor desc = new EditAppointmentDescriptorBuilder(editedAppt).build();

        EditAppointmentCommand appointmentCommand = new EditAppointmentCommand(INDEX_FIRST_PERSON,
                new EditAppointmentDescriptor(desc));

        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_UPDATE_APPOINTMENT_SUCCESS,
                editedPerson.getName().fullName, editedAppt.getDate(), editedAppt.getTimeFormatted(),
                editedAppt.getVenue());

        Model expectedModel = new ModelManager(new Fast(model.getFast()), new UserPrefs());
        expectedModel.setPerson(secondPerson, editedPerson);

        assertCommandSuccess(appointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_twoFieldsSpecifiedFilteredList_success() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);
        Person secondPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(secondPerson)
                .withAppointment(VALID_APPOINTMENT_AMY, VALID_APPOINTMENT_TIME_BOB, VALID_APPOINTMENT_VENUE_BOB)
                .build();
        Appointment editedAppt = editedPerson.getAppointment();
        EditAppointmentDescriptor desc = new EditAppointmentDescriptorBuilder(editedAppt).build();

        EditAppointmentCommand appointmentCommand = new EditAppointmentCommand(INDEX_FIRST_PERSON,
                new EditAppointmentDescriptor(desc));

        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_UPDATE_APPOINTMENT_SUCCESS,
                editedPerson.getName().fullName, editedAppt.getDate(), editedAppt.getTimeFormatted(),
                editedAppt.getVenue());

        Model expectedModel = new ModelManager(new Fast(model.getFast()), new UserPrefs());
        expectedModel.setPerson(secondPerson, editedPerson);

        assertCommandSuccess(appointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_dateFieldSpecifiedFilteredList_success() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);
        Person secondPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(secondPerson)
                .withAppointment(VALID_APPOINTMENT_BOB, VALID_APPOINTMENT_TIME_AMY, VALID_APPOINTMENT_VENUE_AMY)
                .build();
        Appointment editedAppt = editedPerson.getAppointment();
        EditAppointmentDescriptor desc = new EditAppointmentDescriptorBuilder(editedAppt).build();

        EditAppointmentCommand appointmentCommand = new EditAppointmentCommand(INDEX_FIRST_PERSON,
                new EditAppointmentDescriptor(desc));

        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_UPDATE_APPOINTMENT_SUCCESS,
                editedPerson.getName().fullName, editedAppt.getDate(), editedAppt.getTimeFormatted(),
                editedAppt.getVenue());

        Model expectedModel = new ModelManager(new Fast(model.getFast()), new UserPrefs());
        expectedModel.setPerson(secondPerson, editedPerson);

        assertCommandSuccess(appointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_timeFieldSpecifiedFilteredList_success() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);
        Person secondPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(secondPerson)
                .withAppointment(VALID_APPOINTMENT_AMY, VALID_APPOINTMENT_TIME_BOB, VALID_APPOINTMENT_VENUE_AMY)
                .build();
        Appointment editedAppt = editedPerson.getAppointment();
        EditAppointmentDescriptor desc = new EditAppointmentDescriptorBuilder(editedAppt).build();

        EditAppointmentCommand appointmentCommand = new EditAppointmentCommand(INDEX_FIRST_PERSON,
                new EditAppointmentDescriptor(desc));

        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_UPDATE_APPOINTMENT_SUCCESS,
                editedPerson.getName().fullName, editedAppt.getDate(), editedAppt.getTimeFormatted(),
                editedAppt.getVenue());

        Model expectedModel = new ModelManager(new Fast(model.getFast()), new UserPrefs());
        expectedModel.setPerson(secondPerson, editedPerson);

        assertCommandSuccess(appointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_venueFieldSpecifiedFilteredList_success() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);
        Person secondPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(secondPerson)
                .withAppointment(VALID_APPOINTMENT_AMY, VALID_APPOINTMENT_TIME_AMY, VALID_APPOINTMENT_VENUE_BOB)
                .build();
        Appointment editedAppt = editedPerson.getAppointment();
        EditAppointmentDescriptor desc = new EditAppointmentDescriptorBuilder(editedAppt).build();

        EditAppointmentCommand appointmentCommand = new EditAppointmentCommand(INDEX_FIRST_PERSON,
                new EditAppointmentDescriptor(desc));

        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_UPDATE_APPOINTMENT_SUCCESS,
                editedPerson.getName().fullName, editedAppt.getDate(), editedAppt.getTimeFormatted(),
                editedAppt.getVenue());

        Model expectedModel = new ModelManager(new Fast(model.getFast()), new UserPrefs());
        expectedModel.setPerson(secondPerson, editedPerson);

        assertCommandSuccess(appointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editNonExistingAppointmentFilteredList_failure() {
        showPersonAtIndex(model, INDEX_THIRD_PERSON);
        Person thirdPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(thirdPerson)
                .withAppointment(VALID_APPOINTMENT_AMY, VALID_APPOINTMENT_TIME_AMY, VALID_APPOINTMENT_VENUE_BOB)
                .build();
        Appointment editedAppt = editedPerson.getAppointment();
        EditAppointmentDescriptor desc = new EditAppointmentDescriptorBuilder(editedAppt).build();

        EditAppointmentCommand appointmentCommand = new EditAppointmentCommand(INDEX_FIRST_PERSON,
                new EditAppointmentDescriptor(desc));

        String expectedMessage = EditAppointmentCommand.MESSAGE_UPDATE_APPOINTMENT_ERROR;

        assertCommandFailure(appointmentCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditAppointmentCommand apptCommand = new EditAppointmentCommand(outOfBoundIndex,
                new EditAppointmentDescriptor(APPT_DES_BOB));

        assertCommandFailure(apptCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person secondPerson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(secondPerson)
                .withAppointment(VALID_APPOINTMENT_BOB, VALID_APPOINTMENT_TIME_BOB, VALID_APPOINTMENT_VENUE_BOB)
                .build();
        Appointment editedAppt = editedPerson.getAppointment();
        EditAppointmentDescriptor desc = new EditAppointmentDescriptorBuilder(editedAppt).build();

        EditAppointmentCommand appointmentCommand = new EditAppointmentCommand(INDEX_SECOND_PERSON,
                new EditAppointmentDescriptor(desc));

        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_UPDATE_APPOINTMENT_SUCCESS,
                editedPerson.getName().fullName, editedAppt.getDate(), editedAppt.getTimeFormatted(),
                editedAppt.getVenue());

        Model expectedModel = new ModelManager(new Fast(model.getFast()), new UserPrefs());
        expectedModel.setPerson(secondPerson, editedPerson);

        assertCommandSuccess(appointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_twoFieldsSpecifiedUnfilteredList_success() {
        Person secondPerson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(secondPerson)
                .withAppointment(VALID_APPOINTMENT_BOB, VALID_APPOINTMENT_TIME_BOB, VALID_APPOINTMENT_VENUE_AMY)
                .build();
        Appointment editedAppt = editedPerson.getAppointment();
        EditAppointmentDescriptor desc = new EditAppointmentDescriptorBuilder(editedAppt).build();

        EditAppointmentCommand appointmentCommand = new EditAppointmentCommand(INDEX_SECOND_PERSON,
                new EditAppointmentDescriptor(desc));

        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_UPDATE_APPOINTMENT_SUCCESS,
                editedPerson.getName().fullName, editedAppt.getDate(), editedAppt.getTimeFormatted(),
                editedAppt.getVenue());

        Model expectedModel = new ModelManager(new Fast(model.getFast()), new UserPrefs());
        expectedModel.setPerson(secondPerson, editedPerson);

        assertCommandSuccess(appointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_dateFieldSpecifiedUnfilteredList_success() {
        Person secondPerson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(secondPerson)
                .withAppointment(VALID_APPOINTMENT_BOB, VALID_APPOINTMENT_TIME_AMY, VALID_APPOINTMENT_VENUE_AMY)
                .build();
        Appointment editedAppt = editedPerson.getAppointment();
        EditAppointmentDescriptor desc = new EditAppointmentDescriptorBuilder(editedAppt).build();

        EditAppointmentCommand appointmentCommand = new EditAppointmentCommand(INDEX_SECOND_PERSON,
                new EditAppointmentDescriptor(desc));

        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_UPDATE_APPOINTMENT_SUCCESS,
                editedPerson.getName().fullName, editedAppt.getDate(), editedAppt.getTimeFormatted(),
                editedAppt.getVenue());

        Model expectedModel = new ModelManager(new Fast(model.getFast()), new UserPrefs());
        expectedModel.setPerson(secondPerson, editedPerson);

        assertCommandSuccess(appointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_timeFieldSpecifiedUnfilteredList_success() {
        Person secondPerson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(secondPerson)
                .withAppointment(VALID_APPOINTMENT_AMY, VALID_APPOINTMENT_TIME_BOB, VALID_APPOINTMENT_VENUE_AMY)
                .build();
        Appointment editedAppt = editedPerson.getAppointment();
        EditAppointmentDescriptor desc = new EditAppointmentDescriptorBuilder(editedAppt).build();

        EditAppointmentCommand appointmentCommand = new EditAppointmentCommand(INDEX_SECOND_PERSON,
                new EditAppointmentDescriptor(desc));

        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_UPDATE_APPOINTMENT_SUCCESS,
                editedPerson.getName().fullName, editedAppt.getDate(), editedAppt.getTimeFormatted(),
                editedAppt.getVenue());

        Model expectedModel = new ModelManager(new Fast(model.getFast()), new UserPrefs());
        expectedModel.setPerson(secondPerson, editedPerson);

        assertCommandSuccess(appointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_venueFieldSpecifiedUnfilteredList_success() {
        Person secondPerson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(secondPerson)
                .withAppointment(VALID_APPOINTMENT_AMY, VALID_APPOINTMENT_TIME_AMY, VALID_APPOINTMENT_VENUE_BOB)
                .build();
        Appointment editedAppt = editedPerson.getAppointment();
        EditAppointmentDescriptor desc = new EditAppointmentDescriptorBuilder(editedAppt).build();

        EditAppointmentCommand appointmentCommand = new EditAppointmentCommand(INDEX_SECOND_PERSON,
                new EditAppointmentDescriptor(desc));

        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_UPDATE_APPOINTMENT_SUCCESS,
                editedPerson.getName().fullName, editedAppt.getDate(), editedAppt.getTimeFormatted(),
                editedAppt.getVenue());

        Model expectedModel = new ModelManager(new Fast(model.getFast()), new UserPrefs());
        expectedModel.setPerson(secondPerson, editedPerson);

        assertCommandSuccess(appointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editNonExistingAppointmentUnfilteredList_failure() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson)
                .withAppointment(VALID_APPOINTMENT_AMY, VALID_APPOINTMENT_TIME_AMY, VALID_APPOINTMENT_VENUE_BOB)
                .build();
        Appointment editedAppt = editedPerson.getAppointment();
        EditAppointmentDescriptor desc = new EditAppointmentDescriptorBuilder(editedAppt).build();

        EditAppointmentCommand appointmentCommand = new EditAppointmentCommand(INDEX_FIRST_PERSON,
                new EditAppointmentDescriptor(desc));

        String expectedMessage = EditAppointmentCommand.MESSAGE_UPDATE_APPOINTMENT_ERROR;

        assertCommandFailure(appointmentCommand, model, expectedMessage);
    }
}
