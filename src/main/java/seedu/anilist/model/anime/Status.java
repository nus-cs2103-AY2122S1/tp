package seedu.anilist.model.anime;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.commons.util.AppUtil.checkArgument;

/**
 * Represents an Anime's watch status in the Anime List.
 * Guarantees: immutable; is valid as declared in {@link #isValidStatus(String)}
 */
public class Status {

    public enum WatchStatus {
        TOWATCH,
        WATCHING,
        FINISHED
    }

    public static final String[] VALID_STATUS_STRING = {"towatch", "watching", "finished"};

    public static final String MESSAGE_CONSTRAINTS =
            "Status should only be one of 'towatch', 'watching' or 'finished'";

    public final WatchStatus status;

    /**
     * Constructs a {@code Status}.
     *
     * @param status A valid status.
     */
    public Status(String status) {
        requireNonNull(status);
        checkArgument(isValidStatus(status), MESSAGE_CONSTRAINTS);
        this.status = parseWatchStatus(status);
    }

    /**
     * Returns true if a given string is a valid status.
     */
    public static boolean isValidStatus(String test) {
        for (String statusString: VALID_STATUS_STRING) {
            if (test.toLowerCase().equals(statusString)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        switch (this.status) {
        case TOWATCH:
            return VALID_STATUS_STRING[0];
        case WATCHING:
            return VALID_STATUS_STRING[1];
        case FINISHED:
            return VALID_STATUS_STRING[2];
        default:
            assert false : "Invalid watch status";
            return null;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Status // instanceof handles nulls
                && status == ((Status) other).status); // state check
    }

    private WatchStatus parseWatchStatus(String status) {
        switch(status.toLowerCase()) {
        case "towatch":
            return WatchStatus.TOWATCH;
        case "watching":
            return WatchStatus.WATCHING;
        case "finished":
            return WatchStatus.FINISHED;
        default:
            assert false : "Invalid watch status";
            return null;
        }
    }

    @Override
    public int hashCode() {
        return status.hashCode();
    }
}
