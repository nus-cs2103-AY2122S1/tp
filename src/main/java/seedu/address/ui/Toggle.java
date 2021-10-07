package seedu.address.ui;


import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Region;

/**
 * A ui for the toggle buttons to toggle between clients and tasks.
 */
public class Toggle extends UiPart<Region> {

    private static final String FXML = "Toggle.fxml";

    @FXML
    private ToggleButton clients;
    @FXML
    private ToggleButton tasks;

    public Toggle() {
        super(FXML);
    }
    
}
