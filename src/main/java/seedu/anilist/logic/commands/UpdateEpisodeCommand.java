package seedu.anilist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_EPISODE;
import static seedu.anilist.model.Model.PREDICATE_SHOW_ALL_ANIME;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.anilist.commons.core.Messages;
import seedu.anilist.commons.core.index.Index;
import seedu.anilist.logic.commands.exceptions.CommandException;
import seedu.anilist.model.Model;
import seedu.anilist.model.anime.Anime;
import seedu.anilist.model.anime.Episode;
import seedu.anilist.model.anime.Name;
import seedu.anilist.model.tag.Tag;

public class UpdateEpisodeCommand extends Command {
    public static final String COMMAND_WORD = "update";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Updates the episode number of the anime identified "
        + "by the index number used in the displayed anime list. "
        + "Existing values will be overwritten by the input values.\n"
        + "Parameters: INDEX (must be a positive integer) "
        + PREFIX_EPISODE + "EPISODE\n"
        + "Example: " + COMMAND_WORD + " 1 " + PREFIX_EPISODE + "1";

    public static final String MESSAGE_UPDATE_ANIME_EPISODE_SUCCESS = "Updated Anime episode: %1$s";
    public static final String MESSAGE_NOT_UPDATED = "An episode number should be provided";

    private final Index index;
    private final UpdateEpisodeCommand.EpisodeDescriptor episodeDescriptor;

    /**
     * @param index of the anime in the filtered anime list to edit
     * @param episodeDescriptor details to update the anime's episode with
     */
    public UpdateEpisodeCommand(Index index, UpdateEpisodeCommand.EpisodeDescriptor episodeDescriptor) {
        requireNonNull(index);
        requireNonNull(episodeDescriptor);

        this.index = index;
        this.episodeDescriptor = new UpdateEpisodeCommand.EpisodeDescriptor(episodeDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Anime> lastShownList = model.getFilteredAnimeList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ANIME_DISPLAYED_INDEX);
        }

        Anime animeToEdit = lastShownList.get(index.getZeroBased());
        Anime editedAnime = createUpdatedAnime(animeToEdit, episodeDescriptor);

        model.setAnime(animeToEdit, editedAnime);
        model.updateFilteredAnimeList(PREDICATE_SHOW_ALL_ANIME);
        return new CommandResult(String.format(MESSAGE_UPDATE_ANIME_EPISODE_SUCCESS, editedAnime));
    }

    /**
     * Creates and returns an {@code Anime} with the episode number of {@code animeToEdit}
     * edited with {@code episodeDescriptor}.
     */
    private static Anime createUpdatedAnime(Anime animeToEdit,
                                            UpdateEpisodeCommand.EpisodeDescriptor episodeDescriptor) {
        assert animeToEdit != null;

        Name updatedName = animeToEdit.getName();
        Episode episode = episodeDescriptor.getEpisode().orElse(animeToEdit.getEpisode());
        Set<Tag> updatedTags = animeToEdit.getTags();

        return new Anime(updatedName, episode, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UpdateEpisodeCommand)) {
            return false;
        }

        // state check
        UpdateEpisodeCommand e = (UpdateEpisodeCommand) other;
        return index.equals(e.index)
            && episodeDescriptor.equals(e.episodeDescriptor);
    }

    /**
     * Stores the episode to update the anime episode with.
     */
    public static class EpisodeDescriptor {
        private Episode episode;

        public EpisodeDescriptor() {}

        /**
         * Copy constructor.
         */
        public EpisodeDescriptor(UpdateEpisodeCommand.EpisodeDescriptor toCopy) {
            setEpisode(toCopy.episode);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isEpisodeUpdated() {
            return episode != null;
        }


        public void setEpisode(Episode e) {
            this.episode = e;
        }

        public Optional<Episode> getEpisode() {
            return Optional.ofNullable(episode);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof UpdateEpisodeCommand.EpisodeDescriptor)) {
                return false;
            }

            // state check
            UpdateEpisodeCommand.EpisodeDescriptor e = (UpdateEpisodeCommand.EpisodeDescriptor) other;

            return getEpisode().equals(e.getEpisode());
        }
    }
}
