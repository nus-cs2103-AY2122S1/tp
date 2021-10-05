package seedu.address.model.facility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Iterator;

import static java.util.Objects.requireNonNull;

/**
 * Represents a list of facilities.
 */
public class UniqueFacilityList implements Iterable<Facility> {
    private final ObservableList<Facility> facilityList = FXCollections.observableArrayList();

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
}
