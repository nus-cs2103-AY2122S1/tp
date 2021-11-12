package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Github;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Telegram;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @param args Arguments passed into the function.
     * @return AddCommand object for execution.
     * @throws ParseException if there is any invalid input.
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TELEGRAM, PREFIX_GITHUB, PREFIX_PHONE,
                        PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME) && !arePrefixesPresent(argMultimap, PREFIX_GITHUB)
            && !arePrefixesPresent(argMultimap, PREFIX_TELEGRAM)) {
            String errorMessage = AddCommand.MESSAGE_ALL_COMPULSORY_FIELDS_MISSING
                    + "\n" + AddCommand.MESSAGE_USAGE;
            throw new ParseException(errorMessage);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME) && !arePrefixesPresent(argMultimap, PREFIX_GITHUB)) {
            String errorMessage = AddCommand.MESSAGE_NAME_GITHUB_FIELDS_MISSING
                    + "\n" + AddCommand.MESSAGE_USAGE;
            throw new ParseException(errorMessage);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME) && !arePrefixesPresent(argMultimap, PREFIX_TELEGRAM)) {
            String errorMessage = AddCommand.MESSAGE_NAME_TELEGRAM_FIELDS_MISSING
                    + "\n" + AddCommand.MESSAGE_USAGE;
            throw new ParseException(errorMessage);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_GITHUB) && !arePrefixesPresent(argMultimap, PREFIX_TELEGRAM)) {
            String errorMessage = AddCommand.MESSAGE_GITHUB_TELEGRAM_FIELDS_MISSING
                    + "\n" + AddCommand.MESSAGE_USAGE;
            throw new ParseException(errorMessage);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)) {
            String errorMessage = AddCommand.MESSAGE_NAME_FIELD_MISSING
                    + "\n" + AddCommand.MESSAGE_USAGE;
            throw new ParseException(errorMessage);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_GITHUB)) {
            String errorMessage = AddCommand.MESSAGE_GITHUB_FIELD_MISSING
                    + "\n" + AddCommand.MESSAGE_USAGE;
            throw new ParseException(errorMessage);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_TELEGRAM)) {
            String errorMessage = AddCommand.MESSAGE_TELEGRAM_FIELD_MISSING
                    + "\n" + AddCommand.MESSAGE_USAGE;
            throw new ParseException(errorMessage);
        }

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Telegram telegram = ParserUtil.parseTelegram(argMultimap.getValue(PREFIX_TELEGRAM).get());
        Github github = ParserUtil.parseGithub(argMultimap.getValue(PREFIX_GITHUB).get());
        Phone phone = new Phone("");
        if (arePrefixesPresent(argMultimap, PREFIX_PHONE)) {
            phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        }
        Email email = new Email("");
        if (arePrefixesPresent(argMultimap, PREFIX_EMAIL)) {
            email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        }
        Address address = new Address("");
        if (arePrefixesPresent(argMultimap, PREFIX_ADDRESS)) {
            address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        }
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Person person = new Person(name, telegram, github, phone, email, address, tagList);

        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     *
     * @param argumentMultimap ArgumentMultimap used to get value of prefixes.
     * @param prefixes Prefixes that are passed into the function.
     * @return boolean representation of whether all the prefixes passed into
     * this method is present in the ArgumentMultimap.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
