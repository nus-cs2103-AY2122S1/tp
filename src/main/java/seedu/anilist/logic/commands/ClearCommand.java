package seedu.anilist.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.anilist.model.AnimeList;
import seedu.anilist.model.Model;
import seedu.anilist.model.anime.Anime;

import java.util.List;

/**
 * Clears the anime list.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Anime List has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Anime> lastShownList = model.getFilteredAnimeList();
        while (!model.getFilteredAnimeList().isEmpty()) {
            model.deleteAnime(lastShownList.get(0));
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
