package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LABEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

public class AddTaskCommand extends Command {

    public static final String COMMAND_WORD = "addtask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the application. "
            + "Parameters: "
            + PREFIX_LABEL + "LABEL "
            + PREFIX_DATE + "DATE "
            + "[" + PREFIX_TASK_TAG + "TASKTAG]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_LABEL + "Sew black buttons onto blazer "
            + PREFIX_DATE + "20 August 2021 "
            + PREFIX_TASK_TAG + "SO1";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_UNFOUND_ORDERID = "The sales order with the given id cannot be found.";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the taskbook";

    private final Task toAdd;

    /**
     * Creates an AddTaskCommand to add the specified {@code Task}
     */
    public AddTaskCommand(Task task) {
        requireNonNull(task);
        toAdd = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        long tagId = toAdd.getTagId();
        if (tagId != -1 && !model.hasOrder(tagId)) {
            throw new CommandException(MESSAGE_UNFOUND_ORDERID);
        }

        if (model.hasTask(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), CommandResult.DisplayState.TASK);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AddTaskCommand
                && toAdd.equals(((AddTaskCommand) other).toAdd));
    }
}
