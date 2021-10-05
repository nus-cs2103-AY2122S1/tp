package seedu.plannermd.ui;

import seedu.plannermd.model.doctor.Doctor;

/**
 * A UI component that displays information of a {@code Doctor}.
 */
public class DoctorCard extends PersonCard {

    private static final String FXML = "DoctorListCard.fxml";

    public final Doctor doctor;

    /**
     * Creates a {@code DoctorCard} with the given {@code Doctor} and index to display.
     */
    public DoctorCard(Doctor doctor, int displayedIndex) {
        super(FXML, doctor, displayedIndex);
        this.doctor = doctor;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DoctorCard)) {
            return false;
        }

        // state check
        DoctorCard card = (DoctorCard) other;
        return super.getId().getText().equals(card.getId().getText())
                && doctor.equals(card.doctor);
    }
}
