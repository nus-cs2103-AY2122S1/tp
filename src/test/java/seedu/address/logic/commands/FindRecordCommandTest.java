package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_RECORDS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalRecordsBook.RESIDENCY_ONE;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.residency.exceptions.ResidencyContainsKeywordsPredicate;
import seedu.address.testutil.TypicalRecordsBook;

public class FindRecordCommandTest {
    private Model model = new ModelManager(TypicalRecordsBook.getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalRecordsBook.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        ResidencyContainsKeywordsPredicate firstPredicate =
                new ResidencyContainsKeywordsPredicate(Collections.singletonList("001"));
        ResidencyContainsKeywordsPredicate secondPredicate =
                new ResidencyContainsKeywordsPredicate(Collections.singletonList("002"));

        FindRecordCommand findFirstCommand = new FindRecordCommand(firstPredicate);
        FindRecordCommand findSecondCommand = new FindRecordCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindRecordCommand findFirstCommandCopy = new FindRecordCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_presentKeyword_recordFound() {
        String expectedMessage = String.format(MESSAGE_RECORDS_LISTED_OVERVIEW);
        ResidencyContainsKeywordsPredicate predicate = preparePredicate("001");
        FindRecordCommand command = new FindRecordCommand(predicate);
        expectedModel.updateFilteredRecordList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(RESIDENCY_ONE), model.getFilteredRecordList());
    }

    @Test
    public void execute_missingKeyword_noRecordFound() {
        String expectedMessage = String.format(MESSAGE_RECORDS_LISTED_OVERVIEW);
        ResidencyContainsKeywordsPredicate predicate = preparePredicate("002");
        FindRecordCommand command = new FindRecordCommand(predicate);
        expectedModel.updateFilteredRecordList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredRecordList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private ResidencyContainsKeywordsPredicate preparePredicate(String userInput) {
        return new ResidencyContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
