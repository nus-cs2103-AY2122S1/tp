package socialite.model.handle;

import java.util.Optional;

import socialite.commons.util.AppUtil;

public class Twitter extends Handle {

    public static final String MESSAGE_CONSTRAINTS =
            "A Twitter username should only consist of alphanumerical characters and underscores.\n"
            + "It must also be between 4-15 characters long.\n"
            + "https://help.twitter.com/en/managing-your-account/twitter-username-rules";
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9_]{4,15}$";

    /**
     * Constructor for {@code Twitter} object
     *
     * @param value Twitter handle
     */
    public Twitter(String value) {
        if (value != null && !value.equals("")) {
            AppUtil.checkArgument(isValidHandle(value), MESSAGE_CONSTRAINTS);
        }
        this.value = Optional.ofNullable(value);
    }

    /**
     * Checks if given twitter handle is valid
     */
    public static boolean isValidHandle(String value) {
        return value.matches(VALIDATION_REGEX);
    }

    @Override
    public String getUrl() {
        return "https://www.twitter.com/" + this.get();
    }

    @Override
    public String toString() {
        return this.value.orElse("");
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || (obj instanceof Twitter && value.equals(((Twitter) obj).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
