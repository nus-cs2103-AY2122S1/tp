package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a total orders page
 */
public class TotalOrdersWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(TotalOrdersWindow.class);
    // TODO change to TotalOrdersWindow.fxml when ready
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    /**
     * Creates a new TotalOrdersWindow.
     *
     * @param root Stage to use as the root of the TotalOrdersWindow.
     */
    public TotalOrdersWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new TotalOrdersWindow.
     */
    public TotalOrdersWindow() {
        this(new Stage());
    }

    /**
     * Shows the total orders window.
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
        logger.fine("Showing total orders.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the total orders window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the total orders window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the total orders window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Dummy method to pass the compilation.
     * TODO remove this method after TotalOrdersWindow.fxml is ready
     */
    @FXML
    private void copyUrl() {
        ;
    }
}
