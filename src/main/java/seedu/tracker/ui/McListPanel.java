package seedu.tracker.ui;

import java.util.ArrayList;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.tracker.commons.core.LogsCenter;
import seedu.tracker.model.module.Mc;
import seedu.tracker.model.module.Module;

/**
 * Panel containing the list of modules.
 */
public class McListPanel extends UiPart<Region> {
    private static final String FXML = "McListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ModuleListPanel.class);

    @FXML
    private ListView<Mc> mcListView;

    /**
     * Creates a {@code ModuleListPanel} with the given {@code ObservableList}.
     */
    public McListPanel(ObservableList<Mc> obsList) {
        super(FXML);
//        ArrayList<Mc> mcList = createTempMcList();
//        ObservableList<Mc> obsList = FXCollections.observableArrayList(mcList);
        mcListView.setItems(obsList);
        mcListView.setCellFactory(listView -> new McListPanel.McListViewCell());
    }

    private ArrayList<Mc> createTempMcList() {
        ArrayList<Mc> list = new ArrayList<Mc>();
        list.add(new Mc(76));
        list.add(new Mc(10));
        list.add(new Mc(12));
        list.add(new Mc(99));
        list.add(new Mc(8));
        list.add(new Mc(10));
        list.add(new Mc(100));
        return list;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Module} using a {@code ModuleCard}.
     */
    class McListViewCell extends ListCell<Mc> {
        @Override
        protected void updateItem(Mc mc, boolean empty) {
            super.updateItem(mc, empty);

            if (empty || mc == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new McCard(mc, getIndex()).getRoot());
            }
        }
    }
}

