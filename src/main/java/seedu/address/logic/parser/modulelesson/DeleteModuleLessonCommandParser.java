package seedu.address.logic.parser.modulelesson;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.modulelesson.DeleteModuleLessonCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.modulelesson.ModuleCodeContainsKeywordsPredicate;
import seedu.address.model.person.ModuleCode;

/**
 * Parses input arguments and creates a new DeleteModuleLessonCommand object.
 */
public class DeleteModuleLessonCommandParser implements Parser<DeleteModuleLessonCommand> {
    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public DeleteModuleLessonCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_MODULE_CODE);
        List<String> moduleCodes = argMultimap.getAllValues(PREFIX_MODULE_CODE);
        String args = userInput.trim();
        if (!moduleCodes.isEmpty()) {
            if (args.indexOf(String.valueOf(PREFIX_MODULE_CODE)) != 0) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteModuleLessonCommand.MESSAGE_USAGE));
            }
            try {
                return parseDeleteByModuleCode(moduleCodes);
            } catch (ParseException pe) {
                throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, pe.getMessage()), pe);
            }
        }
        try {
            if (userInput.contains("-")) {
                Index start = ParserUtil.parseIndex(userInput.substring(1, userInput.indexOf("-")));
                Index end = ParserUtil.parseIndex(userInput.substring(userInput.indexOf("-") + 1));
                return new DeleteModuleLessonCommand(start, end);
            }
            return new DeleteModuleLessonCommand(ParserUtil.parseIndex(userInput));
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                            DeleteModuleLessonCommand.MESSAGE_USAGE), pe);
        }
    }

    private DeleteModuleLessonCommand parseDeleteByModuleCode(List<String> moduleCodes) throws ParseException {
        if (moduleCodes.size() > 1) {
            throw new ParseException(DeleteModuleLessonCommand.MESSAGE_DELETE_BY_MODULE_CODE_USAGE);
        }
        if (!ModuleCode.isValidModuleCode(moduleCodes.get(0))) {
            throw new ParseException(DeleteModuleLessonCommand.MESSAGE_USAGE);
        }
        List<String> listOfModuleCode = ParserUtil.parseModuleCodes(moduleCodes).stream()
                .map(ModuleCode::toString)
                .map(string -> {
                    String temp = string;
                    if (string.contains("[")) {
                        temp = temp.substring(temp.indexOf("[") + 1);
                    }
                    if (string.contains("]")) {
                        temp = temp.substring(0, temp.indexOf("]"));
                    }
                    return temp;
                })
                .collect(Collectors.toList());
        return new DeleteModuleLessonCommand(
                new ModuleCodeContainsKeywordsPredicate(Collections.singletonList(listOfModuleCode.get(0)))
        );
    }
}
