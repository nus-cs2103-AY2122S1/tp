package seedu.plannermd.ui;

import javafx.fxml.FXML;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.plannermd.logic.Logic;

/**
 * A UI that holds the two tabs for patient and doctor records in the plannermd.
 */
public class PersonTab extends UiPart<Region> {

    private static final String FXML = "PersonTab.fxml";

    /** The order of the tabs in the tab pane. */
    private static final int PATIENT_TAB = 0;
    private static final int DOCTOR_TAB = 1;

    private Logic logic;
    private final SingleSelectionModel<Tab> selectionModel;

    // Independent Ui parts residing in this Ui container
    private PatientListPanel patientListPanel;
    private DoctorListPanel doctorListPanel;

    @FXML
    private TabPane personTab;

    @FXML
    private Tab patientsTab;

    @FXML
    private Tab doctorsTab;

    @FXML
    private StackPane patientListPanelPlaceholder;

    @FXML
    private StackPane doctorListPanelPlaceholder;

    /** Creates a new PersonTab Pane and initialises them. */
    public PersonTab(Logic logic) {
        super(FXML);
        this.logic = logic;
        selectionModel = personTab.getSelectionModel();
        initialiseTabPanels();

        // Patients tab is shown by default on start up
        doctorsTab.setDisable(true);
    }

    private void initialiseTabPanels() {
        patientListPanel = new PatientListPanel(logic.getFilteredPatientList());
        patientListPanelPlaceholder.getChildren().add(patientListPanel.getRoot());

        doctorListPanel = new DoctorListPanel(logic.getFilteredDoctorList());
        doctorListPanelPlaceholder.getChildren().add(doctorListPanel.getRoot());
    }

    /**
     * Switch the UI view to the tab containing the patient's records.
     */
    public void setTabToPatient() {
        selectionModel.select(PATIENT_TAB);
        patientsTab.setDisable(false);
        doctorsTab.setDisable(true);
    }

    /**
     * Switch the UI view to the tab containing the doctor's records.
     */
    public void setTabToDoctor() {
        selectionModel.select(DOCTOR_TAB);
        doctorsTab.setDisable(false);
        patientsTab.setDisable(true);
    }
}
