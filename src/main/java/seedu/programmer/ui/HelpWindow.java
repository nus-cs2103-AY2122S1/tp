package seedu.programmer.ui;

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

/**
 * Controller for a help page
 */
public class HelpWindow extends PopupWindow {

    public static final String USERGUIDE_URL = "https://ay2122s1-cs2103-f09-3.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Full user guide: " + USERGUIDE_URL;
    private static final String INSTRUCTION = "Here is the feature list of ProgrammerError:";

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
                "add -n <NAME> -sid <STUDENT_ID> -cid <CLASS_ID> -email <email>",
                "Create records of individual students");
        FeatureTableItem view = new FeatureTableItem("Filter",
                "filter -n <NAME>/-sid <STUDENT_ID>/-cid <CLASS_ID>/-email <EMAIL>",
                "Filter the list with the specified parameters. Multiple fields can be used as the filter criteria");
        FeatureTableItem edit = new FeatureTableItem("Edit",
                "edit <INDEX_IN_LIST> -n <NAME>/-sid <STUDENT_ID>/-cid <CLASS_ID>/-email <email>",
                "Edit the specified field to the new value. Multiple fields can be edited at the same time");
        FeatureTableItem delete = new FeatureTableItem("Delete",
                "delete <INDEX_IN_LIST>",
                "Delete the specified student's record");
        FeatureTableItem show = new FeatureTableItem("Show",
                "show <INDEX_IN_LIST>",
                "Show the lab results of the chosen student");
        FeatureTableItem exit = new FeatureTableItem("Exit",
                "exit/press Exit button/press F1",
                "Exit ProgrammerError");
        FeatureTableItem help = new FeatureTableItem("Help",
                "help/press Help button/press F2",
                "Open this Help Window");
        FeatureTableItem download = new FeatureTableItem("Download",
                "Press Download Button/press F3",
                "Download the database as a csv file");
        FeatureTableItem upload = new FeatureTableItem("Upload",
                "Press Upload Button/press F4",
                "Upload a csv containing students' details to ProgrammerError's database");
        FeatureTableItem addLab = new FeatureTableItem("AddLab",
                "addlab -t <LAB_NUMBER> -ts <TOTAL_SCORE>",
                "Create a lab record for all students in the database");
        FeatureTableItem delLab = new FeatureTableItem("DeleteLab",
                "dellab -t <LAB_NUMBER>",
                "Remove the lab with the selected number from ProgrammerError");
        FeatureTableItem editLab = new FeatureTableItem("EditLab",
                "editlab -i<INDEX_IN_LIST> -t <LAB_NUMBER> -s <ACTUAL_SCORE>",
                "Assign an actual score to the student specified by the index");
        featureTableItems = FXCollections.observableArrayList();
        featureTableItems.addAll(list, purge, fill, add, view, edit, delete, show,
                addLab, editLab, delLab, exit, help, download, upload);
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
