package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OVERDUE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_COMPLETED_TASKS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_DUE_TASKS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_OVERDUE_TASKS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.module.member.Member;

/**
 * Lists all tasks of the user in the Ailurus.
 */
public class TlistCommand extends Command {
    public static final String COMMAND_WORD = "tlist";

    public static final String MESSAGE_SUCCESS = "Listed all tasks";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the task list of a member. \n"
            + "Only one of " + PREFIX_DONE + "or " + PREFIX_OVERDUE + "may be present\n"
            + "Parameters: "
            + PREFIX_MEMBER_ID + "MEMBER_ID "
            + "[" + PREFIX_DONE + " OPTION (must be \"y\" or \"n\")] "
            + "[" + PREFIX_OVERDUE + "] \n"
            + "Examples: " + COMMAND_WORD + " " + PREFIX_MEMBER_ID + "2\n"
            + COMMAND_WORD + " " + PREFIX_MEMBER_ID + "2" + PREFIX_DONE + "y\n"
            + COMMAND_WORD + " " + PREFIX_MEMBER_ID + "2" + PREFIX_OVERDUE + "\n";

    public final Index targetMemberID;
    private String filter = "";

    /**
     * Creates an TListCommand to display the specified {@code Tasks}
     * belonging to the member with the specified {@code MemberID}.
     */
    public TlistCommand(Index memberID) {
        requireNonNull(memberID);
        targetMemberID = memberID;
    }

    /**
     * Creates an TListCommand to display the specified {@code Tasks}
     * belonging to the member with the specified {@code MemberID} with a {@code filter}.
     */
    public TlistCommand(Index memberID, String filter) {
        requireNonNull(memberID, filter);
        targetMemberID = memberID;
        this.filter = filter;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ObservableList<Member> members = model.getFilteredMemberList();
        Member targetMember = members.get(targetMemberID.getZeroBased());
        switch (filter) {
        case "y":
            model.updateFilteredTaskList(targetMember, PREDICATE_SHOW_ALL_COMPLETED_TASKS);
            return new CommandResult(MESSAGE_SUCCESS + " that have been completed by "
                    + targetMember.getName());
        case "n":
            model.updateFilteredTaskList(targetMember, PREDICATE_SHOW_ALL_DUE_TASKS);
            return new CommandResult(MESSAGE_SUCCESS + " that are due for " + targetMember.getName());
        case "overdue":
            model.updateFilteredTaskList(targetMember, PREDICATE_SHOW_ALL_OVERDUE_TASKS);
            return new CommandResult(MESSAGE_SUCCESS + " that are overdue for " + targetMember.getName());
        default:
            model.updateFilteredTaskList(targetMember, PREDICATE_SHOW_ALL_TASKS);
            return new CommandResult(MESSAGE_SUCCESS + " of " + targetMember.getName());
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TlistCommand // instanceof handles nulls
                && targetMemberID.equals(((TlistCommand) other).targetMemberID));
    }
}
