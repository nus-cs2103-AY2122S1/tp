package seedu.address.logic.commands.modulelesson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_LESSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_DAY_TUES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_DAY_WED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_TIME_09;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_TIME_15;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2103;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModuleLessons.CS2100_LAB1;
import static seedu.address.testutil.TypicalModuleLessons.CS2103_TUT1;
import static seedu.address.testutil.TypicalModuleLessons.CS2106_TUT1;
import static seedu.address.testutil.TypicalModuleLessons.getTypicalConthacks;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.modulelesson.LessonDayContainsKeywordsPredicate;
import seedu.address.model.modulelesson.LessonTimeContainsKeywordsPredicate;
import seedu.address.model.modulelesson.ModuleCodeContainsKeywordsPredicate;

public class FindModuleLessonCommandTest {
    private Model model = new ModelManager(getTypicalConthacks(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalConthacks(), new UserPrefs());

    @Test
    public void equals() {
        ModuleCodeContainsKeywordsPredicate firstModuleCodePredicate =
                new ModuleCodeContainsKeywordsPredicate(Collections.singletonList("first"));
        ModuleCodeContainsKeywordsPredicate secondModuleCodePredicate =
                new ModuleCodeContainsKeywordsPredicate(Collections.singletonList("second"));

        FindModuleLessonCommand findFirstModuleCodeCommand = new FindModuleLessonCommand(firstModuleCodePredicate);
        FindModuleLessonCommand findSecondModuleCodeCommand = new FindModuleLessonCommand(secondModuleCodePredicate);

        // same object -> returns true
        assertTrue(findFirstModuleCodeCommand.equals(findFirstModuleCodeCommand));

        // same values -> returns true
        FindModuleLessonCommand findFirstModuleCodeCommandCopy = new FindModuleLessonCommand(firstModuleCodePredicate);
        assertTrue(findFirstModuleCodeCommand.equals(findFirstModuleCodeCommandCopy));

        // different types -> returns false
        assertFalse(findFirstModuleCodeCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstModuleCodeCommand.equals(null));

        // different lesson -> returns false
        assertFalse(findFirstModuleCodeCommand.equals(findSecondModuleCodeCommand));

        LessonDayContainsKeywordsPredicate firstLessonDayPredicate =
                new LessonDayContainsKeywordsPredicate(Collections.singletonList("first"));
        LessonDayContainsKeywordsPredicate secondLessonDayPredicate =
                new LessonDayContainsKeywordsPredicate(Collections.singletonList("second"));

        FindModuleLessonCommand findFirstLessonDayCommand = new FindModuleLessonCommand(firstLessonDayPredicate);
        FindModuleLessonCommand findSecondLessonDayCommand = new FindModuleLessonCommand(secondLessonDayPredicate);

        // same object -> returns true
        assertTrue(findFirstLessonDayCommand.equals(findFirstLessonDayCommand));

        // same values -> returns true
        FindModuleLessonCommand findFirstLessonDayCommandCopy = new FindModuleLessonCommand(firstLessonDayPredicate);
        assertTrue(findFirstLessonDayCommand.equals(findFirstLessonDayCommandCopy));

        // different types -> returns false
        assertFalse(findFirstLessonDayCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstLessonDayCommand.equals(null));

        // different lesson -> returns false
        assertFalse(findFirstLessonDayCommand.equals(findSecondLessonDayCommand));

        LessonTimeContainsKeywordsPredicate firstLessonTimePredicate =
                new LessonTimeContainsKeywordsPredicate(Collections.singletonList("first"));
        LessonTimeContainsKeywordsPredicate secondLessonTimePredicate =
                new LessonTimeContainsKeywordsPredicate(Collections.singletonList("second"));

        FindModuleLessonCommand findFirstLessonTimeCommand = new FindModuleLessonCommand(firstLessonTimePredicate);
        FindModuleLessonCommand findSecondLessonTimeCommand = new FindModuleLessonCommand(secondLessonTimePredicate);

        // same object -> returns true
        assertTrue(findFirstLessonTimeCommand.equals(findFirstLessonTimeCommand));

        // same values -> returns true
        FindModuleLessonCommand findFirstLessonTimeCommandCopy = new FindModuleLessonCommand(firstLessonTimePredicate);
        assertTrue(findFirstLessonTimeCommand.equals(findFirstLessonTimeCommandCopy));

        // different types -> returns false
        assertFalse(findFirstLessonTimeCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstLessonTimeCommand.equals(null));

        // different lesson -> returns false
        assertFalse(findFirstLessonTimeCommand.equals(findSecondLessonTimeCommand));
    }

    @Test
    public void execute_multipleModuleCodes_noLessonFound() {
        String expectedMessage = String.format(MESSAGE_LESSONS_LISTED_OVERVIEW, 0);
        ModuleCodeContainsKeywordsPredicate predicate = prepareModulePredicate("MA1521, MA1101");
        FindModuleLessonCommand command = new FindModuleLessonCommand(predicate);
        expectedModel.updateFilteredModuleLessonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredModuleLessonList());
    }

