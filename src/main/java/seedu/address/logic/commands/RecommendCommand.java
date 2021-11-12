package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_GAME_ID;
import static seedu.address.logic.parser.CliSyntax.FLAG_TIME;

import java.time.DayOfWeek;
import java.util.Comparator;
import java.util.Locale;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendRecommendFilterPredicate;
import seedu.address.model.game.GameId;
import seedu.address.model.gamefriendlink.GameFriendLink;
import seedu.address.model.gamefriendlink.SkillValue;
import seedu.address.model.time.HourOfDay;

/**
 * Represents a {@code Command} which recommends friends to play with by the
 * highest skill value for the given game and within the given time slot.
 */
public class RecommendCommand extends Command {
    public static final String COMMAND_WORD = "recommend";
    public static final String MESSAGE_USAGE = "Format: "
            + COMMAND_WORD + " " + FLAG_GAME_ID + "GAME_ID " + FLAG_TIME + "HOUR DAY\n"
            + "Example: " + COMMAND_WORD + " " + FLAG_GAME_ID + "Valorant " + FLAG_TIME + "10 6";
    public static final String MESSAGE_GAME_NOT_FOUND = "Game with provided GAME_ID not found in games list.";
    public static final String MESSAGE_SUCCESS = "Listing friend recommendations - sorted by highest skill "
            + "for GAME_ID: %1$s, available weekly on: %2$s, %3$s";

    private final GameId gameFilter;
    private final HourOfDay hourFilter;
    private final DayOfWeek dayFilter;

    /**
     * Constructs an instance of RecommendCommand.
     *
     * @param gameFilter valid existing game to recommend friends for.
     * @param hourFilter valid hour to find friends available during.
     * @param dayFilter  valid day to find friends available during.
     */
    public RecommendCommand(GameId gameFilter, HourOfDay hourFilter, DayOfWeek dayFilter) {
        requireNonNull(gameFilter);
        requireNonNull(hourFilter);
        requireNonNull(dayFilter);
        this.gameFilter = gameFilter;
        this.hourFilter = hourFilter;
        this.dayFilter = dayFilter;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (!model.hasGameWithId(gameFilter)) {
            throw new CommandException(MESSAGE_GAME_NOT_FOUND);
        }
        FriendRecommendFilterPredicate recommendFilterPredicate =
                new FriendRecommendFilterPredicate(hourFilter, dayFilter, model.getGame(gameFilter));

        Comparator<Friend> friendComparator = (friend, other) -> {
            GameFriendLink friendGfl = friend.getGameFriendLinks().get(gameFilter);
            GameFriendLink otherFriendGfl = other.getGameFriendLinks().get(gameFilter);

            requireNonNull(friendGfl);
            requireNonNull(otherFriendGfl);

            SkillValue friendSkillValue = friendGfl.getSkillValue();
            SkillValue otherFriendSkillValue = otherFriendGfl.getSkillValue();

            if (friendSkillValue.getSkillVal() > otherFriendSkillValue.getSkillVal()) {
                return -1;
            } else if (friendSkillValue.getSkillVal().equals(otherFriendSkillValue.getSkillVal())) {
                return 0;
            } else {
                return 1;
            }
        };

        model.updateFilteredAndSortedFriendsList(recommendFilterPredicate, friendComparator);

        return new CommandResult(String.format(MESSAGE_SUCCESS, gameFilter,
                dayFilter.toString().toLowerCase(Locale.ROOT),
                hourFilter.toString().length() == 1
                        ? "0" + hourFilter + "00"
                        : hourFilter + "00"),
                CommandType.RECOMMEND);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (!(other instanceof RecommendCommand)) {
            return false;
        } else {
            RecommendCommand otherRecommendCommand = (RecommendCommand) other;
            return otherRecommendCommand.gameFilter.equals(this.gameFilter)
                    && otherRecommendCommand.hourFilter.equals(this.hourFilter)
                    && otherRecommendCommand.dayFilter.equals(this.dayFilter);
        }
    }
}
