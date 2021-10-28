package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DAY_MONDAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DAY_SUNDAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOUR_LOWER_BOUNDARY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOUR_UPPER_BOUNDARY;
import static seedu.address.logic.commands.RecommendCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalFriends.SKILL_SIX_MINECRAFT_FRIEND_BUILDER;
import static seedu.address.testutil.TypicalFriends.SKILL_TEN_VALORANT_FRIEND_BUILDER;
import static seedu.address.testutil.TypicalFriends.SKILL_THREE_MINECRAFT_FRIEND_BUILDER;
import static seedu.address.testutil.TypicalFriends.SKILL_TWO_VALORANT_FRIEND_BUILDER;
import static seedu.address.testutil.TypicalFriends.SKILL_ZERO_MINECRAFT_FRIEND_BUILDER;
import static seedu.address.testutil.TypicalGames.ANIMAL_CROSSING;
import static seedu.address.testutil.TypicalGames.CSGO;
import static seedu.address.testutil.TypicalGames.MINECRAFT;
import static seedu.address.testutil.TypicalGames.VALORANT;
import static seedu.address.testutil.TypicalGames.getTypicalGamesList;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.FriendsList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.Schedule;
import seedu.address.model.friend.exceptions.InvalidDayTimeException;
import seedu.address.model.game.GameId;
import seedu.address.model.time.HourOfDay;

public class RecommendCommandTest {

    private Model outOfOrderModel;
    private Model expectedOrderedValorantModel;
    private Model expectedOrderedMinecraftModel;

    @BeforeEach
    public void setUp() throws InvalidDayTimeException {
        Schedule tenToFifteenMondayFree = new Schedule();
        tenToFifteenMondayFree.setScheduleDay(1, "10", "15", true);

        Schedule twelveToFifteenMondayFree = new Schedule();
        twelveToFifteenMondayFree.setScheduleDay(1, "10", "15", true);

        Friend skillThreeMinecraftFriend =
            SKILL_THREE_MINECRAFT_FRIEND_BUILDER.withSchedule(tenToFifteenMondayFree).build();
        Friend skillTwoValorantFriend =
            SKILL_TWO_VALORANT_FRIEND_BUILDER.withSchedule(tenToFifteenMondayFree).build();
        Friend skillTenValorantFriend =
            SKILL_TEN_VALORANT_FRIEND_BUILDER.withSchedule(tenToFifteenMondayFree).build();
        Friend skillSixMinecraftFriend =
            SKILL_SIX_MINECRAFT_FRIEND_BUILDER.withSchedule(tenToFifteenMondayFree).build();
        Friend skillZeroMinecraftFriend =
            SKILL_ZERO_MINECRAFT_FRIEND_BUILDER.withSchedule(tenToFifteenMondayFree).build();

        List<Friend> friends = new ArrayList<>();
        friends.add(skillThreeMinecraftFriend);
        friends.add(skillTwoValorantFriend);
        friends.add(skillTenValorantFriend);
        friends.add(skillSixMinecraftFriend);
        friends.add(skillZeroMinecraftFriend);

        FriendsList scrambledList = new FriendsList();
        scrambledList.setFriends(friends);

        List<Friend> orderedBySkillValorantFriends = new ArrayList<>();
        orderedBySkillValorantFriends.add(skillTenValorantFriend);
        orderedBySkillValorantFriends.add(skillTwoValorantFriend);

        FriendsList valorantSorted = new FriendsList();
        valorantSorted.setFriends(orderedBySkillValorantFriends);

        List<Friend> orderedBySkillMinecraftFriends = new ArrayList<>();
        orderedBySkillMinecraftFriends.add(skillSixMinecraftFriend);
        orderedBySkillMinecraftFriends.add(skillThreeMinecraftFriend);
        orderedBySkillMinecraftFriends.add(skillZeroMinecraftFriend);

        FriendsList minecraftSorted = new FriendsList();
        minecraftSorted.setFriends(orderedBySkillMinecraftFriends);

        outOfOrderModel = new ModelManager(scrambledList, getTypicalGamesList(), new UserPrefs());
        expectedOrderedValorantModel = new ModelManager(valorantSorted, getTypicalGamesList(), new UserPrefs());
        expectedOrderedMinecraftModel = new ModelManager(minecraftSorted, getTypicalGamesList(), new UserPrefs());
    }

