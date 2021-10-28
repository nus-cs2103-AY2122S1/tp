package seedu.unify.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.unify.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.unify.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.unify.commons.core.Messages;
import seedu.unify.commons.core.index.Index;
import seedu.unify.commons.util.CollectionUtil;
import seedu.unify.logic.commands.exceptions.CommandException;
import seedu.unify.model.Model;
import seedu.unify.model.tag.Tag;
import seedu.unify.model.task.Task;



/**
 * Adds tags to an existing task in the Uni-Fy app.
 */
public class TagCommand extends Command {
    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":Adds tags to the task identified "
            + "by the index number used in the displayed task list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: task_id "
            + "(" + PREFIX_TAG + "tagName)\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TAG + "assignment";

    public static final String MESSAGE_TAG_TASK_SUCCESS = "Tagged Task %1$s";
    public static final String MESSAGE_NOT_TAGGED = "At least one field to tag should be provided.";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the Uni-Fy app.";

    private final Index index;
    private final TagTaskDescriptor tagTaskDescriptor;


    /**
     * @param index of the task in the filtered task list to edit
     * @param tagTaskDescriptor details to edit the task with
     */
    public TagCommand(Index index, TagTaskDescriptor tagTaskDescriptor) {
        requireNonNull(index);
        requireNonNull(tagTaskDescriptor);

        this.index = index;
        this.tagTaskDescriptor = new TagTaskDescriptor(tagTaskDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToTag = lastShownList.get(index.getZeroBased());
        Task taggedTask = createTaggedTask(taskToTag, tagTaskDescriptor);

        if (!taskToTag.isSameTask(taggedTask) && model.hasTask(taggedTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.setTask(taskToTag, taggedTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_TAG_TASK_SUCCESS, taggedTask));

    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToTag}
     * added with {@code tagTaskDescriptor}.
     */
    private static Task createTaggedTask(Task taskToTag, TagTaskDescriptor tagTaskDescriptor) {
        assert taskToTag != null;
        Set<Tag> updatedTags = tagTaskDescriptor.getTags().orElse(taskToTag.getTags());
        return new Task(taskToTag.getName(), taskToTag.getTime(),
                taskToTag.getDate(), updatedTags, taskToTag.getState(), taskToTag.getPriority());
    }

    @Override
    public boolean equals(Object other) {
        //short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagCommand)) {
            return false;
        }

        //state check
        TagCommand t = (TagCommand) other;
        return index.equals(t.index)
                && tagTaskDescriptor.equals(t.tagTaskDescriptor);
    }

    /**
     * Stores the details to add tags to the tasks.
     */
    public static class TagTaskDescriptor {
        private Set<Tag> tags;

        public TagTaskDescriptor() {}

        /**
         * Copy constructor
         * A defensive copy of {@code tags} is used internally.
         */
        public TagTaskDescriptor(TagTaskDescriptor toCopy) {
            setTags(toCopy.tags);
        }

        /**
         * Returns true if the tag has been added
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(tags);
        }

        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            //short circuit if same object
            if (other == this) {
                return true;
            }

            //instanceof handles nulls
            if (!(other instanceof TagTaskDescriptor)) {
                return false;
            }

            //state check
            TagTaskDescriptor t = (TagTaskDescriptor) other;

            return getTags().equals(t.getTags());
        }

    }
}
