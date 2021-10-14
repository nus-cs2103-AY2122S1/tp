//package seedu.address.logic.commands;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.commons.core.Messages.MESSAGE_INVALID_FRIEND_ID;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.address.testutil.Assert.assertThrows;
//import static seedu.address.testutil.TypicalFriends.BENSON;
//import static seedu.address.testutil.TypicalFriends.getTypicalFriendsList;
//import static seedu.address.testutil.TypicalGames.getTypicalGamesList;
//
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.address.logic.commands.exceptions.CommandException;
//import seedu.address.logic.commands.friends.GetFriendCommand;
//import seedu.address.model.Model;
//import seedu.address.model.ModelManager;
//import seedu.address.model.UserPrefs;
//import seedu.address.model.friend.FriendIdMatchesKeywordPredicate;
//
///**
// * Contains integration tests (interaction with the Model) for {@code GetCommand}.
// */
//public class GetFriendCommandTest {
//    private final Model model = new ModelManager(getTypicalFriendsList(), getTypicalGamesList(), new UserPrefs());
//    private final Model expectedModel = new ModelManager(getTypicalFriendsList(), getTypicalGamesList(),
//            new UserPrefs());
//
//    @Test
//    public void equals() {
//        FriendIdMatchesKeywordPredicate firstPredicate =
//                new FriendIdMatchesKeywordPredicate("first");
//        FriendIdMatchesKeywordPredicate secondPredicate =
//                new FriendIdMatchesKeywordPredicate("second");
//
//        GetFriendCommand getFirstCommand = new GetFriendCommand(firstPredicate);
//        GetFriendCommand getSecondCommand = new GetFriendCommand(secondPredicate);
//
//        // same object -> returns true
//        assertTrue(getFirstCommand.equals(getFirstCommand));
//
//        // same values -> returns true
//        GetFriendCommand getFirstCommandCopy = new GetFriendCommand(firstPredicate);
//        assertTrue(getFirstCommand.equals(getFirstCommandCopy));
//
//        // different types -> returns false
//        assertFalse(getFirstCommand.equals(1));
//
//        // null -> returns false
//        assertFalse(getFirstCommand.equals(null));
//
//        // different person -> returns false
//        assertFalse(getFirstCommand.equals(getSecondCommand));
//    }
//
//    @Test
//    public void execute_emptyKeyword_throwsCommandException() {
//        String emptyString = " ";
//        String expectedMessage = String.format(MESSAGE_INVALID_FRIEND_ID, emptyString);
//        FriendIdMatchesKeywordPredicate predicate = new FriendIdMatchesKeywordPredicate(emptyString);
//        GetFriendCommand getFriendCommand = new GetFriendCommand(predicate);
//
//        assertThrows(CommandException.class, expectedMessage, () -> getFriendCommand.execute(model));
//    }
//
//    @Test
//    public void execute_existingFriendId_correctFriendFound() {
//        String friendId = "98765432";
//        String expectedMessage = String.format(GetFriendCommand.MESSAGE_FRIEND_FULL_INFORMATION, friendId);
//        FriendIdMatchesKeywordPredicate predicate = new FriendIdMatchesKeywordPredicate(friendId);
//        GetFriendCommand command = new GetFriendCommand(predicate);
//
//        expectedModel.updateFilteredFriendsList(predicate);
//        assertCommandSuccess(command, model, expectedMessage, expectedModel);
//        assertEquals(List.of(BENSON), model.getFilteredFriendsList());
//    }
//}
