package nustracker.logic.commands;

import static java.util.Objects.requireNonNull;
import static nustracker.commons.core.Messages.MESSAGE_INVALID_EVENT_NAME;
import static nustracker.commons.core.Messages.MESSAGE_INVALID_STUDENTID;
import static nustracker.commons.util.CollectionUtil.requireAllNonNull;
import static nustracker.logic.commands.RemoveCommand.MESSAGE_REMOVE_EVENT_SUCCESS;
import static nustracker.logic.commands.RemoveCommand.MESSAGE_STUDENT_NOT_ALREADY_ENROLLED;
import static nustracker.testutil.Assert.assertThrows;
import static nustracker.testutil.TypicalAddressBook.getTypicalAddressBook;
import static nustracker.testutil.TypicalEvents.EVENTNAME_INVALID;
import static nustracker.testutil.TypicalEvents.EVENTNAME_ONE;
import static nustracker.testutil.TypicalEvents.EVENTNAME_TWO;
import static nustracker.testutil.TypicalEvents.MATH_OLYMPIAD;
import static nustracker.testutil.TypicalEvents.ORIENTATION;
import static nustracker.testutil.TypicalEvents.SPORTS_CAMP;
import static nustracker.testutil.TypicalStudents.GOD;
import static nustracker.testutil.TypicalStudents.STUDENTID_MISSING;
import static nustracker.testutil.TypicalStudents.STUDENTID_ONE;
import static nustracker.testutil.TypicalStudents.STUDENTID_TWO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import nustracker.commons.util.CollectionUtil;
import nustracker.logic.commands.exceptions.CommandException;
import nustracker.model.AddressBook;
import nustracker.model.Model;
import nustracker.model.ModelManager;
import nustracker.model.ReadOnlyAddressBook;
import nustracker.model.UserPrefs;
import nustracker.model.event.Event;
import nustracker.model.event.EventName;
import nustracker.model.student.Student;
import nustracker.model.student.StudentId;
import nustracker.testutil.EventBuilder;
import nustracker.testutil.ModelStub;
import nustracker.testutil.StudentBuilder;
import nustracker.ui.MainWindow.CurrentlyShownList;

public class RemoveCommandTest {


    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullParameters_throwsNullPointerException() {

        assertThrows(NullPointerException.class, () -> new RemoveCommand(null, null));
        assertThrows(NullPointerException.class, () -> new RemoveCommand(STUDENTID_ONE, null));
        assertThrows(NullPointerException.class, () -> new RemoveCommand(null, EVENTNAME_ONE));

    }

    @Test
    public void execute_removeStudent_removeSuccess() {

        RemoveCommand removeCommand = new RemoveCommand(STUDENTID_ONE, MATH_OLYMPIAD.getName());

        ModelStubRemoveEvents compareModel = new ModelStubRemoveEvents();

        Student currStudent = model.getStudent(STUDENTID_ONE);
        Event currEvent = model.getEvent(MATH_OLYMPIAD.getName());

        compareModel.addEnrolledEvent(currStudent, currEvent);
        compareModel.addParticipant(currEvent, STUDENTID_ONE);

        CommandResult actualCommandResult = null;

        try {
            actualCommandResult = removeCommand.execute(compareModel, CurrentlyShownList.STUDENTS_LIST);
        } catch (CommandException e) {
            fail("Command Exception should not be thrown.");
        }

        CommandResult expectedCommandResult = new CommandResult(String.format(
                MESSAGE_REMOVE_EVENT_SUCCESS,
                currStudent.getName().toString(),
                currStudent.getStudentId().getStudentIdString(),
                currEvent.getName().getEventName()));

        assertEquals(expectedCommandResult, actualCommandResult);

        assertEquals(model.getAddressBook(), compareModel.getAddressBook());

    }

    @Test
    public void execute_removeStudent_studentNotAlreadyEnrolled() {
        RemoveCommand removeCommand = new RemoveCommand(GOD.getStudentId(), SPORTS_CAMP.getName());

        CommandException expectedCommandException = new CommandException(String.format(
                MESSAGE_STUDENT_NOT_ALREADY_ENROLLED,
                GOD.getName().toString(),
                GOD.getStudentId().getStudentIdString(),
                SPORTS_CAMP.getName().getEventName()));


        assertThrows(CommandException.class,
                expectedCommandException.getMessage(), () ->
                        removeCommand.execute(model, CurrentlyShownList.STUDENTS_LIST));

    }



    @Test
    public void execute_removeStudentFromNonExistentEvent_throwsCommandException() {
        RemoveCommand removeCommand = new RemoveCommand(GOD.getStudentId(), EVENTNAME_INVALID);

        CommandException expectedCommandException = new CommandException(String.format(
                MESSAGE_INVALID_EVENT_NAME,
                EVENTNAME_INVALID));


        assertThrows(CommandException.class,
                expectedCommandException.getMessage(), () ->
                        removeCommand.execute(model, CurrentlyShownList.STUDENTS_LIST));

    }

    @Test
    public void execute_removeNonExistentStudent_throwsCommandException() {
        RemoveCommand removeCommand = new RemoveCommand(STUDENTID_MISSING, ORIENTATION.getName());

        CommandException expectedCommandException = new CommandException(String.format(
                MESSAGE_INVALID_STUDENTID,
                STUDENTID_MISSING));


        assertThrows(CommandException.class,
                expectedCommandException.getMessage(), () ->
                        removeCommand.execute(model, CurrentlyShownList.STUDENTS_LIST));

    }


    @Test
    public void equals() {
        final RemoveCommand standardCommand = new RemoveCommand(
                STUDENTID_ONE,
                EVENTNAME_ONE);

        // same values -> returns true
        RemoveCommand commandWithSameValues = new RemoveCommand(
                STUDENTID_ONE,
                EVENTNAME_ONE);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new HelpCommand()));

        // different student ID -> returns false
        assertFalse(standardCommand.equals(new RemoveCommand(
                STUDENTID_TWO,
                EVENTNAME_ONE)));

        // different event -> returns false
        assertFalse(standardCommand.equals(new RemoveCommand(
                STUDENTID_ONE,
                EVENTNAME_TWO)));
    }

    /**
     * A Model Stub to easily create models to compare with.
     */
    private class ModelStubRemoveEvents extends ModelStub {
        final AddressBook addressBook = getTypicalAddressBook();

        @Override
        public Student getStudent(StudentId studentId) {
            requireAllNonNull(studentId);
            return addressBook.getStudent(studentId);
        }

        @Override
        public void setStudent(Student target, Student editedStudent) {
            CollectionUtil.requireAllNonNull(target, editedStudent);
            addressBook.setStudent(target, editedStudent);
        }

        @Override
        public Event getEvent(EventName name) {
            requireNonNull(name);

            return addressBook.getEvent(name);
        }

        @Override
        public void setEvent(Event target, Event editedEvent) {
            requireAllNonNull(target, editedEvent);

            addressBook.setEvent(target, editedEvent);
        }


        public void addParticipant(Event target, StudentId studentId) {
            Event updatedParticipantsEvent = new EventBuilder(target)
                    .addParticipant(studentId)
                    .build();

            setEvent(target, updatedParticipantsEvent);
        }

        public void addEnrolledEvent(Student target, Event eventToAdd) {
            Student updatedEnrolledEventsStudent = new StudentBuilder(target)
                    .withEvent(eventToAdd)
                    .build();

            setStudent(target, updatedEnrolledEventsStudent);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return addressBook;
        }
    }
}
