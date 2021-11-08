package seedu.gamebook.ui;

import static seedu.gamebook.logic.commands.ClearCommand.COMMAND_CONFIRMATION;
import static seedu.gamebook.logic.commands.ClearCommand.COMMAND_WORD;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.gamebook.commons.core.LogsCenter;



/**
 * Controller for a clear data confirmation page
 */
public class ClearWindow extends UiPart<Stage> {

    public static final String CLEAR_MESSAGE = "Are you sure you want to clear all game entries?";

    private static final Logger logger = LogsCenter.getLogger(ClearWindow.class);
    private static final String FXML = "ClearWindow.fxml";

    @FXML
    private Button clearButton;

    @FXML
    private Label clearMessage;

    private final MainWindow mainWindow;

    /**
     * Creates a new ClearWindow.
     *
     * @param root Stage to use as the root of the ClearWindow.
     */
    public ClearWindow(Stage root, MainWindow mainWindow) {
        super(FXML, root);
        clearMessage.setText(CLEAR_MESSAGE);
        this.mainWindow = mainWindow;
    }

    /**
     * Creates a new ClearWindow.
     */
    public ClearWindow(MainWindow mainWindow) {
        this(new Stage(), mainWindow);
    }

    /**
     * Shows the clear game entry confirmation window.
     *
     * @throws IllegalStateException <ul>
     *                                   <li>
     *                                       if this method is called on a thread other than the JavaFX Application
     *                                       Thread.
     *                                   </li>
     *                                   <li>
     *                                       if this method is called during animation or layout processing.
     *                                   </li>
     *                                   <li>
     *                                       if this method is called on the primary stage.
     *                                   </li>
     *                                   <li>
     *                                       if {@code dialogStage} is already showing.
     *                                   </li>
     *                               </ul>
     */
    public void show() {
        logger.fine("Showing clear data confirmation pop up.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the clear window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the clear window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the clear window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Clears user's data by passing a clear data confirmation command to logic.
     */
    @FXML
    private void clearData() {
        try {
            mainWindow.executeCommand(COMMAND_WORD + " " + COMMAND_CONFIRMATION);
            mainWindow.updateGraph();
            this.hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
