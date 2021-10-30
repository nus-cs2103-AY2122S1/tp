package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.EditCommand;
import seedu.address.model.util.UserProfileWatcher;

import java.util.logging.Logger;

/**
 *
 */
public class UserProfileInMenuBar extends UiPart<Region> implements UserProfileWatcher {

    private static final Logger logger = LogsCenter.getLogger(UserProfileInMenuBar.class);
    private static final String FXML = "UserProfileInMenuBar.fxml";

    @FXML
    private ImageView userProfile;

    @FXML
    private Label userName;

    private Logic logic;

    public UserProfileInMenuBar(Logic logic) {
        super(FXML);
        this.logic = logic;
        setUserProfileOnMenuBar();
        addToUserProfileWatcherList();
    }

    @Override
    public void updateUserProfile() {
        setUserProfileOnMenuBar();
        logger.info("User Name updated on Screen (Menu Bar)");
    }

    public void addToUserProfileWatcherList() {
        EditCommand.addUserProfileWatcher(this);
        logger.info("Added User Profile (Menu Bar) to the Watcher List.");
    }

    /**
     * Sets the {@code userName} and {@code userProfile}
     * to be displayed on the Menu Bar.
     */
    public void setUserProfileOnMenuBar() {
        userProfile.setImage(logic.getUserProfile().getProfilePicture());
        userName.setText(logic.getUserProfile().getName().toString());
    }
}
