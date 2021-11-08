package seedu.address.commons.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents a version with major, minor and patch number.
 */
public class Version implements Comparable<Version> {

    /**
     * Sets the version regex.
     */
    public static final String VERSION_REGEX = "V(\\d+)\\.(\\d+)\\.(\\d+)(ea)?";

    private static final String EXCEPTION_STRING_NOT_VERSION = "String is not a valid Version. %s";

    private static final Pattern VERSION_PATTERN = Pattern.compile(VERSION_REGEX);

    private final int major;
    private final int minor;
    private final int patch;
    private final boolean isEarlyAccess;

    /**
     * Constructs a {@code Version} with the given version details.
     */
    public Version(int major, int minor, int patch, boolean isEarlyAccess) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
        this.isEarlyAccess = isEarlyAccess;
    }

    /**
     * Gets the major from {@code major}.
     *
     * @return int of the major.
     */
    public int getMajor() {
        return major;
    }

    /**
     * Gets the minor from {@code minor}.
     *
     * @return int of the minor.
     */
    public int getMinor() {
        return minor;
    }

    /**
     * Gets the patch from {@code patch}.
     *
     * @return int of the patch.
     */
    public int getPatch() {
        return patch;
    }

    /**
     * Returns whether is early access.
     *
     * @return boolean ture is early access.
     */
    public boolean isEarlyAccess() {
        return isEarlyAccess;
    }

    /**
     * Parses a version number string in the format V1.2.3.
     *
     * @param versionString version number string.
     * @return a version object.
     */
    @JsonCreator
    public static Version fromString(String versionString) throws IllegalArgumentException {
        Matcher versionMatcher = VERSION_PATTERN.matcher(versionString);

        if (!versionMatcher.find()) {
            throw new IllegalArgumentException(String.format(EXCEPTION_STRING_NOT_VERSION, versionString));
        }

        return new Version(Integer.parseInt(versionMatcher.group(1)),
                Integer.parseInt(versionMatcher.group(2)),
                Integer.parseInt(versionMatcher.group(3)),
                versionMatcher.group(4) == null ? false : true);
    }

    /**
     * Overrides the toString for Version class.
     *
     * @return String for version.
     */
    @JsonValue
    public String toString() {
        return String.format("V%d.%d.%d%s", major, minor, patch, isEarlyAccess ? "ea" : "");
    }

    /**
     * Overrides the compareTo method for Version class.
     */
    @Override
    public int compareTo(Version other) {
        if (major != other.major) {
            return major - other.major;
        }
        if (minor != other.minor) {
            return minor - other.minor;
        }
        if (patch != other.patch) {
            return patch - other.patch;
        }
        if (isEarlyAccess == other.isEarlyAccess()) {
            return 0;
        }
        if (isEarlyAccess) {
            return -1;
        }
        return 1;
    }

    /**
     * Overrides the equals method for version class.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Version)) {
            return false;
        }
        final Version other = (Version) obj;

        return compareTo(other) == 0;
    }

    /**
     * Overrides the hashCode for version class.
     */
    @Override
    public int hashCode() {
        String hash = String.format("%03d%03d%03d", major, minor, patch);
        if (!isEarlyAccess) {
            hash = "1" + hash;
        }
        return Integer.parseInt(hash);
    }
}
