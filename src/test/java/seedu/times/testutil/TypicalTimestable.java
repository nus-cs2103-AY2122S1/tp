package seedu.times.testutil;

import static seedu.times.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.times.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.times.logic.commands.CommandTestUtil.VALID_ADDRESS_NOK;
import static seedu.times.logic.commands.CommandTestUtil.VALID_CLASSNAME_IB_MATHS;
import static seedu.times.logic.commands.CommandTestUtil.VALID_CLASSNAME_IB_PHYSICS;
import static seedu.times.logic.commands.CommandTestUtil.VALID_CLASSTIMING_IB_MATHS;
import static seedu.times.logic.commands.CommandTestUtil.VALID_CLASSTIMING_IB_PHYSICS;
import static seedu.times.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.times.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.times.logic.commands.CommandTestUtil.VALID_EMAIL_NOK;
import static seedu.times.logic.commands.CommandTestUtil.VALID_LOCATION_IB_MATHS;
import static seedu.times.logic.commands.CommandTestUtil.VALID_LOCATION_IB_PHYSICS;
import static seedu.times.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.times.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.times.logic.commands.CommandTestUtil.VALID_NAME_NOK;
import static seedu.times.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.times.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.times.logic.commands.CommandTestUtil.VALID_PHONE_NOK;
import static seedu.times.logic.commands.CommandTestUtil.VALID_RATE_IB_MATHS;
import static seedu.times.logic.commands.CommandTestUtil.VALID_RATE_IB_PHYSICS;
import static seedu.times.logic.commands.CommandTestUtil.VALID_STUDENTLIST_IB_MATHS;
import static seedu.times.logic.commands.CommandTestUtil.VALID_STUDENTLIST_IB_PHYSICS;
import static seedu.times.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.times.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.times.model.TimesTable;
import seedu.times.model.person.Student;
import seedu.times.model.tuitionclass.TuitionClass;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalTimestable {

    ///Typical Students
    public static final Student ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withNokName("Long Chai Boon")
            .withNokAddress("325, Clementi State 3, #40-32")
            .withNokEmail("longchatbooon@gmail.com").withNokPhone("67785914")
            .withTags("Maths").build();
    public static final Student BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withNokName("Short Chai Boon")
            .withNokAddress("325, West State 3, #60-12")
            .withNokEmail("hahiihi@gmail.com").withNokPhone("87759868")
            .withTags("Physics").build();
    public static final Student CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("Campbell Road Ave 2, #11")
            .withNokName("Jamie Kurz")
            .withNokAddress("325, Bat Cave 3, #10-23")
            .withNokEmail("dontcallmeillcome@gmail.com").withNokPhone("62212222")
            .build();
    public static final Student DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street")
            .withNokName("Bad Chai Boon")
            .withNokAddress("1, Happy Sad 3, #10-12")
            .withNokEmail("happyorsadyouchoose@gmail.com").withNokPhone("88888888")
            .withTags("Maths").build();
    public static final Student ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave")
            .withNokName("Bernard Wan")
            .withNokAddress("222, Berkeys State 4, #11-32")
            .withNokEmail("bernard@gmail.com").withNokPhone("67785314").build();
    public static final Student FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo")
            .withNokName("Ong Lin Zhen")
            .withNokAddress("323, Lin Estate 3, #44-31")
            .withNokEmail("zhenngggii@gmail.com").withNokPhone("67382344")
            .build();
    public static final Student GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street")
            .withNokName("Hehe Chai Red")
            .withNokAddress("999, Estate State 4, #10-32")
            .withNokEmail("salmon@gmail.com").withNokPhone("67111111")
            .build();

    // Manually added
    public static final Student HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india")
            .build();
    public static final Student IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Student AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND)
            .withNokName(VALID_NAME_NOK).withNokPhone(VALID_PHONE_NOK).withNokAddress(VALID_ADDRESS_NOK)
            .withNokEmail(VALID_EMAIL_NOK).build();
    public static final Student BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withNokName(VALID_NAME_NOK).withNokPhone(VALID_PHONE_NOK).withNokAddress(VALID_ADDRESS_NOK)
            .withNokEmail(VALID_EMAIL_NOK)
            .build();


    // Typical Classes
    public static final String JC_PHYSICS_DAY = "Mon";
    public static final String JC_PHYSICS_TIME = "10:00-12:00";
    public static final String JC_PHYSICS_CLASS_TIMING = JC_PHYSICS_DAY + " " + JC_PHYSICS_TIME;
    public static final String SEC_PHYSICS_DAY = "Tue";
    public static final String SEC_PHYSICS_TIME = "11:00-13:00";
    public static final String SEC_PHYSICS_CLASS_TIMING = SEC_PHYSICS_DAY + " " + SEC_PHYSICS_TIME;
    public static final String JC_MATHS_DAY = "Wed";
    public static final String JC_MATHS_TIME = "15:00-17:00";
    public static final String JC_MATHS_CLASS_TIMING = JC_MATHS_DAY + " " + JC_MATHS_TIME;
    public static final String SEC_MATHS_DAY = "Thu";
    public static final String SEC_MATHS_TIME = "10:00-12:00";
    public static final String SEC_MATHS_CLASS_TIMING = SEC_MATHS_DAY + " " + SEC_MATHS_TIME;
    public static final String JC_CHEMISTRY_DAY = "Fri";
    public static final String JC_CHEMISTRY_TIME = "13:00-16:00";
    public static final String JC_CHEMISTRY_CLASS_TIMING = JC_CHEMISTRY_DAY + " " + JC_CHEMISTRY_TIME;
    public static final String SEC_CHEMISTRY_DAY = "Fri";
    public static final String SEC_CHEMISTRY_TIME = "17:00-18:00";
    public static final String SEC_CHEMISTRY_CLASS_TIMING = SEC_CHEMISTRY_DAY + " " + SEC_CHEMISTRY_TIME;

    public static final TuitionClass JC_PHYSICS = new TuitionClassBuilder().withClassName("JC Physics")
            .withClassTiming(JC_PHYSICS_CLASS_TIMING).withRate("70").withLocation("Jaycee Tuition Center Nex")
            .withStudentList(VALID_STUDENTLIST_IB_PHYSICS).build();
    public static final TuitionClass SEC_PHYSICS = new TuitionClassBuilder().withClassName("Sec 4 Physics")
            .withClassTiming(SEC_PHYSICS_CLASS_TIMING).withRate("77").withLocation("Learning Lab Orchard")
            .withStudentList(VALID_STUDENTLIST_IB_MATHS).build();
    public static final TuitionClass JC_MATHS = new TuitionClassBuilder().withClassName("JC Maths")
            .withClassTiming(JC_MATHS_CLASS_TIMING).withRate("55")
            .withLocation("Bukit Merah Block 614 #01-330").build();
    public static final TuitionClass SEC_MATHS = new TuitionClassBuilder().withClassName("Sec 4 Maths")
            .withClassTiming(SEC_MATHS_CLASS_TIMING).withRate("60")
            .withLocation("Merlion Tuition Center Kovan").build();
    public static final TuitionClass JC_CHEMISTRY = new TuitionClassBuilder().withClassName("JC Chemistry")
            .withClassTiming(JC_CHEMISTRY_CLASS_TIMING).withRate("50").withLocation("Hougang Blk 313 #11-394").build();
    public static final TuitionClass SEC_CHEMISTRY = new TuitionClassBuilder().withClassName("Sec 4 Chemistry")
            .withClassTiming(SEC_CHEMISTRY_CLASS_TIMING).withRate("80").withLocation("Kumon at Orchard").build();

    // Classes found in {@code CommandTestUtil}
    public static final TuitionClass IB_PHYSICS = new TuitionClassBuilder().withClassName(VALID_CLASSNAME_IB_PHYSICS)
            .withClassTiming(VALID_CLASSTIMING_IB_PHYSICS).withRate(VALID_RATE_IB_PHYSICS)
            .withLocation(VALID_LOCATION_IB_PHYSICS).withStudentList(VALID_STUDENTLIST_IB_PHYSICS).build();
    public static final TuitionClass IB_MATHS = new TuitionClassBuilder().withClassName(VALID_CLASSNAME_IB_MATHS)
            .withClassTiming(VALID_CLASSTIMING_IB_MATHS).withRate(VALID_RATE_IB_MATHS)
            .withLocation(VALID_LOCATION_IB_MATHS).withStudentList(VALID_STUDENTLIST_IB_MATHS).build();


    private TypicalTimestable() {
    } // prevents instantiation

    /**
     * Returns a {@code TimesTable} with all the typical students and classes.
     * Contains 7 students and 6 classes.
     */
    public static TimesTable getTypicalTimesTable() {
        TimesTable ab = new TimesTable();

        for (Student student : getTypicalStudents()) {
            ab.addStudent(student);
        }

        for (TuitionClass tuitionClass : getTypicalClasses()) {
            ab.addTuitionClass(tuitionClass);
        }

        return ab;
    }

    /**
     * Returns an {@code TimesTable} with all the typical students in reversed order, and classes.
     * Contains 7 students and 6 classes.
     */
    public static TimesTable getTypicalTimesTableReverseStudents() {
        TimesTable ab = new TimesTable();

        List<Student> studentList = getTypicalStudents();

        for (int i = 0; i < studentList.size(); i++) {
            ab.addStudent(studentList.get(studentList.size() - 1 - i));
        }

        for (TuitionClass tuitionClass : getTypicalClasses()) {
            ab.addTuitionClass(tuitionClass);
        }

        return ab;
    }

    /**
     * Returns a {@code TimesTable} with all the typical students, and classes in reversed order.
     * Contains 7 students and 6 classes.
     */
    public static TimesTable getTypicalTimesTableReverseClasses() {
        TimesTable ab = new TimesTable();

        for (Student student : getTypicalStudents()) {
            ab.addStudent(student);
        }

        List<TuitionClass> tuitionClassList = getTypicalClasses();

        for (int i = 0; i < tuitionClassList.size(); i++) {
            ab.addTuitionClass(tuitionClassList.get(tuitionClassList.size() - 1 - i));
        }

        return ab;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<TuitionClass> getTypicalClasses() {
        return new ArrayList<>(Arrays.asList(JC_PHYSICS, SEC_PHYSICS, JC_MATHS, SEC_MATHS,
                JC_CHEMISTRY, SEC_CHEMISTRY));
    }
}
