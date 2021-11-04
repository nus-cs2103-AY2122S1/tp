package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a download page
 */
public class DownloadWindow extends UiPart<Stage> {

    public static final String DOWNLOAD_MESSAGE_SUCCESS = "Data has been successfully downloaded!";
    public static final String DOWNLOAD_MESSAGE_FAILURE = "There has been an error while downloading, check that "
            + "addressbook.json is present.";

    private static final Logger logger = LogsCenter.getLogger(DownloadWindow.class);
    private static final String FXML = "DownloadWindow.fxml";

    @FXML
    private Label downloadMessage;

    /**
     * Creates a new DownloadWindow.
     *
     * @param root Stage to use as the root of the DownloadWindow.
     */
    public DownloadWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new DownloadWindow.
     */
    public DownloadWindow(boolean success) {
        this(new Stage());
        if (success) {
            downloadMessage.setText(DOWNLOAD_MESSAGE_SUCCESS);
        } else {
            downloadMessage.setText(DOWNLOAD_MESSAGE_FAILURE);
        }
    }

    /**
     * Shows the download window.
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
        logger.fine("Showing download page.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the download window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the download window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the download window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
