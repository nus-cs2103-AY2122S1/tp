package seedu.unify.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.unify.commons.core.Messages;
import seedu.unify.model.Model;
import seedu.unify.model.task.Task;

/**
 * Finds and lists all tasks in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: keyword (more_keywords)... (d/date) (t/tag)...\n"
            + "Example: " + COMMAND_WORD + " assignment 2 d/2021-12-12 t/CS1234";

    private final Predicate<Task> predicate;

    /**
     * Creates a FindCommand to find {@code Task} filtered by the predicate
     */
    public FindCommand(Predicate<Task> predicate) {
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
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
