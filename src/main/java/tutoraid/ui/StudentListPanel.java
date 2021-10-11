package tutoraid.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import tutoraid.commons.core.LogsCenter;
import tutoraid.model.student.Student;

/**
 * Panel containing the list of persons.
 */
public class StudentListPanel extends UiPart<Region> {
    private static final String FXML = "StudentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(StudentListPanel.class);
    private final boolean viewAll;

    @FXML
    private ListView<Student> personListView;

    /**
     * Creates a {@code StudentListPanel} with the given {@code ObservableList}.
     */
    public StudentListPanel(ObservableList<Student> studentList, boolean viewAll) {
        super(FXML);
        personListView.setItems(studentList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
        this.viewAll = viewAll;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Student} using a {@code StudentCard}.
     */
    class PersonListViewCell extends ListCell<Student> {
        @Override
        protected void updateItem(Student student, boolean empty) {
            super.updateItem(student, empty);

            if (empty || student == null) {
                setGraphic(null);
                setText(null);
            } else if (viewAll) {
                setGraphic(new StudentCard(student, getIndex() + 1).getRoot());
            } else {
                setGraphic(new MinimalStudentCard(student, getIndex() + 1).getRoot());
            }
        }
    }

}
