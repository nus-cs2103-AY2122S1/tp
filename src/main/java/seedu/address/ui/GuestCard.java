package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.guest.Guest;

/**
 * An UI component that displays information of a {@code Guest}.
 */
public class GuestCard extends UiPart<Region> {

    private static final String FXML = "GuestCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Guest guest;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private Label roomNumber;
    @FXML
    private Label passportNumber;
    @FXML
    private ImageView roomNumberIcon;
    @FXML
    private ImageView emailIcon;
    @FXML
    private ImageView passportNumberIcon;

    /**
     * Creates a {@code GuestCode} with the given {@code Guest} and index to display.
     */
    public GuestCard(Guest guest) {
        super(FXML);
        this.guest = guest;
        passportNumber.setText("[" + guest.getPassportNumber().value + "]");
        name.setText(guest.getName().fullName);
        email.setText(" " + guest.getEmail().value);
        roomNumber.setText(" " + guest.getRoomNumber().value);

        passportNumberIcon.setImage(new Image(getClass().getResourceAsStream("/images/passport_number_icon.png")));
        emailIcon.setImage(new Image(getClass().getResourceAsStream("/images/email_icon.png")));
        roomNumberIcon.setImage(new Image(getClass().getResourceAsStream("/images/room_number_icon.png")));

        guest.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GuestCard)) {
            return false;
        }

        // state check
        GuestCard card = (GuestCard) other;
        return passportNumber.getText().equals(card.passportNumber.getText())
                && guest.equals(card.guest);
    }
}
