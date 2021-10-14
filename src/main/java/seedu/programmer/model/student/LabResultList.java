package seedu.programmer.model.student;

import static seedu.programmer.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LabResultList implements Iterable<LabResult> {

    private final ObservableList<LabResult> internalList = FXCollections.observableArrayList();
    private final ObservableList<LabResult> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<LabResult> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    public void setLabResults(List<LabResult> labResults) {
        requireAllNonNull(labResults);
        internalList.setAll(labResults);
    }

    @Override
    public Iterator<LabResult> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LabResultList // instanceof handles nulls
                && internalList.equals(((LabResultList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

}
