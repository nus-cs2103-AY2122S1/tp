package socialite.model.handle;

import java.util.Optional;

import socialite.commons.util.AppUtil;

/**
 * Represents a Person's Facebook username in SociaLite.
 * Guarantees: immutable; handle is valid as declared in {@link #isValidHandle(String)}
 */
public class Facebook extends Handle {

    public static final String MESSAGE_CONSTRAINTS =
            "A Facebook username should only contain alphanumeric characters and full stops.\n"
            + "It must also be at least 5 characters long.\n"
            + "Furthermore, it cannot start or end with a full stop.\n"
            + "Neither can full stops appear consecutively in the username.\n"
            + "Lastly, the username should not contain \".com\" or \".net\"\n"
            + "https://www.facebook.com/help/105399436216001/?helpref=uf_share";
    public static final String VALIDATION_REGEX =
            "^((?!.*^\\.)[a-zA-Z0-9.](?!(\\.com|\\.net)+$)(?!.*\\.\\.)(?!.*\\.$)){5,}$";

    /**
     * Constructs a {@code Facebook}.
     *
     * @param handle A valid Facebook username.
     */
    public Facebook(String handle) {
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
        return "https://www.facebook.com/" + this.get();
    }

    @Override
    public String toString() {
        return value.orElse("");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Facebook // instanceof handles nulls
                && value.equals(((Facebook) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
