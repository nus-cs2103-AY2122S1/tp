package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class ScheduleCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToMeet = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Appointment sampleAppointment = new Appointment("04-Feb-2021 05:00");
        ScheduleCommand scheduleCommand = new ScheduleCommand(INDEX_FIRST_PERSON, sampleAppointment);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        Person newAppointmentPerson = new PersonBuilder(personToMeet).withAppointment(sampleAppointment).build();
        expectedModel.setPerson(personToMeet, newAppointmentPerson);

        String expectedMessage = String.format(ScheduleCommand.MESSAGE_MEET_PERSON_SUCCESS, newAppointmentPerson);

        assertCommandSuccess(scheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Appointment sampleAppointment = new Appointment("04-Feb-2021 05:00");
        ScheduleCommand scheduleCommand = new ScheduleCommand(outOfBoundIndex, sampleAppointment);

        assertCommandFailure(scheduleCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Appointment sampleAppointment1 = new Appointment("04-Feb-2021 05:00");
        Appointment sampleAppointment2 = new Appointment("05-Feb-2021 05:00");
        ScheduleCommand scheduleFirstCommand = new ScheduleCommand(INDEX_FIRST_PERSON, sampleAppointment1);
        ScheduleCommand scheduleSecondCommand = new ScheduleCommand(INDEX_FIRST_PERSON, sampleAppointment2);
        ScheduleCommand scheduleThirdCommand = new ScheduleCommand(INDEX_SECOND_PERSON, sampleAppointment1);

        // same object -> returns true
        assertTrue(scheduleFirstCommand.equals(scheduleFirstCommand));

        // same values -> returns true
        Appointment sampleAppointment1Copy = new Appointment("04-Feb-2021 05:00");
        ScheduleCommand scheduleFirstCommandCopy = new ScheduleCommand(INDEX_FIRST_PERSON, sampleAppointment1Copy);
        assertTrue(scheduleFirstCommand.equals(scheduleFirstCommandCopy));

        // different types -> returns false
        assertFalse(scheduleFirstCommand.equals(1));

        // null -> returns false
        assertFalse(scheduleFirstCommand.equals(null));

        // different appointment -> returns false
        assertFalse(scheduleFirstCommand.equals(scheduleSecondCommand));

        // different index -> returns false
        assertFalse(scheduleFirstCommand.equals(scheduleThirdCommand));
    }

}
