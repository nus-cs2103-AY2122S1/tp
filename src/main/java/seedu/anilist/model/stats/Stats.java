package seedu.anilist.model.stats;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import seedu.anilist.model.genre.Genre;

public class Stats {
    private static final int genreCountLimit = 6;

    //stats to be displayed
    private final int watchingCount;
    private final int toWatchCount;
    private final int finishedCount;

    private final int numUniqueGenres;

    private final HashMap<Genre, Integer> topGenres;

    /**
     * Creates a Stats object containing the user statistics.
     */
    public Stats(int watchingCount, int toWatchCount, int finishedCount,
                 HashMap<Genre, Integer> genres) {
        this.watchingCount = watchingCount;
        this.toWatchCount = toWatchCount;
        this.finishedCount = finishedCount;
        this.topGenres = extractTopGenres(genres);
        this.numUniqueGenres = genres.size();
    }

    public static int getGenreCountLimit() {
        return genreCountLimit;
    }

    private HashMap<Genre, Integer> extractTopGenres(HashMap<Genre, Integer> genres) {
        Comparator<Entry<Genre, Integer>> genreCountComparator = new Comparator<>() {
            @Override
            public int compare(Entry<Genre, Integer> e1, Entry<Genre, Integer> e2) {
                int count1 = e1.getValue();
                int count2 = e2.getValue();
                return count2 - count1;
            }
        };

        //sorts genres input by value i.e. count
        List<Entry<Genre, Integer>> listOfEntries = new ArrayList<>(genres.entrySet());
        listOfEntries.sort(genreCountComparator);

        //number of items to be included in the stats display is the lower
        //of the genreCountLimit and the number of unique genres tagged to animes
        int numItems = Math.min(genreCountLimit, genres.size());
        LinkedHashMap<Genre, Integer> topGenresSorted = new LinkedHashMap<>(numItems);

        for (int i = numItems - 1; i >= 0; i--) {
            Entry<Genre, Integer> e = listOfEntries.get(i);
            topGenresSorted.put(e.getKey(), e.getValue());
        }
        return topGenresSorted;
    }

    public int getTotalAnimesCount() {
        return this.watchingCount + this.toWatchCount + this.finishedCount;
    }

    public int getWatchingCount() {
        return this.watchingCount;
    }

    public int getToWatchCount() {
        return this.toWatchCount;
    }

    public int getFinishedCount() {
        return this.finishedCount;
    }

    public int getNumUniqueGenres() {
        return this.numUniqueGenres;
    }

    public HashMap<Genre, Integer> getTopGenres() {
        return this.topGenres;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { // short circuit if same object
            return true;
        }

        Stats otherStats = (Stats) other;
        assert otherStats != null;
        return this.watchingCount == otherStats.watchingCount
                && this.toWatchCount == otherStats.toWatchCount
                && this.finishedCount == otherStats.finishedCount
                && this.numUniqueGenres == otherStats.numUniqueGenres
                && this.topGenres.equals(otherStats.topGenres);
    }
}
