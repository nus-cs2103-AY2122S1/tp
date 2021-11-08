package seedu.address.logic.commands.event;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.module.NameContainsKeywordsPredicate;
import seedu.address.model.module.event.Event;

/**
 * Finds and lists all events in Ailurus whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class EfindCommand extends Command {

    public static final String COMMAND_WORD = "efind";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all events whose names contain any of "
            + "the specified keywords (case-insensitive) and display them as a list of events with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " fresh";

    private final NameContainsKeywordsPredicate<Event> predicate;

    public EfindCommand(NameContainsKeywordsPredicate<Event> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEventList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, model.getFilteredEventList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EfindCommand // instanceof handles nulls
                && predicate.equals(((EfindCommand) other).predicate)); // state check
    }
}
