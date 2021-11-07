package seedu.address.logic.parser.modulelesson;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.modulelesson.FindModuleLessonCommand.MESSAGE_INVALID_DAY;
import static seedu.address.logic.commands.modulelesson.FindModuleLessonCommand.MESSAGE_INVALID_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.model.modulelesson.LessonTime.isValidTime;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import seedu.address.logic.commands.modulelesson.FindModuleLessonCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.modulelesson.LessonDayContainsKeywordsPredicate;
import seedu.address.model.modulelesson.LessonTimeContainsKeywordsPredicate;
import seedu.address.model.modulelesson.ModuleCodeContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindModuleLessonCommand object.
 */
public class FindModuleLessonCommandParser implements Parser<FindModuleLessonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindModuleLessonCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer
                .tokenize(args, PREFIX_MODULE_CODE, PREFIX_LESSON_DAY, PREFIX_LESSON_TIME);
        boolean isModulePrefixPresent = argMultimap.getValue(PREFIX_MODULE_CODE).isPresent();
        boolean isDayPrefixPresent = argMultimap.getValue(PREFIX_LESSON_DAY).isPresent();
        boolean isTimePrefixPresent = argMultimap.getValue(PREFIX_LESSON_TIME).isPresent();

        long numberOfValidPrefixes = countValidPrefixes(isModulePrefixPresent, isDayPrefixPresent, isTimePrefixPresent);

        if (numberOfValidPrefixes == 0) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindModuleLessonCommand.MESSAGE_USAGE)
            );
        }

        if (numberOfValidPrefixes > 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindModuleLessonCommand.MESSAGE_SINGLE_PREFIX_SEARCH)
            );
        }

        assert(numberOfValidPrefixes == 1) : "There should only be one prefix used for the command!";

        if (isModulePrefixPresent) {
            return getFindModuleCommand(argMultimap);
        } else if (isDayPrefixPresent) {
            try {
                int dayValue = Integer.parseInt(argMultimap.getValue(PREFIX_LESSON_DAY).get());
                if (dayValue < 0 || dayValue > 7) {
                    throw new ParseException(
                            String.format(MESSAGE_INVALID_DAY, FindModuleLessonCommand.MESSAGE_SINGLE_PREFIX_SEARCH)
                    );
                }
            } catch (NumberFormatException e) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_DAY, FindModuleLessonCommand.MESSAGE_USAGE)
                );
            }

            return getFindDayCommand(argMultimap);
        } else if (isTimePrefixPresent) {
            if (!isValidTime(argMultimap.getValue(PREFIX_LESSON_TIME).get())) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_TIME, FindModuleLessonCommand.MESSAGE_USAGE)
                );
            }
            return getFindTimeCommand(argMultimap);
        }
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindModuleLessonCommand.MESSAGE_USAGE));
    }

    private long countValidPrefixes (Boolean... prefixPresent) {
        long count = Arrays.stream(prefixPresent).filter(x -> x).count();
        return count;
    }

    private FindModuleLessonCommand getFindModuleCommand(ArgumentMultimap argMultimap) throws ParseException {
        Optional<String> searchInput = argMultimap.getValue(PREFIX_MODULE_CODE);
        String modules = searchInput.get().trim();
        if (modules.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindModuleLessonCommand.MESSAGE_USAGE));
        }

        List<String> moduleKeywordsList = Arrays.stream(modules.split("\\s+"))
                .collect(Collectors.toList());

        return new FindModuleLessonCommand(new ModuleCodeContainsKeywordsPredicate(moduleKeywordsList));
    }

    private FindModuleLessonCommand getFindDayCommand(ArgumentMultimap argMultimap) throws ParseException {
        Optional<String> searchInput = argMultimap.getValue(PREFIX_LESSON_DAY);
        String days = searchInput.get().trim();
        if (days.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindModuleLessonCommand.MESSAGE_USAGE));
        }

        List<String> lessonDayKeywordsList = Arrays.stream(days.split("\\s+"))
                .collect(Collectors.toList());

        return new FindModuleLessonCommand(new LessonDayContainsKeywordsPredicate(lessonDayKeywordsList));
    }

    private FindModuleLessonCommand getFindTimeCommand(ArgumentMultimap argMultimap) throws ParseException {
        Optional<String> searchInput = argMultimap.getValue(PREFIX_LESSON_TIME);
        String times = searchInput.get().trim();
        if (times.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindModuleLessonCommand.MESSAGE_USAGE));
        }

        List<String> lessonTimeKeywordsList = Arrays.stream(times.split("\\s+"))
                .collect(Collectors.toList());

        return new FindModuleLessonCommand(new LessonTimeContainsKeywordsPredicate(lessonTimeKeywordsList));
    }
}
