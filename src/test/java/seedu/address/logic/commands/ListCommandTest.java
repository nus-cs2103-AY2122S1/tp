package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalFriends.getTypicalFriendsList;
import static seedu.address.testutil.TypicalGames.getTypicalGamesList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.friend.FriendIdContainsKeywordPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFriendsList(), getTypicalGamesList(), new UserPrefs());
        expectedModel = new ModelManager(model.getFriendsList(), getTypicalGamesList(), new UserPrefs());
    }

    @Test
    public void execute_listFriendNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(preparePredicate("")), model,
                String.format(ListCommand.MESSAGE_SUCCESS_PREPEND, ListCommand.FRIEND_LIST), expectedModel);
    }

    @Test
    public void execute_listFriendIsFiltered_showsEverything() {
        // Set up expected filtered list by Ali
        expectedModel.updateFilteredFriendsList(preparePredicate("Ali"));

        ListCommand listCommand = new ListCommand(preparePredicate("Ali"));
        assertCommandSuccess(listCommand, model,
                listCommand.getMessageSuccess(), expectedModel);
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private FriendIdContainsKeywordPredicate preparePredicate(String userInput) {
        return new FriendIdContainsKeywordPredicate(userInput);
    }
}
