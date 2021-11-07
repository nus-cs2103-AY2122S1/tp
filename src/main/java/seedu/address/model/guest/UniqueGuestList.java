package seedu.address.model.guest;

import java.util.Optional;

import seedu.address.model.uniquelist.UniqueList;

public class UniqueGuestList extends UniqueList<Guest> {

    public Optional<Guest> get(PassportNumber passportNumber) {
        return super.asModifiableObservableList().stream().filter(
            guest -> guest.getPassportNumber().equals(passportNumber)).findFirst();
    }
}
