package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFacilities.TAMPINES_HUB_FIELD_SECTION_B;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.facility.Facility;
import seedu.address.model.person.Person;
import seedu.address.testutil.FacilityBuilder;

public class AllocateMemberCommandTest {
    @Test
    public void constructor_null_exceptionThrown() {
        // null member index
        assertThrows(NullPointerException.class, () ->
                new AllocateMemberCommand(null, INDEX_FIRST, DayOfWeek.MONDAY));

        // null facility index
        assertThrows(NullPointerException.class, () ->
                new AllocateMemberCommand(INDEX_FIRST, null, DayOfWeek.MONDAY));

        // null day
        assertThrows(NullPointerException.class, () ->
                new AllocateMemberCommand(INDEX_FIRST, INDEX_FIRST, null));
    }

    @Test
    public void execute_allocatePersonNotMaxCapacity_successful() {
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        model.addPerson(AMY);
        Facility facility = new FacilityBuilder(TAMPINES_HUB_FIELD_SECTION_B).build();
        model.addFacility(facility);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Map<DayOfWeek, List<Person>> expectedMap = new EnumMap<>(DayOfWeek.class);
        for (DayOfWeek day : DayOfWeek.values()) {
            expectedMap.put(day, new ArrayList<>());
        }
        expectedMap.put(DayOfWeek.MONDAY, List.of(AMY));
        Facility facilityAfterAllocation = new FacilityBuilder(facility).withAllocationMap(expectedMap).build();
        expectedModel.setFacility(facility, facilityAfterAllocation);

        AllocateMemberCommand command = new AllocateMemberCommand(INDEX_FIRST, INDEX_FIRST, DayOfWeek.MONDAY);
        String expectedMessage = String.format(AllocateMemberCommand.MESSAGE_SUCCESS,
                AMY.getName(), facilityAfterAllocation.getName(), "Monday");
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_allocatePersonMaxCapacity_failure() {
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        model.addPerson(AMY);
        model.addPerson(BOB);
        Facility facility = new FacilityBuilder(TAMPINES_HUB_FIELD_SECTION_B).withCapacity("1").build();
        facility.addPersonToFacilityOnDay(AMY, DayOfWeek.MONDAY);
        model.addFacility(facility);

        AllocateMemberCommand command = new AllocateMemberCommand(INDEX_SECOND, INDEX_FIRST, DayOfWeek.MONDAY);
        assertCommandFailure(command, model, Messages.MESSAGE_FACILITY_AT_MAX_CAPACITY);
    }

    @Test
    public void execute_allocateDuplicatePersonSameDay_failure() {
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        model.addPerson(AMY);
        Facility facility = new FacilityBuilder(TAMPINES_HUB_FIELD_SECTION_B).build();
        facility.addPersonToFacilityOnDay(AMY, DayOfWeek.MONDAY);
        model.addFacility(facility);

        AllocateMemberCommand command = new AllocateMemberCommand(INDEX_FIRST, INDEX_FIRST, DayOfWeek.MONDAY);
        assertCommandFailure(command, model, Messages.MESSAGE_MEMBER_ALREADY_ALLOCATED);
    }

    @Test
    public void execute_allocatePersonNotAvailable_failure() {
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        model.addPerson(AMY);
        Facility facility = new FacilityBuilder(TAMPINES_HUB_FIELD_SECTION_B).build();
        model.addFacility(facility);

        AllocateMemberCommand command = new AllocateMemberCommand(INDEX_FIRST, INDEX_FIRST, DayOfWeek.SUNDAY);
        assertCommandFailure(command, model, Messages.MESSAGE_MEMBER_NOT_AVAILABLE);
    }
}
