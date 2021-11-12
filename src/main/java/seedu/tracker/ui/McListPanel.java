package seedu.tracker.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.tracker.commons.core.LogsCenter;
import seedu.tracker.model.module.McProgress;

/**
 * Panel containing the user's mc progress.
 */
public class McListPanel extends UiPart<Region> {
    private static final String FXML = "McListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(McListPanel.class);

    @FXML
    private ListView<McProgress> mcListView;

    /**
     * Creates a {@code McListPanel} with the given {@code ObservableList}.
     */
    public McListPanel(ObservableList<McProgress> progressList) {
        super(FXML);
        mcListView.setItems(progressList);
        mcListView.setCellFactory(listView -> new McListPanel.McListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code McProgress} using a {@code McCard}.
     */
    class McListViewCell extends ListCell<McProgress> {
        @Override
        protected void updateItem(McProgress progress, boolean empty) {
            super.updateItem(progress, empty);

            if (empty || progress == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new McCard(progress, getIndex()).getRoot());
            }
        }
    }
}

