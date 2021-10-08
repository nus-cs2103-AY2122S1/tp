package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;

import com.calendarfx.model.Entry;
import com.calendarfx.model.Interval;

import seedu.address.model.lesson.Lesson;

/**
 * Converts a {@code Lesson} object instance to a calendar {@code Entry}.
 */
public class CalendarUtil {

    /**
     * Converts a {@code Lesson} to a calendar {@code Entry} for CalendarFX.
     * Adapted from CalendarFX API example: https://dlsc.com/wp-content/html/calendarfx/apidocs/index.html
     *
     * @param lesson The lesson to be converted to a calendar entry.
     */
    public static Entry<Lesson> convertToEntry(Lesson lesson) {
        requireNonNull(lesson);

        Entry<Lesson> entry = new Entry<>();
        entry.setUserObject(lesson);
        Interval entryInterval = new Interval(lesson.getStartDateTime(), lesson.getEndDateTime());
        entry.setInterval(entryInterval);
        StringBuilder entryTitle = new StringBuilder(lesson.getName().toString());
        entryTitle.append(" (" + lesson.getSubject().toString() + ")");
        if (lesson.isRecurring()) {
            entry.setRecurrenceRule("RRULE:FREQ=WEEKLY");
            entryTitle.append("(Recurring)");
        }
        entry.setTitle(entryTitle.toString());
        return entry;
    }
}
