package socialite.model.handle;

import java.util.Optional;

import socialite.commons.util.AppUtil;

/**
 * Represents a Person's Instagram handle in SociaLite.
 * Guarantees: immutable; handle is valid as declared in {@link #isValidHandle(String)}
 */
public class Instagram extends Handle {

    public static final String MESSAGE_CONSTRAINTS =
            "Instagram handles should only contain alphanumeric characters, periods & underscores.\n"
            + "A handle is limited to 30 characters and can't use other punctuation marks.\n"
            + "Furthermore, it cannot start or end with a period or an underscore.\n"
            + "Consecutive periods or underscores cannot appear in the handle either.\n"
            + "Lastly, the handle should not contain \".com\" or \".net\"\n"
            + "https://tinyurl.com/instaHandle";
    public static final String VALIDATION_REGEX =
            "^((?!.*^\\.)(?!.*^_)[a-zA-Z0-9._](?!(\\.com|\\.net)+$)(?!.*\\.\\.)(?!.*__)(?!.*\\._)(?!.*_\\.)(?!.*\\.$)"
                    + "(?!.*_$)){1,30}$";

    /**
     * Constructs a {@code Instagram}.
     *
     * @param handle A valid Instagram handle.
     */
    public Instagram(String handle) {
        if (handle != null && !handle.equals("")) {
            AppUtil.checkArgument(isValidHandle(handle), MESSAGE_CONSTRAINTS);
        }
        value = Optional.ofNullable(handle);
    }

    /**
     * Returns if a given input is a valid Facebook username.
     */
    public static boolean isValidHandle(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String getUrl() {
        return "https://www.instagram.com/" + this.get();
    }

    @Override
    public String toString() {
        return value.orElse("");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Instagram // instanceof handles nulls
                && value.equals(((Instagram) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
