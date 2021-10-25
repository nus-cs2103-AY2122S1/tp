package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import seedu.address.model.person.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.logging.Logger;

public class ProfileWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(ProfileWindow.class);

    private static final String FXML = "ProfileWindow.fxml";
    private static final String INVALID_ADDRESS_MESSAGE = "The Address Entered is Invalid";
    private static final String INVALID_EMAIL_MESSAGE = "The Email Entered is Invalid";
    private static final String INVALID_GITHUB_MESSAGE = "The GitHub Username Entered is Invalid";
    private static final String INVALID_NAME_MESSAGE = "The Name Entered is Invalid";
    private static final String INVALID_PHONE_MESSAGE = "The Phone Number Entered is Invalid";
    private static final String INVALID_TELEGRAM_MESSAGE = "The Telegram Handle Entered is Invalid";

    private Logic logic;
    private MainWindow mainWindow;

    @FXML
    private Button submit;

    @FXML
    private Text message;

    @FXML
    private TextField name;

    @FXML
    private TextField telegram;

    @FXML
    private TextField github;

    @FXML
    private TextField phone;

    @FXML
    private TextField email;

    @FXML
    private TextField address;

    @FXML
    private TextField tag;

    public ProfileWindow(Stage stage, MainWindow mainWindow, Logic logic) {
        super(FXML, stage);
        this.mainWindow = mainWindow;
        this.logic = logic;
        //submit.setOnAction(event());
    }

    public void start() {
        if (logic.isProfilePresent()) {
            mainWindow.start();
        } else {
            getRoot().show();
        }
    }

    public void close() {
        getRoot().close();
    }

    private EventHandler<ActionEvent> event() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // CHECK EVERYTHING IS VALID
                System.out.println("Checking");
            }
        };
    }

    public void submit(ActionEvent event) {
        //event.consume();
        System.out.println("Checking submit");

        String userAddress = address.getText();
        String userEmail = email.getText();
        String userGithub = github.getText();
        String userName = name.getText();
        String userPhone = phone.getText();
        String userTelegram = telegram.getText();

        Address address;
        Email email;
        Github github;
        Name name;
        Phone phone;
        Telegram telegram;

        if (areUserCredentialsValid()) {
            address = new Address(userAddress);

            email = new Email(userEmail);

            github = new Github(userGithub);

            name = new Name(userName);

            phone = new Phone(userPhone);

            telegram = new Telegram(userTelegram);

            Person user = new Person(name, telegram, github, phone, email, address, new HashSet<>());

            try {
                logic.setUserProfile(user);
            } catch (IOException e) {
                logger.severe("Could Not Set User Data.");
            }

            close();
            mainWindow.start();
        }

        //message.setText("Submitted!");
    }

    public boolean areUserCredentialsValid() {
        String userAddress = address.getText();
        String userEmail = email.getText();
        String userGithub = github.getText();
        String userName = name.getText();
        String userPhone = phone.getText();
        String userTelegram = telegram.getText();

        if (!Address.isValidAddress(userAddress)) {
            message.setText(INVALID_ADDRESS_MESSAGE);
            return false;
        }

        if (!Email.isValidEmail(userEmail)) {
            message.setText(INVALID_EMAIL_MESSAGE);
            return false;
        }

        if (!Github.isValidGithub(userGithub)) {
            message.setText(INVALID_GITHUB_MESSAGE);
            return false;
        }

        if (!Name.isValidName(userName)) {
            message.setText(INVALID_NAME_MESSAGE);
            return false;
        }

        if (!Phone.isValidPhone(userPhone)) {
            message.setText(INVALID_PHONE_MESSAGE);
            return false;
        }

        if (!Telegram.isValidTelegram(userTelegram)) {
            message.setText(INVALID_TELEGRAM_MESSAGE);
            return false;
        }

        return true;
    }
}
