package seedu.times.ui.classtab;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import seedu.times.model.person.Student;
import seedu.times.model.tuitionclass.TuitionClass;

/**
 * Custom {@code ListCell} that displays the graphics of a {@code TuitionClass} using a {@code TuitionClassCard}.
 */
public class TuitionClassListViewCell extends ListCell<TuitionClass> {

    private final ObservableList<Student> studentList;
    private final ListView<Student> studentClassListView;


    /**
     * Represents the cell displaying the tuition class.
     *
     * @param studentList The student list associated with the tuition class.
     * @param studentClassListView The UI associated with the tuition class.
     */
    public TuitionClassListViewCell(ObservableList<Student> studentList, ListView<Student> studentClassListView) {
        this.studentList = studentList;
        this.studentClassListView = studentClassListView;
    }

    @Override
    protected void updateItem(TuitionClass tuitionClass, boolean empty) {
        super.updateItem(tuitionClass, empty);

        if (empty || tuitionClass == null) {
            setGraphic(null);
            setText(null);
        } else {
            setGraphic(new TuitionClassCard(tuitionClass, getIndex() + 1, studentList,
                    studentClassListView).getRoot());
        }
    }
}
