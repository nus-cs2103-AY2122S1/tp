package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * Panel that displays the full information of a {@code Person}.
 */
public class PersonViewPanel extends UiPart<Region> {

    private static final String FXML = "PersonViewPanel.fxml";

    @FXML
    private VBox clientInfoContainer;

    @FXML
    private Label clientName;

    @FXML
    private Label clientId;

    @FXML
    private Label clientCreationDate;

    @FXML
    private Label clientLastMet1;

    @FXML
    private Label clientLastMet;

    @FXML
    private Label clientRiskAppetite;

    @FXML
    private Label clientCurrentPlans;

    @FXML
    private Label clientDisposableIncome;

    @FXML
    private Label nextMeeting;

    @FXML
    private VBox contactInfoContainer;

    @FXML
    private Label clientEmail;

    @FXML
    private Label clientPhoneNumber;

    public PersonViewPanel() {
        super(FXML);
    }
}

