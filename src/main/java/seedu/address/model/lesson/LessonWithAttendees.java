package seedu.address.model.lesson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Lesson that wraps around a Lesson and its corresponding List of attendees
 * Use for transporting the association between a lesson and its attendees
 */
public class LessonWithAttendees {
    private Lesson lesson;
    private List<Attendee> attendeeList;

    /**
     * Constructor for lesson with attendees
     * @param lesson to hold
     * @param attendees to hold
     */
    public LessonWithAttendees(Lesson lesson, List<Attendee> attendees) {
        this.lesson = lesson;
        this.attendeeList = new ArrayList<>(attendees);
    }

    public Lesson getLesson() {
        return lesson;
    }

    public List<Attendee> getAttendeeList() {
        return Collections.unmodifiableList(attendeeList);
    }

    public static class SortByLesson implements Comparator<LessonWithAttendees> {
        @Override
        public int compare(LessonWithAttendees o1, LessonWithAttendees o2) {
            return o1.lesson.compareTo(o2.lesson);
        }
    }
}
