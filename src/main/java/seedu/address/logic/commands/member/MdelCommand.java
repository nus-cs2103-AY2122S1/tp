package seedu.address.logic.commands.member;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER_INDEX;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.member.Member;

/**
 * Deletes a member identified using it's displayed index from the address book.
 */
public class MdelCommand extends Command {

    public static final String COMMAND_WORD = "mdel";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the member identified by the corresponding index number.\n"
            + "Parameters: " + PREFIX_MEMBER_INDEX + "MEMBER_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_MEMBER_INDEX + "1\n";

    public static final String MESSAGE_DELETE_MEMBER_SUCCESS = "Deleted Member: %1$s";

    private final Index targetIndex;

    public MdelCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Member> lastShownList = model.getFilteredMemberList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
        }

        Member memberToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteMember(memberToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_MEMBER_SUCCESS, memberToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MdelCommand // instanceof handles nulls
                && targetIndex.equals(((MdelCommand) other).targetIndex)); // state check
    }
}
