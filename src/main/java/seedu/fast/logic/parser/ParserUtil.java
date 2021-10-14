package seedu.fast.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_HELP_COMMAND_FORMAT;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.fast.commons.core.index.Index;
import seedu.fast.commons.util.StringUtil;
import seedu.fast.logic.commands.AppointmentCommand;
import seedu.fast.logic.commands.HelpCommand;
import seedu.fast.logic.parser.exceptions.HelpParseException;
import seedu.fast.logic.parser.exceptions.ParseException;
import seedu.fast.model.person.Address;
import seedu.fast.model.person.Appointment;
import seedu.fast.model.person.Email;
import seedu.fast.model.person.Name;
import seedu.fast.model.person.Phone;
import seedu.fast.model.tag.PriorityTag;
import seedu.fast.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    public static final String[] COMMAND_LIST = new String[]{"Quick Start", "Add", "Appointment", "Clear", "Delete",
        "Edit", "Find", "List", "Help", "Remark", "Sort", "Tag", "Priority Tag", "Misc"};

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
        trimmedName = capitalise(trimmedName);
        return new Name(trimmedName);
    }

    /**
     * Takes in a {@code String trimmedName} and capitalises each letter
     * after a whitespace.
     *
     * @param trimmedName
     * @return trimmedName with capitalised words.
     */
    private static String capitalise(String trimmedName) {
        char[] chars = trimmedName.toLowerCase().toCharArray();
        if (Character.isLetter(chars[0])) {
            chars[0] = Character.toUpperCase(chars[0]);
            //Capitalise first letter
        }
        for (int i = 1; i < chars.length - 1; i++) {
            char current = chars[i];
            char next = chars[i + 1];
            if (Character.isWhitespace(current) && Character.isLetter(next)) {
                chars[i + 1] = Character.toUpperCase(next);
            }
            //Capitalise any letter after a whitespace
        }
        trimmedName = String.valueOf(chars);
        return trimmedName;
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
        return new Email(trimmedEmail);
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
        if (!Tag.isValidTagTerm(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return Tag.createTag(trimmedTag);
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
     * Parses {@code String tagName} and returns the corresponding priority tag name.
     */
    public static String parsePriorityTag(String tagName) {
        switch (tagName) {
        case PriorityTag.LowPriority.COMMAND:
            return PriorityTag.LowPriority.NAME;
        case PriorityTag.MediumPriority.COMMAND:
            return PriorityTag.MediumPriority.NAME;
        default:
            return PriorityTag.HighPriority.NAME;
        //It is guaranteed that the default case will always be a high priority tag instance.
        }
    }

    /**
     * Parses {@code String command} and returns the corresponding help command.
     */
    public static String matchArgs(String command) {
        for (String s : COMMAND_LIST) {
            if (s.equals(command)) {
                return s;
            }
        }
        return "";
    }

    /**
     * Extracts the arguments from a help command.
     *
     * @param commandText The input text.
     * @return The args of the help command, or "" if there is no or invalid args.
     * @throws HelpParseException if help is not followed by a valid arg
     */
    public static String parseHelp(String commandText) throws HelpParseException {

        // if there are no args
        if (commandText.split(" ").length == 1) {
            return "";
        }

        String arg = commandText.substring(HelpCommand.COMMAND_WORD.length());
        String trimmedArgs = arg.trim();
        String capitalisedArg = ParserUtil.capitaliseFirstLetters(trimmedArgs);

        if (!ParserUtil.matchArgs(capitalisedArg).equals("")) {
            return capitalisedArg;

        } else { // if the arg does not match a given command, throw exception
            throw new HelpParseException(
                String.format(MESSAGE_INVALID_HELP_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

    }

    /**
     * Capitalise the start of each word in the args
     */
    public static String capitaliseFirstLetters(String inputString) {
        String[] words = inputString.split(" ");
        StringBuilder capitalisedWordsBuilder = new StringBuilder();

        for (String s : words) {
            // Capitalises the first letter of the word
            capitalisedWordsBuilder.append(s.substring(0, 1).toUpperCase()).append(s.substring(1));
            capitalisedWordsBuilder.append(" ");
        }
        return capitalisedWordsBuilder.toString().trim();
    }

    /**
     * Checks if the retrieved date from user input is valid.
     *
     * A valid date input is of the format yyyy-mm-dd.
     * `mm` is a 2-digit number in the range 01-12, which represents a calendar month.
     * `dd` is a 2-digit number in the range of 01-31, depending on the number of days in the calendar month.
     *
     * If the retrieved date is valid, returns the date in `dd MMM yyyy` format.
     * Otherwise, it means that the user did not enter the correct input. A ParseException will be thrown.
     *
     * @param date Date String retrieved from user input
     * @return A String representing the date in the specified format if it is valid (for add/update)
     * @throws ParseException Thrown when the date retrieved is invalid
     */
    public static String parseDateString(String date) throws ParseException {
        try {
            // converts the date to the specified format
            date = LocalDate.parse(date).format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        } catch (DateTimeParseException dtpe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AppointmentCommand.MESSAGE_USAGE), dtpe);
        }

        return date.trim();
    }

    /**
     * Checks if the retrieved time from user input is valid.
     *
     * A valid time input is of the format hh:mm (in 24-hour format).
     * `hh` is a 2-digit number in the range 00-23, which represents the hour in the 24-hour format.
     * `mm` is a 2-digit number in the range of 00-59, which represents the minute in the 24-hour format.
     *
     * If the retrieved time is valid, returns the time in `HHmm` format.
     * Otherwise, it means that the user did not enter the correct input. A ParseException will be thrown.
     *
     * @param time Time String retrieved from user input
     * @return A String representing the time in the specified format if it is valid.
     * @throws ParseException Thrown when the date retrieved is invalid
     */
    public static String parseTimeString(String time) throws ParseException {
        String validationPattern = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$";

        if (!time.equals(Appointment.NO_TIME)) {
            // checks that time only contains HH:mm and nothing else
            if (!time.matches(validationPattern)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AppointmentCommand.MESSAGE_USAGE));
            }

            try {
                // converts the time to the specified format
                time = LocalTime.parse(time).format(DateTimeFormatter.ofPattern("HHmm"));
            } catch (DateTimeParseException dtpe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AppointmentCommand.MESSAGE_USAGE), dtpe);
            }
        }

        return time.trim();
    }

    /**
     * Checks if the retrieved venue from user input is too long.
     * The venue description should not be longer than 50 characters.
     *
     * If the retrieved venue if longer than 30 characters, the string will be truncated.
     *
     * @param venue Time String retrieved from user input
     * @return A String representing the venue (no longer than 50 characters).
     */
    public static String parseVenueString(String venue) throws ParseException {
        if (venue.length() > 30) {
            return venue.substring(0, 29);
        }

        return venue.trim();
    }

}
