package seedu.address.logic.parser;

import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code HelpCommand} object.
 */
public class HelpCommandParser implements Parser<HelpCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code HelpCommand}.
     *
     * @param args user input.
     * @return HelpCommand object.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public HelpCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);
        String helpCommandWord = argMultimap.getPreamble();

        return new HelpCommand(helpCommandWord);
    }

}
