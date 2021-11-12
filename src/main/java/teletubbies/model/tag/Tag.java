package teletubbies.model.tag;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.function.Function;

import teletubbies.commons.core.UserProfile.Role;
import teletubbies.commons.util.AppUtil;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final String[] RESERVED_TAG_NAMES = new String[] { "CompletionStatus" };
    public static final Function<String, String> RESERVED_NAME_CONSTRAINTS = (name) ->
        name + " is a reserved tag name. Please choose another name";
    public static final String ALPHANUMERIC_NAME_CONSTRAINTS = "Tag names should be alphanumeric";
    public static final String MESSAGE_CONSTRAINTS = "Tag names should be alphanumeric and cannot be reserved or empty";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String tagName;
    public final Role[] editAccessRoles;
    private String tagValue;

    /**
     * Constructs a {@code Tag}
     *
     * @param tagName A valid tag name
     * @param tagValue A tag value string
     * @param editRoles Roles that can edit the tag
     */
    public Tag(String tagName, String tagValue, Role[] editRoles, boolean isTestTag) {
        requireNonNull(tagName);
        AppUtil.checkArgument(isAlphanumericTagName(tagName), ALPHANUMERIC_NAME_CONSTRAINTS);
        if (!isTestTag) {
            AppUtil.checkArgument(isNonReservedTagName(tagName), RESERVED_NAME_CONSTRAINTS.apply(tagName));
        }
        this.tagName = tagName;
        this.tagValue = tagValue;
        this.editAccessRoles = editRoles;
    }

    /**
     * Constructs a {@code Tag} without name validation
     *
     * @param tagName A valid tag name
     * @param editRoles
     */
    protected Tag(String tagName, Role[] editRoles) {
        this(tagName, null, editRoles, true);
    }

    /**
     * Constructs a {@code Tag} based on whether tag is
     * supervisor only
     *
     * @param tagName name of tag
     * @param tagValue value of tag
     * @param isSupervisorOnly true if tag is supervisor only
     */
    public Tag(String tagName, String tagValue, boolean isSupervisorOnly) {
        this(tagName, tagValue, getRolesForEditAccess(isSupervisorOnly), false);
    }

    /**
     * Constructs a {@code Tag} with no tag value and accessible by
     * all users.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        this(tagName, "", Role.values(), false);
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return isAlphanumericTagName(test) && isNonReservedTagName(test);
    }

    /**
     * Returns true if a given string is an alphanumeric tag name.
     */
    public static boolean isAlphanumericTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a non-reserved tag name.
     */
    public static boolean isNonReservedTagName(String test) {
        return !Arrays.asList(RESERVED_TAG_NAMES).contains(test);
    }

    /**
     * Get array of roles that can edit the tag, based on
     * whether tag is supervisor only
     *
     * @param isSupervisorOnly true if tag is supervisor-only
     * @return array of roles
     */
    public static Role[] getRolesForEditAccess(boolean isSupervisorOnly) {
        if (isSupervisorOnly) {
            return new Role[]{ Role.SUPERVISOR };
        }
        return new Role[]{ Role.SUPERVISOR, Role.TELEMARKETER };
    }

    /**
     * Checks if the given role can edit the tag
     *
     * @param role User's role
     * @return whether tag can be edited by role
     */
    public boolean isEditableByRole(Role role) {
        return Arrays.asList(editAccessRoles).contains(role);
    }

    /**
     * Set the value of the tag
     */
    public void setTagValue(String value) {
        this.tagValue = value;
    }

    /**
     * Get tag's value
     *
     * @return tag value
     */
    public String getTagValue() {
        return this.tagValue;
    }

    /**
     *
     *
     * @param other
     * @return
     */
    public boolean equalsNameAndValue(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tag // instanceof handles nulls
                && tagName.equals(((Tag) other).tagName)
                && tagValue.equals(((Tag) other).tagValue));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tag // instanceof handles nulls
                && tagName.equals(((Tag) other).tagName));
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        boolean valueEmpty = isNull(tagValue) || tagValue.equals("");
        return tagName + (valueEmpty ? "" : ": " + tagValue);
    }

}
