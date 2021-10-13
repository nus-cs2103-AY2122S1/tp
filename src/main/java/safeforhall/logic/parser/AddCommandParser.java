package safeforhall.logic.parser;

import static safeforhall.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static safeforhall.logic.parser.CliSyntax.PREFIX_COLLECTIONDATE;
import static safeforhall.logic.parser.CliSyntax.PREFIX_EMAIL;
import static safeforhall.logic.parser.CliSyntax.PREFIX_FACULTY;
import static safeforhall.logic.parser.CliSyntax.PREFIX_FETDATE;
import static safeforhall.logic.parser.CliSyntax.PREFIX_NAME;
import static safeforhall.logic.parser.CliSyntax.PREFIX_PHONE;
import static safeforhall.logic.parser.CliSyntax.PREFIX_ROOM;
import static safeforhall.logic.parser.CliSyntax.PREFIX_VACCSTATUS;

import java.util.stream.Stream;

import safeforhall.logic.commands.AddCommand;
import safeforhall.logic.parser.exceptions.ParseException;
import safeforhall.model.person.Email;
import safeforhall.model.person.Faculty;
import safeforhall.model.person.LastDate;
import safeforhall.model.person.Name;
import safeforhall.model.person.Person;
import safeforhall.model.person.Phone;
import safeforhall.model.person.Room;
import safeforhall.model.person.VaccStatus;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE,
                        PREFIX_EMAIL, PREFIX_ROOM, PREFIX_VACCSTATUS, PREFIX_FACULTY,
                        PREFIX_FETDATE, PREFIX_COLLECTIONDATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_ROOM, PREFIX_VACCSTATUS, PREFIX_FACULTY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        // Required fields
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Room room = ParserUtil.parseRoom(argMultimap.getValue(PREFIX_ROOM).get());
        VaccStatus vaccStatus = ParserUtil.parseVaccStatus(argMultimap.getValue(PREFIX_VACCSTATUS).get());
        Faculty faculty = ParserUtil.parseFaculty(argMultimap.getValue(PREFIX_FACULTY).get());

        // Optional fields
        LastDate lastFetDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_FETDATE)
                .orElse(LastDate.DEFAULT_DATE));
        LastDate lastCollectionDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_COLLECTIONDATE)
                .orElse(LastDate.DEFAULT_DATE));

        Person person = new Person(name, room, phone, email, vaccStatus, faculty, lastFetDate, lastCollectionDate);
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
