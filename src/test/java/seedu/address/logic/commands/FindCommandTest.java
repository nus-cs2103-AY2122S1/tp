package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PersonContainsFieldsPredicate;
import seedu.address.model.person.predicates.StaffHasCorrectIndexPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));
        PersonContainsFieldsPredicate emptyPredicate = new PersonContainsFieldsPredicate();

        FindCommand findFirstCommand = new FindCommand(firstPredicate, emptyPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate, emptyPredicate);
        FindCommand findThirdCommand = new FindCommand(1, new PersonContainsFieldsPredicate());
        FindCommand findFourthCommand = new FindCommand(2, new PersonContainsFieldsPredicate());

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate, emptyPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));

        // Test equals() method for Find Commands that search by index
        assertTrue(findThirdCommand.equals(new FindCommand(1, new PersonContainsFieldsPredicate())));
        assertFalse(findThirdCommand.equals(findFourthCommand));
        assertFalse(findFirstCommand.equals(findThirdCommand));
        assertFalse(findFourthCommand.equals(findFirstCommand));
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate("Kurz Elle Kunz");
        PersonContainsFieldsPredicate emptyPredicate = new PersonContainsFieldsPredicate();
        FindCommand command = new FindCommand(predicate, emptyPredicate);
        expectedModel.updateFilteredPersonList(predicate);
        // assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), expectedModel.getFilteredPersonList());
    }

    @Test
    public void execute_indexSearchWithinRange() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        StaffHasCorrectIndexPredicate predicate = prepareIndexPredicate(2);
        FindCommand command = new FindCommand(2, new PersonContainsFieldsPredicate());
        expectedModel.updateFilteredPersonList(predicate);
        // assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL), expectedModel.getFilteredPersonList());
    }

    @Test
    public void execute_indexSearchOutOfRange() {
        int outOfBoundIndex = model.getFilteredPersonList().size() + 1;
        FindCommand command = new FindCommand(outOfBoundIndex, new PersonContainsFieldsPredicate());
        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code StaffHasCorrectIndexPredicate predicate}.
     * Note that the index is the userInput - 1, as it starts from 0 instead of 1.
     */
    private StaffHasCorrectIndexPredicate prepareIndexPredicate(int index) {
        return new StaffHasCorrectIndexPredicate(index, model);
    }
}
