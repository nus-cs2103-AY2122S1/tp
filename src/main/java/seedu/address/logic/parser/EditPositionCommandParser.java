package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.position.EditPositionCommand;

public class EditPositionCommandParser implements Parser<EditPositionCommand> {

    @Override
    public EditPositionCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_POSITION_STATUS);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, pe.getMessage())
                    + EditPositionCommand.MESSAGE_USAGE, pe);
        }

        EditPositionCommand.EditPositionDescriptor editPositionDescriptor =
                new EditPositionCommand.EditPositionDescriptor();

        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            editPositionDescriptor.setTitle(ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get()));
        }
        if (argMultimap.getValue(PREFIX_POSITION_STATUS).isPresent()) {
            editPositionDescriptor.setPositionStatus(ParserUtil
                    .parsePositionStatus(argMultimap.getValue(PREFIX_POSITION_STATUS).get()));
        }

        if (!editPositionDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditPositionCommand.MESSAGE_NOT_EDITED);
        }

        return new EditPositionCommand(index, editPositionDescriptor);
    }
}
