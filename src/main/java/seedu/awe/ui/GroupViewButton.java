package seedu.awe.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import seedu.awe.ui.listener.GroupButtonListener;

/**
 * A button to toggle the view to GroupPanel.
 */
public class GroupViewButton extends UiPart<Region> {

    private static final String FXML = "GroupViewButton.fxml";

    @FXML
    private Button groupViewButton;

    /**
     * Constructor for GroupViewButton.
     *
     * @param mainWindow mainWindow to set onclick action
     */
    public GroupViewButton(MainWindow mainWindow) {
        super(FXML);
        groupViewButton.setOnAction(new GroupButtonListener(mainWindow));

    }


}
