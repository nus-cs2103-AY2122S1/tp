package seedu.address.logic.commands.friends;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendId;
import seedu.address.model.friend.Schedule;
import seedu.address.model.friend.exceptions.InvalidDayTimeException;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.testutil.FriendBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ScheduleFriendCommand}.
 */
class ScheduleFriendCommandTest {

    private final Model model = new ModelManager(SampleDataUtil.getSampleFriendsList(),
            SampleDataUtil.getSampleGamesList(), new UserPrefs());

    @Test
    public void execute_updateSchedule_success() throws InvalidDayTimeException {
        // Set up Schedule
        int day = 7;
        String startTime = "0000";
        String endTime = "2300";
        boolean isFree = true;
        Schedule updatedSchedule = new Schedule();
        updatedSchedule.setScheduleDay(day, startTime, endTime, isFree);

        // Set up Models
        Model expectedModel = new ModelManager(SampleDataUtil.getSampleFriendsList(),
                SampleDataUtil.getSampleGamesList(), new UserPrefs());
        Friend friendToEdit = model.getFriendsList().getFriendsList().get(0);
        Friend editedFriend = new FriendBuilder(friendToEdit).withSchedule(updatedSchedule).build();
        expectedModel.setFriend(expectedModel.getFriend(friendToEdit.getFriendId()), editedFriend);

        // Test if command works
        ScheduleFriendCommand scheduleFriendCommand =
                new ScheduleFriendCommand(friendToEdit.getFriendId(), day, startTime, endTime, isFree);
        String expectedMessage = String.format(ScheduleFriendCommand.MESSAGE_SCHEDULE_FRIEND_SUCCESS,
                editedFriend.getFriendId());
        assertCommandSuccess(scheduleFriendCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonExistentFriendId_failure() {
        FriendId nonExistentId = new FriendId("completelyrandomid1");
        ScheduleFriendCommand scheduleFriendCommand =
                new ScheduleFriendCommand(nonExistentId, 1, "0000", "2000", true);
        assertCommandFailure(scheduleFriendCommand, model,
                String.format(Messages.MESSAGE_NONEXISTENT_FRIEND_ID, nonExistentId));
    }

    @Test
    public void execute_invalidDayTime_failure() {
        ScheduleFriendCommand scheduleFriendCommand =
                new ScheduleFriendCommand(model.getFriendsList().getFriendsList().get(0).getFriendId(),
                        1, "2000", "1000", true);
        assertCommandFailure(scheduleFriendCommand, model,
                String.format(Messages.MESSAGE_INVALID_DAY_TIME_FORMAT,
                        Messages.MESSAGE_END_TIME_ORDER));
    }

    @Test
    public void equals() {
        // Set up Schedule
        int day = 7;
        String startTime = "0000";
        String endTime = "2300";
        boolean isFree = true;
        FriendId friendId = model.getFriendsList().getFriendsList().get(0).getFriendId();
        ScheduleFriendCommand scheduleFriendCommand =
                new ScheduleFriendCommand(friendId, day, startTime, endTime, isFree);

        // Same Fields
        assertTrue(scheduleFriendCommand.equals(
                new ScheduleFriendCommand(friendId, day, startTime, endTime, isFree)));
        // Same Object
        assertTrue(scheduleFriendCommand.equals(scheduleFriendCommand));

        // Different FriendId
        assertFalse(scheduleFriendCommand.equals(new ScheduleFriendCommand(
                model.getFriendsList().getFriendsList().get(1).getFriendId(), day, startTime, endTime, isFree)));
    }


}
