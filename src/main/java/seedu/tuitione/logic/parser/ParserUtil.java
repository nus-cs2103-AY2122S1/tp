package seedu.tuitione.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.tuitione.commons.core.Messages.HEADER_ALERT;
import static seedu.tuitione.commons.core.Messages.MESSAGE_INVALID_DAY;
import static seedu.tuitione.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static seedu.tuitione.commons.core.Messages.MESSAGE_INVALID_PRICE_NOT_NUMBER;
import static seedu.tuitione.commons.core.Messages.MESSAGE_INVALID_TIME;
import static seedu.tuitione.model.lesson.LessonTime.TIME_FORMATTER;
import static seedu.tuitione.model.lesson.LessonTime.parseStringToDay;
import static seedu.tuitione.model.lesson.Price.PRICE_MESSAGE_CONSTRAINT;
import static seedu.tuitione.model.lesson.Price.isValidPrice;
import static seedu.tuitione.model.lesson.Subject.SUBJECT_MESSAGE_CONSTRAINTS;
import static seedu.tuitione.model.lesson.Subject.isValidSubject;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.tuitione.commons.core.index.Index;
import seedu.tuitione.commons.util.StringUtil;
import seedu.tuitione.logic.parser.exceptions.ParseException;
import seedu.tuitione.model.lesson.Price;
import seedu.tuitione.model.lesson.Subject;
import seedu.tuitione.model.remark.Remark;
import seedu.tuitione.model.student.Address;
import seedu.tuitione.model.student.Email;
import seedu.tuitione.model.student.Grade;
import seedu.tuitione.model.student.Name;
import seedu.tuitione.model.student.ParentContact;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

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
            throw new ParseException(HEADER_ALERT + Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static ParentContact parseParentContact(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!ParentContact.isValidPhone(trimmedPhone)) {
            throw new ParseException(HEADER_ALERT + ParentContact.MESSAGE_CONSTRAINTS);
        }
        return new ParentContact(trimmedPhone);
    }

    /**
     * Parses a {@code String tuitione} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tuitione} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(HEADER_ALERT + Address.MESSAGE_CONSTRAINTS);
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
            throw new ParseException(HEADER_ALERT + Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String grade} into an {@code Grade}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code grade} is invalid.
     */
    public static Grade parseGrade(String grade) throws ParseException {
        requireNonNull(grade);
        String trimmedGrade = grade.trim();
        if (!Grade.isValidGrade(trimmedGrade)) {
            throw new ParseException(HEADER_ALERT + Grade.GRADE_MESSAGE_CONSTRAINTS);
        }
        return new Grade(trimmedGrade);
    }

    /**
     * Parses a {@code String remark} into a {@code Remark}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code remark} is invalid.
     */
    public static Remark parseRemark(String remark) throws ParseException {
        requireNonNull(remark);
        String trimmedRemark = remark.trim();
        if (!Remark.isValidRemarkName(trimmedRemark)) {
            throw new ParseException(HEADER_ALERT + Remark.MESSAGE_CONSTRAINTS);
        }
        return new Remark(trimmedRemark);
    }

    /**
     * Parses {@code Collection<String> remarks} into a {@code Set<Remark>}.
     */
    public static Set<Remark> parseRemarks(Collection<String> remarks) throws ParseException {
        requireNonNull(remarks);
        final Set<Remark> remarkSet = new HashSet<>();
        for (String remarkName : remarks) {
            remarkSet.add(parseRemark(remarkName));
        }
        return remarkSet;
    }

    /**
     * Parses a {@code String time} into a {@code LocalTime}.
     */
    public static LocalTime parseLocalTime(String time) throws ParseException {
        requireNonNull(time);
        String trimmedTime = time.trim();
        try {
            return LocalTime.parse(trimmedTime, TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new ParseException(MESSAGE_INVALID_TIME);
        }
    }

    /**
     * Parses a {@code String day} into {@code DayOfWeek}.
     */
    public static DayOfWeek parseDayOfWeek(String day) throws ParseException {
        requireNonNull(day);
        String cleanedDay = StringUtil.capitalizeFirstCharAndLowerRest(day.trim());
        return parseStringToDay(cleanedDay)
                .orElseThrow(() -> new ParseException(MESSAGE_INVALID_DAY));
    }

    /**
     * Parses a {@code String subject} into a {@code String subject}
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param args Subject
     * @throws ParseException if the given {@code subject} is invalid
     */
    public static Subject parseSubjectArgs(String args) throws ParseException {
        requireNonNull(args);
        String cleanedSubject = StringUtil.capitalizeFirstCharAndLowerRest(args.trim());

        if (!isValidSubject(cleanedSubject)) {
            throw new ParseException(HEADER_ALERT + SUBJECT_MESSAGE_CONSTRAINTS);
        }
        return new Subject(cleanedSubject);
    }

    /**
     * Parses a {@code String cost} into a {@code double cost}
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code cost} is invalid
     */
    public static Price parsePriceArgs(String args) throws ParseException {
        requireNonNull(args);
        String trimmedCost = args.trim();
        double cost;

        try {
            cost = Double.parseDouble(trimmedCost);
        } catch (NumberFormatException e) {
            throw new ParseException(MESSAGE_INVALID_PRICE_NOT_NUMBER);
        }

        if (!isValidPrice(cost)) {
            throw new ParseException(HEADER_ALERT + PRICE_MESSAGE_CONSTRAINT);
        }
        return new Price(cost);
    }
}
