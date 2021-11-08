package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BILLING;
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
import seedu.address.model.member.Member;
import seedu.address.model.member.Name;
import seedu.address.model.member.Phone;
import seedu.address.model.member.Point;
import seedu.address.model.reservation.Reservation;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.Transaction;

/**
 * Adds a transaction to the ezFoodie.
 */
public class AddTransactionCommand extends AddCommand {

    /**
     * Stands for the message add transaction command.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a transaction to each member in the ezFoodie.\n"
            + "Parameters:\n"
            + PREFIX_TRANSACTION + " "
            + PREFIX_BILLING + "BILLING_AMOUNT (STRICTLY 2 DECIMAL PLACES) "
            + PREFIX_ID + "ID\n"
            + "Example:\n"
            + COMMAND_WORD + " "
            + PREFIX_TRANSACTION + " "
            + PREFIX_BILLING + "23.00 "
            + PREFIX_ID + "00001";

    /**
     * Stands for the success message of new transaction added.
     */
    public static final String MESSAGE_SUCCESS = "New transaction added: %1$s";
    public static final String MESSAGE_FULL = "Transaction ID has reached " + seedu.address.model.transaction.Id.MAX;

    private final Transaction transactionToAdd;
    private final seedu.address.model.member.Id idToAdd;

    /**
     * Constructs an AddTransactionCommand to add the specified {@code Member}.
     */
    public AddTransactionCommand(Transaction transaction, seedu.address.model.member.Id id) {
        requireAllNonNull(transaction, id);
        transactionToAdd = transaction;
        idToAdd = id;
    }

    /**
     * Executes the model in add transaction command.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult with edited member.
     * @throws CommandException if the user input does not conform the expected format.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Member> lastShownList = model.getUpdatedMemberList();
        Member memberToEdit = lastShownList.stream()
                .filter(member -> idToAdd.equals(member.getId())).findAny().orElse(null);
        if (memberToEdit == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_ID);
        }
        Member editedMember = createEditedMember(memberToEdit, transactionToAdd);
        model.setMember(memberToEdit, editedMember);
        model.updateFilteredMemberList(PREDICATE_SHOW_ALL_MEMBERS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, "Id: " + editedMember.getId()
                + "; Name: " + editedMember.getName()
                + "; Transaction: " + "[" + transactionToAdd + "]"));
    }

    /**
     * Creates and returns a {@code Member} with the details of {@code memberToEdit} and {@code transactionToAdd}.
     *
     * @param memberToEdit {@code memberToEdit} which the command should operate on.
     * @param transactionToAdd {@code transaction} which the command should operate on.
     * @return member with updated transactions and points.
     */
    private static Member createEditedMember(Member memberToEdit, Transaction transactionToAdd) {
        assert memberToEdit != null;
        assert transactionToAdd != null;

        seedu.address.model.member.Id id = memberToEdit.getId();
        Name name = memberToEdit.getName();
        Phone phone = memberToEdit.getPhone();
        Email email = memberToEdit.getEmail();
        Address address = memberToEdit.getAddress();
        Timestamp timestamp = memberToEdit.getTimestamp();
        List<Transaction> transactions = memberToEdit.getTransactions();
        List<Reservation> reservations = memberToEdit.getReservations();
        Set<Tag> tags = memberToEdit.getTags();
        List<Transaction> updatedTransactions = new ArrayList<>(transactions);
        updatedTransactions.add(transactionToAdd);
        Credit updatedCredit = new Credit("" + Math.min(updatedTransactions.stream()
                .mapToInt(t -> (int) t.getBilling().getDoubleValue()).sum(), Credit.MAX));
        Point updatePoint = new Point("" + Math.min(Integer.parseInt(String.valueOf(updatedCredit.getIntValue()
                - memberToEdit.getCredit().getIntValue()
                + memberToEdit.getPoint().getIntValue())), Point.MAX));
        return new Member(id, name, phone, email, address, timestamp, updatedCredit,
                updatePoint, updatedTransactions, reservations, tags);
    }

    /**
     * Overrides the equals method.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTransactionCommand // instanceof handles nulls
                && transactionToAdd.equals(((AddTransactionCommand) other).transactionToAdd))
                && idToAdd.equals(((AddTransactionCommand) other).idToAdd);
    }
}
