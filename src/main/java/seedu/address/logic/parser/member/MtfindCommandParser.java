package seedu.address.logic.parser.member;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.member.MtfindCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.member.Member;
import seedu.address.model.module.task.Task;
import seedu.address.model.module.task.TaskList;

public class MtfindCommandParser implements Parser<MtfindCommand> {

    /**
     * Parses {@code userInput} of arguments in the context of the MtfindCommand
     * and returns a MtfindCommand Object.
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public MtfindCommand parse(String args) throws ParseException {
        String trimmedArg = args.trim();
        if (trimmedArg.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MtfindCommand.MESSAGE_USAGE));
        }

        String[] keywords = trimmedArg.split("\\s+");

        Predicate<Member> resultPredicate = member -> {
            TaskList taskList = member.getTaskList();
            return Arrays.stream(keywords).anyMatch(keyword -> {
                for (Task task : taskList) {
                    if (StringUtil.containsWordIgnoreCase(task.getName().fullName, keyword)) {
                        return true;
                    }
                }
                return false;
            });
        };
        return new MtfindCommand(resultPredicate);
    }
}
