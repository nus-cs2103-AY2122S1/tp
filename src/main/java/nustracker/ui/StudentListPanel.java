package nustracker.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import nustracker.model.student.Student;

/**
 * Panel containing the list of students.
 */
public class StudentListPanel extends UiPart<Region> {

    private static final String FXML = "StudentListPanel.fxml";
    private String glowColorHexCode;
    private final ObservableList<Student> studentList;

    @FXML
    private ListView<Student> studentListView;

    /**
     * Creates a {@code StudentListPanel} with the given {@code ObservableList}.
     */
    public StudentListPanel(ObservableList<Student> studentList, String glowColor) {
        super(FXML);

        this.glowColorHexCode = glowColor;
        this.studentList = studentList;

        fillPanelWithCells(studentList, 0);
    }

    /**
     * Updates the glow color of the containing student cards according to the colour changed in the
     * color picker in the Settings Window
     *
     * @param newGlowColorHexCode The string hex code of color
     */
    public void updateGlow(String newGlowColorHexCode) {
        glowColorHexCode = newGlowColorHexCode;

        refreshPanel();
    }

    /**
     * Refreshes the panel, forcing changes made to each {@link StudentCard} to update.
     */
    public void refreshPanel() {
        //Keeps track of the current focus, so the focus does not jump to the top.
        int focusOnCurrentCell = studentListView.getFocusModel().getFocusedIndex();

        fillPanelWithCells(studentList, focusOnCurrentCell);
    }

    /**
     * Fills the panel with the {@code ListCells}, each containing a {@link StudentCard}.
     *
     * @param studentList The student list to be used.
     * @param focusIndex The cell (that contains a Student) that should be focused after filling.
     */
    public void fillPanelWithCells(ObservableList<Student> studentList, int focusIndex) {
        studentListView.setItems(studentList);
        studentListView.setCellFactory(listView -> new StudentListViewCell());

        if (focusIndex == -1) {
            //This is possible if the control does not detect any focus
            focusOnItem(0);
        } else {
            focusOnItem(focusIndex);
        }
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
                StudentCard currentStudent = new StudentCard(student, glowColorHexCode);
                setGraphic(currentStudent.getRoot());
            }
        }
    }

}
