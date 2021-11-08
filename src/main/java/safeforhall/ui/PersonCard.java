package safeforhall.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import safeforhall.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {
    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label room;
    @FXML
    private Label id;
    @FXML
    private Rectangle status;
    @FXML
    private HBox informationContainer;
    @FXML
    private VBox statusContainer;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        room.setText(person.getRoom().toString());

        if (person.hasMissedDeadline()) {
            this.getRoot().setStyle("-fx-border-color: derive(#FF0000, 70%); -fx-border-width: 1 1 1 7;");
        }

        if (person.getVaccStatus().vaccinated) {
            Image img = new Image("/images/vaccinated.png");
            Rectangle rec = new Rectangle(30, 30);
            rec.setArcHeight(10);
            rec.setArcWidth(10);
            rec.setFill(new ImagePattern(img));
            statusContainer.getChildren().add(rec);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
