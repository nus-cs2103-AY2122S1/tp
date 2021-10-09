package seedu.tracker.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.tracker.commons.core.index.Index;
import seedu.tracker.commons.util.StringUtil;
import seedu.tracker.logic.parser.exceptions.ParseException;
import seedu.tracker.model.calendar.AcademicYear;
import seedu.tracker.model.calendar.Semester;
import seedu.tracker.model.module.Code;
import seedu.tracker.model.module.Description;
import seedu.tracker.model.module.Mc;
import seedu.tracker.model.module.Title;
import seedu.tracker.model.tag.Tag;

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
     * Parses a {@code String code} into a {@code Code}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code code} is invalid.
     */
    public static Code parseCode(String code) throws ParseException {
        requireNonNull(code);
        String trimmedCode = code.trim();
        if (!Code.isValidCode(trimmedCode)) {
            throw new ParseException(Code.MESSAGE_CONSTRAINTS);
        }
        return new Code(trimmedCode);
    }

    /**
     * Parses a {@code String title} into a {@code Title}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code title} is invalid.
     */
    public static Title parseTitle(String title) throws ParseException {
        requireNonNull(title);
        String trimmedTitle = title.trim();
        if (!Title.isValidTitle(trimmedTitle)) {
            throw new ParseException(Title.MESSAGE_CONSTRAINTS);
        }
        return new Title(trimmedTitle);
    }

    /**
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        return new Description(trimmedDescription);
    }

    /**
     * Parses {@code mc} into an {@code Mc} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified mc is invalid (not non-zero unsigned integer).
     */
    public static Mc parseMc(String mc) throws ParseException {
        String trimmedMc = mc.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedMc)) {
            throw new ParseException(Mc.MESSAGE_CONSTRAINTS);
        }
        return new Mc(Integer.parseInt(trimmedMc));
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
     * Parses {@code year} into an {@code AcademicYear} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified year is invalid (not non-zero unsigned integer).
     */
    public static AcademicYear parseAcademicYear(String year) throws ParseException {
        String trimmedYear = year.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedYear)) {
            throw new ParseException(AcademicYear.MESSAGE_CONSTRAINTS);
        }
        if (!AcademicYear.isValidAcademicYear(Integer.parseInt(trimmedYear))) {
            throw new ParseException(AcademicYear.MESSAGE_CONSTRAINTS);
        }
        return new AcademicYear(Integer.parseInt(trimmedYear));
    }

    /**
     * Parses {@code semester} into an {@code Semester} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified semester is invalid (not non-zero unsigned integer).
     */
    public static Semester parseSemester(String semester) throws ParseException {
        String trimmedSemester = semester.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedSemester)) {
            throw new ParseException(Semester.MESSAGE_CONSTRAINTS);
        }
        if (!Semester.isValidSemester(Integer.parseInt(trimmedSemester))) {
            throw new ParseException(Semester.MESSAGE_CONSTRAINTS);
        }
        return new Semester(Integer.parseInt(trimmedSemester));
    }
}
