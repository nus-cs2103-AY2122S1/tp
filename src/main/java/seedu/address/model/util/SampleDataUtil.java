package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.Money;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Insurance;
import seedu.address.model.person.InsuranceType;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Revenue;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static final Revenue ZERO_REVENUE = new Revenue(new Money(0));

    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Revenue(new Money(0)), new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends"), getInsuranceSet("Life"), new Note("Likes chicken"),
                new Appointment(""), new HashSet<>()),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends"), getInsuranceSet("Health"),
                new Note("Enjoys beef"), new Appointment(""), new HashSet<>()),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours"), getInsuranceSet("Life", "Health"),
                new Note("Eats chinese food"), new Appointment(""), new HashSet<>()),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family"), getInsuranceSet("General"),
                new Note("Does not eat pork"), new Appointment(""), new HashSet<>()),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates"), getInsuranceSet("Life", "General"),
                new Note("Does not like cake"), new Appointment(""), new HashSet<>()),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"), getInsuranceSet(), new Note("Does not like coffee"),
                    new Appointment(""), new HashSet<>())
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

    /**
     * Returns an Insurance, assumed to be valid
     */
    public static Insurance ofValidInsurance(String insuranceName) {
        for (InsuranceType type : InsuranceType.values()) {
            if (type.getTypeName().equalsIgnoreCase(insuranceName)) {
                return new Insurance(type);
            }
        }
        assert false; // This shouldn't happen in testing
        return null;
    }

    /**
     * Returns an insurance set containing the list of insurances given.
     */
    public static Set<Insurance> getInsuranceSet(String... insurances) {
        return Arrays.stream(insurances)
                .map(SampleDataUtil::ofValidInsurance)
                .collect(Collectors.toSet());
    }

}
