package seedu.edrecord.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.edrecord.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.edrecord.logic.commands.EditAssignmentCommand.MESSAGE_USAGE;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_SCORE;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_WEIGHTAGE;

import seedu.edrecord.commons.core.index.Index;
import seedu.edrecord.logic.commands.EditAssignmentCommand;
import seedu.edrecord.logic.commands.EditAssignmentCommand.EditAssignmentDescriptor;
import seedu.edrecord.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditAssignmentCommand object
 */
public class EditAssignmentCommandParser implements Parser<EditAssignmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditAssignmentCommand
     * and returns an EditAssignmentCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public EditAssignmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_WEIGHTAGE, PREFIX_SCORE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE), pe);
        }

        EditAssignmentDescriptor descriptor = new EditAssignmentDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            descriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_WEIGHTAGE).isPresent()) {
            descriptor.setWeightage(ParserUtil.parseWeightage(argMultimap.getValue(PREFIX_WEIGHTAGE).get()));
        }
        if (argMultimap.getValue(PREFIX_SCORE).isPresent()) {
            descriptor.setMaxScore(ParserUtil.parseScore(argMultimap.getValue(PREFIX_SCORE).get()));
        }

        if (!descriptor.isAnyFieldEdited()) {
            throw new ParseException(EditAssignmentCommand.MESSAGE_NOT_EDITED);
        }

        return new EditAssignmentCommand(index, descriptor);
    }

}

