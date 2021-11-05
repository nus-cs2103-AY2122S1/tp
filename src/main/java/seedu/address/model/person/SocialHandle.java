package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Objects;

/**
 * Represents a Person's social handle in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidSocialHandle(String)}
 */
public class SocialHandle {

    public static final String MESSAGE_CONSTRAINTS = "Social handle should be in PLATFORM:USERID format.\n"
            + "USERID should be tied to a specific account of the PLATFORM. \n"
            + "Supported PLATFORM: "
            + "Instagram (ig), "
            + "Telegram (tg), "
            + "Linkedin (in), "
            + "Facebook (fb), "
            + "Snapchat (sc), "
            + "Twitter (tw), "
            + "Github (gh), "
            + "Discord (dc)"
            + "\n"
            + "(e.g. tg:tanjj3298)\n"
            + "Leaving it blank will remove all the Social Handles.";

    public static final String PLATFORM_CONSTRAINTS = "Only the following platforms are supported: "
            + "Instagram (ig), "
            + "Telegram (tg), "
            + "Linkedin (in), "
            + "Facebook (fb), "
            + "Snapchat (sc), "
            + "Twitter (tw), "
            + "Github (gh), "
            + "Discord (dc)";

    public static final String USERID_CONSTRAINTS = "USERID should not contain whitespaces.";

    public static final String USERID_LENGTH_CONSTRAINTS = "USERID is too long. "
            + "Please check if you have input correctly";

    public static final String CLEAR_CONSTRAINTS = "Unable to clear social handles if there are"
            + " multiple social handle fields.";

    public static final int MAXIMUM_USERID_LENGTH = 255;

    /*
     * The value should not contain whitespace,
     * and it should not be blank.
     */
    public static final String VALIDATION_REGEX = "\\S+";

    private static final HashSet<String> SUPPORTED_PLATFORMS = getSupportedPlatforms();

    private static final Hashtable<String, String> PLATFORM_MAPPING = getPlatformMapping();

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
        checkArgument(socialHandle.contains(":"), MESSAGE_CONSTRAINTS);
        assert socialHandle.contains(":") : "String does not contain colon (:), " + socialHandle;
        String[] s = socialHandle.split(":", 2);
        assert s.length == 2 : "Length of split string not 2:" + socialHandle;
        String platform = parsePlatform(s[0]);
        checkArgument(isValidPlatform(platform), PLATFORM_CONSTRAINTS);
        String value = s[1].strip();
        checkArgument(isValidValue(value), "Invalid social handle: " + socialHandle
                + "\n" + USERID_CONSTRAINTS);
        this.platform = platform;
        this.value = value;
    }

    /**
     * Constructs a {@code SocialHandle}
     */
    public SocialHandle(String platform, String value) {
        requireAllNonNull(platform, value);
        if (value.isEmpty()) {
            this.value = "";
            if (platform.isEmpty()) {
                this.platform = "";
                return;
            } else {
                checkArgument(isValidPlatform(platform), PLATFORM_CONSTRAINTS);
                this.platform = platform;
                return;
            }
        }
        checkArgument(isValidSocialHandle(platform, value), MESSAGE_CONSTRAINTS);
        this.platform = platform;
        this.value = value;
    }

    /**
     * Constructs an empty {@code SocialHandle}
     */
    public SocialHandle() {
        this.platform = "";
        this.value = "";
    }

    /**
     * Returns true if a given string is a valid social handle.
     */
    public static boolean isValidSocialHandle(String test) {
        if (!test.contains(":")) {
            return false;
        }
        String[] s = test.split(":", 2);
        assert s.length == 2 : "Length of split string not 2: " + test;
        return isValidPlatform(parsePlatform(s[0])) && isValidValue(s[1]);
    }

    /**
     * Returns true if a given {@code platform} and {@code value} is a valid social handle.
     */
    public static boolean isValidSocialHandle(String platform, String value) {
        return isValidPlatform(platform) && isValidValue(value);
    }

    /**
     * Returns true if a given {@code platform} is valid.
     */
    public static boolean isValidPlatform(String platform) {
        return SUPPORTED_PLATFORMS.contains(platform);
    }

    /**
     * Returns true if a given {@code value} is valid.
     */
    public static boolean isValidValue(String value) {
        if (value.length() > MAXIMUM_USERID_LENGTH) {
            return false;
        }
        return value.matches(VALIDATION_REGEX);
    }

    /**
     * Parse platform to a standardized form
     */
    public static String parsePlatform(String platform) {
        String formattedPlatform = platform.strip().toLowerCase();
        if (PLATFORM_MAPPING.containsKey(formattedPlatform)) {
            return PLATFORM_MAPPING.get(formattedPlatform);
        }
        return formattedPlatform;
    }

    private static Hashtable<String, String> getPlatformMapping() {
        Hashtable<String, String> tmp = new Hashtable<>();
        tmp.put("instagram", "ig");
        tmp.put("telegram", "tg");
        tmp.put("tl", "tg");
        tmp.put("linkedin", "in");
        tmp.put("ln", "in");
        tmp.put("facebook", "fb");
        tmp.put("snapchat", "sc");
        tmp.put("twitter", "tw");
        tmp.put("github", "gh");
        tmp.put("discord", "dc");
        return tmp;
    }

    private static HashSet<String> getSupportedPlatforms() {
        return new HashSet<>(new ArrayList<>(Arrays.asList(
                "ig", "gh", "in", "dc", "fb", "sc", "tg", "tw")));
    }

    /**
     * Returns true if other {@code SocialHandle} has the same {@code platform}.
     */
    public boolean isSamePlatform(SocialHandle other) {
        return other == this // short circuit if same object
                || this.platform.equals(other.platform); // state check
    }

    /**
     * Returns true if {@code platform} and {@code value} are valid.
     */
    public boolean isValid() {
        return isValidPlatform(platform) && isValidValue(value);
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
        return Objects.hash(platform, value);
    }

}
