package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CAPACITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditFacilityCommand;
import seedu.address.logic.commands.EditFacilityCommand.EditFacilityDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

public class EditFacilityCommandParser implements Parser<EditFacilityCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditFacilityCommand
     * and returns an EditFacilityCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public EditFacilityCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultiMap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_LOCATION, PREFIX_TIME, PREFIX_CAPACITY);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultiMap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditFacilityCommand.MESSAGE_USAGE), pe);
        }

        EditFacilityDescriptor editFacilityDescriptor = generateDescriptor(argMultiMap);

        if (!editFacilityDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditFacilityCommand.MESSAGE_NOT_EDITED);
        }

        return new EditFacilityCommand(index, editFacilityDescriptor);
    }

    /**
     * Parses input arguments and constructs the relevant {@code EditFacilityDescriptor}.
     *
     * @param argMultiMap the input arguments to parse.
     * @return EditFacilityDescriptor consisting of the fields to edit.
     * @throws ParseException if any of the input arguments are invalid.
     */
    private EditFacilityDescriptor generateDescriptor(ArgumentMultimap argMultiMap) throws ParseException {
        EditFacilityDescriptor editFacilityDescriptor = new EditFacilityDescriptor();

        if (argMultiMap.getValue(PREFIX_NAME).isPresent()) {
            editFacilityDescriptor.setFacilityName(
                    ParserUtil.parseFacilityName(argMultiMap.getValue(PREFIX_NAME).get()));
        }
        if (argMultiMap.getValue(PREFIX_LOCATION).isPresent()) {
            editFacilityDescriptor.setLocation(
                    ParserUtil.parseLocation(argMultiMap.getValue(PREFIX_LOCATION).get()));
        }
        if (argMultiMap.getValue(PREFIX_TIME).isPresent()) {
            editFacilityDescriptor.setTime(
                    ParserUtil.parseTime(argMultiMap.getValue(PREFIX_TIME).get()));
        }
        if (argMultiMap.getValue(PREFIX_CAPACITY).isPresent()) {
            editFacilityDescriptor.setCapacity(
                    ParserUtil.parseCapacity(argMultiMap.getValue(PREFIX_CAPACITY).get()));
        }

        return editFacilityDescriptor;
    }
}
