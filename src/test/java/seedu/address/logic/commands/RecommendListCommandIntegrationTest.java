package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.RecommendCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalFriends.SKILL_SIX_MINECRAFT_FRIEND_BUILDER;
import static seedu.address.testutil.TypicalFriends.SKILL_TEN_VALORANT_FRIEND_BUILDER;
import static seedu.address.testutil.TypicalFriends.SKILL_THREE_MINECRAFT_FRIEND_BUILDER;
import static seedu.address.testutil.TypicalFriends.SKILL_TWO_VALORANT_FRIEND_BUILDER;
import static seedu.address.testutil.TypicalFriends.SKILL_ZERO_MINECRAFT_FRIEND_BUILDER;
import static seedu.address.testutil.TypicalGames.MINECRAFT;
import static seedu.address.testutil.TypicalGames.getTypicalGamesList;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.friends.ListFriendCommand;
import seedu.address.model.FriendsList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendIdContainsKeywordPredicate;
import seedu.address.model.friend.Schedule;
import seedu.address.model.friend.exceptions.InvalidDayTimeException;
import seedu.address.model.time.HourOfDay;

public class RecommendListCommandIntegrationTest {

    private Model outOfOrderModel;
    private Model outOfOrderModelClone;
    private Model expectedOrderedMinecraftModel;
    private Model onlyVFriendIdModel;

    @BeforeEach
    public void setUp() throws InvalidDayTimeException {
        Schedule tenToFifteenMondayFree = new Schedule();
        tenToFifteenMondayFree.setScheduleDay(1, "10", "15", true);

        Schedule twelveToFifteenMondayFree = new Schedule();
        twelveToFifteenMondayFree.setScheduleDay(1, "10", "15", true);

        Friend SKILL_THREE_MINECRAFT_FRIEND =
            SKILL_THREE_MINECRAFT_FRIEND_BUILDER.withSchedule(tenToFifteenMondayFree).build();
        Friend SKILL_TWO_VALORANT_FRIEND =
            SKILL_TWO_VALORANT_FRIEND_BUILDER.withSchedule(tenToFifteenMondayFree).build();
        Friend SKILL_TEN_VALORANT_FRIEND =
            SKILL_TEN_VALORANT_FRIEND_BUILDER.withSchedule(tenToFifteenMondayFree).build();
        Friend SKILL_SIX_MINECRAFT_FRIEND =
            SKILL_SIX_MINECRAFT_FRIEND_BUILDER.withSchedule(tenToFifteenMondayFree).build();
        Friend SKILL_ZERO_MINECRAFT_FRIEND =
            SKILL_ZERO_MINECRAFT_FRIEND_BUILDER.withSchedule(tenToFifteenMondayFree).build();

        List<Friend> friends = new ArrayList<>();
        friends.add(SKILL_THREE_MINECRAFT_FRIEND);
        friends.add(SKILL_TWO_VALORANT_FRIEND);
        friends.add(SKILL_TEN_VALORANT_FRIEND);
        friends.add(SKILL_SIX_MINECRAFT_FRIEND);
        friends.add(SKILL_ZERO_MINECRAFT_FRIEND);

        FriendsList scrambledList = new FriendsList();
        scrambledList.setFriends(friends);

        List<Friend> orderedBySkillMinecraftFriends = new ArrayList<>();
        orderedBySkillMinecraftFriends.add(SKILL_SIX_MINECRAFT_FRIEND);
        orderedBySkillMinecraftFriends.add(SKILL_THREE_MINECRAFT_FRIEND);
        orderedBySkillMinecraftFriends.add(SKILL_ZERO_MINECRAFT_FRIEND);

        FriendsList minecraftSorted = new FriendsList();
        minecraftSorted.setFriends(orderedBySkillMinecraftFriends);

        List<Friend> onlyContainsFriendsWithVInFriendId = new ArrayList<>();
        onlyContainsFriendsWithVInFriendId.add(SKILL_TWO_VALORANT_FRIEND);
        onlyContainsFriendsWithVInFriendId.add(SKILL_TEN_VALORANT_FRIEND);

        FriendsList containsVInFriendIdFriends = new FriendsList();
        containsVInFriendIdFriends.setFriends(onlyContainsFriendsWithVInFriendId);

        outOfOrderModel = new ModelManager(scrambledList, getTypicalGamesList(), new UserPrefs());
        outOfOrderModelClone = new ModelManager(scrambledList, getTypicalGamesList(), new UserPrefs());
        expectedOrderedMinecraftModel = new ModelManager(minecraftSorted, getTypicalGamesList(), new UserPrefs());
        onlyVFriendIdModel = new ModelManager(containsVInFriendIdFriends, getTypicalGamesList(), new UserPrefs());
    }

    /**
     * Test that recommend and list commands work correctly in succession.
     * @throws CommandException thrown when invalid command is executed.
     */
    @Test
    public void executeRecommendThenList_validCommandsAndList_shouldFilterAndSortCorrectly() throws CommandException {
        // assert that recommend filters initially as expected.
        RecommendCommand recommendCommand = new RecommendCommand(MINECRAFT.getGameId(), new HourOfDay(14),
            DayOfWeek.of(1));

        recommendCommand.execute(outOfOrderModel);
        assertEquals(expectedOrderedMinecraftModel.getFilteredAndSortedFriendsList(),
            outOfOrderModel.getFilteredAndSortedFriendsList());

        // assert that list all command overrides the recommendation filter and sort
        ListFriendCommand listFriendCommand = new ListFriendCommand(new FriendIdContainsKeywordPredicate(""));

         listFriendCommand.execute(outOfOrderModel);
        assertEquals(outOfOrderModelClone.getFilteredAndSortedFriendsList(),
            outOfOrderModel.getFilteredAndSortedFriendsList());
        assertEquals(outOfOrderModelClone.getFilteredAndSortedFriendsList().size(),
            outOfOrderModel.getFilteredAndSortedFriendsList().size());

        // assert that no match recommend command works next.
        RecommendCommand recommendCommandNoMatch = new RecommendCommand(MINECRAFT.getGameId(), new HourOfDay(20),
            DayOfWeek.of(1));

        recommendCommandNoMatch.execute(outOfOrderModel);
        assertEquals(0, outOfOrderModel.getFilteredAndSortedFriendsList().size());
        assertEquals(new FriendsList().getFriendsList(),
            outOfOrderModel.getFilteredAndSortedFriendsList());

        // assert that list command filter by FriendId works next.
        ListFriendCommand listFriendCommandFilterV = new ListFriendCommand(new FriendIdContainsKeywordPredicate("v"));

        listFriendCommandFilterV.execute(outOfOrderModel);
        assertEquals(onlyVFriendIdModel.getFilteredAndSortedFriendsList(),
            outOfOrderModel.getFilteredAndSortedFriendsList());
        assertEquals(onlyVFriendIdModel.getFilteredAndSortedFriendsList().size(),
            outOfOrderModel.getFilteredAndSortedFriendsList().size());
    }
}
