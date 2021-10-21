package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.comparator.*;

import java.util.Locale;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String trimmedArgs = args.trim().toLowerCase(Locale.ROOT);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_NATIONALITY,
                        PREFIX_TUTORIAL_GROUP, PREFIX_SOCIAL_HANDLE, PREFIX_GENDER, PREFIX_REMARK, PREFIX_TAG);

        if (argMultimap.getValue(PREFIX_NAME).equals(trimmedArgs)) {
            return new SortCommand(new NameComparator());
        }
        if (argMultimap.getValue(PREFIX_PHONE).equals(trimmedArgs)) {
            return new SortCommand(new PhoneComparator());
        }
        if (argMultimap.getValue(PREFIX_EMAIL).equals(trimmedArgs)) {
            return new SortCommand(new EmailComparator());
        }
        if (argMultimap.getValue(PREFIX_NATIONALITY).equals(trimmedArgs)) {
            return new SortCommand(new NationalityComparator());
        }
        if (argMultimap.getValue(PREFIX_TUTORIAL_GROUP).equals(trimmedArgs)) {
            return new SortCommand(new TutorialGroupComparator());
        }
        if (argMultimap.getValue(PREFIX_SOCIAL_HANDLE).equals(trimmedArgs)) {
            return new SortCommand(new SocialHandleComparator());
        }
        if (argMultimap.getValue(PREFIX_GENDER).equals(trimmedArgs)) {
            return new SortCommand(new GenderComparator());
        }
        if (argMultimap.getValue(PREFIX_REMARK).equals(trimmedArgs)) {
            return new SortCommand(new RemarkComparator());
        }
        if (argMultimap.getValue(PREFIX_TAG).equals(trimmedArgs)) {
            return new SortCommand(new TagComparator());
        }

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }
}
