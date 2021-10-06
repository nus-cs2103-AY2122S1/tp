package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.AddProductCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AddProductCommandParser implements Parser<AddProductCommand> {
    @Override
    public AddProductCommand parse(String args) throws ParseException {
        requireNonNull(args);
        return null;
    }
}
