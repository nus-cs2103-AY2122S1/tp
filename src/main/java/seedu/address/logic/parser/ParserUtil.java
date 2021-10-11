package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Name;
import seedu.address.model.student.ParentName;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Progress;
import seedu.address.model.student.StudentName;

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

}
