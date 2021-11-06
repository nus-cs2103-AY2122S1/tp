package tutoraid.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.stream.Collectors;

import tutoraid.commons.core.index.Index;
import tutoraid.commons.util.StringUtil;
import tutoraid.logic.parser.exceptions.ParseException;
import tutoraid.model.lesson.Capacity;
import tutoraid.model.lesson.LessonName;
import tutoraid.model.lesson.Price;
import tutoraid.model.lesson.Timing;
import tutoraid.model.student.Name;
import tutoraid.model.student.ParentName;
import tutoraid.model.student.Phone;
import tutoraid.model.student.Progress;
import tutoraid.model.student.StudentName;

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
     * Parses multiple {@code oneBasedIndex} into an arraylist of {@code Index} and returns it.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if any of the specified indexes are invalid (not non-zero unsigned integer).
     */
    public static ArrayList<Index> parseMultipleIndexes(String multipleOneBasedIndexes) throws ParseException {
        String[] trimmedMultipleIndexes = multipleOneBasedIndexes.trim().split(" ");
        ArrayList<Index> indexesToReturn = new ArrayList<>();

        for (String indexInString : trimmedMultipleIndexes) {
            Index index = parseIndex(indexInString);
            indexesToReturn.add(index);
        }
        return indexesToReturn.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Parses a {@code String name} into a {@code StudentName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static StudentName parseStudentName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new StudentName(trimmedName);
    }

    /**
     * Parses a {@code String name} into a {@code ParentName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static ParentName parseParentName(String name) throws ParseException {
        requireNonNull(name);
        if (name.equals("")) {
            return new ParentName("");
        }
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new ParentName(trimmedName);

    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        if (phone.equals("")) {
            return new Phone("");
        }
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String progress} into a {@code Progress}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code progress} is invalid.
     */
    public static Progress parseProgress(String progress) throws ParseException {
        requireNonNull(progress);
        String trimmedProgress = progress.trim();
        if (!Progress.isValidProgress(trimmedProgress)) {
            throw new ParseException(Progress.MESSAGE_CONSTRAINTS);
        }
        return new Progress(trimmedProgress);
    }

    /**
     * Parses a {@code String name} into a {@code LessonName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static LessonName parseLessonName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();

        if (!LessonName.isValidLessonName(trimmedName)) {

            throw new ParseException(LessonName.MESSAGE_CONSTRAINTS);
        }
        return new LessonName(trimmedName);
    }

    /**
     * Parses a {@code String capacity} into a {@code Capacity}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code capacity} is invalid.
     */
    public static Capacity parseCapacity(String capacity) throws ParseException {
        requireNonNull(capacity);
        if (capacity.equals("")) {
            return new Capacity("");
        }
        String trimmedCapacity = capacity.trim();
        if (!Capacity.isValidCapacity(trimmedCapacity)) {
            throw new ParseException(Capacity.MESSAGE_CONSTRAINTS);
        }
        return new Capacity(trimmedCapacity);
    }

    /**
     * Parses a {@code String price} into a {@code Price}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code price} is invalid.
     */
    public static Price parsePrice(String price) throws ParseException {
        requireNonNull(price);
        if (price.equals("")) {
            return new Price("");
        }

        String trimmedPrice = price.trim();
        if (!Price.isValidPrice(trimmedPrice)) {
            throw new ParseException(Price.MESSAGE_CONSTRAINTS);
        }
        return new Price(trimmedPrice);
    }

    /**
     * Parses a {@code String timing} into a {@code Timing}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code timing} is invalid.
     */
    public static Timing parseTiming(String timing) throws ParseException {
        requireNonNull(timing);
        if (timing.equals("")) {
            return new Timing("");
        }
        String trimmedTiming = timing.trim();
        if (!Timing.isValidTiming(trimmedTiming)) {
            throw new ParseException(Timing.MESSAGE_CONSTRAINTS);
        }
        return new Timing(trimmedTiming);
    }
}
