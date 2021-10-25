package seedu.academydirectory.ui.creator;

import java.net.URL;

import javax.swing.plaf.synth.Region;

import javafx.scene.control.Control;
import javafx.scene.control.TextArea;
import seedu.academydirectory.model.AdditionalInfo;
import seedu.academydirectory.model.AdditionalViewModel;
import seedu.academydirectory.ui.UiPart;

public class GraphCreator extends Creator {

    private static final String FXML = "";

    private AdditionalInfo<? extends Object> additionalInfo;
    public GraphCreator(AdditionalViewModel additionalViewModel) {
        super(additionalViewModel, FXML);
    }

    @Override
    public Control create() {
        return new TextArea("Work In Progress");
    }
}
