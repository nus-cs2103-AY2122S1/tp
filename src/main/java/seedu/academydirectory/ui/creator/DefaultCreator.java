package seedu.academydirectory.ui.creator;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import seedu.academydirectory.model.AdditionalInfo;

public class DefaultCreator extends Creator {

    private static final String FXML = "creator/DefaultCreator.fxml";

    private final AdditionalInfo<?> additionalInfo;

    @FXML
    private StackPane placeHolder;

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
