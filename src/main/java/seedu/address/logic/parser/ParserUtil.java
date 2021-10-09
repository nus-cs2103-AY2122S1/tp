package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;
import seedu.address.model.person.EmploymentType;
import seedu.address.model.person.ExpectedSalary;
import seedu.address.model.person.Experience;
import seedu.address.model.person.LevelOfEducation;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
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

    /** Parses a {@code String role} into an {@code Role}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param role String to be parsed into a Role object.
     * @return Role object
     * @throws ParseException if the given {@code Role} is invalid.
     */
    public static Role parseRole(String role) throws ParseException {
        requireNonNull(role);
        String trimmedRole = role.trim();
        if (!Role.isValidRole(trimmedRole)) {
            throw new ParseException(Role.MESSAGE_CONSTRAINTS);
        }
        return new Role(trimmedRole);
    }

    /**
     * Parses a {@code String employmentType} into an {@code EmploymentType}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param employmentType String to be parsed into an EmploymentType object.
     * @return EmploymentType object
     * @throws ParseException if the given {@code EmploymentType} is invalid.
     */
    public static EmploymentType parseEmploymentType(String employmentType) throws ParseException {
        requireNonNull(employmentType);
        String trimmedEmploymentType = employmentType.trim();
        if (!EmploymentType.isValidEmploymentType(trimmedEmploymentType)) {
            throw new ParseException(EmploymentType.MESSAGE_CONSTRAINTS);
        }
        return new EmploymentType(trimmedEmploymentType);
    }

    /**
     * Parses a {@code String expectedSalary} into an {@code ExpectedSalary}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param expectedSalary String to be parsed into an ExpectedSalary object.
     * @return ExpectedSalary object
     * @throws ParseException if the given {@code expectedSalary} is invalid.
     */
    public static ExpectedSalary parseExpectedSalary(String expectedSalary) throws ParseException {
        requireNonNull(expectedSalary);
        String trimmedExpectedSalary = expectedSalary.trim();
        if (!ExpectedSalary.isValidExpectedSalary(trimmedExpectedSalary)) {
            throw new ParseException(ExpectedSalary.MESSAGE_CONSTRAINTS);
        }
        return new ExpectedSalary(trimmedExpectedSalary);
    }

    /**
     * Parses a {@code String levelOfEducation} into a {@code LevelOfEducation}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param levelOfEducation String to be parsed into a LevelOfEducation object.
     * @return LevelOfEducation object
     * @throws ParseException if the given {@code LevelOfEducation} is invalid.
     */
    public static LevelOfEducation parseLevelOfEducation(String levelOfEducation) throws ParseException {
        requireNonNull(levelOfEducation);
        String trimmedLevelOfEducation = levelOfEducation.trim();
        if (!LevelOfEducation.isValidLevelOfEducation(trimmedLevelOfEducation)) {
            throw new ParseException(LevelOfEducation.MESSAGE_CONSTRAINTS);
        }
        return new LevelOfEducation(trimmedLevelOfEducation);
    }

    /**
     * Parses a {@code Integer experience} into an {@code Experience}.
     *
     * @param experience String to be parsed into an Experience object.
     * @return Experience object
     * @throws ParseException if the given {@code experience} is invalid.
     */
    public static Experience parseExperience(String experience) throws ParseException {
        requireNonNull(experience);
        String years = experience.trim();
        if (!Experience.isValidExperience(years)) {
            throw new ParseException(Experience.MESSAGE_CONSTRAINTS);
        }
        return new Experience(years);
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
}
