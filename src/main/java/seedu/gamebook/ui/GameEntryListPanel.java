package seedu.gamebook.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.gamebook.commons.core.LogsCenter;
import seedu.gamebook.model.gameentry.GameEntry;

/**
 * Panel containing the list of game entries.
 */
public class GameEntryListPanel extends UiPart<Region> {
    private static final String FXML = "GameEntryListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(GameEntryListPanel.class);

    @FXML
    private ListView<GameEntry> gameEntryListView;

    /**
     * Creates a {@code GameEntryListPanel} with the given {@code ObservableList}.
     */
    public GameEntryListPanel(ObservableList<GameEntry> gameEntryList) {
        super(FXML);
        gameEntryListView.setItems(gameEntryList);
        gameEntryListView.setCellFactory(listView -> new GameEntryListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code GameEntry} using a {@code GameEntryCard}.
     */
    class GameEntryListViewCell extends ListCell<GameEntry> {
        @Override
        protected void updateItem(GameEntry gameEntry, boolean empty) {
            super.updateItem(gameEntry, empty);

            if (empty || gameEntry == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new GameEntryCard(gameEntry, getIndex() + 1).getRoot());
            }
        }
    }

}
