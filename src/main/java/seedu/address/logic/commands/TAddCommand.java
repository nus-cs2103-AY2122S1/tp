package seedu.address.logic.commands;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.member.Member;
import seedu.address.model.task.MemberID;
import seedu.address.model.task.Task;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASKNAME;

/**
 * Adds a task to the task list of a person.
 */
public class TAddCommand extends Command {
    public static final String COMMAND_WORD = "tadd";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the task list of a person. "
            + "Parameters: "
            + PREFIX_TASKNAME + "TASKNAME "
            + PREFIX_MEMBER_ID + "MEMBER_ID\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TASKNAME + "Submit form "
            + PREFIX_MEMBER_ID + "2";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task list of the member";

    public  final MemberID targetMemberID;
    public final Task toAdd;

    /**
     * Creates an PAddCommand to add the specified {@code Person}
     */
    public TAddCommand(MemberID memberID, Task task) {
        requireNonNull(memberID);
        requireNonNull(task);
        targetMemberID = memberID;
        toAdd = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ObservableList<Member> members = model.getAddressBook().getMemberList();
        Member targetMember = members.get(Integer.parseInt(targetMemberID.toString()));

        if (model.hasTask(targetMember, toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }
        model.addTask(targetMember, toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TAddCommand // instanceof handles nulls
                && toAdd.equals(((TAddCommand) other).toAdd));
    }
}
