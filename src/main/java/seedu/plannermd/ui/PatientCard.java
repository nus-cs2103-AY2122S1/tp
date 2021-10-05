package seedu.plannermd.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import seedu.plannermd.model.patient.Patient;
import seedu.plannermd.model.patient.Risk;

/**
 * An UI component that displays information of a {@code Patient}.
 */
public class PatientCard extends PersonCard {

    private static final String FXML = "PatientListCard.fxml";

    public final Patient patient;

    @FXML
    private FlowPane risk;

    /**
     * Creates a {@code PatientCard} with the given {@code Patient} and index to display.
     */
    public PatientCard(Patient patient, int displayedIndex) {
        super(FXML, patient, displayedIndex);
        this.patient = patient;
        setRisk(patient.getRisk());
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
        return super.getId().getText().equals(card.getId().getText())
                && patient.equals(card.patient);
    }

}
