package seedu.tracker.ui;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.tracker.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String ADD_COMMAND = "Adding a module to the database:\n     "
            + "Example: add c/CODE t/TITLE d/DESCRIPTION m/MC [tag/TAG]";
    public static final String CLEAR_COMMAND = "Removing all modules from a specific "
            + "semester in the academic plan:\n     "
            + "Example: clear y/YEAR s/SEMESTER";
    public static final String COMMAND_FORMAT = "Below shows the format of all commands : ";
    public static final String DELETE_COMMAND = "Deleting a module from the database:\n     Example: delete INDEX";
    public static final String EDIT_COMMAND = "Editing a module in the database:\n     "
            + "Example: edit INDEX [c/CODE] [t/TITLE] [d/Description] [m/MC] [tag/TAG]";
    public static final String FIND_COMMAND = "Finding module(s) in the database:\n     "
            + "Example: find [c/] [t/] [d/] [m/] [tag/] [y/] [s/] KEYWORDS";
    public static final String HELP_COMMAND = "Viewing the command summary:\n     Example: help";
    public static final String EXIT_COMMAND = "Exiting NUS Mod Tracker:\n     Example: exit";
    public static final String USERGUIDE_URL =
            "https://ay2122s1-cs2103t-w17-2.github.io/tp/UserGuide";
    public static final String HELP_MESSAGE = "Refer to the user guide for more information: " + USERGUIDE_URL;
    public static final String LIST_COMMAND = "Listing all the modules in the database :\n     Example: list";
    public static final String SET_COMMAND = "Setting user's MC goal or current semester:\n     "
            + "Example: set m/MC or set y/YEAR s/SEMESTER";
    public static final String TAKE_COMMAND = "Taking a module and adding it into the academic plan:\n     "
            + "Example: take INDEX y/YEAR s/SEMESTER";
    public static final String UNTAKE_COMMAND = "Removing(\"untake\") a module from the academic plan:\n     "
            + "Example: untake INDEX";
    public static final String VIEW_COMMAND = "Viewing modules taken in a specific semester:\n     "
            + "Example: view y/YEAR s/SEMESTER";
    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    private static final List<String> commandList = Collections.unmodifiableList(Arrays.asList(
            ADD_COMMAND, DELETE_COMMAND, LIST_COMMAND, FIND_COMMAND, EDIT_COMMAND,
            TAKE_COMMAND, UNTAKE_COMMAND, SET_COMMAND, VIEW_COMMAND, CLEAR_COMMAND,
            HELP_COMMAND, EXIT_COMMAND));


    @FXML
    private Button copyButton;
    @FXML
    private Label helpMessage;
    @FXML
    private TextArea commandInstruction;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
        String commandFormat = new String(COMMAND_FORMAT);
        for (int i = 0; i < HelpWindow.commandList.size(); i++) {
            commandFormat += "\n\n" + (i + 1) + ". " + HelpWindow.commandList.get(i);
        }
        commandInstruction.setText(commandFormat);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }
}
