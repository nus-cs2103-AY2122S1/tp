package seedu.unify.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.unify.model.task.Task;

public class DailyPanel extends UiPart<Region> {

    private static final String FXML = "DailyPanel.fxml";

    @FXML
    private Label day;

    /**
     * Creates a {@code DailyPanel} with the given {@code Task} and dayString to display.
     */
    public DailyPanel(Task task, String dayString) {
        super(FXML);
        //this.task = task;
        day.setText(dayString);

    }
}
