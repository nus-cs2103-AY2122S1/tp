package seedu.address.logic.commands.member;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_MEMBERS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalMembers.CARL;
import static seedu.address.testutil.TypicalMembers.ELLE;
import static seedu.address.testutil.TypicalMembers.FIONA;
import static seedu.address.testutil.TypicalMembers.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.NameContainsKeywordsPredicate;
import seedu.address.model.module.member.Member;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class MfindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate<Member> firstPredicate =
                new NameContainsKeywordsPredicate<Member>(Collections.singletonList("first"));
        NameContainsKeywordsPredicate<Member> secondPredicate =
                new NameContainsKeywordsPredicate<Member>(Collections.singletonList("second"));

        MfindCommand findFirstCommand = new MfindCommand(firstPredicate);
        MfindCommand findSecondCommand = new MfindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        MfindCommand findFirstCommandCopy = new MfindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different member -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noMemberFound() {
        String expectedMessage = String.format(MESSAGE_MEMBERS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate<Member> predicate = preparePredicate(" ");
        MfindCommand command = new MfindCommand(predicate);
        expectedModel.updateFilteredMemberList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredMemberList());
    }

    @Test
    public void execute_multipleKeywords_multipleMembersFound() {
        String expectedMessage = String.format(MESSAGE_MEMBERS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate<Member> predicate = preparePredicate("Kurz Elle Kunz");
        MfindCommand command = new MfindCommand(predicate);
        expectedModel.updateFilteredMemberList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredMemberList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate<Member>}.
     */
    private NameContainsKeywordsPredicate<Member> preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate<Member>(Arrays.asList(userInput.split("\\s+")));
    }
}
