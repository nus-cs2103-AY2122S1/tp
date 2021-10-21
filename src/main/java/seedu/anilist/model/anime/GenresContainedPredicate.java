package seedu.anilist.model.anime;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.anilist.logic.parser.ParserUtil;
import seedu.anilist.logic.parser.exceptions.ParseException;
import seedu.anilist.model.genre.Genre;

public class GenresContainedPredicate implements Predicate<Anime> {
    private final List<Genre> genres;

    /**
     * Tests that a {@code Anime}'s {@code Tag} matches any the keywords given.
     */
    public GenresContainedPredicate(List<String> genreNames) throws ParseException {
        genres = new ArrayList<>();
        for (String genreName : genreNames) {
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
