package seedu.notor.ui.listpanel;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.notor.commons.core.LogsCenter;
import seedu.notor.model.group.SuperGroup;
import seedu.notor.ui.GroupCard;
import seedu.notor.ui.UiPart;

/**
 * Panel containing the list of groups.
 */
public class GroupListPanel extends UiPart<Region> {
    private static final String FXML = "GroupListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(GroupListPanel.class);

    @FXML
    private ListView<SuperGroup> groupListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public GroupListPanel(ObservableList<SuperGroup> groupList) {
        super(FXML);
        groupListView.setItems(groupList);
        groupListView.setCellFactory(listView -> new GroupListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
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
