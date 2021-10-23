package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;


/**
 * Represents a Person's social handle in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidSocialHandle(String)}
 */
public class SocialHandle {

    public static final String MESSAGE_CONSTRAINTS = "Social handle should be "
            + "a single string without whitespaces, should starts with "
            + "the platform name in 2 character, followed by a colon (:), and followed "
            + "by the handle name"
            + "and handle name should not be blank";

    /*
     * The first 2 characters of the social handle should be the platform name
     * in shorthand form, followed by a colon (:), and followed by the handle name.
     * Handle name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[a-z]{2}:\\S*";

    public final String platform;
    public final String value;

    /**
     * Constructs an {@code SocialHandle}.
     *
     * @param socialHandle A valid handle.
     */
    public SocialHandle(String socialHandle) {
        requireNonNull(socialHandle);
        if (socialHandle.isEmpty()) {
            this.platform = "";
            this.value = "";
            return;
        }
        checkArgument(isValidSocialHandle(socialHandle), MESSAGE_CONSTRAINTS);
        assert socialHandle.contains(":") : "String does not contain :," + socialHandle;
        String[] s = socialHandle.split(":", 2);
        assert s.length == 2 : "Length of split string not 2:" + socialHandle;
        this.platform = s[0];
        this.value = s[1];
    }

    /**
     * Constructs a {@SocialHandle}
     *
     * @param platform
     * @param value
     */
    public SocialHandle(String platform, String value) {
        checkArgument(isValidSocialHandle(platform, value), MESSAGE_CONSTRAINTS);
        this.platform = platform;
        this.value = value;
    }

    /**
     * Returns true if a given string is a valid social handle.
     */
    public static boolean isValidSocialHandle(String test) {
        if (test.isEmpty()) {
            return true;
        }
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given {@code platform} and {@code value} is a valid social handle.
     */
    public static boolean isValidSocialHandle(String platform, String value) {
        if (platform.isEmpty() && value.isEmpty()) {
            return true;
        }
        String test = platform + ":" + value;
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if other {@code SocialHandle} has the same {@code platform}.
     */
    public boolean isSamePlatform(SocialHandle other) {
        return other == this // short circuit if same object
                || this.platform.equals(other.platform); // state check
    }

    @Override
    public String toString() {
        return "[" + platform + ":" + value + "]";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SocialHandle // instanceof handles nulls
                && value.equals(((SocialHandle) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
