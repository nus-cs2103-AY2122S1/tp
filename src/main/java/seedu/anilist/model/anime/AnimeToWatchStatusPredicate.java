package seedu.anilist.model.anime;

import java.util.function.Predicate;

public class AnimeToWatchStatusPredicate implements Predicate<Anime> {
    @Override
    public boolean test(Anime anime) {
        return anime.getStatus().equals(new Status("towatch"));
    }
}
