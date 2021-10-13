package seedu.anilist.testutil;

import seedu.anilist.logic.commands.UpdateEpisodeCommand;
import seedu.anilist.model.anime.Episode;

public class EpisodeDescriptorBuilder {

    /**
     * A utility class to help with building EpisodeDescriptorBuilder objects.
     */

    private UpdateEpisodeCommand.EpisodeDescriptor descriptor;

    public EpisodeDescriptorBuilder() {
        descriptor = new UpdateEpisodeCommand.EpisodeDescriptor();
    }

    public EpisodeDescriptorBuilder(UpdateEpisodeCommand.EpisodeDescriptor descriptor) {
        this.descriptor = new UpdateEpisodeCommand.EpisodeDescriptor(descriptor);
    }

    /**
     * Sets the {@code Name} of the {@code EpisodeDescriptor} that we are building.
     */
    public seedu.anilist.testutil.EpisodeDescriptorBuilder withEpisode(String episodeNumber) {
        descriptor.setEpisode(new Episode(episodeNumber));
        return this;
    }

    public UpdateEpisodeCommand.EpisodeDescriptor build() {
        return descriptor;
    }

}
