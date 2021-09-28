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
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on PlannerMd level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private VBox personDetailsBox;
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
    private Label remark;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane risk;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        // To be updated when the DOB field in the Person class has been implemented.
        dateOfBirth.setText("28/02/1999 (Age: 22)");
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        setPatientRemark("Some random remark");
        // To be updated when the risk field in a patient has been implemented.
        setPatientRiskTag("Low");
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    /**
     * Displays the remarks for a patient on the patient records. If there is no remarks, delete
     * the label that is supposed to contain the remark.
     *
     * @param patientRemark The remark for a particular patient.
     */
    private void setPatientRemark(String patientRemark) {
        if (patientRemark == null) {
            personDetailsBox.getChildren().remove(remark);
        } else {
            remark.setText("Remarks: " + patientRemark);
        }
    }

    private void setPatientRiskTag(String patientRisk) {
        Label riskLabel = new Label(patientRisk);
        switch (patientRisk) {
        case "High":
            riskLabel.setStyle("-fx-background-color: red");
            break;
        case "Medium":
            riskLabel.setStyle("-fx-background-color: #fcba03");
            break;
        case "Low":
            riskLabel.setStyle("-fx-background-color: green");
            break;
        default:
            break;
        }
        risk.getChildren().add(riskLabel);
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
