package seedu.fast.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.fast.model.Fast;
import seedu.fast.model.ReadOnlyFast;
import seedu.fast.model.person.Address;
import seedu.fast.model.person.Appointment;
import seedu.fast.model.person.AppointmentCount;
import seedu.fast.model.person.Email;
import seedu.fast.model.person.Name;
import seedu.fast.model.person.Person;
import seedu.fast.model.person.Phone;
import seedu.fast.model.person.Remark;
import seedu.fast.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static final Remark EMPTY_REMARK = new Remark("");
    public static final Appointment EMPTY_APPOINTMENT = new Appointment(Appointment.NO_APPOINTMENT,
            Appointment.NO_TIME, Appointment.NO_VENUE);

    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), EMPTY_REMARK,
                getTagSet("friends", "HighPriority"), EMPTY_APPOINTMENT, new AppointmentCount("1")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), EMPTY_REMARK,
                getTagSet("colleagues", "friends", "Savings"), EMPTY_APPOINTMENT, new AppointmentCount("0")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), EMPTY_REMARK,
                getTagSet("neighbours", "Investment"), EMPTY_APPOINTMENT, new AppointmentCount("0")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), EMPTY_REMARK,
                getTagSet("family"), EMPTY_APPOINTMENT, new AppointmentCount("0")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), EMPTY_REMARK,
                getTagSet("classmates", "LifeInsurance", "HealthInsurance"), EMPTY_APPOINTMENT,
                new AppointmentCount("0")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), EMPTY_REMARK,
                getTagSet("colleagues", "PropertyInsurance"), EMPTY_APPOINTMENT, new AppointmentCount("0")),
            new Person(new Name("Stuart Brown"), new Phone("98490328"), new Email("stuartb@example.com"),
                new Address("Blk 822 Sembawang Road, #02-13"), new Remark("He likes to eat pizza"),
                getTagSet("LowPriority", "friends"),
                new Appointment("16 Dec 2023", "1330", "Domino's Pizza"),
                new AppointmentCount("0")),
            new Person(new Name("Thomas Lim"), new Phone("98902563"), new Email("thomaslim@example.com"),
                new Address("Blk 145 Braddell Street 85, #20-13"), new Remark("He has a lot of money"),
                getTagSet("HighPriority", "acquaintance", "MotorInsurance"), EMPTY_APPOINTMENT,
                new AppointmentCount("0")),
            new Person(new Name("Wayne Robinson"), new Phone("92378943"), new Email("waynerob@example.com"),
                new Address("Blk 412 Bishan Ring Road, #04-13"), EMPTY_REMARK,
                getTagSet("MediumPriority", "oldfriend"),
                new Appointment("09 Sep 2023", "1200", "Bishan Park"),
                new AppointmentCount("0")),
            new Person(new Name("Jonathan Low"), new Phone("93576543"), new Email("lowjonathan@example.com"),
                    new Address("Blk 212 Pasar Ring Road, #03-11"), new Remark("He lives in the market"),
                    getTagSet("LowPriority", "TravelInsurance"),
                    EMPTY_APPOINTMENT, new AppointmentCount("0"))

        };
    }

    public static ReadOnlyFast getSampleFast() {
        Fast sampleAb = new Fast();
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
