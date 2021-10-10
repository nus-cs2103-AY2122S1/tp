package seedu.unify.ui;

import java.util.logging.Logger;

import javafx.scene.layout.Region;
import seedu.unify.commons.core.LogsCenter;

public class WeeklyPanel extends UiPart<Region> {
    private static final String FXML = "WeeklyPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(WeeklyPanel.class);

    public WeeklyPanel() {
        super(FXML);
    }
}
