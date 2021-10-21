package seedu.anilist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.model.Model.PREDICATE_SHOW_ALL_ANIME;

import seedu.anilist.model.Model;

/**
 * Displays the statistics of animes in the anime list.
 */
public class StatsCommand extends Command {

    public static final String COMMAND_WORD = "stats";

    public static final String MESSAGE_SUCCESS = "Here are your statistics: ";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAnimeList(PREDICATE_SHOW_ALL_ANIME);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}