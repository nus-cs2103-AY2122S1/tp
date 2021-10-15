package seedu.plannermd.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.plannermd.model.patient.Patient;

public class AppointmentListPanel extends UiPart<Region> {
    private static final String FXML = "AppointmentListPanel.fxml";
    //private final Logger logger = LogsCenter.getLogger(PatientListPanel.class);

    @FXML
    private ListView<Patient> appointmentListView;

    /**
     * Creates a {@code PatientListPanel} with the given {@code ObservableList}.
     */
    public AppointmentListPanel(ObservableList<Patient> patientList) {
        super(FXML);
        appointmentListView.setItems(patientList);
        appointmentListView.setCellFactory(listView -> new AppointmentListPanel.AppointmentListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Patient} using
     * a {@code PatientCard}.
     */
    class AppointmentListViewCell extends ListCell<Patient> {
        @Override
        protected void updateItem(Patient patient, boolean empty) {
            super.updateItem(patient, empty);

            if (empty || patient == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new AppointmentCard(patient, getIndex() + 1).getRoot());
            }
        }
    }

}
