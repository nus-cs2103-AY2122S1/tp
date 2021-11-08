package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRANSACTION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEMBERS;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Timestamp;
import seedu.address.model.member.Address;
import seedu.address.model.member.Credit;
import seedu.address.model.member.Email;
import seedu.address.model.member.Id;
import seedu.address.model.member.Member;
import seedu.address.model.member.Name;
import seedu.address.model.member.Phone;
import seedu.address.model.member.Point;
import seedu.address.model.reservation.Reservation;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.Transaction;

/**
 * Deletes a transaction identified by it's member ID and transaction ID from the ezFoodie.
 */
public class DeleteTransactionCommand extends DeleteCommand {

    /**
     * Stands for delete command.
     */
    public static final String COMMAND_WORD = "del";

    /**
     * Stands for the message of delete command related to transaction.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the transaction identified by the member ID and transaction ID.\n"
            + "Parameters:\n"
            + "Delete by member ID and transaction ID: "
            + PREFIX_TRANSACTION + " " + PREFIX_ID + "member ID + transaction ID\n"
            + "Example:\n"
            + "Delete by member ID and transaction ID: "
            + COMMAND_WORD + " " + PREFIX_TRANSACTION + " " + PREFIX_ID + "00001000001";

    /**
     * Stands for succeed message of delete transaction.
     */
    public static final String MESSAGE_SUCCESS = "Deleted Transaction: %1$s";

    private final seedu.address.model.member.Id memberId;
    private final seedu.address.model.transaction.Id transactionId;

    /**
     * Constructs an DeleteCommand to delete the specified {@code Member} by {@code memberId} and {@code transactionId}.
     */
    public DeleteTransactionCommand(
            seedu.address.model.member.Id memberId, seedu.address.model.transaction.Id transactionId) {
        requireAllNonNull(memberId, transactionId);
        this.memberId = memberId;
        this.transactionId = transactionId;
    }

    /**
     * Creates and returns a {@code Member} with the details of {@code memberToEdit} and {@code transactionToDelete}
     *
     * @param memberToEdit create a new member to edit and update.
     * @param transactionToDelete the transaction will be deleted.
     * @return Member with added transactions and updated credits.
     */
    private static Member createEditedMember(Member memberToEdit, Transaction transactionToDelete) {
        assert memberToEdit != null;
        assert transactionToDelete != null;

        Id id = memberToEdit.getId();
        Name name = memberToEdit.getName();
        Phone phone = memberToEdit.getPhone();
        Email email = memberToEdit.getEmail();
        Address address = memberToEdit.getAddress();
        Timestamp timestamp = memberToEdit.getTimestamp();
        List<Transaction> transactions = memberToEdit.getTransactions();
        List<Reservation> reservations = memberToEdit.getReservations();
        Set<Tag> updatedTags = memberToEdit.getTags();

        List<Transaction> updatedTransactions = new ArrayList<>(transactions);
        updatedTransactions.remove(transactionToDelete);
        Credit updatedCredit = new Credit("" + Math.min(updatedTransactions.stream()
                .mapToInt(t -> (int) t.getBilling().getDoubleValue()).sum(), Credit.MAX));
        Point point = memberToEdit.getPoint();
        return new Member(id, name, phone, email, address, timestamp, updatedCredit,
                point, updatedTransactions, reservations, updatedTags);
    }

    /**
     * Executes the model.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult delete transaction command.
     * @throws CommandException if the user input does not conform the expected format.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Member> lastShownList = model.getUpdatedMemberList();
        Member memberToEdit = lastShownList.stream()
                .filter(member -> memberId.equals(member.getId())).findAny().orElse(null);
        if (memberToEdit == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_ID);
        }
        Transaction transactionToDelete = memberToEdit.getTransactions().stream()
                .filter(transaction -> transactionId.equals(transaction.getId())).findAny().orElse(null);
        if (transactionToDelete == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_TRANSACTION_DISPLAYED_ID);
        }
        Member editedMember = createEditedMember(memberToEdit, transactionToDelete);
        model.setMember(memberToEdit, editedMember);
        model.updateFilteredMemberList(PREDICATE_SHOW_ALL_MEMBERS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, "Id: " + editedMember.getId()
                + "; Name: " + editedMember.getName()
                + "; Transaction: " + "[" + transactionToDelete + "]"));
    }

    /**
     * Overrides the equals method.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTransactionCommand // instanceof handles nulls
                && memberId.equals(((DeleteTransactionCommand) other).memberId)
                && transactionId.equals(((DeleteTransactionCommand) other).transactionId)); // state check
    }
}
