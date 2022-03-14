package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.participant.Participant;

/**
 * An UI component that displays information of a {@code Participant}.
 */
public class ParticipantCard extends UiPart<Region> {

    private static final String FXML = "ParticipantListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Participant participant;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label birthDate;
    @FXML
    private Label nextOfKins;

    /**
     * Creates a {@code ParticipantCode} with the given {@code Participant} and index to display.
     */
    public ParticipantCard(Participant participant, int displayedIndex) {
        super(FXML);
        this.participant = participant;
        id.setText(displayedIndex + ". ");
        id.setWrapText(true);
        name.setText(participant.getFullName());
        name.setWrapText(true);
        phone.setText(participant.getPhoneValue());
        phone.setWrapText(true);
        address.setText(participant.getAddressValue());
        address.setWrapText(true);
        email.setText(participant.getEmailValue());
        email.setWrapText(true);
        birthDate.setText(participant.getBirthDateString());
        birthDate.setWrapText(true);
        nextOfKins.setText(participant.getNextOfKinsListString());
        nextOfKins.setWrapText(true);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ParticipantCard)) {
            return false;
        }

        // state check
        ParticipantCard card = (ParticipantCard) other;
        return id.getText().equals(card.id.getText())
                && participant.equals(card.participant);
    }
}
