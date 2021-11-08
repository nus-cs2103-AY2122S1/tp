package seedu.address.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.util.DateTimeUtil;
import seedu.address.model.member.Member;
import seedu.address.model.member.Tier;
import seedu.address.model.reservation.Reservation;
import seedu.address.model.transaction.Transaction;

/**
 * A UI component that displays information of a {@code Member}.
 */
public class MemberDetailsCard extends UiPart<Region> {

    /**
     * Uses FXML to identify MemberDetailsCard.
     */
    private static final String FXML = "MemberDetailsCard.fxml";

    /**
     * Stands for current displaying member.
     */

    public final Member member;

    /**
     * Stands for Components to be used in FXML.
     *
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */
    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label address;
    @FXML
    private Label registrationTime;
    @FXML
    private Label credit;
    @FXML
    private Label point;
    @FXML
    private Label tier;
    @FXML
    private StackPane reservationListPanelPlaceholder;
    @FXML
    private StackPane transactionListPanelPlaceholder;

    /**
     * Creates a {@code MemberCode} with the given {@code members} to display.
     */
    public MemberDetailsCard(Member member) {
        super(FXML);
        this.member = member;
        id.setText(member.getId().value);
        name.setText(member.getName().fullName);
        phone.setText(member.getPhone().value);
        email.setText(member.getEmail().value);
        address.setText(member.getAddress().value);
        registrationTime.setText(DateTimeUtil.timestampToDate(Long.parseLong(member.getTimestamp().value)).toString());
        credit.setText(member.getCredit().value);
        point.setText(member.getPoint().value);
        tier.setText(Tier.getTierByCredit(Integer.parseInt(member.getCredit().value)));
        tier.getStyleClass().add(Tier.getTierByCredit(Integer.parseInt(member.getCredit().value)).toLowerCase());

        ObservableList<Reservation> internalReservationList = FXCollections.observableArrayList();
        internalReservationList.addAll(member.getReservations());
        ReservationListPanel reservationListPanel = new ReservationListPanel(internalReservationList);
        reservationListPanelPlaceholder.getChildren().add(reservationListPanel.getRoot());

        ObservableList<Transaction> internalTransactionList = FXCollections.observableArrayList();
        internalTransactionList.addAll(member.getTransactions());
        TransactionListPanel transactionListPanel = new TransactionListPanel(internalTransactionList);
        transactionListPanelPlaceholder.getChildren().add(transactionListPanel.getRoot());
    }

    /**
     * Overrides equals by comparing their member objects.
     *
     * @param other object.
     * @return true if contains the same member, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MemberDetailsCard)) {
            return false;
        }

        // state check
        MemberDetailsCard card = (MemberDetailsCard) other;
        return member.equals(card.member);
    }
}
