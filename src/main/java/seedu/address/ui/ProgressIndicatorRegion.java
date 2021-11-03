package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Region;

/**
 * A ui for the status bar that is displayed at the footer of the application.
 */
public class ProgressIndicatorRegion extends UiPart<Region> {

    private static final String FXML = "ProgressIndicator.fxml";

    @FXML
    private ProgressBar progressBar;

    /**
     * Creates a {@code StatusBarFooter} with the given {@code Path}.
     */
    public ProgressIndicatorRegion() {
        super(FXML);
        this.getRoot().setVisible(false);
    }
}
