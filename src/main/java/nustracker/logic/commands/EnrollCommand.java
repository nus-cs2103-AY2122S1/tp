package nustracker.logic.commands;

import static nustracker.commons.core.Messages.MESSAGE_INVALID_EVENT_NAME;
import static nustracker.commons.core.Messages.MESSAGE_INVALID_STUDENTID;
import static nustracker.commons.util.CollectionUtil.requireAllNonNull;
import static nustracker.logic.parser.CliSyntax.PREFIX_EVENT;
import static nustracker.logic.parser.CliSyntax.PREFIX_STUDENTID;

import java.util.HashSet;
import java.util.Set;

import nustracker.logic.commands.exceptions.CommandException;
import nustracker.model.Model;
import nustracker.model.event.Event;
import nustracker.model.event.EventName;
import nustracker.model.event.Participant;
import nustracker.model.student.EnrolledEvents;
import nustracker.model.student.Student;
import nustracker.model.student.StudentId;
import nustracker.ui.MainWindow.CurrentlyShownList;

/**
 * Adds a student to an existing event.
 */
public class EnrollCommand extends Command {

    public static final String COMMAND_WORD = "enroll";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds the person identified by student ID to an event "
            + "identified by its name.\n"
            + "Parameters: "
            + PREFIX_STUDENTID + "STUDENTID "
            + PREFIX_EVENT + "EVENT_NAME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STUDENTID + "e0322322 "
            + PREFIX_EVENT + "Orientation Camp";
    public static final String MESSAGE_ADD_TO_EVENT_SUCCESS =
            "Enrolled Student: %1$s with student Id %2$s into the Event: %3$s";
    public static final String MESSAGE_STUDENT_ALREADY_ENROLLED =
            "The Student %1$s with student Id %2$s is already enrolled into the event: %3$s";
    public static final String MESSAGE_STUDENT_ON_BLACKLIST =
            "The Student %1$s with student Id %2$s is on the blacklist of the event: %3$s"
                    + " and hence cannot be enrolled.";

    private final StudentId studentId;
    private final EventName eventName;

    /**
     * @param studentId of the student to enroll into the event
     * @param eventName the person is to be added to
     */
    public EnrollCommand(StudentId studentId, EventName eventName) {
        requireAllNonNull(studentId, eventName);

        this.studentId = studentId;
        this.eventName = eventName;
    }

    @Override
    public CommandResult execute(Model model,
                                 CurrentlyShownList currentlyShownList) throws CommandException {

        // Enroll Student to event (4 Cases)
        // 1. Success
        // 2. Student does not exist
        // 3. Event does not exist
        // 4. Student already in event

        // Check if a student with this student ID exists in the list
        Student currStudent = model.getStudent(studentId);

        if (currStudent == null) {
            throw new CommandException(String.format(MESSAGE_INVALID_STUDENTID, studentId.getStudentIdString()));
        }

        // Check if an event with this event name exists
        Event currEvent = model.getEvent(eventName);

        if (currEvent == null) {
            throw new CommandException(String.format(MESSAGE_INVALID_EVENT_NAME, eventName.getEventName()));
        }

        // Check if student is in event's blacklist
        boolean isOnBlacklist = currEvent.isBlacklisted(studentId);

        if (isOnBlacklist) {
            throw new CommandException(String.format(MESSAGE_STUDENT_ON_BLACKLIST,
                    currStudent.getName().toString(),
                    currStudent.getStudentId().getStudentIdString(),
                    currEvent.getName().getEventName()));
        }

        // Check if student is already in event
        boolean isAlreadyInEvent = currEvent.isInEvent(studentId);

        if (isAlreadyInEvent) {
            throw new CommandException(String.format(MESSAGE_STUDENT_ALREADY_ENROLLED,
                    currStudent.getName().toString(),
                    currStudent.getStudentId().getStudentIdString(),
                    currEvent.getName().getEventName()));
        }

        // Enroll the student into the event by:
        // 1. Add into this student's EnrolledEvents in model
        // 2. Add to event list

        EnrolledEvents currentlyEnrolledEvents = currStudent.getEvents();
        EnrolledEvents updatedEnrolledEvents = currentlyEnrolledEvents.enrollIntoEvent(currEvent);

        Student editedStudent = new Student(
                currStudent.getName(), currStudent.getPhone(), currStudent.getEmail(),
                currStudent.getYear(), currStudent.getMajor(), currStudent.getStudentId(),
                updatedEnrolledEvents);


        model.setStudent(currStudent, editedStudent);


        Set<Participant> oldParticipants = currEvent.getParticipants();

        Set<Participant> updatedParticipants = new HashSet<>(oldParticipants);
        updatedParticipants.add(new Participant(currStudent.getStudentId().getStudentIdString()));

        Event updatedEvent = new Event(
                currEvent.getName(), currEvent.getDate(), currEvent.getTime(), updatedParticipants,
                currEvent.getBlacklist());

        model.setEvent(currEvent, updatedEvent);

        return new CommandResult(String.format(MESSAGE_ADD_TO_EVENT_SUCCESS,
                editedStudent.getName().toString(),
                editedStudent.getStudentId().getStudentIdString(),
                updatedEvent.getName().getEventName()));
    }



    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EnrollCommand)) {
            return false;
        }

        // state check
        EnrollCommand e = (EnrollCommand) other;
        return studentId.equals(e.studentId)
                && eventName.equals(e.eventName);
    }
}
