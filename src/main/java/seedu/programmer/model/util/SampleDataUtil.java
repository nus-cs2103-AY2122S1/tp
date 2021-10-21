package seedu.programmer.model.util;

import seedu.programmer.model.ProgrammerError;
import seedu.programmer.model.ReadOnlyProgrammerError;
import seedu.programmer.model.student.*;
import seedu.programmer.model.student.Email;


/**
 * Contains utility methods for populating {@code ProgrammerError} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Allard Quek"), new StudentId("A0212421H"), new ClassId("B01"), new Email("e0518431@u.nus.edu")),
            new Student(new Name("Samay Sagar"), new StudentId("A0212422H"), new ClassId("B02"), new Email("e0513441@u.nus.edu")),
            new Student(new Name("Erwin Quek"), new StudentId("A0212423H"), new ClassId("B03"), new Email("e0538441@u.nus.edu")),
            new Student(new Name("David Li"), new StudentId("A0212424H"), new ClassId("B04"), new Email("e0518641@u.nus.edu")),
            new Student(new Name("Irfan Ibrahim"), new StudentId("A0212425H"), new ClassId("B05"), new Email("e0558441@u.nus.edu")),
            new Student(new Name("Roy Balakrishnan"), new StudentId("A0212426H"), new ClassId("B06"), new Email("e0518481@u.nus.edu"))
        };
    }

    public static ReadOnlyProgrammerError getSampleProgrammerError() {
        ProgrammerError sampleAb = new ProgrammerError();
        for (Student sampleStudent : getSampleStudents()) {
            sampleAb.addStudent(sampleStudent);
        }
        return sampleAb;
    }

    /**
     * Same method as getSampleProgrammerError but with ProgrammerError object as its return type.
     * @return ProgrammerError filled with sample data
     */
    public static ProgrammerError fillSampleProgrammerError() {
        ProgrammerError sampleAb = new ProgrammerError();
        for (Student sampleStudent : getSampleStudents()) {
            sampleAb.addStudent(sampleStudent);
        }
        return sampleAb;
    }

}
