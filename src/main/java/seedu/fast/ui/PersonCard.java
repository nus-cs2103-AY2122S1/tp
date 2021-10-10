package seedu.fast.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.fast.commons.util.Colors;
import seedu.fast.model.person.Appointment;
import seedu.fast.model.person.Person;

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
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private Label remark;
    @FXML
    private Label appointment;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        remark.setText(person.getRemark().value);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(colorSelector(tag.tagName)));

        // shows the appointment date if there is one, otherwise shows "No Appointment Scheduled"
        appointment.setText(appointmentToText());
    }

    /**
     * Creates a Label with the given tag name.
     * @param tagName The given tag name.
     * @return Label with the given tag name and custom color.
     */
    public Label colorSelector(String tagName) {
        Label temp = new Label(tagName);

        switch (tagName.toLowerCase()) { //Todo: add more color tags & abstract out case names

        case "friends":
            temp.setStyle(Colors.BLUE);
            break;
        default:
            temp.setStyle(Colors.GREY);
        }
        return temp;
    }

    /**
     * Formats the string representation of the appointment.
     *
     * The string will always contain the date detail of the appointment.
     * If the time detail is present, it will be added to the string.
     *
     * @return A string representation
     */
    private String appointmentToText() {
        String text = "Appointment: " + person.getAppointment().getDate();
        text += person.getAppointment().getTime().equals("") ? person.getAppointment().getTime()
                : " " + person.getAppointment().getTime() + "hrs";

        return text;
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
