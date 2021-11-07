package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddDeadlineTaskCommand;
import seedu.address.logic.commands.AddEventTaskCommand;
import seedu.address.logic.commands.AddGroupCommand;
import seedu.address.logic.commands.AddMemberCommand;
import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.logic.commands.AddTodoTaskCommand;
import seedu.address.logic.commands.ClearAllCommand;
import seedu.address.logic.commands.ClearGroupsCommand;
import seedu.address.logic.commands.ClearStudentsCommand;
import seedu.address.logic.commands.ClearTasksCommand;
import seedu.address.logic.commands.DeleteGroupCommand;
import seedu.address.logic.commands.DeleteMemberCommand;
import seedu.address.logic.commands.DeleteStudentCommand;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.commands.EditGroupCommand;
import seedu.address.logic.commands.EditStudentCommand;
import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindGroupCommand;
import seedu.address.logic.commands.FindStudentCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListGroupCommand;
import seedu.address.logic.commands.ListStudentCommand;
import seedu.address.logic.commands.ListTaskCommand;
import seedu.address.logic.commands.MarkStudentAttCommand;
import seedu.address.logic.commands.MarkStudentPartCommand;
import seedu.address.logic.commands.MarkTaskDoneCommand;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USER_GUIDE_URL = "https://ay2122s1-cs2103-w14-4.github.io/tp/UserGuide.html#quick-start";
    public static final String HELP_MESSAGE = "For more information, refer to the user guide: " + USER_GUIDE_URL;
    private static final ObservableList<CommandCell> commandList = FXCollections.observableArrayList(
            new CommandCell(ListStudentCommand.COMMAND_WORD, "Lists all students"),
            new CommandCell(AddStudentCommand.COMMAND_WORD, AddStudentCommand.MESSAGE_USAGE),
            new CommandCell(DeleteStudentCommand.COMMAND_WORD, DeleteStudentCommand.MESSAGE_USAGE),
            new CommandCell(EditStudentCommand.COMMAND_WORD, EditStudentCommand.MESSAGE_USAGE),
            new CommandCell(MarkStudentAttCommand.COMMAND_WORD, MarkStudentAttCommand.MESSAGE_USAGE),
            new CommandCell(MarkStudentPartCommand.COMMAND_WORD, MarkStudentPartCommand.MESSAGE_USAGE),
            new CommandCell(FindStudentCommand.COMMAND_WORD, FindStudentCommand.MESSAGE_USAGE),
            new CommandCell(ClearStudentsCommand.COMMAND_WORD, "Clears all students from the student list"),
            new CommandCell(ListTaskCommand.COMMAND_WORD, "Lists all tasks"),
            new CommandCell(AddTodoTaskCommand.COMMAND_WORD, AddTodoTaskCommand.MESSAGE_USAGE),
            new CommandCell(AddDeadlineTaskCommand.COMMAND_WORD, AddDeadlineTaskCommand.MESSAGE_USAGE),
            new CommandCell(AddEventTaskCommand.COMMAND_WORD, AddEventTaskCommand.MESSAGE_USAGE),
            new CommandCell(DeleteTaskCommand.COMMAND_WORD, DeleteTaskCommand.MESSAGE_USAGE),
            new CommandCell(EditTaskCommand.COMMAND_WORD, EditTaskCommand.MESSAGE_USAGE),
            new CommandCell(MarkTaskDoneCommand.COMMAND_WORD, MarkTaskDoneCommand.MESSAGE_USAGE),
            new CommandCell(ClearTasksCommand.COMMAND_WORD, "Clears all tasks from the task list"),
            new CommandCell(ListGroupCommand.COMMAND_WORD, "lists all groups"),
            new CommandCell(AddGroupCommand.COMMAND_WORD, AddGroupCommand.MESSAGE_USAGE),
            new CommandCell(EditGroupCommand.COMMAND_WORD, EditGroupCommand.MESSAGE_USAGE),
            new CommandCell(AddMemberCommand.COMMAND_WORD, AddMemberCommand.MESSAGE_USAGE),
            new CommandCell(DeleteMemberCommand.COMMAND_WORD, DeleteMemberCommand.MESSAGE_USAGE),
            new CommandCell(DeleteGroupCommand.COMMAND_WORD, DeleteGroupCommand.MESSAGE_USAGE),
            new CommandCell(FindGroupCommand.COMMAND_WORD, FindGroupCommand.MESSAGE_USAGE),
            new CommandCell(ClearGroupsCommand.COMMAND_WORD, "Clears all groups from the groups list"),
            new CommandCell(ClearAllCommand.COMMAND_WORD, "Clears all data in tApp"),
            new CommandCell(HelpCommand.COMMAND_WORD, HelpCommand.MESSAGE_USAGE),
            new CommandCell(ExitCommand.COMMAND_WORD, "Exits from tApp"));

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    @FXML
    private TableView<CommandCell> commandSummaryTable;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
        TableColumn<CommandCell, String> column1 = new TableColumn<>("Command");
        column1.setCellValueFactory(new PropertyValueFactory<>("commandWord"));
        column1.setPrefWidth(150);

        TableColumn<CommandCell, String> column2 = new TableColumn<>("Usage");
        column2.setCellValueFactory(new PropertyValueFactory<>("usage"));

        commandSummaryTable.setItems(commandList);
        commandSummaryTable.getColumns().add(column1);
        commandSummaryTable.getColumns().add(column2);
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
        url.putString(USER_GUIDE_URL);
        clipboard.setContent(url);
    }

    /**
     * A command cell class.
     */
    public static class CommandCell {
        public final String commandWord;
        public final String usage;

        /**
         * Creates a command cell with the given string parameters.
         *
         * @param commandWord The command word of the command.
         * @param usage The String usage message.
         */
        public CommandCell(String commandWord, String usage) {
            this.commandWord = commandWord;
            this.usage = usage;
        }

        public String getCommandWord() {
            return commandWord;
        }

        public String getUsage() {
            return usage;
        }
    }
}
