package seedu.anilist.model.anime;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

public class StatusEqualsPredicate implements Predicate<Anime> {
    private final Status status;

    public StatusEqualsPredicate(Status status) {
        requireNonNull(status);
        this.status = status;
    }

    @Override
    public boolean test(Anime anime) {
        return anime.getStatus().equals(status);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StatusEqualsPredicate // instanceof handles nulls
                && status.equals(((StatusEqualsPredicate) other).status)); // state check
    }
}
