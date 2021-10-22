package nustracker.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import nustracker.commons.core.LogsCenter;
import nustracker.model.student.Student;

/**
 * Panel containing the list of students.
 */
public class StudentListPanel extends UiPart<Region> {
    private static final String FXML = "StudentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(StudentListPanel.class);

    @FXML
    private ListView<Student> studentListView;

    private String glowColorHexCode;

    private ObservableList<Student> studentList;

    /**
     * Creates a {@code StudentListPanel} with the given {@code ObservableList}.
     */
    public StudentListPanel(ObservableList<Student> studentList, String glowColor) {
        super(FXML);

        this.glowColorHexCode = glowColor;
        this.studentList = studentList;

        fillPanelWithCells(studentList);
    }

    /**
     * Updates the glow color of the containing student cards according to the colour changed in the
     * color picker in the Settings Window
     *
     * @param newGlowColorHexCode The string hex code of color
     */
    public void updateGlow(String newGlowColorHexCode) {
        glowColorHexCode = newGlowColorHexCode;

        fillPanelWithCells(studentList);
    }

    /**
     * Fills the panel with the {@code ListCells}, each containing a {@link StudentCard}.
     *
     * @param studentList The student list to be used.
     */
    public void fillPanelWithCells(ObservableList<Student> studentList) {
        studentListView.setItems(studentList);
        studentListView.setCellFactory(listView -> new StudentListViewCell());
        focusOnItem(0); //Select first item upon startup
    }

    /**
     * Focuses on the first student, upon execution
     */
    public void focusOnItem(int index) {
        studentListView.getSelectionModel().select(index);
        studentListView.getFocusModel().focus(index);
        studentListView.scrollTo(index);
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
            } else {
                StudentCard currentStudent = new StudentCard(student);
                currentStudent.setGlow(glowColorHexCode);
                setGraphic(currentStudent.getRoot());
            }
        }
    }

}
