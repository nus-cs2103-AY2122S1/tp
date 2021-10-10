package safeforhall.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import safeforhall.model.AddressBook;
import safeforhall.model.ReadOnlyAddressBook;
import safeforhall.model.person.Email;
import safeforhall.model.person.Faculty;
import safeforhall.model.person.LastCollectionDate;
import safeforhall.model.person.LastFetDate;
import safeforhall.model.person.Name;
import safeforhall.model.person.Person;
import safeforhall.model.person.Phone;
import safeforhall.model.person.Room;
import safeforhall.model.person.VaccStatus;
import safeforhall.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        // TODO: Compete lastFetDate and lastCollectionDate
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Room("E417"), new Phone("87438807"),
                    new Email("alexyeoh@example.com"), new VaccStatus("F"),
                    new Faculty("SoC"), new LastFetDate("TODO"), new LastCollectionDate("TODO")),
            new Person(new Name("Bernice Yu"), new Room("A213"), new Phone("99272758"),
                    new Email("berniceyu@example.com"), new VaccStatus("T"),
                    new Faculty("FASS"), new LastFetDate("TODO"), new LastCollectionDate("TODO")),
            new Person(new Name("Charlotte Oliveiro"), new Room("B423"), new Phone("93210283"),
                    new Email("charlotte@example.com"), new VaccStatus("T"),
                    new Faculty("SoC"), new LastFetDate("TODO"), new LastCollectionDate("TODO")),
            new Person(new Name("David Li"), new Room("C112"), new Phone("91031282"),
                    new Email("lidavid@example.com"), new VaccStatus("F"),
                    new Faculty("SDE"), new LastFetDate("TODO"), new LastCollectionDate("TODO")),
            new Person(new Name("Irfan Ibrahim"), new Room("D422"), new Phone("92492021"),
                    new Email("irfan@example.com"), new VaccStatus("T"),
                    new Faculty("FoE"), new LastFetDate("TODO"), new LastCollectionDate("TODO")),
            new Person(new Name("Roy Balakrishnan"), new Room("A309"), new Phone("92624417"),
                    new Email("royb@example.com"), new VaccStatus("T"),
                    new Faculty("BIZ"), new LastFetDate("TODO"), new LastCollectionDate("TODO")),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
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
