package seedu.plannermd.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.plannermd.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.plannermd.logic.commands.tagcommand.AddPatientTagCommand.MESSAGE_NOT_ADDED;
import static seedu.plannermd.logic.parser.CliSyntax.FLAG_DELETE;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.commons.exceptions.IllegalValueException;
import seedu.plannermd.logic.commands.Command;
import seedu.plannermd.logic.commands.tagcommand.AddPatientTagCommand;
import seedu.plannermd.logic.commands.tagcommand.DeletePatientTagCommand;
import seedu.plannermd.logic.parser.exceptions.ParseException;
import seedu.plannermd.model.tag.Tag;

public class TagCommandParser implements Parser {

    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param userInput user's input string
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public Command parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_ID, PREFIX_TAG);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_ID).orElse(""));
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddPatientTagCommand.MESSAGE_USAGE + "\n" + DeletePatientTagCommand.MESSAGE_USAGE), ive);
        }

        if (argMultimap.getAllValues(PREFIX_TAG).size() > 1) {
            throw new ParseException("Please enter only one tag.");
        }

        String tagString = argMultimap.getValue(PREFIX_TAG).orElse("");
        if (tagString.equals("")) {
            throw new ParseException(MESSAGE_NOT_ADDED);
        }
        Tag tag = ParserUtil.parseTag(tagString);

        String preamble = argMultimap.getPreamble();
        if (preamble.equals("")) {
            return new AddPatientTagCommand(index, tag);
        } else if (preamble.equals(FLAG_DELETE.toString())) {
            return new DeletePatientTagCommand(index, tag);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddPatientTagCommand.MESSAGE_USAGE + "\n" + DeletePatientTagCommand.MESSAGE_USAGE));
        }
    }
}
