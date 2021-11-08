package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREAMBLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESTAMP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.CommandArgument.filled;
import static seedu.address.logic.parser.CommandArgument.optionalMultiple;
import static seedu.address.logic.parser.CommandArgument.optionalSingle;
import static seedu.address.logic.parser.CommandArgument.requiredSingle;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.TaskCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.CommandSpecification;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Contact;
import seedu.address.model.task.Task;
import seedu.address.model.task.Timestamp;

/**
 * Edits the details of an existing task in the address book.
 */
public class EditTaskCommand extends TaskCommand {
    public static final String COMMAND_WORD = "edit";
    public static final String FULL_COMMAND_WORD = TaskCommand.COMMAND_WORD + " " + COMMAND_WORD;

    public static final CommandSpecification COMMAND_SPECS = new CommandSpecification(
            FULL_COMMAND_WORD,
            "Edits the details of the task identified by the index number used in the displayed task list.\n"
            + "Existing values will be overwritten by the input values.",
            requiredSingle(PREFIX_PREAMBLE, "index"),
            optionalSingle(PREFIX_TITLE, "title"),
            optionalSingle(PREFIX_DESCRIPTION, "description"),
            optionalSingle(PREFIX_TIMESTAMP, "timestamp"),
            optionalMultiple(PREFIX_TAG, "tag")
    ).withExample(
            filled(PREFIX_PREAMBLE, "1"),
            filled(PREFIX_DESCRIPTION, "Physics assignment"),
            filled(PREFIX_TIMESTAMP, "25-12-2020")
    );


    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited Task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private final Index index;
    private final EditTaskDescriptor editTaskDescriptor;
    private Task oldTask;

    /**
     * @param index index of the task in the filtered task list to edit
     * @param editTaskDescriptor details to edit the task with
     */
    public EditTaskCommand(Index index, EditTaskDescriptor editTaskDescriptor) {
        requireNonNull(index);
        requireNonNull(editTaskDescriptor);

        this.index = index;
        this.editTaskDescriptor = new EditTaskDescriptor(editTaskDescriptor);
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToEdit}
     * edited with {@code editTaskDescriptor}.
     */
    private static Task createEditedTask(Task taskToEdit, EditTaskDescriptor editTaskDescriptor) {
        assert taskToEdit != null;

        String updatedTitle = editTaskDescriptor.getTitle()
                .orElse(taskToEdit.getTitle());
        String updatedDescription = editTaskDescriptor.getDescription()
                .or(taskToEdit::getDescription).orElse(null);
        Timestamp updatedTimestamp = editTaskDescriptor.getTimestamp()
                .or(taskToEdit::getTimestamp).orElse(null);
        boolean updatedDone = editTaskDescriptor.isDone().orElse(taskToEdit.isDone());
        Set<Tag> updatedTags = editTaskDescriptor.getTags()
                .orElse(taskToEdit.getTags());
        Set<Contact> updatedContacts = editTaskDescriptor.getContacts()
                .orElse(taskToEdit.getContacts());

        return new Task(updatedTitle,
                updatedDescription,
                updatedTimestamp,
                updatedTags,
                updatedDone,
                updatedContacts);
    }

    @Override
    protected CommandResult executeDo(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> taskList = model.getFilteredTaskList();

        if (index.getZeroBased() >= taskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToEdit = taskList.get(index.getZeroBased());
        this.oldTask = taskToEdit;
        Task editedTask = createEditedTask(taskToEdit,
                editTaskDescriptor);

        // Replace task with edited task
        model.setTask(taskToEdit,
                editedTask);

        // Return with new edited task
        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS,
                editedTask));
    }

    @Override
    protected CommandResult executeUndo(Model model) throws CommandException {
        EditTaskDescriptor oldTaskDescriptor = EditTaskDescriptor.from(this.oldTask);
        List<Task> taskList = model.getFilteredTaskList();
        Task taskToEdit = taskList.get(index.getZeroBased());
        Task previousEditTask = createEditedTask(taskToEdit,
                oldTaskDescriptor);
        model.setTask(taskToEdit,
                previousEditTask);
        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS,
                taskToEdit));
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
        return index.equals(e.index) && editTaskDescriptor.equals(e.editTaskDescriptor);
    }

    /**
     * Stores the details to edit the task with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditTaskDescriptor {
        private String title;
        private String description;
        private Timestamp timestamp;
        private Boolean isDone;
        private Set<Tag> tags;
        private Set<Contact> contacts;

        public EditTaskDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditTaskDescriptor(EditTaskDescriptor toCopy) {
            setTitle(toCopy.title);
            setDescription(toCopy.description);
            setTimestamp(toCopy.timestamp);
            setDone(toCopy.isDone);
            setTags(toCopy.tags);
            setContacts(toCopy.contacts);
        }

        /**
         * Factory method that takes in a Task that returns an EditTaskDescriptor.
         * @param toCopy Task to copy to a descriptor.
         * @return The EditTaskDescriptor representation of the task.
         */
        public static EditTaskDescriptor from(Task toCopy) {
            requireNonNull(toCopy);
            EditTaskDescriptor descriptor = new EditTaskDescriptor();
            descriptor.setTitle(toCopy.getTitle());
            descriptor.setTimestamp(toCopy.getTimestamp().orElse(null));
            descriptor.setTags(toCopy.getTags());
            descriptor.setDescription(toCopy.getDescription().orElse(null));
            descriptor.setContacts(toCopy.getContacts());
            descriptor.setDone(toCopy.isDone());
            return descriptor;
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(title,
                    description,
                    timestamp,
                    tags,
                    contacts);
        }

        public Optional<String> getTitle() {
            return Optional.ofNullable(title);
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Optional<String> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Optional<Boolean> isDone() {
            return Optional.ofNullable(isDone);
        }

        public void setDone(Boolean done) {
            isDone = done;
        }

        public Optional<Timestamp> getTimestamp() {
            return Optional.ofNullable(timestamp);
        }

        public void setTimestamp(Timestamp timestamp) {
            this.timestamp = timestamp;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable contact set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code contacts} is null.
         */
        public Optional<Set<Contact>> getContacts() {
            return (contacts != null) ? Optional.of(Collections.unmodifiableSet(contacts)) : Optional.empty();
        }

        /**
         * Sets {@code contacts} to this object's {@code contacts}.
         * A defensive copy of {@code contacts} is used internally.
         */
        public void setContacts(Set<Contact> contacts) {
            this.contacts = (contacts != null) ? new HashSet<>(contacts) : null;
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

            return getTitle().equals(e.getTitle())
                    && getDescription().equals(e.getDescription())
                    && getTimestamp().equals(e.getTimestamp())
                    && getTags().equals(e.getTags())
                    && getContacts().equals(e.getContacts());
        }
    }
}
