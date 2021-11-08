package seedu.address.ui;

import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import seedu.address.commons.status.LoginStatus;

/**
 * A ui for the status bar that is displayed at the footer of the application.
 */
public class StatusBarFooter extends UiPart<Region> {

    /**
     * Uses FXML to identify StatusBarFooter.
     */
    private static final String FXML = "StatusBarFooter.fxml";

    /**
     * Stands for components to be used in FXML.
     */
    @FXML
    private Label saveLocationStatus;

    @FXML
    private Label loginStatus;


    /**
     * Creates a StatusBarFooter to display.
     *
     * @param saveLocation current location of saved file.
     */
    public StatusBarFooter(Path saveLocation) {
        super(FXML);
        saveLocationStatus.setText(Paths.get(".").resolve(saveLocation).toString());
        loginStatus.textProperty().bind(LoginStatus.CURRENT_STATUS);
        GridPane.setHalignment(loginStatus, HPos.RIGHT);
    }

}
