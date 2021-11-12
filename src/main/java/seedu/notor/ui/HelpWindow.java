package seedu.notor.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.notor.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2122s1-cs2103t-w08-1.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE =
            "Refer to the user guide for more details: " + USERGUIDE_URL;
    public static final String COMMAND_SUMMARY_PERSON = "PERSON COMMANDS: \n"
            + "Create : person (NAME) /create | p (NAME) /c \n"
            + "Edit : person (INDEX) /edit [n:NAME] [p:PHONE] [e:EMAIL] | p (INDEX) /e [n:NAME] [p:phone] [e:email]\n"
            + "Delete : person (INDEX) /delete | p (INDEX) /d \n"
            + "Add Group : person (INDEX) /add (g:GROUP_NAME) | p (INDEX) /a (g:GROUP_NAME) \n"
            + "Add SubGroup : person (INDEX) /add (g:GROUP_NAME sg:SUBGROUP_NAME) | p (INDEX) /a (g:GROUP_NAME "
            + "sg:SUBGROUP_NAME)\n"
            + "Remove Group : person (INDEX) /remove (g:GROUP_NAME) | p (INDEX) /r (g:GROUP_NAME)\n"
            + "Remove SubGroup : person (INDEX) /remove (g:GROUP_NAME sg:SUBGROUP_NAME) | p (INDEX) /r (g:GROUP_NAME "
            + "sg:SUBGROUP_NAME)\n"
            + "Note : person (INDEX) /note | p (INDEX) /n\n"
            + "Clear Note : person (INDEX) /clearnote | p (INDEX) /cn\n"
            + "Tag : person (INDEX) /tag [t:TAG1,TAG2,...] | p (INDEX) /t [t:TAG1,TAG2,...]\n"
            + "Untag : person (INDEX) /untag [t:TAG1,TAG2,...] | p (INDEX) /ut [t:TAG1,TAG2,...]\n"
            + "Clear Tags : person (INDEX) /cleartags | p (INDEX) /ct \n"
            + "List Persons : person /list | p /l\n"
            + "List Persons in Group or SubGroup : person [INDEX] /list | p [INDEX] /l\n"
            + "Find : person /find (n:QUERY) | p /f (n:QUERY)\n"
            + "Archive : `person (INDEX) /archive | p (INDEX) /ar\n"
            + "Archive All : person /archive | p /ar\n"
            + "List Archived Persons : `person /listarchive` | `p /lar`\n"
            + "Unarchive : `person (INDEX) /unarchive` | `p (INDEX) /uar\n\n";
    public static final String COMMAND_SUMMARY_GROUP = "GROUP COMMANDS: \n"
            + "Create Group : group (GROUP_NAME) /create | g (GROUP_NAME) /c\n"
            + "Create Subgroup : group (INDEX) /create n:SUBGROUP_NAME | g (INDEX) /c n:SUBGROUP_NAME\n"
            + "Delete : group (INDEX) /delete | g (INDEX) /d\n"
            + "Note : group (INDEX) /note | g (INDEX) /n\n"
            + "List Groups : group /list | g /l\n"
            + "List Out Subgroups : group (INDEX) /list | g (INDEX) /l\n"
            + "Find : group /find (n:QUERY) | g /f (n:QUERY)\n\n";
    public static final String COMMAND_SUMMARY_GENERAL = "GENERAL COMMANDS: \n"
            + "Help : help | h\n"
            + "Exit : exit | e\n"
            + "Clear : clear | c\n"
            + "Export : export | exp\n\n";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label commandSummary;

    @FXML
    private Label helpMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        commandSummary.setText(COMMAND_SUMMARY_GENERAL + COMMAND_SUMMARY_PERSON + COMMAND_SUMMARY_GROUP);
        helpMessage.setText(HELP_MESSAGE);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     *
     * @throws IllegalStateException <ul>
     * <li>
     * if this method is called on a thread other than the JavaFX Application Thread.
     * </li>
     * <li>
     * if this method is called during animation or layout processing.
     * </li>
     * <li>
     * if this method is called on the primary stage.
     * </li>
     * <li>
     * if {@code dialogStage} is already showing.
     * </li>
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
