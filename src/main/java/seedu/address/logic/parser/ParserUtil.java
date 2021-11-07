package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.commonattributes.Email;
import seedu.address.model.commonattributes.Name;
import seedu.address.model.guest.PassportNumber;
import seedu.address.model.guest.RoomNumber;
import seedu.address.model.tag.Tag;
import seedu.address.model.vendor.Address;
import seedu.address.model.vendor.Cost;
import seedu.address.model.vendor.OperatingHours;
import seedu.address.model.vendor.Phone;
import seedu.address.model.vendor.ServiceName;
import seedu.address.model.vendor.VendorId;

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
        return new Email(trimmedEmail.toLowerCase());
    }

    /**
     * Parses a {@code String vendorId} into a {@code vendorId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code vendorId} is invalid.
     */
    public static VendorId parseVendorId(String vendorId) throws ParseException {
        requireNonNull(vendorId);
        String trimmedVendorId = vendorId.trim();
        if (!VendorId.isValidVendorId(trimmedVendorId)) {
            throw new ParseException(VendorId.MESSAGE_CONSTRAINTS);
        }
        return new VendorId(trimmedVendorId);
    }

    /**
     * Parses a {@code String roomNumber} into a {@code roomNumber}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code roomNumber} is invalid.
     */
    public static RoomNumber parseRoomNumber(String roomNumber) throws ParseException {
        requireNonNull(roomNumber);
        String trimmedRoomNumber = roomNumber.trim();
        trimmedRoomNumber = trimmedRoomNumber.replaceAll("^0+(?!$)", "");
        if (!RoomNumber.isValidRoomNumber(trimmedRoomNumber)) {
            throw new ParseException(RoomNumber.MESSAGE_CONSTRAINTS);
        }
        return new RoomNumber(trimmedRoomNumber);
    }

    /**
     * Parses a {@code String passportNumber} into a {@code passportNumber}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code passportNumber} is invalid.
     */
    public static PassportNumber parsePassportNumber(String passportNumber) throws ParseException {
        requireNonNull(passportNumber);
        String trimmedPassportNumber = passportNumber.trim();
        if (!PassportNumber.isValidPassportNumber(trimmedPassportNumber)) {
            throw new ParseException(PassportNumber.MESSAGE_CONSTRAINTS);
        }
        return new PassportNumber(trimmedPassportNumber.toUpperCase());
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
        return new Tag(StringUtil.capitalizeFirstLetter(trimmedTag));
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
     * Parses a {@code String serviceName} into a {@code ServiceName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code ServiceName} is invalid.
     */
    public static ServiceName parseServiceName(String serviceName) throws ParseException {
        requireNonNull(serviceName);
        String trimmedServiceName = serviceName.trim();
        if (!ServiceName.isValidServiceName(trimmedServiceName)) {
            throw new ParseException(ServiceName.MESSAGE_CONSTRAINTS);
        }
        return new ServiceName(StringUtil.capitalizeFirstLetter(trimmedServiceName));
    }

    /**
     * Parses a {@code String cost} into a {@code Cost}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Cost} is invalid.
     */
    public static Cost parseCost(String cost) throws ParseException {
        requireNonNull(cost);
        String trimmedCost = cost.trim();
        Double result;
        try {
            result = Math.round(Double.parseDouble(trimmedCost) * 100.0) / 100.0;
        } catch (Exception e) {
            throw new ParseException(Cost.INVALID_DOUBLE);
        }
        if (!Cost.isValidCost(result)) {
            throw new ParseException(Cost.MESSAGE_CONSTRAINTS);
        }
        return new Cost(result);
    }

    /**
     * Parses a {@code String operatingHours} into a {@code OperatingHours}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code OperatingHours} is invalid.
     */
    public static OperatingHours parseOperatingHours(String operatingHours) throws ParseException {
        requireNonNull(operatingHours);
        String trimmedOperatingHours = operatingHours.trim();

        if (!OperatingHours.isValidOperatingHours(trimmedOperatingHours)) {
            throw new ParseException(OperatingHours.MESSAGE_CONSTRAINTS);
        }

        String[] args = trimmedOperatingHours.split("\\s+");
        String days = args[0];
        String startTimeString = args[1].split("-")[0];
        Integer startTimeHour = Integer.parseInt(startTimeString.substring(0, 2));
        Integer startTimeMinutes = Integer.parseInt(startTimeString.substring(2));
        String endTimeString = args[1].split("-")[1];
        Integer endTimeHour = Integer.parseInt(endTimeString.substring(0, 2));
        Integer endTimeMinutes = Integer.parseInt(endTimeString.substring(2));

        LocalTime startTime = LocalTime.of(startTimeHour, startTimeMinutes);
        LocalTime endTime = LocalTime.of(endTimeHour, endTimeMinutes);

        if (!OperatingHours.isValidTimings(startTime, endTime)) {
            throw new ParseException(OperatingHours.MESSAGE_CONSTRAINTS);
        }

        List<DayOfWeek> operatingDays = new ArrayList<>();

        for (int i = 0; i < days.length(); i++) {
            DayOfWeek day = DayOfWeek.of(Character.getNumericValue(days.charAt(i)));
            if (!operatingDays.contains(day)) {
                operatingDays.add(day);
            }
        }

        operatingDays.sort(new Comparator<DayOfWeek>() {
            public int compare(DayOfWeek d1, DayOfWeek d2) {
                return d1.compareTo(d2);
            }
        });

        return new OperatingHours(startTime, endTime, operatingDays, trimmedOperatingHours);
    }

}
