package seedu.address.ui;


import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Region;

/**
 * A ui for the toggle buttons to toggle between clients and tasks.
 */
public class Toggle extends UiPart<Region> {

    private static final String FXML = "Toggle.fxml";

    private final Runnable personsToggler;
    private final Runnable tasksToggler;

    @FXML
    private ToggleButton clients;
    @FXML
    private ToggleButton tasks;

    /**
     * Creates a {@code Toggle} with the given {@code Runnable}s.
     *
     * @param personsToggler toggle that makes the UI lists persons
     * @param tasksToggler toggle that makes the UI lists tasks
     */
    public Toggle(Runnable personsToggler, Runnable tasksToggler) {
        super(FXML);
        this.personsToggler = personsToggler;
        this.tasksToggler = tasksToggler;
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
}
