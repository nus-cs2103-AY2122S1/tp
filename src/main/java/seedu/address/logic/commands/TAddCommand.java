package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.data.member.Member;
import seedu.address.model.task.Task;

/**
 * Adds a task to the task list of a person.
 */
public class TAddCommand extends Command {
    public static final String COMMAND_WORD = "tadd";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the task list of a person. "
            + "Parameters: "
            + PREFIX_NAME + " TASKNAME "
            + PREFIX_MEMBER_ID + " MEMBER_ID\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + " Submit form "
            + PREFIX_MEMBER_ID + " 2";

    public static final String MESSAGE_SUCCESS = "New task added for %1$s: %2$s";

    public final Index targetMemberID;
    public final Task toAdd;

    /**
     * Creates an TAddCommand to add the specified {@code Task} to the member with specified {@code MemberID}.
     */
    public TAddCommand(Index memberID, Task task) {
        requireAllNonNull(memberID, task);
        targetMemberID = memberID;
        toAdd = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ObservableList<Member> members = model.getFilteredMemberList();
        Member targetMember = members.get(targetMemberID.getZeroBased());
        model.addTask(targetMember, toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, targetMember.getName(), toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TAddCommand // instanceof handles nulls
                && toAdd.equals(((TAddCommand) other).toAdd));
    }
}
