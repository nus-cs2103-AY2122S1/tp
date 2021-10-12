package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.core.range.Range;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.common.Address;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_RANGE = "Range given is invalid";

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

     * Parses parameter into a {@code Range} and returns it. Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if the specified range is invalid.
     */
    public static Range parseRange(String range) throws ParseException {
        String trimmedRange = range.trim();
        if (!StringUtil.isValidRange(trimmedRange)) {
            throw new ParseException(MESSAGE_INVALID_RANGE);
        }
        String[] rangeArr = trimmedRange.split("-");
        Index start = parseIndex(rangeArr[0]);
        Index end = parseIndex(rangeArr[1]);
        return new Range(start, end);
    }

    /**
     * Parses parameter into a {@code Range} and returns it. Leading and trailing whitespaces will be trimmed.
     * First, it will try to parse into an {@code Index}. If successful, the index will be converted to a
     * Range from itself to itself. Else, it will parse into a Range.
     * @throws ParseException if specified argument can neither be parsed into an Index nor a Range.
     */
    public static Range parseDeleteArgument(String args) throws ParseException {
        try {
            Index index = parseIndex(args);
            return new Range(index, index);
        } catch (ParseException pe) {
            return parseRange(args);
        }
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
