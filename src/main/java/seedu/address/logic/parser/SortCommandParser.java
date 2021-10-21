package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NATIONALITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SOCIAL_HANDLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.comparator.EmailComparator;
import seedu.address.model.comparator.GenderComparator;
import seedu.address.model.comparator.NameComparator;
import seedu.address.model.comparator.NationalityComparator;
import seedu.address.model.comparator.PhoneComparator;
import seedu.address.model.comparator.RemarkComparator;
import seedu.address.model.comparator.SocialHandleComparator;
import seedu.address.model.comparator.TagComparator;
import seedu.address.model.comparator.TutorialGroupComparator;

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

        String trimmedArgs = args.trim().toLowerCase();

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_NATIONALITY,
                        PREFIX_TUTORIAL_GROUP, PREFIX_SOCIAL_HANDLE, PREFIX_GENDER, PREFIX_REMARK, PREFIX_TAG);

        if (trimmedArgs.length() > 3) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            return new SortCommand(new NameComparator());
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            return new SortCommand(new PhoneComparator());
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            return new SortCommand(new EmailComparator());
        }
        if (argMultimap.getValue(PREFIX_NATIONALITY).isPresent()) {
            return new SortCommand(new NationalityComparator());
        }
        if (argMultimap.getValue(PREFIX_TUTORIAL_GROUP).isPresent()) {
            return new SortCommand(new TutorialGroupComparator());
        }
        if (argMultimap.getValue(PREFIX_SOCIAL_HANDLE).isPresent()) {
            return new SortCommand(new SocialHandleComparator());
        }
        if (argMultimap.getValue(PREFIX_GENDER).isPresent()) {
            return new SortCommand(new GenderComparator());
        }
        if (argMultimap.getValue(PREFIX_REMARK).isPresent()) {
            return new SortCommand(new RemarkComparator());
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            return new SortCommand(new TagComparator());
        }

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }
}
