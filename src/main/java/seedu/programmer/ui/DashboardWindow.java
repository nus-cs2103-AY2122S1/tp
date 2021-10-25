package seedu.programmer.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seedu.programmer.logic.Logic;
import seedu.programmer.model.ReadOnlyProgrammerError;

/**
 * Controller for a help page
 */
public class DashboardWindow extends PopupWindow {

    private static final String FXML = "DashboardWindow.fxml";

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
        int numStudents = readOnlyPE.getStudentList().size();
        int numClasses = 30;
        int numLabs = 10;
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
