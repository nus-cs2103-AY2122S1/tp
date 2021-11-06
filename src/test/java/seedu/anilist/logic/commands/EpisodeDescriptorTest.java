package seedu.anilist.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.anilist.logic.commands.CommandTestUtil.DESC_EPISODE_ONE;
import static seedu.anilist.logic.commands.CommandTestUtil.DESC_EPISODE_ZERO;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_EPISODE_TWO;

import org.junit.jupiter.api.Test;

import seedu.anilist.testutil.EpisodeDescriptorBuilder;

public class EpisodeDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        UpdateEpisodeCommand.EpisodeDescriptor descriptorWithSameValues =
            new UpdateEpisodeCommand.EpisodeDescriptor(DESC_EPISODE_ONE);
        assertEquals(DESC_EPISODE_ONE, descriptorWithSameValues);

        // same object -> returns true
        assertEquals(DESC_EPISODE_ONE, DESC_EPISODE_ONE);

        // null -> returns false
        assertNotEquals(null, DESC_EPISODE_ONE);

        // different types -> returns false
        assertNotEquals(5, DESC_EPISODE_ONE);

        // different values -> returns false
        assertNotEquals(DESC_EPISODE_ONE, DESC_EPISODE_ZERO);

        // different episode -> returns false
        UpdateEpisodeCommand.EpisodeDescriptor editedEpisodeDesc =
            new EpisodeDescriptorBuilder(DESC_EPISODE_ONE).withEpisode(VALID_EPISODE_TWO).build();
        assertNotEquals(DESC_EPISODE_ONE, editedEpisodeDesc);
    }
}
