package seedu.address.logic.parser.modulelesson;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;

import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.modulelesson.AddModuleLessonCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.modulelesson.LessonDay;
import seedu.address.model.modulelesson.LessonTime;
import seedu.address.model.modulelesson.ModuleLesson;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Remark;

/**
 * Parses input arguments and creates a new AddModuleLessonCommand object.
 */
public class AddModuleLessonCommandParser implements Parser<AddModuleLessonCommand> {
    /**
     * Parses the given {@code String args} of arguments in the context of the {@code AddModuleLessonCommand}
     * and returns an {@code AddModuleLessonCommand} object for execution.
     *
     * @throws ParseException if {@code String args} does not conform the expected format
     */
    @Override
    public AddModuleLessonCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE_CODE, PREFIX_LESSON_DAY,
                        PREFIX_LESSON_TIME, PREFIX_REMARK);

        if (!arePrefixesPresent(argMultimap, PREFIX_MODULE_CODE, PREFIX_LESSON_DAY, PREFIX_LESSON_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddModuleLessonCommand.MESSAGE_USAGE));
        }

        ModuleCode moduleCode = ParserUtil
                .parseModuleCodeForModuleLesson(argMultimap.getValue(PREFIX_MODULE_CODE).get());
        LessonDay lessonDay = ParserUtil.parseLessonDay(argMultimap.getValue(PREFIX_LESSON_DAY).get());
        List<LessonTime> lessonTime = ParserUtil.parseLessonTime(argMultimap.getValue(PREFIX_LESSON_TIME).get());
        LessonTime lessonStartTime = lessonTime.get(0);
        LessonTime lessonEndTime = lessonTime.get(1);
        Remark remark = ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).orElse(""));

        ModuleLesson moduleLesson = new ModuleLesson(moduleCode, lessonDay, lessonStartTime, lessonEndTime, remark);
        return new AddModuleLessonCommand(moduleLesson);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
