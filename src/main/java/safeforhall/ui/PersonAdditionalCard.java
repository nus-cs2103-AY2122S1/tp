package safeforhall.ui;

import static safeforhall.model.person.LastDate.DEFAULT_DATE;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import safeforhall.model.person.Email;
import safeforhall.model.person.Faculty;
import safeforhall.model.person.LastDate;
import safeforhall.model.person.Person;
import safeforhall.model.person.Phone;
import safeforhall.model.person.Room;
import safeforhall.model.person.VaccStatus;

public class PersonAdditionalCard extends UiPart<Region> {

    private static final String FXML = "PersonAdditionalCard.fxml";

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label room;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label vaccStatus;
    @FXML
    private Label faculty;
    @FXML
    private HBox informationContainer;
    @FXML
    private VBox deadlineContainer;
    @FXML
    private VBox labelBox;
    @FXML
    private VBox labelBoxInterior;

    /**
     * Creates a {@code PersonAdditionalCard} with the given {@code Member} and index to display.
     */
    public PersonAdditionalCard(Person person) {
        super(FXML);
        this.person = person;
        name.setText(person.getName().fullName);
        room.setText(Room.DESC + person.getRoom().room);
        phone.setText(Phone.DESC + person.getPhone().value);
        email.setText(Email.DESC + person.getEmail().value);
        faculty.setText(Faculty.DESC + person.getFaculty().faculty);
        vaccStatus.setText(VaccStatus.DESC + person.getVaccStatus().vaccStatus);

        if (person.hasMissedDeadline()) {
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

        if (person.getLastFetDate().date != DEFAULT_DATE) {
            Label textBox = new Label(LastDate.FET_DESC + person.getLastFetDate().date);
            textBox.getStyleClass().add("cell_small_label");
            labelBoxInterior.getChildren().add(textBox);
        }

        if (person.getLastCollectionDate().date != DEFAULT_DATE) {
            Label textBox = new Label(LastDate.COLLECTION_DESC + person.getLastCollectionDate().date);
            textBox.getStyleClass().add("cell_small_label");
            labelBoxInterior.getChildren().add(textBox);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonAdditionalCard)) {
            return false;
        }

        // state check
        PersonAdditionalCard card = (PersonAdditionalCard) other;
        return person.equals(card.person);
    }

}
