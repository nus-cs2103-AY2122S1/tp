package seedu.address.model.util;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.facility.AllocationMap;
import seedu.address.model.facility.Capacity;
import seedu.address.model.facility.Facility;
import seedu.address.model.facility.FacilityName;
import seedu.address.model.facility.Location;
import seedu.address.model.facility.Time;
import seedu.address.model.person.Availability;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static final List<DayOfWeek> SAMPLE_AVAILABILITY =
            Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY);

    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Availability(SAMPLE_AVAILABILITY),
                    getTagSet("exco")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Availability(SAMPLE_AVAILABILITY),
                    getTagSet("y1")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Availability(SAMPLE_AVAILABILITY),
                    getTagSet("exco", "y2")),
            new Person(new Name("David Li"), new Phone("91031282"), new Availability(SAMPLE_AVAILABILITY),
                    getTagSet("y3")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Availability(SAMPLE_AVAILABILITY),
                    getTagSet("coach")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Availability(SAMPLE_AVAILABILITY),
                    getTagSet("y3"))
        };
    }

    public static Facility[] getSampleFacilities() {
        // to initialize separate maps for each sample facility
        Map<DayOfWeek, List<Person>> emptyAllocationMap1 = new EnumMap<>(DayOfWeek.class);
        Map<DayOfWeek, List<Person>> emptyAllocationMap2 = new EnumMap<>(DayOfWeek.class);
        Map<DayOfWeek, List<Person>> emptyAllocationMap3 = new EnumMap<>(DayOfWeek.class);
        Map<DayOfWeek, List<Person>> emptyAllocationMap4 = new EnumMap<>(DayOfWeek.class);

        for (DayOfWeek day : DayOfWeek.values()) {
            emptyAllocationMap1.put(day, new ArrayList<>());
            emptyAllocationMap2.put(day, new ArrayList<>());
            emptyAllocationMap3.put(day, new ArrayList<>());
            emptyAllocationMap4.put(day, new ArrayList<>());
        }

        return new Facility[] {
            new Facility(new FacilityName("Court 1"), new Location("University Sports Hall"),
                    new Time("1130"), new Capacity("5"), new AllocationMap(new EnumMap<>(emptyAllocationMap1))),
            new Facility(new FacilityName("NUS Field 2"), new Location("Opp University Hall"),
                    new Time("1330"), new Capacity("8"), new AllocationMap(new EnumMap<>(emptyAllocationMap2))),
            new Facility(new FacilityName("Court 2"), new Location("University Sports Hall"),
                    new Time("2030"), new Capacity("10"), new AllocationMap(new EnumMap<>(emptyAllocationMap3))),
            new Facility(new FacilityName("Court 3"), new Location("University Sports Hall"),
                    new Time("1230"), new Capacity("6"), new AllocationMap(new EnumMap<>(emptyAllocationMap4)))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }

        for (Facility sampleFacility : getSampleFacilities()) {
            sampleAb.addFacility(sampleFacility);
        }

        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }
}
