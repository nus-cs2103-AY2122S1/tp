package seedu.anilist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.anilist.model.Model.PREDICATE_SHOW_ALL_ANIME;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.anilist.commons.core.Messages;
import seedu.anilist.commons.core.index.Index;
import seedu.anilist.commons.util.CollectionUtil;
import seedu.anilist.logic.commands.exceptions.CommandException;
import seedu.anilist.model.Model;
import seedu.anilist.model.anime.Anime;
import seedu.anilist.model.anime.Episode;
import seedu.anilist.model.anime.Name;
import seedu.anilist.model.anime.Status;
import seedu.anilist.model.tag.Tag;

/**
 * Edits the details of an existing anime in the anime list.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the anime identified "
            + "by the index number used in the displayed anime list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_EDIT_ANIME_SUCCESS = "Edited Anime: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ANIME = "This anime already exists in the anime list.";

    public final Index index;
    public final EditAnimeDescriptor editAnimeDescriptor;

    /**
     * @param index of the anime in the filtered anime list to edit
     * @param editAnimeDescriptor details to edit the anime with
     */
    public EditCommand(Index index, EditAnimeDescriptor editAnimeDescriptor) {
        requireNonNull(index);
        requireNonNull(editAnimeDescriptor);

        this.index = index;
        this.editAnimeDescriptor = new EditAnimeDescriptor(editAnimeDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Anime> lastShownList = model.getFilteredAnimeList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ANIME_DISPLAYED_INDEX);
        }

        Anime animeToEdit = lastShownList.get(index.getZeroBased());
        Anime editedAnime = createEditedAnime(animeToEdit, editAnimeDescriptor);

        if (!animeToEdit.isSameAnime(editedAnime) && model.hasAnime(editedAnime)) {
            throw new CommandException(MESSAGE_DUPLICATE_ANIME);
        }

        model.setAnime(animeToEdit, editedAnime);
        model.updateFilteredAnimeList(PREDICATE_SHOW_ALL_ANIME);
        return new CommandResult(String.format(MESSAGE_EDIT_ANIME_SUCCESS, editedAnime));
    }

    /**
     * Creates and returns a {@code Anime} with the details of {@code animeToEdit}
     * edited with {@code editAnimeDescriptor}.
     */
    private static Anime createEditedAnime(Anime animeToEdit, EditAnimeDescriptor editAnimeDescriptor) {
        assert animeToEdit != null;

        Name updatedName = editAnimeDescriptor.getName().orElse(animeToEdit.getName());
        Episode episode = animeToEdit.getEpisode();
        Status status = animeToEdit.getStatus();
        Set<Tag> updatedTags = editAnimeDescriptor.getTags().orElse(animeToEdit.getTags());

        return new Anime(updatedName, episode, status, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editAnimeDescriptor.equals(e.editAnimeDescriptor);
    }

    /**
     * Stores the details to edit the anime with. Each non-empty field value will replace the
     * corresponding field value of the anime.
     */
    public static class EditAnimeDescriptor {
        private Name name;
        private Episode episode;
        private Status status;
        private Set<Tag> tags;

        public EditAnimeDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditAnimeDescriptor(EditAnimeDescriptor toCopy) {
            setName(toCopy.name);
            setEpisode(toCopy.episode);
            setStatus(toCopy.status);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, episode, status, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        public void setEpisode(Episode e) {
            this.episode = e;
        }

        public Optional<Episode> getEpisode() {
            return Optional.ofNullable(episode);
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
            if (!(other instanceof EditAnimeDescriptor)) {
                return false;
            }

            // state check
            EditAnimeDescriptor e = (EditAnimeDescriptor) other;

            return getName().equals(e.getName())
                    && getTags().equals(e.getTags());
        }
    }
}
