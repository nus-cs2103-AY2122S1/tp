package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.interview.Interview;
import seedu.address.model.notes.Notes;
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

    public static final String MESSAGE_INVALID_INDEX = "Index should be a non-zero unsigned integer.";

    public static final String MESSAGE_DUPLICATE_INDEX = "There should not be any duplicate indexes.";

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
     * Parses multiple {@code oneBasedIndex} into {@code Index} and returns them in a sorted array.
     * The array is sorted from the largest to smallest index so that any invalid (out of range)
     * indexes will be encountered first.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if the specified indexes are invalid (not non-zero unsigned integer)
     * or if there are duplicates.
     */
    public static Index[] parseMultipleIndex(String oneBasedIndexes) throws ParseException {
        String trimmedIndexes = oneBasedIndexes.trim();
        String[] trimmedIndexesList = trimmedIndexes.split("\\s+");

        Index[] indexesList = new Index[trimmedIndexesList.length];
        for (int i = 0; i < trimmedIndexesList.length; i++) {
            String trimmedIndex = trimmedIndexesList[i];
            if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
                throw new ParseException(MESSAGE_INVALID_INDEX);
            }
            indexesList[i] = Index.fromOneBased(Integer.parseInt(trimmedIndex));
        }
        Arrays.sort(indexesList, (i1, i2) -> i2.getZeroBased() - i1.getZeroBased());

        // Checking for duplicates
        if (Arrays.stream(indexesList)
                .anyMatch(index -> Collections.frequency(Arrays.asList(indexesList), index) > 1)) {
            throw new ParseException(MESSAGE_DUPLICATE_INDEX);
        }

        return indexesList;
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param name {@code String} to be parsed into a {@code Name}
     * @return {@code Name}
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
     * @param phone {@code String} to be parsed into a {@code Phone}
     * @return {@code Phone}
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
     * @param email {@code String} to be parsed into an {@code Email}
     * @return {@code Email}
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
     * @param role {@code String} to be parsed into a {@code Role}
     * @return {@code Role}
     * @throws ParseException if the given {@code Role} is invalid
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
     * @param employmentType String to be parsed into an EmploymentType object
     * @return {@code EmploymentType}
     * @throws ParseException if the given {@code EmploymentType} is invalid
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
     * @param expectedSalary String to be parsed into an ExpectedSalary object
     * @return {@code ExpectedSalary}
     * @throws ParseException if the given {@code expectedSalary} is invalid
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
     * @param levelOfEducation String to be parsed into a LevelOfEducation object
     * @return {@code LevelOfEducation}
     * @throws ParseException if the given {@code LevelOfEducation} is invalid
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
     * @param experience {@code String} to be parsed into an {@code Experience}
     * @return {@code Experience}
     * @throws ParseException if the given {@code experience} is invalid
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
     * @param tag {@code String} to be parsed into a {@code Tag}
     * @return {@code Tag}
     * @throws ParseException if the given {@code tag} is invalid
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
     *
     * @param tags {@code Collection<String>} to be parsed
     * @return {@code Set<Tag>}
     * @throws ParseException if any {@code String} in {@code Collection<String>} cannot be parsed into {@code Tag}
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
     * Parses a {@code String interview} into a {@code Interview}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param interview {@code String} to be parsed into a {@code Interview}
     * @return {@code Optional<Interview>}
     * @throws ParseException if the given {@code interview} is invalid
     */
    public static Optional<Interview> parseInterview(String interview) throws ParseException {
        requireNonNull(interview);
        String trimmedTime = interview.trim();
        if (interview.isEmpty()) { // parse empty interview
            return Optional.of(Interview.EMPTY_INTERVIEW);
        }

        if (!Interview.isValidInterviewTime(trimmedTime)) {
            throw new ParseException(Interview.MESSAGE_CONSTRAINTS);
        }
        return Optional.of(new Interview(interview));
    }

    /**
     * Parses a {@code String notes} into a {@code Notes}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param notes {@code String} to be parsed into a {@code Notes}
     * @return {@code Optional<Notes>}
     */
    public static Optional<Notes> parseNotes(String notes) throws ParseException {
        requireNonNull(notes);
        return Optional.of(new Notes(notes));
    }


}
