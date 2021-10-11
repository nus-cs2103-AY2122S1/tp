package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.student.*;
import seedu.address.model.student.Student;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static final Progress DEFAULT_PROGRESS = new Progress("No Progress");
    public static final PaymentStatus DEFAULT_PAYMENT_STATUS = new PaymentStatus(false);

    public static Student[] getSamplePersons() {
        return new Student[] {
            // All details available
            new Student(new StudentName("Alex Yeoh"), new Phone("87438807"),
                    new ParentName("Mr Yeoh"), new Phone("93726483"),
                    DEFAULT_PROGRESS, DEFAULT_PAYMENT_STATUS),
            new Student(new StudentName("Bernice Yu"), new Phone("99272758"),
                    new ParentName("Mrs Yu"), new Phone("83548274"),
                    DEFAULT_PROGRESS, DEFAULT_PAYMENT_STATUS),
            // Parent's details unavailable
            new Student(new StudentName("Charlotte Oliveiro"), new Phone("93210283"),
                    new ParentName(""), new Phone(""),
                    DEFAULT_PROGRESS, DEFAULT_PAYMENT_STATUS),
            // Phone details unavailable
            new Student(new StudentName("David Li"), new Phone(""),
                    new ParentName("Mr Li"), new Phone(""),
                    DEFAULT_PROGRESS, DEFAULT_PAYMENT_STATUS),
            // Phone and parent's details unavailable
            new Student(new StudentName("Irfan Ibrahim"), new Phone(""),
                    new ParentName(""), new Phone(""),
                    DEFAULT_PROGRESS, DEFAULT_PAYMENT_STATUS),
            // Student phone unavailable
            new Student(new StudentName("Roy Balakrishnan"), new Phone(""),
                    new ParentName("Mrs Balakrishnan"), new Phone("93628676"),
                    DEFAULT_PROGRESS, DEFAULT_PAYMENT_STATUS)
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Student sampleStudent : getSamplePersons()) {
            sampleAb.addPerson(sampleStudent);
        }
        return sampleAb;
    }

}
