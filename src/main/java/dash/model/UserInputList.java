package dash.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dash.commons.util.CollectionUtil;

/**
 * A list of user input strings with maximum length 10.
 * <p>
 * Supports a minimal set of list operations.
 */
public class UserInputList implements Iterable<String> {

    private static final int LIST_MAX_LENGTH = 10;

    private final ArrayList<String> internalList = new ArrayList<>();

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
     * Creates a UserInputList using a list of strings.
     */
    public UserInputList(List<String> userInputs) {
        internalList.addAll(userInputs);
        trimInternalList();
    }

    /**
     * Resets the existing data of this {@code UserInputList} with {@code newData}.
     */
    public void resetData(UserInputList newData) {
        requireNonNull(newData);
        setUserInputs(newData.getInternalUserInputList());
        trimInternalList();
    }

    /**
     * Adds a user input String to the start of the list.
     * Trims the internal list to {@code LIST_MAX_LENGTH}
     */
    public void add(String toAdd) {
        requireNonNull(toAdd);
        internalList.add(0, toAdd);
        trimInternalList();
    }

    /**
     * Replaces the contents of this list with {@code userInputs}.
     */
    public void setUserInputs(List<String> userInputs) {
        CollectionUtil.requireAllNonNull(userInputs);
        internalList.clear();
        internalList.addAll(userInputs);
        trimInternalList();
    }

    /**
     * Trims the internal list of user inputs to {@code LIST_MAX_LENGTH}
     */
    public void trimInternalList() {
        if (internalList.size() > LIST_MAX_LENGTH) {
            internalList.subList(LIST_MAX_LENGTH, internalList.size()).clear();
        }
    }

    /**
     * Returns the internal list of user inputs.
     */
    public ArrayList<String> getInternalUserInputList() {
        return internalList;
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
    public int hashCode() {
        return internalList.hashCode();
    }

}
