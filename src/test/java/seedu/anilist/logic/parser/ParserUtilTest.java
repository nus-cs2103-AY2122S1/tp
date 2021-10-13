package seedu.anilist.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.anilist.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.anilist.testutil.Assert.assertThrows;
import static seedu.anilist.testutil.TypicalIndexes.INDEX_FIRST_ANIME;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.anilist.logic.parser.exceptions.ParseException;
import seedu.anilist.model.anime.Name;
import seedu.anilist.model.genre.Genre;

public class ParserUtilTest {
    private static final String INVALID_NAME = " ";
    private static final String INVALID_GENRE = "#adventure";


    private static final String VALID_NAME = "PSYCHO-PASS";
    private static final String VALID_GENRE_1 = "cyberpunk";
    private static final String VALID_GENRE_2 = "psychological thriller";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_ANIME, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_ANIME, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parseGenre_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGenre(null));
    }

    @Test
    public void parseGenre_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGenre(INVALID_GENRE));
    }

    @Test
    public void parseGenre_validValueWithoutWhitespace_returnsGenre() throws Exception {
        Genre expectedGenre = new Genre(VALID_GENRE_1);
        assertEquals(expectedGenre, ParserUtil.parseGenre(VALID_GENRE_1));
    }

    @Test
    public void parseGenre_validValueWithWhitespace_returnsTrimmedGenre() throws Exception {
        String genreWithWhitespace = WHITESPACE + VALID_GENRE_1 + WHITESPACE;
        Genre expectedGenre = new Genre(VALID_GENRE_1);
        assertEquals(expectedGenre, ParserUtil.parseGenre(genreWithWhitespace));
    }

    @Test
    public void parseGenres_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGenres(null));
    }

    @Test
    public void parseGenres_collectionWithInvalidGenres_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGenres(Arrays.asList(VALID_GENRE_1, INVALID_GENRE)));
    }

    @Test
    public void parseGenres_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseGenres(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseGenres_collectionWithValidGenres_returnsGenreSet() throws Exception {
        Set<Genre> actualGenreSet = ParserUtil.parseGenres(Arrays.asList(VALID_GENRE_1, VALID_GENRE_2));
        Set<Genre> expectedGenreSet = new HashSet<Genre>(
                Arrays.asList(new Genre(VALID_GENRE_1), new Genre(VALID_GENRE_2))
        );

        assertEquals(expectedGenreSet, actualGenreSet);
    }
}
