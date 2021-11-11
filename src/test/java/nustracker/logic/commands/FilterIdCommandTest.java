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
import nustracker.model.student.StudentId;
import nustracker.model.student.StudentIdContainsKeywordsPredicate;
import nustracker.testutil.TypicalStudents;


/**
 * Contains integration tests (interaction with the Model) for {@code FilterIdCommand}.
 */
public class FilterIdCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        StudentIdContainsKeywordsPredicate firstPredicate =
                new StudentIdContainsKeywordsPredicate(Collections.singletonList(new StudentId("e0000000")));
        StudentIdContainsKeywordsPredicate secondPredicate =
                new StudentIdContainsKeywordsPredicate(Collections.singletonList(new StudentId("e1111111")));

        FilterCommand filterFirstCommand = new FilterIdCommand(firstPredicate);
        FilterCommand filterSecondCommand = new FilterIdCommand(secondPredicate);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterCommand filterFirstCommandCopy = new FilterIdCommand(firstPredicate);
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
        StudentIdContainsKeywordsPredicate predicate = preparePredicate(" ");
        FilterCommand command = new FilterIdCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
    }

    @Test
    public void execute_multipleKeywords_multipleStudentsFound() {
        String expectedMessage = String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, 3);
        StudentIdContainsKeywordsPredicate predicate = preparePredicate("e9034800 e8123198 e9012390");
        FilterCommand command = new FilterIdCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        CommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalStudents.ALICE, TypicalStudents.BENSON, TypicalStudents.DANIEL),
                model.getFilteredStudentList());
    }

    /**
     * Parses {@code userInput} into a {@code StudentIdContainsKeywordsPredicate}.
     */
    private StudentIdContainsKeywordsPredicate preparePredicate(String userInput) {
        return new StudentIdContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")).stream().map(
            x -> new StudentId(x)).collect(Collectors.toUnmodifiableList()));
    }
}
