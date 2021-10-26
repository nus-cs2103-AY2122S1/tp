package seedu.address.ui;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;
import javax.crypto.NoSuchPaddingException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    private TextField userInput;

    @FXML
    private Button sendButton;

    @FXML
    private Label responseDisplay;

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
     * @param app The app to have the login screen.
     * @param isNew The boolean value for whether the user is new.
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
        if (PasswordCommandParser.passwordValidation(userInput.getText())) {
            try {
                app.logIn(userInput.getText());
            } catch (UnsupportedPasswordException | NoSuchPaddingException | NoSuchAlgorithmException
                    | InvalidKeyException | InvalidAlgorithmParameterException e) {
                responseDisplay.setText("Something went wrong, try again!");
            } catch (IOException e) {
                responseDisplay.setText("Wrong password, try again!");
            }
        } else {
            responseDisplay.setText(PasswordCommand.CORRECT_PASSWORD_FORMAT);
        }
        userInput.clear();
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
