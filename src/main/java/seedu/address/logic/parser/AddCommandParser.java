package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENUE;

import java.util.NoSuchElementException;
import java.util.Optional;

import seedu.address.logic.commands.GuiState;
import seedu.address.logic.commands.add.AddCommand;
import seedu.address.logic.commands.add.AddExamCommand;
import seedu.address.logic.commands.add.AddLessonCommand;
import seedu.address.logic.commands.add.AddModCommand;
import seedu.address.logic.parser.exceptions.GuiStateException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Day;
import seedu.address.model.module.Link;
import seedu.address.model.module.ModBookDate;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.Timeslot;
import seedu.address.model.module.Venue;
import seedu.address.model.module.exam.Exam;
import seedu.address.model.module.exam.ExamName;
import seedu.address.model.module.lesson.Lesson;
import seedu.address.model.module.lesson.LessonName;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args, GuiState guiState) throws ParseException {
        String errorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);
        Type type = ParserUtil.parseFirstArg(args, errorMessage);

        switch(type) {
        case MOD:
            return parseMod(args, guiState);
        case LESSON:
            return parseLesson(args, guiState);
        case EXAM:
            return parseExam(args, guiState);
        default:
            throw new ParseException(errorMessage);
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddModCommand
     * and returns an AddModCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddModCommand parseMod(String args, GuiState guiState) throws ParseException {
        if (guiState != GuiState.SUMMARY) {
            throw new GuiStateException(GuiState.SUMMARY);
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CODE, PREFIX_NAME);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_CODE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModCommand.MESSAGE_USAGE));
        }

        ModuleCode modCode = ParserUtil.parseModuleCode(argMultimap.getValue(PREFIX_CODE).get());
        Optional<ModuleName> modName;
        try {
            modName = Optional.of(ParserUtil.parseModuleName(argMultimap.getValue(PREFIX_NAME).get()));
        } catch (NoSuchElementException e) {
            modName = Optional.empty();
        }

        Module module = new Module(modCode, modName);
        return new AddModCommand(module);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddLessonCommand
     * and returns an AddLessonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddLessonCommand parseLesson(String args, GuiState guiState) throws ParseException {
        if (guiState != GuiState.DETAILS) {
            throw new GuiStateException(GuiState.DETAILS);
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DAY, PREFIX_START, PREFIX_END,
                        PREFIX_LINK, PREFIX_VENUE);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DAY, PREFIX_START, PREFIX_END)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLessonCommand.MESSAGE_USAGE));
        }

        LessonName lessonName = ParserUtil.parseLessonName(argMultimap.getValue(PREFIX_NAME).get());
        Day day = ParserUtil.parseDay(argMultimap.getValue(PREFIX_DAY).get());
        String startTime = argMultimap.getValue(PREFIX_START).get();
        String endTime = argMultimap.getValue(PREFIX_END).get();
        Timeslot timeslot = ParserUtil.parseTimeslot(startTime, endTime);
        Optional<Link> link;
        Optional<Venue> venue;

        try {
            link = Optional.of(ParserUtil.parseLink(argMultimap.getValue(PREFIX_LINK).get()));
        } catch (NoSuchElementException e) {
            link = Optional.empty();
        }

        try {
            venue = Optional.of(ParserUtil.parseVenue(argMultimap.getValue(PREFIX_VENUE).get()));
        } catch (NoSuchElementException e) {
            venue = Optional.empty();
        }

        Lesson lesson = new Lesson(lessonName, day, timeslot, venue, link);
        return new AddLessonCommand(lesson);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddExamCommand
     * and returns an AddExamCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddExamCommand parseExam(String args, GuiState guiState) throws ParseException {
        if (guiState != GuiState.DETAILS) {
            throw new GuiStateException(GuiState.DETAILS);
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DATE, PREFIX_START, PREFIX_END,
                        PREFIX_LINK, PREFIX_VENUE);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DATE, PREFIX_START, PREFIX_END)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddExamCommand.MESSAGE_USAGE));
        }

        ExamName examName = ParserUtil.parseExamName(argMultimap.getValue(PREFIX_NAME).get());
        ModBookDate date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        String startTime = argMultimap.getValue(PREFIX_START).get();
        String endTime = argMultimap.getValue(PREFIX_END).get();
        Timeslot timeslot = ParserUtil.parseTimeslot(startTime, endTime);
        Optional<Link> link;
        Optional<Venue> venue;

        try {
            link = Optional.of(ParserUtil.parseLink(argMultimap.getValue(PREFIX_LINK).get()));
        } catch (NoSuchElementException e) {
            link = Optional.empty();
        }

        try {
            venue = Optional.of(ParserUtil.parseVenue(argMultimap.getValue(PREFIX_VENUE).get()));
        } catch (NoSuchElementException e) {
            venue = Optional.empty();
        }

        Exam exam = new Exam(examName, date, timeslot, venue, link);
        return new AddExamCommand(exam);
    }
}
