package seedu.times.ui.timetabletab;

import java.time.temporal.ChronoUnit;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seedu.times.model.tuitionclass.TuitionClass;

// Solution below adapted from
// https://github.com/AY1920S2-CS2103-W15-4/main/blob/master/src/main/java/clzzz/helper/ui/calendar/CalendarSlot.java
/**
 * Ui for the timetable tuition class slot.
 */
public class TimetableTuitionClassSlot extends TimetableRegion {

    private static final String FXML = "timetableTab/TimetableTuitionClassSlot.fxml";

    @FXML
    private Label time;

    @FXML
    private Label className;

    /**
     * Creates a timetable tuition class slot on the Timetable Ui.
     *
     * @param tuitionClass tuitionClass to be built into the Timetable Ui
     */
    public TimetableTuitionClassSlot(TuitionClass tuitionClass) {
        super(FXML, tuitionClass.getStartTime().until(tuitionClass.getEndTime(), ChronoUnit.MINUTES));
        className.setText(tuitionClass.getClassNameString());
        time.setText(tuitionClass.getClassTiming().getClassTiming());
    }
}
