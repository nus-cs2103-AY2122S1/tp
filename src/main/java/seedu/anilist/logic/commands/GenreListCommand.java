package seedu.anilist.logic.commands;

import seedu.anilist.model.Model;
import seedu.anilist.model.genre.GenreList;

/**
 * Displays all available genres
 */
public class GenreListCommand extends Command {

    public static final String COMMAND_WORD = "genrelist";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows available genres.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_GENRE_LIST_MESSAGE = "These are the available genres for usage: \n"
            + GenreList.getListOfGenresAsString();

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_GENRE_LIST_MESSAGE);
    }
}
