package tutoraid.logic.parser;

import static java.util.Objects.requireNonNull;
import static tutoraid.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tutoraid.logic.commands.EditLessonCommand.EditLessonDescriptor;
import static tutoraid.logic.parser.CliSyntax.PREFIX_LESSON_CAPACITY;
import static tutoraid.logic.parser.CliSyntax.PREFIX_LESSON_NAME;
import static tutoraid.logic.parser.CliSyntax.PREFIX_LESSON_PRICE;
import static tutoraid.logic.parser.CliSyntax.PREFIX_LESSON_TIMING;

import tutoraid.commons.core.index.Index;
import tutoraid.logic.commands.EditLessonCommand;
import tutoraid.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditLessonCommand object
 */
public class EditLessonCommandParser implements Parser<EditLessonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditLessonCommand
     * and returns an EditLessonCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditLessonCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args, PREFIX_LESSON_NAME, PREFIX_LESSON_PRICE, PREFIX_LESSON_TIMING, PREFIX_LESSON_CAPACITY);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException | ArrayIndexOutOfBoundsException e) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, EditLessonCommand.MESSAGE_USAGE), e);
        }

        EditLessonDescriptor editLessonDescriptor = new EditLessonDescriptor();
        if (argMultimap.getValue(PREFIX_LESSON_NAME).isPresent()) {
            editLessonDescriptor.setLessonName(
                    ParserUtil.parseLessonName(argMultimap.getValue(PREFIX_LESSON_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_LESSON_PRICE).isPresent()) {
            editLessonDescriptor.setPrice(
                    ParserUtil.parsePrice(argMultimap.getValue(PREFIX_LESSON_PRICE).get()));
        }
        if (argMultimap.getValue(PREFIX_LESSON_TIMING).isPresent()) {
            editLessonDescriptor.setTiming(
                    ParserUtil.parseTiming(argMultimap.getValue(PREFIX_LESSON_TIMING).get()));
        }
        if (argMultimap.getValue(PREFIX_LESSON_CAPACITY).isPresent()) {
            editLessonDescriptor.setCapacity(
                    ParserUtil.parseCapacity(argMultimap.getValue(PREFIX_LESSON_CAPACITY).get()));
        }
        if (!editLessonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditLessonCommand.MESSAGE_NOT_EDITED);
        }

        return new EditLessonCommand(index, editLessonDescriptor);
    }
}
