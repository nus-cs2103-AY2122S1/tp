package seedu.academydirectory.ui.creator;

import javafx.scene.control.Control;
import javafx.scene.control.TextArea;
import seedu.academydirectory.model.AdditionalInfo;

public class GraphCreator extends Creator {

    private static final String FXML = "DefaultCreator.fxml";

    private final AdditionalInfo<?> additionalInfo;

    /**
     * Constructor of Graph Creator
     * @param additionalInfo info to be passed in
     */
    public GraphCreator(AdditionalInfo<?> additionalInfo) {
        super(additionalInfo, FXML);
        this.additionalInfo = additionalInfo;
    }

    /**
     * Create the view
     * @return new View
     */
    @Override
    public Control create() {
        return new TextArea("Work In Progress");
    }
}
