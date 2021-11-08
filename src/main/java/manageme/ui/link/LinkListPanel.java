package manageme.ui.link;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import manageme.commons.core.LogsCenter;
import manageme.model.link.Link;
import manageme.ui.UiPart;

/**
 * Panel containing the list of Links.
 */
public class LinkListPanel extends UiPart<Region> {
    private static final String FXML = "LinkListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(LinkListPanel.class);

    @FXML
    private ListView<Link> linkListView;

    /**
     * Creates a {@code LinkListPanel} with the given {@code ObservableList}.
     */
    public LinkListPanel(ObservableList<Link> linkList) {
        super(FXML);
        linkListView.setItems(linkList);
        linkListView.setCellFactory(listView -> new LinkListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Link} using a {@code LinkCard}.
     */
    class LinkListViewCell extends ListCell<Link> {
        @Override
        protected void updateItem(Link link, boolean empty) {
            super.updateItem(link, empty);

            if (empty || link == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new LinkCard(link, getIndex() + 1).getRoot());
            }
        }
    }

}
