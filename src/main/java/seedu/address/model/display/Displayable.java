package seedu.address.model.display;

import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

public interface Displayable {

    public UiPart<Region> asDisplayCard(int index);

}
