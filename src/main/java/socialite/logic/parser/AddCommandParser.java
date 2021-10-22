package socialite.logic.parser;

import java.util.Set;
import java.util.stream.Stream;

import static socialite.logic.parser.CliSyntax.PREFIX_DATES;
import static socialite.logic.parser.CliSyntax.PREFIX_FACEBOOK;
import static socialite.logic.parser.CliSyntax.PREFIX_INSTAGRAM;
import static socialite.logic.parser.CliSyntax.PREFIX_NAME;
import static socialite.logic.parser.CliSyntax.PREFIX_PHONE;
import static socialite.logic.parser.CliSyntax.PREFIX_REMARK;
import static socialite.logic.parser.CliSyntax.PREFIX_TAG;
import static socialite.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static socialite.logic.parser.CliSyntax.PREFIX_TIKTOK;
import static socialite.logic.parser.CliSyntax.PREFIX_TWITTER;

import socialite.commons.core.Messages;
import socialite.logic.commands.AddCommand;
import socialite.logic.parser.exceptions.ParseException;
import socialite.model.handle.Facebook;
import socialite.model.handle.Instagram;
import socialite.model.handle.Telegram;
import socialite.model.handle.TikTok;
import socialite.model.handle.Twitter;
import socialite.model.person.Dates;
import socialite.model.person.Name;
import socialite.model.person.Person;
import socialite.model.person.Phone;
import socialite.model.person.Remark;
import socialite.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args, PREFIX_NAME, PREFIX_PHONE, PREFIX_REMARK, PREFIX_TAG,
                        PREFIX_FACEBOOK, PREFIX_INSTAGRAM, PREFIX_TELEGRAM, PREFIX_TIKTOK, PREFIX_TWITTER,
                        PREFIX_DATES);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_HELP_GUIDE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Remark remark = ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).orElse(""));
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Facebook facebook = ParserUtil.parseFacebook(argMultimap.getValue(PREFIX_FACEBOOK).orElse(null));
        Instagram instagram = ParserUtil.parseInstagram(argMultimap.getValue(PREFIX_INSTAGRAM).orElse(null));
        Telegram telegram = ParserUtil.parseTelegram(argMultimap.getValue(PREFIX_TELEGRAM).orElse(null));
        TikTok tiktok = ParserUtil.parseTikTok(argMultimap.getValue(PREFIX_TIKTOK).orElse(null));
        Twitter twitter = ParserUtil.parseTwitter(argMultimap.getValue(PREFIX_TWITTER).orElse(null));
        Dates dates = ParserUtil.parseDates(argMultimap.getValue(PREFIX_DATES).get());

        Person person = new Person(name, phone, remark, tagList, facebook,
                instagram, telegram, tiktok, twitter, dates);

        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
