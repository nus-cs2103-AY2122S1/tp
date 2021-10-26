package seedu.address.ui;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;
import javax.crypto.NoSuchPaddingException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.encryption.exceptions.UnsupportedPasswordException;
import seedu.address.logic.commands.PasswordCommand;
import seedu.address.logic.parser.PasswordCommandParser;

public class LoginScreen extends UiPart<Stage> {
    private static final String FXML = "LoginWindow.fxml";
    private final Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    private PasswordField userInput;

    @FXML
    private Button sendButton;

    @FXML
    private Label responseDisplay;

    @FXML
    private StackPane responseDisplayPane;

    private final MainApp app;
    private final boolean isNew;

    /**
     * Constructs a new LoginScreen.
     *
     * @param app   The app to have the login screen.
     * @param isNew The boolean value for whether the user is new.
     */
    public LoginScreen(MainApp app, boolean isNew) {
        super(FXML);
        this.app = app;
        this.isNew = isNew;
    }

    /**
     * Constructs a new LoginScreen.
     *
     * @param app          The app to have the login screen.
     * @param isNew        The boolean value for whether the user is new.
     * @param primaryStage The stage to run.
     */
    public LoginScreen(MainApp app, boolean isNew, Stage primaryStage) {
        super(FXML, primaryStage);
        this.app = app;
        this.isNew = isNew;
    }

    /**
     * Handles the login button pressed event.
     */
    @FXML
    private void onButtonClick() {
        handleUserInputPassword();
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void onEnter() {
        handleUserInputPassword();
    }

    private void handleUserInputPassword() {
        if (isNew) {
            handleNewPassword();
        } else {
            handlePassword();
        }
    }

    private void handleNewPassword() {
        if (!PasswordCommandParser.passwordValidation(userInput.getText())) {
            responseDisplay.setText(PasswordCommand.CORRECT_PASSWORD_FORMAT);
            userInput.clear();
            return;
        }
        try {
            app.logIn(userInput.getText());
        } catch (UnsupportedPasswordException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException
                | InvalidAlgorithmParameterException e) {
            responseDisplay.setText("Something went wrong, try again!");
        }
    }

    private void handlePassword() {
        try {
            boolean isCorrectPassword = app.logIn(userInput.getText());
            if (!isCorrectPassword) {
                responseDisplay.setText("Wrong password, try again!");
            }
        } catch (UnsupportedPasswordException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException
                | InvalidAlgorithmParameterException e) {
            responseDisplay.setText("Something went wrong, try again!");
        }
    }

    /**
     * Shows the login screen.
     */
    public void show() {
        logger.fine("Showing login page...");
        getRoot().show();
        getRoot().centerOnScreen();
        if (isNew) {
            responseDisplay.setText("Please set up a new password!");
        } else {
            responseDisplay.setText("Please enter your password!");
        }
    }

}
