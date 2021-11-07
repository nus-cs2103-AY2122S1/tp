package seedu.address.testutil.guest;

import seedu.address.model.guest.Archive;
import seedu.address.model.guest.Guest;

/**
 * A utility class to help with building Archive objects.
 * Example usage: <br>
 * {@code Archive archive = new ArchiveBuilder().withArchivedGuest(JOHN_DOE).build();}
 */
public class ArchiveBuilder {

    private Archive archive;

    public ArchiveBuilder() {
        archive = new Archive();
    }

    public ArchiveBuilder(Archive archive) {
        this.archive = archive;
    }

    /**
     * Adds a new {@code Guest} to the {@code Archive} that we are building.
     */
    public ArchiveBuilder withArchivedGuest(Guest guest) {
        archive.addGuest(guest);
        return this;
    }

    public Archive build() {
        return archive;
    }
}
