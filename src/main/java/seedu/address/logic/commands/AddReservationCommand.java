package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESERVATION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEMBERS;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.DateTimeUtil;
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
 * Adds a reservation to the ezFoodie.
 */
public class AddReservationCommand extends AddCommand {

    /**
     * Stands for the message add reservation command.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds reservation to member "
            + "by member ID in the ezFoodie.\n"
            + "Parameters:\n"
            + PREFIX_RESERVATION + " "
            + PREFIX_DATE_TIME + "DATE_TIME (" + DateTimeUtil.DATE_TIME_PATTERN + ") "
            + PREFIX_REMARK + "REMARK "
            + PREFIX_ID + "ID\n"
            + "Example:\n"
            + COMMAND_WORD + " "
            + PREFIX_RESERVATION + " "
            + PREFIX_DATE_TIME + "2021-12-01 13:00 "
            + PREFIX_REMARK + "2 people "
            + PREFIX_ID + "00001";

    /**
     * Stands for message success for new reservation added.
     */
    public static final String MESSAGE_SUCCESS = "New reservation added: %1$s";
    public static final String MESSAGE_FULL = "Reservation ID has reached " + seedu.address.model.reservation.Id.MAX;
    public static final String MESSAGE_SAME_DATE = "Only one reservation can be added within the same day. "
            + "Previous reservation: %1$s";

    private final Reservation reservationToAdd;
    private final seedu.address.model.member.Id idToAdd;

    /**
     * Constructs an {@code AddReservationCommand} to add the specified {@code Member}.
     */
    public AddReservationCommand(Reservation reservation, seedu.address.model.member.Id id) {
        requireAllNonNull(reservation, id);
        reservationToAdd = reservation;
        idToAdd = id;
    }

    /**
     * Executes the model in AddReservationCommand.
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
        if (!Reservation.isValidDateTime(reservationToAdd.getDateTime())) {
            throw new CommandException(Reservation.MESSAGE_CONSTRAINTS);
        }
        Reservation reservationSameDate = memberToEdit.getReservations().stream()
                .filter(reservation -> reservation.isSameDate(reservationToAdd)).findAny().orElse(null);
        if (reservationSameDate == null) {
            Member editedMember = createEditedMember(memberToEdit, reservationToAdd);
            model.setMember(memberToEdit, editedMember);
            model.updateFilteredMemberList(PREDICATE_SHOW_ALL_MEMBERS);
            return new CommandResult(String.format(MESSAGE_SUCCESS, "Id: " + editedMember.getId()
                    + "; Name: " + editedMember.getName()
                    + "; Reservation: " + "[" + reservationToAdd + "]"));
        }
        throw new CommandException(String.format(MESSAGE_SAME_DATE, reservationSameDate));
    }

    /**
     * Creates and returns a {@code Member} with the details of {@code memberToEdit} and {@code reservationToAdd}.
     *
     * @param memberToEdit {@code memberToEdit} which the command should operate on.
     * @param reservationToAdd {@code reservation} which the command should operate on.
     * @return Member with updated reservations.
     */
    private static Member createEditedMember(Member memberToEdit, Reservation reservationToAdd) {
        assert memberToEdit != null;
        assert reservationToAdd != null;

        seedu.address.model.member.Id id = memberToEdit.getId();
        Name name = memberToEdit.getName();
        Phone phone = memberToEdit.getPhone();
        Email email = memberToEdit.getEmail();
        Address address = memberToEdit.getAddress();
        Timestamp timestamp = memberToEdit.getTimestamp();
        Credit credit = memberToEdit.getCredit();
        Point point = memberToEdit.getPoint();
        List<Transaction> transactions = memberToEdit.getTransactions();
        List<Reservation> reservations = memberToEdit.getReservations();
        Set<Tag> tags = memberToEdit.getTags();

        List<Reservation> updatedReservations = new ArrayList<>(reservations);
        updatedReservations.add(reservationToAdd);

        return new Member(id, name, phone, email, address, timestamp, credit, point,
                transactions, updatedReservations, tags);
    }

    /**
     * Overrides the equals method.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddReservationCommand // instanceof handles nulls
                && reservationToAdd.equals(((AddReservationCommand) other).reservationToAdd))
                && idToAdd.equals(((AddReservationCommand) other).idToAdd);
    }
}
