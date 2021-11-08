package seedu.address.commons.status;

import javafx.beans.property.SimpleStringProperty;

/**
 * Represents the login status.
 */
public enum LoginStatus {

    STAFF("STAFF"),
    MANAGER("MANAGER");

    /**
     * Represents the static the simple string property.
     */
    public static final SimpleStringProperty CURRENT_STATUS = new SimpleStringProperty(STAFF.value);

    private final String value;

    /**
     * Constructs {@code LoginStatus} with the given value.
     */
    LoginStatus(String value) {
        this.value = value;
    }

    /**
     * Gets login status.
     *
     * Returns {@code LoginStatus} the status of login.
     */
    public static LoginStatus getLoginStatus() {
        if (CURRENT_STATUS.getValue().equals(STAFF.value)) {
            return STAFF;
        } else {
            return MANAGER;
        }
    }

    /**
     * Resets {@code CURRENT_STATUS} by {@code LoginStatus}.
     */
    public static void setLoginStatus(LoginStatus loginStatus) {
        CURRENT_STATUS.setValue(loginStatus.value);
    }
}
