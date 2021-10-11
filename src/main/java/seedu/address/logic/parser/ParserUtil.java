package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindCommand.FindCondition;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lesson.Date;
import seedu.address.model.lesson.Homework;
import seedu.address.model.lesson.Subject;
import seedu.address.model.lesson.Time;
import seedu.address.model.lesson.TimeRange;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Fee;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
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
     * Parses a {@code String fee} into an {@code Fee}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code fee} is invalid.
     */
    public static Fee parseFee(String fee) throws ParseException {
        requireNonNull(fee);
        String strippedFee = fee.strip();
        if (!Fee.isValidFee(strippedFee)) {
            throw new ParseException(Fee.MESSAGE_CONSTRAINTS);
        }
        return new Fee(strippedFee);
    }

    /**
     * Parses a {@code String remark} into an {@code Remark}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Remark parseRemark(String remark) {
        requireNonNull(remark);
        String trimmedRemark = remark.trim();
        return new Remark(trimmedRemark);
    }

    /**
     * Parses a {@code String date} into a {@code Date}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static Date parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!Date.isValidDate(trimmedDate)) {
            throw new ParseException(Date.MESSAGE_CONSTRAINTS);
        }
        return new Date(trimmedDate);
    }

    /**
     * Parses a {@code String Time} into a {@code Time}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Time} is invalid.
     */
    public static Time parseTime(String time) throws ParseException {
        requireNonNull(time);
        String trimmedTime = time.trim();
        if (!Time.isValidTime(trimmedTime)) {
            throw new ParseException(Time.MESSAGE_CONSTRAINTS);
        }
        return new Time(trimmedTime);
    }

    /**
     * Parses {@code String Time} and {@code String Time} into a {@code TimeRange}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Time} pr {@code TimeRange} is invalid.
     */
    public static TimeRange parseTimeRange(String start, String end) throws ParseException {
        requireNonNull(start);
        requireNonNull(end);
        Time startTime = parseTime(start);
        Time endTime = parseTime(end);
        if (!TimeRange.isValidTimeRange(startTime, endTime)) {
            throw new ParseException(TimeRange.MESSAGE_CONSTRAINTS);
        }
        return new TimeRange(startTime, endTime);
    }


    /**
     * Parses a {@code String subject} into a {@code Subject}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Subject} is invalid.
     */
    public static Subject parseSubject(String subject) throws ParseException {
        requireNonNull(subject);
        String trimmedSubject = subject.trim();
        if (!Subject.isValidSubject(trimmedSubject)) {
            throw new ParseException(Subject.MESSAGE_CONSTRAINTS);
        }
        return new Subject(trimmedSubject);
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
     * Parses a {@code String individualHomework} into a {@code Homework}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Homework} is invalid.
     */
    public static Homework parseIndividualPieceOfHomework(String individualHomework) throws ParseException {
        requireNonNull(individualHomework);
        String trimmedHomework = individualHomework.trim();
        if (!Homework.isValidDescription(trimmedHomework)) {
            throw new ParseException(Homework.MESSAGE_CONSTRAINTS);
        }
        return new Homework(trimmedHomework);
    }

    /**
     * Parses {@code Collection<String> homework} into a {@code Set<Homework>}.
     */
    public static Set<Homework> parseHomeworkList(Collection<String> homework) throws ParseException {
        requireNonNull(homework);
        final Set<Homework> homeworkSet = new HashSet<>();
        for (String description : homework) {
            homeworkSet.add(parseIndividualPieceOfHomework(description));
        }
        return homeworkSet;
    }

    /**
     * Parses {@code String keywords} into a {@code List<String>}.
     * Leading and trailing whitespaces will be stripped and
     * keywords string is split by whitespace into a list of words.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static List<String> parseKeywords(String keywords) throws ParseException {
        requireNonNull(keywords);
        String strippedKeywords = keywords.strip();
        if (strippedKeywords.isEmpty()) {
            throw new ParseException(FindCommand.MESSAGE_INVALID_KEYWORD);
        }
        return Arrays.asList(strippedKeywords.split("\\s+"));
    }

    /**
     * Parses {@code String keywords} into a {@code String}.
     * Leading and trailing whitespaces of keywords will be stripped.
     * A tag keyword can consist of multiple words.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static String parseTagKeyword(String keyword) throws ParseException {
        requireNonNull(keyword);
        String strippedKeyword = keyword.strip();
        if (strippedKeyword.isEmpty()) {
            throw new ParseException(FindCommand.MESSAGE_INVALID_KEYWORD);
        }
        return strippedKeyword;
    }

    /**
     * Parses {@code Collection<String> keywords} into a {@code List<String>}.
     */
    public static List<String> parseTagKeywords(Collection<String> keywords) throws ParseException {
        requireNonNull(keywords);
        final List<String> tagKeywords = new ArrayList<>();
        for (String keyword : keywords) {
            tagKeywords.add(parseTagKeyword(keyword));
        }
        return tagKeywords;
    }

    /**
     * Parses a {@code String condition} into a {@code FindCondition}.
     * Leading and trailing whitespaces will be stripped.
     *
     * @throws ParseException if the given {@code Subject} is invalid.
     */
    public static FindCondition parseFindCondition(String condition) throws ParseException {
        requireNonNull(condition);
        String strippedCondition = condition.strip();

        FindCondition findCondition = FindCondition.valueOfName(strippedCondition);
        if (findCondition == null) {
            throw new ParseException(FindCommand.MESSAGE_CONDITION_CONSTRAINTS);
        }

        return findCondition;
    }
}
