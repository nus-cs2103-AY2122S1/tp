package seedu.address.ui;

import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * A ui for the status bar that is displayed at the footer of the application.
 */
public class StatusBarFooter extends UiPart<Region> {

    private static final String FXML = "StatusBarFooter.fxml";

    @FXML
    private Label saveLocationStatus;

    /**
     * Creates a {@code StatusBarFooter} with the given {@code Path}.
     */
    public StatusBarFooter(ObservableValue<Path> saveLocation) {
        super(FXML);
        saveLocationStatus.setText(formatFilePath(saveLocation.getValue()));
        saveLocation.addListener((observable, oldValue, newValue) ->
                saveLocationStatus.setText(formatFilePath(saveLocation.getValue())));
    }

    private String formatFilePath(Path filePath) {
        return Paths.get(".").resolve(filePath).toString();
    }
}
