package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BILLING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRANSACTION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEMBERS;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Timestamp;
import seedu.address.model.member.Address;
import seedu.address.model.member.Credit;
import seedu.address.model.member.Email;
import seedu.address.model.member.Member;
import seedu.address.model.member.Name;
import seedu.address.model.member.Phone;
import seedu.address.model.member.Point;
import seedu.address.model.reservation.Reservation;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.Billing;
import seedu.address.model.transaction.Transaction;

/**
 * Edits the details of an existing transaction in the ezFoodie.
 */
public class EditTransactionCommand extends EditCommand {

    /**
     * Stands for edit command.
     */
    public static final String COMMAND_WORD = "edit";

    /**
     * Stands for the message of edit command for edit transaction.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the transaction identified "
            + "by the member ID and transaction ID. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters:\n"
            + "Edit by member ID and transaction ID: "
            + PREFIX_TRANSACTION + " " + PREFIX_ID + "member ID + transaction ID "
            + PREFIX_BILLING + "BILLING_AMOUNT\n"
            + "Example:\n"
            + "Edit by member ID and transaction ID: "
            + COMMAND_WORD + " " + PREFIX_TRANSACTION + " " + PREFIX_ID + "00001000001 "
            + PREFIX_BILLING + "123.45";

    /**
     * Stands for succeed message of edit member.
     */
    public static final String MESSAGE_SUCCESS = "Edited Member: %1$s";

    /**
     * Stands for message of not edited which need fields provided.
     */
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private final seedu.address.model.member.Id memberId;
    private final seedu.address.model.transaction.Id transactionId;
    private final EditTransactionDescriptor editTransactionDescriptor;

    /**
     * Constructs EditTransactionCommand.
     *
     * @param memberId of the member in the updated member list to edit.
     * @param transactionId of the transaction in the transaction list to edit.
     * @param editTransactionDescriptor details to edit the transaction with.
     */
    public EditTransactionCommand(
            seedu.address.model.member.Id memberId, seedu.address.model.transaction.Id transactionId,
            EditTransactionDescriptor editTransactionDescriptor) {
        requireAllNonNull(memberId, transactionId, editTransactionDescriptor);

        this.memberId = memberId;
        this.transactionId = transactionId;
        this.editTransactionDescriptor = new EditTransactionDescriptor(editTransactionDescriptor);
    }

    /**
     * Creates and returns a {@code Member} with the details of {@code memberToEdit},
     * {@code transactionToEdit} and {@code editTransactionDescriptor}.
     *
     * @return Member with updated credits.
     */
    private static Member createEditedMember(
            Member memberToEdit, Transaction transactionToEdit, EditTransactionDescriptor editTransactionDescriptor) {
        assert memberToEdit != null;
        assert transactionToEdit != null;

        // Member
        seedu.address.model.member.Id id = memberToEdit.getId();
        Name name = memberToEdit.getName();
        Phone phone = memberToEdit.getPhone();
        Email email = memberToEdit.getEmail();
        Address address = memberToEdit.getAddress();
        Timestamp timestamp = memberToEdit.getTimestamp();
        List<Transaction> transactions = memberToEdit.getTransactions();
        List<Reservation> reservations = memberToEdit.getReservations();
        Set<Tag> updatedTags = memberToEdit.getTags();

        // Transaction
        Timestamp updatedTimestamp = editTransactionDescriptor.getTimestamp().orElse(transactionToEdit.getTimestamp());
        Billing updatedBilling = editTransactionDescriptor.getBilling().orElse(transactionToEdit.getBilling());

        List<Transaction> updatedTransactions = new ArrayList<>(transactions);
        Transaction updatedTransaction = new Transaction(transactionToEdit.getId(), updatedTimestamp, updatedBilling);
        updatedTransactions.stream()
                .filter(transaction -> transaction.isSameId(transactionToEdit)).findAny()
                .ifPresent(transaction -> updatedTransactions
                        .set(updatedTransactions.indexOf(transaction), updatedTransaction));
        Credit updatedCredit = new Credit("" + Math.min(updatedTransactions.stream()
                .mapToInt(t -> (int) t.getBilling().getDoubleValue()).sum(), Credit.MAX));

        Point updatedPoint;
        if (updatedBilling.getDoubleValue() > transactionToEdit.getBilling().getDoubleValue()) {
            updatedPoint = new Point("" + Math.min(Integer.parseInt(String.valueOf(updatedCredit.getIntValue()
                    - memberToEdit.getCredit().getIntValue()
                    + memberToEdit.getPoint().getIntValue())), Point.MAX));
        } else {
            updatedPoint = memberToEdit.getPoint();
        }
        return new Member(id, name, phone, email, address, timestamp, updatedCredit,
                updatedPoint, updatedTransactions, reservations, updatedTags);
    }

    /**
     * Overrides and executes model.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult related to edir transaction command.
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
        Transaction transactionToEdit = memberToEdit.getTransactions().stream()
                .filter(transaction -> transactionId.equals(transaction.getId())).findAny().orElse(null);
        if (transactionToEdit == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_TRANSACTION_DISPLAYED_ID);
        }
        Member editedMember = createEditedMember(memberToEdit, transactionToEdit, editTransactionDescriptor);
        model.setMember(memberToEdit, editedMember);
        model.updateFilteredMemberList(PREDICATE_SHOW_ALL_MEMBERS);
        Transaction updatedTransaction = editedMember.getTransactions().stream()
                .filter(transaction -> transactionId.equals(transaction.getId())).findAny().orElse(null);
        return new CommandResult(String.format(MESSAGE_SUCCESS, "Id: " + editedMember.getId()
                + "; Name: " + editedMember.getName()
                + "; Transaction: " + "[" + updatedTransaction + "]"));
    }

    /**
     * Overrides the equals method.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditTransactionCommand // instanceof handles nulls
                && memberId.equals(((EditTransactionCommand) other).memberId)
                && transactionId.equals(((EditTransactionCommand) other).transactionId)
                && editTransactionDescriptor
                .equals(((EditTransactionCommand) other).editTransactionDescriptor)); // state check
    }

    /**
     * Stores the details to edit the transaction with. Each non-empty field value will replace the
     * corresponding field value of the transaction.
     */
    public static class EditTransactionDescriptor {
        private Timestamp timestamp;
        private Billing billing;

        public EditTransactionDescriptor() {}

        /**
         * Copies constructor.
         * A defensive copy of {@code toCopy} is used internally.
         */
        public EditTransactionDescriptor(EditTransactionDescriptor toCopy) {
            setTimestamp(toCopy.timestamp);
            setBilling(toCopy.billing);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(timestamp, billing);
        }

        /**
         * Sets time stamp from input {@code timestamp}.
         *
         * @param timestamp transaction's timestamp.
         */
        public void setTimestamp(Timestamp timestamp) {
            this.timestamp = timestamp;
        }

        /**
         * Gets time stamp.
         */
        public Optional<Timestamp> getTimestamp() {
            return Optional.ofNullable(timestamp);
        }

        /**
         * Sets Billing from {@code billing}.
         *
         * @param billing transaction's billing.
         */
        public void setBilling(Billing billing) {
            this.billing = billing;
        }

        /**
         * Gets billing.
         */
        public Optional<Billing> getBilling() {
            return Optional.ofNullable(billing);
        }

        /**
         * Overrides the equals method.
         */
        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTransactionDescriptor)) {
                return false;
            }

            // state check
            EditTransactionDescriptor e = (EditTransactionDescriptor) other;

            return getTimestamp().equals(e.getTimestamp())
                    && getBilling().equals(e.getBilling());
        }
    }
}
