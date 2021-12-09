package seedu.times.ui.timetabletab;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

// Solution below adapted from
// https://github.com/AY1920S2-CS2103-W15-4/main/blob/master/src/main/java/clzzz/helper/ui/calendar/CalendarBuffer.java
/**
 * A UI for the empty slot with no tuition class in the timetable.
 */
public class TimetableEmptySlot extends TimetableRegion {

    private static final String FXML = "timetableTab/TimetableEmptySlot.fxml";

    /**
     * Builds the empty slot with no tuition class.
     *
     * @param startTime Start Timing of empty slot.
     * @param endTime End Timing of empty slot.
     */
    public TimetableEmptySlot(LocalTime startTime, LocalTime endTime) {
        super(FXML, startTime.until(endTime, ChronoUnit.MINUTES));
    }
}
