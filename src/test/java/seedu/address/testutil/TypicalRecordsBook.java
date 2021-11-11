package seedu.address.testutil;

import static seedu.address.model.residency.ResidencyTest.CHECK_IN_TIME_ONE;
import static seedu.address.model.residency.ResidencyTest.CHECK_OUT_TIME_ONE;
import static seedu.address.testutil.TypicalPersons.PERSON_LIST_ONE;
import static seedu.address.testutil.TypicalRooms.ROOM_ONE;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.residency.Residency;

public class TypicalRecordsBook {


    public static final Residency RESIDENCY_ONE =
            new Residency(ROOM_ONE, PERSON_LIST_ONE, CHECK_IN_TIME_ONE, CHECK_OUT_TIME_ONE);

    private TypicalRecordsBook() {};

    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Residency residency : getTypicalRecordsBook()) {
            ab.record(residency);
        }
        return ab;
    }

    public static List<Residency> getTypicalRecordsBook() {
        ArrayList<Residency> records = new ArrayList<>();
        records.add(RESIDENCY_ONE);
        return records;
    }
}
