package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERACTION;

import java.util.Optional;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.InteractionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.interaction.Interaction;

/**
 * Parses input arguments and creates a new InteractionCommand object
 */
public class InteractionCommandParser implements Parser<InteractionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the InteractionCommand
     * and returns an InteractionCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public InteractionCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_INTERACTION, PREFIX_DATE);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, InteractionCommand.MESSAGE_USAGE), pe);
        }
        if (!arePrefixesPresent(argMultimap, PREFIX_INTERACTION, PREFIX_DATE)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, InteractionCommand.MESSAGE_USAGE));
        }

        Optional<String> description = argMultimap.getValue(PREFIX_INTERACTION);
        Optional<String> date = argMultimap.getValue(PREFIX_DATE);
        if (!Interaction.isValidDate(date.get())) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, Interaction.MESSAGE_CONSTRAINTS));
        }

        Interaction interaction = new Interaction(description.get(), date.get());
        return new InteractionCommand(index, interaction);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
