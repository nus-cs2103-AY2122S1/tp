package seedu.plannermd.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.plannermd.model.person.Person;

/**
 * An UI component that is capable of displaying information of a {@code Person}
 * type.
 */
public abstract class PersonCard extends UiPart<Region> {

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved
     * keywords in JavaFX. As a consequence, UI elements' variable names cannot be
     * set to such keywords or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The
     *      issue on PlannerMd level 4</a>
     */
    @FXML
    private HBox cardPane;
    @FXML
    private VBox detailsBox;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label dateOfBirth;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label birthDate;
    @FXML
    private Label remark;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCard} with the given {@code Person} and index to
     * display.
     */
    public PersonCard(String fxml, Person person, int displayedIndex) {
        super(fxml);
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        // To be updated when the DOB field in the Person class has been implemented.
        dateOfBirth.setText("28/02/1999 (Age: 22)");
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        birthDate.setText(person.getBirthDate().value + " (Age: " + person.getBirthDate().calculateAge() + ")");
        setRemark(person.getRemark().value);
        person.getTags().stream().sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    /**
     * Displays the remarks for a patient on the patient records. If there is no
     * remarks, delete the label that is supposed to contain the remark.
     *
     * @param personRemark The remark for a particular patient.
     */
    private void setRemark(String personRemark) {
        if (personRemark == null || personRemark.equals("".trim())) {
            detailsBox.getChildren().remove(remark);
        } else {
            remark.setText("Remarks: " + personRemark);
        }
    }

    protected Label getId() {
        return id;
    }
}
