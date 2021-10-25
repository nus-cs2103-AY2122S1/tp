package seedu.academydirectory.ui.creator;

import java.net.URL;

import javafx.scene.control.Control;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import seedu.academydirectory.model.AdditionalInfo;
import seedu.academydirectory.model.AdditionalViewModel;
import seedu.academydirectory.ui.UiPart;

public class DefaultCreator extends Creator {

    private static final String FXML = "";

    private final AdditionalInfo<? extends Object> additionalInfo;

    public DefaultCreator(AdditionalViewModel additionalViewModel) {
        super(additionalViewModel, FXML);
        additionalInfo = additionalViewModel.getAdditionalInfo();
    }

    @Override
    public Control create() {
        return new TextArea("Work In Progress");
    }
}
