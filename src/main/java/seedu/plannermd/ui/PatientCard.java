package seedu.plannermd.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.plannermd.model.patient.Patient;
import seedu.plannermd.model.patient.Risk;

/**
 * An UI component that displays information of a {@code Patient}.
 */
public class PatientCard extends UiPart<Region> {

    private static final String FXML = "PatientListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on PlannerMd level 4</a>
     */

    public final Patient patient;

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
     * Creates a {@code PatientCode} with the given {@code Patient} and index to display.
     */
    public PatientCard(Patient patient, int displayedIndex) {
        super(FXML);
        this.patient = patient;
        id.setText(displayedIndex + ". ");
        name.setText(patient.getName().fullName);
        phone.setText(patient.getPhone().value);
        // To be updated when the DOB field in the Person class has been implemented.
        dateOfBirth.setText("28/02/1999 (Age: 22)");
        address.setText(patient.getAddress().value);
        email.setText(patient.getEmail().value);
        setPatientRemark("Some random remark");
        patient.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        setRisk(patient.getRisk());
    }

    /**
     * Displays the remarks for a patient on the patient records. If there is no remarks, delete
     * the label that is supposed to contain the remark.
     *
     * @param patientRemark The remark for a particular patient.
     */
    private void setPatientRemark(String patientRemark) {
        if (patientRemark == null || patientRemark.equals("".trim())) {
            personDetailsBox.getChildren().remove(remark);
        } else {
            remark.setText("Remarks: " + patientRemark);
        }
    }

    private void setRisk(Risk risk) {
        Label riskLabel = new Label(risk.toString());
        switch (risk.riskLevel) {
        case HIGH:
            riskLabel.setStyle("-fx-background-color: red");
            break;
        case MEDIUM:
            riskLabel.setStyle("-fx-background-color: #fcba03");
            break;
        case LOW:
            riskLabel.setStyle("-fx-background-color: green");
            break;
        default:
            // unclassified risk
            return;
        }
        this.risk.getChildren().add(riskLabel);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PatientCard)) {
            return false;
        }

        // state check
        PatientCard card = (PatientCard) other;
        return id.getText().equals(card.id.getText())
                && patient.equals(card.patient);
    }
}
