package seedu.programmer.ui;

import java.util.HashSet;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.programmer.commons.core.LogsCenter;
import seedu.programmer.logic.Logic;
import seedu.programmer.model.ReadOnlyProgrammerError;
import seedu.programmer.model.student.ClassId;
import seedu.programmer.model.student.Student;

/**
 * Dashboard window of student data.
 */
public class DashboardWindow extends PopupWindow {

    private static final String FXML = "DashboardWindow.fxml";
    private final Logger logger = LogsCenter.getLogger(getClass());
    private Logic logic;

    @FXML
    private StackPane overallStatsPlaceholder;


    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public DashboardWindow(Stage root, Logic logic) {
        super(FXML, root);
        this.logic = logic;
        fillOverallStats();
    }

    /**
     * Creates a new HelpWindow.
     */
    public DashboardWindow(Logic logic) {
        this(new Stage(), logic);
    }

    private void fillOverallStats() {
        ReadOnlyProgrammerError readOnlyPE = logic.getProgrammerError();
        ObservableList<Student> stuList = readOnlyPE.getStudentList();
        HashSet<ClassId> classes = new HashSet<>();
        for (Student s: stuList) {
            classes.add(s.getClassId());
        }
        int numStudents = stuList.size();
        int numClasses = classes.size();
        int numLabs = stuList.size() > 0 ? stuList.get(0).getLabList().size() : 0;

        String overallStats = "";
        overallStats += "No. of students: " + numStudents + "\n";
        overallStats += "No. of classes: " + numClasses + "\n";
        overallStats += "No. of labs: " + numLabs + "\n";
        Label label = new Label(overallStats);
        label.getStylesheets().add("view/Dashboard.css");
        label.getStyleClass().add("overall-stats");
        overallStatsPlaceholder.getChildren().add(label);
    }
}
