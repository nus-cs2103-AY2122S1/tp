package seedu.academydirectory.logic.parser;

import static seedu.academydirectory.logic.parser.ParserUtil.assertSingularCommand;

import seedu.academydirectory.commons.core.Messages;
import seedu.academydirectory.logic.commands.ClearCommand;
import seedu.academydirectory.logic.commands.Command;
import seedu.academydirectory.logic.commands.ExitCommand;
import seedu.academydirectory.logic.commands.HistoryCommand;
import seedu.academydirectory.logic.commands.ListCommand;
import seedu.academydirectory.logic.commands.RedoCommand;
import seedu.academydirectory.logic.commands.UndoCommand;
import seedu.academydirectory.logic.commands.VisualizeCommand;
import seedu.academydirectory.logic.parser.exceptions.ParseException;

/**
 * Represents a Parser that is able to parse user input into a {@code Command} of type {@code T}.
 */
public interface Parser<T extends Command> {
    /**
     * Parses {@code userInput} into a command and returns it.
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    T parse(String userInput) throws ParseException;
}
