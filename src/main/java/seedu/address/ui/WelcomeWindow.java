package seedu.address.ui;

import java.util.logging.Logger;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for the Welcome Window
 */
public class WelcomeWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(WelcomeWindow.class);
    private static final String FXML = "WelcomeWindow.fxml";

    private final String tagLine = "TYPE. EXPLORE. CONNECT. ";
    private final double delayTime = (tagLine.length() * 5) + 100;
    private final Image appLogoImage = new Image(this.getClass().getResourceAsStream("/images/logo.png"));

    @FXML
    private Label appTagLine;

    @FXML
    private ImageView appLogo;

    /**
     * Creates a new WelcomeWindow.
     */
    public WelcomeWindow() {
        super(FXML, new Stage());
    }

    /**
     * Helps in Creating a WelcomeWindow.
     */
    public void start(MainWindow mainWindow) {
        // To show the welcome window
        logger.fine("Showing Welcome Window (App has started)");
        getRoot().initStyle(StageStyle.UNDECORATED);
        getRoot().show();
        getRoot().centerOnScreen();
        getRoot().setAlwaysOnTop(true);

        appLogo.setImage(appLogoImage);
        displayAnimatedText(tagLine, delayTime);

        // To add the fading effect on the app logo.
        fadeTransition();

        // To close the WelcomeWindow after a specified amount of time.
        close(mainWindow);
    }

    /**
     * Handles the closing of the WelcomeWindow created, and
     * launching of the MainWindow.
     */
    public void close(MainWindow mainWindow) {
        PauseTransition delay = new PauseTransition(Duration.millis(delayTime * tagLine.length() + 100));
        delay.setOnFinished(event -> {
            getRoot().close();
            mainWindow.show();
        });
        delay.play();
    }

    /**
     * Handles the fading in effect of the picture (here app logo)
     * in the WelcomeWindow.
     */
    public void fadeTransition() {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(4200), appLogo);
        fadeOut.setFromValue(0.0);
        fadeOut.setToValue(1.0);
        fadeOut.play();
    }

    /**
     * Handles the animation effect of characters in the WelcomeWindow.
     * The characters of the text passed are printed one by one after
     * a certain amount of delay.
     *
     * @param textToDisplay The text to be displayed on the WelcomeWindow stage.
     * @param delayTime The delay after which each character is to be printed.
     */
    // Solution below adapted from https://stackoverflow.com/a/33648481
    public void displayAnimatedText(String textToDisplay, double delayTime) {
        IntegerProperty i = new SimpleIntegerProperty(0);
        Timeline timeline = new Timeline();

        KeyFrame keyFrame = new KeyFrame(Duration.millis(delayTime), event -> {
            if (i.get() > textToDisplay.length()) {
                timeline.stop();
            } else {
                appTagLine.setText(textToDisplay.substring(0, i.get()));
                i.set(i.get() + 1);
            }
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
