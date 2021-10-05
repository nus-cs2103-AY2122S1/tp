package seedu.plannermd.ui;

import seedu.plannermd.model.patient.Patient;

/**
 * A UI component that displays information of a {@code Doctor}.
 */
public class DoctorCard extends PersonCard {

    private static final String FXML = "DoctorListCard.fxml";

    public final Patient patient;

    /**
     * Creates a {@code DoctorCard} with the given {@code Doctor} and index to display.
     */
    public DoctorCard(Patient patient, int displayedIndex) {
        super(FXML, patient, displayedIndex);
        this.patient = patient;
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
