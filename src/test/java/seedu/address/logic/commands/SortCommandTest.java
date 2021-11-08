package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalMembers.ALICE;
import static seedu.address.testutil.TypicalMembers.BENSON;
import static seedu.address.testutil.TypicalMembers.CARL;
import static seedu.address.testutil.TypicalMembers.DANIEL;
import static seedu.address.testutil.TypicalMembers.ELLE;
import static seedu.address.testutil.TypicalMembers.FIONA;
import static seedu.address.testutil.TypicalMembers.GEORGE;
import static seedu.address.testutil.TypicalMembers.getTypicalEzFoodie;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.status.SortStatus;
import seedu.address.model.Account;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.member.CreditSortComparator;

/**
 * Contains integration tests (interaction with the Model) for
 * {@code FindCommand}.
 */
public class SortCommandTest {
    private Model model = new ModelManager(new Account(), getTypicalEzFoodie(), new UserPrefs());
    private Model expectedModel = new ModelManager(new Account(), getTypicalEzFoodie(), new UserPrefs());

    @Test
    public void equals() {
        CreditSortComparator sortCreditAscComparator = new CreditSortComparator(SortStatus.ASC);
        CreditSortComparator sortCreditDescComparator = new CreditSortComparator(SortStatus.DESC);

        SortCommand sortCreditAscCommand = new SortCommand(sortCreditAscComparator);
        SortCommand sortCreditDescCommand = new SortCommand(sortCreditDescComparator);

        // same object -> returns true
        assertTrue(sortCreditAscCommand.equals(sortCreditAscCommand));

        // same values -> returns true
        SortCommand sortCreditAscCommandCopy = new SortCommand(sortCreditAscComparator);
        assertTrue(sortCreditAscCommand.equals(sortCreditAscCommandCopy));

        // different types -> returns false
        assertFalse(sortCreditAscCommand.equals(1));

        // null -> returns false
        assertFalse(sortCreditAscCommand.equals(null));

        // different member -> returns false
        assertFalse(sortCreditAscCommand.equals(sortCreditDescCommand));
    }

    @Test
    public void execute_sortCreditAsc_membersSorted() {
        String expectedMessage = SortCommand.MESSAGE_SORT_ASC;
        CreditSortComparator comparator = prepareComparator(SortStatus.ASC);
        SortCommand command = new SortCommand(comparator);
        expectedModel.updateSortedMemberList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL, ELLE, FIONA, GEORGE, CARL, ALICE, BENSON), model.getUpdatedMemberList());
    }

    @Test
    public void execute_sortCreditDesc_membersSorted() {
        String expectedMessage = SortCommand.MESSAGE_SORT_DESC;
        CreditSortComparator comparator = prepareComparator(SortStatus.DESC);
        SortCommand command = new SortCommand(comparator);
        expectedModel.updateSortedMemberList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, ALICE, CARL, DANIEL, ELLE, FIONA, GEORGE), model.getUpdatedMemberList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private CreditSortComparator prepareComparator(SortStatus sortStatus) {
        return new CreditSortComparator(sortStatus);
    }
}
