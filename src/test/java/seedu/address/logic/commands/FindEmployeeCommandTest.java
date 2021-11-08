package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_EMPLOYEES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEmployees.getTypicalRhrhEmployees;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.employee.EmployeeClassContainsKeywordsPredicate;

public class FindEmployeeCommandTest {
    private Model model = new ModelManager(getTypicalRhrhEmployees(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalRhrhEmployees(), new UserPrefs());

    @Test
    public void equals() {
        EmployeeClassContainsKeywordsPredicate firstPredicate =
                new EmployeeClassContainsKeywordsPredicate(Collections.singletonList("first"));
        EmployeeClassContainsKeywordsPredicate secondPredicate =
                new EmployeeClassContainsKeywordsPredicate(Collections.singletonList("second"));

        FindEmployeeCommand findFirstCommand = new FindEmployeeCommand(firstPredicate);
        FindEmployeeCommand findSecondCommand = new FindEmployeeCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindEmployeeCommand findFirstCommandCopy = new FindEmployeeCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_multipleKeywords_employeesNamesNotFound() {
        String expectedMessage = String.format(MESSAGE_EMPLOYEES_LISTED_OVERVIEW, 0);
        EmployeeClassContainsKeywordsPredicate predicate = preparePredicate("Karlz Kunz");
        FindEmployeeCommand command = new FindEmployeeCommand(predicate);
        expectedModel.updateFilteredEmployeeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEmployeeList());
    }

    @Test
    public void execute_shiftString_employeesNamesFound() {
        String expectedMessage = String.format(MESSAGE_EMPLOYEES_LISTED_OVERVIEW, 1);
        EmployeeClassContainsKeywordsPredicate predicate = preparePredicate("2021-12-10 0800");
        FindEmployeeCommand command = new FindEmployeeCommand(predicate);
        expectedModel.updateFilteredEmployeeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedModel.getFilteredEmployeeList(), model.getFilteredEmployeeList());
    }

    /**
     * Parses {@code userInput} into a {@code ClassContainsKeywordsPredicate}.
     */
    private EmployeeClassContainsKeywordsPredicate preparePredicate(String userInput) {
        return new EmployeeClassContainsKeywordsPredicate(Arrays.asList(userInput));
    }
}
