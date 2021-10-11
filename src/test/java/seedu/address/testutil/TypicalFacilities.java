package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.facility.Facility;

/**
 * A utility class containing a list of {@code Facility} objects to be used in tests.
 */
public class TypicalFacilities {

    // First facility uniquely different
    public static final Facility TAMPINES_HUB_FIELD_SECTION_B = new FacilityBuilder()
            .withFacilityName("Court 1")
            .withLocation("Tampines Hub Field")
            .withTime("1500")
            .withCapacity("4").build();

    public static final Facility KENT_RIDGE_SPORT_HALL_5_COURT_1 = new FacilityBuilder()
            .withFacilityName("Court 1")
            .withLocation("Kent Ridge Sports Hall 5")
            .withTime("1500")
            .withCapacity("4").build();
    public static final Facility KENT_RIDGE_SPORT_HALL_5_COURT_2 = new FacilityBuilder()
            .withFacilityName("Court 2")
            .withLocation("Kent Ridge Sports Hall 5")
            .withTime("1500")
            .withCapacity("4").build();
    public static final Facility UNIVERSITY_TOWN_SPORTS_HALL_1_COURT_20 = new FacilityBuilder()
            .withFacilityName("Court 20")
            .withLocation("University Town Sports Hall 1")
            .withTime("1600")
            .withCapacity("4").build();
    public static final Facility UNIVERSITY_TOWN_SPORTS_HALL_COURT_21 = new FacilityBuilder()
            .withFacilityName("Court 21")
            .withLocation("University Town Sports Hall 1")
            .withTime("1600")
            .withCapacity("4").build();
    public static final Facility KENT_RIDGE_OUTDOOR_TENNIS_COURTS_COURT_1 = new FacilityBuilder()
            .withFacilityName("Court 1")
            .withLocation("Kent Ridge Outdoor Tennis Courts")
            .withTime("1900")
            .withCapacity("5").build();
    public static final Facility KENT_RIDGE_OUTDOOR_TENNIS_COURTS_COURT_10 = new FacilityBuilder()
            .withFacilityName("Court 10")
            .withLocation("Kent Ridge Outdoor Tennis Courts")
            .withTime("1900")
            .withCapacity("5").build();
    public static final Facility UTOWN_FIELD_SECTION_A = new FacilityBuilder()
            .withFacilityName("Section A")
            .withLocation("UTown Field")
            .withTime("1400")
            .withCapacity("10").build();

    //Manually added
    public static final Facility UNIVERSITY_TOWN_SPORTS_HALL_1_COURT_1 = new FacilityBuilder()
            .withFacilityName("Court 1")
            .withLocation("University Town Sports Hall 1")
            .withTime("2000")
            .withCapacity("4").build();
    public static final Facility UNIVERSITY_TOWN_SPORTS_HALL_1_COURT_2 = new FacilityBuilder()
            .withFacilityName("Court 2")
            .withLocation("University Town Sports Hall 1")
            .withTime("2100")
            .withCapacity("4").build();

    private TypicalFacilities() {} // Prevents instantiation

    public static List<Facility> getTypicalFacilities() {
        return new ArrayList<>(Arrays.asList(
                TAMPINES_HUB_FIELD_SECTION_B,
                KENT_RIDGE_SPORT_HALL_5_COURT_1,
                KENT_RIDGE_SPORT_HALL_5_COURT_2,
                UNIVERSITY_TOWN_SPORTS_HALL_1_COURT_20,
                UNIVERSITY_TOWN_SPORTS_HALL_COURT_21,
                KENT_RIDGE_OUTDOOR_TENNIS_COURTS_COURT_1,
                KENT_RIDGE_OUTDOOR_TENNIS_COURTS_COURT_10,
                UTOWN_FIELD_SECTION_A));
    }
}
