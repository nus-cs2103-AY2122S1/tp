package seedu.anilist.model;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.anilist.commons.core.GuiSettings;
import seedu.anilist.commons.core.LogsCenter;
import seedu.anilist.model.anime.Anime;

/**
 * Represents the in-memory model of the anime list data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AnimeList animeList;
    private final UserPrefs userPrefs;
    private final FilteredList<Anime> filteredAnime;

    /**
     * Initializes a ModelManager with the given animeList and userPrefs.
     */
    public ModelManager(ReadOnlyAnimeList animeList, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(animeList, userPrefs);

        logger.fine("Initializing with anime list: " + animeList + " and user prefs " + userPrefs);

        this.animeList = new AnimeList(animeList);
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
    public Path getAnimeListFilePath() {
        return userPrefs.getAnimeListFilePath();
    }

    @Override
    public void setAnimeListFilePath(Path animeListFilePath) {
        requireNonNull(animeListFilePath);
        userPrefs.setAnimeListFilePath(animeListFilePath);
    }

    //=========== AnimeList ================================================================================

    @Override
    public void setAnimeList(ReadOnlyAnimeList animeList) {
        this.animeList.resetData(animeList);
    }

    @Override
    public ReadOnlyAnimeList getAnimeList() {
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
     * {@code versionedAnimeList}
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
