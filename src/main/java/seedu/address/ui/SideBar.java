package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.person.Person;

/**
 * An UI that acts as the side bar of the application, housing panels for easy viewing.
 */
public class SideBar extends UiPart<Region> {

    private static final String FXML = "SideBar.fxml";

    private PersonViewPanel personViewPanel;

    @FXML
    private StackPane personViewPanelPlaceholder;

    /**
     * Creates a {@code SideBar} with panels initiated.
     */
    public SideBar() {
        super(FXML);

        personViewPanel = new PersonViewPanel();
        personViewPanelPlaceholder.getChildren().add(personViewPanel.getRoot());
    }

    public void updatePersonViewPanel(Person personToView) {
        personViewPanel.setClientInfo(personToView);
    }
}
