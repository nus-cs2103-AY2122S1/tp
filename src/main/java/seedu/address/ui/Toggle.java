package seedu.address.ui;


import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Region;

/**
 * A ui for the toggle buttons to toggle between clients, tasks and orders.
 */
public class Toggle extends UiPart<Region> {

    private static final String FXML = "Toggle.fxml";

    @FXML
    private ToggleButton clients;

    @FXML
    private ToggleButton tasks;

    @FXML
    private ToggleButton orders;

    private final Runnable personsToggler;
    private final Runnable tasksToggler;
    private final Runnable ordersToggler;

    /**
     * Creates a {@code Toggle} with the given {@code Runnable}s.
     *
     * @param personsToggler toggle that makes the UI lists persons
     * @param tasksToggler   toggle that makes the UI lists tasks
     * @param ordersToggler  toggle that makes the UI lists orders
     */
    public Toggle(Runnable personsToggler, Runnable tasksToggler, Runnable ordersToggler) {
        super(FXML);
        this.personsToggler = personsToggler;
        this.tasksToggler = tasksToggler;
        this.ordersToggler = ordersToggler;
    }

    public void selectClientTab() {
        clients.setSelected(true);
    }

    public void selectTaskTab() {
        tasks.setSelected(true);
    }

    public void selectOrderTab() {
        orders.setSelected(true);
    }

    /**
     * Handles the Clients button pressed event.
     */
    @FXML
    private void handleClientsPressed() {
        personsToggler.run();
    }

    /**
     * Handles the Tasks button pressed event.
     */
    @FXML
    private void handleTasksPressed() {
        tasksToggler.run();
    }

    /**
     * Handles the Orders button pressed event.
     */
    @FXML
    private void handleOrdersPressed() {
        ordersToggler.run();
    }
}
