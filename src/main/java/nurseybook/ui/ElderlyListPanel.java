package nurseybook.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import nurseybook.commons.core.LogsCenter;
import nurseybook.model.person.Elderly;

/**
 * Panel containing the list of elderlies.
 */
public class ElderlyListPanel extends UiPart<Region> {
    private static final String FXML = "ElderlyListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ElderlyListPanel.class);

    @FXML
    private ListView<Elderly> elderlyListView;

    /**
     * Creates a {@code ElderlyListPanel} with the given {@code ObservableList}.
     */
    public ElderlyListPanel(ObservableList<Elderly> elderlyList) {
        super(FXML);
        elderlyListView.setItems(elderlyList);
        elderlyListView.setCellFactory(listView -> new ElderlyListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Elderly} using a {@code ElderlyCard}.
     */
    class ElderlyListViewCell extends ListCell<Elderly> {
        @Override
        protected void updateItem(Elderly elderly, boolean empty) {
            super.updateItem(elderly, empty);

            if (empty || elderly == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ElderlyCard(elderly, getIndex() + 1).getRoot());
            }
        }
    }

}
