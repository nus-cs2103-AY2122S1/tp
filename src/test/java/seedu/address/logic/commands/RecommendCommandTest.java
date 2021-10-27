package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DAY_MONDAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DAY_SUNDAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOUR_LOWER_BOUNDARY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOUR_UPPER_BOUNDARY;
import static seedu.address.testutil.TypicalFriends.getTypicalFriendsList;
import static seedu.address.testutil.TypicalGames.CSGO;
import static seedu.address.testutil.TypicalGames.VALORANT;
import static seedu.address.testutil.TypicalGames.getTypicalGamesList;

import java.time.DayOfWeek;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.time.HourOfDay;

public class RecommendCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFriendsList(), getTypicalGamesList(), new UserPrefs());
        expectedModel = new ModelManager(model.getFriendsList(), getTypicalGamesList(), new UserPrefs());
    }

//    @Test
//    public void execute_allFieldsSpecified_success() {
//        Friend friendToEdit = model.getFriendsList().getFriendsList().get(0);
//
//        Friend editedFriend = new FriendBuilder(friendToEdit).withFriendName("1234 5678 9101112 13141516").build();
//
//        EditFriendCommand.EditFriendDescriptor editFriendDescriptor =
//                new EditFriendDescriptorBuilder(editedFriend).build();
//
//        EditFriendCommand editFriendCommand = new EditFriendCommand(friendToEdit.getFriendId(), editFriendDescriptor);
//
//        String expectedMessage = String.format(EditFriendCommand.MESSAGE_EDIT_FRIEND_SUCCESS,
//                editedFriend.getFriendId(), editedFriend.getFriendName());
//
//        expectedModel.setFriend(expectedModel.getFriend(friendToEdit.getFriendId()), editedFriend);
//
//        assertCommandSuccess(editFriendCommand, model, expectedMessage, expectedModel);
//    }
//
//    /**
//     * Edit FriendId which does not exist in the friends list.
//     */
//    @Test
//    public void execute_gameIdNotInGamesList_failure() {
//        GameId gameId = new GameId("NOTINLIST");
//        assertThrows(FriendNotFoundException.class, () -> model.getFriend(notInListFriendId));
//        EditFriendCommand editFriendCommand = new EditFriendCommand(notInListFriendId,
//                new EditFriendDescriptorBuilder().withFriendName(VALID_NAME_BOB).build());
//
//        assertCommandFailure(editFriendCommand, model,
//                String.format(Messages.MESSAGE_FRIEND_ID_NOT_FOUND, notInListFriendId));
//    }

    @Test
    public void equals() {
        RecommendCommand recommendCommand = new RecommendCommand(CSGO.getGameId(),
                new HourOfDay(VALID_HOUR_UPPER_BOUNDARY), DayOfWeek.of(VALID_DAY_MONDAY));

        // same values -> equal
        RecommendCommand copy = new RecommendCommand(CSGO.getGameId(), new HourOfDay(VALID_HOUR_UPPER_BOUNDARY),
                DayOfWeek.of(VALID_DAY_MONDAY));
        assertEquals(recommendCommand, copy);

        // same object -> equal
        assertEquals(recommendCommand, recommendCommand);

        // null -> not equal
        assertNotEquals(null, recommendCommand);

        // different types -> not equal
        assertNotEquals(new ClearCommand(), recommendCommand);

        // different gameId -> not equal
        assertNotEquals(new RecommendCommand(VALORANT.getGameId(), new HourOfDay(VALID_HOUR_UPPER_BOUNDARY),
                DayOfWeek.of(VALID_DAY_MONDAY)), recommendCommand);

        // different day of week -> not equal
        assertNotEquals(new RecommendCommand(CSGO.getGameId(), new HourOfDay(VALID_HOUR_UPPER_BOUNDARY),
                DayOfWeek.of(VALID_DAY_SUNDAY)), recommendCommand);

        // different hour of day -> not equal
        assertNotEquals(new RecommendCommand(CSGO.getGameId(), new HourOfDay(VALID_HOUR_LOWER_BOUNDARY),
                DayOfWeek.of(VALID_DAY_MONDAY)), recommendCommand);
    }
}
