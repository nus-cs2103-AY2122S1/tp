package seedu.address.logic.parser;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class TagCommandParser implements Parser<Command> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TagCommand parse(String args) throws ParseException {
        return new TagCommand(ParserUtil.parseIndex("1"));
    }
}
