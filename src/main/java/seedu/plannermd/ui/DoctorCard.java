package seedu.plannermd.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.plannermd.model.patient.Patient;

import java.util.Comparator;

public class DoctorCard extends UiPart<Region> {

    private static final String FXML = "DoctorListCard.fxml";

    public final Patient patient;

    @FXML
    private HBox cardPane;
    @FXML
    private VBox doctorDetailsBox;
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

    public DoctorCard(Patient patient, int displayedIndex) {
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
    }

    /**
     * Displays the remarks for a patient on the patient records. If there is no remarks, delete
     * the label that is supposed to contain the remark.
     *
     * @param patientRemark The remark for a particular patient.
     */
    private void setPatientRemark(String patientRemark) {
        if (patientRemark == null || patientRemark.equals("".trim())) {
            doctorDetailsBox.getChildren().remove(remark);
        } else {
            remark.setText("Remarks: " + patientRemark);
        }
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
        return id.getText().equals(card.getId().getText())
                && patient.equals(card.patient);
    }
}
