package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.FindCommand.MESSAGE_FIND_RESULTS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.KEYWORD_MATCHING_AVE;
import static seedu.address.testutil.TypicalPersons.KEYWORD_MATCHING_FORGETFUL;
import static seedu.address.testutil.TypicalPersons.KEYWORD_MATCHING_MEIER;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.UndoRedoStack;
import seedu.address.logic.commands.FindCommand.FindCondition;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonMatchesKeywordsPredicate;
import seedu.address.testutil.LessonBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonMatchesKeywordsPredicateBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

    @Test
    public void equals() {
        PersonMatchesKeywordsPredicate firstPredicate = new PersonMatchesKeywordsPredicateBuilder()
                .withName(VALID_NAME_AMY).build();
        PersonMatchesKeywordsPredicate secondPredicate = new PersonMatchesKeywordsPredicateBuilder()
                .withName(VALID_NAME_BOB).build();

        FindCommand findFirstCommand = prepareFindCommand(firstPredicate);
        FindCommand findSecondCommand = prepareFindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = prepareFindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_oneField_multiplePersonsFound() {
        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicateBuilder()
                .withName(KEYWORD_MATCHING_MEIER).build();
        List<Person> expectedPersons = Arrays.asList(BENSON, DANIEL);
        String expectedMessage = String.format(MESSAGE_FIND_RESULTS, expectedPersons.size(), predicate);
        FindCommand command = prepareFindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedPersons, model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleFieldsAllCondition_multiplePersonsFound() {
        // Default condition
        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicateBuilder()
                .withAddress(KEYWORD_MATCHING_AVE).withTags(KEYWORD_MATCHING_FORGETFUL).build();
        List<Person> expectedPersons = Arrays.asList(ALICE, BENSON);
        String expectedMessage = String.format(MESSAGE_FIND_RESULTS, 2, predicate);
        FindCommand command = prepareFindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedPersons, model.getFilteredPersonList());

        // Match all condition
        predicate = new PersonMatchesKeywordsPredicateBuilder().withAddress(KEYWORD_MATCHING_AVE)
                .withTags(KEYWORD_MATCHING_FORGETFUL).withCondition(FindCondition.ALL).build();
        expectedMessage = String.format(MESSAGE_FIND_RESULTS, expectedPersons.size(), predicate);
        command = prepareFindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedPersons, model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleFieldsAnyCondition_multiplePersonsFound() {
        // Match any condition
        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicateBuilder()
                .withAddress("ave").withTags(KEYWORD_MATCHING_FORGETFUL)
                .withCondition(FindCondition.ANY).build();
        List<Person> expectedPersons = Arrays.asList(ALICE, BENSON, DANIEL, ELLE);
        String expectedMessage = String.format(MESSAGE_FIND_RESULTS, expectedPersons.size(), predicate);
        FindCommand command = prepareFindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL, ELLE), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleFieldsNoneCondition_multiplePersonsFound() {
        // Match none condition
        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicateBuilder()
                .withAddress(KEYWORD_MATCHING_AVE).withTags(KEYWORD_MATCHING_FORGETFUL)
                .withCondition(FindCondition.NONE).build();
        List<Person> expectedPersons = Arrays.asList(CARL, FIONA, GEORGE);
        String expectedMessage = String.format(MESSAGE_FIND_RESULTS, expectedPersons.size(), predicate);
        FindCommand command = prepareFindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedPersons, model.getFilteredPersonList());
    }

    /**
     * Generates a {@code FindCommand} with parameters {@code predicate}.
     */
    private FindCommand prepareFindCommand(Predicate<Person> predicate) {
        FindCommand findCommand = new FindCommand(predicate);
        findCommand.setDependencies(model, new UndoRedoStack());
        return findCommand;
    }

    @Test
    public void executeFindConditionValueOfName_mixedCase_success() {
        assertEquals(FindCondition.ALL, FindCondition.valueOfName("AlL"));
        assertEquals(FindCondition.ANY, FindCondition.valueOfName("aNY"));
        assertEquals(FindCondition.NONE, FindCondition.valueOfName("NoNe"));
    }

    @Test
    public void execute_timeRangeOverlapping_multiplePersonsFound() {
        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicateBuilder()
                .withTimeRange("1000-1400").build();

        Person personWtihLesson = new PersonBuilder(firstPerson).withLessons(new LessonBuilder()
                .withTimeRange("1000-1400")
                .buildRecurring())
                .build();
        model.setPerson(firstPerson, personWtihLesson); // Ensure at least one lesson to find

        List<Person> expectedPersons = Arrays.asList(personWtihLesson);
        String expectedMessage = String.format(MESSAGE_FIND_RESULTS, 1, predicate);
        FindCommand command = prepareFindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedPersons, model.getFilteredPersonList());
    }
}
