package seedu.address.ui;

import java.util.ArrayList;

import seedu.address.commons.core.index.Index;

/**
 * Class which stores previous commands which the user has entered.
 */
public class UserStringHistory {
    /** ArrayList of all the previously entered userStrings. **/
    private final ArrayList<String> userStrings = new ArrayList<>();

    private final int EMPTY_INDEX = -1;
    private final int FIRST_INDEX = 0;

    /**
     * Current index of userString that is being pointed to.
     * -1 means userStrings is empty.
     */
    private int currentIndex = EMPTY_INDEX;

    /**
     * Adds a new string to the user string history.
     *
     * @param s String to be added
     */
    public void add(String s) {
        userStrings.add(s);
        currentIndex++;
    }

    /**
     * Gets the previous command the user entered.
     *
     * @return previous command.
     */
    public String getPrevious() {
        if (currentIndex == EMPTY_INDEX) {
            return "";
        } else if (currentIndex == FIRST_INDEX) {
            return userStrings.get(FIRST_INDEX);
        }
        String currentString = userStrings.get(currentIndex);
        currentIndex--;
        return currentString;
    }

    /**
     * Gets the next command the user entered.
     *
     * @return next command.
     */
    public String getNext() {
        final int LAST_INDEX = userStrings.size() - 1;
        if (currentIndex == EMPTY_INDEX) {
            return "";
        } else if (currentIndex == LAST_INDEX) {
            return "";
        }
        currentIndex++;
        return userStrings.get(currentIndex);

    }

    /**
     * Resets the current index point to the latest user command.
     */
    public void resetIndex() {
        currentIndex = userStrings.size() - 1;
    }

}
