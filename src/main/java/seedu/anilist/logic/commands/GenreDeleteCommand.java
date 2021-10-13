package seedu.anilist.logic.commands;

import seedu.anilist.commons.core.Messages;
import seedu.anilist.commons.core.index.Index;
import seedu.anilist.logic.commands.exceptions.CommandException;
import seedu.anilist.model.Model;
import seedu.anilist.model.anime.Anime;
import seedu.anilist.model.anime.Episode;
import seedu.anilist.model.anime.Name;
import seedu.anilist.model.genre.Genre;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.model.Model.PREDICATE_SHOW_ALL_ANIME;

public class GenreDeleteCommand extends GenreCommand {

    public static final String MESSAGE_SUCCESS = "Genres %1$s deleted from anime %2$s";

    public GenreDeleteCommand(Index index, GenreCommand.GenresDescriptor genresDescriptor) {
        super(index, genresDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Anime> lastShownList = model.getFilteredAnimeList();

        if (this.getIndex().getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ANIME_DISPLAYED_INDEX);
        }

        Anime animeToEdit = lastShownList.get(getIndex().getZeroBased());
        Anime editedAnime = createUpdatedAnime(animeToEdit, getGenresDescriptor());

        model.setAnime(animeToEdit, editedAnime);
        model.updateFilteredAnimeList(PREDICATE_SHOW_ALL_ANIME);
        return new CommandResult(String.format(MESSAGE_SUCCESS, getGenresDescriptor().toString(), editedAnime));
    }

    private static Anime createUpdatedAnime(Anime animeToEdit,
                                            GenreCommand.GenresDescriptor genresDescriptor) {
        assert animeToEdit != null;

        Name updatedName = animeToEdit.getName();
        Episode episode = animeToEdit.getEpisode();

        Set<Genre> updatedGenres = new HashSet<>(animeToEdit.getGenres());
        Set<Genre> genresToDelete = genresDescriptor.getGenres().get();

        assert genresToDelete != null;

        updatedGenres.removeAll(genresToDelete);


        return new Anime(updatedName, episode, updatedGenres);
    }
}
