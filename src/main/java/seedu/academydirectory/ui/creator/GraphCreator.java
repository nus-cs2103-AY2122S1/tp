package seedu.academydirectory.ui.creator;

import javafx.scene.control.TextArea;
import javafx.scene.control.Control;
import seedu.academydirectory.model.AdditionalInfo;

public class GraphCreator implements Creator {
    @Override
    public Control create(AdditionalInfo<?> additionalInfo) {
        return new TextArea("Work In Progress");
    }
}
