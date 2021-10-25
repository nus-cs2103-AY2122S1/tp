package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;

import javafx.event.ActionEvent;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Github;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Telegram;

import java.io.IOException;
import java.util.HashSet;
import java.util.logging.Logger;

public class ProfileWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(ProfileWindow.class);

    private static final String FXML = "ProfileWindow.fxml";
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
    private Text message;

    @FXML
    private TextField name;

    @FXML
    private TextField telegram;

    @FXML
    private TextField github;

    public ProfileWindow(Stage stage, MainWindow mainWindow, Logic logic) {
        super(FXML, stage);
        this.mainWindow = mainWindow;
        this.logic = logic;
        welcomeMessage.setText(WELCOME_MESSAGE);
    }

    public void start() {
        if (logic.isProfilePresent()) {
            logger.info("User Profile Found");
            mainWindow.start();
        } else {
            logger.info("No User Profile Found, Launching Profile Window");
            getRoot().show();
        }
    }

    public void close() {
        logger.info("Closing Profile Window");
        getRoot().close();
    }

    public void submit(ActionEvent event) {
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
                logger.severe("Could Not Set User Data.");
            }

            close();
            mainWindow.start();
        }
    }

    public boolean areUserCredentialsValid() {
        String userGithub = github.getText();
        String userName = name.getText();
        String userTelegram = telegram.getText();


        if (!Name.isValidName(userName)) {
            logger.info("Invalid Name Detected");
            message.setText(INVALID_NAME_MESSAGE);
            return false;
        }

        if (!Telegram.isValidTelegram(userTelegram)) {
            logger.info("Invalid Telegram Handle Detected");
            message.setText(INVALID_TELEGRAM_MESSAGE);
            return false;
        }

        if (!Github.isValidGithub(userGithub)) {
            logger.info("Invalid GitHub Username Detected");
            message.setText(INVALID_GITHUB_MESSAGE);
            return false;
        }

        logger.info("User Credentials Valid.");
        return true;
    }
}
