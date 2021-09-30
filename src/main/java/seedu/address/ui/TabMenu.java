package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * An UI component that displays the Tab Pane.
 */
public class TabMenu extends UiPart<Region> {

    private static final String FXML = "TabMenu.fxml";

    @FXML
    private TabPane tabMenu;

    @FXML
    private Tab tab1;

    @FXML
    private Tab tab2;

    @FXML
    private GridPane containerBox;

    @FXML
    private VBox leftBox;

    @FXML
    private VBox rightBox;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public TabMenu() {
        super(FXML);
    }

    public GridPane getGridPane() {
        return containerBox;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TabPane)) {
            return false;
        }

        return false;
    }
}
