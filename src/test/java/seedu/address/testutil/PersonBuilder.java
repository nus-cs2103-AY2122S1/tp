package seedu.address.testutil;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import seedu.address.model.person.Availability;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final List<DayOfWeek> DEFAULT_AVAILABILITY = new ArrayList<>();

    private Name name;
    private Phone phone;
    private Availability availability;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        availability = new Availability(DEFAULT_AVAILABILITY);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        availability = personToCopy.getAvailability();
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

    public Person build() {
        return new Person(name, phone, availability);
    }

}
