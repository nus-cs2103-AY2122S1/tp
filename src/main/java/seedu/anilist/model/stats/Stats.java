package seedu.anilist.model.stats;

import seedu.anilist.model.genre.Genre;

import java.util.HashMap;

public class Stats {
    //stats to be displayed
    private final int watchingCount;
    private final int toWatchCount;
    private final int finishedCount;

    private final int episodesCount;

    private final HashMap<Genre, Integer> topGenres;

    public Stats(int watchingCount, int toWatchCount, int finishedCount, int episodesCount,
                 HashMap<Genre, Integer> genres) {
        this.watchingCount = watchingCount;
        this.toWatchCount = toWatchCount;
        this.finishedCount = finishedCount;
        this.episodesCount = episodesCount;
        this.topGenres = extractTopGenres(genres);
    }

    private HashMap<Genre, Integer> extractTopGenres(HashMap<Genre, Integer> genres) {
        //TODO update to extract
        return genres;
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

    public int getEpisodesCount() {
        return this.episodesCount;
    }

    public HashMap<Genre, Integer> getTopGenres() {
        return this.topGenres;
    }
}
