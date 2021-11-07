package seedu.address.testutil.guest;

import static seedu.address.testutil.guest.TypicalPassportNumbers.PASSPORT_NUMBER_DEFAULT;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.commonattributes.Email;
import seedu.address.model.commonattributes.Name;
import seedu.address.model.guest.Guest;
import seedu.address.model.guest.PassportNumber;
import seedu.address.model.guest.RoomNumber;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

public class GuestBuilder {

    public static final Name DEFAULT_NAME = new Name("Amy Bee");
    public static final Email DEFAULT_EMAIL = new Email("amy@gmail.com");
    public static final RoomNumber DEFAULT_ROOM_NUMBER = new RoomNumber("101");
    public static final PassportNumber DEFAULT_PASSPORT_NUMBER = new PassportNumber(PASSPORT_NUMBER_DEFAULT.toString());

    private Name name;
    private Email email;
    private Set<Tag> tags;
    private RoomNumber roomNumber;
    private PassportNumber passportNumber;

    /**
     * Creates a {@code GuestBuilder} with the default details.
     */
    public GuestBuilder() {
        name = DEFAULT_NAME;
        email = DEFAULT_EMAIL;
        tags = new HashSet<>();
        roomNumber = DEFAULT_ROOM_NUMBER;
        passportNumber = DEFAULT_PASSPORT_NUMBER;
    }

    /**
     * Initializes the GuestBuilder with the data of {@code guestToCopy}.
     */
    public GuestBuilder(Guest guestToCopy) {
        name = guestToCopy.getName();
        email = guestToCopy.getEmail();
        tags = new HashSet<>(guestToCopy.getTags());
        roomNumber = guestToCopy.getRoomNumber();
        passportNumber = guestToCopy.getPassportNumber();
    }

    /**
     * Sets the {@code Name} of the {@code Guest} that we are building.
     */
    public GuestBuilder withName(String name) {
        //        this.name = new Name("name");
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Guest} that we are building.
     */
    public GuestBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Guest} that we are building.
     */
    public GuestBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code RoomNumber} of the {@code Guest} that we are building.
     */
    public GuestBuilder withRoomNumber(String roomNumber) {
        this.roomNumber = new RoomNumber(roomNumber);
        return this;
    }

    /**
     * Sets the {@code PassportNumber} of the {@code Guest} that we are building.
     */
    public GuestBuilder withPassportNumber(String passportNumber) {
        this.passportNumber = new PassportNumber(passportNumber);
        return this;
    }


    public Guest build() {
        return new Guest(name, email, tags, roomNumber, passportNumber);
    }
}
