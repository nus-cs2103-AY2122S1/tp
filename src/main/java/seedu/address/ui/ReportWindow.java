package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a report page
 */
public class ReportWindow extends UiPart<Stage> {

    private static String message;

    private static final Logger logger = LogsCenter.getLogger(ReportWindow.class);
    private static final String FXML = "ReportWindow.fxml";

    @FXML
    private Label reportMessage;

    /**
     * Creates a new ReportWindow.
     *
     * @param root Stage to use as the root of the ReportWindow.
     */
    public ReportWindow(Stage root) {
        super(FXML, root);
        reportMessage.setText(message);
    }

    /**
     * Creates a new ReportWindow.
     */
    public ReportWindow() {
        this(new Stage());
    }

    /**
     * Sets the message used for creating the report.
     *
     *@param messageInput input used to set final message
     */
    public static void setReportMessage(String messageInput) {
        message = messageInput;
    }

    /**
     * Shows the report window.
     */
    public void show() {
        logger.fine("Displaying the current report for the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }
}
