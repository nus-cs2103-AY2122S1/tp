package seedu.notor.ui.listpanel;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.notor.commons.core.LogsCenter;
import seedu.notor.model.group.SubGroup;
import seedu.notor.ui.SubgroupCard;
import seedu.notor.ui.UiPart;

/**
 * Panel containing the list of groups.
 */
public class SubgroupListPanel extends UiPart<Region> {
    private static final String FXML = "SubgroupListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(SubgroupListPanel.class);

    @FXML
    private ListView<SubGroup> subgroupListView;

    /**
     * Creates a {@code SubgroupListPanel} with the given {@code ObservableList}.
     */
    public SubgroupListPanel(ObservableList<SubGroup> groupList) {
        super(FXML);
        subgroupListView.setItems(groupList);
        subgroupListView.setCellFactory(listView -> new SubgroupListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class SubgroupListViewCell extends ListCell<SubGroup> {
        @Override
        protected void updateItem(SubGroup subGroup, boolean empty) {
            super.updateItem(subGroup, empty);

            if (empty || subGroup == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new SubgroupCard(subGroup, getIndex() + 1).getRoot());
            }
        }
    }
}
