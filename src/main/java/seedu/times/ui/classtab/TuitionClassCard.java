package seedu.times.ui.classtab;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.times.model.person.Student;
import seedu.times.model.tuitionclass.TuitionClass;
import seedu.times.ui.UiPart;

/**
 * Represents a Tuition Card to be shown in the GUI.
 */
public class TuitionClassCard extends UiPart<Region> {

    private static final String FXML = "classTab/TuitionClassListCard.fxml";
    private static final Character DOLLAR_SIGN = '$';
    private static final String PER_HOUR = "/hr";
    private static final String CLASS_TIMING_FIELD = "Timing: ";
    private static final String LOCATION_FIELD = "Location: ";
    private static final String RATE_FIELD = "Rate: ";
    private static final String CLASS_SIZE_FIELD = "Size: ";

    public final TuitionClass tuitionClass;

    // TuitionClass
    @FXML
    private HBox cardPane;
    @FXML
    private Label className;
    @FXML
    private Label id;
    @FXML
    private Label classTiming;
    @FXML
    private Label classLocation;
    @FXML
    private Label rate;
    @FXML
    private Label classSize;

    private final ListView<Student> tuitionClassListView;

    private final ObservableList<Student> studentList;

    /**
     * Represents a Tuition Card to be shown in the GUI.
     *
     * @param tuitionClass The class to display.
     * @param displayedIndex The index to display.
     */
    public TuitionClassCard(TuitionClass tuitionClass, int displayedIndex,
                            ObservableList<Student> studentList, ListView<Student> tuitionClassListView) {
        super(FXML);
        this.tuitionClass = tuitionClass;
        this.studentList = studentList;
        this.tuitionClassListView = tuitionClassListView;

        int index = tuitionClassListView.getSelectionModel().getSelectedIndex();
        tuitionClassListView.getSelectionModel().clearSelection(index);

        // TuitionClass
        id.setText(displayedIndex + ". ");
        className.setText(tuitionClass.getClassName().className);
        classTiming.setText(CLASS_TIMING_FIELD + tuitionClass.getClassTiming().value);
        classLocation.setText(LOCATION_FIELD + tuitionClass.getLocation().value);
        rate.setText(RATE_FIELD + DOLLAR_SIGN + tuitionClass.getRate().value + PER_HOUR);
        classSize.setText(CLASS_SIZE_FIELD + String.valueOf(tuitionClass.getStudentList().size()));
    }

    @FXML
    private void onMouseClick() {
        selectTuitionClass();
    }

    /**
     * Visually selects the tuition class and shows the Student List associated to it.
     */
    public void selectTuitionClass() {
        ObservableList<Student> newStudentList =
                studentList.filtered(student -> tuitionClass.containsStudent(student.getName()));

        tuitionClassListView.setItems(newStudentList);
        tuitionClassListView.setCellFactory(listView -> new StudentListClassTabViewCell());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TuitionClassCard)) {
            return false;
        }

        // state check
        TuitionClassCard card = (TuitionClassCard) other;
        return id.getText().equals(card.id.getText())
                && tuitionClass.equals(card.tuitionClass);
    }
}
