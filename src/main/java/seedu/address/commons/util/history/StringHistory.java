package seedu.address.commons.util.history;

/**
 * StringHistory represents the History of objects of String class.
 */
public class StringHistory extends BaseHistory<String> {
    /**
     * Creates a StringHistory object with the specified capacity.
     *
     * @param capacity The specified capacity.
     */
    public StringHistory(int capacity) {
        super(capacity);
    }

    /**
     * Creates a StringHistory object with the default capacity.
     */
    public StringHistory() {
        super();
    }
}
