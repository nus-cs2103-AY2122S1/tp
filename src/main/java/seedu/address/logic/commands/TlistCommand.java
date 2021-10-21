package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER_ID;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.module.member.Member;

/**
 * Lists all tasks of the user in the address book.
 */
public class TlistCommand extends Command {
    public static final String COMMAND_WORD = "tlist";

    public static final String MESSAGE_SUCCESS = "Listed all tasks";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the task list of a member. "
            + "Parameters: "
            + PREFIX_MEMBER_ID + " MEMBER_ID\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MEMBER_ID + " 2";

    public final Index targetMemberID;

    /**
     * Creates an TaddCommand to add the specified {@code Task} to the member with specified {@code MemberID}.
     */
    public TlistCommand(Index memberID) {
        requireNonNull(memberID);
        targetMemberID = memberID;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ObservableList<Member> members = model.getFilteredMemberList();
        Member targetMember = members.get(targetMemberID.getZeroBased());
        model.updateFilteredTaskList(targetMember, PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(MESSAGE_SUCCESS + " of " + targetMember.getName());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TlistCommand // instanceof handles nulls
                && targetMemberID.equals(((TlistCommand) other).targetMemberID));
    }
}
