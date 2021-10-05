package seedu.plannermd.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.plannermd.commons.core.LogsCenter;
import seedu.plannermd.model.patient.Patient;

import java.util.logging.Logger;

public class DoctorListPanel extends UiPart<Region> {

    private static final String FXML = "DoctorListPanel.fxml";
    //private final Logger logger = LogsCenter.getLogger(PatientListPanel.class);

    @FXML
    private ListView<Patient> doctorListView;

    /**
     * Creates a {@code PatientListPanel} with the given {@code ObservableList}.
     */
    public DoctorListPanel(ObservableList<Patient> patientList) {
        super(FXML);
        doctorListView.setItems(patientList);
        doctorListView.setCellFactory(listView -> new DoctorListPanel.DoctorListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PatientCard}.
     */
    class DoctorListViewCell extends ListCell<Patient> {
        @Override
        protected void updateItem(Patient patient, boolean empty) {
            super.updateItem(patient, empty);

            if (empty || patient == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DoctorCard(patient, getIndex() + 1).getRoot());
            }
        }
    }
}
