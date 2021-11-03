package seedu.track2gather.model.util;

import seedu.track2gather.model.ReadOnlyTrack2Gather;
import seedu.track2gather.model.Track2Gather;
import seedu.track2gather.model.person.Person;
import seedu.track2gather.model.person.attributes.Address;
import seedu.track2gather.model.person.attributes.CaseNumber;
import seedu.track2gather.model.person.attributes.Email;
import seedu.track2gather.model.person.attributes.Name;
import seedu.track2gather.model.person.attributes.NextOfKinAddress;
import seedu.track2gather.model.person.attributes.NextOfKinName;
import seedu.track2gather.model.person.attributes.NextOfKinPhone;
import seedu.track2gather.model.person.attributes.Period;
import seedu.track2gather.model.person.attributes.Phone;
import seedu.track2gather.model.person.attributes.QuarantineAddress;
import seedu.track2gather.model.person.attributes.ShnPeriod;
import seedu.track2gather.model.person.attributes.WorkAddress;

/**
 * Contains utility methods for populating {@code Track2Gather} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new CaseNumber("1"), new Address("Blk 30 Geylang Street 29, #06-40"),
                    new WorkAddress(new Address("70 Pasir Panjang Rd, #03-71")),
                    new QuarantineAddress(new Address("Marina Bay Sands, 10 Bayfront Avenue")),
                    new ShnPeriod(new Period("2021-10-11 => 2021-10-24")),
                    new NextOfKinName(new Name("Alexander Yeoh")), new NextOfKinPhone(new Phone("92347283")),
                    new NextOfKinAddress(new Address("Blk 30 Geylang Street 29, #06-40"))),
            new Person(new Name("Bernice Teo"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new CaseNumber("2"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new WorkAddress(), new QuarantineAddress(),
                    new ShnPeriod(new Period("2021-10-02 => 2021-10-07")),
                    new NextOfKinName(), new NextOfKinPhone(), new NextOfKinAddress()),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new CaseNumber("3"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new WorkAddress(),
                    new QuarantineAddress(new Address("NUS Prince George's Park Residences")),
                    new ShnPeriod(new Period("2021-09-29 => 2021-10-05")),
                    new NextOfKinName(new Name("Christopher Oliveiro")), new NextOfKinPhone(new Phone("98290023")),
                    new NextOfKinAddress(new Address("Blk 26 Ang Mo Kio Street 74, #06-23"))),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new CaseNumber("4"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    new WorkAddress(), new QuarantineAddress(), new ShnPeriod(),
                    new NextOfKinName(), new NextOfKinPhone(new Phone("83546783")), new NextOfKinAddress()),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new CaseNumber("5"), new Address("Blk 47 Tampines Street 20, #17-35")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new CaseNumber("6"), new Address("Blk 45 Aljunied Street 85, #11-31")),
            new Person(new Name("Jane Ang"), new Phone("95958462"), new Email("jane@email.com"),
                    new CaseNumber("600204"), new Address("123 Changi Road #01-100 700123"),
                    new WorkAddress(new Address("50 Jurong Road 120050")),
                    new QuarantineAddress(new Address("12 Harbourfront Ring 123012")),
                    new ShnPeriod(new Period("2021-01-01 => 2021-01-14")),
                    new NextOfKinName(new Name("Peter Ang")), new NextOfKinPhone(new Phone("90011234")),
                    new NextOfKinAddress(new Address("73 Yishun Drive #10-301 310073")))
        };
    }

    public static ReadOnlyTrack2Gather getSampleTrack2Gather() {
        Track2Gather sampleAb = new Track2Gather();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

}
