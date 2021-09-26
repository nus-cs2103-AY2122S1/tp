package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.anime.Anime;

/**
 * Panel containing the list of anime.
 */
public class AnimeListPanel extends UiPart<Region> {
    private static final String FXML = "AnimeListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(AnimeListPanel.class);

    @FXML
    private ListView<Anime> animeListView;

    /**
     * Creates a {@code AnimeListPanel} with the given {@code ObservableList}.
     */
    public AnimeListPanel(ObservableList<Anime> animeList) {
        super(FXML);
        animeListView.setItems(animeList);
        animeListView.setCellFactory(listView -> new AnimeListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Anime} using a {@code AnimeCard}.
     */
    class AnimeListViewCell extends ListCell<Anime> {
        @Override
        protected void updateItem(Anime anime, boolean empty) {
            super.updateItem(anime, empty);

            if (empty || anime == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new AnimeCard(anime, getIndex() + 1).getRoot());
            }
        }
    }

}
