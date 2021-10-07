package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lesson.Date;
import seedu.address.model.lesson.Homework;
import seedu.address.model.lesson.Subject;
import seedu.address.model.lesson.Time;
import seedu.address.model.lesson.TimeRange;
import seedu.address.model.person.AcadStream;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Fee;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.School;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * stripped.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String strippedIndex = oneBasedIndex.strip();
        if (!StringUtil.isNonZeroUnsignedInteger(strippedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(strippedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be stripped.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String strippedName = name.strip();
        if (!Name.isValidName(strippedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(strippedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be stripped.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String strippedPhone = phone.strip();
        if (!Phone.isValidPhone(strippedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(strippedPhone);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be stripped.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String strippedEmail = email.strip();
        if (!Email.isValidEmail(strippedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(strippedEmail);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be stripped.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String strippedAddress = address.strip();
        if (!Address.isValidAddress(strippedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(strippedAddress);
    }

    /**
     * Parses a {@code String school} into a {@code School}.
     * Leading and trailing whitespaces will be stripped.
     *
     * @throws ParseException if the given {@code school} is invalid.
     */
    public static School parseSchool(String school) throws ParseException {
        requireNonNull(school);
        String strippedSchName = school.strip();
        if (!School.isValidSchName(strippedSchName)) {
            throw new ParseException(School.MESSAGE_CONSTRAINTS);
        }
        return new School(strippedSchName);
    }

    /**
     * Parses a {@code String acadStream} into an {@code AcadStream}.
     * Leading and trailing whitespaces will be stripped.
     *
     * @throws ParseException if the given {@code acadStream} is invalid.
     */
    public static AcadStream parseAcadStream(String acadStream) throws ParseException {
        requireNonNull(acadStream);
        String strippedAcadStream = acadStream.strip();
        if (!AcadStream.isValidAcadStream(strippedAcadStream)) {
            throw new ParseException(AcadStream.MESSAGE_CONSTRAINTS);
        }
        return new AcadStream(strippedAcadStream);
    }

    /**
     * Parses a {@code String fee} into an {@code Fee}.
     * Leading and trailing whitespaces will be stripped.
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
     * Leading and trailing whitespaces will be stripped.
     */
    public static Remark parseRemark(String remark) {
        requireNonNull(remark);
        String strippedRemark = remark.strip();
        return new Remark(strippedRemark);
    }

    /**
     * Parses a {@code String date} into a {@code Date}.
     * Leading and trailing whitespaces will be stripped.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static Date parseDate(String date) throws ParseException {
        requireNonNull(date);
        String strippedDate = date.strip();
        if (!Date.isValidDate(strippedDate)) {
            throw new ParseException(Date.MESSAGE_CONSTRAINTS);
        }
        return new Date(strippedDate);
    }

    /**
     * Parses a {@code String Time} into a {@code Time}.
     * Leading and trailing whitespaces will be stripped.
     *
     * @throws ParseException if the given {@code Time} is invalid.
     */
    public static Time parseTime(String time) throws ParseException {
        requireNonNull(time);
        String strippedTime = time.strip();
        if (!Time.isValidTime(strippedTime)) {
            throw new ParseException(Time.MESSAGE_CONSTRAINTS);
        }
        return new Time(strippedTime);
    }

    /**
     * Parses {@code String Time} and {@code String Time} into a {@code TimeRange}.
     * Leading and trailing whitespaces will be stripped.
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
     * Leading and trailing whitespaces will be stripped.
     *
     * @throws ParseException if the given {@code Subject} is invalid.
     */
    public static Subject parseSubject(String subject) throws ParseException {
        requireNonNull(subject);
        String strippedSubject = subject.strip();
        if (!Subject.isValidSubject(strippedSubject)) {
            throw new ParseException(Subject.MESSAGE_CONSTRAINTS);
        }
        return new Subject(strippedSubject);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be stripped.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String strippedTag = tag.strip();
        if (!Tag.isValidTagName(strippedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(strippedTag);
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
     * Leading and trailing whitespaces will be stripped.
     *
     * @throws ParseException if the given {@code Homework} is invalid.
     */
    public static Homework parseIndividualPieceOfHomework(String individualHomework) throws ParseException {
        requireNonNull(individualHomework);
        String strippedHomework = individualHomework.strip();
        if (!Homework.isValidDescription(strippedHomework)) {
            throw new ParseException(Homework.MESSAGE_CONSTRAINTS);
        }
        return new Homework(strippedHomework);
    }

    /**
     * Parses {@code Collection<String> homework} into a {@code Set<Homework>}.
     */
    public static Set<Homework> parseHomeworkList(Collection<String> homework)
            throws ParseException {
        requireNonNull(homework);
        final Set<Homework> homeworkSet = new HashSet<>();
        for (String description : homework) {
            homeworkSet.add(parseIndividualPieceOfHomework(description));
        }
        return homeworkSet;
    }
}
