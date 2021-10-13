package safeforhall.ui;

import static safeforhall.model.person.LastDate.DEFAULT_DATE;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import safeforhall.model.person.Email;
import safeforhall.model.person.Faculty;
import safeforhall.model.person.LastDate;
import safeforhall.model.person.Person;
import safeforhall.model.person.Phone;
import safeforhall.model.person.Room;

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
    private Label id;
    @FXML
    private Label room;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label faculty;
    @FXML
    private Rectangle status;
    @FXML
    private HBox informationContainer;
    @FXML
    private VBox deadlineContainer;
    @FXML
    private VBox statusContainer;
    @FXML
    private VBox labelBox;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        room.setText(Room.DESC + person.getRoom().room);
        phone.setText(Phone.DESC + person.getPhone().value);
        email.setText(Email.DESC + person.getEmail().value);
        faculty.setText(Faculty.DESC + person.getFaculty().faculty);

        if (person.getLastFetDate().date != DEFAULT_DATE) {
            Label textBox = new Label(LastDate.FET_DESC + person.getLastFetDate().date);
            textBox.setFont(new Font(3));
            labelBox.getChildren().add(textBox);
        }

        if (person.getLastCollectionDate().date != DEFAULT_DATE) {
            Label textBox = new Label(LastDate.COLLECTION_DESC + person.getLastCollectionDate().date);
            textBox.setFont(new Font(3));
            labelBox.getChildren().add(textBox);
        }

        if (person.hasMissedDeadline()) {
            this.getRoot().setStyle("-fx-background-color: #8B0000;");
            Label textBox = new Label("Late by:");
            int missedDates = person.getMissedDates();
            Label date;
            if (missedDates > 1) {
                date = new Label(missedDates + " days");
            } else {
                date = new Label(missedDates + " day");
            }
            deadlineContainer.getChildren().add(textBox);
            deadlineContainer.getChildren().add(date);
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
