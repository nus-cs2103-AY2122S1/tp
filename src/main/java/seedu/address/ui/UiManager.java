package seedu.address.ui;

import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.Logic;

/**
 * The manager of the UI component.
 */
public class UiManager implements Ui {

    /**
     * Stands for message to be shown about alert dialog pane.
     */
    public static final String ALERT_DIALOG_PANE_FIELD_ID = "alertDialogPane";

    /**
     * Stands for logger to log events happened in UiManager.
     */
    private static final Logger logger = LogsCenter.getLogger(UiManager.class);

    /**
     * Locates ezFoodie Logo.
     */
    private static final String ICON_APPLICATION = "/images/ezfoodie_icon.png";

    /**
     * Refers to backend logic.
     */
    private Logic logic;

    /**
     * Refers to the window to be shown when user first opens ezFoodie.
     */
    private MainWindow mainWindow;

    /**
     * Creates a {@code UiManager} with the given {@code Logic}.
     */
    public UiManager(Logic logic) {
        super();
        this.logic = logic;
    }

    /**
     * Starts the UI (and the Application).
     */
    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting UI...");

        //Set the application icon.
        primaryStage.getIcons().add(getImage(ICON_APPLICATION));

        try {
            mainWindow = new MainWindow(primaryStage, logic);
            mainWindow.show(); //This should be called before creating other UI parts
            mainWindow.fillInnerParts();

        } catch (Throwable e) {
            logger.severe(StringUtil.getDetails(e));
            showFatalErrorDialogAndShutdown("Fatal error during initializing", e);
        }
    }

    /**
     * Gets ezFoodie logo.
     *
     * @param imagePath path of the images stored.
     * @return Image object of ezFoodie logo.
     */
    private Image getImage(String imagePath) {
        return new Image(MainApp.class.getResourceAsStream(imagePath));
    }

    /**
     * Alerts user by showing alert dialog.
     *
     * @param type alert type.
     * @param title tile of alert.
     * @param headerText header text to show.
     * @param contentText content text to show.
     */
    void showAlertDialogAndWait(Alert.AlertType type, String title, String headerText, String contentText) {
        showAlertDialogAndWait(mainWindow.getPrimaryStage(), type, title, headerText, contentText);
    }

    /**
     * Returns after the user has closed the alert dialog.
     *
     * @param owner stage.
     * @param type alert type.
     * @param title title to show.
     * @param headerText header text to show.
     * @param contentText content text to show.
     */
    private static void showAlertDialogAndWait(Stage owner, AlertType type, String title, String headerText,
                                               String contentText) {
        final Alert alert = new Alert(type);
        alert.getDialogPane().getStylesheets().add("view/DarkTheme.css");
        alert.initOwner(owner);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.getDialogPane().setId(ALERT_DIALOG_PANE_FIELD_ID);
        alert.showAndWait();
    }

    /**
     * Shows an error alert dialog with {@code title} and error message, {@code e},
     * and exits the application after the user has closed the alert dialog.
     */
    private void showFatalErrorDialogAndShutdown(String title, Throwable e) {
        logger.severe(title + " " + e.getMessage() + StringUtil.getDetails(e));
        showAlertDialogAndWait(Alert.AlertType.ERROR, title, e.getMessage(), e.toString());
        Platform.exit();
        System.exit(1);
    }

}
