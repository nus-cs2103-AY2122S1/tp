package seedu.anilist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.model.Model.PREDICATE_SHOW_ALL_ANIME;

import seedu.anilist.model.Model;

/**
 * Lists all animes in the anime list to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all animes";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAnimeList(PREDICATE_SHOW_ALL_ANIME);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
