package seedu.address.model.person;

/**
 * This class stands for every possible role for a staff
 */
public enum Role implements Field {
    //Can add more later
    KITCHEN("kitchen"), BARTENDER("bartender"), FLOOR("floor"), NO_ROLE("norole");

    public static final String STORAGE_WRONG_ROLE_MESSAGE =
            "List of valid Roles: kitchen, bartender, floor. (norole if no role is assigned). The role norole"
            + " cannot be together with other roles.";

    public static final String MESSAGE_CONSTRAINTS = "List of valid Roles: kitchen, bartender, floor.";

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
     * Translate a string into a Role enum if the string matches any Role values except for norole.
     * Trims string.
     * @param string String to be translated.
     * @return The translated Role if the string is valid.
     * @throws IllegalArgumentException if the string is invalid.
     */
    public static Role translateStringToRole(String string) throws IllegalArgumentException {
        Role result = translateStringToRoleWithNoRole(string);
        if (result.equals(Role.NO_ROLE)) {
            throw new IllegalArgumentException("String provided does not match any of the valid roles. "
                    + MESSAGE_CONSTRAINTS);
        }
        return result;
    }

    /**
     * Translate a string into a Role enum if the string matches any Role values. Trims string.
     *
     * @param string String to be translated.
     * @return The translated Role if the string is valid.
     * @throws IllegalArgumentException if the string is invalid.
     */
    public static Role translateStringToRoleWithNoRole(String string) throws IllegalArgumentException {
        String trimmedString = string.trim();
        Role resultRole = null;
        for (Role r : Role.values()) {
            if (r.getValue().equalsIgnoreCase(trimmedString)) {
                resultRole = r;
            }
        }
        if (resultRole == null) {
            throw new IllegalArgumentException("String provided does not match any roles. " + MESSAGE_CONSTRAINTS);
        } else {
            return resultRole;
        }

    }

    /**
     * Returns if a given string is a valid Role.
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


    @Override
    public String toString() {
        return this.role;
    }
}
