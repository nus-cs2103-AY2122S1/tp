package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.util.CommandSummary;
import seedu.address.ui.util.TableUtil;

/**
 * Controller for a help page.
 */
public class HelpWindow extends ExternalWindow {
    public static final String USERGUIDE_URL = "https://ay2122s1-cs2103t-f13-3.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Select the table cell(s) and press CTRL + C or CMD + C to copy.\n"
            + "You may sort the rows alphabetically by clicking the right end of each column header.\n"
            + "Refer to the user guide: " + USERGUIDE_URL;

    private static final String TEXT_COLOR = "424874";
    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";
    private static final double ACTION_WIDTH = 0.23;
    private static final double FORMAT_WIDTH = 0.4;
    private static final double EXAMPLE_WIDTH = 0.38;

    private final ObservableList<CommandSummary> list = CommandSummary.getCommandSummaryList();

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
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        setColumnProperties(action, "action", ACTION_WIDTH);
        setColumnProperties(format, "format", FORMAT_WIDTH);
        setColumnProperties(example, "example", EXAMPLE_WIDTH);
        table.setItems(list);
        table.setColumnResizePolicy(p -> true);

        // enable multi-selection
        table.getSelectionModel().setCellSelectionEnabled(true);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // enable copy/paste
        TableUtil.installCopyPasteHandler(table);
        helpMessage.setText(HELP_MESSAGE);
    }

    private void setColumnProperties(TableColumn<CommandSummary, String> tableCol,
            String colName, double colWidth) {
        tableCol.setCellFactory(getTableColumnTableCellCallback(tableCol));
        tableCol.setCellValueFactory(new PropertyValueFactory<>(colName));
        tableCol.prefWidthProperty().bind(table.widthProperty().multiply(colWidth));
    }

    /**
     * Returns the call back to create a wrap-text table cell.
     *
     * @param field Column field to create the wrap-text table cell for.
     * @return Callback to create a wrap-text table cell.
     */
    private Callback<TableColumn<CommandSummary, String>,
            TableCell<CommandSummary, String>> getTableColumnTableCellCallback(
                    TableColumn<CommandSummary, String> field) {
        // @@author: James_D - reused with modifications
        //reused from: https://stackoverflow.com/questions/22732013/javafx-tablecolumn-text-wrapping
        return tc -> {
            TableCell<CommandSummary, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            cell.setPadding(new Insets(7.5));
            text.wrappingWidthProperty().bind(field.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            text.setFill(Color.web(TEXT_COLOR));
            return cell;
        };
    }

    /**
     * Shows the help window.
     */
    @Override
    public void show() {
        super.show();
        logger.fine("Showing help page about the application.");
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        TableUtil.copyStringToClipBoard(USERGUIDE_URL);
    }
}
