package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a confirmation message
 */
public class ConfirmationWindow extends UiPart<Stage> {
    private static String message = "";

    private static final Logger logger = LogsCenter.getLogger(ConfirmationWindow.class);
    private static final String FXML = "ConfirmationWindow.fxml";

    @FXML
    private Button confirmationButton;

    @FXML
    private Label confirmationMessage;

    /**
     * Creates a new ConfirmationWindow.
     *
     * @param root Stage to use as the root of the ConfirmationWindow.
     */
    public ConfirmationWindow(Stage root) {
        super(FXML, root);
        confirmationMessage.setText(message);
    }

    /**
     * Creates a new ConfirmationWindow.
     */
    public ConfirmationWindow() {
        this(new Stage());
    }

    /**
     * Shows the confirmation window.
     */
    public void show() {
        logger.fine("Showing confirmation window about the clearing.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Obtains the ok button for confirmation.
     */
    public Button getConfirmationButton() {
        return this.confirmationButton;
    }

    /**
     * Creates confirmation message informing user that address book has or has
     * not been cleared.
     */
    public static void setConfirmationMessage(String messageInput) {
        message = messageInput;
    }

    /**
     * Closes the confirmation Window.
     */
    @FXML
    private void exitTheWindow() {
        this.hide();
    }
}

