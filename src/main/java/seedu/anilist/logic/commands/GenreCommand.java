package seedu.anilist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_ACTION;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_GENRE;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.anilist.commons.core.index.Index;
import seedu.anilist.logic.parser.Prefix;
import seedu.anilist.model.genre.Genre;

/**
 * Represents a command that affects the genres of an Anime
 */
public abstract class GenreCommand extends Command {

    public static final String COMMAND_WORD = "genre";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds/Deletes a genre from an Anime. "
            + "Parameters:\n"
            + "INDEX (must be a positive integer) "
            + PREFIX_ACTION + "ACTION "
            + PREFIX_GENRE + "GENRE "
            + "[" + PREFIX_GENRE + "GENRE]\n"
            + "Example of adding Genres: " + COMMAND_WORD + " "
            + "1 "
            + PREFIX_ACTION + "add "
            + PREFIX_GENRE + "Fantasy "
            + PREFIX_GENRE + "Action\n"
            + "Example of deleting Genres: " + COMMAND_WORD + " "
            + "1 "
            + PREFIX_ACTION + "delete "
            + PREFIX_GENRE + "Fantasy "
            + PREFIX_GENRE + "Action";

    public static final Prefix[] REQUIRED_PREFIXES = new Prefix[] {PREFIX_ACTION, PREFIX_GENRE};
    public static final Prefix[] OPTIONAL_PREFIXES = new Prefix[] {};
    public static final boolean REQUIRES_PREAMBLE = true;

    private final GenreCommand.GenresDescriptor genresDescriptor;
    private final Index index;

    /**
     * @param index of the anime in the filtered anime list to edit
     * @param genresDescriptor details to update the anime's episode with
     */
    public GenreCommand(Index index, GenreCommand.GenresDescriptor genresDescriptor) {
        requireNonNull(index);
        requireNonNull(genresDescriptor);

        this.index = index;
        this.genresDescriptor = genresDescriptor;
    }

    public Index getIndex() {
        return index;
    }

    public GenresDescriptor getGenresDescriptor() {
        return genresDescriptor;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GenreCommand // instanceof handles nulls
                && index.equals(((GenreCommand) other).index)
                && genresDescriptor.equals(((GenreCommand) other).genresDescriptor));
    }

    /**
     * Stores the set of genres to be modified.
     */
    public static class GenresDescriptor {
        private Set<Genre> genres;
        private Set<Genre> usedGenres;
        private Set<Genre> unusedGenres;

        public GenresDescriptor() {}

        /**
         * Copy constructor.
         */
        public GenresDescriptor(GenreCommand.GenresDescriptor toCopy) {
            setGenres(toCopy.genres);
        }

        /**
         * Sets {@code genres} to this object's {@code genres}.
         * A defensive copy of {@code genres} is used internally.
         */
        public void setGenres(Set<Genre> genres) {
            this.genres = (genres != null) ? new HashSet<>(genres) : null;
        }

        /**
         * Sets {@code usedGenres} to this object's {@code usedGenres}.
         * A defensive copy of {@code usedGenres} is used internally.
         */
        public void setUsedGenres(Set<Genre> usedGenres) {
            this.usedGenres = (usedGenres != null) ? new HashSet<>(usedGenres) : null;
        }

        /**
         * Sets {@code unusedGenres} to this object's {@code unusedGenres}.
         * A defensive copy of {@code unusedGenres} is used internally.
         */
        public void setUnusedGenres(Set<Genre> unusedGenres) {
            this.unusedGenres = (unusedGenres != null) ? new HashSet<>(unusedGenres) : null;
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
            if (!(other instanceof GenreCommand.GenresDescriptor)) {
                return false;
            }

            // state check
            GenreCommand.GenresDescriptor e = (GenreCommand.GenresDescriptor) other;

            return getGenres().equals(e.getGenres());
        }

        @Override
        public String toString() {
            return genresSetToString(genres);
        }

        /**
         * Gets the {@code Genre}s actually used in the command as a {@code String}
         * @return A string representing the used Genres
         */
        public String usedGenresString() {
            return genresSetToString(usedGenres);
        }

        /**
         * Gets the {@code Genre}s not used in the command as a {@code String}
         * @return A string representing the used Genres
         */
        public String unusedGenresString() {
            return genresSetToString(unusedGenres);
        }

        /**
         * Checks if any {@code Genre} is not used in the command
         * @return true if there are unused Genres, false otherwise
         */
        public boolean hasUnusedGenres() {
            return unusedGenres != null && unusedGenres.size() != 0;
        }

        /**
         * Checks if any {@code Genre} is used in the command
         * @return true if there are used Genres, false otherwise
         */
        public boolean hasUsedGenres() {
            return usedGenres != null && usedGenres.size() != 0;
        }

        /**
         * Converts a {@code Genre} {@code Set} into a {@code String}
         * @param genresSet the set to be converted
         * @return a String listing the items inside the set
         */
        private static String genresSetToString(Set<Genre> genresSet) {
            assert genresSet != null;
            Object[] genreArray = genresSet.toArray();
            Arrays.sort(genreArray);
            String genresString = Arrays.toString(genreArray);
            return genresString.substring(1, genresString.length() - 1);
        }
    }
}
