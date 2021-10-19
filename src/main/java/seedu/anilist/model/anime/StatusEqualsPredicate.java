package seedu.anilist.model.anime;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

/**
 * Tests that a {@code Anime}'s {@code Status} matches the status provided.
 */
public class StatusEqualsPredicate implements Predicate<Anime> {
    private final Status status;

    /**
     * @param status to be matched.
     */
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

    public Status getStatus() {
        return status;
    }
}
