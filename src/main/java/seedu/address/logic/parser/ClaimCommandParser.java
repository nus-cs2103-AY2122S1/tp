package seedu.address.logic.parser;

import com.sun.javafx.scene.input.ClipboardHelper;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.ClaimCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

public class ClaimCommandParser implements Parser<ClaimCommand> {

    @Override
    public ClaimCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_NAME, PREFIX_DESCRIPTION, PREFIX_STATUS);
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClaimCommand.MESSAGE_USAGE), e);
        }

        String name = argMultimap.getValue(PREFIX_NAME).orElse("");
        String description = argMultimap.getValue(PREFIX_DESCRIPTION).orElse("");
        String status = argMultimap.getValue(PREFIX_STATUS).orElse("");

        return new ClaimCommand(index, name, description, status);
    }
}
