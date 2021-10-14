package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.friends.ListFriendCommand.MESSAGE_UNKNOWN_PREDICATE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFriends.getTypicalFriendsList;
import static seedu.address.testutil.TypicalGames.getTypicalGamesList;

import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.friends.ListFriendCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendIdContainsKeywordPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListFriendCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFriendsList(), getTypicalGamesList(), new UserPrefs());
        expectedModel = new ModelManager(model.getFriendsList(), getTypicalGamesList(), new UserPrefs());
    }

    @Test
    public void execute_unknownPredicate_throwsCommandException() {
        Predicate<Friend> unknownPredicate = friend -> false;
        assertCommandFailure(new ListFriendCommand(unknownPredicate), model, MESSAGE_UNKNOWN_PREDICATE);
        assertThrows(CommandException.class, () -> new ListFriendCommand(unknownPredicate).getMessageSuccess());
    }

    @Test
    public void execute_listFriendNotFiltered_showsSameList() {
        assertCommandSuccess(new ListFriendCommand(preparePredicate("")), model,
                String.format(ListFriendCommand.MESSAGE_SUCCESS_PREPEND, ListFriendCommand.FRIEND_LIST), expectedModel);
    }

    @Test
    public void execute_listFriendIsFiltered_showsEverything() throws CommandException {
        // Set up expected filtered list by Ali
        expectedModel.updateFilteredFriendsList(preparePredicate("Ali"));

        ListFriendCommand listFriendCommand = new ListFriendCommand(preparePredicate("Ali"));
        assertCommandSuccess(listFriendCommand, model,
                listFriendCommand.getMessageSuccess(), expectedModel);
    }

    @Test
    public void equals() {
        Predicate<Friend> filterByA = preparePredicate("A");
        ListFriendCommand friendCommand = new ListFriendCommand(filterByA);

        // Same Object
        assertTrue(friendCommand.equals(friendCommand));
        // Same Predicate
        assertTrue(friendCommand.equals(new ListFriendCommand(filterByA)));
        // Same Predicate Value
        assertTrue(friendCommand.equals(new ListFriendCommand(preparePredicate("A"))));

        // Different Value
        assertFalse(friendCommand.equals(new ListFriendCommand(preparePredicate("Different"))));
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private FriendIdContainsKeywordPredicate preparePredicate(String userInput) {
        return new FriendIdContainsKeywordPredicate(userInput);
    }
}
