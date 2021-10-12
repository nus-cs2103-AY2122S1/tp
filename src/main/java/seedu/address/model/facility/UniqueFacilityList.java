package seedu.address.model.facility;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.facility.exceptions.FacilityNotFoundException;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;



/**
 * Represents a list of facilities.
 */
public class UniqueFacilityList implements Iterable<Facility> {

    private final ObservableList<Facility> facilityList = FXCollections.observableArrayList();

    /**
     * Returns true if the list contains an equivalent facility as the given argument.
     */
    public boolean contains(Facility toCheck) {
        requireNonNull(toCheck);
        return facilityList.stream().anyMatch(toCheck::isSameFacility);
    }

    /**
     * Adds the specified facility to the facilityList.
     *
     * @param facility Facility to be added to list.
     */
    public void add(Facility facility) {
        requireNonNull(facility);
        facilityList.add(facility);
    }

    /**
     * Removes the specified facility from the facilityList.
     *
     * @param toRemove Facility to be removed.
     */
    public void remove(Facility toRemove) {
        requireNonNull(toRemove);
        if (!facilityList.remove(toRemove)) {
            throw new FacilityNotFoundException();
        }
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
     * Replaces the contents of this list with {@code facilities}.
     * {@code facilities} must not contain duplicate facilities.
     */
    public void setFacilities(List<Facility> facilities) {
        requireAllNonNull(facilities);
        if (!facilitiesAreUnique(facilities)) {
            throw new DuplicatePersonException();
        }

        facilityList.setAll(facilities);
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
     * Allocates members into the different facilities.
     *
     * @param members Members to be allocated.
     */
    public void allocateMembersToFacilities(FilteredList<Person> members) {
        int index = 0;
        for (Facility facility : facilityList) {
            Facility toEdit = facility;
            toEdit.clearAllocationList();

            int facilityCount = 0;

            while (toEdit.isWithinMaxCapacity(facilityCount + 1)
                    && !(index > members.size() - 1)) {
                toEdit.addPersonToFacility(members.get(index));
                facilityCount++;
                index++;
            }
            this.replaceFacility(facility, toEdit);
        }
    }

    /**
     * Replaces target Facility with edited Facility. Does not
     * check for duplicates.
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


    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean facilitiesAreUnique(List<Facility> facilities) {
        for (int i = 0; i < facilities.size() - 1; i++) {
            for (int j = i + 1; j < facilities.size(); j++) {
                if (facilities.get(i).isSameFacility(facilities.get(j))) {
                    return false;
                }
            }
        }
        return true;

    }

}
