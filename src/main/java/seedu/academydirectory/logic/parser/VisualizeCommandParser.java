package seedu.academydirectory.logic.parser;

import seedu.academydirectory.logic.commands.VisualizeCommand;
import seedu.academydirectory.logic.parser.exceptions.ParseException;

public class VisualizeCommandParser implements Parser<VisualizeCommand> {

    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param userInput
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public VisualizeCommand parse(String userInput) throws ParseException {
        return new VisualizeCommand();
    }
}
