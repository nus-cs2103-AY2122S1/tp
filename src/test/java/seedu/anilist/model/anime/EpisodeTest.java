package seedu.anilist.model.anime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.anilist.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EpisodeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Episode(null));
    }

    @Test
    public void constructor_invalidEpisode_throwsIllegalArgumentException() {
        String invalidEpisode = "";
        assertThrows(IllegalArgumentException.class, () -> new Episode(invalidEpisode));
    }

    @Test
    public void isValidEpisode() {
        // null name
        assertThrows(NullPointerException.class, () -> Episode.isValidEpisode(null));

        // invalid name
        assertFalse(Episode.isValidEpisode("")); // empty string
        assertFalse(Episode.isValidEpisode(" ")); // spaces only
        assertFalse(Episode.isValidEpisode("^")); // only non-numeric characters
        assertFalse(Episode.isValidEpisode("^123%")); // contains non-numeric characters
        assertFalse(Episode.isValidEpisode("-1")); // negative numbers
        assertFalse(Episode.isValidEpisode("3.0")); // float numbers

        // valid name
        assertTrue(Episode.isValidEpisode("12345")); // numbers only
        assertTrue(Episode.isValidEpisode("0000")); // leading zeroes
    }
}
