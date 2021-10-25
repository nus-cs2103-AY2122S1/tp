package seedu.academydirectory.ui.creator;

import javafx.scene.control.Control;
import seedu.academydirectory.model.AdditionalInfo;

public interface Creator {
    Control create(AdditionalInfo<? extends Object> additionalInfo);
}
