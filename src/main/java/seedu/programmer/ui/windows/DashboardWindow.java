package seedu.programmer.ui.windows;

import java.util.HashSet;
import java.util.TreeMap;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.programmer.commons.core.LogsCenter;
import seedu.programmer.logic.Logic;
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
    private final Logic logic;

    @FXML
    private Label overallStatsLabel;

    @FXML
    private Label labsUnmarkedLabel;

    /**
     * Creates a new DashboardWindow.
     *
     * @param root Stage to use as the root of the DashboardWindow.
     * @param logic Logic object for accessing student data.
     */
    public DashboardWindow(Stage root, Logic logic) {
        super(FXML, root);
        this.logic = logic;
        update();
    }

    /**
     * Creates a new Dashboard Window.
     *
     * @param logic Logic object for accessing student data.
     */
    public DashboardWindow(Logic logic) {
        this(new Stage(), logic);
    }

    /**
     * Refreshes the dashboard window.
     */
    public void update() {
        logger.info("Updating dashboard window...");
        ObservableList<Student> stuList = logic.getAllStudents();
        HashSet<ClassId> classes = getClasses(stuList);
        fillOverallStats(classes, stuList);
        fillLabsMarked(classes, stuList);
    }

    /**
     * Fills number of students, classes and labs.
     *
     * @param classes Set of classes.
     * @param stuList List of students.
     */
    private void fillOverallStats(HashSet<ClassId> classes, ObservableList<Student> stuList) {
        String dataToDisplay = formatDataToDisplay(stuList, classes);
        overallStatsLabel.setText(dataToDisplay);
    }

    /**
     * Fills the number of unmarked labs per class.
     *
     * @param classes Classes to be displayed.
     * @param stuList List of students.
     */
    private void fillLabsMarked(HashSet<ClassId> classes, ObservableList<Student> stuList) {
        TreeMap<ClassId, Integer> labsUnmarkedMap = populateLabsUnmarkedMap(classes, stuList);
        String labsMarked = formatLabsToDisplay(labsUnmarkedMap);
        labsUnmarkedLabel.setText(labsMarked);
    }

    private HashSet<ClassId> getClasses(ObservableList<Student> stuList) {
        HashSet<ClassId> classes = new HashSet<>();
        stuList.forEach(s -> classes.add(s.getClassId()));
        return classes;
    }

    private TreeMap<ClassId, Integer> populateLabsUnmarkedMap(HashSet<ClassId> classes,
                                                              ObservableList<Student> stuList) {
        TreeMap<ClassId, Integer> labsUnmarkedMap = new TreeMap<>(new SortClassId());
        classes.forEach(cid -> labsUnmarkedMap.put(cid, 0));

        for (Student s : stuList) {
            ObservableList<Lab> stuLabs = s.getLabList();
            for (Lab l : stuLabs) {
                if (!l.isMarked()) {
                    ClassId cid = s.getClassId();
                    labsUnmarkedMap.put(cid, labsUnmarkedMap.get(cid) + 1);
                }
            }
        }
        return labsUnmarkedMap;
    }

    private String formatDataToDisplay(ObservableList<Student> stuList, HashSet<ClassId> classList) {
        int numStudents = stuList.size();
        int numClasses = classList.size();
        int numLabs = stuList.size() > 0 ? stuList.get(0).getNumLabs() : 0;

        return "No. of students: " + numStudents + "\n"
                + "No. of classes: " + numClasses + "\n"
                + "No. of labs: " + numLabs + "\n\n";
    }

    private String formatLabsToDisplay(TreeMap<ClassId, Integer> labMap) {
        if (labMap.size() == 0) {
            return "\nYou don't have any classes yet!";
        }

        // Start building message
        StringBuilder dataToDisplay = new StringBuilder("\nNo. of labs left to mark:\n");
        for (ClassId cid : labMap.keySet()) {
            String key = cid.toString();
            String value = labMap.get(cid).toString();
            dataToDisplay.append(key).append(": ").append(value).append("\n\n");
        }
        return dataToDisplay.append("Keep it going!\n\n").toString();
    }
}
