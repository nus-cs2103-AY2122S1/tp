package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalMembers.CARL;
import static seedu.address.testutil.TypicalMembers.getTypicalEzFoodie;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Account;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.member.IdContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class ViewCommandTest {
    private Model model = new ModelManager(new Account(), getTypicalEzFoodie(), new UserPrefs());

    @Test
    public void equals() {
        IdContainsKeywordsPredicate firstPredicate =
                new IdContainsKeywordsPredicate(Collections.singletonList("00001"));
        IdContainsKeywordsPredicate secondPredicate =
                new IdContainsKeywordsPredicate(Collections.singletonList("00002"));

        ViewCommand viewFirstCommand = new ViewCommand(firstPredicate);
        ViewCommand viewSecondCommand = new ViewCommand(secondPredicate);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewCommand viewFirstCommandCopy = new ViewCommand(firstPredicate);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different member -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noMemberFound() {
        IdContainsKeywordsPredicate predicate = preparePredicate(" ");
        ViewCommand command = new ViewCommand(predicate);
        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_ID);
        assertEquals(Collections.emptyList(), model.getUpdatedMemberListForView());
    }

    @Test
    public void execute_multipleKeywords_multipleMembersFound() throws CommandException {
        IdContainsKeywordsPredicate predicate = preparePredicate("00003");
        ViewCommand command = new ViewCommand(predicate);
        assertEquals(command.execute(model),
                new CommandResult(ViewCommand.SHOWING_VIEW_MESSAGE, false, false, true, false));
        assertEquals(Arrays.asList(CARL), model.getUpdatedMemberListForView());
    }

    /**
     * Parses {@code userInput} into a {@code IdContainsKeywordsPredicate}.
     */
    private IdContainsKeywordsPredicate preparePredicate(String userInput) {
        return new IdContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
