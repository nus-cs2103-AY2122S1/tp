package tutoraid.model.util;

import tutoraid.model.ReadOnlyStudentBook;
import tutoraid.model.StudentBook;
import tutoraid.model.student.Student;
import tutoraid.model.student.*;

/**
 * Contains utility methods for populating {@code StudentBook} with sample data.
 */
public class SampleDataUtil {

    public static final Progress DEFAULT_PROGRESS = new Progress("No Progress");
    public static final PaymentStatus DEFAULT_PAYMENT_STATUS = new PaymentStatus(false);

    public static Student[] getSampleStudents() {
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

    public static ReadOnlyStudentBook getSampleStudentBook() {
        StudentBook sampleAb = new StudentBook();
        for (Student sampleStudent : getSampleStudents()) {
            sampleAb.addStudent(sampleStudent);
        }
        return sampleAb;
    }

}
