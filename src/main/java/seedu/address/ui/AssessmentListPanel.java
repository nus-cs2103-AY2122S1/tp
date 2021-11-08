package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.assessment.Assessment;

public class AssessmentListPanel extends UiPart<Region> {
    private static final String FXML = "AssessmentListPanel.fxml";

    @FXML
    private ListView<Assessment> assessmentListView;

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Assessment} using a {@code AssessmentCard}.
     */
    public AssessmentListPanel(ObservableList<Assessment> assessments) {
        super(FXML);
        assessmentListView.setItems(assessments);
        assessmentListView.setCellFactory(listView -> new AssessmentListViewCell());
    }

    private class AssessmentListViewCell extends ListCell<Assessment> {
        @Override
        protected void updateItem(Assessment assessment, boolean empty) {
            super.updateItem(assessment, empty);

            if (empty || assessment == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new AssessmentCard(assessment, getIndex() + 1).getRoot());
            }
        }
    }
}
