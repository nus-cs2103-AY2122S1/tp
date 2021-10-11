package seedu.fast.logic.commands;

import static java.util.Objects.requireNonNull;
import java.util.function.Predicate;

import seedu.fast.commons.core.Messages;
import seedu.fast.model.Model;
import seedu.fast.model.person.NameContainsKeywordsPredicate;
import seedu.fast.model.person.Person;
import seedu.fast.model.person.PriorityPredicate;

import seedu.fast.model.tag.PriorityTag;


/**
 * Finds and lists all persons in FAST whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) or priority tags \n"
            + "and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]..."
            + " OR " + PriorityTag.PRIORITY_TAG_PREFIX
            + "PRIORITY [MORE_PRIORITIES]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie\n"
            + "Example: " + COMMAND_WORD + " "
            + PriorityTag.PRIORITY_TAG_PREFIX
            + PriorityTag.LowPriority.TERM + "\n";

    private final Predicate<Person> predicate;

    public FindCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    public FindCommand(PriorityPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
