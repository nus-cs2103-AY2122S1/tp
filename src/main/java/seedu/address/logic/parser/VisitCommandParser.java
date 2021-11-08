package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FREQUENCY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OCCURRENCE;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.VisitCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Frequency;
import seedu.address.model.person.Occurrence;
import seedu.address.model.person.Visit;

/**
 * Parses input arguments and creates a new {@code VisitCommand} object
 */
public class VisitCommandParser implements Parser<VisitCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code VisitCommand}
     * and returns a {@code VisitCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public VisitCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DATE,
                PREFIX_FREQUENCY, PREFIX_OCCURRENCE);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, VisitCommand.MESSAGE_USAGE), ive);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_DATE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, VisitCommand.MESSAGE_USAGE));
        }

        if (!areOptionalPrefixesValid(argMultimap)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, VisitCommand.MESSAGE_USAGE));
        }

        String visit = argMultimap.getValue(PREFIX_DATE).orElse("");
        Optional<Visit> convertedVisit = ParserUtil.parseVisit(visit);

        String frequency = argMultimap.getValue(PREFIX_FREQUENCY).orElse("");
        Optional<Frequency> convertedFrequency = ParserUtil.parseFrequency(frequency);

        String occurrence = argMultimap.getValue(PREFIX_OCCURRENCE).orElse("1");
        Optional<Occurrence> convertedOccurrence = ParserUtil.parseOccurrence(occurrence);

        return new VisitCommand(index, convertedVisit, convertedFrequency, convertedOccurrence);
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
