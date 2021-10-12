package seedu.programmer.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.programmer.commons.core.LogsCenter;
import seedu.programmer.model.student.LabResult;

/**
 * Panel containing the list of students.
 */
public class LabResultListPanel extends UiPart<Region> {
    private static final String FXML = "LabResultListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(LabResultListPanel.class);

    @FXML
    private ListView<LabResult> labResultListView;

    /**
     * Creates a {@code StudentListPanel} with the given {@code ObservableList}.
     */
    public LabResultListPanel(ObservableList<LabResult> labResultList) {
        super(FXML);
        labResultListView.setItems(labResultList);
        labResultListView.setCellFactory(listView -> new LabListListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code student} using a {@code studentCard}.
     */
    class LabListListViewCell extends ListCell<LabResult> {
        @Override
        protected void updateItem(LabResult labResult, boolean empty) {
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
