package seedu.academydirectory.ui.creator;


import javafx.scene.Node;
import javafx.scene.layout.Region;
import seedu.academydirectory.model.AdditionalInfo;
import seedu.academydirectory.ui.UiPart;

public abstract class Creator extends UiPart<Region> {

    private AdditionalInfo<?> additionalInfo;
    public Creator(AdditionalInfo<?> additionalInfo, String FXML) {
        super(FXML);
        this.additionalInfo = additionalInfo;
    }

    public abstract Node create();
}
