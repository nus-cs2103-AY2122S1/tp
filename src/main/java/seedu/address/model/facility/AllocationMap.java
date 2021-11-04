package seedu.address.model.facility;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import seedu.address.model.person.Person;

/**
 * Represents the mapping of member allocations to the day of the week in a Facility.
 */
public class AllocationMap {
    private Map<DayOfWeek, List<Person>> personsAllocatedMap;

    /**
     * Creates an AllocationMap object with a specified allocation map.
     *
     * @param personsAllocatedMap A valid allocation map.
     */
    public AllocationMap(Map<DayOfWeek, List<Person>> personsAllocatedMap) {
        this.personsAllocatedMap = personsAllocatedMap;
    }

    public Map<DayOfWeek, List<Person>> getPersonsAllocatedMap() {
        return personsAllocatedMap;
    }

    public int getCapacityOnDay(DayOfWeek day) {
        return personsAllocatedMap.get(day).size();
    }

    public void clearAllocationOnDay(DayOfWeek day) {
        personsAllocatedMap.get(day).clear();
    }

    public boolean isPersonAllocatedOnDay(Person person, DayOfWeek day) {
        return personsAllocatedMap.get(day).contains(person);
    }

    public void addPersonOnDay(Person person, DayOfWeek day) {
        personsAllocatedMap.get(day).add(person);
    }

    public void removePersonOnDay(Person person, DayOfWeek day) {
        personsAllocatedMap.get(day).remove(person);
    }

    /**
     * Removes a person from the allocation for all days.
     *
     * @param person Person to be removed from allocations on all days.
     */
    public void removePersonOnAllDays(Person person) {
        for (DayOfWeek day : DayOfWeek.values()) {
            personsAllocatedMap.get(day).remove(person);
        }
    }

    /**
     * Returns the string representation of the given list of persons.
     *
     * @param personList The list of persons to get the string representation of.
     * @return String representation of all persons in the list of persons.
     */
    public String getPersonsAsString(List<Person> personList) {
        return personList.stream().map(person -> person.getName().toString()).collect(Collectors.joining(", "));
    }

    public List<Person> getPersonsAllocatedOnDay(DayOfWeek day) {
        return personsAllocatedMap.get(day);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj == this)
                || (obj instanceof AllocationMap
                && personsAllocatedMap.equals(((AllocationMap) obj).personsAllocatedMap));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (DayOfWeek day : personsAllocatedMap.keySet()) {
            builder.append(day.getDisplayName(TextStyle.SHORT, Locale.getDefault()));
            builder.append(": ");
            builder.append(getPersonsAsString(personsAllocatedMap.get(day)));
            builder.append("\n");
        }

        return builder.toString();
    }
}
