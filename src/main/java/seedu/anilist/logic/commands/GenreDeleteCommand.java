package seedu.anilist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.model.Model.PREDICATE_SHOW_ALL_ANIME;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.anilist.commons.core.Messages;
import seedu.anilist.commons.core.index.Index;
import seedu.anilist.logic.commands.exceptions.CommandException;
import seedu.anilist.model.Model;
import seedu.anilist.model.anime.Anime;
import seedu.anilist.model.anime.Episode;
import seedu.anilist.model.anime.Name;
import seedu.anilist.model.anime.Status;
import seedu.anilist.model.genre.Genre;

/**
 * Deletes the provided genres from the specified anime.
 */
public class GenreDeleteCommand extends GenreCommand {

    public static final String MESSAGE_SUCCESS = "Genre(s) %1$s deleted from anime.\n"
            + "%2$s";
    public static final String MESSAGE_GENRE_NOT_PRESENT = "Genre(s) %1$s are not present in anime.\n"
            + "%2$s";
    public static final String MESSAGE_PARTIAL_SUCCESS = "Genre(s) %1$s deleted.\n"
            + "Genre(s) %2$s are not present in anime.\n"
            + "%3$s";

    public GenreDeleteCommand(Index index, GenreCommand.GenresDescriptor genresDescriptor) {
        super(index, genresDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Anime> lastShownList = model.getFilteredAnimeList();

        if (getIndex().getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ANIME_DISPLAYED_INDEX);
        }

        Anime animeToEdit = lastShownList.get(getIndex().getZeroBased());
        Anime editedAnime = createUpdatedAnime(animeToEdit, getGenresDescriptor());

        model.setAnime(animeToEdit, editedAnime);

        model.updateFilteredAnimeList(PREDICATE_SHOW_ALL_ANIME);
        String resultMessage;
        if (getGenresDescriptor().hasUnusedGenres() && getGenresDescriptor().hasUsedGenres()) {
            resultMessage = String.format(MESSAGE_PARTIAL_SUCCESS,
                    getGenresDescriptor().usedGenresString(),
                    getGenresDescriptor().unusedGenresString(),
                    editedAnime);
        } else if (getGenresDescriptor().hasUsedGenres()) {
            resultMessage = String.format(MESSAGE_SUCCESS,
                    getGenresDescriptor().usedGenresString(),
                    editedAnime);
        } else {
            resultMessage = String.format(MESSAGE_GENRE_NOT_PRESENT,
                    getGenresDescriptor().unusedGenresString(),
                    editedAnime);
        }
        return new CommandResult(resultMessage);
    }

    /**
     * Creates and returns an {@code Anime} with the {@code Genre} from {@code genresDescriptor} removed.
     */
    private static Anime createUpdatedAnime(Anime animeToEdit,
                                            GenreCommand.GenresDescriptor genresDescriptor) {
        assert animeToEdit != null;

        Name updatedName = animeToEdit.getName();
        Episode episode = animeToEdit.getEpisode();
        Status status = animeToEdit.getStatus();

        Set<Genre> updatedGenres = new HashSet<>(animeToEdit.getGenres());
        Set<Genre> genresToDelete = genresDescriptor.getGenres().get();
        Set<Genre> deletedGenres = new HashSet<>();
        Set<Genre> unusedGenres = new HashSet<>();

        assert genresToDelete != null;

        for (Genre genreToDelete : genresToDelete) {
            boolean genreIsDeleted = updatedGenres.remove(genreToDelete);
            if (genreIsDeleted) {
                deletedGenres.add(genreToDelete);
            } else {
                unusedGenres.add(genreToDelete);
            }
        }

        genresDescriptor.setUsedGenres(deletedGenres);
        genresDescriptor.setUnusedGenres(unusedGenres);


        return new Anime(updatedName, episode, status, updatedGenres);
    }
}
