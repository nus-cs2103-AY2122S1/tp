package seedu.address.logic.parser.member;

import java.util.function.Predicate;

import seedu.address.logic.commands.member.MtfindCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.member.Member;
import seedu.address.model.module.task.TaskList;

public class MtfindCommandParser implements Parser<MtfindCommand> {

    /**
     * Parses {@code userInput} of arguments in the context of the MtfindCommand
     * and returns a MtfindCommand Object.
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public MtfindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        return new Predicate<Member> = member -> {
            TaskList taskList = member.getTaskList();
            return taskList.iterator().forEachRemaining();
        }
    }
}
