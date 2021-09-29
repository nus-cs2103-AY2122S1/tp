package seedu.plannermd.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.plannermd.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.plannermd.logic.commands.TagCommand.MESSAGE_NOT_ADDED;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.commons.exceptions.IllegalValueException;
import seedu.plannermd.logic.commands.Command;
import seedu.plannermd.logic.commands.TagCommand;
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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_TAG);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    TagCommand.MESSAGE_USAGE), ive);
        }

        String tagString = argMultimap.getValue(PREFIX_TAG).orElse("");
        if (tagString.equals("")) {
            throw new ParseException(MESSAGE_NOT_ADDED);
        }

        Tag tag = ParserUtil.parseTag(tagString);
        return new TagCommand(index, tag);
    }
}
