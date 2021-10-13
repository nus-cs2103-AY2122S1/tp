package seedu.anilist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_NAME;
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
import seedu.anilist.model.anime.Name;
import seedu.anilist.model.genre.Genre;

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
            + "[" + PREFIX_GENRE + "GENRE]...\n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_EDIT_ANIME_SUCCESS = "Edited Anime: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ANIME = "This anime already exists in the anime list.";

    private final Index index;
    private final EditAnimeDescriptor editAnimeDescriptor;

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
        Set<Genre> updatedGenres = editAnimeDescriptor.getGenres().orElse(animeToEdit.getGenres());

        return new Anime(updatedName, updatedGenres);
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
        private Set<Genre> genres;

        public EditAnimeDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code genres} is used internally.
         */
        public EditAnimeDescriptor(EditAnimeDescriptor toCopy) {
            setName(toCopy.name);
            setGenres(toCopy.genres);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, genres);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        /**
         * Sets {@code genres} to this object's {@code genres}.
         * A defensive copy of {@code genres} is used internally.
         */
        public void setGenres(Set<Genre> genres) {
            this.genres = (genres != null) ? new HashSet<>(genres) : null;
        }

        /**
         * Returns an unmodifiable genre set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code genres} is null.
         */
        public Optional<Set<Genre>> getGenres() {
            return (genres != null) ? Optional.of(Collections.unmodifiableSet(genres)) : Optional.empty();
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
                    && getGenres().equals(e.getGenres());
        }
    }
}
