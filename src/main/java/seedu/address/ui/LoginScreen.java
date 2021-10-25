package seedu.address.ui;

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
    private boolean isWrongPasswordFormat;
    private boolean isWrongPassword;

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
        this.isWrongPasswordFormat = false;
        this.isWrongPassword = false;
    }

    public LoginScreen(MainApp app, boolean isNew, Stage primaryStage) {
        super(FXML, primaryStage);
        this.app = app;
        this.isNew = isNew;
        this.isWrongPasswordFormat = false;
        this.isWrongPassword = false;
    }

    @FXML
    private void onButtonClick() {
        if (PasswordCommandParser.passwordValidation(userInput.getText())) {
            try {
                app.logIn(userInput.getText());
            } catch (UnsupportedPasswordException | NoSuchPaddingException | NoSuchAlgorithmException e) {
                isWrongPassword = true;
            }
        } else {
            isWrongPasswordFormat = true; // will display the correct format for user.
        }
        responseDisplay.setText("Wrong password, try again!");
        userInput.clear();
    }

    public void show() {
        logger.fine("Showing login page...");
        getRoot().show();
        getRoot().centerOnScreen();
    }

}
