package seedu.address.model.guest;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Guest} matches the {@code PassportNumber}, {@code RoomNumber}, {@code Name}, {@code Email},
 * and list of {@code Tag} given.
 */
public class GuestPredicate implements Predicate<Guest> {

    private final Optional<String> passportNumberOptional;
    private final Optional<String> roomNumberOptional;
    private final Optional<String> nameOptional;
    private final Optional<String> emailOptional;
    private final Optional<Set<Tag>> tagsOptional;

    /**
     * Creates a guest predicate.
     *
     * @param passportNumberOptional
     * @param roomNumberOptional
     * @param nameOptional
     * @param emailOptional
     * @param tagsOptional
     */
    public GuestPredicate(Optional<String> passportNumberOptional,
                          Optional<String> roomNumberOptional,
                          Optional<String> nameOptional,
                          Optional<String> emailOptional,
                          Optional<Set<Tag>> tagsOptional) {
        requireAllNonNull(passportNumberOptional, roomNumberOptional, nameOptional, emailOptional, tagsOptional);
        if (tagsOptional.isPresent()) {
            this.tagsOptional = Optional.of(new HashSet<>(tagsOptional.get()));
        } else {
            this.tagsOptional = Optional.empty();
        }
        this.passportNumberOptional = passportNumberOptional;
        this.roomNumberOptional = roomNumberOptional;
        this.nameOptional = nameOptional;
        this.emailOptional = emailOptional;
    }

    @Override
    public boolean test(Guest guest) {
        requireNonNull(guest);
        return testForEmail(guest) && testForName(guest) && testForTags(guest) && testForRoomNumber(guest)
                && testForPassportNumber(guest);
    }

    private boolean testForPassportNumber(Guest guest) {
        if (passportNumberOptional.isPresent()) {
            return passportNumberOptional.get().trim().toUpperCase().equals(guest.getPassportNumber().value);
        }
        return true;
    }

    private boolean testForName(Guest guest) {
        if (nameOptional.isPresent()) {
            String nameTested = nameOptional.get().toLowerCase().trim();
            String guestName = guest.getName().toString().toLowerCase();
            return guestName.contains(nameTested);
        }
        return true;
    }

    private boolean testForEmail(Guest guest) {
        if (emailOptional.isPresent()) {
            String emailTested = emailOptional.get().toLowerCase().trim();
            String guestEmail = guest.getEmail().toString().toLowerCase();
            return guestEmail.contains(emailTested);
        }
        return true;
    }

    private boolean testForRoomNumber(Guest guest) {
        if (roomNumberOptional.isPresent()) {
            return guest.getRoomNumber().value.indexOf(roomNumberOptional.get().trim()) == 0;
        }
        return true;
    }

    private boolean testForTags(Guest guest) {
        if (tagsOptional.isPresent()) {
            List<String> guestTagStrings =
                    guest.getTags().stream().map(tag -> tag.tagName).collect(Collectors.toList());
            return tagsOptional.get().stream()
                    .anyMatch(tag -> guestTagStrings.contains(StringUtil.capitalizeFirstLetter(tag.tagName.trim())));
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GuestPredicate // instanceof handles nulls
                && emailOptional.equals(((GuestPredicate) other).emailOptional)
                && nameOptional.equals(((GuestPredicate) other).nameOptional)
                && roomNumberOptional.equals(((GuestPredicate) other).roomNumberOptional)
                && passportNumberOptional.equals(((GuestPredicate) other).passportNumberOptional))
                && tagsOptional.equals(((GuestPredicate) other).tagsOptional); // state check
    }


}
