package seedu.academydirectory.logic.parser;

import seedu.academydirectory.logic.commands.VisualiseCommand;
import seedu.academydirectory.logic.parser.exceptions.ParseException;

public class VisualiseCommandParser implements Parser<VisualiseCommand> {

    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param userInput
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public VisualiseCommand parse(String userInput) throws ParseException {
        return new VisualiseCommand();
    }
}
