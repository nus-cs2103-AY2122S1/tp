package seedu.anilist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_STATUS;
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
import seedu.anilist.model.anime.Status;
import seedu.anilist.model.tag.Tag;


public class UpdateStatusCommand extends Command {

    public static final String COMMAND_WORD = "status";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Updates the status of the anime identified "
            + "by the index number used in the displayed anime list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_STATUS + "\n "
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_STATUS + "finished";

    public static final String MESSAGE_UPDATE_ANIME_STATUS_SUCCESS = "Updated Anime: %1$s";
    public static final String MESSAGE_NOT_EDITED = "An updated status should be provided.";

    private final Index index;
    private final UpdateStatusCommand.StatusDescriptor statusDescriptor;

    /**
     * @param index of the anime in the filtered anime list to edit
     * @param statusDescriptor details to edit the anime with
     */
    public UpdateStatusCommand(Index index, UpdateStatusCommand.StatusDescriptor statusDescriptor) {
        requireNonNull(index);
        requireNonNull(statusDescriptor);

        this.index = index;
        this.statusDescriptor = new UpdateStatusCommand.StatusDescriptor(statusDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Anime> lastShownList = model.getFilteredAnimeList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ANIME_DISPLAYED_INDEX);
        }

        Anime animeToEdit = lastShownList.get(index.getZeroBased());
        Anime editedAnime = createUpdatedAnime(animeToEdit, statusDescriptor);

        model.setAnime(animeToEdit, editedAnime);
        model.updateFilteredAnimeList(PREDICATE_SHOW_ALL_ANIME);
        return new CommandResult(String.format(MESSAGE_UPDATE_ANIME_STATUS_SUCCESS, editedAnime));
    }

    /**
     * Creates and returns a {@code Anime} with the details of {@code animeToEdit}
     * edited with {@code editAnimeDescriptor}.
     */
    private static Anime createUpdatedAnime(Anime animeToEdit, UpdateStatusCommand.StatusDescriptor statusDescriptor) {
        assert animeToEdit != null;

        Name name = animeToEdit.getName();
        Episode episode = animeToEdit.getEpisode();
        Status updatedStatus = statusDescriptor.getStatus().orElse(animeToEdit.getStatus());
        Set<Tag> tags = animeToEdit.getTags();

        return new Anime(name, episode, updatedStatus, tags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UpdateStatusCommand)) {
            return false;
        }

        // state check
        UpdateStatusCommand e = (UpdateStatusCommand) other;
        return index.equals(e.index)
                && statusDescriptor.equals(e.statusDescriptor);
    }

    /**
     * Stores the details to edit the anime with. Each non-empty field value will replace the
     * corresponding field value of the anime.
     */
    public static class StatusDescriptor {
        private Status status;

        public StatusDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public StatusDescriptor(UpdateStatusCommand.StatusDescriptor toCopy) {
            setStatus(toCopy.status);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isStatusEdited() {
            return status != null;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public Optional<Status> getStatus() {
            return Optional.ofNullable(status);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof UpdateStatusCommand.StatusDescriptor)) {
                return false;
            }

            // state check
            UpdateStatusCommand.StatusDescriptor e = (UpdateStatusCommand.StatusDescriptor) other;

            return getStatus().equals(e.getStatus());
        }
    }
}
