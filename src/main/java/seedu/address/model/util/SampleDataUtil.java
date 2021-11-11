package seedu.address.model.util;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.residency.Residency;
import seedu.address.model.room.Room;
import seedu.address.model.room.RoomNumber;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static Person[] getSamplePersons() {
        return new Person[] {
            Person.createPerson(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), new Nric("S9678901C"),
                    Set.of()),
            Person.createPerson(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Nric("S9876543A"),
                    Set.of()),
            Person.createPerson(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new Nric("000-00-0001"),
                    Set.of()),
            Person.createPerson(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new Nric("S9012345B"),
                    Set.of()),
            Person.createPerson(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), new Nric("000-00-0002"),
                    Set.of()),
            Person.createPerson(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), new Nric("000-00-0003"),
                    Set.of())
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Room sampleRoom : getSampleRooms()) {
            sampleAb.addRoom(sampleRoom);
        }
        for (Residency sampleResidency : getSampleResidency()) {
            sampleAb.record(sampleResidency);
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

    public static Room[] getSampleRooms() {
        return new Room[] {
            new Room(new RoomNumber("001"), getTagSet("normal")),
            new Room(new RoomNumber("002"), getTagSet("normal")),
            new Room(new RoomNumber("003"), getTagSet("normal")),
            new Room(new RoomNumber("004"), getTagSet("normal")),
            new Room(new RoomNumber("005"), getTagSet("normal")),
        };
    }

    public static Residency[] getSampleResidency() {
        Person[] guests = getSamplePersons();
        Person[] guestsOne = new Person[] {guests[0], guests[1], guests[2]};
        Person[] guestsTwo = new Person[] {guests[3], guests[4], guests[5]};
        Set<Person> guestsOneSet = Arrays.stream(guestsOne).collect(Collectors.toSet());
        Set<Person> guestsTwoSet = Arrays.stream(guestsTwo).collect(Collectors.toSet());
        Room[] rooms = getSampleRooms();
        LocalDateTime sampleDateOne = LocalDateTime.of(2021, 11, 1, 12, 0);
        LocalDateTime sampleDateTwo = LocalDateTime.of(2021, 11, 2, 12, 0);
        LocalDateTime sampleDateThree = LocalDateTime.of(2021, 10, 30, 12, 0);
        LocalDateTime sampleDateFour = LocalDateTime.of(2021, 10, 31, 12, 0);

        return new Residency[] {
            new Residency(rooms[0], guestsOneSet, sampleDateOne, sampleDateTwo),
            new Residency(rooms[1], guestsTwoSet, sampleDateThree, sampleDateFour)
        };
    }
}
