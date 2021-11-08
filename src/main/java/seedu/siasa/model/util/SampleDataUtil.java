package seedu.siasa.model.util;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.siasa.model.ReadOnlySiasa;
import seedu.siasa.model.Siasa;
import seedu.siasa.model.contact.Address;
import seedu.siasa.model.contact.Contact;
import seedu.siasa.model.contact.Email;
import seedu.siasa.model.contact.Name;
import seedu.siasa.model.contact.Phone;
import seedu.siasa.model.policy.Commission;
import seedu.siasa.model.policy.CoverageExpiryDate;
import seedu.siasa.model.policy.PaymentStructure;
import seedu.siasa.model.policy.Policy;
import seedu.siasa.model.policy.Title;
import seedu.siasa.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Siasa} with sample data.
 */
public class SampleDataUtil {
    public static Contact[] getSampleContacts() {
        return new Contact[] {
            new Contact(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Contact(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Contact(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Contact(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Contact(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Contact(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static Policy[] getSamplePolicies() {
        return new Policy[] {
            new Policy(new Title("Life Policy"),
                new PaymentStructure(400000, 1, 1),
                new CoverageExpiryDate(LocalDate.now().plusYears(1)),
                new Commission(10, 1),
                new Contact(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"),
                    getTagSet("friends")),
                getTagSet("yearly")),
            new Policy(new Title("Critical Illness Policy"),
                new PaymentStructure(30000, 12, 120),
                new CoverageExpiryDate(LocalDate.now().plusYears(10)),
                new Commission(15, 120),
                new Contact(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getTagSet("colleagues", "friends")),
                getTagSet("aviva"))
        };
    }

    public static ReadOnlySiasa getSampleSiasa() {
        Siasa sampleSiasa = new Siasa();
        for (Contact sampleContact : getSampleContacts()) {
            sampleSiasa.addContact(sampleContact);
        }
        for (Policy policy : getSamplePolicies()) {
            assert sampleSiasa.hasContact(policy.getOwner());
            sampleSiasa.addPolicy(policy);
        }
        return sampleSiasa;
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
