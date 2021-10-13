package seedu.anilist.model.genre;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.anilist.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GenreTest {

    private static final String INVALID_GENRE_NON_ALPHANUMERICAL = "@#$%^&";
    private static final String INVALID_GENRE_START_WITH_SPACE = " bad";
    private static final String INVALID_GENRE_WITH_DOUBLE_SPACE = "three  word  genres";

    private static final String VALID_GENRE_ONE_WORD = "OneWordGenre";
    private static final String VALID_GENRE_MULTIPLE_WORDS = "Multiple Word Genres";
    private static final String VALID_GENRE_WITH_NUMBERS = "123fant123asy123";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Genre(null));
    }

    @Test
    public void constructor_invalidGenreName_throwsIllegalArgumentException() {
        String invalidGenreName = "";
        assertThrows(IllegalArgumentException.class, () -> new Genre(invalidGenreName));
    }

    @Test
    public void isValidGenreName() {
        // null genre name
        assertThrows(NullPointerException.class, () -> Genre.isValidGenreName(null));

        // invalid genres
        // genre containing non-alphanumerical characters
        assertFalse(Genre.isValidGenreName(INVALID_GENRE_NON_ALPHANUMERICAL));
        // genre starting with space
        assertFalse(Genre.isValidGenreName(INVALID_GENRE_START_WITH_SPACE));
        // genre with double spaces
        assertFalse(Genre.isValidGenreName(INVALID_GENRE_WITH_DOUBLE_SPACE));

        // valid genres
        // genre with one word
        assertTrue(Genre.isValidGenreName(VALID_GENRE_ONE_WORD));
        // genre with multiple words
        assertTrue(Genre.isValidGenreName(VALID_GENRE_MULTIPLE_WORDS));
        // genre with numbers
        assertTrue(Genre.isValidGenreName(VALID_GENRE_WITH_NUMBERS));
    }
}
