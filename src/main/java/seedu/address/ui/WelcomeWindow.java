package seedu.address.ui;

import javafx.animation.*;
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

import java.util.logging.Logger;

/**
 * Controller for a welcome page
 */
public class WelcomeWindow extends UiPart<Stage>{

    private static final Logger logger = LogsCenter.getLogger(WelcomeWindow.class);
    private static final String FXML = "WelcomeWindow.fxml";

    private final String tagLine = "TYPE. EXPLORE. CONNECT";
    private final double delayTime = (tagLine.length() * 5 ) + 100;

    private Image appLogoImage = new Image(this.getClass().getResourceAsStream("/images/logo.png"));

    @FXML
    private Label appTagLine;

    @FXML
    private ImageView appLogo;

    /**
     * Creates a new WelcomeWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public WelcomeWindow(Stage root) {
        super(FXML, root);
        show();
    }

    /**
     * Creates a new WelcomeWindow.
     */
    public WelcomeWindow() {
        this(new Stage());
    }

    /**
     * Helps in Creating a WelcomeWindow.
     */
    public void start() {
        // To show the welcome window
        logger.fine("Showing welcome window (App has started)");
        getRoot().initStyle(StageStyle.UNDECORATED);
        getRoot().show();
        getRoot().centerOnScreen();
        getRoot().setAlwaysOnTop(true);

        appLogo.setImage(appLogoImage);
        displayAnimatedText(tagLine, delayTime);

        // Fading transition
        //FadeTransition fadeOut = new FadeTransition(Duration.millis(5000), appLogo);
        //fadeOut.setFromValue(1.0);
        //fadeOut.setToValue(0.0);
        //fadeOut.play();

        fadeTransition();
        close();

        // Quit app
        /*
        try {
            Thread.sleep(10000);
            getRoot().close();
        } catch (InterruptedException e) {
            logger.warning("Welcome Window could not be closed.");
        }
         */

        // set a delay function to close the app here
    }

    /**
     * Creates a new WelcomeWindow.
     */
    public void show() {
        start();
    }

    /**
     * Handles the closing of the WelcomeWindow created.
     */
    public void close() {
        PauseTransition delay = new PauseTransition(Duration.millis(5100));
        delay.setOnFinished( event -> getRoot().close() );
        delay.play();
    }

    /**
     * Handles the fading in effect of the picture (here app logo)
     * in the WelcomeWindow.
     */
    public void fadeTransition() {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(4000), appLogo);
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
    public void displayAnimatedText(String textToDisplay, double delayTime) {
        IntegerProperty i = new SimpleIntegerProperty(0);
        Timeline timeline = new Timeline();

        KeyFrame keyFrame = new KeyFrame(
                Duration.millis(delayTime), event -> {
                    if (i.get() > textToDisplay.length()) {
                        timeline.stop();
                    } else {
                        // Add where to show text
                        appTagLine.setText(textToDisplay.substring(0, i.get()));
                        i.set(i.get() + 1);
                    }
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
