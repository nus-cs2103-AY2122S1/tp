package seedu.notor.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import seedu.notor.logic.commands.exceptions.CommandException;


public class WarningWindow extends UiPart<Stage> {
    private static final String FXML = "WarningWindow.fxml";
    private boolean canContinue;

    @FXML
    private TextFlow textFlow;

    @FXML
    private Button cancelButton;

    /**
     * Creates a Warning Window if user attempts to delete Person or clear Notor.
     *
     * @param message of Warning Window.
     */
    public WarningWindow(String message) {
        super(FXML);
        this.canContinue = false;
        Text text = new Text(message);
        textFlow.getChildren().add(text);
        Platform.runLater(() -> cancelButton.requestFocus());
        getRoot().setTitle("Warning");
        getRoot().initModality(Modality.APPLICATION_MODAL);
        getRoot().setResizable(false);
    }

    /**
     * Shows Warning Window.
     */
    public void show() {
        getRoot().showAndWait();
        getRoot().centerOnScreen();
    }

    /**
     * Saves and exits both Warning Window upon clicking continue button.
     *
     * @throws CommandException commandException
     */
    @FXML
    public void onClickContinue() throws CommandException {
        getRoot().close();
        canContinue = true;
    }

    @FXML
    public boolean canContinue() {
        return canContinue;
    }

    /**
     * Exits Warning Window upon clicking cancel button.
     */
    @FXML
    public void onClickCancel() {
        getRoot().close();
    }
}
