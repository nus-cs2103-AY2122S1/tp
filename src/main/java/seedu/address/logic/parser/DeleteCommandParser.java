package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.ALL_PREFIXES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENTID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.allPrefixLess;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ClientId;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonHasEmail;
import seedu.address.model.person.PersonHasId;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {


    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, ALL_PREFIXES);

        if (!anyPrefixesPresent(argMultimap, PREFIX_CLIENTID, PREFIX_EMAIL) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }

        //Throws error if other fields are inputted
        if (anyPrefixesPresent(argMultimap, allPrefixLess(PREFIX_CLIENTID, PREFIX_EMAIL))) {
            throw new ParseException(String.format(Messages.MESSAGE_TOO_MANY_FIELDS, DeleteCommand.MESSAGE_USAGE));
        }

        ArrayList<Predicate<Person>> predicatesToDelete = new ArrayList<>();
        String clientIdString = argMultimap.getValue(PREFIX_CLIENTID).orElse("");
        if (!clientIdString.isEmpty()) {
            ClientId clientId = ParserUtil.parseClientId(clientIdString);
            predicatesToDelete.add(new PersonHasId(clientId));
        }

        String emailString = argMultimap.getValue(PREFIX_EMAIL).orElse("");
        if (!emailString.isEmpty()) {
            Email email = ParserUtil.parseEmail(emailString);
            predicatesToDelete.add(new PersonHasEmail(email));
        }

        return new DeleteCommand(predicatesToDelete);

    }

    /**
     * Returns true if any of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean anyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
