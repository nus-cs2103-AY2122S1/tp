package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddPersonToEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventNameEqualKeywordPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameEqualKeywordPredicate;


/**
 * Parses input arguments and creates a new AddPersonToEventCommand object
 */
public class AddPersonToEventCommandParser implements Parser<AddPersonToEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddPersonToEventCommand
     * and returns an AddPersonToEventCommand object for execution.
     *
     * @param args The arguments to be parsed.
     * @return Command created with arguments parsed.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddPersonToEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_EVENT_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_EVENT_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddPersonToEventCommand.MESSAGE_USAGE));
        }

        Name personName = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        EventName eventName = ParserUtil.parseEventName(argMultimap.getValue(PREFIX_EVENT_NAME).get());

        return new AddPersonToEventCommand(new NameEqualKeywordPredicate(personName),
                new EventNameEqualKeywordPredicate(eventName));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     *
     * @param argumentMultimap Argument multimap involved in parsing.
     * @param prefixes The prefixes used.
     * @return true if prefixes are present.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
