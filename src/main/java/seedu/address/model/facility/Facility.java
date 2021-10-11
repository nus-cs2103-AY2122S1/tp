package seedu.address.model.facility;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.person.Person;

/**
 * Represents a Facility in the address book.
 */
public class Facility {
    private final FacilityName name;
    private final Location location;
    private final Time time;
    private final Capacity capacity;
    private List<Person> personAllocatedList = new ArrayList<>();

    /**
     * Creates a Facility object with the specified name,
     * location, time and capacity.
     *
     * @param name Name of facility.
     * @param location Location of facility.
     * @param time Time of slot booked for facility.
     * @param capacity Capacity of facility booked.
     */
    public Facility(FacilityName name, Location location, Time time, Capacity capacity) {
        requireAllNonNull(name, location, time, capacity);
        this.name = name;
        this.location = location;
        this.time = time;
        this.capacity = capacity;
    }

    public FacilityName getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public Time getTime() {
        return time;
    }

    public Capacity getCapacity() {
        return capacity;
    }

    public void clearAllocationList() {
        personAllocatedList = new ArrayList<>();
    }

    public String getPersonsAsString() {
        if (personAllocatedList == null) {
            return "no person allocated";
        }

        StringBuilder builder = new StringBuilder();
        personAllocatedList.forEach(person -> builder
                .append(person.getName()).append(", "));

        return builder.toString();
    }

    public boolean isWithinMaxCapacity(int numberOfPersons) {
        return capacity.isWithinCapacity(numberOfPersons);
    }

    public void addPersonToFacility(Person person) {
        personAllocatedList.add(person);
    }

    /**
     * Returns true if both facilities have same identity
     * and field values.
     *
     * @param obj Object being compared with facility.
     * @return Boolean value of equality between two facilities.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Facility)) {
            return false;
        }

        Facility facility = (Facility) obj;
        return name.equals(facility.name)
                && location.equals(facility.location)
                && time.equals(facility.time)
                && capacity.equals(facility.capacity)
                && personAllocatedList.equals(facility.personAllocatedList);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(name)
                .append("; Location: ")
                .append(location)
                .append("; Time: ")
                .append(time)
                .append("; Capacity: ")
                .append(capacity);

        return builder.toString();
    }
}
