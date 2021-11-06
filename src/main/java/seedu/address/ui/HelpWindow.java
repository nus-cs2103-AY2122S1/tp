package seedu.address.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Logger;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.ScrollEvent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.CommandDetails;

/**
 * Controller for the Help Window
 */
public class HelpWindow extends UiPart<Stage> {

    private static final String USERGUIDE_URL = "https://ay2122s1-cs2103t-t10-1.github.io/tp/UserGuide.html";
    private static final String HELP_MESSAGE = "For more Information, please refer to the user guide:\n"
            + USERGUIDE_URL;

    private static final String ADD_NEW_CONTACT_FEATURE_NAME = "Add a New Contact";
    private static final String ADD_NEW_CONTACT_FEATURE_COMMAND = "add n/ te/ g/ [p/] [e/] [a/] [t/]";
    private static final String DELETE_CONTACT_FEATURE_NAME = "Delete a Contact";
    private static final String DELETE_CONTACT_FEATURE_COMMAND = "delete <INDEX>";
    private static final String DELETE_CONTACTS_FEATURE_NAME = "Delete ALL Contacts";
    private static final String DELETE_CONTACTS_FEATURE_COMMAND = "clear";
    private static final String EDIT_CONTACT_FEATURE_NAME = "Edit a Contact";
    private static final String EDIT_CONTACT_FEATURE_COMMAND = "edit <INDEX> [n/] [p/] [g/] [e/] [a/] [t/]";
    private static final String EDIT_PROFILE_FEATURE_NAME = "Edit Your Profile";
    private static final String EDIT_PROFILE_FEATURE_COMMAND = "edit profile [n/] [g/] [te/]";
    private static final String FIND_CONTACT_FEATURE_NAME_V1 = "Find a Contact (by Name)";
    private static final String FIND_CONTACT_FEATURE_COMMAND_V1 = "find <STRING>";
    private static final String FIND_CONTACT_FEATURE_NAME_V2 = "Find a Contact (by Tag)";
    private static final String FIND_CONTACT_FEATURE_COMMAND_V2 = "find t/<TAG>";
    private static final String FIND_CONTACT_FEATURE_NAME_V3 = "Find a Contact (by GitHub Username)";
    private static final String FIND_CONTACT_FEATURE_COMMAND_V3 = "find g/<STRING>";
    private static final String FIND_CONTACT_FEATURE_NAME_V4 = "Find a Contact (by Telegram Handle)";
    private static final String FIND_CONTACT_FEATURE_COMMAND_V4 = "find te/<STRING>";
    private static final String SHOW_CONTACT_FEATURE_NAME_V1 = "Show Contact Details (by Name)";
    private static final String SHOW_CONTACT_FEATURE_COMMAND_V1 = "show <NAME>";
    private static final String SHOW_CONTACT_FEATURE_NAME_V2 = "Show Contact Details (by Index)";
    private static final String SHOW_CONTACT_FEATURE_COMMAND_V2 = "show <INDEX>";
    private static final String LIST_CONTACTS_FEATURE_NAME = "List all Contacts present";
    private static final String LIST_CONTACTS_FEATURE_COMMAND = "list";
    private static final String FAVORITE_CONTACT_FEATURE_NAME = "Mark a Contact as Favorite";
    private static final String FAVORITE_CONTACT_FEATURE_COMMAND = "fav <INDEX>";
    private static final String UNFAVORITE_CONTACT_FEATURE_NAME = "Mark a Contact as UnFavorite";
    private static final String UNFAVORITE_CONTACT_FEATURE_COMMAND = "unfav <INDEX>";
    private static final String ADD_TAGS_FEATURE_NAME = "To Add Tag(s)";
    private static final String ADD_TAGS_FEATURE_COMMAND = "tag <INDEX> a/<TAG> [MORE_TAGS]";
    private static final String REMOVE_TAGS_FEATURE_NAME = "To Remove Tag(s)";
    private static final String REMOVE_TAGS_FEATURE_COMMAND = "tag <INDEX> r/<TAG> [MORE_TAGS]";
    private static final String ADD_AND_REMOVE_TAGS_SIMULTANEOUSLY_FEATURE_NAME = "To Add and Remove Tag(s) "
            + "Simultaneously";
    private static final String ADD_AND_REMOVE_TAGS_SIMULTANEOUSLY_FEATURE_COMMAND = "tag <INDEX> a/<TAG> [TAGS] "
            + "r/<TAG> [TAGS]";
    private static final String IMPORT_CONTACT_CSV_FEATURE_NAME = "Import Contacts from CSV file";
    private static final String IMPORT_CONTACT_CSV_FEATURE_COMMAND = "import <FILENAME>.csv";
    private static final String IMPORT_CONTACT_JSON_FEATURE_NAME = "Import Contacts from JSON file";
    private static final String IMPORT_CONTACT_JSON_FEATURE_COMMAND = "import <FILENAME>.json";
    private static final String EXPORT_CONTACT_CSV_FEATURE_NAME = "Export Contacts to CSV file";
    private static final String EXPORT_CONTACT_CSV_FEATURE_COMMAND = "export <FILENAME>.csv";
    private static final String EXPORT_CONTACT_JSON_FEATURE_NAME = "Export Contacts to JSON file";
    private static final String EXPORT_CONTACT_JSON_FEATURE_COMMAND = "export <FILENAME>.json";
    private static final String HELP_FEATURE_NAME = "Launch this Help Window";
    private static final String HELP_FEATURE_COMMAND = "help";
    private static final String EXIT_APP_FEATURE_NAME = "Exit the App";
    private static final String EXIT_APP_FEATURE_COMMAND = "exit";

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
        setUpHelpTableView();
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
     * Sets up the table view. Disables scrolling and
     * adjusts the height according to the number of rows.
     */
    // Solution below adapted from
    // https://stackoverflow.com/questions/27945817/javafx-adapt-tableview-height-to-number-of-rows
    public void setUpHelpTableView() {
        helpTable.addEventFilter(ScrollEvent.ANY, Event::consume);
        helpTable.setFixedCellSize(27);
        helpTable.prefHeightProperty().bind(helpTable.fixedCellSizeProperty()
                .multiply(Bindings.size(helpTable.getItems()).add(1.01)));
        helpTable.minHeightProperty().bind(helpTable.prefHeightProperty());
        helpTable.maxHeightProperty().bind(helpTable.prefHeightProperty());
    }

