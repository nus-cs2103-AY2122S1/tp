package nurseybook.logic.commands;

import static java.util.Objects.requireNonNull;
import static nurseybook.commons.core.Messages.MESSAGE_DUPLICATE_TASK;
import static nurseybook.commons.core.Messages.MESSAGE_INVALID_TASK_DATETIME_FOR_RECURRING_TASK;
import static nurseybook.commons.core.Messages.MESSAGE_NO_SUCH_ELDERLY;
import static nurseybook.logic.parser.CliSyntax.PREFIX_NAME;
import static nurseybook.logic.parser.CliSyntax.PREFIX_TASK_DATE;
import static nurseybook.logic.parser.CliSyntax.PREFIX_TASK_DESC;
import static nurseybook.logic.parser.CliSyntax.PREFIX_TASK_RECURRING;
import static nurseybook.logic.parser.CliSyntax.PREFIX_TASK_TIME;
import static nurseybook.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.Set;
import java.util.stream.Collectors;

import nurseybook.logic.commands.exceptions.CommandException;
import nurseybook.model.Model;
import nurseybook.model.person.Name;
import nurseybook.model.task.Task;

/**
 * Adds a task to the nursey book.
 */
public class AddTaskCommand extends Command {
    public static final String COMMAND_WORD = "addTask";
    public static final String[] PARAMETERS = { "[" + PREFIX_NAME + "NAME]...",
        PREFIX_TASK_DESC + "DESCRIPTION", PREFIX_TASK_DATE + "DATE",
        PREFIX_TASK_TIME + "TIME", "[" + PREFIX_TASK_RECURRING + "RECURRENCE_TYPE]" };

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the nursey book.\n"
            + "Parameters: "
            + String.join(" ", PARAMETERS)
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Alex Yeoh "
            + PREFIX_NAME + "Bernice Yu "
            + PREFIX_TASK_DESC + "Weekly Taiji "
            + PREFIX_TASK_DATE + "2021-11-20 "
            + PREFIX_TASK_TIME + "14:30 "
            + PREFIX_TASK_RECURRING + "week";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";

    private final Task toAdd;

    /**
     * Creates an AddTaskCommand to add the specified {@code Task}
     */
    public AddTaskCommand(Task t) {
        requireNonNull(t);
        toAdd = t;
    }

    /**
     * Check if entered names are valid and match the casing of the name to be the same as elderly name
     * in NurseyBook's elderly list
     * @param model
     * @throws CommandException if some elderly names entered are not found in NurseyBook
     */
    private void checkAndMatchNames(Model model) throws CommandException {
        Set<Name> enteredNames = toAdd.getRelatedNames();

        if (!model.areAllElderliesPresent(enteredNames)) {
            throw new CommandException(MESSAGE_NO_SUCH_ELDERLY);
        } else if (!enteredNames.isEmpty()) {
            Set<Name> namesWithCasesMatched = enteredNames.stream()
                    .map(n -> model.findElderlyWithName(n).getName()).collect(Collectors.toSet());
            toAdd.setRelatedNames(namesWithCasesMatched);
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (toAdd.isPastCurrentDateAndRecurringTask()) {
            throw new CommandException(MESSAGE_INVALID_TASK_DATETIME_FOR_RECURRING_TASK);
        }

        if (model.hasTask(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        checkAndMatchNames(model);

        model.addTask(toAdd);
        model.updateTasksAccordingToTime();
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        CommandResult result = new CommandResult(String.format(MESSAGE_SUCCESS, toAdd),
                CommandResult.ListDisplayChange.TASK);
        model.commitNurseyBook(result);
        return result;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTaskCommand // instanceof handles nulls
                && toAdd.equals(((AddTaskCommand) other).toAdd));
    }
}