    @Test
    public void execute_allFieldsSpecifiedWithinTimeValorant_success() throws CommandException {
        String successMessage = String.format(MESSAGE_SUCCESS, VALORANT.getGameId(),
            DayOfWeek.of(1).toString().toLowerCase(Locale.ROOT), new HourOfDay(10).toString() + "00");

        RecommendCommand recommendCommand = new RecommendCommand(VALORANT.getGameId(), new HourOfDay(10),
            DayOfWeek.of(1));

        assertNotEquals(outOfOrderModel.getFilteredAndSortedFriendsList(),
            expectedOrderedValorantModel.getFilteredAndSortedFriendsList());

        CommandResult commandResult = recommendCommand.execute(outOfOrderModel);
        assertEquals(successMessage, commandResult.getFeedbackToUser());
        assertEquals(expectedOrderedValorantModel.getFilteredAndSortedFriendsList(),
            outOfOrderModel.getFilteredAndSortedFriendsList());
    }

    @Test
    public void execute_allFieldsSpecifiedWithinTimeMinecraft_success() throws CommandException {
        String successMessage = String.format(MESSAGE_SUCCESS, MINECRAFT.getGameId(),
            DayOfWeek.of(1).toString().toLowerCase(Locale.ROOT), new HourOfDay(14).toString() + "00");

        RecommendCommand recommendCommand = new RecommendCommand(MINECRAFT.getGameId(), new HourOfDay(14),
            DayOfWeek.of(1));

        // assert that ordering is initially not equals
        assertNotEquals(expectedOrderedMinecraftModel.getFilteredAndSortedFriendsList(),
            outOfOrderModel.getFilteredAndSortedFriendsList());

        CommandResult commandResult = recommendCommand.execute(outOfOrderModel);
        assertEquals(successMessage, commandResult.getFeedbackToUser());
        assertEquals(expectedOrderedMinecraftModel.getFilteredAndSortedFriendsList(),
            outOfOrderModel.getFilteredAndSortedFriendsList());
    }

    @Test
    public void execute_notInSpecifiedTiming_emptyList() throws CommandException {
        String successMessage = String.format(MESSAGE_SUCCESS, MINECRAFT.getGameId(),
            DayOfWeek.of(7).toString().toLowerCase(Locale.ROOT), new HourOfDay(14).toString() + "00");

        // hour within but day not within available times
        RecommendCommand recommendCommand = new RecommendCommand(MINECRAFT.getGameId(), new HourOfDay(14),
            DayOfWeek.of(7));

        CommandResult commandResult = recommendCommand.execute(outOfOrderModel);
        assertEquals(successMessage, commandResult.getFeedbackToUser());
        // gets an empty list since no match
        assertEquals(new FriendsList().getFriendsList(),
            outOfOrderModel.getFilteredAndSortedFriendsList());

        String successMessageHourOutside = String.format(MESSAGE_SUCCESS, MINECRAFT.getGameId(),
            DayOfWeek.of(1).toString().toLowerCase(Locale.ROOT), new HourOfDay(15).toString() + "00");

        // hour not within but day within available times
        RecommendCommand recommendCommandDayHourOutside = new RecommendCommand(MINECRAFT.getGameId(),
            new HourOfDay(15), DayOfWeek.of(1));

        CommandResult commandResultHourOutside = recommendCommandDayHourOutside.execute(outOfOrderModel);
        assertEquals(successMessageHourOutside, commandResultHourOutside.getFeedbackToUser());
        // gets an empty list since no match
        assertEquals(new FriendsList().getFriendsList(),
            outOfOrderModel.getFilteredAndSortedFriendsList());
    }

    @Test
    public void execute_noFriendsWithGameFilter_emptyList() throws CommandException {
        String successMessage = String.format(MESSAGE_SUCCESS, ANIMAL_CROSSING.getGameId(),
            DayOfWeek.of(1).toString().toLowerCase(Locale.ROOT), new HourOfDay(10).toString() + "00");

        // no friend in list should have this gameId
        RecommendCommand recommendCommand = new RecommendCommand(ANIMAL_CROSSING.getGameId(), new HourOfDay(10),
            DayOfWeek.of(1));

        CommandResult commandResult = recommendCommand.execute(outOfOrderModel);
        assertEquals(successMessage, commandResult.getFeedbackToUser());
        // gets an empty list since no match
        assertEquals(new FriendsList().getFriendsList(),
            outOfOrderModel.getFilteredAndSortedFriendsList());
    }

    @Test
    public void execute_gameIdNotInGamesList_failure() throws CommandException {
        // typical games list should not have game with gameId "NOTINLIST"
        RecommendCommand recommendCommand = new RecommendCommand(new GameId("NOTINLIST"), new HourOfDay(10),
            DayOfWeek.of(1));

        assertThrows(CommandException.class, () -> recommendCommand.execute(outOfOrderModel));
    }

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
