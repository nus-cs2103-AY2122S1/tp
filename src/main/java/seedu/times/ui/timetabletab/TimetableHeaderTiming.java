package seedu.times.ui.timetabletab;

import java.time.LocalTime;

/**
 * A UI for the header timing of the timetable.
 */
public class TimetableHeaderTiming extends TimetableHeader {

    private static final int TIMETABLE_HEADER_SLOT_LENGTH = 60;

    /**
     * Creates a new TimetableHeaderTiming with a start time and end time.
     *
     * @param headerStartTime Start time of the header.
     * @param headerEndTime End time of the header.
     */
    public TimetableHeaderTiming(LocalTime headerStartTime, LocalTime headerEndTime) {
        super(headerStartTime.toString() + "-" + headerEndTime.toString(), TIMETABLE_HEADER_SLOT_LENGTH);
    }

}
