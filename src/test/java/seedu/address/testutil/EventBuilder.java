package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.event.Date;
import seedu.address.model.event.Event;
import seedu.address.model.event.Location;
import seedu.address.model.event.Name;
import seedu.address.model.event.Remark;
import seedu.address.model.event.TimeSlot;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Event objects.
 */
public class EventBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_DATE = "2020-01-01";
    public static final String DEFAULT_STARTTIME = "0800";
    public static final String DEFAULT_ENDTIME = "0900";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Date date;
    private TimeSlot timeSlot;
    private Location address;
    private Set<Tag> tags;
    private Remark remark;

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        name = new Name(DEFAULT_NAME);
        date = new Date(DEFAULT_DATE);
        timeSlot = new TimeSlot(DEFAULT_STARTTIME, DEFAULT_ENDTIME);
        address = new Location(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        remark = new Remark("");
    }

    /**
     * Initializes the EventBuilder with the data of {@code eventToCopy}.
     */
    public EventBuilder(Event eventToCopy) {
        name = eventToCopy.getName();
        date = eventToCopy.getDate();
        timeSlot = eventToCopy.getTimeSlot();
        address = eventToCopy.getLocation();
        tags = new HashSet<>(eventToCopy.getTags());
        remark = eventToCopy.getRemark();
    }

    /**
     * Sets the {@code Name} of the {@code Event} that we are building.
     */
    public EventBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Event} that we are building.
     */
    public EventBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Event} that we are building.
     */
    public EventBuilder withLocation(String address) {
        this.address = new Location(address);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Event} that we are building.
     */
    public EventBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    /**
     * Sets the {@code TimeSlot} of the {@code Event} that we are building.
     */
    public EventBuilder withTimeSlot(String startTime, String endTime) {
        this.timeSlot = new TimeSlot(startTime, endTime);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Event} that we are building.
     */
    public EventBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    public Event build() {
        return new Event(name, date, timeSlot, address, tags, remark);
    }

}
