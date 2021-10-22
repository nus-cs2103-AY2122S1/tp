package seedu.address.testutil;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.person.Availability;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TodayAttendance;
import seedu.address.model.person.TotalAttendance;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final Boolean DEFAULT_TODAY_ATTENDANCE = false;
    public static final Integer DEFAULT_TOTAL_ATTENDANCE = 0;
    public static final List<DayOfWeek> DEFAULT_AVAILABILITY = new ArrayList<>();

    private Name name;
    private Phone phone;
    private Availability availability;
    private TotalAttendance totalAttendance;
    private TodayAttendance todayAttendance;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        availability = new Availability(DEFAULT_AVAILABILITY);
        totalAttendance = new TotalAttendance(DEFAULT_TOTAL_ATTENDANCE);
        todayAttendance = new TodayAttendance(DEFAULT_TODAY_ATTENDANCE);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        availability = personToCopy.getAvailability();
        todayAttendance = personToCopy.getTodayAttendance();
        totalAttendance = personToCopy.getTotalAttendance();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Availability} of the {@code Person} that we are building.
     */
    public PersonBuilder withAvailability(List<DayOfWeek> availability) {
        this.availability = new Availability(availability);
        return this;
    }

    /**
     * Sets {@code TodayAttendance} of the {@code Person} that we are building.
     */
    public PersonBuilder withTodayAttendance(Boolean attendance) {
        this.todayAttendance = new TodayAttendance(attendance);
        return this;
    }

    /**
     * Sets the {@code Availability} of the {@code Person} that we are building.
     * Used with string input.
     */
    public PersonBuilder withAvailability(String availabilityString) {
        List<DayOfWeek> availability = new ArrayList<>();
        for (String dayNumber : availabilityString.split(" ")) {
            availability.add(DayOfWeek.of(Integer.parseInt(dayNumber)));
        }
        this.availability = new Availability(availability);
        return this;
    }

    /**
     * Sets {@code TotalAttendance} of the {@code Person} that we are building.
     */
    public PersonBuilder withTotalAttendance(Integer attendance) {
        this.totalAttendance = new TotalAttendance(attendance);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public Person build() {
        return new Person(name, phone, availability, todayAttendance, totalAttendance, tags);
    }

}
