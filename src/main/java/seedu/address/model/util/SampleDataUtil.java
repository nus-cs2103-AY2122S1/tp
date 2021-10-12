package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyTeachingAssistantBuddy;
import seedu.address.model.module.student.Email;
import seedu.address.model.module.student.Name;
import seedu.address.model.module.student.Student;
import seedu.address.model.module.student.StudentId;
import seedu.address.model.module.student.TeleHandle;
import seedu.address.model.tag.Tag;


/**
 * Contains utility methods for populating {@code TeachingAssistantBuddy} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSamplePersons() {
        return new Student[]{ new Student(new StudentId("Blk 30 Geylang Street 29, #06-40"), new Name("Alex Yeoh"),
                        new TeleHandle("87438807"), new Email("alexyeoh@example.com")),
                                new Student(new StudentId("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                                    new Name("Bernice Yu"),
                        new TeleHandle("99272758"), new Email("berniceyu@example.com")),
                                new Student(new StudentId("Blk 11 Ang Mo Kio Street 74, #11-04"),
                                    new Name("Charlotte Oliveiro"),
                        new TeleHandle("93210283"), new Email("charlotte@example.com")),
                                new Student(new StudentId("Blk 436 Serangoon Gardens Street 26, #16-43"),
                                    new Name("David Li"),
                        new TeleHandle("91031282"), new Email("lidavid@example.com")),
                                new Student(new StudentId("Blk 47 Tampines Street 20, #17-35"),
                                        new Name("Irfan Ibrahim"),
                        new TeleHandle("92492021"), new Email("irfan@example.com")),
                                new Student(new StudentId("Blk 45 Aljunied Street 85, #11-31"),
                                    new Name("Roy Balakrishnan"),
                        new TeleHandle("92624417"), new Email("royb@example.com"))
        };
    }
    public static ReadOnlyTeachingAssistantBuddy getSampleModule() {
        //        TeachingAssistantBuddy sampleAb = new TeachingAssistantBuddy();
        //        for (Student sampleStudent : getSamplePersons()) {
        //            sampleAb.addStudent(sampleStudent);
        //        }
        //        return sampleAb;
        return null;
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
