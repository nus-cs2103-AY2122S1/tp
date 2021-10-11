package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.CategoryCode;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Review;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static Person[] getSamplePersons() {
        return new Person[]{
            new Person(new CategoryCode("att"), new Name("Marina Bay Sands"), new Phone("66888868"),
                new Email("marinabaysands@example.com"), new Address("10 Bayfront Ave, Singapore 018956"),
                new Review("amazing"),
                getTagSet("casino", "popular")),
            new Person(new CategoryCode("com"), new Name("VivoCity"), new Phone("63776860"),
                new Email("vivocity@example.com"), new Address("1 HarbourFront Walk, Singapore 098585"),
                new Review("meh"),
                getTagSet("mall", "south")),
            new Person(new CategoryCode("tpt"), new Name("Singapore DUCKtours"), new Phone("63386877"),
                new Email("sgducktours@example.com"), new Address("3 Temasek Blvd, #01-330 Suntec City,"
                + " Singapore 038983"),
                new Review("not bad"), getTagSet("tour")),
            new Person(new CategoryCode("fnb"), new Name("Bread Street Kitchen by Gordon Ramsay"),
                new Phone("66885665"), new Email("bskbygordon@example.com"),
                new Address("10 Bayfront Ave, L1 - 81, Singapore 018956"),
                new Review("the best"), getTagSet("michelin")),
            new Person(new CategoryCode("oth"), new Name("Esso Telok Blangah"), new Phone("62712705"),
                new Email("essotelokblangah@example.com"), new Address("396 Telok Blangah Road, Singapore 98837."),
                new Review("horrible"),
                getTagSet("kiosk", "break")),
            new Person(new CategoryCode("att"), new Name("Gardens By The Bay"), new Phone("64206848"),
                new Email("gbtb@example.com"), new Address("18 Marina Gardens Dr, Singapore 018953"),
                new Review("- No Review -"),
                getTagSet("park", "outdoor"))
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
