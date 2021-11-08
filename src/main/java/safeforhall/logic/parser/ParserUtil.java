package safeforhall.logic.parser;

import static java.util.Objects.requireNonNull;
import static safeforhall.logic.commands.sort.SortPersonCommand.ALLOWED_ORDER;
import static safeforhall.logic.commands.sort.SortPersonCommand.ASCENDING;
import static safeforhall.logic.commands.sort.SortPersonCommand.DESCENDING;

import java.util.ArrayList;
import java.util.Arrays;

import safeforhall.commons.core.Messages;
import safeforhall.commons.core.index.Index;
import safeforhall.commons.util.StringUtil;
import safeforhall.logic.commands.ExportCommand;
import safeforhall.logic.commands.ImportCommand;
import safeforhall.logic.commands.sort.SortEventCommand;
import safeforhall.logic.commands.sort.SortPersonCommand;
import safeforhall.logic.parser.exceptions.ParseException;
import safeforhall.model.event.Capacity;
import safeforhall.model.event.EventDate;
import safeforhall.model.event.EventName;
import safeforhall.model.event.EventTime;
import safeforhall.model.event.ResidentList;
import safeforhall.model.event.Venue;
import safeforhall.model.person.Email;
import safeforhall.model.person.Faculty;
import safeforhall.model.person.LastDate;
import safeforhall.model.person.Name;
import safeforhall.model.person.Phone;
import safeforhall.model.person.Room;
import safeforhall.model.person.VaccStatus;

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
            throw new ParseException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        } else {
            return Index.fromOneBased(Integer.parseInt(trimmedIndex));
        }
    }

    /**
     * Parses {@code indexes} into their relevant {@code Index} and returns it.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    @SafeVarargs
    public static ArrayList<Index> parseIndexes(String... indexes) throws ParseException {
        ArrayList<Index> indexArray = new ArrayList<>();
        for (String i : indexes) {
            if (!StringUtil.isNonZeroUnsignedInteger(i)) {
                throw new ParseException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            indexArray.add(Index.fromOneBased(Integer.parseInt(i)));
        }
        return indexArray;
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
     * Parses a {@code String room} into a {@code Room}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code room} is invalid.
     */
    public static Room parseRoom(String room) throws ParseException {
        requireNonNull(room);
        String trimmedRoom = room.trim();
        if (!Room.isValidRoom(trimmedRoom)) {
            throw new ParseException(Room.MESSAGE_CONSTRAINTS);
        }
        return new Room(trimmedRoom);
    }

    /**
     * Parses a {@code String room} into a {@code Room}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code room} is invalid.
     */
    public static String parseRoomForFind(String room) throws ParseException {
        requireNonNull(room);
        String trimmedRoom = room.trim();
        if (!Room.isValidRoomForFind(trimmedRoom)) {
            throw new ParseException(Room.MESSAGE_CONSTRAINTS_FOR_FIND);
        }
        return trimmedRoom;
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
     * Parses a {@code String vaccStatus} into a {@code VaccStatus}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code vaccStatus} is invalid.
     */
    public static VaccStatus parseVaccStatus(String vaccStatus) throws ParseException {
        requireNonNull(vaccStatus);
        String trimmedVaccStatus = vaccStatus.trim();
        if (!VaccStatus.isValidVaccStatus(trimmedVaccStatus)) {
            throw new ParseException(VaccStatus.MESSAGE_CONSTRAINTS);
        }
        return new VaccStatus(trimmedVaccStatus);
    }

    /**
     * Parses a {@code String faculty} into a {@code Faculty}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code faculty} is invalid.
     */
    public static Faculty parseFaculty(String faculty) throws ParseException {
        requireNonNull(faculty);
        String trimmedFaculty = faculty.trim();
        if (!Faculty.isValidFaculty(trimmedFaculty)) {
            throw new ParseException(Faculty.MESSAGE_CONSTRAINTS);
        }
        return new Faculty(trimmedFaculty);
    }

    /**
     * Parses a {@code String date} into a {@code LastDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static LastDate parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!LastDate.isValidDate(trimmedDate)) {
            throw new ParseException(LastDate.MESSAGE_CONSTRAINTS);
        }
        if (LastDate.isFutureDate(trimmedDate)) {
            throw new ParseException(LastDate.MESSAGE_IS_FUTURE_DATE);
        }

        return new LastDate(trimmedDate);
    }

    /**
     * Parses a {@code String eventName} into a {@code EventName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code eventName} is invalid.
     */
    public static EventName parseEventName(String eventName) throws ParseException {
        requireNonNull(eventName);
        String trimmedEventName = eventName.trim();
        if (!EventName.isValidEventName(trimmedEventName)) {
            throw new ParseException(EventName.MESSAGE_CONSTRAINTS);
        }
        return new EventName(trimmedEventName);
    }

    /**
     * Parses a {@code String eventDate} into a {@code EventDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code eventDate} is invalid.
     */
    public static EventDate parseEventDate(String eventDate) throws ParseException {
        requireNonNull(eventDate);
        String trimmedEventDate = eventDate.trim();
        if (!EventDate.isValidEventDate(trimmedEventDate)) {
            throw new ParseException(EventDate.MESSAGE_CONSTRAINTS);
        }
        return new EventDate(trimmedEventDate);
    }

    /**
     * Parses a {@code String eventTime} into a {@code EventTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code eventTime} is invalid.
     */
    public static EventTime parseEventTime(String eventTime) throws ParseException {
        requireNonNull(eventTime);
        String trimmedEventTime = eventTime.trim();
        if (!EventTime.isValidEventTime(trimmedEventTime)) {
            throw new ParseException(EventTime.MESSAGE_CONSTRAINTS);
        }
        return new EventTime(trimmedEventTime);
    }

    /**
     * Parses a {@code String venue} into a {@code Venue}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code venue} is invalid.
     */
    public static Venue parseVenue(String venue) throws ParseException {
        requireNonNull(venue);
        String trimmedVenue = venue.trim();
        if (!Venue.isValidVenue(trimmedVenue)) {
            throw new ParseException(Venue.MESSAGE_CONSTRAINTS);
        }
        return new Venue(trimmedVenue);
    }

    /**
     * Parses a {@code String capacity} into a {@code Capacity}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code capacity} is invalid.
     */
    public static Capacity parseCapacity(String capacity) throws ParseException {
        requireNonNull(capacity);
        String trimmedCapacity = capacity.trim();
        if (!Capacity.isValidCapacity(trimmedCapacity)) {
            throw new ParseException(Capacity.MESSAGE_CONSTRAINTS);
        }
        return new Capacity(trimmedCapacity);
    }

    /**
     * Parses a {@code String information} into a {@code InformationList}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code information} is invalid.
     */
    public static ResidentList parseResidents(String information) throws ParseException {
        requireNonNull(information);
        String trimmedInformation = information.trim();
        if (!ResidentList.isValidResidentList(trimmedInformation)) {
            throw new ParseException(ResidentList.MESSAGE_CONSTRAINTS);
        }
        return new ResidentList(trimmedInformation);
    }

    /**
     * Parse a {@code String fileName} into a {@code String fileName} for ExportCommand.
     * Leading and trailing whitespaces will be trimmed.
     * Ensures that fileName is a single word with no whitespace.
     *
     * @throws ParseException if the given {@code information} is invalid.
     */
    public static String parseExportFileName(String fileName) throws ParseException {
        requireNonNull(fileName);
        String trimmedFileName = fileName.trim();

        if (trimmedFileName.isEmpty() || trimmedFileName.contains(" ")) {
            throw new ParseException(ExportCommand.MESSAGE_CONSTRAINTS);
        }

        return trimmedFileName;
    }

    /**
     * Parse a {@code String fileName} into a {@code String fileName} for ImportCommand.
     * Leading and trailing whitespaces will be trimmed.
     * Ensures that fileName is a single word with no whitespace.
     *
     * @throws ParseException if the given {@code information} is invalid.
     */
    public static String parseImportFileName(String fileName) throws ParseException {
        requireNonNull(fileName);
        String trimmedFileName = fileName.trim();

        if (trimmedFileName.isEmpty() || trimmedFileName.contains(" ")) {
            throw new ParseException(ImportCommand.MESSAGE_CONSTRAINTS);
        }

        return trimmedFileName;
    }

    /**
     * Parse a {@code String field} into a {@code String field} for SortPersonCommand.
     * Leading and trailing whitespaces will be trimmed.
     * Ensures that field is valid.
     *
     * @throws ParseException if the given {@code information} is invalid.
     */
    public static String parsePersonField(String field) throws ParseException {
        requireNonNull(field);
        String trimmedField = field.trim().toLowerCase();
        ArrayList<String> allowedFields = new ArrayList<>(Arrays.asList(Name.FIELD,
                Email.FIELD, Room.FIELD, Phone.FIELD, Faculty.FIELD, VaccStatus.FIELD,
                LastDate.FET_FIELD, LastDate.COLLECTION_FIELD));

        if (!allowedFields.contains(trimmedField)) {
            throw new ParseException(SortPersonCommand.ALLOWED_FIELDS);
        }
        return trimmedField;
    }

    /**
     * Parse a {@code String field} into a {@code String field} for SortEventCommand.
     * Leading and trailing whitespaces will be trimmed.
     * Ensures that field is valid.
     *
     * @throws ParseException if the given {@code information} is invalid.
     */
    public static String parseEventField(String field) throws ParseException {
        requireNonNull(field);
        String trimmedField = field.trim().toLowerCase();
        ArrayList<String> allowedFields = new ArrayList<>(Arrays.asList(EventName.FIELD,
                EventDate.FIELD, Capacity.FIELD, Venue.FIELD));

        if (!allowedFields.contains(trimmedField)) {
            throw new ParseException(SortEventCommand.ALLOWED_FIELDS);
        }
        return trimmedField;
    }

    /**
     * Parse a {@code String order} into a {@code String order} for SortCommand.
     * Leading and trailing whitespaces will be trimmed.
     * Ensures that field is valid.
     *
     * @throws ParseException if the given {@code information} is invalid.
     */
    public static String parseOrder(String order) throws ParseException {
        requireNonNull(order);
        String trimmedOrder = order.trim().toLowerCase();

        if (!trimmedOrder.equals(ASCENDING) && !trimmedOrder.equals(DESCENDING)) {
            throw new ParseException(ALLOWED_ORDER);
        }
        return trimmedOrder;
    }

}

