package seedu.edrecord.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.edrecord.commons.core.index.Index;
import seedu.edrecord.commons.util.StringUtil;
import seedu.edrecord.logic.parser.exceptions.ParseException;
import seedu.edrecord.model.assignment.Grade;
import seedu.edrecord.model.assignment.Grade.GradeStatus;
import seedu.edrecord.model.assignment.Score;
import seedu.edrecord.model.assignment.Weightage;
import seedu.edrecord.model.group.Group;
import seedu.edrecord.model.module.Module;
import seedu.edrecord.model.name.Name;
import seedu.edrecord.model.person.Email;
import seedu.edrecord.model.person.Info;
import seedu.edrecord.model.person.Phone;
import seedu.edrecord.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_ID = "ID is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
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
     * Parses a {@code String info} into a {@code Info}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code info} is invalid.
     */
    public static Info parseInfo(String info) throws ParseException {
        requireNonNull(info);
        String trimmedInfo = info.trim();
        if (!Info.isValidInfo(trimmedInfo)) {
            throw new ParseException(Info.MESSAGE_CONSTRAINTS);
        }
        return new Info(trimmedInfo);
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
     * Parses a {@code String moduleCode} and return the module system's {@code Module}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code moduleCode} is invalid.
     */
    public static Module parseModule(String moduleCode) throws ParseException {
        requireNonNull(moduleCode);
        String trimmedModuleCode = moduleCode.trim().toUpperCase();
        if (!Module.isValidModuleCode(trimmedModuleCode)) {
            throw new ParseException(Module.MESSAGE_CONSTRAINTS);
        }

        return new Module(trimmedModuleCode);
    }

    /**
     * Parses a {@code String code} into a {@code Group}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code groupCode} is invalid.
     */
    public static Group parseGroup(String groupCode) throws ParseException {
        requireNonNull(groupCode);
        String trimmedGroupCode = groupCode.trim().toUpperCase();

        if (!Group.isValidGroup(trimmedGroupCode)) {
            throw new ParseException(Group.MESSAGE_CONSTRAINTS);
        }

        return new Group(trimmedGroupCode);
    }

    /**
     * Parses a {@code String weightage} into a {@code Weightage}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code weightage} is invalid.
     */
    public static Weightage parseWeightage(String weightage) throws ParseException {
        requireNonNull(weightage);
        String trimmedWeightage = weightage.trim();
        if (!Weightage.isValidWeightage(trimmedWeightage)) {
            throw new ParseException(Weightage.MESSAGE_CONSTRAINTS);
        }
        return new Weightage(trimmedWeightage);
    }

    /**
     * Parses a {@code String score} into a {@code Score}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code score} is invalid.
     */
    public static Score parseScore(String score) throws ParseException {
        requireNonNull(score);
        String trimmedScore = score.trim();
        if (!Score.isValidScore(trimmedScore)) {
            throw new ParseException(Score.MESSAGE_CONSTRAINTS);
        }
        return new Score(trimmedScore);
    }

    /**
     * Parses a {@code String status} into a {@code GradeStatus}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code status} is invalid.
     */
    public static GradeStatus parseStatus(String status) throws ParseException {
        requireNonNull(status);
        String trimmedStatus = status.trim().toUpperCase();
        switch (trimmedStatus) {
        case "NOT SUBMITTED":
            return GradeStatus.NOT_SUBMITTED;
        case "SUBMITTED":
            return GradeStatus.SUBMITTED;
        case "GRADED":
            return GradeStatus.GRADED;
        default:
            throw new ParseException(Grade.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String id} into a {@code Index}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code ID} is invalid.
     */
    public static Index parseId(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_ID);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }
}
