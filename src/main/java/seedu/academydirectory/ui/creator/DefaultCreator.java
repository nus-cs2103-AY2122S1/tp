package seedu.academydirectory.ui.creator;

import javafx.scene.Node;
import seedu.academydirectory.model.AdditionalInfo;

public class DefaultCreator extends Creator {

    private static final String FXML = "DefaultCreator.fxml";

    private final AdditionalInfo<?> additionalInfo;

    /**
     * Constructor of Default Creator
     * @param additionalInfo information
     */
    public DefaultCreator(AdditionalInfo<?> additionalInfo) {
        super(additionalInfo, FXML);
        this.additionalInfo = additionalInfo;
    }

    @Override
    public Node create() {
        return getRoot();
    }
}
