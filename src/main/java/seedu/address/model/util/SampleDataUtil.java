package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.student.ClassId;
import seedu.address.model.student.Grade;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Allard Quek"), new StudentId("A0212425H"), new ClassId("B01"), new Grade("F")),
            new Student(new Name("Samay Sagar"), new StudentId("A0212425H"), new ClassId("B02"), new Grade("A")),
            new Student(new Name("Erwin Quek"), new StudentId("A0212425H"), new ClassId("B03"), new Grade("A")),
            new Student(new Name("David Li"), new StudentId("A0212425H"), new ClassId("B04"), new Grade("A")),
            new Student(new Name("Irfan Ibrahim"), new StudentId("A0212425H"), new ClassId("B05"), new Grade("A")),
            new Student(new Name("Roy Balakrishnan"), new StudentId("A0212425H"), new ClassId("B06"), new Grade("A"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Student sampleStudent : getSampleStudents()) {
            sampleAb.addStudent(sampleStudent);
        }
        return sampleAb;
    }

    /**
     * Same method as getSampleAddressBook but with AddressBook object as its return type.
     * @return AddressBook filled with sample data
     */
    public static AddressBook fillSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Student sampleStudent : getSampleStudents()) {
            sampleAb.addStudent(sampleStudent);
        }
        return sampleAb;
    }

}
