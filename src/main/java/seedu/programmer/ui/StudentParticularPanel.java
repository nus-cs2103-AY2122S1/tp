package seedu.programmer.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.programmer.commons.core.LogsCenter;
import seedu.programmer.model.student.Student;

/**
 * Panel containing the student particular on the side panel.
 */
public class StudentParticularPanel extends UiPart<Region> {
    private static final String FXML = "StudentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(StudentParticularPanel.class);

    @FXML
    private ListView<Student> studentListView;

    /**
     * Creates a {@code StudentParticularPanel} with the given {@code ObservableList}.
     */
    public StudentParticularPanel(ObservableList<Student> studentList) {
        super(FXML);
        studentListView.setItems(studentList);
        studentListView.setCellFactory(listView -> new StudentParticularViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code student} using a {@code studentCard}
     * without an index.
     */
    static class StudentParticularViewCell extends ListCell<Student> {
        @Override
        protected void updateItem(Student student, boolean empty) {
            super.updateItem(student, empty);

            if (empty || student == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new StudentCard(student).getRoot());
            }
        }
    }
}