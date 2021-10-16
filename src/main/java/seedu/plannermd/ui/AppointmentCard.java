package seedu.plannermd.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.plannermd.model.appointment.Appointment;
import seedu.plannermd.model.appointment.Session;

/**
 * An UI component that displays information of a {@code Appointment}.
 */
public class AppointmentCard extends UiPart<Region> {

    private static final String FXML = "AppointmentListCard.fxml";

    private static final DateTimeFormatter displayFormatter =
            DateTimeFormatter.ofPattern("dd MMM yy, E");

    public final Appointment appointment;

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
     * Creates a {@code AppointmentCard} with the given {@code Appointment} and index to display.
     */
    public AppointmentCard(Appointment appointment, int displayedIndex) {
        super(FXML);
        this.appointment = appointment;
        id.setText(displayedIndex + ". ");
        apptDate.setText(formatApptDate(appointment.getAppointmentDate().date));
        apptTime.setText(formatApptTime(appointment.getSession()));
        doctorName.setText("Doctor: Dr " + appointment.getDoctor().getName().fullName);
        patientName.setText("Patient: " + appointment.getPatient().getName().fullName);
        setAppointmentRemark(appointment.getRemark().value);
    }

    private void setAppointmentRemark(String apptRemark) {
        if (apptRemark == null || apptRemark.equals("".trim())) {
            detailsBox.getChildren().remove(remarks);
        } else {
            remarks.setText("Remarks: " + apptRemark);
        }
    }

    private String formatApptDate(LocalDate date) {
        return displayFormatter.format(date);
    }

    private String formatApptTime(Session session) {
        return session.getFormattedStartTime() + " - " + session.getFormattedEndTime();
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
