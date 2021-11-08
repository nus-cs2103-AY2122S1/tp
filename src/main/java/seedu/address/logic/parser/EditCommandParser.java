package seedu.address.logic.parser;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommandParser object.
 */
public abstract class EditCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @param args the input arguments related edit member command to be parsed.
     * @return EditCommand the class for process input edit command string.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public abstract EditCommand parse(String args) throws ParseException;
}
