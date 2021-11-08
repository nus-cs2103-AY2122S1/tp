package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.DeadlineTask;
import seedu.address.model.task.Description;
import seedu.address.model.task.EventTask;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDate;
import seedu.address.model.task.TaskName;
import seedu.address.model.task.TodoTask;

/**
 * Edits the details of tasks stored in the list.
 */
public class EditTaskCommand extends Command {

    public static final String COMMAND_WORD = "editTask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the task identified "
            + "by the index number used in the displayed task list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DEADLINE + "2012-10-31";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited Task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the tApp.";
    public static final String MESSAGE_TODO_TIME = "A Todo task cannot contain any time information";
    public static final String MESSAGE_DEADLINE_ON = "on/ prefix cannot be used for a Deadline task";
    public static final String MESSAGE_EVENT_BY = "by/ prefix cannot be used for an Event task";

    private final Index index;
    private final EditTaskDescriptor editTaskDescriptor;

    /**
     * @param index of the task in the filtered task list to edit
     * @param editTaskDescriptor details to edit the task with
     */
    public EditTaskCommand(Index index, EditTaskDescriptor editTaskDescriptor) {
        requireNonNull(index);
        requireNonNull(editTaskDescriptor);

        this.index = index;
        this.editTaskDescriptor = new EditTaskDescriptor(editTaskDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToEdit = lastShownList.get(index.getZeroBased());
        Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);

        if (!taskToEdit.isSameTask(editedTask) && model.hasTask(editedTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.setTask(taskToEdit, editedTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask));
    }

    /**
     * Creates and returns a {@code task} with the details of {@code taskToEdit}
     * edited with {@code editTaskDescriptor}.
     */
    private static Task createEditedTask(Task taskToEdit, EditTaskDescriptor editTaskDescriptor)
            throws CommandException {
        assert taskToEdit != null;

        TaskName updatedTaskName = editTaskDescriptor.getTaskName().orElse(taskToEdit.getName());
        Set<Tag> updatedTags = editTaskDescriptor.getTags().orElse(taskToEdit.getTags());
        Description description = editTaskDescriptor.getDescription()
                .orElse(new Description(taskToEdit.getDescription()));
        Task.Priority updatedPriority = editTaskDescriptor.getPriority().orElse(taskToEdit.getPriority());


        if (taskToEdit instanceof DeadlineTask) {

            //If the task is a Deadline Task, it shouldn't contain /on prefix.
            if (editTaskDescriptor.getEventTaskDate().isPresent()) {
                throw new CommandException(EditTaskCommand.MESSAGE_DEADLINE_ON);
            }
            TaskDate updatedDeadline = editTaskDescriptor.getDeadlineTaskDate()
                    .orElse(((DeadlineTask) taskToEdit).getDeadline());
            return new DeadlineTask(updatedTaskName, updatedTags, taskToEdit.checkIsDone(), updatedDeadline,
                    description, updatedPriority);

        } else if (taskToEdit instanceof EventTask) {

            //If the task is an Event Task, it shouldn't contain /by prefix.
            if (editTaskDescriptor.getDeadlineTaskDate().isPresent()) {
                throw new CommandException(EditTaskCommand.MESSAGE_EVENT_BY);
            }
            TaskDate updatedTaskDate = editTaskDescriptor.getEventTaskDate()
                    .orElse(((EventTask) taskToEdit).getTaskDate());
            return new EventTask(updatedTaskName, updatedTags,
                    taskToEdit.checkIsDone(), updatedTaskDate, description, updatedPriority);

        } else {

            //if the task is a @codeTodo task, it should not contain any time info.
            if (editTaskDescriptor.getEventTaskDate().isPresent()
                    || editTaskDescriptor.getDeadlineTaskDate().isPresent()) {
                throw new CommandException(EditTaskCommand.MESSAGE_TODO_TIME);
            }
            return new TodoTask(updatedTaskName, updatedTags, taskToEdit.checkIsDone(),
                    description, updatedPriority);
        }
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
        return index.equals(e.index)
                && editTaskDescriptor.equals(e.editTaskDescriptor);
    }

    /**
     * Stores the details to edit the student with. Each non-empty field value will replace the
     * corresponding field value of the student.
     */
    public static class EditTaskDescriptor {
        private TaskName taskName;
        private Description description;
        private TaskDate eventTaskDate;
        private TaskDate deadlineTaskDate;
        private Set<Tag> tags;
        private Task.Priority priority;

        public EditTaskDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditTaskDescriptor(EditTaskDescriptor toCopy) {
            setTaskName(toCopy.taskName);
            setEventTaskDate(toCopy.eventTaskDate);
            setDeadlineTaskDate(toCopy.deadlineTaskDate);
            setTags(toCopy.tags);
            setTaskPriority(toCopy.priority);
            setDescription(toCopy.description);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(taskName, eventTaskDate, tags, description, deadlineTaskDate, priority);
        }

        public void setTaskName(TaskName taskName) {
            this.taskName = taskName;
        }

        public void setTaskPriority(Task.Priority priority) {
            this.priority = priority;
        }

        public Optional<TaskName> getTaskName() {
            return Optional.ofNullable(taskName);
        }

        public void setEventTaskDate(TaskDate taskDate) {
            this.eventTaskDate = taskDate;
        }

        public void setDeadlineTaskDate(TaskDate deadline) {
            this.deadlineTaskDate = deadline;
        }


        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public Optional<TaskDate> getEventTaskDate() {
            return Optional.ofNullable(eventTaskDate);
        }

        public Optional<TaskDate> getDeadlineTaskDate() {
            return Optional.ofNullable(deadlineTaskDate);
        }

        public Optional<Task.Priority> getPriority() {
            return Optional.ofNullable(priority);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTaskDescriptor)) {
                return false;
            }

            // state check
            EditTaskDescriptor e = (EditTaskDescriptor) other;

            return getTaskName().equals(e.getTaskName())
                    && getEventTaskDate().equals(e.getEventTaskDate())
                    && getPriority().equals(e.getPriority())
                    && getDeadlineTaskDate().equals(e.getDeadlineTaskDate())
                    && getDescription().equals(e.getDescription())
                    && getTags().equals(e.getTags());
        }
    }
}
