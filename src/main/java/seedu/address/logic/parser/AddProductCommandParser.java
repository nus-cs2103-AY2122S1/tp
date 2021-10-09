package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;
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

        Name name = ParserUtil.parseName(listToString(argMultimap.getAllValues(startOfCommand)));

        AddProductCommand.AddProductDescriptor descriptor = new AddProductCommand.AddProductDescriptor(name);
        return new AddProductCommand(descriptor);
    }

    /**
     * Converts a list of string into a string by inserting white spaces in between.
     *
     * @param list A list of string.
     * @return Combined string.
     */
    private static String listToString(List<String> list) {
        int len = list.size();
        String result = "";
        for (int i = 0; i < len; i++) {
            if (i == len - 1) {
                result += list.get(i);
            } else {
                result += String.format("%s ", list.get(i));
            }
        }
        return result;
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
