package seedu.address.logic.commands.guest;

import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.guest.Guest;
import seedu.address.model.guest.GuestBook;
import seedu.address.model.guest.PassportNumber;
import seedu.address.model.guest.ReadOnlyGuestBook;
import seedu.address.model.guest.UniqueGuestList;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.guest.GuestBuilder;

public class CheckInNewGuestCommandTest {

    @Test
    public void constructor_nullGuest_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CheckInNewGuestCommand(null));
    }

    @Test
    public void execute_guestAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingGuestAdded modelStub = new ModelStubAcceptingGuestAdded();
        Guest validGuest = new GuestBuilder().build();

        CommandResult commandResult = new CheckInNewGuestCommand(validGuest).execute(modelStub);

        assertEquals(String.format(CheckInNewGuestCommand.MESSAGE_SUCCESS, validGuest),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validGuest), modelStub.guestsAdded);
    }

    @Test
    public void execute_duplicateGuest_throwsCommandException() {
        Guest validGuest = new GuestBuilder().build();
        CheckInNewGuestCommand checkInCommand = new CheckInNewGuestCommand(validGuest);
        ModelStub modelStub = new ModelStubWithGuest(validGuest);

        assertThrows(CommandException.class, CheckInNewGuestCommand.MESSAGE_DUPLICATE_GUEST, (
        ) -> checkInCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Guest alice = new GuestBuilder().withName("Alice").build();
        Guest bob = new GuestBuilder().withName("Bob").build();
        CheckInNewGuestCommand addAliceCommand = new CheckInNewGuestCommand(alice);
        CheckInNewGuestCommand addBobCommand = new CheckInNewGuestCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        CheckInNewGuestCommand addAliceCommandCopy = new CheckInNewGuestCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different guest -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A Model stub that contains a single guest.
     */
    private class ModelStubWithGuest extends ModelStub {
        private final Guest guest;

        ModelStubWithGuest(Guest guest) {
            requireNonNull(guest);
            this.guest = guest;
        }

        @Override
        public boolean hasGuest(Guest guest) {
            requireNonNull(guest);
            return this.guest.isSame(guest);
        }
    }

    /**
     * A Model stub that always accept the guest being added.
     */
    private class ModelStubAcceptingGuestAdded extends ModelStub {
        final ArrayList<Guest> guestsAdded = new ArrayList<>();
        private final UniqueGuestList uniqueGuestList = new UniqueGuestList();
        private final GuestBook guestBook = new GuestBook();
        private final FilteredList<Guest> filteredGuests = new FilteredList<Guest>(this.guestBook.getGuestList());

        @Override
        public boolean hasGuest(Guest guest) {
            requireNonNull(guest);
            return guestsAdded.stream().anyMatch(guest::isSame);
        }

        @Override
        public void addGuest(Guest guest) {
            requireNonNull(guest);
            guestsAdded.add(guest);
        }

        @Override
        public Optional<Guest> getArchivedGuest(PassportNumber passportNumber) {
            return uniqueGuestList.get(passportNumber);
        }

        @Override
        public ObservableList<Guest> getFilteredGuestList() {
            return filteredGuests;
        }

        @Override
        public ReadOnlyGuestBook getGuestBook() {
            return new GuestBook();
        }
    }

}
