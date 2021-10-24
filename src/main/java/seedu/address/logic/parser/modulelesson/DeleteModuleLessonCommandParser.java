package seedu.address.logic.parser.modulelesson;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;

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

public class DeleteModuleLessonCommandParser implements Parser<DeleteModuleLessonCommand> {
    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param userInput
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public DeleteModuleLessonCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_MODULE_CODE);
        List<String> moduleCodes = argMultimap.getAllValues(PREFIX_MODULE_CODE);
        if (moduleCodes.size() == 1) {
            List<String> listOfModuleCode = ParserUtil.parseModuleCodes(moduleCodes).stream()
                    .map(moduleCode -> moduleCode.toString())
                    .collect(Collectors.toList());
            return new DeleteModuleLessonCommand(new ModuleCodeContainsKeywordsPredicate(listOfModuleCode.get(0)));
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

    /*private DeleteModuleLessonCommand parseDeleteByModule(List<String> moduleCodes) throws ParseException {

    }*/
}
