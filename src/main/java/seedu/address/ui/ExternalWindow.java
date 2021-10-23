package seedu.address.ui;

import java.util.logging.Logger;

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

public class ExternalWindow extends UiPart<Stage> {
    protected static final String TEXT_COLOR = "424874";

    private static final Logger logger = LogsCenter.getLogger(ExternalWindow.class);

    /**
     * Creates an external window.
     *
     * @param fxmlFileName FXML file name to be used.
     * @param root Stage to be shown.
     */
    public ExternalWindow(String fxmlFileName, Stage root) {
        super(fxmlFileName, root);
        root.getScene().setFill(Color.web(TEXT_COLOR));
    }

    /**
     * Shows an external window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing an external window.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the external window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the external window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the external window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
