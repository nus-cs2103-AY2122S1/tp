package seedu.anilist.model.anime;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.anilist.logic.parser.ParserUtil;
import seedu.anilist.logic.parser.exceptions.ParseException;
import seedu.anilist.model.genre.Genre;

/**
 * Tests that a {@code Anime}'s {@code Tag} matches any the keywords given.
 */
public class GenresContainedPredicate implements Predicate<Anime> {
    private final List<Genre> genres;

    /**
     * Constructs a {@code GenresContainedPredicate}.
     *
     * @param genreKeywords Keywords to search within the anime's genre.
     * @throws ParseException if the keywords given are not valid.
     */
    public GenresContainedPredicate(List<String> genreKeywords) throws ParseException {
        genres = new ArrayList<>();
        for (String genreName : genreKeywords) {
            genres.add(ParserUtil.parseGenre(genreName));
        }
    }

    @Override
    public boolean test(Anime anime) {
        return genres.stream()
            .anyMatch(genre -> anime.getGenres().contains(genre));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof GenresContainedPredicate // instanceof handles nulls
            && genres.equals(((GenresContainedPredicate) other).genres)); // state check
    }
}
