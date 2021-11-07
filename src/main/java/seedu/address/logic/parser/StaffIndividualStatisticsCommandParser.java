package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import java.time.LocalDate;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.StaffIndividualStatisticsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Period;
import seedu.address.model.person.predicates.PersonContainsFieldsPredicate;

public class StaffIndividualStatisticsCommandParser implements Parser<StaffIndividualStatisticsCommand> {

    private static final ParseException NO_FIELD_EXCEPTION =
            new ParseException(StaffIndividualStatisticsCommand.MESSAGE_USAGE);

    @Override
    public StaffIndividualStatisticsCommand parse(String userInput) throws ParseException {
        //created to test if there are any identifiers
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_DASH_NAME, PREFIX_DASH_PHONE,
                        PREFIX_DASH_INDEX, PREFIX_DATE, PREFIX_DASH_EMAIL, PREFIX_DASH_TAG,
                        PREFIX_DASH_STATUS, PREFIX_DASH_ROLE, PREFIX_DASH_SALARY);

        Period period = Period.getPeriodFromDateOverMonth(LocalDate.now());
        PersonContainsFieldsPredicate predicate = ParserUtil.testByAllFields(argMultimap);
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            LocalDate[] dates = ParserUtil.extractTupleDates(argMultimap);
            period = new Period(dates[0], dates[1]);
        }
        //checks for index
        if (argMultimap.getValue(PREFIX_DASH_INDEX).isPresent()) {
            Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_DASH_INDEX).get());
            return new StaffIndividualStatisticsCommand(predicate, index, period);
        }
        //checks for empty
        if (predicate.isEmpty()) {
            throw NO_FIELD_EXCEPTION;
        }


        return new StaffIndividualStatisticsCommand(predicate, period);
    }
}
