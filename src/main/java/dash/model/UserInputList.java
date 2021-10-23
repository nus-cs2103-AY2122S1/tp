package dash.model;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import dash.commons.util.CollectionUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserInputList implements Iterable<String> {

    private final ObservableList<String> internalList = FXCollections.observableArrayList();
    private final ObservableList<String> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    public UserInputList() {
    }

    /**
     * Creates a UserInputList using the user input Strings in the {@code toBeCopied}
     */
    public UserInputList(UserInputList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Resets the existing data of this {@code UserInputList} with {@code newData}.
     */
    public void resetData(UserInputList newData) {
        requireNonNull(newData);
        setUserInputs(newData.getObservableUserInputList());
    }

    /**
     * Adds a user input String to the start of the list.
     */
    public void add(String toAdd) {
        requireNonNull(toAdd);
        internalList.add(0,toAdd);
    }

    /**
     * Replaces the contents of this list with {@code tasks}.
     */
    public void setUserInputs(List<String> userInputs) {
        CollectionUtil.requireAllNonNull(userInputs);
        internalList.setAll(userInputs);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<String> getObservableUserInputList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<String> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UserInputList // instanceof handles nulls
                && internalList.equals(((UserInputList) other).internalList));
    }

    @Override
    public int hashCode() { return internalList.hashCode(); }

}
