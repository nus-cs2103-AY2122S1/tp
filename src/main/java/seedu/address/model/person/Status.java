package seedu.address.model.person;

/**
 * This class stands for every possible status for a staff
 */
public enum Status {
    //Can add more later
    FULL_TIME("fulltime"), PART_TIME("parttime"), NO_STATUS("nostatus");

    public static final String MESSAGE_CONSTRAINTS ="List of valid Statuses: fulltime, parttime, nostatus if not assigned." ; //cleanup next time


    private final String status;

    Status(String status) {
        this.status = status;
    }

    /**
     * Gets the value of a status.
     *
     * @return The value of a status.
     */
    public String getValue() {
        return status;
    }

    /**
     * Translate a string into a Status enum if the string matches any Status values. Trims string.
     *
     * @param string String to be translated.
     * @return The translated Status if the string is valid, null object otherwise.
     */
    public static Status translateStringToStatus(String string) {
        String trimmedString = string.trim();
        Status resultStatus = null;
        for (Status r : Status.values()) {
            if (r.getValue().equalsIgnoreCase(trimmedString)) {
                resultStatus = r;
            }
        }
        return resultStatus;
    }

    /**
     * Checks if the string provided matches with any Status enum strings.
     *
     * @param test String to be checked.
     * @return boolean true if valid, false otherwise
     */
    public static boolean isValidStatus(String test) {
        String trimmedTest = test.trim();
        for (Status r : Status.values()) {
            if (r.getValue().equalsIgnoreCase(trimmedTest)) {
                return true;
            }
        }
        return false;
    }
}
