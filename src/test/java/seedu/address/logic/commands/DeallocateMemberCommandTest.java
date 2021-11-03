package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFacilities.TAMPINES_HUB_FIELD_SECTION_B;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalPersons.AMY;

import java.time.DayOfWeek;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.facility.Facility;
import seedu.address.testutil.FacilityBuilder;

public class DeallocateMemberCommandTest {
    @Test
    public void constructor_null_exceptionThrown() {
        // null member index
        assertThrows(NullPointerException.class, () ->
                new DeallocateMemberCommand(null, INDEX_FIRST, DayOfWeek.MONDAY));

        // null facility index
        assertThrows(NullPointerException.class, () ->
                new DeallocateMemberCommand(INDEX_FIRST, null, DayOfWeek.MONDAY));

        // null day
        assertThrows(NullPointerException.class, () ->
                new DeallocateMemberCommand(INDEX_FIRST, INDEX_FIRST, null));
    }

    @Test
    public void execute_deallocatePerson_successful() {
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        model.addPerson(AMY);
        Facility facility = new FacilityBuilder(TAMPINES_HUB_FIELD_SECTION_B).build();
        facility.addPersonToFacilityOnDay(AMY, DayOfWeek.MONDAY);
        model.addFacility(facility);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Facility facilityAfterDeallocation = new FacilityBuilder(TAMPINES_HUB_FIELD_SECTION_B).build();
        expectedModel.setFacility(facility, facilityAfterDeallocation);

        DeallocateMemberCommand command = new DeallocateMemberCommand(INDEX_FIRST, INDEX_FIRST, DayOfWeek.MONDAY);
        String expectedMessage = String.format(DeallocateMemberCommand.MESSAGE_SUCCESS,
                AMY.getName(), facilityAfterDeallocation.getName(), "Monday");
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deallocatePersonNotAllocated_failure() {
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        model.addPerson(AMY);
        Facility facility = new FacilityBuilder(TAMPINES_HUB_FIELD_SECTION_B).build();
        model.addFacility(facility);

        DeallocateMemberCommand command = new DeallocateMemberCommand(INDEX_FIRST, INDEX_FIRST, DayOfWeek.MONDAY);
        assertCommandFailure(command, model, Messages.MESSAGE_MEMBER_NOT_ALLOCATED);
    }
}
