package seedu.address.testutil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Phone;
import seedu.address.model.reservation.Reservation;

public class TypicalReservation {
    public static final Reservation ALICE_RESERVATION = new Reservation(
            new Phone("94351253"), 2, LocalDateTime.parse("2021-11-11T20:00")
    );
    public static final Reservation BENSON_RESERVATION = new Reservation(
            new Phone("98765432"), 3, LocalDateTime.parse("2021-11-12T19:30")
    );
    public static final Reservation CARL_RESERVATION = new Reservation(
            new Phone("98765432"), 3, LocalDateTime.parse("2021-11-11T09:00")
    );

    // prevent initialization
    private TypicalReservation() {}

    public AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Reservation res : getTypicalReservations()) {
            ab.addReservation(res);
        }
        return ab;
    }

    public List<Reservation> getTypicalReservations() {
        return new ArrayList<>(Arrays.asList(ALICE_RESERVATION, BENSON_RESERVATION, CARL_RESERVATION));
    }
}
