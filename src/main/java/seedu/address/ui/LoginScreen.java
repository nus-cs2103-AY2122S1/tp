package seedu.address.ui;

import java.io.FileNotFoundException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
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

public class LoginScreen extends UiPart<Stage> {
    private static final String FXML = "LoginWindow.fxml";
    private static final String INSTRUCTION = "Please enter your password!";
    private static final String PASSWORD_WRONG = "Wrong password, try again!";
    private static final String SOMETHING_WRONG = "Something went wrong, try again!";
    private static final String DATA_NOT_FOUND = "Data not found. Please exit and start again!";
    private final Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    private PasswordField userInputPassword;

    @FXML
    private Label responseDisplay;

    private final MainApp app;

    /**
     * Constructs a new LoginScreen.
     *
     * @param app The app to have the login screen.
     */
    public LoginScreen(MainApp app) {
        super(FXML);
        this.app = app;
    }

    /**
     * Constructs a new LoginScreen.
     *
     * @param app The app to have the login screen.
     * @param primaryStage The stage to run.
     */
    public LoginScreen(MainApp app, Stage primaryStage) {
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
        handlePassword();
    }

    private void handlePassword() {
        // blocks the case when / is appended to the end of password
        if (!PasswordUtil.isValidPassword(userInputPassword.getText())) {
            responseDisplay.setText(PASSWORD_WRONG);
            userInputPassword.clear();
            return;
        }

        try {
            boolean isCorrectPassword = app.logIn(userInputPassword.getText());
            // wrong password
            if (!isCorrectPassword) {
                responseDisplay.setText(PASSWORD_WRONG);
                userInputPassword.clear();
            }
        } catch (UnsupportedPasswordException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException
                | InvalidAlgorithmParameterException e) {
            // app problems
            responseDisplay.setText(SOMETHING_WRONG);
            userInputPassword.clear();
        } catch (FileNotFoundException e) {
            // data deleted after opening the app
            responseDisplay.setText(DATA_NOT_FOUND);
            userInputPassword.clear();
        }
    }

    /**
     * Shows the login screen.
     */
    public void show() {
        logger.fine("Showing login page...");
        getRoot().show();
        getRoot().centerOnScreen();
        responseDisplay.setText(INSTRUCTION);
    }

}
