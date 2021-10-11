package seedu.address.model.facility;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.facility.exceptions.FacilityNotFoundException;


/**
 * Represents a list of facilities.
 */
public class UniqueFacilityList implements Iterable<Facility> {
    /** Remove when storage is implemented.*/
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

    /**
     * Replaces the contents of this list with empty list.
     */
    public void resetFacilities() {
        facilityList.setAll();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueFacilityList // instanceof handles nulls
                && facilityList.equals(((UniqueFacilityList) other).facilityList));
    }

    /**
     * Replaces target Facility with edited Facility.
     *
     * @param target Facility to be replaced.
     * @param editedFacility Facility replacing old one.
     */
    public void replaceFacility(Facility target, Facility editedFacility) {
        int index = facilityList.indexOf(target);
        if (index == -1) {
            throw new FacilityNotFoundException();
        }

        facilityList.set(index, editedFacility);
    }


    public void setFacilities(List<Facility> replacement) {
        requireNonNull(replacement);
        facilityList.setAll(replacement);

    }
}
