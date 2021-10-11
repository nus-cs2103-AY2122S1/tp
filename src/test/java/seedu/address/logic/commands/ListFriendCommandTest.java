package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalFriends.getTypicalFriendsList;
import static seedu.address.testutil.TypicalGames.getTypicalGamesList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.friends.ListFriendCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
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
    public void execute_listFriendNotFiltered_showsSameList() {
        assertCommandSuccess(new ListFriendCommand(preparePredicate("")), model,
                String.format(ListFriendCommand.MESSAGE_SUCCESS_PREPEND, ListFriendCommand.FRIEND_LIST), expectedModel);
    }

    @Test
    public void execute_listFriendIsFiltered_showsEverything() {
        // Set up expected filtered list by Ali
        expectedModel.updateFilteredFriendsList(preparePredicate("Ali"));

        ListFriendCommand listFriendCommand = new ListFriendCommand(preparePredicate("Ali"));
        assertCommandSuccess(listFriendCommand, model,
                listFriendCommand.getMessageSuccess(), expectedModel);
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private FriendIdContainsKeywordPredicate preparePredicate(String userInput) {
        return new FriendIdContainsKeywordPredicate(userInput);
    }
}
