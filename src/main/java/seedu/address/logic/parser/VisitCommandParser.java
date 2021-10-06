package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.VisitCommand;
import seedu.address.logic.parser.exceptions.ParseException;
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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DATE);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, VisitCommand.MESSAGE_USAGE), ive);
        }

        String visit = argMultimap.getValue(PREFIX_DATE).orElse("");
        Visit convertedVisit = ParserUtil.parseVisit(visit);

        return new VisitCommand(index, convertedVisit);
    }
}
