package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2122s1-cs2103t-t10-1.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "For more Information, please refer to the user guide: \n" + USERGUIDE_URL;

    private static final String ADD_NEW_CONTACT_FEATURE_NAME = "Add a New Contact";
    private static final String ADD_NEW_CONTACT_FEATURE_COMMAND = "add n/ te/ [p/] [e/] [a/<ADDRESS>] [t/]";
    private static final String DELETE_CONTACT_FEATURE_NAME = "Delete a Contact";
    private static final String DELETE_CONTACT_FEATURE_COMMAND = "delete <INDEX>";
    private static final String EDIT_CONTACT_FEATURE_NAME = "Edit a Contact";
    private static final String EDIT_CONTACT_FEATURE_COMMAND = "edit [n/] [p/] [e/] [a/<ADDRESS>] [t/]";
    private static final String FIND_CONTACT_FEATURE_NAME_V1 = "Find a Contact (by Name)";
    private static final String FIND_CONTACT_FEATURE_COMMAND_V1 = "find <STRING>";
    private static final String FIND_CONTACT_FEATURE_NAME_V2 = "Find a Contact (by Tag)";
    private static final String FIND_CONTACT_FEATURE_COMMAND_V2 = "find t/<TAG>";
    private static final String SHOW_CONTACT_FEATURE_NAME_V1 = "Show Contact Details (by Name)";
    private static final String SHOW_CONTACT_FEATURE_COMMAND_V1 = "show <NAME>";
    private static final String SHOW_CONTACT_FEATURE_NAME_V2 = "Show Contact Details (by Index)";
    private static final String SHOW_CONTACT_FEATURE_COMMAND_V2 = "show <INDEX>";
    private static final String IMPORT_CONTACT_FEATURE_NAME = "Import Contacts from JSON file";
    private static final String IMPORT_CONTACT_FEATURE_COMMAND = "import filename.JSON";
    private static final String EXPORT_CONTACT_FEATURE_NAME = "Export Contacts to JSON file";
    private static final String EXPORT_CONTACT_FEATURE_COMMAND = "export filename.JSON";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    @FXML
    private TableView<CommandDetails> helpTable;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
        setUpCommandDetails();
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

    /**
     * Sets up the items (commands) to be showed in the
     * help section table.
     */
    public void setUpCommandDetails() {
        ObservableList<CommandDetails> helpSectionCommandDetails= helpTable.getItems();
        helpSectionCommandDetails.clear();

        CommandDetails addNewContactCommandDetails = new CommandDetails(ADD_NEW_CONTACT_FEATURE_NAME, ADD_NEW_CONTACT_FEATURE_COMMAND);
        CommandDetails deleteContactCommandDetails = new CommandDetails(DELETE_CONTACT_FEATURE_NAME, DELETE_CONTACT_FEATURE_COMMAND);
        CommandDetails editContactCommandDetails = new CommandDetails(EDIT_CONTACT_FEATURE_NAME, EDIT_CONTACT_FEATURE_COMMAND);
        CommandDetails findContactByNameCommandDetails = new CommandDetails(FIND_CONTACT_FEATURE_NAME_V1, FIND_CONTACT_FEATURE_COMMAND_V1);
        CommandDetails findContactByTagCommandDetails = new CommandDetails(FIND_CONTACT_FEATURE_NAME_V2, FIND_CONTACT_FEATURE_COMMAND_V2);
        CommandDetails showContactByNameCommandDetails = new CommandDetails(SHOW_CONTACT_FEATURE_NAME_V1, SHOW_CONTACT_FEATURE_COMMAND_V1);
        CommandDetails showContactByIndexCommandDetails = new CommandDetails(SHOW_CONTACT_FEATURE_NAME_V2, SHOW_CONTACT_FEATURE_COMMAND_V2);
        CommandDetails importContactCommandDetails = new CommandDetails(IMPORT_CONTACT_FEATURE_NAME, IMPORT_CONTACT_FEATURE_COMMAND);
        CommandDetails exportContactCommandDetails = new CommandDetails(EXPORT_CONTACT_FEATURE_NAME, EXPORT_CONTACT_FEATURE_COMMAND);

        helpSectionCommandDetails.add(addNewContactCommandDetails);
        helpSectionCommandDetails.add(deleteContactCommandDetails);
        helpSectionCommandDetails.add(editContactCommandDetails);
        helpSectionCommandDetails.add(findContactByNameCommandDetails);
        helpSectionCommandDetails.add(findContactByTagCommandDetails);
        helpSectionCommandDetails.add(showContactByNameCommandDetails);
        helpSectionCommandDetails.add(showContactByIndexCommandDetails);
        helpSectionCommandDetails.add(importContactCommandDetails);
        helpSectionCommandDetails.add(exportContactCommandDetails);
    }
}
