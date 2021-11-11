package nustracker.logic.commands;

import static java.util.Objects.requireNonNull;
import static nustracker.commons.core.Messages.MESSAGE_INVALID_EVENT_NAME;
import static nustracker.commons.core.Messages.MESSAGE_STUDENT_LIST_NOT_SHOWN;

import nustracker.commons.core.Messages;
import nustracker.logic.commands.exceptions.CommandException;
import nustracker.model.Model;
import nustracker.model.event.Event;
import nustracker.model.event.EventName;
import nustracker.model.student.EnrolledEventsContainsKeywordsPredicate;
import nustracker.ui.MainWindow.CurrentlyShownList;

/**
 * Filters and lists all students in address book attending the event in the argument.
 */
public class FilterEventCommand extends FilterCommand {

    private final EventName eventName;

    public FilterEventCommand(EventName eventName) {
        this.eventName = eventName;
    }

    @Override
    public CommandResult execute(Model model,
                                 CurrentlyShownList currentlyShownList) throws CommandException {
        requireNonNull(model);

        if (currentlyShownList != CurrentlyShownList.STUDENTS_LIST) {
            throw new CommandException(MESSAGE_STUDENT_LIST_NOT_SHOWN);
        }

        Event event = model.getEvent(eventName);

        //event does not exist
        if (event == null) {
            throw new CommandException(String.format(MESSAGE_INVALID_EVENT_NAME, eventName.toString()));
        }

        EnrolledEventsContainsKeywordsPredicate predicate =
                new EnrolledEventsContainsKeywordsPredicate(event.getName());

        requireNonNull(model);
        model.updateFilteredStudentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterEventCommand // instanceof handles nulls
                && eventName.equals(((FilterEventCommand) other).eventName)); // state check
    }
}
