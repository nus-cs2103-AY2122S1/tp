package seedu.anilist.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.anilist.commons.core.GuiSettings;
import seedu.anilist.model.anime.Anime;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Anime> PREDICATE_SHOW_ALL_ANIME = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAniListFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAnimeList addressBook);

    /** Returns the AddressBook */
    ReadOnlyAnimeList getAniList();

    /**
     * Returns true if a Anime with the same identity as {@code Anime} exists in the address book.
     */
    boolean hasAnime(Anime anime);

    /**
     * Deletes the given Anime.
     * The Anime must exist in the address book.
     */
    void deleteAnime(Anime target);

    /**
     * Adds the given Anime.
     * {@code Anime} must not already exist in the address book.
     */
    void addAnime(Anime anime);

    /**
     * Replaces the given Anime {@code target} with {@code editedAnime}.
     * {@code target} must exist in the address book.
     * The Anime identity of {@code editedAnime} must not be the same as another existing Anime in the address book.
     */
    void setAnime(Anime target, Anime editedAnime);

    /** Returns an unmodifiable view of the filtered Anime list */
    ObservableList<Anime> getFilteredAnimeList();

    /**
     * Updates the filter of the filtered Anime list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredAnimeList(Predicate<Anime> predicate);
}
