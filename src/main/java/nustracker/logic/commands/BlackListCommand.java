package nustracker.logic.commands;

import static nustracker.commons.core.Messages.MESSAGE_INVALID_EVENT_NAME;
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
import nustracker.model.student.StudentId;
import nustracker.ui.MainWindow.CurrentlyShownList;

/**
 * Blacklists a Student ID for an event.
 * Blacklisted Student IDs will not be allowed to be added to the event.
 * Student ID need not exist currently in the database.
 */
public class BlackListCommand extends Command {
    public static final String COMMAND_WORD = "blacklist";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Blacklists a Student ID for an event identified by its name.\n"
            + "Parameters: "
            + PREFIX_STUDENTID + "[STUDENT_ID] "
            + PREFIX_EVENT + "[EVENT_NAME]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STUDENTID + "e0322322 "
            + PREFIX_EVENT + "Orientation Camp";
    public static final String MESSAGE_BLACKLIST_SUCCESS =
            "Student ID %1$s blacklisted for %2$s.";
    public static final String MESSAGE_STUDENTID_ALREADY_BLACKLISTED =
            "Student ID %1$s is already blacklisted for %2$s.";
    public static final String MESSAGE_STUDENTID_CURRENTLY_ENROLLED =
            "Student ID %1$s is currently enrolled in %2$s. Enrolled student IDs cannot be blacklisted.\n"
            + "If you want to blacklist the student ID, remove the student ID from the event first using the remove "
            + "command:\n"
            + RemoveCommand.MESSAGE_USAGE;

    private final StudentId studentId;
    private final EventName eventName;

    /**
     * @param studentId of the student to blacklist for the event
     * @param eventName the event name
     */
    public BlackListCommand(StudentId studentId, EventName eventName) {
        requireAllNonNull(studentId, eventName);

        this.studentId = studentId;
        this.eventName = eventName;
    }

    @Override
    public CommandResult execute(Model model,
                                 CurrentlyShownList currentlyShownList) throws CommandException {

        // Check if an event with this event name exists
        Event currEvent = model.getEvent(eventName);

        if (currEvent == null) {
            throw new CommandException(String.format(MESSAGE_INVALID_EVENT_NAME, eventName.getEventName()));
        }

        // Check if student ID is already blacklisted in event
        boolean isOnBlacklist = currEvent.isBlacklisted(studentId);

        if (isOnBlacklist) {
            throw new CommandException(String.format(MESSAGE_STUDENTID_ALREADY_BLACKLISTED,
                    studentId.toString(),
                    eventName.toString()));
        }

        // Check if student ID is enrolled in event
        boolean isEnrolled = currEvent.isInEvent(studentId);

        if (isEnrolled) {
            throw new CommandException(String.format(MESSAGE_STUDENTID_CURRENTLY_ENROLLED,
                    studentId.toString(),
                    eventName.toString()));
        }

        Set<Participant> oldBlacklist = currEvent.getBlacklist();

        Set<Participant> updatedBlacklist = new HashSet<>(oldBlacklist);
        updatedBlacklist.add(new Participant(studentId.toString()));

        Event updatedEvent = new Event(
                currEvent.getName(), currEvent.getDate(), currEvent.getTime(), currEvent.getParticipants(),
                updatedBlacklist);

        model.setEvent(currEvent, updatedEvent);

        return new CommandResult(String.format(MESSAGE_BLACKLIST_SUCCESS,
                studentId.toString(),
                eventName.toString()));
    }



    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BlackListCommand)) {
            return false;
        }

        // state check
        BlackListCommand e = (BlackListCommand) other;
        return studentId.equals(e.studentId)
                && eventName.equals(e.eventName);
    }
}
