package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindCommand.FindCondition;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lesson.Date;
import seedu.address.model.lesson.Homework;
import seedu.address.model.lesson.LessonRates;
import seedu.address.model.lesson.Money;
import seedu.address.model.lesson.OutstandingFees;
import seedu.address.model.lesson.Subject;
import seedu.address.model.lesson.TimeRange;
import seedu.address.model.person.AcadLevel;
import seedu.address.model.person.AcadStream;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.School;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {
    /** Number of index arguments expected in preamble when parsing student index */
    public static final int INDEX_ARGS_COUNT_STUDENT = 1;

    /** Number of index arguments expected in preamble when parsing student and lesson index */
    public static final int INDEX_ARGS_COUNT_STUDENT_LESSON = 2;

    /** Zero based position of student index in preamble */
    public static final int STUDENT_INDEX_ZERO_BASED = 0;

    /** Zero based position of lesson index in preamble */
    public static final int LESSON_INDEX_ZERO_BASED = 1;

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code preamble} into {@code String[]} and returns it.
     * Leading and trailing whitespaces will be stripped and
     * preamble string is split by whitespace into an array of strings.
     *
     * @param preamble The preamble string to parse.
     * @return Array of argument strings in preamble.
     */
    public static String[] parsePreamble(String preamble) {
        String strippedPreamble = preamble.strip();
        // required check as empty string split by whitespaces
        // returns array with empty string instead of empty array.
        if (strippedPreamble.isEmpty()) {
            return new String[0];
        }
        return strippedPreamble.split("\\s+");
    }

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it.
     * Leading and trailing whitespaces will be stripped.
     *
     * @param oneBasedIndex The index string to parse.
     * @return Index with the value of oneBasedIndex.
     * @throws ParseException If the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String strippedIndex = oneBasedIndex.strip();
        if (!StringUtil.isNonZeroUnsignedInteger(strippedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(strippedIndex));
    }

    /**
     * Parses {@code studentIndex} into {@code Index} and returns it.
     *
     * @param studentIndex The student index string to parse.
     * @return Index with the value of studentIndex.
     * @throws ParseException If the student index is invalid.
     */
    public static Index parseStudentIndex(String studentIndex) throws ParseException {
        try {
            return parseIndex(studentIndex);
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX, pe);
        }
    }

    /**
     * Parses {@code lessonIndex} into {@code Index} and returns it.
     *
     * @param lessonIndex The lesson index string to parse.
     * @return Index with the value of studentIndex.
     * @throws ParseException If the lesson index is invalid.
     */
    public static Index parseLessonIndex(String lessonIndex) throws ParseException {
        try {
            return parseIndex(lessonIndex);
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_LESSON_DISPLAYED_INDEX, pe);
        }
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
        return new AcadStream(strippedAcadStream);
    }

    /**
     * Parses a {@code String acadLevel} into an {@code AcadLevel}.
     * Leading and trailing whitespaces will be stripped.
     *
     * @throws ParseException if the given {@code acadLevel} is invalid.
     */
    public static AcadLevel parseAcadLevel(String acadLevel) throws ParseException {
        requireNonNull(acadLevel);
        String strippedAcadLevel = acadLevel.strip();
        if (!AcadLevel.isValidAcadLevel(strippedAcadLevel)) {
            throw new ParseException(AcadLevel.MESSAGE_CONSTRAINTS);
        }
        return new AcadLevel(strippedAcadLevel);
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
     * Parses a {@code String date} into a {@code Optional}.
     * Leading and trailing whitespaces will be stripped.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static Optional<Date> parseDate(String date) throws ParseException {
        // if no date string given
        if (date == null || date.strip().isEmpty()) {
            return Optional.empty();
        }
        String strippedDate = date.strip();
        assert strippedDate != null;
        // remove leading zeroes
        strippedDate = StringUtil.stripLeadingZeroes(strippedDate);
        if (!Date.isValidDate(strippedDate)) {
            throw new ParseException(Date.MESSAGE_CONSTRAINTS);
        }
        return Optional.ofNullable(new Date(strippedDate));
    }

    /**
     * Parses {@code Collection<String> dates} into a {@code Set<Date>}.
     */
    public static Set<Date> parseDates(Collection<String> dates) throws ParseException {
        requireNonNull(dates);
        final Set<Date> dateSet = new HashSet<>();
        for (String date : dates) {
            Optional<Date> parsedDate = parseDate(date);
            if (parsedDate.isEmpty()) {
                throw new ParseException(Date.MESSAGE_CONSTRAINTS);
            }
            dateSet.add(parseDate(date).get());
        }
        return dateSet;
    }
    /**
     * Parses {@code String TimeRange} into a {@code TimeRange}.
     * Leading and trailing whitespaces will be stripped.
     *
     * @throws ParseException if the given {@code TimeRange} is invalid.
     */
    public static TimeRange parseTimeRange(String timeRange) throws ParseException {
        requireNonNull(timeRange);
        String strippedTimeRange = timeRange.strip();
        if (!TimeRange.isValidTimeRange(strippedTimeRange)) {
            throw new ParseException(TimeRange.MESSAGE_CONSTRAINTS);
        }
        return new TimeRange(strippedTimeRange);
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
        Collection<String> nonEmptyTags = tags.stream()
            .filter(tagName -> !tagName.isEmpty()).collect(Collectors.toSet());
        for (String tagName : nonEmptyTags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses an {@code String individualHomework} into a {@code Homework}.
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
    public static Set<Homework> parseHomeworkList(Collection<String> homework) throws ParseException {
        requireNonNull(homework);
        Collection<String> nonEmptyHomework = homework.stream()
            .filter(desc -> !desc.isEmpty()).collect(Collectors.toSet());
        final Set<Homework> homeworkSet = new HashSet<>();
        for (String description : nonEmptyHomework) {
            homeworkSet.add(parseIndividualPieceOfHomework(description));
        }
        return homeworkSet;
    }

    /**
     * Parses {@code String keywords} into a {@code List<String>}.
     * Leading and trailing whitespaces will be stripped and
     * keywords string is split by whitespace into a list of words.
     *
     * @throws ParseException If the given {@code address} is invalid.
     */
    public static List<String> parseKeywords(String keywords) throws ParseException {
        requireNonNull(keywords);
        String strippedKeywords = keywords.strip();
        if (strippedKeywords.isEmpty()) {
            throw new ParseException(FindCommand.MESSAGE_KEYWORD_CONSTRAINTS);
        }
        return Arrays.asList(strippedKeywords.split("\\s+"));
    }

    /**
     * Parses {@code String keywords} into a {@code String}.
     * Leading and trailing whitespaces of keywords will be stripped.
     * A tag keyword can only be one word.
     *
     * @throws ParseException If the given {@code address} is invalid.
     */
    public static String parseTagKeyword(String keyword) throws ParseException {
        requireNonNull(keyword);
        String strippedKeyword = keyword.strip();
        if (strippedKeyword.isEmpty()) {
            throw new ParseException(FindCommand.MESSAGE_KEYWORD_CONSTRAINTS);
        }
        if (!Tag.isValidTagName(strippedKeyword)) {
            throw new ParseException(FindCommand.MESSAGE_TAG_KEYWORD_CONSTRAINTS);
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
     * @throws ParseException If the given {@code Subject} is invalid.
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

    /**
     * Parses a {@code String lessonRates} into an {@code lessonRates}.
     * Leading and trailing whitespaces will be stripped.
     *
     * @throws ParseException if the given {@code lessonRates} is invalid.
     */
    public static LessonRates parseLessonRates(String lessonRates) throws ParseException {
        requireNonNull(lessonRates);
        String strippedRates = parseMoney(lessonRates).value;
        return new LessonRates(strippedRates);
    }

    /**
     * Parses a {@code String outstandingFees} into an {@code OutstandingFees}.
     * Leading and trailing whitespaces will be stripped.
     *
     * @throws ParseException if the given {@code outstandingFees} is invalid.
     */
    public static OutstandingFees parseOutstandingFees(String fees) throws ParseException {
        requireNonNull(fees);
        String strippedFees = parseMoney(fees).value;
        return new OutstandingFees(strippedFees);
    }

    /**
     * Parses a {@code String amount} into {@code Money}.
     * Leading and trailing whitespaces will be stripped.
     *
     * @throws ParseException if the given {@code amount} is invalid.
     */
    public static Money parseMoney(String amount) throws ParseException {
        String strippedAmount = amount.strip();
        if (!Money.isValidMonetaryField(strippedAmount)) {
            throw new ParseException(Money.MESSAGE_FORMAT_CONSTRAINTS);
        }
        return new Money(strippedAmount);
    }
}
