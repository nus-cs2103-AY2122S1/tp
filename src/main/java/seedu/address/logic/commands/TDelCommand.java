package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER_ID_DEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_ID;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.data.member.Member;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskList;

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

    public final Index targetMemberID;
    public final Index targetTaskID;

    /**
     * Creates an TDelCommand to delete the task with specified {@code TaskID}
     * from the member with specified {@code MemberID}.
     */
    public TDelCommand(Index memberID, Index taskID) {
        requireAllNonNull(memberID, taskID);
        targetMemberID = memberID;
        targetTaskID = taskID;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ObservableList<Member> members = model.getFilteredMemberList();
        int memberId = targetMemberID.getZeroBased();
        if (targetMemberID.getZeroBased() >= members.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
        }
        Member targetMember = members.get(memberId);

        int taskId = targetTaskID.getZeroBased();
        TaskList taskList = targetMember.getTaskList();
        ObservableList<Task> tasks = taskList.asUnmodifiableObservableList();
        if (targetTaskID.getZeroBased() >= tasks.size()) {
            throw new CommandException(MESSAGE_TASK_NOT_FOUND);
        }
        Task targetTask = tasks.get(targetTaskID.getZeroBased());
        String deletedTaskName = targetTask.getTaskName();
        model.deleteTask(targetMember, taskId);

        return new CommandResult(String.format(MESSAGE_SUCCESS, targetMember.getName().toString(), deletedTaskName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TDelCommand // instanceof handles nulls
                && targetTaskID.equals(((TDelCommand) other).targetTaskID));
    }
}
