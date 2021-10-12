package seedu.address.testutil;

import seedu.address.logic.commands.TAddCommand;
import seedu.address.logic.commands.TDelCommand;
import seedu.address.model.member.Member;
import seedu.address.model.task.MemberID;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskID;

import static seedu.address.logic.parser.CliSyntax.*;

/**
 * A utility class for Task.
 */
public class TaskUtil {
    /**
     * Returns an add command string for adding the {@code task} to the member with {@code memberID}.
     */
    public static String getTAddCommand(Task task, MemberID memberID) {
        return TAddCommand.COMMAND_WORD + " " + getTaskDetails(task, memberID);
    }

    /**
     * Returns the part of command string for the given {@code task}'s details.
     */
    public static String getTaskDetails(Task task, MemberID memberID) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_TASKNAME + task.getTaskName() + " ");
        sb.append(PREFIX_MEMBER_ID + memberID.toString());
        return sb.toString();
    }

    /**
     * Returns a task delete command string for deleting the {@code task}
     * from the member with {@code memberID}.
     */
    public static String getTDelCommand(TaskID taskID, MemberID memberID) {
        return TDelCommand.COMMAND_WORD + " " + getTaskIDDetails(taskID, memberID);
    }

    /**
     * Returns the part of command string for the given {@code taskID} and {@code memberID}'s details.
     */
    public static String getTaskIDDetails(TaskID taskID, MemberID memberID) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_TASK_ID + taskID.toString() + " ");
        sb.append(PREFIX_MEMBER_ID_DEL + memberID.toString());
        return sb.toString();
    }
}
