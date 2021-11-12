package seedu.academydirectory.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.academydirectory.model.AcademyDirectory;
import seedu.academydirectory.model.ReadOnlyAcademyDirectory;
import seedu.academydirectory.model.student.Assessment;
import seedu.academydirectory.model.student.Email;
import seedu.academydirectory.model.student.Name;
import seedu.academydirectory.model.student.Phone;
import seedu.academydirectory.model.student.Student;
import seedu.academydirectory.model.student.Telegram;
import seedu.academydirectory.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AcademyDirectory} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Telegram("@alexyeoh"),
                    getAssessment(35, 26, 60, 47, 33),
                    getTagSet("streams")),
            new Student(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Telegram("@berniceyu"),
                    getAssessment(34, 13, 4, 59, 50),
                    getTagSet("recursion")),
            new Student(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Telegram("@charlotte"),
                    getAssessment(30, 24, 5, 12, 48),
                    getTagSet("wishfulThinking", "recursion")),
            new Student(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Telegram("@lidavid"),
                    getAssessment(47, 5, 54, 57, 44),
                    getTagSet("higherOrderFunctions")),
            new Student(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Telegram("@irfan"),
                    getTagSet("genius")),
            new Student(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Telegram("@royb"),
                    getTagSet("abstractions"))
        };
    }

    public static ReadOnlyAcademyDirectory getSampleAcademyDirectory() {
        AcademyDirectory sampleAb = new AcademyDirectory();
        for (Student sampleStudent : getSampleStudents()) {
            sampleAb.addStudent(sampleStudent);
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

    public static Assessment getAssessment(Integer... gradeArray) {
        assert gradeArray.length == Assessment.ASSESSMENT_LIST.size()
                : "Provided list of grade has different length from list of supported Assessment";
        Assessment assessment = new Assessment();

        for (int i = 0; i < gradeArray.length; i += 1) {
            assessment.updateAssessmentGrade(Assessment.ASSESSMENT_LIST.get(i),
                                                gradeArray[i]);
        }

        return assessment.getCopy();
    }
}
