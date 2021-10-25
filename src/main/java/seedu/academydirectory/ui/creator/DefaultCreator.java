package seedu.academydirectory.ui.creator;

import javafx.scene.control.Control;
import javafx.scene.control.TextArea;
import seedu.academydirectory.model.AdditionalInfo;

public class DefaultCreator implements Creator {

    @Override
    public Control create(AdditionalInfo<?> additionalInfo) {
        return new TextArea("Work In Progress");
    }
}
