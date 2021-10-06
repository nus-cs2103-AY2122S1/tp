package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddProductCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.commons.Name;

public class AddProductCommandParser implements Parser<AddProductCommand> {
    @Override
    public AddProductCommand parse(String args) throws ParseException {
        requireNonNull(args);
        Prefix startOfCommand = new Prefix("");
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, startOfCommand);

        if (!arePrefixesPresent(argMultimap, startOfCommand)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProductCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(startOfCommand).get());

        AddProductCommand.AddProductDescriptor descriptor = new AddProductCommand.AddProductDescriptor(name);
        return new AddProductCommand(descriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
