package seedu.anilist.testutil;

import static seedu.anilist.logic.parser.CliSyntax.PREFIX_EPISODE;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.Set;

import seedu.anilist.logic.commands.AddCommand;
import seedu.anilist.logic.commands.GenreCommand;
import seedu.anilist.model.anime.Anime;
import seedu.anilist.model.genre.Genre;

/**
 * A utility class for Anime.
 */
public class AnimeUtil {

    /**
     * Returns an add command string for adding the {@code anime}.
     */
    public static String getAddCommand(Anime anime) {
        return AddCommand.COMMAND_WORD + " " + getAnimeDetails(anime);
    }

    /**
     * Returns the part of command string for the given {@code anime}'s details.
     */
    public static String getAnimeDetails(Anime anime) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + anime.getName().fullName + " ");
        sb.append(PREFIX_EPISODE + anime.getEpisode().toString() + " ");
        sb.append(PREFIX_STATUS + anime.getStatus().toString() + " ");
        anime.getGenres().stream().forEach(
            s -> sb.append(PREFIX_GENRE + s.genreName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code GenresDescriptor}'s details.
     */
    public static String getGenreDescriptorDetails(GenreCommand.GenresDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        Set<Genre> genres = descriptor.getGenres().get();
        if (genres.isEmpty()) {
            sb.append(PREFIX_GENRE);
        } else {
            genres.forEach(s -> sb.append(PREFIX_GENRE).append(s.genreName).append(" "));
        }

        return sb.toString();
    }
}
