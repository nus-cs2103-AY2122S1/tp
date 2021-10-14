package seedu.notor.logic.parser;

import seedu.notor.logic.commands.Command;
import seedu.notor.logic.parser.exceptions.ParseException;

/**
 * Represents a Parser that is able to parse user input into a {@code Command} of type {@code T}.
 */
public abstract class Parser<T extends Command> {
    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    public abstract T parse(String userInput) throws ParseException;
}
