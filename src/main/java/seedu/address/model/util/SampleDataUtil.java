package seedu.address.model.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.healthcondition.HealthCondition;
import seedu.address.model.person.Address;
import seedu.address.model.person.Language;
import seedu.address.model.person.LastVisit;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Visit;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static final Optional<Visit> EXAMPLE_OVERDUE_VISIT = Optional.ofNullable(new Visit("2021-10-01 13:00"));
    public static final Optional<Visit> EXAMPLE_VALID_VISIT = Optional.ofNullable(new Visit(LocalDateTime.now()
            .plusDays(7).format(DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm"))));

    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Language("English"),
                new Address("Blk 30 Geylang Street 29, #06-40"), Optional.of(new LastVisit("2021-03-25 08:00")),
                    EXAMPLE_VALID_VISIT, Optional.empty(), Optional.empty(), getHealthConditionSet("diabetes")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Language("Chinese"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    Optional.of(new LastVisit("2021-04-27 12:00")), EXAMPLE_OVERDUE_VISIT, Optional.empty(),
                    Optional.empty(),
                    getHealthConditionSet("handicapped", "cancer")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Language("English"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), Optional.of(new LastVisit("2021-05-11 13:00")),
                    EXAMPLE_VALID_VISIT, Optional.empty(), Optional.empty(), getHealthConditionSet("amputee")),
            new Person(new Name("David Li"), new Phone("91031282"), new Language("English"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    Optional.of(new LastVisit("2021-08-23 10:00")),
                    EXAMPLE_VALID_VISIT, Optional.empty(), Optional.empty(), getHealthConditionSet("heart disease")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Language("Malay"),
                new Address("Blk 47 Tampines Street 20, #17-35"), Optional.of(new LastVisit("2021-09-01 11:00")),
                    EXAMPLE_VALID_VISIT, Optional.empty(), Optional.empty(), getHealthConditionSet("asthma")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Language("Tamil"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), Optional.of(new LastVisit("2021-09-28 09:00")),
                    EXAMPLE_OVERDUE_VISIT, Optional.empty(), Optional.empty(), getHealthConditionSet("dementia"))
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
     * Returns a healthCondition set containing the list of strings given.
     */
    public static Set<HealthCondition> getHealthConditionSet(String... strings) {
        return Arrays.stream(strings)
                .map(HealthCondition::new)
                .collect(Collectors.toSet());
    }

}
