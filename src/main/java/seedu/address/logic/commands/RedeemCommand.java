package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REDEEM;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEMBERS;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
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
 * Redeems point from an existing member in the ezFoodie.
 */
public class RedeemCommand extends Command {

    /**
     * Stands for redeem command.
     */
    public static final String COMMAND_WORD = "redeem";

    /**
     * Stands for the message of redeem command.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Redeems points from member id in the ezFoodie.\n"
            + "Parameters:\n"
            + "Redeem by index number: " + PREFIX_INDEX + "INDEX "
            + "(INDEX must be a positive integer) "
            + "[" + PREFIX_REDEEM + " POINTS]...\n"
            + "Redeem by member ID: " + PREFIX_ID + "ID "
            + "[" + PREFIX_REDEEM + " POINTS]...\n"
            + "Example:\n"
            + "Redeem by index number: " + COMMAND_WORD + " " + PREFIX_INDEX + "1 "
            + PREFIX_REDEEM + "10 "
            + PREFIX_REDEEM + "20\n"
            + "Redeem by member ID: " + COMMAND_WORD + " " + PREFIX_ID + "00001 "
            + PREFIX_REDEEM + "10 "
            + PREFIX_REDEEM + "20";

    /**
     * Stands for message for redeem points successfully.
     */
    public static final String MESSAGE_SUCCESS_REDEMPTION = "Redeemed Member: %1$s";

    /**
     * Stands for message for duplicate member.
     */
    public static final String MESSAGE_DUPLICATE_MEMBER =
            "This member (phone or email) already exists in the ezFoodie.";

    /**
     * Stands for message when redemption point exceed.
     */
    public static final String MESSAGE_INVALID_POINTS_LESS_THAN_ZERO = "Redeemed point has already exceeded.\n"
            + "Points can't redeemed less than 0, please try again.";

    private final List<Point> pointsToRedeemList = new ArrayList<>();
    private final Id idToRedeem;
    private final Index indexToRedeem;

    /**
     * Constructs an RedeemCommand to add the specified {@code Member} by id.
     *
     * @param pointsToRedeemList the points of to redeemed list.
     * @param id the member id that needs to redeem point.
     */
    public RedeemCommand(List<Point> pointsToRedeemList, Id id) {
        requireAllNonNull(pointsToRedeemList, id);
        this.pointsToRedeemList.addAll(pointsToRedeemList);
        this.idToRedeem = id;
        this.indexToRedeem = null;
    }

    /**
     * Constructs an RedeemCommand to add the specified {@code Member} by index.
     *
     * @param pointsToRedeemList the points of to redeemed list.
     * @param index the member index that needs to redeem point.
     */
    public RedeemCommand(List<Point> pointsToRedeemList, Index index) {
        requireAllNonNull(pointsToRedeemList, index);
        this.pointsToRedeemList.addAll(pointsToRedeemList);
        this.indexToRedeem = index;
        this.idToRedeem = null;
    }

    /**
     * Overrides and executes the model.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult related to redeem command.
     * @throws CommandException if the user input does not conform the expected format.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Member> lastShownList = model.getUpdatedMemberList();

        Member memberToEdit = null;
        if (indexToRedeem != null) {
            if (indexToRedeem.getZeroBased() < lastShownList.size()) {
                memberToEdit = lastShownList.get(indexToRedeem.getZeroBased());
            } else {
                throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
            }
        }
        if (idToRedeem != null) {
            memberToEdit = lastShownList.stream()
                    .filter(member -> idToRedeem.equals(member.getId())).findAny().orElse(null);
        }
        if (memberToEdit != null) {
            Member toRedeemPointsMember = createToRedeemPointsMember(memberToEdit, pointsToRedeemList);
            if (model.hasMember(toRedeemPointsMember, member -> member.getId() != toRedeemPointsMember.getId())) {
                throw new CommandException(MESSAGE_DUPLICATE_MEMBER);
            }
            model.setMember(memberToEdit, toRedeemPointsMember);
            model.updateFilteredMemberList(PREDICATE_SHOW_ALL_MEMBERS);
            return new CommandResult(String.format(MESSAGE_SUCCESS_REDEMPTION, toRedeemPointsMember));
        } else {
            throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_ID);
        }
    }

    /**
     * Creates and returns a {@code Member} with the details of {@code memberToEdit}
     * edited with {@code editMemberDescriptor}.
     *
     * @param memberToRedeemPoints creates the member who need to be redeemed points.
     * @param toRedeemPointsList the list of points need to redeem all.
     * @return Member with redeemed Points
     * @throws CommandException if the user input does not conform the expected format.
     */
    private static Member createToRedeemPointsMember(Member memberToRedeemPoints, List<Point> toRedeemPointsList)
            throws CommandException {
        assert memberToRedeemPoints != null;

        Id id = memberToRedeemPoints.getId();
        Name name = memberToRedeemPoints.getName();
        Phone phone = memberToRedeemPoints.getPhone();
        Email email = memberToRedeemPoints.getEmail();
        Address address = memberToRedeemPoints.getAddress();
        Timestamp timestamp = memberToRedeemPoints.getTimestamp();
        Set<Tag> tags = memberToRedeemPoints.getTags();
        List<Transaction> transactions = memberToRedeemPoints.getTransactions();
        List<Reservation> reservations = memberToRedeemPoints.getReservations();
        Credit credit = memberToRedeemPoints.getCredit();
        Point toRedeemPointsSum = new Point("" + Math.min(toRedeemPointsList.stream()
                .mapToInt(pointToUpdate -> (int) pointToUpdate.getDoubleValue()).sum(), Point.MAX));
        int oldPoint = memberToRedeemPoints.getPoint().getIntValue();
        int toRedeemPoint = toRedeemPointsSum.getIntValue();
        if (oldPoint < toRedeemPoint) {
            throw new CommandException(MESSAGE_INVALID_POINTS_LESS_THAN_ZERO);
        }
        Point updatePoint = new Point(String.valueOf(oldPoint - toRedeemPoint));
        return new Member(id, name, phone, email, address, timestamp, credit,
                updatePoint, transactions, reservations, tags);
    }
}
