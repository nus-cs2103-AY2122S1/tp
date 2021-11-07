package seedu.address.storage.guest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.guest.Guest;
import seedu.address.model.guest.GuestBook;
import seedu.address.model.guest.ReadOnlyGuestBook;

/**
 * An Immutable GuestBook() that is serializable to JSON format.
 */
@JsonRootName(value = "guestBook")
public class JsonSerializableGuestBook {

    public static final String MESSAGE_DUPLICATE_GUEST = "Guest list contains duplicate guest(s).";

    private final List<JsonAdaptedGuest> guests = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableGuestBook} with the given guests.
     * The json property here reads from the guests.json for the header staff
     */
    @JsonCreator
    public JsonSerializableGuestBook(@JsonProperty("guests") List<JsonAdaptedGuest> guests) {
        this.guests.addAll(guests);
    }

    /**
     * Converts a given {@code ReadOnlyGuestBook} into this class for Jackson use.
     * This code saves the data in this format
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableGuestBook}.
     */
    public JsonSerializableGuestBook(ReadOnlyGuestBook source) {
        guests.addAll(source.getGuestList().stream().map(
                JsonAdaptedGuest::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code GuestBook} object.
     * This code loads the data into the system.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public GuestBook toModelType() throws IllegalValueException {
        GuestBook guestBook = new GuestBook();
        for (JsonAdaptedGuest jsonAdaptedGuest : guests) {
            Guest guest = jsonAdaptedGuest.toModelType();
            if (guestBook.hasGuest(guest)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_GUEST);
            }
            guestBook.addGuest(guest);
        }
        return guestBook;
    }

}
