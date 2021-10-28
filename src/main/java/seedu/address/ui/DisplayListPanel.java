package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.display.Displayable;

/**
 * Panel containing the list of items.
 */
public class DisplayListPanel extends UiPart<Region> {
    private static final String FXML = "DisplayListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(DisplayListPanel.class);

    @FXML
    private ListView<Displayable> displayListView;

    /**
     * Creates a {@code DisplayListPanel} with the given {@code ObservableList}.
     */
    public DisplayListPanel(ObservableList<Displayable> displayList) {
        super(FXML);
        displayListView.setItems(displayList);
        displayListView.setCellFactory(listView -> new DisplayListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Displayable}.
     */
    class DisplayListViewCell extends ListCell<Displayable> {

        @Override
        protected void updateItem(Displayable displayable, boolean empty) {
            super.updateItem(displayable, empty);

            if (empty || displayable == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(displayable.asDisplayCard(getIndex() + 1).getRoot());
            }
        }

    }

}
