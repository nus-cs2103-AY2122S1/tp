package donnafin.ui;

import donnafin.logic.InvalidFieldException;
import donnafin.logic.PersonAdapter;
import donnafin.model.person.Attribute;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ClientInfoPanel extends UiPart<Region> {
    private static final String FXML = "ClientInfoPanel.fxml";
    private final PersonAdapter personAdapter;

    @FXML
    private Button personalInformation;

    @FXML
    private Button policyInformation;

    @FXML
    private Button assets;

    @FXML
    private Button liabilities;

    @FXML
    private Button notes;

    @FXML
    private VBox clientInfoList;

    @FXML
    private VBox financialInfoTab;

    @FXML
    private TextArea notesTextArea;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public ClientInfoPanel(PersonAdapter personAdapter) {
        super(FXML);
        this.personAdapter = personAdapter;

        personAdapter.getAllAttributesList().stream()
                .map(attr -> createAttributePanel(attr).getRoot())
                .forEach(y -> clientInfoList.getChildren().add(y));
    }

    private AttributePanel createAttributePanel(Attribute attr) {
        String fieldInString = attr.getClass().getSimpleName();
        return new AttributePanel(
                fieldInString,
                attr.toString(),
                createEditHandler(getPersonPersonalField(fieldInString))
        );
    }

    /** Gets the PersonField enum type of attribute from label */
    private PersonAdapter.PersonField getPersonPersonalField(String fieldInString) {
        switch(fieldInString) {
        case "Name":
            return PersonAdapter.PersonField.NAME;
        case "Address":
            return PersonAdapter.PersonField.ADDRESS;
        case "Phone":
            return PersonAdapter.PersonField.PHONE;
        case "Email":
            return PersonAdapter.PersonField.EMAIL;
        default:
            throw new IllegalArgumentException("Unexpected Person Field used");
        }
    }

    private AttributePanel.EditHandler createEditHandler(PersonAdapter.PersonField field) {
        return newValue -> {
            try {
                this.personAdapter.edit(field, newValue);
                return null;
            } catch (InvalidFieldException e) {
                return e.getMessage();
            }
        };
    }

    private void refresh() {
        clientInfoList.getChildren().clear();
    }


    /**
     * Updates the VBox content to the Client's personal Details
     */
    public void changeTabToPersonal() {
        refresh();
        personAdapter.getAllAttributesList().stream()
                .map(attr -> createAttributePanel(attr).getRoot())
                .forEach(y -> clientInfoList.getChildren().add(y));
    }

    /**
     * Updates the VBox content to the Client's policy Details
     */
    public void changeTabToPolicy() {
        refresh();
    }

    /**
     * Updates the VBox content to the Client's Asset Details
     */
    public void changeTabToAssets() {
        refresh();
    }

    /**
     * Updates the VBox content to the Client's Liabilities Details
     */
    public void changeTabToLiabilities() {
        refresh();
    }
}
