package seedu.siasa.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import seedu.siasa.commons.core.LogsCenter;

public class WarningWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(WarningWindow.class);
    private static final String FXML = "WarningWindow.fxml";
    private static final String MESSAGE_ASK_CONFIRMATION = " Do you want to continue?";
    private static boolean isConfirmingCommand;

    @FXML
    private Button yesButton;

    @FXML
    private Button noButton;

    @FXML
    private Label warningMessage;

    /**
     * Creates a new warning window.
     *
     * @param root Stage to use as the root for the warning window.
     * @param warning The warning message to be displayed.
     */
    public WarningWindow(Stage root, String warning) {
        super(FXML, root);
        warningMessage.setText(warning + MESSAGE_ASK_CONFIRMATION);
    }

    /**
     * Displays the warning window to the user.
     */
    public void display() {
        logger.fine("Showing warning dialog");
        getRoot().initModality(Modality.APPLICATION_MODAL);
        getRoot().showAndWait();
        getRoot().centerOnScreen();
    }

    /**
     * Gets the user's response to the warning, on whether to proceed with
     * the action.
     *
     * @return Boolean value of the user's response.
     */
    public boolean isUserConfirmingCommand() {
        return isConfirmingCommand;
    }

    /**
     * Handles the event when user clicks on 'Confirm' or 'Cancel' button. Updates
     * the boolean value for userResponse accordingly.
     *
     * @param event ActionEvent caused by the button click.
     */
    @FXML
    private void handleButtonAction(ActionEvent event) {
        Node source = (Node) event.getSource();
        String buttonClicked = source.getId();
        if (buttonClicked.equals("noButton")) {
            getRoot().hide();
            isConfirmingCommand = false;
        } else {
            getRoot().hide();
            isConfirmingCommand = true;
        }
    }

    @FXML
    private void handleCloseButtonAction(WindowEvent event) {
        isConfirmingCommand = false;
    }

}
