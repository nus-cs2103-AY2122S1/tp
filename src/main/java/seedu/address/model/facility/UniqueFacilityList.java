package seedu.address.model.facility;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.util.SampleDataUtil;


/**
 * Represents a list of facilities.
 */
public class UniqueFacilityList implements Iterable<Facility> {
    /** Remove when storage is implemented.*/
    private final ObservableList<Facility> facilityList = FXCollections.observableArrayList(
            Arrays.asList(SampleDataUtil.getSampleFacilities()));

    /**
     * Adds the specified facility to the facilityList.
     *
     * @param facility Facility to be added to list.
     */
    public void add(Facility facility) {
        requireNonNull(facility);
        facilityList.add(facility);
    }

    @Override
    public Iterator<Facility> iterator() {
        return facilityList.iterator();
    }

    /**
     * Returns the facilities list.
     *
     * @return ObservableList of containing all facilities.
     */
    public ObservableList<Facility> getObservableList() {
        return facilityList;
    }

    /**
     * Replaces the contents of this list with empty list.
     */
    public void resetFacilities() {
        facilityList.setAll();
    }
}
