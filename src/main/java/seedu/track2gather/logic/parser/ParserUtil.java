package seedu.track2gather.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import seedu.track2gather.commons.core.index.Index;
import seedu.track2gather.commons.util.StringUtil;
import seedu.track2gather.logic.parser.exceptions.ParseException;
import seedu.track2gather.model.person.attributes.Address;
import seedu.track2gather.model.person.attributes.CaseNumber;
import seedu.track2gather.model.person.attributes.Email;
import seedu.track2gather.model.person.attributes.Name;
import seedu.track2gather.model.person.attributes.Period;
import seedu.track2gather.model.person.attributes.Phone;

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
     * Parses a string of {@code oneBasedIndex}s into an ArrayList of {@code Index} and returns it. Leading and
     * trailing whitespaces will be trimmed.
     * @throws ParseException if the specified indices are invalid (not non-zero unsigned integer).
     */
    public static ArrayList<Index> parseIndices(String oneBasedIndices) throws ParseException {
        ArrayList<Index> indexList = new ArrayList<>();
        String trimmedIndices = oneBasedIndices.trim();

        String[] trimmedIndicesArr = trimmedIndices.split("\\s+");

        for (String trimmedIndex : trimmedIndicesArr) {
            if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
                throw new ParseException(MESSAGE_INVALID_INDEX);
            }

            indexList.add(Index.fromOneBased(Integer.parseInt(trimmedIndex)));
        }

        return indexList;
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
     * Parses a {@code String caseNumber} into a {@code CaseNumber}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code caseNumber} is invalid.
     */
    public static CaseNumber parseCaseNumber(String caseNumber) throws ParseException {
        requireNonNull(caseNumber);
        String trimmedCaseNumber = caseNumber.trim();
        if (!CaseNumber.isValidCaseNumber(trimmedCaseNumber)) {
            throw new ParseException(CaseNumber.MESSAGE_CONSTRAINTS);
        }
        return new CaseNumber(trimmedCaseNumber);
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
     * Parses {@code String SHN period} into an {@code ShnPeriod}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code SHN period} is invalid.
     */
    public static Period parsePeriod(String period) throws ParseException {
        requireNonNull(period);
        String trimmedShnPeriod = period.trim();
        String[] dates = trimmedShnPeriod.split(" ", 2);

        if (dates.length < 2) {
            throw new ParseException(Period.MESSAGE_CONSTRAINTS);
        }

        LocalDate startDate;
        LocalDate endDate;

        try {
            startDate = LocalDate.parse(dates[0]);
            endDate = LocalDate.parse(dates[1]);
        } catch (DateTimeParseException e) {
            throw new ParseException(Period.MESSAGE_CONSTRAINTS);
        }

        if (!Period.isValidPeriod(startDate, endDate)) {
            throw new ParseException(Period.MESSAGE_CONSTRAINTS);
        }
        return new Period(startDate, endDate);
    }
}
