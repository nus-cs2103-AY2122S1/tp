package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Period;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.person.Salary;
import seedu.address.model.person.Status;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                getRoleSet("floor"),
                    new Salary("11"), Status.PART_TIME,
                    getTagSet("friends"), getPeriodSet()),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    getRoleSet("kitchen"), new Salary("6"),
                    Status.FULL_TIME, getTagSet("colleagues", "friends"), getPeriodSet()),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    getRoleSet("bartender"), new Salary("7.5"),
                    Status.PART_TIME, getTagSet("neighbours"), getPeriodSet()),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    getRoleSet("kitchen"), new Salary("8"),
                    Status.FULL_TIME, getTagSet("family"), getPeriodSet()),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    getRoleSet("kitchen"), new Salary("8.5"), Status.PART_TIME,
                    getTagSet("classmates"), getPeriodSet()),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    getRoleSet("floor"), new Salary("7"), Status.PART_TIME,
                    getTagSet("colleagues"), getPeriodSet())
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
     * Returns a period set containing the list of strings given.
     */
    public static Set<Period> getPeriodSet(String ... strings) {
        return Arrays.stream(strings)
                .map(Period::transformStringToPeriod)
                .collect(Collectors.toSet());
    }


    /**
     * Returns a role set containing the list of strings given.
     */
    public static Set<Role> getRoleSet(String... strings) {
        return Arrays.stream(strings)
                .map(Role::translateStringToRoleWithNoRole)
                .collect(Collectors.toSet());
    }
}
