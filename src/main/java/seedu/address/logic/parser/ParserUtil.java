package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.GitHubId;
import seedu.address.model.person.Name;
import seedu.address.model.person.NusNetworkId;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.TutorialId;
import seedu.address.model.person.Type;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_FILEPATH = "File Path is invalid";

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
     * Parses {@code filePath} into an {@code Path} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified filepath is invalid (cannot be converted to a Path).
     */
    public static Path parseNewFilePath(String filePath) throws ParseException {
        requireNonNull(filePath);
        String trimmedFilePath = filePath.trim();
        if (!FileUtil.isValidPath(trimmedFilePath)) {
            throw new ParseException(MESSAGE_INVALID_FILEPATH);
        }
        return Paths.get(trimmedFilePath);
    }

    /**
     * Parses {@code filePath} into an {@code Path} and returns it.
     * @throws ParseException if the specified filepath is invalid (does not exist).
     */
    public static Path parseExistingFilePath(String filePath) throws ParseException {
        requireNonNull(filePath);
        String trimmedFilePath = filePath.trim();
        Path path = Paths.get(trimmedFilePath);
        if (!FileUtil.isFileExists(path)) {
            throw new ParseException(MESSAGE_INVALID_FILEPATH);
        }
        return path;
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
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
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

    /**
     * Parses a {@code String gitHubId} into an {@code GitHubId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code gitHubId} is invalid.
     */
    public static GitHubId parseGitHubId(String gitHubId) throws ParseException {
        requireNonNull(gitHubId);
        String trimmedGitHubId = gitHubId.trim();
        if (!GitHubId.isValidGitHubId(trimmedGitHubId)) {
            throw new ParseException(GitHubId.MESSAGE_CONSTRAINTS);
        }
        return new GitHubId(trimmedGitHubId);
    }

    /**
     * Parses a {@code String nusNetworkId} into an {@code NusNetworkId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code nusNetworkId} is invalid.
     */
    public static NusNetworkId parseNusNetworkId(String nusNetworkId) throws ParseException {
        requireNonNull(nusNetworkId);
        String trimmedNusNetworkId = nusNetworkId.trim();
        if (!NusNetworkId.isValidNusNetworkId(trimmedNusNetworkId)) {
            throw new ParseException(NusNetworkId.MESSAGE_CONSTRAINTS);
        }
        return new NusNetworkId(trimmedNusNetworkId);
    }

    /**
     * Parses a {@code String type} into an {@code NusNetworkId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code type} is invalid.
     */
    public static Type parseType(String type) throws ParseException {
        requireNonNull(type);
        String trimmedType = type.trim();
        if (!Type.isValidType(trimmedType)) {
            throw new ParseException(Type.MESSAGE_CONSTRAINTS);
        }
        return new Type(trimmedType);
    }

    /**
     * Parses a {@code String studentId} into an {@code NusNetworkId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code studentId} is invalid.
     */
    public static StudentId parseStudentId(String studentId) throws ParseException {
        requireNonNull(studentId);
        String trimmedStudentId = studentId.trim();
        if (!StudentId.isValidStudentId(trimmedStudentId)) {
            throw new ParseException(StudentId.MESSAGE_CONSTRAINTS);
        }
        return new StudentId(trimmedStudentId);
    }

    /**
     * Parses a {@code String tutorialId} into an {@code NusNetworkId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tutorialId} is invalid.
     */
    public static TutorialId parseTutorialId(String tutorialId) throws ParseException {
        requireNonNull(tutorialId);
        String trimmedTutorialId = tutorialId.trim();
        if (!TutorialId.isValidTutorialId(trimmedTutorialId)) {
            throw new ParseException(TutorialId.MESSAGE_CONSTRAINTS);
        }
        return new TutorialId(trimmedTutorialId);
    }


}
