package seedu.address.ui;

import java.util.logging.Logger;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.ClientTotalOrder;

/**
 * Controller for a total orders page
 */
public class TotalOrdersWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(TotalOrdersWindow.class);
    private static final String FXML = "TotalOrdersWindow.fxml";

    private final Logic logic;

    @FXML
    private TableView<ClientTotalOrder> table;
    @FXML
    private TableColumn<ClientTotalOrder, String> clientCol;
    @FXML
    private TableColumn<ClientTotalOrder, Double> totalCol;

    /**
     * Creates a {@code TotalOrdersWindow} with the given {@code Logic}.
     */
    public TotalOrdersWindow(Logic logic) {
        super(FXML, new Stage());
        this.logic = logic;
        setCloseOnEsc();
        formatTotalColumn();
    }

    /**
     * Shows the total orders window.
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
        logger.fine("Showing total orders.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the total orders window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the total orders window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the total orders window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Reloads ClientTotalOrders items from AddressBook.
     */
    public void reloadData() {
        table.setItems(logic.getClientTotalOrders());
        clientCol.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("totalOrder"));
    }

    /**
     * Formats the total column to always display amount with 2 decimal places.
     */
    private void formatTotalColumn() {
        // Solution below adapted from https://stackoverflow.com/a/34924734/13896417
        totalCol.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(Double value, boolean empty) {
                super.updateItem(value, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(String.format("%.2f ", value));
                }
            }
        });
    }

    private void setCloseOnEsc() {
        Stage stage = getRoot();
        // Solution below adapted from https://stackoverflow.com/a/42104595/13896417
        EventHandler<KeyEvent> escHandler = event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                stage.close();
            }
        };
        stage.getScene().addEventHandler(KeyEvent.KEY_PRESSED, escHandler);
    }
}
