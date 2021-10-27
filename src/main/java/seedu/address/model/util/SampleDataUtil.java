package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.student.Assessment;
import seedu.address.model.student.Group;
import seedu.address.model.student.ID;
import seedu.address.model.student.Name;
import seedu.address.model.student.Score;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    private static final Random random = new Random();

    /**
     * For sample data, the maximum group number and group suffix is kept sufficiently small so that each group can
     * have a reasonable number of students in it.
     */
    private static final int MAX_GROUP_NUMBER = 3;
    private static final char MAX_GROUP_SUFFIX = 'C';

    private static final String[] SAMPLE_STUDENT_NAMES = {
        "How Kai Xin", "Wu Wei Le", "Ruchi Rattan", "Eva Porter", "Lai Guo Qiang Jack", "Tang Jia Xin",
        "Damini Kashyap", "Zhuang Yi Xi", "Winnie Ong Jia Ling", "Irfan Syahid Bin Mohammad Aydin", "Chen Kok Meng",
        "Sameera Raval", "Freya Murray", "Chang Yong Quan", "Haleigh Green", "Qian Jia Wen", "Ho Yi Xin",
        "Nur Sharifah Binte Azmi Noh", "Betania Suartini", "Zhuo Jia Wen", "Sim Ming Hao Jeremy", "Ng Sing Yu",
        "Rosey Shah", "Angus Johnson", "Lye Kai Feng", "Joel Jesus", "Nhung Phan", "Roberto Ferraro",
        "Qian Kai Wen", "Lew Si Hui", "Mohammad Syaril Bin Mohammad Irfan", "Loh Xin Yi", "Choo Yi Ling",
        "Juli Mandasari", "Jonathan Hong Yong Quan", "Teoh Yi Ling", "Leung Zheng Min", "Toh Hui Wen",
        "David Balasubramanian", "Tin Zhi Xin", "Asher Edwards", "Baey Yi De", "Low Hui Qi", "Chang Hao Ming",
        "Jackson Adams", "Edward Davidson", "Sim Hui Qi", "Kanika Kothari", "Tang Xin En", "Tin Zhi En"
    };

    private static final String[] SAMPLE_ASSESSMENT_NAMES = {
        "Reading Assessment 1", "Reading Assessment 2", "Midterm Examination", "Practical Assessment",
        "Final Examination"
    };

    private static final String[] SAMPLE_TAG_NAMES = {
        "beginner", "intermediate", "advanced"
    };

    /**
     * Stores the IDs that have been randomly generated and created, to avoid generating duplicate IDs.
     */
    private static final Set<String> TAKEN_IDS = new HashSet<>();

    public static List<Student> getSamplePersons() {
        List<Student> sampleStudents = new ArrayList<>();

        for (String studentName : SAMPLE_STUDENT_NAMES) {
            Student student = new Student(new Name(studentName), getRandomStudentId(),
                    getGroupList(getRandomTutorialGroup(), getRandomRecitationGroup()),
                    getRandomAssessmentScores(), getTagSet(getRandomTag()));
            sampleStudents.add(student);
        }

        return sampleStudents;
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Student sampleStudent : getSamplePersons()) {
            sampleAb.addStudent(sampleStudent);
        }
        return sampleAb;
    }

    /**
     * Generates a random {@code ID}. A random six digit number is generated and a '0' is prepended to it.
     * Ensures that only unique IDs will be generated each time.
     */
    public static ID getRandomStudentId() {
        int randomSixDigitNumber = random.nextInt((int) Math.pow(10, 6));
        String studentId = "E" + String.format("%07d", randomSixDigitNumber);

        if (TAKEN_IDS.contains(studentId)) {
            // If the generated student ID has already been taken, generate a new student ID.
            return getRandomStudentId();
        }

        TAKEN_IDS.add(studentId);
        return new ID(studentId);
    }

    /**
     * Generates a random name of a tutorial group, which begins with 'T'.
     */
    public static String getRandomTutorialGroup() {
        return "T" + getRandomGroupNumber() + getRandomGroupSuffix();
    }

    /**
     * Generates a random name of a recitation group, which begins with 'R'.
     */
    public static String getRandomRecitationGroup() {
        return "R" + getRandomGroupNumber() + getRandomGroupSuffix();
    }

    /**
     * Generates a random group number.
     */
    public static String getRandomGroupNumber() {
        int randomGroupNumber = random.nextInt(MAX_GROUP_NUMBER) + 1;
        return String.format("%02d", randomGroupNumber);
    }

    /**
     * Generates a random group suffix.
     */
    public static char getRandomGroupSuffix() {
        return (char) ('A' + random.nextInt(MAX_GROUP_SUFFIX - 'A'));
    }

    /**
     * Generates a score map for the sample assessments.
     */
    public static Map<Assessment, Score> getRandomAssessmentScores() {
        Map<Assessment, Score> scores = new LinkedHashMap<>();

        for (String assessmentName : SAMPLE_ASSESSMENT_NAMES) {
            String s = String.format("%.2f", getRandomScore());
            scores.put(new Assessment(assessmentName), new Score(s));
        }

        return scores;
    }

    /**
     * Generates a random score, following a normal distribution with mean = 60 and standard deviation = 15.
     */
    public static double getRandomScore() {
        double randomScore = 60 + random.nextGaussian() * 15;
        randomScore = Math.min(randomScore, Score.MAX_SCORE);
        randomScore = Math.max(randomScore, Score.MIN_SCORE);
        return randomScore;
    }

    /**
     * Gets a random tag from the sample tags.
     */
    public static String getRandomTag() {
        return SAMPLE_TAG_NAMES[random.nextInt(SAMPLE_TAG_NAMES.length)];
    }

    /**
     * Returns a group list containing the list of strings given.
     */
    public static List<Group> getGroupList(String... strings) {
        return Arrays.stream(strings)
                .map(Group::new)
                .collect(Collectors.toList());
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
