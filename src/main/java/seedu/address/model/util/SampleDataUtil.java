package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.GitHubId;
import seedu.address.model.person.Name;
import seedu.address.model.person.NusNetworkId;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.TutorialId;
import seedu.address.model.person.Type;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("e0123456@u.nus.edu"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends"), new GitHubId("test1"), new NusNetworkId("e0123456"),
                new Type("student"), new StudentId("A0123456A"), new TutorialId("01")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("e0123456@u.nus.edu"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends"), new GitHubId("test2"), new NusNetworkId("e0123457"),
                new Type("student"), new StudentId("A0123456B"), new TutorialId("02")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("e0123456@u.nus.edu"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours"), new GitHubId("test3"), new NusNetworkId("e0123458"),
                new Type("student"), new StudentId("A0123456C"), new TutorialId("03")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("e0123456@u.nus.edu"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family"), new GitHubId("test4"), new NusNetworkId("e0123459"),
                new Type("student"), new StudentId("A0123456D"), new TutorialId("04")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("e0123456@u.nus.edu"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates"), new GitHubId("test5"), new NusNetworkId("e0123450"),
                new Type("student"), new StudentId("A0123456E"), new TutorialId("05")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("e0123456@u.nus.edu"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"), new GitHubId("test6"), new NusNetworkId("e0123451"),
                new Type("student"), new StudentId("A0123456F"), new TutorialId("06"))
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