    /**
     * Returns true if the help window is currently being shown.
     *
     * @return true, if the help window is being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the Help Window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the Help Window.
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
        ObservableList<CommandDetails> helpSectionCommandDetails = helpTable.getItems();
        helpSectionCommandDetails.clear();

        CommandDetails addNewContactCommandDetails = new CommandDetails(ADD_NEW_CONTACT_FEATURE_NAME,
                ADD_NEW_CONTACT_FEATURE_COMMAND);
        CommandDetails deleteContactCommandDetails = new CommandDetails(DELETE_CONTACT_FEATURE_NAME,
                DELETE_CONTACT_FEATURE_COMMAND);
        CommandDetails deleteAllContactsCommandDetails = new CommandDetails(DELETE_CONTACTS_FEATURE_NAME,
                DELETE_CONTACTS_FEATURE_COMMAND);
        CommandDetails editContactCommandDetails = new CommandDetails(EDIT_CONTACT_FEATURE_NAME,
                EDIT_CONTACT_FEATURE_COMMAND);
        CommandDetails editProfileCommandDetails = new CommandDetails(EDIT_PROFILE_FEATURE_NAME,
                EDIT_PROFILE_FEATURE_COMMAND);
        CommandDetails findContactByNameCommandDetails = new CommandDetails(FIND_CONTACT_FEATURE_NAME_V1,
                FIND_CONTACT_FEATURE_COMMAND_V1);
        CommandDetails findContactByGitHubCommandDetails = new CommandDetails(FIND_CONTACT_FEATURE_NAME_V3,
                FIND_CONTACT_FEATURE_COMMAND_V3);
        CommandDetails findContactByTagCommandDetails = new CommandDetails(FIND_CONTACT_FEATURE_NAME_V2,
                FIND_CONTACT_FEATURE_COMMAND_V2);
        CommandDetails findContactByTelegramCommandDetails = new CommandDetails(FIND_CONTACT_FEATURE_NAME_V4,
                FIND_CONTACT_FEATURE_COMMAND_V4);
        CommandDetails showContactByNameCommandDetails = new CommandDetails(SHOW_CONTACT_FEATURE_NAME_V1,
                SHOW_CONTACT_FEATURE_COMMAND_V1);
        CommandDetails showContactByIndexCommandDetails = new CommandDetails(SHOW_CONTACT_FEATURE_NAME_V2,
                SHOW_CONTACT_FEATURE_COMMAND_V2);
        CommandDetails listAllContactsCommandDetails = new CommandDetails(LIST_CONTACTS_FEATURE_NAME,
                LIST_CONTACTS_FEATURE_COMMAND);
        CommandDetails favoriteContactCommandDetails = new CommandDetails(FAVORITE_CONTACT_FEATURE_NAME,
                FAVORITE_CONTACT_FEATURE_COMMAND);
        CommandDetails unFavoriteContactCommandDetails = new CommandDetails(UNFAVORITE_CONTACT_FEATURE_NAME,
                UNFAVORITE_CONTACT_FEATURE_COMMAND);
        CommandDetails addTagsCommandDetails = new CommandDetails(ADD_TAGS_FEATURE_NAME, ADD_TAGS_FEATURE_COMMAND);
        CommandDetails removeTagsCommandDetails = new CommandDetails(REMOVE_TAGS_FEATURE_NAME,
                REMOVE_TAGS_FEATURE_COMMAND);
        CommandDetails addAndRemoveTagsSimultaneouslyCommandDetails = new CommandDetails(
                ADD_AND_REMOVE_TAGS_SIMULTANEOUSLY_FEATURE_NAME, ADD_AND_REMOVE_TAGS_SIMULTANEOUSLY_FEATURE_COMMAND);
        CommandDetails importContactCsvCommandDetails = new CommandDetails(IMPORT_CONTACT_CSV_FEATURE_NAME,
                IMPORT_CONTACT_CSV_FEATURE_COMMAND);
        CommandDetails importContactJsonCommandDetails = new CommandDetails(IMPORT_CONTACT_JSON_FEATURE_NAME,
                IMPORT_CONTACT_JSON_FEATURE_COMMAND);
        CommandDetails exportContactCsvCommandDetails = new CommandDetails(EXPORT_CONTACT_CSV_FEATURE_NAME,
                EXPORT_CONTACT_CSV_FEATURE_COMMAND);
        CommandDetails exportContactJsonCommandDetails = new CommandDetails(EXPORT_CONTACT_JSON_FEATURE_NAME,
                EXPORT_CONTACT_JSON_FEATURE_COMMAND);
        CommandDetails helpWindowCommandDetails = new CommandDetails(HELP_FEATURE_NAME,
                HELP_FEATURE_COMMAND);
        CommandDetails exitAppCommandDetails = new CommandDetails(EXIT_APP_FEATURE_NAME,
                EXIT_APP_FEATURE_COMMAND);

        helpSectionCommandDetails.add(addNewContactCommandDetails);
        helpSectionCommandDetails.add(deleteContactCommandDetails);
        helpSectionCommandDetails.add(deleteAllContactsCommandDetails);
        helpSectionCommandDetails.add(editContactCommandDetails);
        helpSectionCommandDetails.add(editProfileCommandDetails);
        helpSectionCommandDetails.add(findContactByNameCommandDetails);
        helpSectionCommandDetails.add(findContactByGitHubCommandDetails);
        helpSectionCommandDetails.add(findContactByTagCommandDetails);
        helpSectionCommandDetails.add(findContactByTelegramCommandDetails);
        helpSectionCommandDetails.add(showContactByNameCommandDetails);
        helpSectionCommandDetails.add(showContactByIndexCommandDetails);
        helpSectionCommandDetails.add(listAllContactsCommandDetails);
        helpSectionCommandDetails.add(favoriteContactCommandDetails);
        helpSectionCommandDetails.add(unFavoriteContactCommandDetails);
        helpSectionCommandDetails.add(addTagsCommandDetails);
        helpSectionCommandDetails.add(removeTagsCommandDetails);
        helpSectionCommandDetails.add(addAndRemoveTagsSimultaneouslyCommandDetails);
        helpSectionCommandDetails.add(importContactCsvCommandDetails);
        helpSectionCommandDetails.add(importContactJsonCommandDetails);
        helpSectionCommandDetails.add(exportContactCsvCommandDetails);
        helpSectionCommandDetails.add(exportContactJsonCommandDetails);
        helpSectionCommandDetails.add(helpWindowCommandDetails);
        helpSectionCommandDetails.add(exitAppCommandDetails);
    }

    /**
     * Opens the user guide in the default web browser on
     * the system.
     */
    @FXML
    public void openUserGuide() {
        try {
            Desktop.getDesktop().browse(new URL(USERGUIDE_URL).toURI());
        } catch (IOException e) {
            logger.severe("Could not open the browser to show the user guide.");
        } catch (URISyntaxException e) {
            logger.severe("URL to user guide not formatted well.");
        }
    }
}
