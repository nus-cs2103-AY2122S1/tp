package seedu.times.ui.timetabletab;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.times.ui.UiPart;

// Solution below adapted from
// https://github.com/AY1920S2-CS2103-W15-4/main/blob/master/src/main/java/clzzz/helper/ui/calendar/CalendarRegion.java
/**
 * A UI for the timetable region.
 */
public class TimetableRegion extends UiPart<Region> {

    @FXML
    private HBox slot;

    /**
     * Creates the timetable region UI.
     *
     * @param fxml Specified file path of the fxml to construct the timetable region.
     * @param scale Amount to scale the Ui by.
     */
    public TimetableRegion(String fxml, long scale) {
        super(fxml);
        slot.setPrefWidth(scale * 2);
        slot.setMinWidth(scale * 2);
    }

}
