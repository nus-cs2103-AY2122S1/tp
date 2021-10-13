package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER_ID_DEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_ID;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.TaddCommand;
import seedu.address.logic.commands.TdelCommand;
import seedu.address.model.task.Task;

/**
 * A utility class for Task.
 */
public class TaskUtil {
    /**
     * Returns an add command string for adding the {@code task} to the member with {@code memberID}.
     */
    public static String getTaddCommand(Task task, Index memberID) {
        return TaddCommand.COMMAND_WORD + " " + getTaskDetails(task, memberID);
    }

    /**
     * Returns the part of command string for the given {@code task}'s details.
     */
    public static String getTaskDetails(Task task, Index memberID) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + task.getTaskName() + " ");
        sb.append(PREFIX_MEMBER_ID + Integer.toString(memberID.getOneBased()));
        return sb.toString();
    }

    /**
     * Returns a task delete command string for deleting the {@code task}
     * from the member with {@code memberID}.
     */
    public static String getTdelCommand(Index taskID, Index memberID) {
        return TdelCommand.COMMAND_WORD + " " + getTaskIdDetails(taskID, memberID);
    }

    /**
     * Returns the part of command string for the given {@code taskID} and {@code memberID}'s details.
     */
    public static String getTaskIdDetails(Index taskID, Index memberID) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_TASK_ID + Integer.toString(taskID.getOneBased()) + " ");
        sb.append(PREFIX_MEMBER_ID_DEL + Integer.toString(memberID.getOneBased()));
        return sb.toString();
    }
}
