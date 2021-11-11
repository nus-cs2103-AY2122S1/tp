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
import nustracker.model.student.Major;
import nustracker.model.student.MajorContainsKeywordsPredicate;
import nustracker.testutil.TypicalStudents;

/**
 * Contains integration tests (interaction with the Model) for {@code FilterMajorCommand}.
 */
public class FilterMajorCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        MajorContainsKeywordsPredicate firstPredicate =
                new MajorContainsKeywordsPredicate(Collections.singletonList(new Major("cs")));
        MajorContainsKeywordsPredicate secondPredicate =
                new MajorContainsKeywordsPredicate(Collections.singletonList(new Major("is")));

        FilterCommand filterFirstCommand = new FilterMajorCommand(firstPredicate);
        FilterCommand filterSecondCommand = new FilterMajorCommand(secondPredicate);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterCommand filterFirstCommandCopy = new FilterMajorCommand(firstPredicate);
        assertTrue(filterFirstCommand.equals(filterFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different predicates -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noStudentFound() {
        String expectedMessage = String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, 0);
        MajorContainsKeywordsPredicate predicate = preparePredicate(" ");
        FilterCommand command = new FilterMajorCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
    }

    @Test
    public void execute_multipleKeywords_multipleStudentsFound() {
        String expectedMessage = String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, 3);
        MajorContainsKeywordsPredicate predicate = preparePredicate("is bza");
        FilterCommand command = new FilterMajorCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        CommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalStudents.BENSON, TypicalStudents.DANIEL, TypicalStudents.GEORGE),
                model.getFilteredStudentList());
    }

    /**
     * Parses {@code userInput} into a {@code MajorContainsKeywordsPredicate}.
     */
    private MajorContainsKeywordsPredicate preparePredicate(String userInput) {
        return new MajorContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")).stream().map(
            x -> new Major(x)).collect(Collectors.toUnmodifiableList()));
    }
}
