package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.person.Person;

/**
 * Controller for the User Profile Window.
 */
public class UserProfileWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(UserProfileWindow.class);
    private static final String FXML = "UserProfileWindow.fxml";

    private static final String HEADING = "Your Details";
    private static final String NAME = "Name: ";
    private static final String GITHUB = "GitHub Username: ";
    private static final String TELEGRAM = "Telegram Handle: ";

    private Logic logic;

    @FXML
    private Text heading;

    @FXML
    private Label userName;

    @FXML
    private Label userGitHub;

    @FXML
    private Label userTelegram;

    @FXML
    private ImageView userProfile;

    /**
     * Creates a new {@code UserProfileWindow}.
     *
     * @param stage Stage to use as the root of the {@code UserProfileWindow}.
     * @param logic To obtain user data.
     */
    public UserProfileWindow(Stage stage, Logic logic) {
        super(FXML, stage);
        this.logic = logic;
    }

    /**
     * Creates a new {@code UserProfileWindow}.
     *
     * @param logic To obtain user data.
     */
    public UserProfileWindow(Logic logic) {
        this(new Stage(), logic);
    }

    /**
     * Shows the {@code UserProfileWindow} after initializing
     * fields.
     */
    public void show() {
        logger.fine("Showing User Profile Window");
        initializeFields();
        getRoot().show();
        getRoot().centerOnScreen();
        getRoot().requestFocus();
    }

    /**
     * Returns true if the {@code UserProfileWindow} is currently being shown.
     *
     * @return true, if the {@code UserProfileWindow} is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Focuses on the {@code UserProfileWindow}.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Hides the {@code UserProfileWindow}.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Obtains the fields including {@code userName}
     * {@code userGitHub}, {@code userTelegram} and
     * {@code userProfile}, that are to be displayed
     * on the window.
     */
    public void initializeFields() {
        assert logic != null : "User Data Could Not Be Found";
        Person user = logic.getUserProfile();

        String userName = user.getName().toString();
        String userGitHub = user.getGithub().toString();
        String userTelegram = user.getTelegram().toString();
        Image userProfile = user.getProfilePicture();

        assert userName != null : "User Name Could Not Be Set Up";
        assert userGitHub != null : "User GitHub Username Could Not Be Set Up";
        assert userTelegram != null : "User Telegram Handle Could Not Be Set Up";
        assert userProfile != null : "User Profile Picture Could Not Be Set Up";

        setFields(userName, userGitHub, userTelegram, userProfile);
        logger.fine("Fields Initialized in User Profile Window");
    }

    /**
     * Sets up the fields including {@code heading},
     * {@code userName}, {@code userGitHub},
     * {@code userTelegram} and {@code userProfile},
     * that are to be displayed on the window.
     *
     * @param userNameObtained The {@code userName} obtained.
     * @param userGitHubObtained The {@code userGitHub} obtained.
     * @param userTelegramObtained The {@code userTelegram} obtained.
     * @param userProfileObtained The {@code userProfile} obtained.
     */
    public void setFields(String userNameObtained, String userGitHubObtained,
                          String userTelegramObtained, Image userProfileObtained) {
        heading.setText(HEADING);
        userName.setText(NAME + userNameObtained);
        userGitHub.setText(GITHUB + userGitHubObtained);
        userTelegram.setText(TELEGRAM + userTelegramObtained);
        userProfile.setImage(userProfileObtained);
    }
}
