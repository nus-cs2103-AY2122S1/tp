package seedu.intern.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_COURSE;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_GRADUATIONYEARMONTH;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_INSTITUTION;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_JOB;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.intern.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.intern.commons.core.selection.Index;
import seedu.intern.commons.core.selection.Selection;
import seedu.intern.logic.commands.exceptions.CommandException;
import seedu.intern.model.InternWatcher;
import seedu.intern.model.Model;
import seedu.intern.model.applicant.Applicant;
import seedu.intern.model.applicant.NameContainsKeywordsPredicate;
import seedu.intern.testutil.EditApplicantDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_GRADE_AMY = "4.50";
    public static final String VALID_GRADE_BOB = "4.60";
    public static final String VALID_STATUS_AMY = "INTERVIEWED";
    public static final String VALID_STATUS_BOB = "REJECTED";
    public static final String VALID_INSTITUTION_AMY = "NUS";
    public static final String VALID_INSTITUTION_BOB = "NUSS";
    @SuppressWarnings("SpellCheckingInspection")
    public static final String VALID_GRADUATION_YEARMONTH_AMY = "06/2023";
    @SuppressWarnings("SpellCheckingInspection")
    public static final String VALID_GRADUATION_YEARMONTH_BOB = "09/2021";
    public static final String VALID_JOB_AMY = "Software Engineer";
    public static final String VALID_JOB_BOB = "Hardware Engineer";
    public static final String VALID_COURSE_AMY = "Computer Science";
    public static final String VALID_COURSE_BOB = "Computer Science";
    public static final String VALID_SKILL_JAVA = "JAVA";
    public static final String VALID_SKILL_PYTHON = "PYTHON";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String GRADE_DESC_AMY = " " + PREFIX_GRADE + VALID_GRADE_AMY;
    public static final String GRADE_DESC_BOB = " " + PREFIX_GRADE + VALID_GRADE_BOB;
    public static final String INSTITUTION_DESC_AMY = " " + PREFIX_INSTITUTION + VALID_INSTITUTION_AMY;
    public static final String INSTITUTION_DESC_BOB = " " + PREFIX_INSTITUTION + VALID_INSTITUTION_BOB;
    @SuppressWarnings("SpellCheckingInspection")
    public static final String GRADUATION_YEARMONTH_DESC_AMY = " "
            + PREFIX_GRADUATIONYEARMONTH + VALID_GRADUATION_YEARMONTH_AMY;
    @SuppressWarnings("SpellCheckingInspection")
    public static final String GRADUATION_YEARMONTH_DESC_BOB = " "
            + PREFIX_GRADUATIONYEARMONTH + VALID_GRADUATION_YEARMONTH_BOB;
    public static final String JOB_DESC_AMY = " " + PREFIX_JOB + VALID_JOB_AMY;
    public static final String JOB_DESC_BOB = " " + PREFIX_JOB + VALID_JOB_BOB;
    public static final String STATUS_DESC_AMY = " " + PREFIX_STATUS + VALID_STATUS_AMY;
    public static final String STATUS_DESC_BOB = " " + PREFIX_STATUS + VALID_STATUS_BOB;
    public static final String COURSE_DESC_AMY = " " + PREFIX_COURSE + VALID_COURSE_AMY;
    public static final String COURSE_DESC_BOB = " " + PREFIX_COURSE + VALID_COURSE_BOB;
    public static final String SKILL_DESC_PYTHON = " " + PREFIX_SKILL + VALID_SKILL_PYTHON;
    public static final String SKILL_DESC_JAVA = " " + PREFIX_SKILL + VALID_SKILL_JAVA;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_GRADE_DESC = " " + PREFIX_GRADE + "5.000"; //3dp not allowed for grades
    public static final String INVALID_SKILL_DESC = " " + PREFIX_SKILL + "java*"; // '*' not allowed in SKILLs
    public static final String INVALID_INSTITUTION_DESC = " " + PREFIX_INSTITUTION + "NU$"; // '$' not allowed
    public static final String INVALID_STATUS_DESC = " " + PREFIX_STATUS + "somestring"; // value must be in enum
    @SuppressWarnings("SpellCheckingInspection")
    public static final String INVALID_GRADUATION_YEARMONTH_DESC = " " + PREFIX_GRADUATIONYEARMONTH
            + "13/2021"; // invalid month not allowed
    public static final String INVALID_JOB_DESC = " " + PREFIX_JOB + "Software $ngineer"; // '$' not allowed
    public static final String INVALID_COURSE_DESC = " " + PREFIX_COURSE + "Computer $cience"; // '$' not allowed

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditApplicantDescriptor DESC_AMY;
    public static final EditCommand.EditApplicantDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditApplicantDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withApplicationStatus(VALID_STATUS_AMY)
                .withGrade(VALID_GRADE_AMY).withInstitution(VALID_INSTITUTION_AMY).withCourse(VALID_COURSE_AMY)
                .withGraduationYearMonth(VALID_GRADUATION_YEARMONTH_AMY).withSkills(VALID_SKILL_PYTHON)
                .withJob(VALID_JOB_AMY).build();
        DESC_BOB = new EditApplicantDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withApplicationStatus(VALID_STATUS_BOB)
                .withGrade(VALID_GRADE_BOB).withInstitution(VALID_INSTITUTION_BOB).withCourse(VALID_COURSE_BOB)
                .withGraduationYearMonth(VALID_GRADUATION_YEARMONTH_BOB).withJob(VALID_JOB_BOB)
                .withSkills(VALID_SKILL_JAVA, VALID_SKILL_PYTHON).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the intern book, filtered applicant list and selected applicant in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        InternWatcher expectedInternWatcher = new InternWatcher(actualModel.getInternWatcher());
        List<Applicant> expectedFilteredList = new ArrayList<>(actualModel.getFilteredApplicantList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedInternWatcher, actualModel.getInternWatcher());
        assertEquals(expectedFilteredList, actualModel.getFilteredApplicantList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the applicant at the given {@code targetIndex} in the
     * {@code model}'s intern book.
     */
    public static void showApplicantAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredApplicantList().size());

        Applicant applicant = model.getFilteredApplicantList().get(targetIndex.getZeroBased());
        final String[] splitName = applicant.getName().fullName.split("\\s+");
        model.updateFilteredApplicantList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredApplicantList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the applicant at the given {@code targetIndex} in the
     * {@code model}'s intern book.
     */
    public static void showSelectedApplicant(Model model, Selection targetSelection) {
        assertTrue(targetSelection.getIndexZeroBased() < model.getFilteredApplicantList().size());

        Applicant applicant = model.getFilteredApplicantList().get(targetSelection.getIndexZeroBased());
        final String[] splitName = applicant.getName().fullName.split("\\s+");
        model.updateFilteredApplicantList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredApplicantList().size());
    }

}
