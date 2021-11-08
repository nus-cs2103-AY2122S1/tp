package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.util.DateTimeUtil;
import seedu.address.model.member.Member;
import seedu.address.model.member.Tier;
import seedu.address.model.reservation.Reservation;

/**
 * A UI component that displays information of a {@code Member}.
 */
public class MemberCard extends UiPart<Region> {

    /**
     * Uses FXML to identify MemberListCard.
     */
    private static final String FXML = "MemberListCard.fxml";

    /**
     * Stands for current displaying member.
     *
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Member member;

    /**
     * Stands for components to be used in FXML.
     */
    @FXML
    private HBox cardPane;
    @FXML
    private Label index;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label credit;
    @FXML
    private Label point;
    @FXML
    private Label tier;
    @FXML
    private Label reservation;

    /**
     * Constructs a {@code MemberCode} with the given {@code Member} and index to display.
     */
    public MemberCard(Member member, int displayedIndex) {
        super(FXML);
        this.member = member;
        index.setText(displayedIndex + ". ");
        id.setText(member.getId().value);
        name.setText(member.getName().fullName);
        phone.setText(member.getPhone().value);
        email.setText(member.getEmail().value);
        credit.setText(member.getCredit().value);
        point.setText(member.getPoint().value);
        tier.setText(Tier.getTierByCredit(Integer.parseInt(member.getCredit().value)));
        tier.getStyleClass().add(Tier.getTierByCredit(Integer.parseInt(member.getCredit().value)).toLowerCase());
        member.getReservations().stream()
                .sorted(Comparator.comparing(reservation -> DateTimeUtil
                        .parseDateTime(reservation.getDateTime().value)))
                .filter(reservation -> Reservation.isValidDateTime(reservation.getDateTime()))
                .findAny().ifPresentOrElse(comingReservation -> reservation.setText(
                        comingReservation.getDateTime().value + " "
                        + comingReservation.getRemark().value), () -> reservation.setText(""));
    }

    /**
     * Overrides equals by comparing their text and member objects.
     *
     * @param other object.
     * @return true if equals, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MemberCard)) {
            return false;
        }

        // state check
        MemberCard card = (MemberCard) other;
        return index.getText().equals(card.index.getText())
                && member.equals(card.member);
    }
}
