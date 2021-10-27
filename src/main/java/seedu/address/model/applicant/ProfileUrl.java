package seedu.address.model.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Represents an Applicant's name in MrTechRecruiter.
 * Guarantees: immutable; is valid as declared in {@link #isValidUrl(String)}
 */
public class ProfileUrl {

    public static final String MESSAGE_CONSTRAINTS =
            "Profile urls must be valid github and linkedin urls.";

    private static final ProfileUrl EMPTY_PROFILE_URL = new ProfileUrl("Empty");
    public final String url;

    /**
     * Constructs a {@code ProfileUrl}.
     *
     * @param url A valid github or linkedin url.
     */
    public ProfileUrl(String url) {
        requireNonNull(url);
        checkArgument(isValidUrl(url), MESSAGE_CONSTRAINTS);
        this.url = url;
    }

    /**
     * Returns the one single instance of empty profile url.
     *
     * @return the one single instance of empty profile url.
     */
    public static ProfileUrl emptyProfileUrl() {
        return EMPTY_PROFILE_URL;
    }

    /**
     * Returns true if a given string is a valid url.
     *
     * @param gitHubUrl given url to test.
     * @return boolean whether this url is a valid url.
     */
    public static boolean isValidUrl(String gitHubUrl) {
        try {
            new URL(gitHubUrl);
            return true;
        } catch (MalformedURLException malformedUrlException) {
            return false;
        }
    }

    /**
     * Checks whether this ProfileUrl is empty or not, i.e. testing whether there is an existing profile.
     *
     * @return true is there is a profile stored, false if there is no profile.
     */
    public boolean hasProfile() {
        return this != EMPTY_PROFILE_URL;
    }

    public String getUrlString() {
        return this.url;
    }
}
