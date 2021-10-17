package seedu.tracker.model;

import javafx.beans.value.ObservableObjectValue;

public interface ReadOnlyUserInfo {
    /**
     * Returns an user info object.
     */
    ObservableObjectValue<UserInfo> getUserInfo();
}
