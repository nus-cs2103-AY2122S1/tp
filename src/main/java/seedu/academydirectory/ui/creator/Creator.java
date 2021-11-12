package seedu.academydirectory.ui.creator;

import javafx.scene.Node;
import javafx.scene.layout.Region;
import seedu.academydirectory.model.AdditionalInfo;
import seedu.academydirectory.ui.UiPart;

/**
 * Special sub-abstract-class of UIRegion that takes in an
 * Additional Info object and create a new Scene from it.
 */
public abstract class Creator extends UiPart<Region> {

    private final AdditionalInfo<?> additionalInfo;
    private final String fxml;
    /**
     * Constructor of an Additional View creator
     * @param additionalInfo information to be passed in
     * @param fxml file
     */
    public Creator(AdditionalInfo<?> additionalInfo, String fxml) {
        super(fxml);
        this.additionalInfo = additionalInfo;
        this.fxml = fxml;
    }

    /**
     * Create a new Scene/Node with the specified information
     * @return new Scene with additional information
     */
    public abstract Node create();

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Creator)) {
            return false;
        }
        Creator other = (Creator) obj;
        return this.additionalInfo.equals(other.additionalInfo)
                && this.fxml.equals(other.fxml);
    }
}
