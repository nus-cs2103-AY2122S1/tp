package seedu.anilist.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.anilist.model.AnimeList;
import seedu.anilist.model.Model;

/**
 * Clears the anime list.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Anime List has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAnimeList(new AnimeList());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
