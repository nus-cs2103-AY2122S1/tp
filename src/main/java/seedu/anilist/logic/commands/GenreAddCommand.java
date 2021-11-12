package seedu.anilist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.model.Model.PREDICATE_SHOW_ALL_ANIME;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
 * Adds the provided genres to the specified anime.
 */
public class GenreAddCommand extends GenreCommand {

    public static final String MESSAGE_SUCCESS = "Genre(s) %1$s added to anime.\n"
            + "%2$s";
    public static final String MESSAGE_GENRE_PRESENT = "Genre(s) %1$s are already present in anime.\n"
            + "%2$s";
    public static final String MESSAGE_PARTIAL_SUCCESS = "Genre(s) %1$s added.\n"
            + "Genre(s) %2$s are already present in anime.\n"
            + "%3$s";

    public GenreAddCommand(Index index, GenreCommand.GenresDescriptor genresDescriptor) {
        super(index, genresDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Anime> lastShownList = model.getFilteredAnimeList();

        Index index = getIndex();
        GenresDescriptor genresDescriptor = getGenresDescriptor();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ANIME_DISPLAYED_INDEX);
        }

        Anime animeToEdit = lastShownList.get(index.getZeroBased());
        Anime editedAnime = createUpdatedAnime(animeToEdit, genresDescriptor);

        model.setAnime(animeToEdit, editedAnime);
        model.updateFilteredAnimeList(PREDICATE_SHOW_ALL_ANIME);

        String resultMessage;
        if (genresDescriptor.hasUnusedGenres() && genresDescriptor.hasUsedGenres()) {
            resultMessage = String.format(MESSAGE_PARTIAL_SUCCESS,
                    genresDescriptor.usedGenresString(),
                    genresDescriptor.unusedGenresString(),
                    editedAnime);
        } else if (genresDescriptor.hasUsedGenres()) {
            resultMessage = String.format(MESSAGE_SUCCESS,
                    genresDescriptor.usedGenresString(),
                    editedAnime);
        } else {
            resultMessage = String.format(MESSAGE_GENRE_PRESENT,
                    genresDescriptor.unusedGenresString(),
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
        Optional<Set<Genre>> descriptorGenre = genresDescriptor.getGenres();
        assert descriptorGenre.isPresent();
        Set<Genre> genresToAdd = descriptorGenre.get();
        Set<Genre> addedGenres = new HashSet<>();
        Set<Genre> unusedGenres = new HashSet<>();

        assert genresToAdd != null;

        for (Genre genreToAdd : genresToAdd) {
            boolean isGenreAdded = updatedGenres.add(genreToAdd);
            if (isGenreAdded) {
                addedGenres.add(genreToAdd);
            } else {
                unusedGenres.add(genreToAdd);
            }
        }

        genresDescriptor.setUsedGenres(addedGenres);
        genresDescriptor.setUnusedGenres(unusedGenres);

        return new Anime(updatedName, episode, status, updatedGenres);
    }
}
