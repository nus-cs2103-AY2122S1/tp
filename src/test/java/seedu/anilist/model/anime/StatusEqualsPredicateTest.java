package seedu.anilist.model.anime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_STATUS_FINISHED;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_STATUS_TOWATCH;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_STATUS_WATCHING;

import org.junit.jupiter.api.Test;

import seedu.anilist.testutil.AnimeBuilder;

public class StatusEqualsPredicateTest {
    @Test
    public void equals() {
        Status firstStatus = new Status(VALID_STATUS_TOWATCH);
        Status secondStatus = new Status(VALID_STATUS_FINISHED);
        StatusEqualsPredicate firstPredicate = new StatusEqualsPredicate(firstStatus);
        StatusEqualsPredicate secondPredicate = new StatusEqualsPredicate(secondStatus);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        StatusEqualsPredicate firstPredicateCopy = new StatusEqualsPredicate(firstStatus);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertNotEquals(1, firstPredicate);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different status -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_statusMatches_returnsTrue() {
        Anime animeWatching = new AnimeBuilder().withStatus(VALID_STATUS_FINISHED).build();
        StatusEqualsPredicate predicateWatching = new StatusEqualsPredicate(new Status(VALID_STATUS_FINISHED));

        assertTrue(predicateWatching.test(animeWatching));
    }

    @Test
    public void test_statusDoNotMatch_returnsFalse() {
        Anime animeWatching = new AnimeBuilder().withStatus(VALID_STATUS_WATCHING).build();
        StatusEqualsPredicate predicateToWatch = new StatusEqualsPredicate(new Status(VALID_STATUS_TOWATCH));

        assertFalse(predicateToWatch.test(animeWatching));
    }
}
