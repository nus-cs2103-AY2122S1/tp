package safeforhall.logic.commands.sort;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import safeforhall.logic.commands.Command;
import safeforhall.logic.commands.CommandResult;
import safeforhall.logic.commands.exceptions.CommandException;
import safeforhall.model.Model;
import safeforhall.model.event.Event;

public class SortEventCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String PARAMETERS = "";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sort events by date and time\n"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_SUCCESS = "Events have successfully been sorted";

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateSortedEventList(new EventComparator());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    public class EventComparator implements Comparator<Event> {
        @Override
        public int compare(Event e1, Event e2) {
            int comparedResult = e1.getEventDate().compareTo(e2.getEventDate());
            if (comparedResult == 0) {
                return e1.getEventTime().compareTo(e2.getEventTime());
            }
            return comparedResult;
        }
    }
}
