package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.anime.Anime;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AnimeList animeList;
    private final UserPrefs userPrefs;
    private final FilteredList<Anime> filteredAnime;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAnimeList addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.animeList = new AnimeList(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredAnime = new FilteredList<>(this.animeList.getAnimeList());
    }

    public ModelManager() {
        this(new AnimeList(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAniListFilePath() {
        return userPrefs.getAniListFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAniListFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAnimeList addressBook) {
        this.animeList.resetData(addressBook);
    }

    @Override
    public ReadOnlyAnimeList getAniList() {
        return animeList;
    }

    @Override
    public boolean hasAnime(Anime anime) {
        requireNonNull(anime);
        return animeList.hasAnime(anime);
    }

    @Override
    public void deleteAnime(Anime target) {
        animeList.removeAnime(target);
    }

    @Override
    public void addAnime(Anime anime) {
        animeList.addAnime(anime);
        updateFilteredAnimeList(PREDICATE_SHOW_ALL_ANIME);
    }

    @Override
    public void setAnime(Anime target, Anime editedAnime) {
        requireAllNonNull(target, editedAnime);

        animeList.setAnime(target, editedAnime);
    }

    //=========== Filtered Anime List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Anime} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Anime> getFilteredAnimeList() {
        return filteredAnime;
    }

    @Override
    public void updateFilteredAnimeList(Predicate<Anime> predicate) {
        requireNonNull(predicate);
        filteredAnime.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return animeList.equals(other.animeList)
                && userPrefs.equals(other.userPrefs)
                && filteredAnime.equals(other.filteredAnime);
    }

}
