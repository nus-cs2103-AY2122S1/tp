package seedu.address.model.facility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFacilities.KENT_RIDGE_OUTDOOR_TENNIS_COURTS_COURT_1;

import org.junit.jupiter.api.Test;

import seedu.address.model.facility.exceptions.FacilityNotFoundException;

public class UniqueFacilityListTest {
    public final UniqueFacilityList uniqueFacilityList = new UniqueFacilityList();
    public final Facility facility = new Facility(
            new FacilityName("Court 1"),
            new Location("Kent Ridge Sports Hall"),
            new Time("13:00"),
            new Capacity("5"));

    @Test
    public void add_null_exceptionThrown() {
        assertThrows(NullPointerException.class, () -> uniqueFacilityList.add(null));
    }

    @Test
    public void remove_nullFacility_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFacilityList.remove(null));
    }

    @Test
    public void remove_facilityDoesNotExist_throwsFacilityNotFoundException() {
        assertThrows(FacilityNotFoundException.class, () -> uniqueFacilityList.remove(facility));
    }

    @Test
    public void remove_existingFacility_removesFacility() {
        uniqueFacilityList.add(facility);
        uniqueFacilityList.remove(facility);
    }

    @Test
    public void resetFacilities_clearsFacilityList() {
        uniqueFacilityList.add(KENT_RIDGE_OUTDOOR_TENNIS_COURTS_COURT_1);
        uniqueFacilityList.resetFacilities();
        UniqueFacilityList expectedUniqueFacilityList = new UniqueFacilityList();
        assertEquals(expectedUniqueFacilityList, uniqueFacilityList);
    }
}
