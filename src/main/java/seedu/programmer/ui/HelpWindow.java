package seedu.programmer.ui;

import java.util.logging.Logger;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.programmer.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2122s1-cs2103-f09-3.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Full user guide: " + USERGUIDE_URL;
    private static final String INSTRUCTION = "Here is the feature list of ProgrammerError:";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    private ObservableList<FeatureTableItem> featureTableItems;

    @FXML
    private Button copyButton;

    @FXML
    private Label instruction;

    @FXML
    private Label helpMessage;

    @FXML
    private TextArea featureList;

    @FXML
    private TableView<FeatureTableItem> featureTable;


    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        instruction.setText(INSTRUCTION);
        helpMessage.setText(HELP_MESSAGE);
        initializeFeatureList();
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    private void initializeFeatureList() {
        initializeFeatureTableItem();

        TableColumn<FeatureTableItem, String> featureColumn = new TableColumn<>("Feature");
        featureColumn.setCellValueFactory(new PropertyValueFactory<>("feature"));
        TableColumn<FeatureTableItem, String> commandColumn = new TableColumn<>("Command Syntax");
        commandColumn.setCellValueFactory(new PropertyValueFactory<>("command"));
        TableColumn<FeatureTableItem, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        featureTable.getColumns().add(featureColumn);
        featureTable.getColumns().add(commandColumn);
        featureTable.getColumns().add(descriptionColumn);
        featureTable.setItems(featureTableItems);
    }

    private void initializeFeatureTableItem(FeatureTableItem... items) {
        FeatureTableItem list = new FeatureTableItem("List",
                "list",
                "List all students records in the database");
        FeatureTableItem purge = new FeatureTableItem("Purge",
                "purge",
                "Purge Sample Data (Delete all)");
        FeatureTableItem fill = new FeatureTableItem("Fill",
                "fill",
                "Fill ProgrammerError with sample data, only when the database is empty");
        FeatureTableItem add = new FeatureTableItem("Add",
                "add -n <NAME> -sid <STUDENT_ID> -cid <CLASS_ID> -grade <GRADE>",
                "Create records of individual students");
        FeatureTableItem view = new FeatureTableItem("View",
                "view -n <NAME>/-sid <STUDENT_ID>/-cid <CLASS_ID>/-grade <GRADE>",
                "View the student with the specified field. Multiple fields can be used as the search criteria");
        FeatureTableItem edit = new FeatureTableItem("Edit",
                "edit <INDEX_IN_LIST> -n <NAME>/-sid <STUDENT_ID>/-cid <CLASS_ID>/-grade <GRADE>",
                "Edit the specified field to the new value. Multiple fields can be edited at the same time");
        FeatureTableItem delete = new FeatureTableItem("Delete",
                "delete <INDEX_IN_LIST>",
                "Delete the specified student's record");
        FeatureTableItem show = new FeatureTableItem("Show",
                "show <INDEX_IN_LIST>",
                "Show the lab results of the chosen student");
        featureTableItems = FXCollections.observableArrayList();
        featureTableItems.addAll(list, purge, fill, add, view, edit, delete, show);
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

    protected static class FeatureTableItem {
        private final SimpleStringProperty feature;
        private final SimpleStringProperty command;
        private final SimpleStringProperty description;


        public FeatureTableItem(String feature, String command, String description) {
            this.feature = new SimpleStringProperty(feature);
            this.command = new SimpleStringProperty(command);
            this.description = new SimpleStringProperty(description);
        }

        public String getFeature() {
            return feature.get();
        }

        public String getCommand() {
            return command.get();
        }

        public String getDescription() {
            return description.get();
        }
    }
}
