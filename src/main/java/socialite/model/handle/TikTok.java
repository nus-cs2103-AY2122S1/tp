package socialite.model.handle;

//import static java.util.Objects.requireNonNull;
import java.util.Optional;

import socialite.commons.util.AppUtil;

public class TikTok extends Handle {

    public static final String MESSAGE_CONSTRAINTS =
            "A TikTok Username should only consist of alphanumerical characters, underscores & periods.\n"
            + "It must not end with period.\n"
            + "https://support.tiktok.com/en/getting-started/setting-up-your-profile/changing-your-username";
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9._]*[a-zA-Z0-9_]$";

    /**
     * Constructor for {@code TikTok} object
     *
     * @param value TikTok handle
     */
    public TikTok(String value) {
        if (value != null && !value.equals("")) {
            AppUtil.checkArgument(isValidHandle(value), MESSAGE_CONSTRAINTS);
        }
        this.value = Optional.ofNullable(value);
    }

    /**
     * Checks if given tiktok handle is valid
     */
    public static boolean isValidHandle(String value) {
        return value.matches(VALIDATION_REGEX);
    }

    @Override
    public String getUrl() {
        return "https://www.tiktok.com/@" + this.get();
    }

    @Override
    public String toString() {
        return this.value.orElse("");
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || (obj instanceof TikTok && value.equals(((TikTok) obj).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
