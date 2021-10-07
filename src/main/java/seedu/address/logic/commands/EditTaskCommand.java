package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * Edits the details of an existing task.
 */
public class EditTaskCommand extends Command {

    public static final String COMMAND_WORD = "edittask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the task identified "
            + "by the index number used in the displayed person list and task number. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_TASK_INDEX + "TASK_INDEX "
            + "[" + PREFIX_TASK + "TASKNAME]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TASK_INDEX + "2 "
            + PREFIX_TASK + "Assignment Discussion";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited Task: %1$s ";
    public static final String MESSAGE_TASK_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TASK = "Task already exists.";
    public static final String MESSAGE_INVALID_TASK = "The size of %1$s's task list is not that big";

    private final Index targetPersonIndex;
    private final Index targetTaskIndex;
    private final Task editedTask;

    /**
     * @param targetPersonIndex of the person in the filtered person list
     * @param targetTaskIndex of the task to edit
     */
    public EditTaskCommand(Index targetPersonIndex, Index targetTaskIndex, Task editedTask) {
        requireNonNull(targetPersonIndex);
        requireNonNull(targetTaskIndex);
        requireNonNull(editedTask);

        this.targetPersonIndex = targetPersonIndex;
        this.targetTaskIndex = targetTaskIndex;
        this.editedTask = editedTask;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetPersonIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(targetPersonIndex.getZeroBased());
        List<Task> tasks = new ArrayList<>();
        tasks.addAll(personToEdit.getTasks());

        if (targetTaskIndex.getZeroBased() >= tasks.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_TASK, personToEdit.getName()));
        }

        Task taskToEdit = tasks.get(targetTaskIndex.getZeroBased());
        if (taskToEdit.equals(editedTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        tasks.set(targetTaskIndex.getZeroBased(), editedTask);
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getTags(), tasks);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditTaskCommand)) {
            return false;
        }

        // state check
        EditTaskCommand e = (EditTaskCommand) other;
        return targetPersonIndex.equals(e.targetPersonIndex)
                && targetTaskIndex.equals(e.targetTaskIndex)
                && editedTask.equals(e.editedTask);
    }
}
