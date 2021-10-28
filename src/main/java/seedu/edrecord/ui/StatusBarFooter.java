package seedu.edrecord.ui;

import static seedu.edrecord.commons.core.Messages.MESSAGE_NO_MODULE_SELECTED;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.edrecord.logic.commands.CdCommand;
import seedu.edrecord.model.module.Module;

/**
 * A ui for the status bar that is displayed at the footer of the application.
 */
public class StatusBarFooter extends UiPart<Region> {

    private static final String FXML = "StatusBarFooter.fxml";

    @FXML
    private Label selectedModuleStatus;

    /**
     * Creates a {@code StatusBarFooter}.
     */
    public StatusBarFooter() {
        super(FXML);
        selectedModuleStatus.setText(MESSAGE_NO_MODULE_SELECTED);
    }

    public void setSelectedModule(Module module) {
        String selectedModule = MESSAGE_NO_MODULE_SELECTED;
        if (module != null && !module.toString().equals(CdCommand.WILDCARD_MODULE_CODE)) {
            selectedModule = module.toString();
        }

        selectedModuleStatus.setText(selectedModule);
    }

}
