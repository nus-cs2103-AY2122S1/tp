package seedu.times.ui.studenttab;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.times.commons.core.LogsCenter;
import seedu.times.model.person.Student;
import seedu.times.ui.StudentListViewCell;
import seedu.times.ui.UiPart;

/**
 * Panel containing the list of persons.
 */
public class StudentListPanel extends UiPart<Region> {
    private static final String FXML = "classTab/StudentClassListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(StudentListPanel.class);

    @FXML
    private ListView<Student> studentListView;

    /**
     * Creates a {@code StudentListPanel} with the given {@code ObservableList}.
     */
    public StudentListPanel(ObservableList<Student> studentList) {
        super(FXML);
        studentListView.setItems(studentList);
        studentListView.setCellFactory(listView -> new StudentListViewCell());
    }
}
