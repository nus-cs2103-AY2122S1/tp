package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ApplicantBook;
import seedu.address.model.PositionBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyApplicantBook;
import seedu.address.model.ReadOnlyPositionBook;
import seedu.address.model.applicant.Address;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.Email;
import seedu.address.model.applicant.Name;
import seedu.address.model.applicant.Phone;
import seedu.address.model.person.Person;
import seedu.address.model.position.Description;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new seedu.address.model.person.Name("Alex Yeoh"),
                    new seedu.address.model.person.Phone("87438807"),
                    new seedu.address.model.person.Email("alexyeoh@example.com"),
                    new seedu.address.model.person.Address("Blk 30 Geylang Street 29, #06-40"),
                        getTagSet("friends")),
            new Person(new seedu.address.model.person.Name("Bernice Yu"),
                    new seedu.address.model.person.Phone("99272758"),
                    new seedu.address.model.person.Email("berniceyu@example.com"),
                    new seedu.address.model.person.Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                        getTagSet("colleagues", "friends")),
            new Person(new seedu.address.model.person.Name("Charlotte Oliveiro"),
                    new seedu.address.model.person.Phone("93210283"),
                    new seedu.address.model.person.Email("charlotte@example.com"),
                    new seedu.address.model.person.Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                        getTagSet("neighbours")),
            new Person(new seedu.address.model.person.Name("David Li"),
                    new seedu.address.model.person.Phone("91031282"),
                    new seedu.address.model.person.Email("lidavid@example.com"),
                    new seedu.address.model.person.Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                        getTagSet("family")),
            new Person(new seedu.address.model.person.Name("Irfan Ibrahim"),
                    new seedu.address.model.person.Phone("92492021"),
                    new seedu.address.model.person.Email("irfan@example.com"),
                    new seedu.address.model.person.Address("Blk 47 Tampines Street 20, #17-35"),
                        getTagSet("classmates")),
            new Person(new seedu.address.model.person.Name("Roy Balakrishnan"),
                    new seedu.address.model.person.Phone("92624417"),
                    new seedu.address.model.person.Email("royb@example.com"),
                    new seedu.address.model.person.Address("Blk 45 Aljunied Street 85, #11-31"),
                        getTagSet("colleagues"))
        };
    }

    public static Position[] getSamplePositions() {
        return new Position[] {
            new Position(new Title("software engineer"), new Description("work in a team that builds a "
                        + "facial recognition application")),
            new Position(new Title("database administrator"), new Description("handles database administration "
                        + "matters"))
        };
    }

    public static Applicant[] getSampleApplicants() {
        return new Applicant[] {
            new Applicant(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"), new Position(new Title("software engineer"),
                    new Description("work in a team that builds a facial recognition application"))),
            new Applicant(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new Position(new Title("database administrator"),
                            new Description("handles database administration matters")))
        };
    }

    public static ReadOnlyApplicantBook getSampleApplicantBook() {
        ApplicantBook sampleApplicantBook = new ApplicantBook();
        for (Applicant sampleApplicant : getSampleApplicants()) {
            sampleApplicantBook.addApplicant(sampleApplicant);
        }
        return sampleApplicantBook;
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

    public static ReadOnlyPositionBook getSamplePositionBook() {
        PositionBook samplePb = new PositionBook();
        for (Position samplePosition : getSamplePositions()) {
            samplePb.addPosition(samplePosition);
        }
        return samplePb;
    }
}
