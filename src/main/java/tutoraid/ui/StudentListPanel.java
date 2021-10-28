package tutoraid.ui;

import static tutoraid.ui.DetailLevel.HIGH;
import static tutoraid.ui.DetailLevel.MED;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import tutoraid.commons.core.LogsCenter;
import tutoraid.model.student.Student;

/**
 * Panel containing the list of students.
 */
public class StudentListPanel extends UiPart<Region> {
    private static final String FXML = "StudentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(StudentListPanel.class);
    private final DetailLevel detailLevel;

    @FXML
    private ListView<Student> studentListView;

    /**
     * Creates a {@code StudentListPanel} with the given {@code ObservableList}.
     */
    public StudentListPanel(ObservableList<Student> studentList, DetailLevel detailLevel) {
        super(FXML);
        studentListView.setItems(studentList);
        studentListView.setCellFactory(listView -> new StudentListViewCell());
        this.detailLevel = detailLevel;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Student} using a {@code StudentCard}.
     */
    class StudentListViewCell extends ListCell<Student> {
        @Override
        protected void updateItem(Student student, boolean empty) {
            super.updateItem(student, empty);

            if (empty || student == null) {
                setGraphic(null);
                setText(null);
            } else if (detailLevel == HIGH) {
                setGraphic(new FullStudentCard(student, getIndex() + 1).getRoot());
            } else if (detailLevel == MED) {
                setGraphic(new StudentCard(student, getIndex() + 1).getRoot());
            } else {
                setGraphic(new MinimalStudentCard(student, getIndex() + 1).getRoot());
            }
        }
    }

}
