package safeforhall.logic.commands.view;

import static java.util.Objects.requireNonNull;
import static safeforhall.model.Model.PREDICATE_SHOW_ALL_EVENTS;

import java.util.List;

import safeforhall.commons.core.Messages;
import safeforhall.commons.core.index.Index;
import safeforhall.logic.commands.Command;
import safeforhall.logic.commands.CommandResult;
import safeforhall.logic.commands.exceptions.CommandException;
import safeforhall.model.Model;
import safeforhall.model.event.Event;

/**
 * Lists all persons in the address book to the user.
 */
public class ViewEventCommand extends Command {

    public static final String COMMAND_WORD = "view";
    public static final String PARAMETERS = "[INDEX]";
    public static final String MESSAGE_ALL_EVENTS_SHOWN = "All events shown";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views the additional details of the identified event "
            + "by the index numbers used in the displayed event list, and views all events when "
            + "index is not specified. "
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_VIEW_EVENT_SUCCESS = "Event details displayed in sidebar";

    private Index targetIndex;

    public ViewEventCommand() {
    }

    /**
     * @param targetIndex Index of Event in the filtered event list to edit
     */
    public ViewEventCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (targetIndex == null) {
            model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
            model.setNoEvent();
            model.updateSortedEventList(null);
            return new CommandResult(MESSAGE_ALL_EVENTS_SHOWN);
        } else {
            setSingleEvent(model);
            return new CommandResult(MESSAGE_VIEW_EVENT_SUCCESS);
        }
    }

    public void setSingleEvent(Model model) throws CommandException {
        List<Event> filteredPersonList = model.getFilteredEventList();
        if (targetIndex.getZeroBased() >= filteredPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }
        Event eventToShow = filteredPersonList.get(targetIndex.getZeroBased());
        model.setSingleEvent(eventToShow);
    }
}
