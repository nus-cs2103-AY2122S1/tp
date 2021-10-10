package dash.ui;

import java.util.logging.Logger;

import dash.commons.core.LogsCenter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class HelpPanel extends UiPart<Region>{
    private static final String FXML = "helpPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TaskListPanel.class);

    @FXML
    private VBox container;

    @FXML
    private Label text;

    private final String helpContent = "test";

    public HelpPanel() {
        super(FXML);
        text.setText(helpContent);
    }

}
