package seedu.notor.ui.listpanel;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.notor.ui.UiPart;

/**
 * Panel containing the list of T.
 */
public abstract class ListPanel<T> extends UiPart<Region> {

    @FXML
    protected ListView<T> listView;

    /**
     * Creates a {@code ListPanel}.
     */
    public ListPanel(String fxml, ObservableList<T> list) {
        super(fxml);
        initializeListView(list);
    }

    abstract void initializeListView(ObservableList<T> list);
}
