package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OVERDUE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_COMPLETED_TASKS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_DUE_TASKS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_OVERDUE_TASKS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.member.Member;

/**
 * Lists all tasks of the user in the Ailurus.
 */
public class TlistCommand extends Command {
    public static final String COMMAND_WORD = "tlist";

    public static final String MESSAGE_SUCCESS = "Listed all tasks";

    public static final String MESSAGE_MEMBER_NOT_FOUND = "This member does not exist in the member list.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the task list of a member with filters. \n"
            + "Only one of " + PREFIX_DONE + "or " + PREFIX_OVERDUE + "may be present\n"
            + "Parameters: "
            + PREFIX_MEMBER_INDEX + "MEMBER_INDEX "
            + "[" + PREFIX_DONE + " OPTION (must be \"y\" or \"n\")] "
            + "[" + PREFIX_OVERDUE.toString().trim() + "] \n"
            + "Examples: " + COMMAND_WORD + " " + PREFIX_MEMBER_INDEX + "2\n"
            + COMMAND_WORD + " " + PREFIX_MEMBER_INDEX + "2 " + PREFIX_DONE + "y\n"
            + COMMAND_WORD + " " + PREFIX_MEMBER_INDEX + "2 " + PREFIX_OVERDUE + "\n";

    public final Index targetMemberId;

    private String filter = "";

    /**
     * Creates an TListCommand to display the specified {@code Tasks}
     * belonging to the member with the specified {@code MemberId}.
     */
    public TlistCommand(Index memberId) {
        requireNonNull(memberId);
        targetMemberId = memberId;
    }

    /**
     * Creates an TListCommand to display the specified {@code Tasks}
     * belonging to the member with the specified {@code MemberId} with a {@code filter}.
     */
    public TlistCommand(Index memberId, String filter) {
        requireNonNull(memberId, filter);
        targetMemberId = memberId;
        this.filter = filter;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Member> members = model.getFilteredMemberList();
        if (targetMemberId.getZeroBased() >= members.size()) {
            throw new CommandException(MESSAGE_MEMBER_NOT_FOUND);
        }
        Member targetMember = members.get(targetMemberId.getZeroBased());
        switch (filter) {
        case "y":
            model.updateFilteredTaskList(targetMember, PREDICATE_SHOW_ALL_COMPLETED_TASKS);
            return new CommandResult(MESSAGE_SUCCESS + " that have been completed by "
                    + targetMember.getName());
        case "n":
            model.updateFilteredTaskList(targetMember, PREDICATE_SHOW_ALL_DUE_TASKS);
            return new CommandResult(MESSAGE_SUCCESS + " that are incomplete for " + targetMember.getName());
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
                && targetMemberId.equals(((TlistCommand) other).targetMemberId));
    }
}
