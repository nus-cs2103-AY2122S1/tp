package seedu.anilist.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.anilist.commons.core.Messages;
import seedu.anilist.commons.core.index.Index;
import seedu.anilist.logic.commands.exceptions.CommandException;
import seedu.anilist.model.Model;
import seedu.anilist.model.anime.Anime;

/**
 * Deletes an anime identified using it's displayed index from the anime list.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the anime identified by the index number used in the displayed anime list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ANIME_SUCCESS = "Deleted Anime: %1$s";


    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Anime> lastShownList = model.getFilteredAnimeList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_OUT_OF_RANGE_INDEX);
        }

        Anime animeToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteAnime(animeToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_ANIME_SUCCESS, animeToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
