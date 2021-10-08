package seedu.address.model.person;

/**
 * This class stands for every possible role for a staff
 */
public enum Role {
    //Can add more later
    MANAGER("manager"), DIRECTOR("director"), HR("hr"), NO_ROLE("norole");

    public static final String MESSAGE_CONSTRAINTS ="List of valid Roles: manager, director, hr, norole if not assigned."; //cleanup next time

    private final String role;

    Role(String role) {
        this.role = role;
    }

    /**
     * Gets the value of a role.
     *
     * @return The value of a role.
     */
    public String getValue() {
        return role;
    }

    /**
     * Translate a string into a Role enum if the string matches any Role values. Trims string.
     *
     * @param string String to be translated.
     * @return The translated Role if the string is valid, null object otherwise.
     */
    public static Role translateStringToRole(String string) {
        String trimmedString = string.trim();
        Role resultRole = null;
        for (Role r : Role.values()) {
            if (r.getValue().equalsIgnoreCase(trimmedString)) {
                resultRole = r;
            }
        }
        return resultRole;
    }

    /**
     * Checks if the string provided matches with any Role enum strings.
     *
     * @param test String to be checked.
     * @return boolean true if valid, false otherwise
     */
    public static boolean isValidRole(String test) {
        String trimmedTest = test.trim();
        for (Role r : Role.values()) {
            if (r.getValue().equalsIgnoreCase(trimmedTest)) {
                return true;
            }
        }
        return false;
    }
}
