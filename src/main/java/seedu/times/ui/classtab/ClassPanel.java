package seedu.times.ui.classtab;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.times.commons.core.LogsCenter;
import seedu.times.model.person.Student;
import seedu.times.model.tuitionclass.TuitionClass;
import seedu.times.ui.UiPart;

/**
 * A UI for the Class Panel Tab.
 */
public class ClassPanel extends UiPart<Region> {
    private static final String FXML = "classTab/ClassPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ClassPanel.class);

    @FXML
    private StackPane tuitionClassListPlaceholder;

    @FXML
    private StackPane studentListPlaceholder;

    // Labels
    @FXML
    private Label classLabel;
    @FXML
    private Label studentLabel;

    private final StudentClassPanel studentClassPanel;

    private final TuitionClassPanel tuitionClassPanel;

    /**
     * Creates a {@code StudentListPanel} with the given {@code ObservableList}.
     */
    public ClassPanel(ObservableList<Student> studentList, ObservableList<TuitionClass> tuitionClassList) {
        super(FXML);
        //right side
        this.studentClassPanel = new StudentClassPanel(studentList);

        //left side
        this.tuitionClassPanel = new TuitionClassPanel(studentList, tuitionClassList);

        tuitionClassPanel.setStudentClassList(studentClassPanel.getStudentListView());

        studentListPlaceholder.getChildren().add(studentClassPanel.getRoot());
        tuitionClassListPlaceholder.getChildren().add(tuitionClassPanel.getRoot());

        // Set UI stuff
        classLabel.setText("Your Classes");
        studentLabel.setText("Students");

        logger.info("ClassPanel tab opened");
    }

    public void setItems(ObservableList<Student> studentObservableList) {
        studentClassPanel.setItems(studentObservableList);
    }

}
