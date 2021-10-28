package seedu.notor.ui.listpanel;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import seedu.notor.commons.core.LogsCenter;
import seedu.notor.model.group.SuperGroup;
import seedu.notor.ui.GroupCard;

/**
 * Panel containing the list of groups.
 */
public class GroupListPanel extends ListPanel<SuperGroup> {
    private static final String FXML = "GroupListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(GroupListPanel.class);

    /**
     * Creates a {@code GroupListPanel} with the given {@code ObservableList}.
     */
    public GroupListPanel(ObservableList<SuperGroup> groupList) {
        super(FXML, groupList);
    }

    @Override
    void initializeListView(ObservableList<SuperGroup> groupList) {
        super.listView.setItems(groupList);
        super.listView.setCellFactory(listView -> new GroupListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code GroupCard}.
     */
    class GroupListViewCell extends ListCell<SuperGroup> {
        @Override
        protected void updateItem(SuperGroup superGroup, boolean empty) {
            super.updateItem(superGroup, empty);

            if (empty || superGroup == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new GroupCard(superGroup, getIndex() + 1).getRoot());
            }
        }
    }
}
