package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.done.DoneContainsKeywordsPredicate;
import seedu.address.model.notes.NotesContainsKeywordsPredicate;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.EmploymentTypeContainsKeywordsPredicate;
import seedu.address.model.person.ExpectedSalaryWithinRangePredicate;
import seedu.address.model.person.ExperienceContainsKeywordsPredicate;
import seedu.address.model.person.LevelOfEducationContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.RoleContainsKeywordsPredicate;
import seedu.address.model.tag.TagContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        ArrayList<Predicate<Person>> firstPredicateList = new ArrayList<>();
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        firstPredicateList.add(firstPredicate);

        ArrayList<Predicate<Person>> secondPredicateList = new ArrayList<>();
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));
        secondPredicateList.add(secondPredicate);

        FindCommand findFirstCommand = new FindCommand(firstPredicateList);
        FindCommand findSecondCommand = new FindCommand(secondPredicateList);

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicateList);
        assertEquals(findFirstCommandCopy, findFirstCommand);

        // different types -> returns false
        assertNotEquals(findFirstCommand, 1);

        // null -> returns false
        assertNotEquals(findFirstCommand, null);

        // different person -> returns false
        assertNotEquals(findSecondCommand, findFirstCommand);
    }

    @Test
    public void execute_zeroNameKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        NameContainsKeywordsPredicate predicate = prepareNamePredicate(" ");
        predicates.add(predicate);
        FindCommand command = new FindCommand(predicates);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleNameKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        NameContainsKeywordsPredicate predicate = prepareNamePredicate("Kurz Elle Kunz");
        predicates.add(predicate);
        FindCommand command = new FindCommand(predicates);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_zeroEmailKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        EmailContainsKeywordsPredicate predicate = prepareEmailPredicate(" ");
        predicates.add(predicate);
        FindCommand command = new FindCommand(predicates);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleEmailKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        EmailContainsKeywordsPredicate predicate =
                prepareEmailPredicate("heinz@example.com alice@example.com cornelia@example.com");
        predicates.add(predicate);
        FindCommand command = new FindCommand(predicates);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, CARL, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_zeroPhoneKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        PhoneContainsKeywordsPredicate predicate = preparePhonePredicate(" ");
        predicates.add(predicate);
        FindCommand command = new FindCommand(predicates);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multiplePhoneKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        PhoneContainsKeywordsPredicate predicate =
                preparePhonePredicate("98765432 9482427 9482442");
        predicates.add(predicate);
        FindCommand command = new FindCommand(predicates);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, FIONA, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneRoleKeyword_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        RoleContainsKeywordsPredicate predicate =
                prepareRolePredicate("Cashier");
        predicates.add(predicate);
        FindCommand command = new FindCommand(predicates);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneRoleKeyword_multiplePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        // Model with Hoon and Ida manually added
        Model modelWithHoonIda = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        modelWithHoonIda.addPerson(HOON);
        modelWithHoonIda.addPerson(IDA);

        // Expected Model with Hoon and Ida manually added
        Model expectedModelWithHoonIda = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModelWithHoonIda.addPerson(HOON);
        expectedModelWithHoonIda.addPerson(IDA);

        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        RoleContainsKeywordsPredicate predicate = prepareRolePredicate("Software");
        predicates.add(predicate);
        FindCommand command = new FindCommand(predicates);
        expectedModelWithHoonIda.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, modelWithHoonIda, expectedMessage, expectedModelWithHoonIda);
        assertEquals(Arrays.asList(HOON, IDA), modelWithHoonIda.getFilteredPersonList());
    }

    @Test
    public void execute_zeroEmploymentTypeKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        EmploymentTypeContainsKeywordsPredicate predicate = prepareEmploymentTypePredicate(" ");

        predicates.add(predicate);
        FindCommand command = new FindCommand(predicates);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneEmploymentTypeKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        // Model with Hoon and Ida manually added
        Model modelWithHoonIda = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        modelWithHoonIda.addPerson(HOON);
        modelWithHoonIda.addPerson(IDA);

        // Expected Model with Hoon and Ida manually added
        Model expectedModelWithHoonIda = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModelWithHoonIda.addPerson(HOON);
        expectedModelWithHoonIda.addPerson(IDA);

        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        EmploymentTypeContainsKeywordsPredicate predicate =
                prepareEmploymentTypePredicate("Full time");
        predicates.add(predicate);
        FindCommand command = new FindCommand(predicates);
        expectedModelWithHoonIda.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, modelWithHoonIda, expectedMessage, expectedModelWithHoonIda);
        assertEquals(Arrays.asList(ALICE, ELLE, IDA), modelWithHoonIda.getFilteredPersonList());
    }

    @Test
    public void execute_multipleEmploymentTypeKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        EmploymentTypeContainsKeywordsPredicate predicate =
                prepareEmploymentTypePredicate("Temporary internship");
        predicates.add(predicate);
        FindCommand command = new FindCommand(predicates);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, DANIEL, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_zeroExpectedSalaryKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        ExpectedSalaryWithinRangePredicate predicate = prepareExpectedSalaryPredicate(" ");
        predicates.add(predicate);
        FindCommand command = new FindCommand(predicates);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneExpectedSalaryKeywords_multiplePersonDifferentSalariesFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        ExpectedSalaryWithinRangePredicate predicate =
                prepareExpectedSalaryPredicate("2500");
        predicates.add(predicate);
        FindCommand command = new FindCommand(predicates);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleExpectedSalaryKeyword_multiplePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        ExpectedSalaryWithinRangePredicate predicate =
                prepareExpectedSalaryPredicate("5000 3500 500");
        predicates.add(predicate);
        FindCommand command = new FindCommand(predicates);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, DANIEL, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_zeroLevelOfEducationKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        LevelOfEducationContainsKeywordsPredicate predicate = prepareLevelOfEducationPredicate(" ");
        predicates.add(predicate);
        FindCommand command = new FindCommand(predicates);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneLevelOfEducationKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        // Model with Hoon and Ida manually added
        Model modelWithHoonIda = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        modelWithHoonIda.addPerson(HOON);
        modelWithHoonIda.addPerson(IDA);

        // Expected Model with Hoon and Ida manually added
        Model expectedModelWithHoonIda = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModelWithHoonIda.addPerson(HOON);
        expectedModelWithHoonIda.addPerson(IDA);

        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        LevelOfEducationContainsKeywordsPredicate predicate =
                prepareLevelOfEducationPredicate("Masters");
        predicates.add(predicate);
        FindCommand command = new FindCommand(predicates);
        expectedModelWithHoonIda.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, modelWithHoonIda, expectedMessage, expectedModelWithHoonIda);
        assertEquals(Arrays.asList(BENSON, IDA), modelWithHoonIda.getFilteredPersonList());
    }

    @Test
    public void execute_multipleLevelOfEducationKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        LevelOfEducationContainsKeywordsPredicate predicate =
                prepareLevelOfEducationPredicate("Masters University");
        predicates.add(predicate);
        FindCommand command = new FindCommand(predicates);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, GEORGE), model.getFilteredPersonList());
    }


    @Test
    public void execute_zeroExperience_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        ExperienceContainsKeywordsPredicate predicate = prepareExperiencePredicate(" ");
        predicates.add(predicate);
        FindCommand command = new FindCommand(predicates);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneExperience_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        // Model with Hoon and Ida manually added
        Model modelWithHoonIda = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        modelWithHoonIda.addPerson(HOON);
        modelWithHoonIda.addPerson(IDA);

        // Expected Model with Hoon and Ida manually added
        Model expectedModelWithHoonIda = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModelWithHoonIda.addPerson(HOON);
        expectedModelWithHoonIda.addPerson(IDA);

        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        ExperienceContainsKeywordsPredicate predicate =
                prepareExperiencePredicate("7");
        predicates.add(predicate);
        FindCommand command = new FindCommand(predicates);
        expectedModelWithHoonIda.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, modelWithHoonIda, expectedMessage, expectedModelWithHoonIda);
        assertEquals(Arrays.asList(HOON, IDA), modelWithHoonIda.getFilteredPersonList());
    }

    @Test
    public void execute_multipleExperience_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        // Model with Hoon and Ida
        Model modelWithHoonIda = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        modelWithHoonIda.addPerson(HOON);
        modelWithHoonIda.addPerson(IDA);

        // Expected Model with Hoon and Ida manually added
        Model expectedModelWithHoonIda = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModelWithHoonIda.addPerson(HOON);
        expectedModelWithHoonIda.addPerson(IDA);

        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        ExperienceContainsKeywordsPredicate predicate =
                prepareExperiencePredicate("7 6");
        predicates.add(predicate);
        FindCommand command = new FindCommand(predicates);
        expectedModelWithHoonIda.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, modelWithHoonIda, expectedMessage, expectedModelWithHoonIda);
        assertEquals(Arrays.asList(GEORGE, HOON, IDA), modelWithHoonIda.getFilteredPersonList());
    }


    @Test
    public void execute_zeroTagKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        TagContainsKeywordsPredicate predicate = prepareTagPredicate(" ");
        predicates.add(predicate);
        FindCommand command = new FindCommand(predicates);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleTagKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 4);
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        TagContainsKeywordsPredicate predicate = prepareTagPredicate("friends old");
        predicates.add(predicate);
        FindCommand command = new FindCommand(predicates);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL, ELLE), model.getFilteredPersonList());
    }

    @Test
    public void execute_notesKeyword_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        NotesContainsKeywordsPredicate predicate = prepareNotesPredicate("She has prior experience");
        predicates.add(predicate);
        FindCommand command = new FindCommand(predicates);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_notesKeyword_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        NotesContainsKeywordsPredicate predicate = prepareNotesPredicate("this field");
        predicates.add(predicate);
        FindCommand command = new FindCommand(predicates);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, CARL), model.getFilteredPersonList());
    }

    @Test
    public void execute_zeroDoneKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        DoneContainsKeywordsPredicate predicate = prepareDonePredicate(" ");
        predicates.add(predicate);
        FindCommand command = new FindCommand(predicates);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code EmailContainsKeywordsPredicate}.
     */
    private EmailContainsKeywordsPredicate prepareEmailPredicate(String userInput) {
        return new EmailContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code PhoneContainsKeywordsPredicate}.
     */
    private PhoneContainsKeywordsPredicate preparePhonePredicate(String userInput) {
        return new PhoneContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code RoleContainsKeywordsPredicate}.
     */
    private RoleContainsKeywordsPredicate prepareRolePredicate(String userInput) {
        return new RoleContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code EmploymentTypeContainsKeywordsPredicate}.
     */
    private EmploymentTypeContainsKeywordsPredicate prepareEmploymentTypePredicate(String userInput) {
        return new EmploymentTypeContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code ExpectedSalaryWithinRangePredicate}.
     */
    private ExpectedSalaryWithinRangePredicate prepareExpectedSalaryPredicate(String userInput) {
        return new ExpectedSalaryWithinRangePredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code LevelOfEducationContainsKeywordPredicate}.
     */
    private LevelOfEducationContainsKeywordsPredicate prepareLevelOfEducationPredicate(String userInput) {
        return new LevelOfEducationContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code ExperienceContainsKeywordPredicate}.
     */
    private ExperienceContainsKeywordsPredicate prepareExperiencePredicate(String userInput) {
        return new ExperienceContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }


    /**
     * Parses {@code userInput} into a {@code TagContainsKeywordPredicate}.
     */
    private TagContainsKeywordsPredicate prepareTagPredicate(String userInput) {
        return new TagContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code NotesContainsKeywordPredicate}.
     */
    private NotesContainsKeywordsPredicate prepareNotesPredicate(String userInput) {
        return new NotesContainsKeywordsPredicate(userInput);
    }

    /**
     * Parses {@code userInput} into a {@code DoneContainsKeywordPredicate}.
     */
    private DoneContainsKeywordsPredicate prepareDonePredicate(String userInput) {
        return new DoneContainsKeywordsPredicate(Arrays.asList(userInput));
    }

}
