package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.member.Id;
import seedu.address.model.member.Member;

/**
 * Deletes a member identified using it's displayed index or member ID from the ezFoodie.
 */
public class DeleteMemberCommand extends DeleteCommand {

    /**
     * Stands for delete command.
     */
    public static final String COMMAND_WORD = "del";

    /**
     * Stands for the message of delete command related to a member from the ezFoodie.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the member identified by the index number used in the displayed member list or member ID.\n"
            + "Parameters:\n"
            + "Delete by index number: " + PREFIX_MEMBER + " " + PREFIX_INDEX + "INDEX"
            + " (INDEX must be a positive integer)\n"
            + "Delete by member ID: " + PREFIX_MEMBER + " " + PREFIX_ID + "ID\n"
            + "Example:\n"
            + "Delete by index number: " + COMMAND_WORD + " " + PREFIX_MEMBER + " " + PREFIX_INDEX + "1\n"
            + "Delete by member ID: " + COMMAND_WORD + " " + PREFIX_MEMBER + " " + PREFIX_ID + "00001";

    /**
     * Stands for succeed message of delete member.
     */
    public static final String MESSAGE_SUCCESS = "Deleted Member: %1$s";

    private final Index index;
    private final Id id;

    /**
     * Creates an DeleteCommand to delete the specified {@code Member} by {@code index} number.
     *
     * @param index the index shown in the page.
     */
    public DeleteMemberCommand(Index index) {
        requireNonNull(index);
        this.index = index;
        id = null;
    }

    /**
     * Creates an DeleteCommand to delete the specified {@code Member} by member ID.
     *
     * @param id the member ID.
     */
    public DeleteMemberCommand(Id id) {
        requireNonNull(id);
        this.id = id;
        index = null;
    }

    /**
     * Executes the model.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult related delete member command.
     * @throws CommandException if the user input does not conform the expected format.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Member> lastShownList = model.getUpdatedMemberList();

        Member memberToDelete = null;
        if (index != null) {
            if (index.getZeroBased() < lastShownList.size()) {
                memberToDelete = lastShownList.get(index.getZeroBased());
            } else {
                throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
            }
        }
        if (id != null) {
            memberToDelete = lastShownList.stream()
                    .filter(member -> id.equals(member.getId())).findAny().orElse(null);
        }
        if (memberToDelete != null) {
            model.deleteMember(memberToDelete);
            return new CommandResult(String.format(MESSAGE_SUCCESS, memberToDelete));
        } else {
            throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_ID);
        }

    }

    /**
     * Overrides the equals method.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteMemberCommand // instanceof handles nulls
                && (index == null || index.equals(((DeleteMemberCommand) other).index))
                && (id == null || id.equals(((DeleteMemberCommand) other).id))); // state check
    }
}
