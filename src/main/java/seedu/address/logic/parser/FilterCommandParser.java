package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUALIFICATION;

import java.util.Arrays;
import java.util.function.Predicate;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ChainedPredicate;
import seedu.address.model.person.Gender;
import seedu.address.model.person.GenderContainsGenderPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Qualification;
import seedu.address.model.person.QualificationContainsQualificationPredicate;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    @Override
    public FilterCommand parse(String args) throws ParseException {
        requireNonNull(args);

        return new FilterCommand(generatePredicate(args));
    }

    /**
     * Generates the predicate to be used in FilterCommand.
     *
     * @return The Predicate used to filter the match list
     */
    private Predicate<Person> generatePredicate(String args) throws ParseException {
        Predicate<Person> predicate = x -> true;
        ChainedPredicate.Builder builder = new ChainedPredicate.Builder();
        int prefixCount = 0;

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_QUALIFICATION,
                PREFIX_GENDER);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            prefixCount += 1;
            predicate = handleName(predicate, builder, argMultimap);
        }
        if (argMultimap.getValue(PREFIX_GENDER).isPresent()) {
            prefixCount += 1;
            predicate = handleGender(predicate, builder, argMultimap);
        }
        if (argMultimap.getValue(PREFIX_QUALIFICATION).isPresent()) {
            prefixCount += 1;
            predicate = handleQualification(predicate, builder, argMultimap);
        }
        if (prefixCount == 0) { // Checks if user entered any parameter
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        return builder.setPredicate(predicate).build();
    }

    private Predicate<Person> handleQualification(Predicate<Person> predicate, ChainedPredicate.Builder builder,
                                                  ArgumentMultimap argMultimap) throws ParseException {
        Qualification qualification =
                ParserUtil.parseQualification(argMultimap.getValue(PREFIX_QUALIFICATION).get());
        Qualification[] qualificationList = new Qualification[] {qualification};
        Predicate<Person> updatedPredicate = predicate
                .and(new QualificationContainsQualificationPredicate(Arrays.asList(qualificationList)));
        builder.setQualification(qualification);
        return updatedPredicate;
    }

    private Predicate<Person> handleGender(Predicate<Person> predicate, ChainedPredicate.Builder builder,
                                           ArgumentMultimap argMultimap) throws ParseException {
        Gender gender = ParserUtil.parseGender(argMultimap.getValue(PREFIX_GENDER).get());
        Gender[] genderList = new Gender[] {gender};
        Predicate<Person> updatedPredicate = predicate
                .and(new GenderContainsGenderPredicate(Arrays.asList(genderList)));
        builder.setGender(gender);
        return updatedPredicate;
    }

    private Predicate<Person> handleName(Predicate<Person> predicate, ChainedPredicate.Builder builder,
                                         ArgumentMultimap argMultimap) throws ParseException {
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        String[] nameList = new String[] {name.toString()};
        Predicate<Person> updatedPredicate = predicate.and(new NameContainsKeywordsPredicate(Arrays.asList(nameList)));
        builder.setName(name);
        return updatedPredicate;
    }
}
