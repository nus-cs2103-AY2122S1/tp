package seedu.unify.testutil;

import static seedu.unify.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.unify.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.unify.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.unify.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.Set;

import seedu.unify.logic.commands.AddCommand;
import seedu.unify.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.unify.model.task.Tag;
import seedu.unify.model.task.Task;

/**
 * A utility class for Task.
 */
public class TaskUtil {

    /**
     * Returns an add command string for adding the {@code task}.
     */
    public static String getAddCommand(Task task) {
        return AddCommand.COMMAND_WORD + " " + getTaskDetails(task);
    }

    /**
     * Returns the part of command string for the given {@code task}'s details.
     */
    public static String getTaskDetails(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + task.getName().taskName + " ");
        sb.append(PREFIX_TIME + task.getTime().value + " ");
        sb.append(PREFIX_DATE + task.getDate().value + " ");
        task.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagTaskName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditTaskDescriptor}'s details.
     */
    public static String getEditTaskDescriptorDetails(EditTaskDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.taskName).append(" "));
        descriptor.getTime().ifPresent(time -> sb.append(PREFIX_TIME).append(time.value).append(" "));
        descriptor.getDate().ifPresent(date -> sb.append(PREFIX_DATE).append(date.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagTaskName).append(" "));
            }
        }
        return sb.toString();
    }
}
