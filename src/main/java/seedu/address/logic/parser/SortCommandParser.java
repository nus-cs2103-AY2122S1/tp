package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_TOO_MANY_FIELDS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENTID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CURRENTPLAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DISPOSABLEINCOME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LASTMET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RISKAPPETITE;
import static seedu.address.logic.parser.ParserUtil.parseSortDirection;

import java.util.Comparator;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.model.person.comparators.SortByAddress;
import seedu.address.model.person.comparators.SortByClientID;
import seedu.address.model.person.comparators.SortByCurrentPlan;
import seedu.address.model.person.comparators.SortByDisposableIncome;
import seedu.address.model.person.comparators.SortByEmail;
import seedu.address.model.person.comparators.SortByLastMet;
import seedu.address.model.person.comparators.SortByName;
import seedu.address.model.person.comparators.SortByPhone;
import seedu.address.model.person.comparators.SortByRiskAppetite;
import seedu.address.model.person.comparators.SortDirection;

public class SortCommandParser implements Parser<SortCommand> {

    private static final Prefix[] ALL_PREFIXES = {
        PREFIX_CLIENTID, PREFIX_NAME, PREFIX_EMAIL, PREFIX_PHONE, PREFIX_ADDRESS, PREFIX_RISKAPPETITE,
        PREFIX_DISPOSABLEINCOME, PREFIX_CURRENTPLAN, PREFIX_LASTMET
    };

    private String sortedField;

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CLIENTID, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                    PREFIX_ADDRESS, PREFIX_RISKAPPETITE, PREFIX_DISPOSABLEINCOME, PREFIX_CURRENTPLAN, PREFIX_LASTMET);
        if (!anyPrefixesPresent(argMultimap, PREFIX_CLIENTID, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_RISKAPPETITE, PREFIX_DISPOSABLEINCOME, PREFIX_CURRENTPLAN, PREFIX_LASTMET)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        Comparator<Person> sorter = parseSorter(argMultimap);

        return new SortCommand(sorter, sortedField);
    }

    /**
     * Returns true if any of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean anyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Constructs a {@code Comparator} from based off inputted {@code Prefix} with given {@code SortDirection}
     */
    private Comparator<Person> createSorter(Prefix prefix, SortDirection sortDirection) throws ParseException {
        if (PREFIX_CLIENTID.equals(prefix)) {
            sortedField = "Client Id";
            return new SortByClientID(sortDirection);
        } else if (PREFIX_NAME.equals(prefix)) {
            sortedField = "Name";
            return new SortByName(sortDirection);
        } else if (PREFIX_EMAIL.equals(prefix)) {
            sortedField = "Email";
            return new SortByEmail(sortDirection);
        } else if (PREFIX_PHONE.equals(prefix)) {
            sortedField = "Phone";
            return new SortByPhone(sortDirection);
        } else if (PREFIX_ADDRESS.equals(prefix)) {
            sortedField = "Address";
            return new SortByAddress(sortDirection);
        } else if (PREFIX_RISKAPPETITE.equals(prefix)) {
            sortedField = "Risk Appetite";
            return new SortByRiskAppetite(sortDirection);
        } else if (PREFIX_DISPOSABLEINCOME.equals(prefix)) {
            sortedField = "Disposable Income";
            return new SortByDisposableIncome(sortDirection);
        } else if (PREFIX_CURRENTPLAN.equals(prefix)) {
            sortedField = "Current Plan";
            return new SortByCurrentPlan(sortDirection);
        } else if (PREFIX_LASTMET.equals(prefix)) {
            sortedField = "Last Met";
            return new SortByLastMet(sortDirection);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

    }

    /**
     * Constructs a {@code Comparator} from parsed prefixes in {@code ArgumentMultiMap }based off inputted prefix
     * with appriopriate {@code SortDirection}
     */
    private Comparator<Person> parseSorter(ArgumentMultimap argumentMultimap) throws ParseException {
        Comparator<Person> sorter = null;
        for (int i = 0; i < ALL_PREFIXES.length; i++) {
            Prefix currentPrefix = ALL_PREFIXES[i];
            if (!argumentMultimap.getValue(currentPrefix).isEmpty() && sorter == null) {
                SortDirection sortDirection = parseSortDirection(argumentMultimap.getValue(currentPrefix).get());
                sorter = createSorter(currentPrefix, sortDirection);
            } else if (!argumentMultimap.getValue(currentPrefix).isEmpty() && !(sorter == null)) {
                //too many fields
                throw new ParseException(String.format(MESSAGE_TOO_MANY_FIELDS, SortCommand.MESSAGE_USAGE));
            }
        }
        return sorter;
    }

}
