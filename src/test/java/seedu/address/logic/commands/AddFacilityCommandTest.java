package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.DayOfWeek;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.facility.AllocationMap;
import seedu.address.model.facility.Capacity;
import seedu.address.model.facility.Facility;
import seedu.address.model.facility.FacilityName;
import seedu.address.model.facility.Location;
import seedu.address.model.facility.Time;
import seedu.address.model.person.Member;

public class AddFacilityCommandTest {
    public static final Map<DayOfWeek, List<Member>> DEFAULT_ALLOCATION_MAP = new EnumMap<>(DayOfWeek.class);
    @Test
    public void constructor_null_exceptionThrown() {
        assertThrows(NullPointerException.class, () -> new AddFacilityCommand(null));
    }

    @Test
    public void execute_addSuccessful() {
        ModelManager model = new ModelManager();
        Facility facility = new Facility(new FacilityName("Court"), new Location("Loc"), new Time("1500"),
                new Capacity("5"), new AllocationMap(DEFAULT_ALLOCATION_MAP));
        Model expectedModel = new ModelManager(model.getSportsPa(), new UserPrefs());
        expectedModel.addFacility(facility);
        AddFacilityCommand command = new AddFacilityCommand(facility);
        String expectedMessage = String.format(AddFacilityCommand.MESSAGE_SUCCESS, facility);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        Facility court = new Facility(new FacilityName("Court"), new Location("Loc"), new Time("1500"),
                new Capacity("5"), new AllocationMap(DEFAULT_ALLOCATION_MAP));

        Facility field = new Facility(new FacilityName("Field"), new Location("Test"), new Time("1300"),
                new Capacity("10"), new AllocationMap(DEFAULT_ALLOCATION_MAP));

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
