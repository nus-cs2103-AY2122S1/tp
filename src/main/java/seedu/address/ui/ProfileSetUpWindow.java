package seedu.address.ui;

import java.io.IOException;
import java.util.HashSet;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.ai.ThreadProcessor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Github;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Telegram;

/**
 * Controller for the Profile Window.
 */
public class ProfileSetUpWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(ProfileSetUpWindow.class);

    private static final String FXML = "ProfileSetUpWindow.fxml";
    private static final String INVALID_GITHUB_MESSAGE = "The GitHub Username Entered is Invalid";
    private static final String INVALID_NAME_MESSAGE = "The Name Entered is Invalid";
    private static final String INVALID_TELEGRAM_MESSAGE = "The Telegram Handle Entered is Invalid";
    private static final String WELCOME_MESSAGE = "CohortConnect";

    private Logic logic;
    private MainWindow mainWindow;

    @FXML
    private Button submit;

    @FXML
    private Text welcomeMessage;

    @FXML
    private Text errorMessage;

    @FXML
    private TextField name;

    @FXML
    private TextField telegram;

    @FXML
    private TextField github;

    /**
     * Creates a new {@code ProfileSetUpWindow}.
     *
     * @param stage Stage to use as the root of the {@code ProfileSetUpWindow}.
     * @param mainWindow To be able to interact with the {@code MainWindow}.
     * @param logic To be able to save and check if the user profile exists.
     */
    public ProfileSetUpWindow(Stage stage, MainWindow mainWindow, Logic logic) {
        super(FXML, stage);
        this.mainWindow = mainWindow;
        this.logic = logic;
        welcomeMessage.setText(WELCOME_MESSAGE);
        submit.requestFocus();
    }

    /**
     * Launches the {@code MainWindow} if the User Profile
     * is present or else the {@code ProfileSetUpWindow} is launched to
     * obtain user credentials.
     */
    public void start() {
        if (logic.isProfilePresent()) {
            logger.info("User Profile Found, Launching Main Window");
            assert mainWindow != null : "Main Window not found";
            mainWindow.start();
        } else {
            logger.info("No User Profile Found, Launching Profile Window");
            getRoot().show();
            submit.requestFocus();
        }
    }

    /**
     * Closes the {@code ProfileSetUpWindow}.
     */
    public void close() {
        logger.info("Closing Profile Window");
        getRoot().close();
    }

    /**
     * Closes the application.
     */
    public void handleExit() {
        MainWindow.setDone(true);
        ThreadProcessor.stopAllThreads();
    }

    /**
     * Creates the user profile, if all credentials are
     * valid, and launches the {@code MainWindow} afterwards.
     */
    public void submit() {
        String userGithub = github.getText();
        String userName = name.getText();
        String userTelegram = telegram.getText();

        Address address;
        Email email;
        Github github;
        Name name;
        Phone phone;
        Telegram telegram;

        if (areUserCredentialsValid()) {
            setErrorMessageText("Setting Up...");

            address = new Address("");

            email = new Email("");

            github = new Github(userGithub);

            name = new Name(userName);

            phone = new Phone("");

            telegram = new Telegram(userTelegram);

            Person user = new Person(name, telegram, github, phone, email, address, new HashSet<>());

            try {
                logic.setUserProfile(user);
                logger.info("User Data Set");
            } catch (IOException e) {
                setErrorMessageText("Could Not Set Up User Profile");
                logger.severe("Could Not Set User Data.");
            }

            close();
            mainWindow.start();
        }
    }

    /**
     * Sets The {@code errorMessage} text to
     * {@code message}.
     *
     * @param message The message to be showed.
     */
    public void setErrorMessageText(String message) {
        errorMessage.setText(message);
    }

    /**
     * Returns true if all the credentials entered by the
     * user are valid.
     *
     * @return true, if all the credentials entered by the user are valid.
     */
    public boolean areUserCredentialsValid() {
        String userGitHub = github.getText();
        String userName = name.getText();
        String userTelegram = telegram.getText();

        assert userName != null : "User Name Could Not Be Found";
        if (!Name.isValidName(userName)) {
            logger.info("Invalid Name Detected");
            setErrorMessageText(INVALID_NAME_MESSAGE);
            return false;
        }

        assert userTelegram != null : "User Telegram Handle Could Not Be Found";
        if (!Telegram.isValidTelegram(userTelegram)) {
            logger.info("Invalid Telegram Handle Detected");
            setErrorMessageText(INVALID_TELEGRAM_MESSAGE);
            return false;
        }

        assert userGitHub != null : "User GitHub Username Could Not Be Found";
        if (!Github.isValidGithub(userGitHub)) {
            logger.info("Invalid GitHub Username Detected");
            setErrorMessageText(INVALID_GITHUB_MESSAGE);
            return false;
        }

        logger.info("User Credentials Valid.");
        return true;
    }
}
