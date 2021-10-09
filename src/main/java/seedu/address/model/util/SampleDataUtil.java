package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.ParentName;
import seedu.address.model.person.PaymentStatus;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Progress;
import seedu.address.model.person.StudentName;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static final Progress DEFAULT_PROGRESS = new Progress("No Progress");
    public static final PaymentStatus DEFAULT_PAYMENT_STATUS = new PaymentStatus(false);

    public static Person[] getSamplePersons() {
        return new Person[] {
            // All details available
            new Person(new StudentName("Alex Yeoh"), new Phone("87438807"),
                    new ParentName("Mr Yeoh"), new Phone("93726483"),
                    DEFAULT_PROGRESS, DEFAULT_PAYMENT_STATUS),
            new Person(new StudentName("Bernice Yu"), new Phone("99272758"),
                    new ParentName("Mrs Yu"), new Phone("83548274"),
                    DEFAULT_PROGRESS, DEFAULT_PAYMENT_STATUS),
            // Parent's details unavailable
            new Person(new StudentName("Charlotte Oliveiro"), new Phone("93210283"),
                    new ParentName(""), new Phone(""),
                    DEFAULT_PROGRESS, DEFAULT_PAYMENT_STATUS),
            // Phone details unavailable
            new Person(new StudentName("David Li"), new Phone(""),
                    new ParentName("Mr Li"), new Phone(""),
                    DEFAULT_PROGRESS, DEFAULT_PAYMENT_STATUS),
            // Phone and parent's details unavailable
            new Person(new StudentName("Irfan Ibrahim"), new Phone(""),
                    new ParentName(""), new Phone(""),
                    DEFAULT_PROGRESS, DEFAULT_PAYMENT_STATUS),
            // Student phone unavailable
            new Person(new StudentName("Roy Balakrishnan"), new Phone(""),
                    new ParentName("Mrs Balakrishnan"), new Phone("93628676"),
                    DEFAULT_PROGRESS, DEFAULT_PAYMENT_STATUS)
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

}
