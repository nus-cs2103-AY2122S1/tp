package seedu.docit.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.docit.model.appointment.Appointment;
import seedu.docit.model.patient.Patient;

/**
 * An UI component that displays information of an {@code Appointment}.
 */
public class AppointmentCard extends UiPart<Region> {

    private static final String FXML = "AppointmentListCard.fxml";
    private static final String PHONE_ICON = "\uD83D\uDCDE\t";
    private static final String DATE_ICON = "\uD83D\uDCC5\t";
    private static final String TIME_ICON = "\u0000\u23f0\t";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Appointment appointment;

    @FXML
    private HBox apptCardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label date;
    @FXML
    private Label time;
    @FXML
    private Label prescription;
    @FXML
    private VBox prescriptionContainer;
    @FXML
    private FlowPane prescriptions;
    @FXML
    private Label isToday;

    /**
     * Creates a {@code AppointmentCode} with the given {@code Appointment} and index to display.
     */
    public AppointmentCard(Appointment appointment, int displayedIndex) {
        super(FXML);

        this.appointment = appointment;
        Patient patient = appointment.getPatient();
        id.setText(displayedIndex + ". ");
        name.setText(patient.getName().fullName);
        phone.setText(PHONE_ICON + patient.getPhone().value);
        date.setText(DATE_ICON + appointment.getFormattedDateString());
        time.setText(TIME_ICON + appointment.getFormattedTimeString());

        if (appointment.getPrescriptions().size() == 0) {
            prescriptionContainer.setVisible(false);
            prescription.setVisible(false);
        }

        appointment.getPrescriptions().stream()
               .sorted(Comparator.comparing(presctn -> presctn.getMedicine()))
               .forEach(presctn -> prescriptions.getChildren().add(new Label(presctn.toUiFormat())));

        isToday.setVisible(appointment.isToday());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AppointmentCard)) {
            return false;
        }

        // state check
        AppointmentCard card = (AppointmentCard) other;
        return id.getText().equals(card.id.getText())
                && appointment.equals(card.appointment);
    }
}
