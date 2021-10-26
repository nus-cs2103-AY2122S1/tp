package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.person.Person;

import java.util.logging.Logger;

public class UserProfileWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(UserProfileWindow.class);
    private static final String FXML = "UserProfileWindow.fxml";
    private final String HEADING = "Your Details";
    private final String NAME = "Name: ";
    private final String GITHUB = "GitHub Username: ";
    private final String TELEGRAM = "Telegram Handle: ";

    private Logic logic;

    @FXML
    private Label heading;

    @FXML
    private Label userName;

    @FXML
    private Label userGitHub;

    @FXML
    private Label userTelegram;

    @FXML
    ImageView userProfile;

    public UserProfileWindow(Stage root, Logic logic) {
        super(FXML, root);
        this.logic = logic;
        initializeFields();
    }

    public UserProfileWindow(Logic logic) {
        this(new Stage(), logic);
    }

    public void show() {
        logger.fine("Showing user profile window");
        getRoot().show();
        getRoot().centerOnScreen();
        getRoot().requestFocus();
    }

    public boolean isShowing() {
        return getRoot().isShowing();
    }

    public void focus() {
        getRoot().requestFocus();
    }

    public void hide() {
        getRoot().hide();
    }

    public void initializeFields() {
        Person user = logic.getUserProfile();

        String userName = user.getName().toString();
        String userGitHub = user.getGithub().toString();
        String userTelegram = user.getTelegram().toString();
        Image userProfile = user.getProfilePicture();

        setFields(userName, userGitHub, userTelegram, userProfile);
        logger.fine("Fields Initialized in User Profile Window");
    }

    public void setFields(String userNameObtained, String userGitHubObtained,
                          String userTelegramObtained, Image userProfileObtained) {
        heading.setText(HEADING);
        userName.setText(NAME + userNameObtained);
        userGitHub.setText(GITHUB + userGitHubObtained);
        userTelegram.setText(TELEGRAM + userTelegramObtained);
        userProfile.setImage(userProfileObtained);
    }
}
