package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.member.Member;
import seedu.address.model.task.MemberID;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskID;
import seedu.address.model.task.TaskList;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Deletes a task from the task list of a person.
 */
public class TDelCommand extends Command {
    public static final String COMMAND_WORD = "tdel";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a task from the task list of a person. "
            + "Parameters: "
            + PREFIX_TASK_ID + " TASK_ID "
            + PREFIX_MEMBER_ID_DEL + " MEMBER_ID\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TASK_ID + " 3 "
            + PREFIX_MEMBER_ID_DEL + " 2";

    public static final String MESSAGE_SUCCESS = "This task is successfully deleted for %1$s: %2$s";
    public static final String MESSAGE_TASK_NOT_FOUND = "This task does not exist in the task list of the member";

    public final MemberID targetMemberID;
    public final TaskID targetTaskID;

    /**
     * Creates an TDelCommand to delete the task with specified {@code TaskID}
     * from the member with specified {@code MemberID}.
     */
    public TDelCommand(MemberID memberID, TaskID taskID) {
        requireNonNull(memberID);
        requireNonNull(taskID);
        targetMemberID = memberID;
        targetTaskID = taskID;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ObservableList<Member> members = model.getAddressBook().getMemberList();
        int taskId = Integer.parseInt(targetMemberID.toString()) - 1;
        Member targetMember = members.get(taskId);

        TaskList taskList = targetMember.getTaskList();
        ObservableList<Task> tasks = taskList.asUnmodifiableObservableList();
        if (tasks.size() <= taskId) {
            throw new CommandException(MESSAGE_TASK_NOT_FOUND);
        }
        Task targetTask = tasks.get(Integer.parseInt(targetTaskID.toString()) - 1);
        String deletedTaskName = targetTask.toString();
        model.deleteTask(targetMember, taskId);

        return new CommandResult(String.format(MESSAGE_SUCCESS, deletedTaskName, targetMember.getName().toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TDelCommand // instanceof handles nulls
                && targetTaskID.equals(((TDelCommand) other).targetTaskID));
    }
}
