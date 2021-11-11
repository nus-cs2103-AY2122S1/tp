package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_ROOM_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalRooms.ROOM_ONE;
import static seedu.address.testutil.TypicalRooms.ROOM_TWO;
import static seedu.address.testutil.TypicalRooms.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.room.RoomNumberContainsKeywordsPredicate;


public class FindRoomCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        RoomNumberContainsKeywordsPredicate firstPredicate =
                new RoomNumberContainsKeywordsPredicate(Collections.singletonList("001"));
        RoomNumberContainsKeywordsPredicate secondPredicate =
                new RoomNumberContainsKeywordsPredicate(Collections.singletonList("002"));

        FindRoomCommand findFirstCommand = new FindRoomCommand(firstPredicate);
        FindRoomCommand findSecondCommand = new FindRoomCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindRoomCommand findFirstCommandCopy = new FindRoomCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noRoomFound() {
        String expectedMessage = String.format(MESSAGE_ROOM_LISTED_OVERVIEW);
        RoomNumberContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindRoomCommand command = new FindRoomCommand(predicate);
        expectedModel.updateFilteredRoomList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multipleRoomsFound() {
        String expectedMessage = String.format(MESSAGE_ROOM_LISTED_OVERVIEW);
        RoomNumberContainsKeywordsPredicate predicate = preparePredicate("001 002");
        FindRoomCommand command = new FindRoomCommand(predicate);
        expectedModel.updateFilteredRoomList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ROOM_ONE, ROOM_TWO), model.getFilteredRoomList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private RoomNumberContainsKeywordsPredicate preparePredicate(String userInput) {
        return new RoomNumberContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
