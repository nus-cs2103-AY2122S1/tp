package dash.ui;

import java.util.logging.Logger;

import dash.commons.core.LogsCenter;
import dash.model.help.HelpCommand;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class HelpPanel extends UiPart<Region> {
    private static final String FXML = "HelpPanel.fxml";
    private static final String COPY_MESSAGE_UG = "User Guide URL copied to clipboard!";
    private static final String COPY_MESSAGE_DG = "Developer Guide URL copied to clipboard!";

    private static final String HELP_CONTENT = "Acceptable Date/Time formats:\n"
            + "Date: dd/MM/yyyy , dd-MM-yyyy , yyyy/MM/dd , yyyy-MM-dd, dd MMM yyyy\n"
            + "Time: HHmm , hh:mm a\n\n"
            + "Icons and graphics taken from https://icons8.com/\n"
            + "For more detailed information,"
            + " visit our User Guide or Developer Guide by clicking the buttons below to copy \n"
            + "their URLs. \n\n";

    private static final String GENERAL_CONTACTS_HEADER = "contacts";
    private static final String GENERAL_CONTACTS_DESC = "Switches to Contacts Tab.";

    private static final String GENERAL_TASKS_HEADER = "tasks";
    private static final String GENERAL_TASKS_DESC = "Switches to Tasks Tab.";

    private static final String GENERAL_HELP_HEADER = "help";
    private static final String GENERAL_HELP_DESC = "Switches to Help Tab.";

    private static final String CONTACTS_ADD_HEADER = "add n/NAME [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]";
    private static final String CONTACTS_ADD_DESC = "Adds a contact with the specified fields.";

    private static final String CONTACTS_EDIT_HEADER = "edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL a/ADDRESS] "
            + "[t/TAG]";
    private static final String CONTACTS_EDIT_DESC = "Edits specified fields of a given contact.";

    private static final String CONTACTS_DELETE_HEADER = "delete INDEX";
    private static final String CONTACTS_DELETE_DESC = "Deletes a contact with the given index.";

    private static final String CONTACTS_FIND_A_HEADER = "find NAME";
    private static final String CONTACTS_FIND_A_DESC = "Finds a contact with the given name.";

    private static final String CONTACTS_FIND_B_HEADER = "find [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]";
    private static final String CONTACTS_FIND_B_DESC = "Finds a contact with the given fields.";

    private static final String CONTACTS_CLEAR_HEADER = "clear";
    private static final String CONTACTS_CLEAR_DESC = "Clears all contacts from the contacts list.";

    private static final String CONTACTS_TAG_HEADER = "tag INDEX [t/TAG]";
    private static final String CONTACTS_TAG_DESC = "Add the tags of the given contact with the given tags.";

    private static final String TASKS_ADD_HEADER = "add d/DESCRIPTION [dt/DATE,TIME] [t/TAG]";
    private static final String TASKS_ADD_DESC = "Adds a task with the specified fields.";

    private static final String TASKS_EDIT_HEADER = "edit INDEX [d/DESCRIPTION] [dt/DATE, TIME] [p/PERSON] [t/TAG]";
    private static final String TASKS_EDIT_DESC = "Edits specified fields of a given task.";

    private static final String TASKS_DELETE_HEADER = "delete INDEX";
    private static final String TASKS_DELETE_DESC = "Deletes a task with the given index.";

    private static final String TASKS_FIND_HEADER = "find [d/DESCRIPTION] [dt/DATE, TIME] [p/PERSON] [t/TAG] "
            + "[c/COMPLETION STATUS (TRUE OR FALSE)] (at least one)";
    private static final String TASKS_FIND_DESC = "Finds a task with the given fields.";

    private static final String TASKS_CLEAR_HEADER = "clear";
    private static final String TASKS_CLEAR_DESC = "Clears all tasks from the task list.";

    private static final String TASKS_TAG_HEADER = "tag INDEX [t/TAG]";
    private static final String TASKS_TAG_DESC = "Add the tags of the given task with the given tags.";

    private static final String TASKS_ASSIGN_HEADER = "assign INDEX [p/INDEX]";
    private static final String TASKS_ASSIGN_DESC = "Assign a given contact to a task.";

    private static final String TASKS_COMPLETE_HEADER = "complete INDEX";
    private static final String TASKS_COMPLETE_DESC = "Mark a task as complete.";

    private static final String TASKS_UPCOMING_HEADER = "upcoming";
    private static final String TASKS_UPCOMING_DESC = "Finds all incomplete tasks whose Date/Time are after the "
            + "current Date/Time (as determined locally on your computer). These tasks will be sorted chronologically, "
            + "with tasks that are closer to the current Date/Time first.";

    private static final String USERGUIDE_URL = "https://ay2122s1-cs2103t-w15-2.github.io/tp/UserGuide.html";

    private static final String DEVGUIDE_URL = "https://ay2122s1-cs2103t-w15-2.github.io/tp/DeveloperGuide.html";

    private final Logger logger = LogsCenter.getLogger(HelpPanel.class);

    private ResultDisplay resultDisplay;

    @FXML
    private VBox container;

    @FXML
    private Label helpContent;

    @FXML
    private Label bannerLabel;

    @FXML
    private GridPane commandContainer;

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
        this.resultDisplay = resultDisplay;
        helpContent.setText(HELP_CONTENT);
    }

    /**
     * Creates a new ObservableList to store General commands.
     *
     */
    public ObservableList<HelpCommand> initialiseHelpListGeneral() {
        ObservableList<HelpCommand> generalList = FXCollections.observableArrayList();
        generalList.add(new HelpCommand(GENERAL_CONTACTS_HEADER, GENERAL_CONTACTS_DESC));
        generalList.add(new HelpCommand(GENERAL_TASKS_HEADER, GENERAL_TASKS_DESC));
        generalList.add(new HelpCommand(GENERAL_HELP_HEADER, GENERAL_HELP_DESC));
        return generalList;
    }

    /**
     * Creates a new ObservableList to store Contact commands.
     *
     */
    public ObservableList<HelpCommand> initialiseHelpListContact() {
        ObservableList<HelpCommand> contactList = FXCollections.observableArrayList();
        contactList.add(new HelpCommand(CONTACTS_ADD_HEADER, CONTACTS_ADD_DESC));
        contactList.add(new HelpCommand(CONTACTS_EDIT_HEADER, CONTACTS_EDIT_DESC));
        contactList.add(new HelpCommand(CONTACTS_DELETE_HEADER, CONTACTS_DELETE_DESC));
        contactList.add(new HelpCommand(CONTACTS_FIND_A_HEADER, CONTACTS_FIND_A_DESC));
        contactList.add(new HelpCommand(CONTACTS_FIND_B_HEADER, CONTACTS_FIND_B_DESC));
        contactList.add(new HelpCommand(CONTACTS_CLEAR_HEADER, CONTACTS_CLEAR_DESC));
        contactList.add(new HelpCommand(CONTACTS_TAG_HEADER, CONTACTS_TAG_DESC));
        return contactList;
    }

    /**
     * Creates a new ObservableList to store Task commands.
     *
     */
    public ObservableList<HelpCommand> initialiseHelpListTask() {
        ObservableList<HelpCommand> taskList = FXCollections.observableArrayList();
        taskList.add(new HelpCommand(TASKS_ADD_HEADER, TASKS_ADD_DESC));
        taskList.add(new HelpCommand(TASKS_EDIT_HEADER, TASKS_EDIT_DESC));
        taskList.add(new HelpCommand(TASKS_DELETE_HEADER, TASKS_DELETE_DESC));
        taskList.add(new HelpCommand(TASKS_FIND_HEADER, TASKS_FIND_DESC));
        taskList.add(new HelpCommand(TASKS_CLEAR_HEADER, TASKS_CLEAR_DESC));
        taskList.add(new HelpCommand(TASKS_TAG_HEADER, TASKS_TAG_DESC));
        taskList.add(new HelpCommand(TASKS_ASSIGN_HEADER, TASKS_ASSIGN_DESC));
        taskList.add(new HelpCommand(TASKS_COMPLETE_HEADER, TASKS_COMPLETE_DESC));
        taskList.add(new HelpCommand(TASKS_UPCOMING_HEADER, TASKS_UPCOMING_DESC));
        return taskList;
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

    /**
     * Returns the GridPane holding the help panel items.
     *
     * @return The GridPane container.
     */
    public GridPane getCommandContainer() {
        return this.commandContainer;
    }

}
