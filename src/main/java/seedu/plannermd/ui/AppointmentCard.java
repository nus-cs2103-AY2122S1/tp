package seedu.plannermd.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.plannermd.model.patient.Patient;

/**
 * An UI component that displays information of a {@code Patient}.
 */
public class AppointmentCard extends UiPart<Region> {

    private static final String FXML = "AppointmentListCard.fxml";

    public final Patient patient;

    @FXML
    private HBox cardPane;
    @FXML
    private VBox detailsBox;
    @FXML
    private Label id;
    @FXML
    private Label apptDate;
    @FXML
    private Label apptTime;
    @FXML
    private Label doctorName;
    @FXML
    private Label patientName;;
    @FXML
    private Label remarks;

    /**
     * Creates a {@code PatientCard} with the given {@code Patient} and index to display.
     */
    public AppointmentCard(Patient patient, int displayedIndex) {
        super(FXML);
        this.patient = patient;
        id.setText(displayedIndex + ". ");
        apptDate.setText("20 Oct 2021, Friday");
        apptTime.setText("Time: 21:00 - 21:15");
        doctorName.setText("Doctor: Dr Strange");
        patientName.setText("Patient: Alex Yeoh");
        setAppointmentRemark(patient.getRemark().value);
    }

    private void setAppointmentRemark(String apptRemark) {
        if (apptRemark == null || apptRemark.equals("".trim())) {
            detailsBox.getChildren().remove(remarks);
        } else {
            remarks.setText("Remarks: " + apptRemark);
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
