package seedu.modulink.logic.parser;

import static seedu.modulink.commons.core.Messages.MESSAGE_DUPLICATE_PREFIX_FORMAT;
import static seedu.modulink.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modulink.commons.core.Messages.MESSAGE_MISSING_PREFIXES_FORMAT;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_GITHUB_USERNAME;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_MOD;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_TELEGRAM_HANDLE;

import java.util.Set;
import java.util.stream.Stream;

import seedu.modulink.logic.commands.CreateCommand;
import seedu.modulink.logic.parser.exceptions.ParseException;
import seedu.modulink.model.person.Email;
import seedu.modulink.model.person.GitHubUsername;
import seedu.modulink.model.person.Name;
import seedu.modulink.model.person.Person;
import seedu.modulink.model.person.Phone;
import seedu.modulink.model.person.StudentId;
import seedu.modulink.model.person.TelegramHandle;
import seedu.modulink.model.tag.Mod;


/**
 * Parses input arguments and creates a new AddCommand object
 */
public class CreateCommandParser implements Parser<CreateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CreateCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID, PREFIX_PHONE,
                        PREFIX_EMAIL, PREFIX_GITHUB_USERNAME, PREFIX_TELEGRAM_HANDLE, PREFIX_MOD);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ID, PREFIX_PHONE, PREFIX_EMAIL)) {
            StringBuilder missingPrefixes = findMissingPrefixes(argMultimap,
                    PREFIX_NAME, PREFIX_ID, PREFIX_PHONE, PREFIX_EMAIL);
            throw new ParseException(String.format(MESSAGE_MISSING_PREFIXES_FORMAT,
                    missingPrefixes, CreateCommand.MESSAGE_USAGE));

        }

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateCommand.MESSAGE_USAGE));
        }

        // either duplicate parameters, or unsupported parameters (dealt with in next try-catch block)
        try {
            ParserUtil.checkDuplicate(args, argMultimap, ParserUtil.isDuplicatePrefix(args, PREFIX_NAME, PREFIX_ID,
                    PREFIX_PHONE, PREFIX_EMAIL, PREFIX_GITHUB_USERNAME, PREFIX_TELEGRAM_HANDLE));
        } catch (ParseException e) {
            StringBuilder duplicatePrefixes = ParserUtil.findDuplicatePrefixes(args,
                    PREFIX_NAME, PREFIX_ID, PREFIX_PHONE,
                    PREFIX_EMAIL, PREFIX_GITHUB_USERNAME, PREFIX_TELEGRAM_HANDLE);
            throw new ParseException(String.format(MESSAGE_DUPLICATE_PREFIX_FORMAT,
                    duplicatePrefixes, CreateCommand.MESSAGE_USAGE));
        }

        try {
            Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
            StudentId id = ParserUtil.parseStudentId(argMultimap.getValue(PREFIX_ID).orElse(null));
            Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
            GitHubUsername gitHubUsername =
                    ParserUtil.parseGithubUsername(argMultimap.getValue(PREFIX_GITHUB_USERNAME).orElse(null));
            TelegramHandle telegramHandle =
                    ParserUtil.parseTelegramHandle(argMultimap.getValue(PREFIX_TELEGRAM_HANDLE).orElse(null));
            Set<Mod> modList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_MOD));
            Person person = new Person(name, id, phone, email, gitHubUsername,
                    telegramHandle, false, modList, true);
            return new CreateCommand(person);

        } catch (ParseException e) {
            throw new ParseException(String.format(e.getMessage() + "%s",
                    e.getMessage().startsWith("Unknown prefix(es)") ? CreateCommand.MESSAGE_USAGE : ""));
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns any prefixes that are missing.
     * {@code ArgumentMultimap}.
     */
    private static StringBuilder findMissingPrefixes(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        StringBuilder missingPrefixList = new StringBuilder();
        for (Prefix prefix : prefixes) {
            if (argumentMultimap.getValue(prefix).isEmpty()) {
                missingPrefixList.append(prefix).append(" ");
            }
        }
        return missingPrefixList;
    }

}
