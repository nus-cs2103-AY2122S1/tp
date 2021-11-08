package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Name;
import seedu.address.model.module.member.Member;
import seedu.address.model.module.task.Task;
import seedu.address.model.module.task.TaskDeadline;

/**
 * Edits the details of an existing task of a member.
 */
public class TeditCommand extends Command {

    public static final String COMMAND_WORD = "tedit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the detail of the task identified "
            + "by the corresponding index number.\n"
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: " + PREFIX_TASK_INDEX + "TASK_INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_DATE + "DATE_TIME]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TASK_INDEX + "1 "
            + PREFIX_NAME + "group meeting "
            + PREFIX_DATE + "24/10/2021 23:59";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited Task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_TASK_NOT_FOUND = "Task %1$s does not exist in the task list of the member\n";

    private final Index targetTaskIndex;
    private final EditTaskDescriptor editTaskDescriptor;

    /**
     * @param targetTaskIndex of the task in the filtered task list to edit.
     * @param editTaskDescriptor details to edit the task with.
     */
    public TeditCommand(Index targetTaskIndex, EditTaskDescriptor editTaskDescriptor) {
        requireAllNonNull(targetTaskIndex, editTaskDescriptor);

        this.targetTaskIndex = targetTaskIndex;
        this.editTaskDescriptor = editTaskDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownTasks = model.getFilteredTaskList();

        if (targetTaskIndex.getZeroBased() >= lastShownTasks.size()) {
            throw new CommandException(String.format(MESSAGE_TASK_NOT_FOUND, targetTaskIndex.getOneBased()));
        }
        Task taskToEdit = lastShownTasks.get(targetTaskIndex.getZeroBased());
        Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);

        assert model.getCurrentMember().isPresent();
        Member curMember = model.getCurrentMember().get();
        if (!taskToEdit.isSameType(editedTask) && model.hasTask(curMember, editedTask)) {
            throw new CommandException(String.format(Messages.MESSAGE_DUPLICATE_TASK, curMember.getName().toString()));
        }

        model.setTask(taskToEdit, editedTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask));
    }

    private static Task createEditedTask(Task taskToEdit, EditTaskDescriptor editTaskDescriptor) {
        assert taskToEdit != null;

        Name updatedName = editTaskDescriptor.getName().orElse(taskToEdit.getName());
        Boolean updateStatus = editTaskDescriptor.getIsDone().orElse(taskToEdit.isDone());
        TaskDeadline updateTaskDeadline = editTaskDescriptor.getTaskDeadline().orElse(taskToEdit.getTaskDeadline());

        return new Task(updatedName, updateStatus, updateTaskDeadline);
    }

    /**
     * Stores the details to edit the task with. Each non-empty field value will replace the
     * corresponding field value of the task.
     */
    public static class EditTaskDescriptor {
        private Name name;
        private Boolean isDone;
        private TaskDeadline deadline;

        public EditTaskDescriptor() {
        }

        /**
         * Copy constructor.
         */
        public EditTaskDescriptor(EditTaskDescriptor toCopy) {
            setName(toCopy.name);
            setIsDone(toCopy.isDone);
            setDeadline(toCopy.deadline);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, isDone, deadline);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setIsDone(Boolean isDone) {
            this.isDone = isDone;
        }

        public Optional<Boolean> getIsDone() {
            return Optional.ofNullable(isDone);
        }

        public void setDeadline(TaskDeadline taskDeadline) {
            this.deadline = taskDeadline;
        }

        public Optional<TaskDeadline> getTaskDeadline() {
            return Optional.ofNullable(deadline);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            EditTaskDescriptor that = (EditTaskDescriptor) o;
            return Objects.equals(name, that.name) && Objects.equals(isDone, that.isDone)
                    && Objects.equals(deadline, that.deadline);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, isDone, deadline);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TeditCommand
                && editTaskDescriptor.equals(((TeditCommand) other).editTaskDescriptor)
                && targetTaskIndex.equals(((TeditCommand) other).targetTaskIndex));

    }
}
