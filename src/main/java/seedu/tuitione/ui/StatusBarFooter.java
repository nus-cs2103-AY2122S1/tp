package seedu.tuitione.ui;

import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * A ui for the status bar that is displayed at the footer of the application.
 */
public class StatusBarFooter extends UiPart<Region> {

    private static final String FXML = "StatusBarFooter.fxml";
    private static final String INTRO_SAVE_DATA_LOCATION = "Data is saved at: \"%s\"";

    @FXML
    private Label saveLocationStatus;

    /**
     * Creates a {@code StatusBarFooter} with the given {@code Path}.
     */
    public StatusBarFooter(Path saveLocation) {
        super(FXML);
        String output = String.format(INTRO_SAVE_DATA_LOCATION, Paths.get(".").resolve(saveLocation));
        saveLocationStatus.setText(output);
    }

}
