package seedu.address.testutil;

import seedu.address.model.meeting.Attendee;
import seedu.address.model.meeting.DateTime;
import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.Meeting;

/**
 * A utility class to help with building Meeting objects.
 */
public class MeetingBuilder {

    public static final String DEFAULT_ATTENDEE = "T";
    public static final String DEFAULT_DATETIME = "2040-10-12 15:45";
    public static final String DEFAULT_TITLE = "Meeting with HOD on progress";
    public static final String DEFAULT_VENUE = "Meeting Room 4";

    private Attendee attendee;
    private DateTime dateTime;
    private Description title;
    private Description venue;

    /**
     * Creates a {@code MeetingBuilder} with the default details.
     */
    public MeetingBuilder() {
        attendee = new Attendee(DEFAULT_ATTENDEE);
        dateTime = new DateTime(DEFAULT_DATETIME);
        title = new Description(DEFAULT_TITLE);
        venue = new Description(DEFAULT_VENUE);
    }

    /**
     * Initializes the MeetingBuilder with the data of {@code meetingToCopy}.
     */
    public MeetingBuilder(Meeting meetingToCopy) {
        attendee = meetingToCopy.getAttendee();
        dateTime = meetingToCopy.getDateTime();
        title = meetingToCopy.getTitle();
        venue = meetingToCopy.getVenue();
    }

    /**
     * Sets the {@code Attendee} of the {@code Student} that we are building.
     */
    public MeetingBuilder withAttendee(String attendee) {
        this.attendee = new Attendee(attendee);
        return this;
    }


    /**
     * Sets the {@code dateTime} of the {@code Student} that we are building.
     */
    public MeetingBuilder withDateTime(String dateTime) {
        this.dateTime = new DateTime(dateTime);
        return this;
    }

    /**
     * Sets the {@code Title} of the {@code Student} that we are building.
     */
    public MeetingBuilder withTitle(String title) {
        this.title = new Description(title);
        return this;
    }

    /**
     * Sets the {@code Venue} of the {@code Student} that we are building.
     */
    public MeetingBuilder withVenue(String venue) {
        this.venue = new Description(venue);
        return this;
    }

    /**
     * Builds the {@code Meeting}.
     * @return the meeting.
     */
    public Meeting build() {
        return new Meeting(dateTime, attendee, title, venue);
    }
}
