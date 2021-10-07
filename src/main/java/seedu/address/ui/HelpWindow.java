package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.HelpCommand;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://se-education.org/addressbook-level3/UserGuide.html";
    public static final String HELP_MESSAGE = "Refer to the user guide:\n" + USERGUIDE_URL;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    // TODO: Replace the constructor arguments with static variable after quick start branch has been merged.
    private final ObservableList<CommandSummary> list = FXCollections.observableArrayList(
            new CommandSummary("Help", HelpCommand.COMMAND_WORD, HelpCommand.COMMAND_WORD),
            new CommandSummary("Add Student",
                    "add n/NAME a/ADDRESS [p/PHONE_NUMBER] [e/EMAIL] [pp/PARENT_PHONE_NUMBER] [pe/PARENT_EMAIL]"
                            + " [sch/SCHOOL] [stream/ACAD_STREAM] [lvl/ACAD_LEVEL] [f/OUTSTANDING_FEES] "
                            + "[r/REMARKS] [t/TAG]…",
                    "add n/James Ho a/123, Clementi Rd, 1234665 p/22224444 e/jamesho@example.com pp/33335555 "
                            + "pe/danielho@example.com sch/DHS lvl/Y1 f/50 r/retainee t/cousin"),
            new CommandSummary("View Student", "view INDEX", "view 1"),
            new CommandSummary("Edit Student",
                    "edit INDEX [n/NAME] [a/ADDRESS] [p/PHONE] [e/EMAIL] [pp/PARENT_PHONE_NUMBER] "
                            + "[pe/PARENT_EMAIL] [sch/SCHOOL] [stream/ACAD_STREAM] [lvl/ACAD_LEVEL]"
                            + " [f/OUTSTANDING_FEES] [r/REMARK] [t/TAG]…\n",
                    "edit 2 n/James Lee e/jameslee@example.com"),
            new CommandSummary("Delete Student", "delete INDEX", "delete 1"),
            new CommandSummary("List Students", "list", "list"),
            new CommandSummary("Find Students",
                    "find [n/NAME_KEYWORD …] [a/ADDRESS_KEYWORD …] [e/EMAIL_KEYWORD …] [p/PHONE_KEYWORD …]"
                            + " [sch/SCHOOL_KEYWORD …] [stream/ACAD_STREAM_KEYWORD …] [lvl/ACAD_LEVEL_KEYWORD …]",
                    "find n/James Tan a/clementi sch/NUS"),
            new CommandSummary("View Tags", "tag", "tag"),
            new CommandSummary("Filter Students",
                    "filter cond/{all | any | none} t/TAG [t/MORE_TAGS]…",
                    "filter cond/all t/Sec1 t/zoom"),
            new CommandSummary("Add Lesson",
                    "ladd INDEX [recurring/] date/dd MMM yyyy start/HH:mm end/HH:mm subject/SUBJECT"
                            + " [hw/HOMEWORK]\n",
                    "ladd 1 recurring/ date/16 Sep 2021 start/15:00 end/16:00 subject/Math"),
            new CommandSummary("Delete Lesson", "ldelete INDEX LESSON_INDEX", "ldelete 2 1"),
            new CommandSummary("View Schedule", "schedule", "schedule"),
            new CommandSummary("Clear All Data", "clear", "clear"),
            new CommandSummary("Undo Command", "undo", "undo"),
            new CommandSummary("Redo Command", "redo", "redo"),
            new CommandSummary("Exit Program", "exit", "exit")
    );

    @FXML
    private TableView<CommandSummary> table;

    @FXML
    private TableColumn<CommandSummary, String> action;

    @FXML
    private TableColumn<CommandSummary, String> format;

    @FXML
    private TableColumn<CommandSummary, String> example;

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        action.setCellValueFactory(new PropertyValueFactory<>("action"));

        // @@author: James_D - reused
        // Lines 102 - 110 reused from: https://stackoverflow.com/questions/22732013/javafx-tablecolumn-text-wrapping
        format.setCellFactory(tc -> {
            TableCell<CommandSummary, String> cell = new WrapTextEditableTableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(format.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });

        format.setCellValueFactory(new PropertyValueFactory<>("format"));
        example.setCellFactory(TextFieldTableCell.forTableColumn());
        example.setCellValueFactory(new PropertyValueFactory<>("example"));
        table.setEditable(true);
        table.setItems(list);
        table.setColumnResizePolicy(p -> true);
        format.setOnEditCommit(event -> event.getTableView().refresh());
        example.setOnEditCommit(event -> event.getTableView().refresh());
        helpMessage.setText(HELP_MESSAGE);
        root.getScene().setFill(Color.web("424874"));
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
