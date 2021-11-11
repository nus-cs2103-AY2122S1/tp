package seedu.intern.testutil;

import static seedu.intern.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_GRADE_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_GRADE_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_GRADUATION_YEARMONTH_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_GRADUATION_YEARMONTH_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_INSTITUTION_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_INSTITUTION_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_JOB_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_JOB_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_SKILL_JAVA;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_SKILL_PYTHON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.intern.model.InternWatcher;
import seedu.intern.model.applicant.Applicant;

/**
 * A utility class containing a list of {@code Applicant} objects to be used in tests.
 */
public class TypicalApplicants {

    public static final Applicant ALICE = new ApplicantBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com").withPhone("94351253").withGrade("4.50").withJob("Software Engineer")
            .withInstitution("NUS").withCourse("Computer Science").withApplicationStatus("REJECTED")
            .withGraduationYearMonth("12/2020").withSkills("python").build();
    public static final Applicant BENSON = new ApplicantBuilder().withName("Benson Meier")
            .withGrade("4.60").withInstitution("NTU").withCourse("Computer Engineering").withEmail("johnd@example.com")
            .withPhone("98765432").withGraduationYearMonth("06/2025").withSkills("java", "python")
            .withApplicationStatus("INTERVIEWED").withJob("Hardware Engineer").build();
    public static final Applicant CARL = new ApplicantBuilder().withName("Carl Kurz").withPhone("95352563")
            .withGrade("4.70").withInstitution("NTU").withCourse("Business and Accounting")
            .withApplicationStatus("ACCEPTED").withEmail("heinz@example.com").withGraduationYearMonth("12/2023")
            .withSkills("java").withJob("Accountant").build();
    public static final Applicant DANIEL = new ApplicantBuilder().withName("Daniel Meier").withPhone("87652533")
            .withGrade("4.80").withInstitution("SMU").withEmail("cornelia@example.com")
            .withSkills("python").withCourse("Accountancy").withGraduationYearMonth("01/2027")
            .withApplicationStatus("INTERVIEWED").withJob("Finance Manager").build();
    public static final Applicant ELLE = new ApplicantBuilder().withName("Elle Meyer").withPhone("9482224")
            .withGrade("4.90").withInstitution("SUTD").withEmail("werner@example.com").withSkills("C").withJob("Helper")
            .withGraduationYearMonth("12/2024").withApplicationStatus("ACCEPTED").withCourse("Philosophy").build();
    public static final Applicant FIONA = new ApplicantBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withGrade("5.00").withInstitution("PATHLIGHT").withEmail("lydia@example.com").withSkills("C")
            .withGraduationYearMonth("12/2021").withCourse("Life Science")
            .withApplicationStatus("INTERVIEWED").withJob("Researcher").build();
    public static final Applicant GEORGE = new ApplicantBuilder().withName("George Best").withPhone("9482442")
            .withGrade("4.40").withInstitution("SUSS").withApplicationStatus("ACCEPTED").withEmail("anna@example.com")
            .withGraduationYearMonth("03/2023").withCourse("Social Work").withSkills("C")
            .withApplicationStatus("REJECTED").withJob("Software Engineer").build();

    // Manually added
    public static final Applicant HOON = new ApplicantBuilder().withName("Hoon Meier").withPhone("8482424")
            .withGrade("4.20").withInstitution("SUTD").withEmail("stefan@example.com").withCourse("Computer Science")
            .withGraduationYearMonth("12/2020").withJob("Software Engineer").build();
    public static final Applicant IDA = new ApplicantBuilder().withName("Ida Mueller").withPhone("8482131")
            .withGrade("4.30").withInstitution("NUS").withEmail("hans@example.com").withCourse("Computer Science")
            .withGraduationYearMonth("12/2020").withJob("Software Engineer").build();


    // Manually added - Applicant's details found in {@code CommandTestUtil}
    public static final Applicant AMY = new ApplicantBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withSkills(VALID_SKILL_JAVA)
            .withGrade(VALID_GRADE_AMY).withInstitution(VALID_INSTITUTION_AMY).withJob(VALID_JOB_AMY)
            .withGraduationYearMonth(VALID_GRADUATION_YEARMONTH_AMY).build();
    public static final Applicant BOB = new ApplicantBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withSkills(VALID_SKILL_PYTHON, VALID_SKILL_JAVA)
            .withGrade(VALID_GRADE_BOB).withInstitution(VALID_INSTITUTION_BOB).withJob(VALID_JOB_BOB)
            .withGraduationYearMonth(VALID_GRADUATION_YEARMONTH_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalApplicants() {} // prevents instantiation

    /**
     * Returns an {@code InternWatcher} with all the typical applicants.
     */
    public static InternWatcher getTypicalInternWatcher() {
        InternWatcher ab = new InternWatcher();
        for (Applicant applicant : getTypicalApplicants()) {
            ab.addApplicant(applicant);
        }
        return ab;
    }

    public static List<Applicant> getTypicalApplicants() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
