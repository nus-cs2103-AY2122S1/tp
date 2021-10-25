package seedu.academydirectory.ui.creator;

import java.net.URL;

import javax.swing.plaf.synth.Region;

import javafx.scene.control.Control;
import javafx.scene.control.TextArea;
import seedu.academydirectory.model.AdditionalInfo;
import seedu.academydirectory.model.AdditionalViewModel;
import seedu.academydirectory.ui.UiPart;

public class GraphCreator extends Creator {

    private static final String FXML = "DefaultCreator.fxml";

    private final AdditionalInfo<?> additionalInfo;
    public GraphCreator(AdditionalInfo<?> additionalInfo) {
        super(additionalInfo, FXML);
        this.additionalInfo = additionalInfo;
    }

    @Override
    public Control create() {
        return new TextArea("Work In Progress");
    }
}
