package seedu.intern.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.intern.commons.core.Messages.MESSAGE_APPLICANTS_LISTED_OVERVIEW;
import static seedu.intern.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.intern.testutil.TypicalApplicants.ALICE;
import static seedu.intern.testutil.TypicalApplicants.BENSON;
import static seedu.intern.testutil.TypicalApplicants.DANIEL;
import static seedu.intern.testutil.TypicalApplicants.ELLE;
import static seedu.intern.testutil.TypicalApplicants.FIONA;
import static seedu.intern.testutil.TypicalApplicants.GEORGE;
import static seedu.intern.testutil.TypicalApplicants.getTypicalInternWatcher;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.intern.logic.commands.FilterCommand.FilterApplicantDescriptor;
import seedu.intern.model.Model;
import seedu.intern.model.ModelManager;
import seedu.intern.model.UserPrefs;
import seedu.intern.model.applicant.CombineFiltersPredicate;
import seedu.intern.testutil.FilterApplicantDescriptorBuilder;




public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalInternWatcher(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalInternWatcher(), new UserPrefs());

    @Test
    public void execute_gradeOnly_multipleApplicantsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 3);
        FilterApplicantDescriptor filterCondition = new FilterApplicantDescriptorBuilder()
                .withGrade("4.80").build();
        CombineFiltersPredicate predicate = new CombineFiltersPredicate(filterCondition);
        FilterCommand command = new FilterCommand(filterCondition);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL, ELLE, FIONA), model.getFilteredApplicantList());
    }

    @Test
    public void execute_skillOnly_multipleApplicantsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 3);
        FilterApplicantDescriptor filterCondition = new FilterApplicantDescriptorBuilder()
                .withSkills("python").build();
        CombineFiltersPredicate predicate = new CombineFiltersPredicate(filterCondition);
        FilterCommand command = new FilterCommand(filterCondition);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredApplicantList());
    }

    @Test
    public void execute_gradYearMonthOnly_multipleApplicantsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 2);
        FilterApplicantDescriptor filterCondition = new FilterApplicantDescriptorBuilder()
                .withGraduationYearMonth("06/2022").build();
        CombineFiltersPredicate predicate = new CombineFiltersPredicate(filterCondition);
        FilterCommand command = new FilterCommand(filterCondition);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, FIONA), model.getFilteredApplicantList());
    }

    @Test
    public void execute_courseOnly_multipleApplicantsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 2);
        FilterApplicantDescriptor filterCondition = new FilterApplicantDescriptorBuilder()
                .withCourses("Computer").build();
        CombineFiltersPredicate predicate = new CombineFiltersPredicate(filterCondition);
        FilterCommand command = new FilterCommand(filterCondition);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredApplicantList());
    }

    @Test
    public void execute_statusOnly_multipleApplicantsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 2);
        FilterApplicantDescriptor filterCondition = new FilterApplicantDescriptorBuilder()
                .withApplicationStatus("REJECTED").build();
        CombineFiltersPredicate predicate = new CombineFiltersPredicate(filterCondition);
        FilterCommand command = new FilterCommand(filterCondition);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, GEORGE), model.getFilteredApplicantList());
    }

    @Test
    public void execute_jobOnly_multipleApplicantsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 2);
        FilterApplicantDescriptor filterCondition = new FilterApplicantDescriptorBuilder()
                .withJobs("software engineer").build();
        CombineFiltersPredicate predicate = new CombineFiltersPredicate(filterCondition);
        FilterCommand command = new FilterCommand(filterCondition);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, GEORGE), model.getFilteredApplicantList());
    }


    @Test
    public void execute_jobAndCourse_multipleApplicantsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 2);
        FilterApplicantDescriptor filterCondition = new FilterApplicantDescriptorBuilder()
                .withJobs("software engineer").withCourses("Social Work", "Computer Science").build();
        CombineFiltersPredicate predicate = new CombineFiltersPredicate(filterCondition);
        FilterCommand command = new FilterCommand(filterCondition);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, GEORGE), model.getFilteredApplicantList());
    }

    @Test
    public void execute_jobAndCourse_oneApplicantsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 1);
        FilterApplicantDescriptor filterCondition = new FilterApplicantDescriptorBuilder()
                .withJobs("software engineer").withCourses("Computer Science").build();
        CombineFiltersPredicate predicate = new CombineFiltersPredicate(filterCondition);
        FilterCommand command = new FilterCommand(filterCondition);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredApplicantList());
    }

    @Test
    public void execute_jobAndCourse_noApplicantsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 0);
        FilterApplicantDescriptor filterCondition = new FilterApplicantDescriptorBuilder()
                .withJobs("software engineer").withCourses("accountant").build();
        CombineFiltersPredicate predicate = new CombineFiltersPredicate(filterCondition);
        FilterCommand command = new FilterCommand(filterCondition);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(), model.getFilteredApplicantList());
    }

    @Test
    public void execute_allFields_oneApplicantFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 1);
        FilterApplicantDescriptor filterCondition = new FilterApplicantDescriptorBuilder()
                .withGrade("4.80")
                .withInstitution("SUTD")
                .withApplicationStatus("ACCEPTED")
                .withCourses("PHILOSOPHY")
                .withGraduationYearMonth("06/2025")
                .withJobs("Helper")
                .build();
        CombineFiltersPredicate predicate = new CombineFiltersPredicate(filterCondition);
        FilterCommand command = new FilterCommand(filterCondition);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE), model.getFilteredApplicantList());
    }

    @Test
    public void execute_allFields_noApplicantFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 0);
        FilterApplicantDescriptor filterCondition = new FilterApplicantDescriptorBuilder()
                .withGrade("5.00")
                .withInstitution("SUTD")
                .withApplicationStatus("ACCEPTED")
                .withCourses("PHILOSOPHY")
                .withGraduationYearMonth("06/2025")
                .withJobs("Helper")
                .build();
        CombineFiltersPredicate predicate = new CombineFiltersPredicate(filterCondition);
        FilterCommand command = new FilterCommand(filterCondition);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(), model.getFilteredApplicantList());
    }
}
