package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FREQUENCY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEALTH_CONDITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LANGUAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAST_VISIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OCCURRENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VISIT;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.healthcondition.HealthCondition;
import seedu.address.model.person.Address;
import seedu.address.model.person.Frequency;
import seedu.address.model.person.Language;
import seedu.address.model.person.LastVisit;
import seedu.address.model.person.Name;
import seedu.address.model.person.Occurrence;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Visit;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_LANGUAGE, PREFIX_ADDRESS,
                        PREFIX_VISIT, PREFIX_LAST_VISIT, PREFIX_FREQUENCY, PREFIX_OCCURRENCE, PREFIX_HEALTH_CONDITION);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_LANGUAGE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        if (!areOptionalPrefixesValid(argMultimap)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Language language = ParserUtil.parseLanguage(argMultimap.getValue(PREFIX_LANGUAGE).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Optional<LastVisit> lastVisit = ParserUtil.parseLastVisit(argMultimap.getValue(PREFIX_LAST_VISIT)
                .orElse(""));
        Optional<Visit> visit = ParserUtil.parseVisitForAdd(argMultimap.getValue(PREFIX_VISIT).orElse(""));
        Optional<Frequency> frequency = ParserUtil.parseFrequency(argMultimap.getValue(PREFIX_FREQUENCY)
                .orElse(""));
        Optional<Occurrence> occurrence = ParserUtil.parseOccurrence(argMultimap.getValue(PREFIX_OCCURRENCE)
                .orElse("1"));
        Set<HealthCondition> healthConditionList = ParserUtil
                .parseHealthConditions(argMultimap.getAllValues(PREFIX_HEALTH_CONDITION));

        Person person = new Person(name, phone, language, address, lastVisit, visit, frequency,
                occurrence, healthConditionList);

        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private static boolean areOptionalPrefixesValid(ArgumentMultimap argumentMultimap) {
        boolean isEitherTrue = arePrefixesPresent(argumentMultimap, PREFIX_FREQUENCY)
                || arePrefixesPresent(argumentMultimap, PREFIX_OCCURRENCE);

        if (arePrefixesPresent(argumentMultimap, PREFIX_FREQUENCY, PREFIX_OCCURRENCE)) {
            return true;
        } else {
            return !isEitherTrue;
        }
    }

}
