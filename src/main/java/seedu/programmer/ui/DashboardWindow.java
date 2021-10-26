package seedu.programmer.ui;

import java.util.HashSet;
import java.util.TreeMap;
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
import seedu.programmer.model.student.Lab;
import seedu.programmer.model.student.Student;
import seedu.programmer.model.student.comparator.SortClassId;

/**
 * Dashboard window of student data.
 */
public class DashboardWindow extends PopupWindow {

    private static final String FXML = "DashboardWindow.fxml";
    private final Logger logger = LogsCenter.getLogger(getClass());
    private Logic logic;
    private TreeMap<ClassId, Integer> labsMarkedMap;

    @FXML
    private StackPane overallStatsPlaceholder;

    @FXML
    private StackPane labsMarkedList;


    /**
     * Creates a new DashboardWindow.
     *
     * @param root Stage to use as the root of the DashboardWindow.
     */
    public DashboardWindow(Stage root, Logic logic) {
        super(FXML, root);
        this.logic = logic;
        fillOverallStats();
        fillLabsMarked();
    }

    /**
     * Creates a new Dashboard Window.
     */
    public DashboardWindow(Logic logic) {
        this(new Stage(), logic);
    }

    private void fillOverallStats() {
        ReadOnlyProgrammerError readOnlyPE = logic.getProgrammerError();
        ObservableList<Student> stuList = readOnlyPE.getStudentList();
        labsMarkedMap = new TreeMap<>(new SortClassId());
        HashSet<ClassId> classes = new HashSet<>();
        for (Student s: stuList) {
            classes.add(s.getClassId());
        }

        for (ClassId cid : classes) {
            if (!labsMarkedMap.containsKey(cid)) {
                labsMarkedMap.put(cid, 0);
            }
        }

        for (Student s : stuList) {
            ObservableList<Lab> stuLabs = s.getLabList();
            for (Lab l : stuLabs) {
                if (!l.isMarked()) {
                    ClassId cid = s.getClassId();
                    labsMarkedMap.put(cid, labsMarkedMap.get(cid) + 1);
                }
            }
        }

        int numStudents = stuList.size();
        int numClasses = classes.size();
        int numLabs = stuList.size() > 0 ? stuList.get(0).getLabList().size() : 0;

        String dataToDisplay = formatDataToDisplay(numStudents, numClasses, numLabs);
        Label label = new Label(dataToDisplay);
        label.getStylesheets().add("view/Dashboard.css");
        label.getStyleClass().add("overall-stats");
        overallStatsPlaceholder.getChildren().add(label);
    }

    void fillLabsMarked() {
        String labsMarked = formatLabsToDisplay(labsMarkedMap);
        Label labsLabel = new Label(labsMarked);
        labsLabel.getStylesheets().add("view/Dashboard.css");
        labsLabel.getStyleClass().add("labs-marked");
        labsMarkedList.getChildren().add(labsLabel);
    }

    private String formatDataToDisplay(int numStudents, int numClasses, int numLabs) {
        return "No. of students: " + numStudents + "\n"
                + "No. of classes: " + numClasses + "\n"
                + "No. of labs: " + numLabs + "\n\n";
    }

    private String formatLabsToDisplay(TreeMap<ClassId, Integer> labMap) {
        StringBuilder dataToDisplay = new StringBuilder("No. of labs left to mark:\n\n");
        for (ClassId cid: labMap.keySet()) {
            String key = cid.toString();
            String value = labMap.get(cid).toString();
            dataToDisplay.append(key).append(": ").append(value).append("\n\n");
        }

        return dataToDisplay.append("Keep it going!  😄").toString();
    }
}
