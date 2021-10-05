package seedu.plannermd.ui;

import javafx.fxml.FXML;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.plannermd.logic.Logic;


public class PersonTab extends UiPart<Region> {
    private static final String FXML = "PersonTab.fxml";

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
    private StackPane patientListPanelPlaceholder;

    @FXML
    private StackPane doctorListPanelPlaceholder;


    public PersonTab(Logic logic) {
        super(FXML);
        this.logic = logic;
        selectionModel = personTab.getSelectionModel();
        initialiseTabPanels();
    }

    private void initialiseTabPanels() {
        patientListPanel = new PatientListPanel(logic.getFilteredPatientList());
        patientListPanelPlaceholder.getChildren().add(patientListPanel.getRoot());

        doctorListPanel = new DoctorListPanel(logic.getFilteredPatientList());
        doctorListPanelPlaceholder.getChildren().add(doctorListPanel.getRoot());
    }

    public void switchToPatientTab() {
        selectionModel.select(PATIENT_TAB);
    }

    public void switchToDoctorTab() {
        selectionModel.select(DOCTOR_TAB);
    }
}
