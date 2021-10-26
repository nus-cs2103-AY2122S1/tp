package seedu.academydirectory.logic.parser;


import seedu.academydirectory.logic.commands.VisualiseCommand;
import seedu.academydirectory.logic.parser.exceptions.ParseException;

public class VisualiseCommandParser implements Parser<VisualiseCommand>  {

    /**
     * Parses the given {@code String} of arguments in the context of the VisualiseCommand
     * and returns an VisualiseCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public VisualiseCommand parse(String args) throws ParseException {
        return new VisualiseCommand();
    }
}
