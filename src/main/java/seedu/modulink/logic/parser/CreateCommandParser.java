package seedu.modulink.logic.parser;

import static seedu.modulink.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_GITHUB_USERNAME;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_MOD;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_TELEGRAM_HANDLE;

import java.util.Set;
import java.util.stream.Stream;

import seedu.modulink.commons.util.StringUtil;
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

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ID, PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()
                || numberOfValidPrefixes(argMultimap) != StringUtil.countMatch(args, '/')) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateCommand.MESSAGE_USAGE));
        }

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
    }

    /**
     * Checks how many valid prefixes are present in args.
     *
     * @param argMultimap tokenized list of arguments.
     * @return number of provided prefixes.
     */
    private int numberOfValidPrefixes(ArgumentMultimap argMultimap) throws ParseException {
        int i = 0;
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            i++;
        }
        if (argMultimap.getValue(PREFIX_ID).isPresent()) {
            i++;
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            i++;
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            i++;
        }
        if (argMultimap.getValue(PREFIX_GITHUB_USERNAME).isPresent()) {
            i++;
        }
        if (argMultimap.getValue(PREFIX_TELEGRAM_HANDLE).isPresent()) {
            i++;
        }

        i += ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_MOD)).size();
        return i;
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
