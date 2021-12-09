package seedu.times.logic.commands.classcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.times.commons.core.Messages.MESSAGE_CLASSES_LISTED_OVERVIEW;
import static seedu.times.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.times.testutil.TypicalTimestable.JC_CHEMISTRY;
import static seedu.times.testutil.TypicalTimestable.JC_CHEMISTRY_DAY;
import static seedu.times.testutil.TypicalTimestable.JC_PHYSICS;
import static seedu.times.testutil.TypicalTimestable.JC_PHYSICS_CLASS_TIMING;
import static seedu.times.testutil.TypicalTimestable.JC_PHYSICS_DAY;
import static seedu.times.testutil.TypicalTimestable.JC_PHYSICS_TIME;
import static seedu.times.testutil.TypicalTimestable.SEC_CHEMISTRY;
import static seedu.times.testutil.TypicalTimestable.SEC_CHEMISTRY_DAY;
import static seedu.times.testutil.TypicalTimestable.SEC_MATHS;
import static seedu.times.testutil.TypicalTimestable.SEC_MATHS_TIME;
import static seedu.times.testutil.TypicalTimestable.SEC_PHYSICS_CLASS_TIMING;
import static seedu.times.testutil.TypicalTimestable.SEC_PHYSICS_DAY;
import static seedu.times.testutil.TypicalTimestable.SEC_PHYSICS_TIME;
import static seedu.times.testutil.TypicalTimestable.getTypicalTimesTable;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.times.model.Model;
import seedu.times.model.ModelManager;
import seedu.times.model.UserPrefs;
import seedu.times.model.tuitionclass.predicates.ClassTimingContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindClassCommandTest {
    // model stub with a class containing JC_PHYISCS, SEC_PHYSICS, JC_MATHS, SEC_MATHS,
    //                JC_CHEMISTRY, SEC_CHEMISTRY
    private Model model = new ModelManager(getTypicalTimesTable(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTimesTable(), new UserPrefs());

    @Test
    public void equals() {
        ClassTimingContainsKeywordsPredicate firstPredicate =
                new ClassTimingContainsKeywordsPredicate(Collections.singletonList("first"));
        ClassTimingContainsKeywordsPredicate secondPredicate =
                new ClassTimingContainsKeywordsPredicate(Collections.singletonList("second"));

        FindClassCommand findClassFirstCommand = new FindClassCommand(firstPredicate);
        FindClassCommand findClassSecondCommand = new FindClassCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findClassFirstCommand.equals(findClassFirstCommand));

        // same values -> returns true
        FindClassCommand findClassFirstCommandCopy = new FindClassCommand(firstPredicate);
        assertTrue(findClassFirstCommand.equals(findClassFirstCommandCopy));

        // different types -> returns false
        assertFalse(findClassFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findClassFirstCommand.equals(null));

        // different predicate with different list of keywords -> returns false
        assertFalse(findClassFirstCommand.equals(findClassSecondCommand));
    }

    @Test
    public void constructor_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FindClassCommand(null));
    }

    @Test
    public void execute_findSingleClass_success() {
        // command to execute
        ClassTimingContainsKeywordsPredicate predicate = preparePredicate(JC_PHYSICS_CLASS_TIMING);
        FindClassCommand command = new FindClassCommand(predicate);
        expectedModel.updateFilteredClassList(predicate);

        String expectedMessage = String.format(MESSAGE_CLASSES_LISTED_OVERVIEW, 1);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(JC_PHYSICS), model.getFilteredTuitionClassList());
    }

    @Test
    public void execute_findTwoClasses_success() {
        // Both JC Physics and Sec maths are at the same time but different day.
        ClassTimingContainsKeywordsPredicate predicate = preparePredicate(JC_PHYSICS_TIME + " " + SEC_MATHS_TIME);
        FindClassCommand command = new FindClassCommand(predicate);
        expectedModel.updateFilteredClassList(predicate);

        String expectedMessage = String.format(MESSAGE_CLASSES_LISTED_OVERVIEW, 2);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(JC_PHYSICS, SEC_MATHS), model.getFilteredTuitionClassList());

        // Both JC Chemistry and Sec Chemistry are at the same day but different time.
        predicate = preparePredicate(JC_CHEMISTRY_DAY + " " + SEC_CHEMISTRY_DAY);
        command = new FindClassCommand(predicate);
        expectedModel.updateFilteredClassList(predicate);

        expectedMessage = String.format(MESSAGE_CLASSES_LISTED_OVERVIEW, 2);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(JC_CHEMISTRY, SEC_CHEMISTRY), model.getFilteredTuitionClassList());
    }

    @Test
    public void execute_findTwoClassDayTimings_noClassFound() {
        // Expected behaviour is that when we specify two timings, no class matches this timing
        // so it finds no classes, for the same timing, day, and class timing.

        ClassTimingContainsKeywordsPredicate predicate =
                preparePredicate(JC_PHYSICS_CLASS_TIMING + " " + SEC_PHYSICS_CLASS_TIMING);
        FindClassCommand command = new FindClassCommand(predicate);
        expectedModel.updateFilteredClassList(predicate);

        String expectedMessage = String.format(MESSAGE_CLASSES_LISTED_OVERVIEW, 0);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        predicate =
                preparePredicate(JC_PHYSICS_DAY + " " + SEC_PHYSICS_DAY);
        command = new FindClassCommand(predicate);
        expectedModel.updateFilteredClassList(predicate);

        expectedMessage = String.format(MESSAGE_CLASSES_LISTED_OVERVIEW, 0);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        predicate =
                preparePredicate(JC_PHYSICS_TIME + " " + SEC_PHYSICS_TIME);
        command = new FindClassCommand(predicate);
        expectedModel.updateFilteredClassList(predicate);

        expectedMessage = String.format(MESSAGE_CLASSES_LISTED_OVERVIEW, 0);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private ClassTimingContainsKeywordsPredicate preparePredicate(String userInput) {
        return new ClassTimingContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
