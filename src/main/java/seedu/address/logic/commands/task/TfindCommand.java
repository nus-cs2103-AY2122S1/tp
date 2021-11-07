package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.module.NameContainsKeywordsPredicate;
import seedu.address.model.module.task.Task;

/**
 * Lists all tasks of the user containing the keyword in the address book.
 */
public class TfindCommand extends Command {

    public static final String COMMAND_WORD = "tfind";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all tasks of the current member whose task names contain any of "
            + "the specified keywords (case-insensitive) and display them as a list of tasks with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " submit form";

    private final NameContainsKeywordsPredicate<Task> predicate;

    /**
     * Creates an TfindCommand to display the specified {@code Tasks}
     * belonging to the current member with the specified keywords {@code predicate}.
     */
    public TfindCommand(NameContainsKeywordsPredicate<Task> predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TfindCommand // instanceof handles nulls
                && predicate.equals(((NameContainsKeywordsPredicate<Task>) ((TfindCommand) other).predicate)));
    }
}
