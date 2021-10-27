package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.DayOfWeek;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.game.GameId;
import seedu.address.model.time.HourOfDay;

/**
 * Represents a {@code Command} which recommends friends to play with by the
 * highest skill value for the given game and within the given time slot.
 */
public class RecommendCommand extends Command {
    public static final String COMMAND_WORD = "recommend";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " : Recommends friends with highest skill value "
            + "for the given game GAME_ID and available within the provided time to play with.\n"
            + "Parameters: -g GAME_ID -t HOUR DAY"
            + "Example: recommend -g Valorant -t 10 6";

    private final GameId gameFilter;
    private final HourOfDay hourFilter;
    private final DayOfWeek dayFilter;

    /**
     * Constructs an instance of RecommendCommand.
     * @param gameFilter valid existing game to recommend friends for.
     * @param hourFilter valid hour to find friends available during.
     * @param dayFilter valid day to find friends available during.
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
        return null;
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
