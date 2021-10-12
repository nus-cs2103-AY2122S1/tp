package seedu.fast.ui;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.fast.commons.core.LogsCenter;
import seedu.fast.logic.commands.AddCommand;
import seedu.fast.logic.commands.AppointmentCommand;
import seedu.fast.logic.commands.ClearCommand;
import seedu.fast.logic.commands.DeleteCommand;
import seedu.fast.logic.commands.EditCommand;
import seedu.fast.logic.commands.ExitCommand;
import seedu.fast.logic.commands.FindCommand;
import seedu.fast.logic.commands.HelpCommand;
import seedu.fast.logic.commands.ListCommand;
import seedu.fast.logic.commands.RemarkCommand;
import seedu.fast.logic.commands.SortCommand;
import seedu.fast.logic.parser.ParserUtil;
import seedu.fast.model.tag.PriorityTag;
import seedu.fast.model.tag.Tag;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2122s1-cs2103t-t09-4.github.io/tp/";
    public static final String HELP_MESSAGE = "View our user guide: " + USERGUIDE_URL;
    public static final String[] COMMAND_LIST = ParserUtil.COMMAND_LIST;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    private static final String QUICK_START_MESSAGE = "Please select a command in the dropdown to view "
        + "the usage for each command! \n\n"
        + "Financial Advisor Smart Tracker (FAST) is a desktop app for"
        + "managing clients, optimized for use via a Command Line Interface (CLI) while still having the "
        + "benefits of a Graphical User Interface (GUI). If you can type fast, FAST can get your contact "
        + "management tasks done faster than traditional GUI apps.";
    private static final String ADD_COMMAND_USAGE = AddCommand.MESSAGE_USAGE;
    private static final String APPOINTMENT_COMMAND_USAGE = AppointmentCommand.MESSAGE_USAGE;
    private static final String CLEAR_COMMAND_USAGE = ClearCommand.MESSAGE_USAGE;
    private static final String DELETE_COMMAND_USAGE = DeleteCommand.MESSAGE_USAGE;
    private static final String EDIT_COMMAND_USAGE = EditCommand.MESSAGE_USAGE;
    private static final String EXIT_COMMAND_USAGE = ExitCommand.MESSAGE_USAGE;
    private static final String FIND_COMMAND_USAGE = FindCommand.MESSAGE_USAGE;
    private static final String LIST_COMMAND_USAGE = ListCommand.MESSAGE_USAGE;
    private static final String HELP_COMMAND_USAGE = HelpCommand.MESSAGE_USAGE;
    private static final String REMARK_COMMAND_USAGE = RemarkCommand.MESSAGE_USAGE;
    private static final String SORT_COMMAND_USAGE = SortCommand.MESSAGE_USAGE;
    private static final String TAG_USAGE = Tag.MESSAGE_USAGE;
    private static final String PRIORITY_TAG_USAGE = PriorityTag.MESSAGE_USAGE;

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    @FXML
    private Label commandInstruction;

    @FXML
    private ComboBox<String> commandList;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
        commandList.setItems(FXCollections.observableArrayList(COMMAND_LIST));
        commandInstruction.setText(QUICK_START_MESSAGE);

        // show different command usage depending on the selected command
        EventHandler<ActionEvent> event =
            e -> commandInstruction.setText(showCommandUsage(commandList.getValue()));
        commandList.setOnAction(event);
    }

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root, String helpArg) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
        commandList.setItems(FXCollections.observableArrayList(COMMAND_LIST));

        // For "help" inputs
        if (helpArg.equals("")) {
            commandInstruction.setText(QUICK_START_MESSAGE);

        // For "help COMMAND" inputs
        } else {
            commandList.getSelectionModel().select(helpArg);
            commandInstruction.setText(showCommandUsage(helpArg));
        }

        // show different command usage depending on the selected command
        EventHandler<ActionEvent> event =
            e -> commandInstruction.setText(showCommandUsage(commandList.getValue()));
        commandList.setOnAction(event);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow(String helpArg) {
        this(new Stage(), helpArg);
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
    public void focus(String command) {
        getRoot().requestFocus();
        if (!ParserUtil.matchArgs(command).equals("")) {
            commandList.getSelectionModel().select(command);
            commandInstruction.setText(showCommandUsage(command));
        } else {
            // when focusing back, the combobox cannot go back to "(Select a command)"
            // Hence we default to Quick Start
            commandList.getSelectionModel().select("Quick Start");
            commandInstruction.setText(showCommandUsage("Quick Start"));
        }
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
     * Displays the command usage.
     *
     * @param commandName Command to be explained.
     * @return The proper usage of the command.
     */
    public String showCommandUsage(String commandName) {
        switch (commandName) {
        case "Quick Start":
            return QUICK_START_MESSAGE;
        case "Add":
            return ADD_COMMAND_USAGE;
        case "Appointment":
            return APPOINTMENT_COMMAND_USAGE;
        case "Clear":
            return CLEAR_COMMAND_USAGE;
        case "Delete":
            return DELETE_COMMAND_USAGE;
        case "Edit":
            return EDIT_COMMAND_USAGE;
        case "Exit":
            return EXIT_COMMAND_USAGE;
        case "Find":
            return FIND_COMMAND_USAGE;
        case "Help":
            return HELP_COMMAND_USAGE;
        case "List":
            return LIST_COMMAND_USAGE;
        case "Remark":
            return REMARK_COMMAND_USAGE;
        case "Sort":
            return SORT_COMMAND_USAGE;
        case "Tag" :
            return TAG_USAGE;
        case "Priority Tag" :
            return PRIORITY_TAG_USAGE;
        case "Misc":
            return "Coming soon!";
        default:
            return "Something's wrong, I can feel it";
        }
    }
}
