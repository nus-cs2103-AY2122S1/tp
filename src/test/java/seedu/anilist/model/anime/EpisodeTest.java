package seedu.anilist.model.anime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.anilist.logic.commands.CommandTestUtil.INVALID_EPISODE_ALPHA;
import static seedu.anilist.logic.commands.CommandTestUtil.INVALID_EPISODE_DECIMAL;
import static seedu.anilist.logic.commands.CommandTestUtil.INVALID_EPISODE_LARGER_THAN_MAX_EPISODE;
import static seedu.anilist.logic.commands.CommandTestUtil.INVALID_EPISODE_LARGER_THAN_MAX_INT;
import static seedu.anilist.logic.commands.CommandTestUtil.INVALID_EPISODE_NEG;
import static seedu.anilist.logic.commands.CommandTestUtil.INVALID_STRING_EMPTY;
import static seedu.anilist.logic.commands.CommandTestUtil.INVALID_STRING_NON_ASCII;
import static seedu.anilist.logic.commands.CommandTestUtil.INVALID_STRING_SPACE;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_EPISODE_MAX;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_EPISODE_ONE;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_EPISODE_TWO;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_EPISODE_ZERO;
import static seedu.anilist.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EpisodeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Episode(null));
    }

    @Test
    public void constructor_invalidEpisode_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Episode(INVALID_STRING_SPACE));
        assertThrows(IllegalArgumentException.class, () -> new Episode(INVALID_STRING_EMPTY));
        assertThrows(IllegalArgumentException.class, () -> new Episode(INVALID_STRING_NON_ASCII));
        assertThrows(IllegalArgumentException.class, () -> new Episode(INVALID_EPISODE_ALPHA));
        assertThrows(IllegalArgumentException.class, () -> new Episode(INVALID_EPISODE_DECIMAL));
        assertThrows(IllegalArgumentException.class, () -> new Episode(INVALID_EPISODE_NEG));
        assertThrows(IllegalArgumentException.class, () -> new Episode(INVALID_EPISODE_LARGER_THAN_MAX_EPISODE));
        assertThrows(IllegalArgumentException.class, () -> new Episode(INVALID_EPISODE_LARGER_THAN_MAX_INT));
    }

    @Test
    public void isValidEpisode() {
        // null episode
        assertThrows(NullPointerException.class, () -> Episode.isValidEpisode(null));

        // invalid episode
        assertFalse(Episode.isValidEpisode(INVALID_STRING_SPACE));
        assertFalse(Episode.isValidEpisode(INVALID_STRING_EMPTY));
        assertFalse(Episode.isValidEpisode(INVALID_STRING_NON_ASCII));
        assertFalse(Episode.isValidEpisode(INVALID_EPISODE_ALPHA));
        assertFalse(Episode.isValidEpisode(INVALID_EPISODE_DECIMAL));
        assertFalse(Episode.isValidEpisode(INVALID_EPISODE_NEG));
        assertFalse(Episode.isValidEpisode(INVALID_EPISODE_LARGER_THAN_MAX_EPISODE));
        assertFalse(Episode.isValidEpisode(INVALID_EPISODE_LARGER_THAN_MAX_INT));

        // valid episodes
        assertTrue(Episode.isValidEpisode(VALID_EPISODE_ZERO));
        assertTrue(Episode.isValidEpisode(VALID_EPISODE_ONE));
        assertTrue(Episode.isValidEpisode(VALID_EPISODE_TWO));
        assertTrue(Episode.isValidEpisode(VALID_EPISODE_MAX));

    }
}
