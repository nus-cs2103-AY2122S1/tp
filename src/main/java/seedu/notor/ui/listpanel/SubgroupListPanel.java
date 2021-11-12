package seedu.notor.ui.listpanel;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import seedu.notor.commons.core.LogsCenter;
import seedu.notor.model.group.SubGroup;
import seedu.notor.ui.SubgroupCard;

/**
 * Panel containing the list of groups.
 */
public class SubgroupListPanel extends ListPanel<SubGroup> {
    private static final String FXML = "SubgroupListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(SubgroupListPanel.class);

    /**
     * Creates a {@code SubgroupListPanel} with the given {@code ObservableList}.
     */
    public SubgroupListPanel(ObservableList<SubGroup> groupList) {
        super(FXML, groupList);
    }

    @Override
    void initializeListView(ObservableList<SubGroup> groupList) {
        super.listView.setItems(groupList);
        super.listView.setCellFactory(listView -> new SubgroupListViewCell());
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
