package seedu.address.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.testfx.service.query.NodeQuery;
import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.storage.*;

import java.nio.file.Path;

@ExtendWith(ApplicationExtension.class)
public class ProfileSetUpWindowTest {
    Model model = new ModelManager();

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonAddressBookStorage addressBookStorage = new JsonAddressBookStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        JsonUserProfileStorage userProfileStorage = new JsonUserProfileStorage();
        storageManager = new StorageManager(addressBookStorage, userPrefsStorage, userProfileStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    Logic logic = new LogicManager(model, storageManager);
    MainWindow mainWindow = new MainWindow(new Stage(), logic);
    ProfileSetUpWindow profileSetUpWindow;

    @Start
    private void start(Stage stage) {
        profileSetUpWindow = new ProfileSetUpWindow(new Stage(), mainWindow, logic);
        stage.setScene(profileSetUpWindow.getRoot().getScene());
        stage.show();
    }

    @Test
    private void hasSubmitButton (FxRobot robot) {
        FxAssert.verifyThat((NodeQuery) profileSetUpWindow.getRoot().getScene(), LabeledMatchers.hasText("Submit"));
    }
}
