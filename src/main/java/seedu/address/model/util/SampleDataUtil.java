package seedu.address.model.util;

import java.util.Arrays;

import seedu.address.model.CsBook;
import seedu.address.model.ReadOnlyCsBook;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.assessment.AssessmentName;
import seedu.address.model.assessment.Score;
import seedu.address.model.group.Description;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Note;
import seedu.address.model.student.Student;
import seedu.address.model.student.TelegramHandle;

/**
 * Contains utility methods for populating {@code CsBook} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSampleStudents() {

        Student alex = new Student(new Name("Alex Yeoh"), new TelegramHandle("@alex_yeoh"),
                new Email("alexyeoh@u.nus.edu"), new Note("Weak in UML diagrams"), new GroupName("CS2103T"));
        alex.addAssessment(new Assessment(new AssessmentName("iP"), new Score(20, 20)));
        alex.addAssessment(new Assessment(new AssessmentName("tP"), new Score(47, 50)));
        alex.addAssessment(new Assessment(new AssessmentName("Finals"), new Score(13, 30)));

        Student bernice = new Student(new Name("Bernice Yu"), new TelegramHandle("@bernice_yu"),
                new Email("berniceyu@u.nus.edu"), new Note("Can work on articulation of vowels"),
                new GroupName("CS2101"));
        bernice.addAssessment(new Assessment(new AssessmentName("OP 1"), new Score(73, 100)));
        bernice.addAssessment(new Assessment(new AssessmentName("OP 2"), new Score(71, 100)));

        Student charlotte = new Student(new Name("Charlotte Oliveiro"), new TelegramHandle("@charlotte"),
                new Email("charlotte@u.nus.edu"), new Note("Very convincing presentations"), new GroupName("CS2101"));
        charlotte.addAssessment(new Assessment(new AssessmentName("OP 1"), new Score(85, 100)));
        charlotte.addAssessment(new Assessment(new AssessmentName("OP 2"), new Score(91, 100)));

        Student david = new Student(new Name("David Li"), new TelegramHandle("@david_li"),
                new Email("lidavid@u.nus.edu"), new Note("Weak in OOP principles"), new GroupName("CS2103T"));
        david.addAssessment(new Assessment(new AssessmentName("iP"), new Score(17, 20)));
        david.addAssessment(new Assessment(new AssessmentName("tP"), new Score(36, 50)));
        david.addAssessment(new Assessment(new AssessmentName("Finals"), new Score(27, 30)));

        Student edgar = new Student(new Name("Edgar Oliveiro"), new TelegramHandle("@edgar_oliveiro"),
                new Email("edgar@u.nus.edu"), new Note(""), new GroupName("CS2103T"));
        edgar.addAssessment(new Assessment(new AssessmentName("iP"), new Score(14, 20)));
        edgar.addAssessment(new Assessment(new AssessmentName("tP"), new Score(49, 50)));
        edgar.addAssessment(new Assessment(new AssessmentName("Finals"), new Score(20, 30)));

        Student irfan = new Student(new Name("Irfan Ibrahim"), new TelegramHandle("@irfan_ibrahim"),
                new Email("irfan@u.nus.edu"), new Note("Need more help with general oratorical skills"),
                new GroupName("CS2101"));
        irfan.addAssessment(new Assessment(new AssessmentName("OP 1"), new Score(55, 100)));
        irfan.addAssessment(new Assessment(new AssessmentName("OP 2"), new Score(45, 100)));

        Student roy = new Student(new Name("Roy Balakrishnan"), new TelegramHandle("@roy_balakrishnan"),
                new Email("royb@u.nus.edu"), new Note(""), new GroupName("CS2103T Consult Group 1"));
        roy.addAssessment(new Assessment(new AssessmentName("iP"), new Score(9, 20)));
        roy.addAssessment(new Assessment(new AssessmentName("tP"), new Score(39, 50)));
        roy.addAssessment(new Assessment(new AssessmentName("Finals"), new Score(25, 30)));

        return new Student[] {
            alex, bernice, charlotte, david, edgar, irfan, roy
        };
    }

    private static Group[] getSampleGroups() {
        Group cs2103T = new Group(new GroupName("CS2103T"), new Description("SWE Module"));
        cs2103T.addAllStudentNames(Arrays.asList(new Name("Alex Yeoh"),
                new Name("David Li"), new Name("Edgar Oliveiro")));

        Group cs2101 = new Group(new GroupName("CS2101"), new Description("Communications Module"));
        cs2101.addAllStudentNames(Arrays.asList(new Name("Bernice Yu"),
                new Name("Charlotte Oliveiro"), new Name("Irfan Ibrahim")));

        Group cs2103TConsultGroup = new Group(new GroupName("CS2103T Consult Group 1"),
                new Description("Consultion group 1 for CS2103T"));
        cs2103TConsultGroup.addStudentName(new Name("Roy Balakrishnan"));

        return new Group[]{cs2103T, cs2101, cs2103TConsultGroup};
    }

    public static ReadOnlyCsBook getSampleCsBook() {
        CsBook sampleAb = new CsBook();
        for (Student sampleStudent : getSampleStudents()) {
            sampleAb.addStudent(sampleStudent);
        }

        for (Group sampleGroup : getSampleGroups()) {
            sampleAb.addGroup(sampleGroup);
        }

        return sampleAb;
    }
}
