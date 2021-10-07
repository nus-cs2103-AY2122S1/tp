package seedu.plannermd.testutil.patient;

import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_BIRTH_DATE_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_BIRTH_DATE_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_RISK_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_RISK_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.plannermd.model.patient.Patient;

/**
 * A utility class containing a list of {@code Patient} objects to be used in
 * tests.
 */
public class TypicalPatients {
    public static final Patient ALICE = new PatientBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withRemark("Awaiting PCR test result").withBirthDate("20/07/1964")
            .withTags("friends").withRisk("LOW").build();
    public static final Patient BENSON = new PatientBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25").withEmail("johnd@example.com").withPhone("98765432")
            .withRemark("Hypochondriac").withTags("owesMoney", "friends").withBirthDate("20/07/1964")
            .withRisk("LOW").build();
    public static final Patient CARL = new PatientBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street")
            .withRemark("Prefers liquid medication").withBirthDate("20/07/1965").withRisk("UNCLASSIFIED")
            .build();
    public static final Patient DANIEL = new PatientBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends")
            .withBirthDate("20/07/1964").withRisk("MEDIUM").build();
    public static final Patient ELLE = new PatientBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").withBirthDate("19/05/1944")
            .withRisk("LOW").build();
    public static final Patient FIONA = new PatientBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").withBirthDate("20/08/1948")
            .withRisk("UNCLASSIFIED").build();
    public static final Patient GEORGE = new PatientBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").withBirthDate("9/1/1964")
            .withRisk("HIGH").build();

    // Manually added
    public static final Patient HOON = new PatientBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").withBirthDate("22/06/1950")
            .withRisk("LOW").build();
    public static final Patient IDA = new PatientBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").withBirthDate("22/06/1950")
            .withRisk("LOW").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Patient AMY = new PatientBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withBirthDate(VALID_BIRTH_DATE_AMY)
            .withTags(VALID_TAG_FRIEND).withRisk(VALID_RISK_AMY).build();
    public static final Patient BOB = new PatientBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withBirthDate(VALID_BIRTH_DATE_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).withRisk(VALID_RISK_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPatients() {
    } // prevents instantiation

    public static List<Patient> getTypicalPatients() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

}
