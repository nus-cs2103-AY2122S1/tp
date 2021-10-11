package seedu.address.ui;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
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

import java.util.logging.Logger;

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

    public WelcomeWindow(Stage root) {
        super(FXML, root);
        show();
    }

    public WelcomeWindow() {
        this(new Stage());
    }

    public void start() {
        /**
         * 1. Add open and closing fading effect.
         * 2. remove top title bar. (Done)
         */

        // To show the welcome window
        logger.fine("Showing welcome window (App has started)");
        getRoot().initStyle(StageStyle.UNDECORATED);
        getRoot().show();
        getRoot().centerOnScreen();
        //getRoot().toFront();

        appLogo.setImage(appLogoImage);
        displayAnimatedText(tagLine, delayTime);

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

    void show() {
        start();
    }

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
