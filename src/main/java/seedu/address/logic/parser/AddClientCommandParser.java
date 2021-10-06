package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddClientCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.commons.Name;

public class AddClientCommandParser implements Parser<AddClientCommand> {
    @Override
    public AddClientCommand parse(String args) throws ParseException {
        requireNonNull(args);
        Prefix startOfCommand = new Prefix("");
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, startOfCommand);

        if (!arePrefixesPresent(argMultimap, startOfCommand)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddClientCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(startOfCommand).get());

        AddClientCommand.AddClientDescriptor descriptor = new AddClientCommand.AddClientDescriptor(name);
        return new AddClientCommand(descriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
