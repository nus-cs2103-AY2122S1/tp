package seedu.address.ui;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;
import javax.crypto.NoSuchPaddingException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.PasswordUtil;
import seedu.address.encryption.exceptions.UnsupportedPasswordException;
import seedu.address.logic.commands.PasswordCommand;

public class SetUpScreen extends UiPart<Stage> {
    private static final String FXML = "SetUpPassword.fxml";
    private static final String PASSWORDS_DO_NOT_MATCH = "Two passwords do not match!";
    private final Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    private PasswordField userInputPassword;

    @FXML
    private PasswordField userConfirmPassword;

    @FXML
    private Label responseDisplay;

    private final MainApp app;

    /**
     * Constructs a new LoginScreen.
     *
     * @param app The app to have the login screen.
     */
    public SetUpScreen(MainApp app) {
        super(FXML);
        this.app = app;
    }

    /**
     * Constructs a new LoginScreen.
     *
     * @param app The app to have the login screen.
     * @param primaryStage The stage to run.
     */
    public SetUpScreen(MainApp app, Stage primaryStage) {
        super(FXML, primaryStage);
        this.app = app;
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void onEnter() {
        handleUserInputPassword();
    }

    private void handleUserInputPassword() {
        if (userConfirmPassword.getText().equals(userInputPassword.getText())) {
            handleNewPassword();
        } else {
            responseDisplay.setText(PASSWORDS_DO_NOT_MATCH);
            userInputPassword.clear();
            userConfirmPassword.clear();
        }
    }

    private void handleNewPassword() {
        if (!PasswordUtil.isValidPassword(userInputPassword.getText())) {
            responseDisplay.setText(PasswordCommand.CORRECT_PASSWORD_FORMAT);
            userInputPassword.clear();
            userConfirmPassword.clear();
            return;
        }
        try {
            app.setUp(userInputPassword.getText());
        } catch (UnsupportedPasswordException | NoSuchPaddingException | NoSuchAlgorithmException e) {
            responseDisplay.setText("Something went wrong, try again!");
            userInputPassword.clear();
            userConfirmPassword.clear();
        }
    }

    /**
     * Shows the login screen.
     */
    public void show() {
        logger.fine("Showing login page...");
        getRoot().show();
        getRoot().centerOnScreen();
        responseDisplay.setText("Please set up a new password!");
    }

}
