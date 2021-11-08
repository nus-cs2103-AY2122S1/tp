package seedu.notor.logic.parser.group;

import static java.util.Objects.requireNonNull;
import static seedu.notor.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.notor.logic.commands.group.GroupCommand;
import seedu.notor.logic.commands.group.SuperGroupCreateCommand;
import seedu.notor.logic.commands.person.PersonCreateCommand;
import seedu.notor.logic.parser.ArgumentMultimap;
import seedu.notor.logic.parser.ArgumentTokenizer;
import seedu.notor.logic.parser.exceptions.ParseException;
import seedu.notor.model.common.Name;
import seedu.notor.model.group.SuperGroup;

/**
 * Parses input arguments to create a group command.
 */
public class SuperGroupCreateCommandParser extends GroupCommandParser {
    private final String uncheckedName;

    /**
     * Constructor for a SuperGroupCreateCommandParser instance.
     *
     * @param uncheckedName Unchecked name of the SuperGroup to be parsed.
     * @param arguments Arguments to be parsed.
     * @throws ParseException If arguments could not be parsed.
     */
    public SuperGroupCreateCommandParser(String uncheckedName, String arguments) throws ParseException {
        super(null, arguments);
        this.uncheckedName = uncheckedName;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the {@code GroupCommand} and
     * returns a {@code GroupCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public GroupCommand parse() throws ParseException {
        requireNonNull(arguments);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(arguments, PREFIX_TAG);

        if (!Name.isValidName(uncheckedName)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    Name.MESSAGE_CONSTRAINTS, PersonCreateCommand.MESSAGE_USAGE));
        }

        Name name = new Name(uncheckedName);
        SuperGroup superGroup = new SuperGroup(name);

        return new SuperGroupCreateCommand(index, superGroup);
    }
}
