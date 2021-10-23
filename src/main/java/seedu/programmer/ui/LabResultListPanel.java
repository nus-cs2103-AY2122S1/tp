package seedu.programmer.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.programmer.commons.core.LogsCenter;
import seedu.programmer.model.student.Lab;

/**
 * Panel containing the list of lab results
 */
public class LabResultListPanel extends UiPart<Region> {
    private static final String FXML = "LabResultListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(LabResultListPanel.class);

    @FXML
    private ListView<Lab> labResultListView;

    /**
     * Creates a {@code LabelResultListPanel} with the given {@code ObservableList}.
     */
    public LabResultListPanel(ObservableList<Lab> labResultList) {
        super(FXML);
        labResultListView.setItems(labResultList);
        labResultListView.setCellFactory(listView -> new LabListListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code labResult} using a {@code LabResultCard}.
     */
    static class LabListListViewCell extends ListCell<Lab> {
        @Override
        protected void updateItem(Lab labResult, boolean empty) {
            super.updateItem(labResult, empty);

            if (empty || labResult == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new LabResultCard(labResult, getIndex() + 1).getRoot());
            }
        }
    }
}
