package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.facility.Capacity;
import seedu.address.model.facility.Facility;
import seedu.address.model.facility.FacilityName;
import seedu.address.model.facility.Location;
import seedu.address.model.facility.Time;


public class AddFacilityCommandTest {

    @Test
    public void constructor_null_exceptionThrown() {
        assertThrows(NullPointerException.class, () -> new AddFacilityCommand(null));
    }

    @Test
    public void execute_addSuccessful() {
        ModelManager model = new ModelManager();
        Facility facility = new Facility(new FacilityName("Court"), new Location("Loc"), new Time("15:00"),
                new Capacity("5"));
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addFacility(facility);
        AddFacilityCommand command = new AddFacilityCommand(facility);
        String expectedMessage = String.format(AddFacilityCommand.MESSAGE_SUCCESS, facility);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        Facility court = new Facility(new FacilityName("Court"), new Location("Loc"), new Time("15:00"),
                new Capacity("5"));

        Facility field = new Facility(new FacilityName("Field"), new Location("Test"), new Time("13:00"),
                new Capacity("10"));

        AddFacilityCommand addCourtCommand = new AddFacilityCommand(court);
        AddFacilityCommand addCourtCommandCopy = new AddFacilityCommand(court);
        AddFacilityCommand addFieldCommand = new AddFacilityCommand(field);

        assertTrue(addCourtCommand.equals(addCourtCommand));
        assertTrue(addCourtCommand.equals(addCourtCommandCopy));
        assertFalse(addCourtCommand.equals(addFieldCommand));
        assertFalse(addCourtCommand.equals(null));
        assertFalse(addCourtCommand.equals("10"));
    }
}
