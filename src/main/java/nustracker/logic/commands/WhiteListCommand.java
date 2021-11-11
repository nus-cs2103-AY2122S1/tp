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
 * Removes a Student ID from an event's blacklist.
 * Student ID need not exist currently in the database.
 */
public class WhiteListCommand extends Command {
    public static final String COMMAND_WORD = "whitelist";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes a Student ID from the blacklist of an event identified by its name.\n"
            + "Parameters: "
            + PREFIX_STUDENTID + "[STUDENT_ID] "
            + PREFIX_EVENT + "[EVENT_NAME]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STUDENTID + "e0322322 "
            + PREFIX_EVENT + "Orientation Camp";
    public static final String MESSAGE_WHITELIST_SUCCESS =
            "%1$s removed from %2$s's blacklist.";
    public static final String MESSAGE_STUDENTID_NOT_BLACKLISTED =
            "%1$s is not on %2$s's blacklist.";

    private final StudentId studentId;
    private final EventName eventName;

    /**
     * @param studentId of the student to remove from the event's blacklist
     * @param eventName the event name
     */
    public WhiteListCommand(StudentId studentId, EventName eventName) {
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

        // Check if student ID is blacklisted in event
        boolean isOnBlacklist = currEvent.isBlacklisted(studentId);

        if (!isOnBlacklist) {
            throw new CommandException(String.format(MESSAGE_STUDENTID_NOT_BLACKLISTED,
                    studentId.toString(),
                    eventName.toString()));
        }


        Set<Participant> oldBlacklist = currEvent.getBlacklist();

        Set<Participant> updatedBlacklist = new HashSet<>(oldBlacklist);
        Participant toRemove = new Participant(studentId.toString());
        updatedBlacklist.remove(toRemove);

        Event updatedEvent = new Event(
                currEvent.getName(), currEvent.getDate(), currEvent.getTime(), currEvent.getParticipants(),
                updatedBlacklist);

        model.setEvent(currEvent, updatedEvent);

        return new CommandResult(String.format(MESSAGE_WHITELIST_SUCCESS,
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
        if (!(other instanceof WhiteListCommand)) {
            return false;
        }

        // state check
        WhiteListCommand e = (WhiteListCommand) other;
        return studentId.equals(e.studentId)
                && eventName.equals(e.eventName);
    }
}
