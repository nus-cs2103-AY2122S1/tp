package seedu.academydirectory.ui.creator;

import java.net.URL;

import javafx.scene.Node;
import javafx.scene.layout.Region;
import seedu.academydirectory.model.AdditionalInfo;
import seedu.academydirectory.model.AdditionalViewModel;
import seedu.academydirectory.ui.UiPart;

public abstract class Creator extends UiPart<Region> {

    private final AdditionalViewModel additionalViewModel;
    private final String FXML;

    public Creator(AdditionalViewModel additionalViewModel, String FXML) {
        super(FXML);
        this.additionalViewModel = additionalViewModel;
        this.FXML = FXML;
    }

    public abstract Node create();
}
