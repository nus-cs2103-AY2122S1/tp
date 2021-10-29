package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.SocialHandle;

public class JsonAdaptedSocialHandle {

    public final String platform;
    public final String value;

    /**
     * Constructs a {@code JsonAdaptedSocialHandle} with the given {@code platform} and {@code value}.
     */
    @JsonCreator
    public JsonAdaptedSocialHandle(@JsonProperty("platform") String platform, @JsonProperty("value") String value) {
        this.platform = SocialHandle.parsePlatform(platform);
        this.value = value;
    }

    /**
     * Constructs an {@code JsonAdaptedSocialHandle}.
     *
     * @param socialHandle A valid handle.
     */
    public JsonAdaptedSocialHandle(String socialHandle) {
        if (socialHandle.isEmpty() || !socialHandle.contains(":")) {
            this.platform = "";
            this.value = "";
            return;
        }
        String[] s = socialHandle.split(":", 2);
        assert s.length == 2 : "Length of split string not 2:" + socialHandle;
        this.platform = SocialHandle.parsePlatform(s[0]);
        this.value = s[1].strip();
    }

    /**
     * Converts a given {@code SocialHandle} into this class for Jackson use.
     */
    public JsonAdaptedSocialHandle(SocialHandle source) {
        this.platform = source.platform;
        this.value = source.value;
    }

    public String getPlatform() {
        return platform;
    }

    public String getValue() {
        return value;
    }

    /**
     * Converts this Jackson-friendly adapted social handle object into the model's {@code SocialHandle} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted social handle.
     */
    public SocialHandle toModelType() throws IllegalValueException {
        if (!SocialHandle.isValidSocialHandle(platform, value)) {
            throw new IllegalValueException(SocialHandle.MESSAGE_CONSTRAINTS);
        }
        return new SocialHandle(platform, value);
    }

}
