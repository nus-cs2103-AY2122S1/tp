package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Address;
import seedu.address.model.student.ClassCode;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.StudentMark;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorialclass.Schedule;
import seedu.address.model.tutorialgroup.GroupNumber;
import seedu.address.model.tutorialgroup.GroupType;

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
     * Parses a {@code String mark} into a {@code StudentMark}.
     *
     * @throws ParseException if the given {@code mark} is invalid.
     */
    public static StudentMark parseMark(String mark) throws ParseException {
        requireNonNull(mark);
        String trimmedMark = mark.trim().toUpperCase();
        StudentMark newMark;
        try {
            newMark = StudentMark.valueOf(trimmedMark);
        } catch (IllegalArgumentException e) {
            throw new ParseException(StudentMark.MESSAGE_CONSTRAINTS);
        }
        return newMark;
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
     * Parses a string to return a ClassCode
     * @param classCode String of classCode
     * @return ClassCode
     * @throws ParseException if the given {@code classCode} is invalid.
     */
    public static ClassCode parseClassCode(String classCode) throws ParseException {
        requireNonNull(classCode);
        String trimmedClassCode = classCode.trim();
        if (!ClassCode.isValidClassCode(trimmedClassCode)) {
            throw new ParseException(ClassCode.MESSAGE_CONSTRAINTS);
        } else if (ClassCode.isDefaultClassCode(classCode)) {
            throw new ParseException(ClassCode.MESSAGE_EMPTY_CLASS);
        }
        return new ClassCode(trimmedClassCode);
    }

    /**
     * Parses a {@code String schedule} into an {@code Schedule}.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if the given {@code schedule} is invalid.
     */
    public static Schedule parseSchedule(String schedule) throws ParseException {
        requireNonNull(schedule);
        String trimmedSchedule = schedule.trim();
        if (!Schedule.isValidSchedule(trimmedSchedule)) {
            throw new ParseException(Schedule.MESSAGE_CONSTRAINTS);
        }
        return new Schedule(trimmedSchedule);
    }

    /**
     * Parses a {@code String groupNumber} into an {@code GroupNumber}.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if the given {@code groupNumber} is invalid.
     */
    public static GroupNumber parseGroupNumber(String groupNumber) throws ParseException {
        requireNonNull(groupNumber);
        String trimmedGroupNumber = groupNumber.trim();
        if (!GroupNumber.isValidGroupNumber(trimmedGroupNumber)) {
            throw new ParseException(GroupNumber.MESSAGE_CONSTRAINTS);
        }
        return new GroupNumber(trimmedGroupNumber);
    }

    /**
     * Parses a {@code String groupType} into an {@code GrougType}.
     * Leading and trailing whitespaces will be trimmed.
     * Letters are set to uppercase.
     * @throws ParseException if the given {@code groupType} is invalid.
     */
    public static GroupType parseGroupType(String groupType) throws ParseException {
        requireNonNull(groupType);
        String trimmedGroupType = groupType.trim();
        if (!GroupType.isValidGroupType(trimmedGroupType)) {
            throw new ParseException(GroupType.MESSAGE_CONSTRAINTS);
        }
        return new GroupType(trimmedGroupType.toUpperCase());
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
