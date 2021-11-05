package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nationality;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.SocialHandle;
import seedu.address.model.person.TutorialGroup;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            if (trimmedIndex.matches("\\d*[1-9]d*")) { // Number of any length, but must not be all 0s.
                // assume the total number of users is less than 2.147 billion.
                return Index.fromOneBased(Integer.MAX_VALUE);
            }
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String nationality} into an {@code Nationality}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code nationality} is invalid.
     */
    public static Nationality parseNationality(String nationality) throws ParseException {
        requireNonNull(nationality);
        String trimmedNationality = nationality.trim();
        if (!Nationality.isValidNationality(trimmedNationality)) {
            String exceptionMessage = "";

            // Check for suggestions
            if (Nationality.VALID_NATIONALITIES.size() > 0) {
                WordSuggestion nationalitiesSuggestion = new WordSuggestion(trimmedNationality,
                        Nationality.VALID_NATIONALITIES, 2, true);

                exceptionMessage = nationalitiesSuggestion.getSuggestedWords();
            }

            if (exceptionMessage.equals("")) {
                exceptionMessage = Nationality.MESSAGE_NOT_FOUND;
            }

            throw new ParseException(exceptionMessage);
        }
        return new Nationality(trimmedNationality);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tutorialGroup} into an {@code TutorialGroup}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tutorialGroup} is invalid.
     */
    public static TutorialGroup parseTutorialGroup(String tutorialGroup) throws ParseException {
        requireNonNull(tutorialGroup);
        String trimmedTutorialGroup = tutorialGroup.trim();
        if (!TutorialGroup.isValidTutorialGroup(trimmedTutorialGroup)) {
            throw new ParseException(TutorialGroup.MESSAGE_CONSTRAINTS);
        }
        return new TutorialGroup(trimmedTutorialGroup);
    }

    /**
     * Parses a {@code String gender} into an {@code Gender}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code gender} is invalid.
     */
    public static Gender parseGender(String gender) throws ParseException {
        requireNonNull(gender);
        String trimmedGender = gender.trim();
        if (!Gender.isValidGender(trimmedGender)) {
            throw new ParseException(Gender.MESSAGE_CONSTRAINTS);
        }
        return new Gender(trimmedGender);
    }

    /**
     * Parses a {@code String remark} into an {@code Remark}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code remark} is invalid.
     */
    public static Remark parseRemark(String remark) throws ParseException {
        requireNonNull(remark);
        String trimmedRemark = remark.trim();
        if (!Remark.isValidRemark(trimmedRemark)) {
            throw new ParseException(Remark.MESSAGE_CONSTRAINTS);
        }
        return new Remark(trimmedRemark);
    }

    /**
     * Parses a {@code String alias}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code alias} is invalid.
     */
    public static String parseAlias(String alias) throws ParseException {
        requireNonNull(alias);

        return alias.trim();
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        String colour;
        if (trimmedTag.contains(":")) {
            int colonIndex = trimmedTag.indexOf(":");
            colour = trimmedTag.substring(colonIndex + 1);
            trimmedTag = trimmedTag.substring(0, colonIndex);
            if (!Tag.isValidTagColour(colour)) {
                throw new ParseException(Tag.MESSAGE_CONSTRAINTS_COLOURS);
            }
            if (!Tag.isValidTagName(trimmedTag)) {
                throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
            }
            return new Tag(trimmedTag, colour);
        }
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);


    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String socialHandle} into an {@code SocialHandle}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code socialHandle} is invalid.
     */
    public static SocialHandle parseSocialHandle(String socialHandle) throws ParseException {
        requireNonNull(socialHandle);
        // Could be added to show which part of input is the error message is referring to
        //String formattedInput = "[" + PREFIX_SOCIAL_HANDLE + socialHandle + "] ";
        if (socialHandle.isEmpty()) {
            return new SocialHandle(); // To delete all socialHandles
        }
        if (!socialHandle.contains(":")) {

            throw new ParseException(SocialHandle.MESSAGE_CONSTRAINTS);
        }
        String[] s = socialHandle.split(":", 2);
        String platform = SocialHandle.parsePlatform(s[0]);
        if (!SocialHandle.isValidPlatform(platform)) {
            throw new ParseException(SocialHandle.PLATFORM_CONSTRAINTS);
        }
        String username = s[1].trim();
        if (username.isEmpty()) {
            return new SocialHandle(platform, ""); // To delete the platform in socialHandles
        }
        // Defensive coding to prevent user dumping long text as username
        if (username.length() > SocialHandle.MAXIMUM_USERID_LENGTH) {
            throw new ParseException(SocialHandle.USERID_LENGTH_CONSTRAINTS);
        }
        if (!SocialHandle.isValidValue(username)) {
            throw new ParseException(SocialHandle.USERID_CONSTRAINTS);
        }
        return new SocialHandle(platform, username);
    }

    /**
     * Parses {@code Collection<String> socialHandles} into a {@code Set<SocialHandle>}.
     */
    public static Set<SocialHandle> parseSocialHandles(Collection<String> socialHandles) throws ParseException {
        requireNonNull(socialHandles);
        // Ensure only the last social handle of each social platform are kept
        boolean isClearSocialHandlesDetected = false;
        Hashtable<String, SocialHandle> socialHandleTable = new Hashtable<>();
        for (String socialHandle : socialHandles) {
            SocialHandle tmp = parseSocialHandle(socialHandle);
            if (tmp.platform.isEmpty()) {
                isClearSocialHandlesDetected = true;
            } else {
                socialHandleTable.put(tmp.platform, tmp);
            }
        }
        if (isClearSocialHandlesDetected) {
            if (socialHandleTable.isEmpty()) {
                return new HashSet<>();
            } else {
                throw new ParseException(SocialHandle.CLEAR_CONSTRAINTS);
            }
        }
        return new HashSet<>(socialHandleTable.values());
    }
}
