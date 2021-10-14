package seedu.notor.logic.parser.person;

import static seedu.notor.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.notor.logic.commands.person.PersonCommand;
import seedu.notor.logic.commands.person.PersonCreateCommand;
import seedu.notor.logic.parser.ArgumentMultimap;
import seedu.notor.logic.parser.ArgumentTokenizer;
import seedu.notor.logic.parser.Parser;
import seedu.notor.logic.parser.ParserUtil;
import seedu.notor.logic.parser.Prefix;
import seedu.notor.logic.parser.exceptions.ParseException;
import seedu.notor.model.person.Email;
import seedu.notor.model.person.Name;
import seedu.notor.model.person.Note;
import seedu.notor.model.person.Person;
import seedu.notor.model.person.Phone;
import seedu.notor.model.tag.Tag;

public class PersonCreateCommandParser extends Parser<PersonCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public PersonCreateCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PersonCreateCommand.MESSAGE_USAGE));
        }

        assert argMultimap.getValue(PREFIX_NAME).isPresent();
        assert argMultimap.getValue(PREFIX_PHONE).isPresent();
        assert argMultimap.getValue(PREFIX_EMAIL).isPresent();

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Note note = new Note("", "");
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Person person = new Person(name, phone, email, note, tagList);

        return new PersonCreateCommand(null, person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
