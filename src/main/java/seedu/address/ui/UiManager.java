package seedu.address.ui;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.student.Remark;

/**
 * The manager of the UI component.
 */
public class UiManager implements Ui {

    public static final String ALERT_DIALOG_PANE_FIELD_ID = "alertDialogPane";
    public static final String REMARK_EDITOR_ERROR_MESSAGE = "Something went wrong with the remark editor!";

    private static final Logger logger = LogsCenter.getLogger(UiManager.class);
    private static final String ICON_APPLICATION = "/images/address_book_32.png";

    private Logic logic;
    private MainWindow mainWindow;

    /**
     * Creates a {@code UiManager} with the given {@code Logic}.
     */
    public UiManager(Logic logic) {
        super();
        this.logic = logic;
    }

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

    private Image getImage(String imagePath) {
        return new Image(MainApp.class.getResourceAsStream(imagePath));
    }

    void showAlertDialogAndWait(Alert.AlertType type, String title, String headerText, String contentText) {
        showAlertDialogAndWait(mainWindow.getPrimaryStage(), type, title, headerText, contentText);
    }

    /**
     * Shows an alert dialog on {@code owner} with the given parameters.
     * This method only returns after the user has closed the alert dialog.
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

    /**
     * Displays a dialog box for the user to edit remarks.
     * @param name The name of the student or tuition class being modified.
     * @param remarkToEdit The current remark of the student or tuition class.
     * @return Returns the updated remark.
     * @throws CommandException If unable to load the fxml file for the remark editor.
     */
    public static Remark showRemarkEditor(String name, String remarkToEdit) throws CommandException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(UiManager.class.getResource("/view/RemarkEditor.fxml"));
            DialogPane remarkEditor = fxmlLoader.load();

            logger.fine("Showing the remark editor...");

            RemarkEditor remarkController = fxmlLoader.getController();
            remarkController.setRemark(name, remarkToEdit);

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(remarkEditor);
            dialog.setTitle("Remark Editor");
            Stage stage = (Stage) remarkEditor.getScene().getWindow();
            stage.getIcons().add(new Image(String.valueOf(UiManager.class.getResource("/images/edit_icon.png"))));

            Optional<ButtonType> clickedButton = dialog.showAndWait();
            if (clickedButton.get() == ButtonType.OK) {
                logger.info("Ok button clicked. Remark Editor closing now.");
                return remarkController.getNewRemark();
            }
        } catch (IOException e) {
            logger.severe(StringUtil.getDetails(e));
            throw new CommandException(REMARK_EDITOR_ERROR_MESSAGE, e);
        }

        logger.info("Cancel remark updates. Remark Editor closing now.");
        return new Remark(remarkToEdit);
    }

}
