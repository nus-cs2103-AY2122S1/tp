package seedu.address.model.facility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFacilities.KENT_RIDGE_OUTDOOR_TENNIS_COURTS_COURT_1;
import static seedu.address.testutil.TypicalFacilities.KENT_RIDGE_OUTDOOR_TENNIS_COURTS_COURT_10;
import static seedu.address.testutil.TypicalFacilities.KENT_RIDGE_SPORT_HALL_5_COURT_1;
import static seedu.address.testutil.TypicalFacilities.TAMPINES_HUB_FIELD_SECTION_B;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.facility.exceptions.FacilityNotFoundException;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

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
    public void setFacility_replaceExistingFacility_success() {
        uniqueFacilityList.add(KENT_RIDGE_OUTDOOR_TENNIS_COURTS_COURT_1);
        uniqueFacilityList.add(TAMPINES_HUB_FIELD_SECTION_B);

        Facility editedFacility = KENT_RIDGE_OUTDOOR_TENNIS_COURTS_COURT_10;

        UniqueFacilityList expectedList = new UniqueFacilityList();
        expectedList.add(KENT_RIDGE_OUTDOOR_TENNIS_COURTS_COURT_10);
        expectedList.add(TAMPINES_HUB_FIELD_SECTION_B);

        uniqueFacilityList.replaceFacility(KENT_RIDGE_OUTDOOR_TENNIS_COURTS_COURT_1, editedFacility);
        assertEquals(expectedList, uniqueFacilityList);

    }

    @Test
    public void allocateMembersToFacilities_addsMembersToFacilities_success() {
        uniqueFacilityList.add(KENT_RIDGE_OUTDOOR_TENNIS_COURTS_COURT_1);
        uniqueFacilityList.add(TAMPINES_HUB_FIELD_SECTION_B);

        ObservableList<Person> members = FXCollections.observableArrayList();
        Person firstMember = new PersonBuilder().build();
        firstMember.setDays(Arrays.asList("Mon", "Tue"));
        Person secondMember = new PersonBuilder().build();
        secondMember.setDays(Arrays.asList("Mon", "Thu"));
        members.add(firstMember);
        members.add(secondMember);

        Facility updatedFacility = KENT_RIDGE_OUTDOOR_TENNIS_COURTS_COURT_1;
        updatedFacility.addPersonToFacility(firstMember);
        updatedFacility.addPersonToFacility(secondMember);
        UniqueFacilityList expected = new UniqueFacilityList();
        expected.setFacilities(Arrays.asList(updatedFacility, TAMPINES_HUB_FIELD_SECTION_B));

        FilteredList<Person> toAllocate = new FilteredList<Person>(members);
        uniqueFacilityList.allocateMembersToFacilities(toAllocate);
        assertEquals(expected, uniqueFacilityList);
    }

    @Test
    public void replaceFacility_replaceNonExistentFacility_exceptionThrown() {
        uniqueFacilityList.add(KENT_RIDGE_OUTDOOR_TENNIS_COURTS_COURT_1);
        uniqueFacilityList.add(TAMPINES_HUB_FIELD_SECTION_B);

        Facility editedFacility = KENT_RIDGE_OUTDOOR_TENNIS_COURTS_COURT_10;

        assertThrows(FacilityNotFoundException.class, () -> uniqueFacilityList
                .replaceFacility(KENT_RIDGE_SPORT_HALL_5_COURT_1, editedFacility));
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
