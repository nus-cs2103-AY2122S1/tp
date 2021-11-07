package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.member.Member;
import seedu.address.model.module.task.Task;

/**
 * Adds a task to the task list of a person.
 */
public class TaddCommand extends Command {
    public static final String COMMAND_WORD = "tadd";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the task list of a member.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_DATE + "DATE_TIME "
            + PREFIX_MEMBER_INDEX + "MEMBER_INDEX "
            + "[" + PREFIX_MEMBER_INDEX + "MORE_MEMBER_INDEX]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Submit form "
            + PREFIX_DATE + "21/10/2021 23:59 "
            + PREFIX_MEMBER_INDEX + "2 "
            + PREFIX_MEMBER_INDEX + "3 ";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";

    public final Set<Index> targetMemberIdList;
    public final Task toAdd;

    /**
     * Creates an TaddCommand to add the specified {@code Task} to the member with specified {@code MemberId}.
     */
    public TaddCommand(Set<Index> memberIdList, Task task) {
        requireAllNonNull(memberIdList, task);
        targetMemberIdList = memberIdList;
        toAdd = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ObservableList<Member> members = model.getFilteredMemberList();

        for (Index targetMemberId: targetMemberIdList) {
            if (targetMemberId.getZeroBased() >= members.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
            }
            Member targetMember = members.get(targetMemberId.getZeroBased());
            if (model.hasTask(targetMember, toAdd)) {
                throw new CommandException(String.format(Messages.MESSAGE_DUPLICATE_TASK,
                        targetMember.getName().toString()));
            }
        }

        for (Index targetMemberId: targetMemberIdList) {
            Member targetMember = members.get(targetMemberId.getZeroBased());
            model.addTask(targetMember, toAdd);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaddCommand // instanceof handles nulls
                && toAdd.equals(((TaddCommand) other).toAdd));
    }
}
