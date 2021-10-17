package seedu.tracker.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.tracker.commons.exceptions.IllegalValueException;
import seedu.tracker.model.ReadOnlyUserInfo;
import seedu.tracker.model.UserInfo;

/**
 * An Immutable UserInfo that is serializable to JSON format.
 */
@JsonRootName(value = "userInfo")
public class JsonSerializableUserInfo {
    private final JsonAdaptedUserInfo userInfo;

    /**
     * Constructs a {@code JsonSerializableUserInfo} with the given user info details.
     */
    @JsonCreator
    public JsonSerializableUserInfo(@JsonProperty("userInfo") JsonAdaptedUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    /**
     * Converts a given {@code ReadOnlyUserInfo} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableUserInfo}.
     */
    public JsonSerializableUserInfo(ReadOnlyUserInfo source) {
        userInfo = new JsonAdaptedUserInfo(source.getUserInfo().get());
    }

    /**
     * Converts this user info into the model's {@code UserInfo} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public UserInfo toModelType() throws IllegalValueException {
        UserInfo info = userInfo.toModelType();
        return info;
    }
}
