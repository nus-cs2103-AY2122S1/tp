package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.moduleclass.ModuleClass;

public class ModuleClassCard extends UiPart<Region> {

    private static final String FXML = "ModuleClassCard.fxml";

    public final ModuleClass moduleClass;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label moduleCode;
    @FXML
    private Label day;
    @FXML
    private Label time;
    @FXML
    private Label remark;

    /**
     * An UI component that displays information of a {@code moduleClass}.
     */
    public ModuleClassCard(ModuleClass moduleClass, int displayedIndex) {
        super(FXML);
        this.moduleClass = moduleClass;
        id.setText(displayedIndex + ". ");
        moduleCode.setText(moduleClass.getModuleCode().value);
        day.setText(moduleClass.getDay().toString());
        time.setText(moduleClass.getTime().toString());
        remark.setText(moduleClass.getRemark().value);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ModuleClassCard)) {
            return false;
        }

        ModuleClassCard card = (ModuleClassCard) other;
        return id.getText().equals(card.id.getText())
                && moduleClass.equals(moduleClass);
    }

}

