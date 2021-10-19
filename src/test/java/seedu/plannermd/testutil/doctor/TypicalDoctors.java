package seedu.plannermd.testutil.doctor;

import static seedu.plannermd.testutil.TypicalPersons.ALICE;
import static seedu.plannermd.testutil.TypicalPersons.AMY;
import static seedu.plannermd.testutil.TypicalPersons.BENSON;
import static seedu.plannermd.testutil.TypicalPersons.BOB;
import static seedu.plannermd.testutil.TypicalPersons.CARL;
import static seedu.plannermd.testutil.TypicalPersons.DANIEL;
import static seedu.plannermd.testutil.TypicalPersons.ELLE;
import static seedu.plannermd.testutil.TypicalPersons.FIONA;
import static seedu.plannermd.testutil.TypicalPersons.GEORGE;
import static seedu.plannermd.testutil.TypicalPersons.HOON;
import static seedu.plannermd.testutil.TypicalPersons.IDA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.plannermd.model.doctor.Doctor;

/**
 * A utility class containing a list of {@code Patient} objects to be used in tests.
 */
public class TypicalDoctors {

    public static final Doctor DR_ALICE = new DoctorBuilder(ALICE).build();
    public static final Doctor DR_BENSON = new DoctorBuilder(BENSON).build();
    public static final Doctor DR_CARL = new DoctorBuilder(CARL).build();
    public static final Doctor DR_DANIEL = new DoctorBuilder(DANIEL).build();
    public static final Doctor DR_ELLE = new DoctorBuilder(ELLE).build();
    public static final Doctor DR_FIONA = new DoctorBuilder(FIONA).build();
    public static final Doctor DR_GEORGE = new DoctorBuilder(GEORGE).build();

    // Manually added
    public static final Doctor DR_HOON = new DoctorBuilder(HOON).build();
    public static final Doctor DR_IDA = new DoctorBuilder(IDA).build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Doctor DR_AMY = new DoctorBuilder(AMY).build();
    public static final Doctor DR_BOB = new DoctorBuilder(BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalDoctors() {} // prevents instantiation

    public static List<Doctor> getTypicalDoctors() {
        return new ArrayList<>(Arrays.asList(DR_ALICE, DR_BENSON, DR_CARL, DR_DANIEL, DR_ELLE, DR_FIONA, DR_GEORGE));
    }

}
