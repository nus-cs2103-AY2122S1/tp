package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonDetails extends UiPart<Region> {

    private static final String FXML = "PersonDetails.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private Person person;

    @FXML
    private VBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label telegram;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private ImageView profileView;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonDetails(Person person) {
        super(FXML);
        this.setPerson(person);
    }

    public void setPerson(Person person) {
        this.person = person;
        if (person == null) {
            cardPane.setVisible(false);
            return;
        }
        cardPane.setVisible(true);
        name.setText(person.getName().fullName);
        telegram.setText("@" + person.getTelegram().value);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        Rectangle clip = new Rectangle(
                profileView.getFitWidth(), profileView.getFitHeight()
        );
        clip.setArcWidth(profileView.getFitWidth() / 2);
        clip.setArcHeight(profileView.getFitHeight() / 2);
        profileView.setClip(clip);

        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = profileView.snapshot(parameters, null);
        profileView.setClip(null);
        profileView.setEffect(new DropShadow(20, Color.BLACK));
        profileView.setImage(image);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonDetails)) {
            return false;
        }

        // state check
        PersonDetails card = (PersonDetails) other;
        return person.equals(card.person);
    }
}
