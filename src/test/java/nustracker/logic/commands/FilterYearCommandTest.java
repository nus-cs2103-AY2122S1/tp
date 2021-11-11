package nustracker.logic.commands;

import static nustracker.logic.commands.CommandTestUtil.assertCommandSuccess;
import static nustracker.testutil.TypicalAddressBook.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import nustracker.commons.core.Messages;
import nustracker.model.Model;
import nustracker.model.ModelManager;
import nustracker.model.UserPrefs;
import nustracker.model.student.Year;
import nustracker.model.student.YearContainsKeywordsPredicate;
import nustracker.testutil.TypicalStudents;


/**
 * Contains integration tests (interaction with the Model) for {@code FilterIdCommand}.
 */
public class FilterYearCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        YearContainsKeywordsPredicate firstPredicate =
                new YearContainsKeywordsPredicate(Collections.singletonList(new Year("1")));
        YearContainsKeywordsPredicate secondPredicate =
                new YearContainsKeywordsPredicate(Collections.singletonList(new Year("2")));

        FilterCommand filterFirstCommand = new FilterYearCommand(firstPredicate);
        FilterCommand filterSecondCommand = new FilterYearCommand(secondPredicate);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterCommand filterFirstCommandCopy = new FilterYearCommand(firstPredicate);
        assertTrue(filterFirstCommand.equals(filterFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noStudentFound() {
        String expectedMessage = String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, 0);
        YearContainsKeywordsPredicate predicate = preparePredicate(" ");
        FilterCommand command = new FilterYearCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
    }

    @Test
    public void execute_multipleKeywords_multipleStudentsFound() {
        String expectedMessage = String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, 3);
        YearContainsKeywordsPredicate predicate = preparePredicate("2 6");
        FilterCommand command = new FilterYearCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        CommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalStudents.DANIEL, TypicalStudents.GEORGE,
                TypicalStudents.GOD),
                model.getFilteredStudentList());
    }

    /**
     * Parses {@code userInput} into a {@code YearContainsKeywordsPredicate}.
     */
    private YearContainsKeywordsPredicate preparePredicate(String userInput) {
        return new YearContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")).stream().map(
            x -> new Year(x)).collect(Collectors.toUnmodifiableList()));
    }
}
