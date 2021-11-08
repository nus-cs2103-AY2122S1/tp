package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.EncryptUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Timestamp;
import seedu.address.model.account.Password;
import seedu.address.model.member.Address;
import seedu.address.model.member.Email;
import seedu.address.model.member.Name;
import seedu.address.model.member.Phone;
import seedu.address.model.member.Point;
import seedu.address.model.reservation.DateTime;
import seedu.address.model.reservation.Remark;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.Billing;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX =
            "Index is not a non-zero unsigned integer (range: 1 ~ 2147483647). \n%1$s";

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
     * Parses a {@code String id} into a {@code Id}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code id} is invalid.
     */
    public static seedu.address.model.member.Id parseMemberId(String id) throws ParseException {
        requireNonNull(id);
        String trimmedId = id.trim();
        if (!seedu.address.model.member.Id.isValidId(trimmedId)) {
            throw new ParseException(seedu.address.model.member.Id.MESSAGE_CONSTRAINTS);
        }
        return new seedu.address.model.member.Id(trimmedId);
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
     * Parses a {@code String timestamp} into an {@code Timestamp}.
     */
    public static Timestamp parseTimestamp(String timestamp) throws ParseException {
        requireNonNull(timestamp);
        String trimmedTimestamp = timestamp.trim();
        if (!Timestamp.isValidTimestamp(trimmedTimestamp)) {
            throw new ParseException(Timestamp.MESSAGE_CONSTRAINTS);
        }
        return new Timestamp(trimmedTimestamp);
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
     * Parses a {@code String id} into a {@code Id}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code id} is invalid.
     */
    public static seedu.address.model.transaction.Id parseTransactionId(String id) throws ParseException {
        requireNonNull(id);
        String trimmedId = id.trim();
        if (!seedu.address.model.transaction.Id.isValidId(trimmedId)) {
            throw new ParseException(seedu.address.model.transaction.Id.MESSAGE_CONSTRAINTS);
        }
        return new seedu.address.model.transaction.Id(trimmedId);
    }

    /**
     * Parses a {@code String id} into a {@code Id}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code id} is invalid.
     */
    public static seedu.address.model.reservation.Id parseReservationId(String id) throws ParseException {
        requireNonNull(id);
        String trimmedId = id.trim();
        if (!seedu.address.model.reservation.Id.isValidId(trimmedId)) {
            throw new ParseException(seedu.address.model.reservation.Id.MESSAGE_CONSTRAINTS);
        }
        return new seedu.address.model.reservation.Id(trimmedId);
    }

    /**
     * Parses a {@code String billing} into a {@code Billing}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code billing} is invalid.
     */
    public static Billing parseBilling(String billing) throws ParseException {
        requireNonNull(billing);
        String trimmedBilling = billing.trim();
        if (!Billing.isValidBilling(trimmedBilling)) {
            throw new ParseException(Billing.MESSAGE_CONSTRAINTS);
        }
        trimmedBilling = BigDecimal.valueOf(Double.parseDouble(trimmedBilling)).stripTrailingZeros()
                .setScale(2, RoundingMode.HALF_EVEN).toPlainString();
        return new Billing(trimmedBilling);
    }

    /**
     * Parses a {@code String dateTime} into a {@code DateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code dateTime} is invalid.
     */
    public static DateTime parseDateTime(String dateTime) throws ParseException {
        requireNonNull(dateTime);
        String trimmedDateTime = dateTime.trim();
        if (!DateTime.isValidDateTime(trimmedDateTime)) {
            throw new ParseException(DateTime.MESSAGE_CONSTRAINTS);
        }
        return new DateTime(trimmedDateTime);
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
        if (!Remark.isValidRemark(trimmedRemark)) {
            throw new ParseException(Remark.MESSAGE_CONSTRAINTS);
        }
        return new Remark(trimmedRemark);
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
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
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
     * Parses a {@code String points} into a {@code Points}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code transaction} is invalid.
     */
    public static Point parsePoint(String point) throws ParseException {
        requireNonNull(point);
        String trimmedPoint = point.trim();
        if (!Point.isValidPoint(trimmedPoint)) {
            throw new ParseException(Point.MESSAGE_CONSTRAINTS);
        }
        return new Point(trimmedPoint);
    }


    /**
     * Parses {@code Collection<String> transactions} into a {@code Set<Transaction>}.
     */
    public static List<Point> parsePoints(Collection<String> points)
            throws ParseException {
        requireNonNull(points);
        final List<Point> pointsList = new ArrayList<>();
        for (String pointAmount : points) {
            pointsList.add(parsePoint(pointAmount));
        }
        return pointsList;
    }

    /**
     * Parses {@code password} into a {@code Password} and returns it.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code password} is invalid.
     */
    public static Password parsePassword(String password) throws ParseException {
        String trimmedPassword = password.trim();
        String hashedTrimmedPassword;
        try {
            hashedTrimmedPassword = EncryptUtil.hash(trimmedPassword);
        } catch (NoSuchAlgorithmException e) {
            hashedTrimmedPassword = "";
        }
        if (!Password.isValidPassword(hashedTrimmedPassword)) {
            throw new ParseException(Password.MESSAGE_CONSTRAINTS);
        }
        return new Password(hashedTrimmedPassword);
    }
}
