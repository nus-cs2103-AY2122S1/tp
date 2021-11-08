package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENUE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.GuiState;
import seedu.address.logic.commands.edit.EditCommand;
import seedu.address.logic.commands.edit.EditExamCommand;
import seedu.address.logic.commands.edit.EditExamCommand.EditExamDescriptor;
import seedu.address.logic.commands.edit.EditLessonCommand;
import seedu.address.logic.commands.edit.EditLessonCommand.EditLessonDescriptor;
import seedu.address.logic.commands.edit.EditModCommand;
import seedu.address.logic.commands.edit.EditModCommand.EditModDescriptor;
import seedu.address.logic.parser.exceptions.GuiStateException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {
    public static final String MESSAGE_INVALID_TIMESLOT =
            "Both start and end timings have to be present to modify the timeslot.";

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args, GuiState guiState) throws ParseException {
        requireAllNonNull(args, guiState);
        Type type = ParserUtil.parseFirstArg(args, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditCommand.MESSAGE_USAGE));
        switch (type) {
        case MOD:
            return parseEditMod(args, guiState);
        case LESSON:
            return parseEditLesson(args, guiState);
        case EXAM:
            return parseEditExam(args, guiState);
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of the EditModCommand
     * and returns an EditModCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditModCommand parseEditMod(String args, GuiState guiState) throws ParseException {
        if (guiState != GuiState.SUMMARY) {
            throw new GuiStateException(GuiState.SUMMARY);
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CODE, PREFIX_NAME);
        Index index = ParserUtil.parseLastIndex(argMultimap.getPreamble());

        if (!(ParserUtil.arePrefixesPresent(argMultimap, PREFIX_CODE)
                || ParserUtil.arePrefixesPresent(argMultimap, PREFIX_NAME))) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditModCommand.MESSAGE_USAGE));
        }
        EditModDescriptor editModDescriptor = new EditModDescriptor();

        if (argMultimap.getValue(PREFIX_CODE).isPresent()) {
            editModDescriptor.setModuleCode(ParserUtil.parseModuleCode(argMultimap.getValue(PREFIX_CODE).get()));
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editModDescriptor.setModuleName(ParserUtil.parseModuleName(argMultimap.getValue(PREFIX_NAME).get()));
        }

        if (!editModDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditModCommand.MESSAGE_NOT_EDITED);
        }

        return new EditModCommand(index, editModDescriptor);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the EditLessonCommand
     * and returns an EditLessonCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditLessonCommand parseEditLesson(String args, GuiState guiState) throws ParseException {
        if (guiState != GuiState.DETAILS) {
            throw new GuiStateException(GuiState.DETAILS);
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DAY,
                PREFIX_START, PREFIX_END, PREFIX_LINK, PREFIX_VENUE);
        Index index = ParserUtil.parseLastIndex(argMultimap.getPreamble());

        EditLessonDescriptor editLessonDescriptor = new EditLessonDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editLessonDescriptor.setName(ParserUtil.parseLessonName(argMultimap.getValue(PREFIX_NAME).get()));
        }

        if (argMultimap.getValue(PREFIX_DAY).isPresent()) {
            editLessonDescriptor.setDay(ParserUtil.parseDay(argMultimap.getValue(PREFIX_DAY).get()));
        }

        // Prevent users from inputting only either the start or the end timings
        if (argMultimap.getValue(PREFIX_START).isPresent() ^ argMultimap.getValue(PREFIX_END).isPresent()) {
            throw new ParseException(MESSAGE_INVALID_TIMESLOT);
        }

        if (argMultimap.getValue(PREFIX_START).isPresent() && argMultimap.getValue(PREFIX_END).isPresent()) {
            editLessonDescriptor.setTimeslot(ParserUtil.parseTimeslot(argMultimap.getValue(PREFIX_START).get(),
                    argMultimap.getValue(PREFIX_END).get()));
        }

        if (argMultimap.getValue(PREFIX_LINK).isPresent()) {
            editLessonDescriptor.setLink(ParserUtil.parseLink(argMultimap.getValue(PREFIX_LINK).get()));
        }

        if (argMultimap.getValue(PREFIX_VENUE).isPresent()) {
            editLessonDescriptor.setVenue(ParserUtil.parseVenue(argMultimap.getValue(PREFIX_VENUE).get()));
        }

        if (!editLessonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditLessonCommand.MESSAGE_NOT_EDITED);
        }

        return new EditLessonCommand(index, editLessonDescriptor);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the EditExamCommand
     * and returns an EditExamCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditExamCommand parseEditExam(String args, GuiState guiState) throws ParseException {
        if (guiState != GuiState.DETAILS) {
            throw new GuiStateException(GuiState.DETAILS);
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DATE,
                PREFIX_START, PREFIX_END, PREFIX_LINK, PREFIX_VENUE);
        Index index = ParserUtil.parseLastIndex(argMultimap.getPreamble());

        EditExamDescriptor editExamDescriptor = new EditExamDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editExamDescriptor.setName(ParserUtil.parseExamName(argMultimap.getValue(PREFIX_NAME).get()));
        }

        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editExamDescriptor.setDate(ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get()));
        }

        // Prevent users from inputting only either the start or the end timings
        if (argMultimap.getValue(PREFIX_START).isPresent() ^ argMultimap.getValue(PREFIX_END).isPresent()) {
            throw new ParseException(MESSAGE_INVALID_TIMESLOT);
        }

        if (argMultimap.getValue(PREFIX_START).isPresent() && argMultimap.getValue(PREFIX_END).isPresent()) {
            editExamDescriptor.setTimeslot(ParserUtil.parseTimeslot(argMultimap.getValue(PREFIX_START).get(),
                    argMultimap.getValue(PREFIX_END).get()));
        }

        if (argMultimap.getValue(PREFIX_LINK).isPresent()) {
            editExamDescriptor.setLink(ParserUtil.parseLink(argMultimap.getValue(PREFIX_LINK).get()));
        }

        if (argMultimap.getValue(PREFIX_VENUE).isPresent()) {
            editExamDescriptor.setVenue(ParserUtil.parseVenue(argMultimap.getValue(PREFIX_VENUE).get()));
        }

        if (!editExamDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditExamCommand.MESSAGE_NOT_EDITED);
        }

        return new EditExamCommand(index, editExamDescriptor);
    }
}
