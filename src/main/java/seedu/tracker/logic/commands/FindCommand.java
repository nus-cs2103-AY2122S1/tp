package seedu.tracker.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.tracker.commons.core.Messages;
import seedu.tracker.model.Model;
import seedu.tracker.model.module.ModuleContainsKeywordsPredicate;

/**
 * Finds and lists all modules in module tracker which contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all modules which contain any of "
        + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
        + "Parameters: [c/] [t/] [d/] [m/] [tag/] [y/] [s/] KEYWORDS\n"
        + "Example: " + COMMAND_WORD + " c/ m/ CS2103t";
    public static final String MESSAGE_EMPTY_KEYWORD = "KEYWORDS cannot be blank."
        + "Example: " + COMMAND_WORD + " c/ m/ CS2103t";

    private final ModuleContainsKeywordsPredicate predicate;

    public FindCommand(ModuleContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredModuleList(predicate);
        return new CommandResult(
            String.format(Messages.MESSAGE_MODULES_LISTED_OVERVIEW, model.getFilteredModuleList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof FindCommand // instanceof handles nulls
            && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
