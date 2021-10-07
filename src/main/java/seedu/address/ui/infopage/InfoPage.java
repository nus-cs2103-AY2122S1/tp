package seedu.address.ui.infopage;


import javafx.scene.Node;
import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

public abstract class InfoPage extends UiPart<Region> {

    public static InfoPage DISPLAYED_PAGE;

    public InfoPage(String fxmlFile){
        super(fxmlFile);
    }

}
