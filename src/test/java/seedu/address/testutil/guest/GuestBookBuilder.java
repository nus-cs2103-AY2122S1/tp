package seedu.address.testutil.guest;

import seedu.address.model.guest.Guest;
import seedu.address.model.guest.GuestBook;

/**
 * A utility class to help with building GuestBook objects.
 * Example usage: <br>
 *     {@code GuestBook gb = new GuestBookBuilder().withGuest("John", "Doe").build();}
 */
public class GuestBookBuilder {

    private GuestBook guestBook;

    public GuestBookBuilder() {
        guestBook = new GuestBook();
    }

    public GuestBookBuilder(GuestBook guestBook) {
        this.guestBook = guestBook;
    }

    /**
     * Adds a new {@code Guest} to the {@code GuestBook} that we are building.
     */
    public GuestBookBuilder withGuest(Guest guest) {
        guestBook.addGuest(guest);
        return this;
    }

    public GuestBook build() {
        return guestBook;
    }
}
