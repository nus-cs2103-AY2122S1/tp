package dash.ui;

import java.util.logging.Logger;

import dash.commons.core.LogsCenter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class HelpPanel extends UiPart<Region> {
    private static final String FXML = "HelpPanel.fxml";
    private static final String COPY_MESSAGE_UG = "User Guide URL copied to clipboard!";
    private static final String COPY_MESSAGE_DG = "Developer Guide URL copied to clipboard!";

    private static final String HELP_CONTENT = "General:\n"
            + "1. Contacts Tab -> contacts\n"
            + "2. Tasks Tab -> tasks\n"
            + "3. Help Tab -> help\n"
            + "4. Exit Tab -> exit\n\n"
            + "Contacts Tab:\n"
            + "1. Add -> add n/NAME [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]\n"
            + "2. Edit -> edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL a/ADDRESS] [t/TAG]\n"
            + "3. Delete -> delete INDEX\n"
            + "4. Find by name -> find NAME\n"
            + "5. Find by other info -> find [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]\n"
            + "6. Clear Contacts -> clear\n\n"
            + "Tasks Tab:\n"
            + "1. Add -> add d/DESCRIPTION [t/TAG]\n"
            + "2. Edit -> edit INDEX [d/DESCRIPTION] [t/TAG]\n"
            + "3. Delete -> delete INDEX\n"
            + "4. Find by description -> find DESCRIPTION\n"
            + "5. Find by tag -> find [t/TAG]\n"
            + "6. Clear Tasks -> clear\n\n"
            + "For more detailed information,"
            + " visit our User Guide or Developer Guide by clicking the buttons below to copy \n"
            + " their URLs. \n\n";

    private static final String USERGUIDE_URL = "https://ay2122s1-cs2103t-w15-2.github.io/tp/UserGuide.html";

    private static final String DEVGUIDE_URL = "https://ay2122s1-cs2103t-w15-2.github.io/tp/DeveloperGuide.html";

    private final Logger logger = LogsCenter.getLogger(HelpPanel.class);

    private ResultDisplay resultDisplay;

    @FXML
    private VBox container;

    @FXML
    private Label text;

    @FXML
    private Button copyUserGuideButton;

    @FXML
    private Button copyDevGuideButton;

    /**
     * Creates a new Help Panel to contain help messages.
     *
     */
    public HelpPanel(ResultDisplay resultDisplay) {
        super(FXML);
        text.setText(HELP_CONTENT);
        this.resultDisplay = resultDisplay;
        text.setWrapText(true);
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUserGuideUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
        resultDisplay.setFeedbackToUser(COPY_MESSAGE_UG);

    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyDevGuideUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(DEVGUIDE_URL);
        clipboard.setContent(url);
        resultDisplay.setFeedbackToUser(COPY_MESSAGE_DG);
    }

}