    @Test
    public void execute_multipleModuleCodes_multipleLessonsFound() {
        String expectedMessage = String.format(MESSAGE_LESSONS_LISTED_OVERVIEW, 2);
        ModuleCodeContainsKeywordsPredicate predicate = prepareModulePredicate(
                String.format("%s %s", VALID_MODULE_CODE_CS2103, VALID_MODULE_CODE_CS2100)
        );
        FindModuleLessonCommand command = new FindModuleLessonCommand(predicate);
        expectedModel.updateFilteredModuleLessonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2100_LAB1, CS2103_TUT1), model.getFilteredModuleLessonList());
    }

    @Test
    public void execute_multipleDays_noLessonFound() {
        String expectedMessage = String.format(MESSAGE_LESSONS_LISTED_OVERVIEW, 0);
        LessonDayContainsKeywordsPredicate predicate = prepareLessonDayPredicate("5, 6");
        FindModuleLessonCommand command = new FindModuleLessonCommand(predicate);
        expectedModel.updateFilteredModuleLessonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredModuleLessonList());
    }

    @Test
    public void execute_multipleDays_multipleLessonsFound() {
        String expectedMessage = String.format(MESSAGE_LESSONS_LISTED_OVERVIEW, 2);
        LessonDayContainsKeywordsPredicate predicate = prepareLessonDayPredicate(
                String.format("%s %s", VALID_LESSON_DAY_TUES, VALID_LESSON_DAY_WED)
        );
        FindModuleLessonCommand command = new FindModuleLessonCommand(predicate);
        expectedModel.updateFilteredModuleLessonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2100_LAB1, CS2106_TUT1), model.getFilteredModuleLessonList());
    }

    @Test
    public void execute_multipleTimes_noLessonFound() {
        String expectedMessage = String.format(MESSAGE_LESSONS_LISTED_OVERVIEW, 0);
        LessonTimeContainsKeywordsPredicate predicate = prepareLessonTimePredicate("10:00, 11:00");
        FindModuleLessonCommand command = new FindModuleLessonCommand(predicate);
        expectedModel.updateFilteredModuleLessonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredModuleLessonList());
    }

    @Test
    public void execute_multipleTimes_multipleLessonsFound() {
        String expectedMessage = String.format(MESSAGE_LESSONS_LISTED_OVERVIEW, 2);
        LessonTimeContainsKeywordsPredicate predicate = prepareLessonTimePredicate(
                String.format("%s %s", VALID_LESSON_TIME_09, VALID_LESSON_TIME_15)
        );
        FindModuleLessonCommand command = new FindModuleLessonCommand(predicate);
        expectedModel.updateFilteredModuleLessonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2100_LAB1, CS2103_TUT1), model.getFilteredModuleLessonList());
    }

    /**
     * Parses {@code userInput} into a {@code ModuleCodesContainsKeywordsPredicate}.
     */
    private ModuleCodeContainsKeywordsPredicate prepareModulePredicate(String userInput) {
        List<String> moduleKeywordsList = Arrays.stream(userInput.trim().split("\\s+"))
                .collect(Collectors.toList());
        return new ModuleCodeContainsKeywordsPredicate(moduleKeywordsList);
    }

    /**
     * Parses {@code userInput} into a {@code LessonDayContainsKeywordsPredicate}.
     */
    private LessonDayContainsKeywordsPredicate prepareLessonDayPredicate(String userInput) {
        List<String> lessonDayKeywordsList = Arrays.stream(userInput.trim().split("\\s+"))
                .collect(Collectors.toList());
        return new LessonDayContainsKeywordsPredicate(lessonDayKeywordsList);
    }

    /**
     * Parses {@code userInput} into a {@code LessonDayContainsKeywordsPredicate}.
     */
    private LessonTimeContainsKeywordsPredicate prepareLessonTimePredicate(String userInput) {
        List<String> lessonTimeKeywordsList = Arrays.stream(userInput.trim().split("\\s+"))
                .collect(Collectors.toList());
        return new LessonTimeContainsKeywordsPredicate(lessonTimeKeywordsList);
    }
}
