package seedu.academydirectory.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.academydirectory.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.stream.Stream;

import seedu.academydirectory.logic.commands.RetrieveCommand;
import seedu.academydirectory.logic.parser.exceptions.ParseException;
import seedu.academydirectory.model.student.InformationWantedFunction;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class RetrieveCommandParser implements Parser<RetrieveCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RetrieveCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RetrieveCommand.MESSAGE_USAGE));
        }
        String[] keywords = trimmedArgs.split("\\s+");
        String info = keywords[0];
        Prefix infoPrefix = new Prefix(info);

        Stream<Prefix> prefixes = Stream.of(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TELEGRAM);
        boolean isAnyPrefixMatch = prefixes.anyMatch(x -> x.equals(infoPrefix));
        if (!isAnyPrefixMatch) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RetrieveCommand.MESSAGE_USAGE));
        }

        return new RetrieveCommand(new InformationWantedFunction(infoPrefix));
    }

}
