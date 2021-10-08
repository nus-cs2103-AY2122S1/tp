package seedu.plannermd.testutil.patient;

import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_RISK_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_RISK_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.plannermd.model.patient.Patient;
import seedu.plannermd.testutil.TypicalPersons;

/**
 * A utility class containing a list of {@code Patient} objects to be used in tests.
 */
public class TypicalPatients {
    public static final Patient ALICE = new PatientBuilder(TypicalPersons.ALICE)
            .withRisk("LOW").build();
    public static final Patient BENSON = new PatientBuilder(TypicalPersons.BENSON)
            .withRisk("LOW").build();
    public static final Patient CARL = new PatientBuilder(TypicalPersons.CARL)
            .withRisk("UNCLASSIFIED").build();
    public static final Patient DANIEL = new PatientBuilder(TypicalPersons.DANIEL)
            .withRisk("MEDIUM").build();
    public static final Patient ELLE = new PatientBuilder(TypicalPersons.ELLE)
            .withRisk("LOW").build();
    public static final Patient FIONA = new PatientBuilder(TypicalPersons.FIONA)
            .withRisk("UNCLASSIFIED").build();
    public static final Patient GEORGE = new PatientBuilder(TypicalPersons.GEORGE)
            .withRisk("HIGH").build();

    // Manually added
    public static final Patient HOON = new PatientBuilder(TypicalPersons.HOON)
            .withRisk("LOW").build();
    public static final Patient IDA = new PatientBuilder(TypicalPersons.IDA)
            .withRisk("LOW").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Patient AMY = new PatientBuilder(TypicalPersons.AMY)
            .withRisk(VALID_RISK_AMY).build();
    public static final Patient BOB = new PatientBuilder(TypicalPersons.BOB)
            .withRisk(VALID_RISK_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPatients() {} // prevents instantiation

    public static List<Patient> getTypicalPatients() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

}
