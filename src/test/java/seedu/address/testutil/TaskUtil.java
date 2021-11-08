package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.task.TaddCommand;
import seedu.address.logic.commands.task.TdelCommand;
import seedu.address.model.module.task.Task;

/**
 * A utility class for Task.
 */
public class TaskUtil {
    /**
     * Returns an add command string for adding the {@code task} to the member with {@code memberId}.
     */
    public static String getTaddCommand(Task task, Index memberId) {
        return TaddCommand.COMMAND_WORD + " " + getTaskDetails(task, memberId);
    }

    /**
     * Returns the part of command string for the given {@code task}'s details.
     */
    public static String getTaskDetails(Task task, Index memberId) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + task.getName().toString() + " ");
        sb.append(PREFIX_DATE + task.getTaskDeadline().toString() + " ");
        sb.append(PREFIX_MEMBER_INDEX + Integer.toString(memberId.getOneBased()));
        return sb.toString();
    }

    /**
     * Returns a task delete command string for deleting the {@code task}
     * from the member with {@code memberId}.
     */
    public static String getTdelCommand(Index taskId) {
        return TdelCommand.COMMAND_WORD + " " + PREFIX_TASK_INDEX + Integer.toString(taskId.getOneBased());
    }
}
