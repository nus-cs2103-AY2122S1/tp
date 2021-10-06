package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.folder.Folder;

/**
 * Panel containing the list of folders.
 */

public class FolderListPanel extends UiPart<Region> {
    private static final String FXML = "FolderListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(FolderListPanel.class);

    @FXML
    private ListView<Folder> folderListView;

    /**
     * Creates a {@code FolderListPanel}
     * with the given {@code ObservableList}.
     */
    public FolderListPanel(ObservableList<Folder> folderList) {
        super(FXML);
        folderListView.setItems(folderList);
        folderListView.setCellFactory(listView -> new FolderListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Folder} using a {@code FolderCard}.
     */
    class FolderListViewCell extends ListCell<Folder> {
        @Override
        protected void updateItem(Folder folder, boolean empty) {
            super.updateItem(folder, empty);

            if (empty || folder == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new FolderCard(folder, getIndex() + 1).getRoot());
            }
        }
    }

}
