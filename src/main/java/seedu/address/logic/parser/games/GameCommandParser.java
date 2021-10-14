package seedu.address.logic.parser.games;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_GAME_FLAG;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.games.AddGameCommand;
import seedu.address.logic.commands.games.DeleteGameCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input for games.
 */
public class GameCommandParser implements Parser<Command> {
    public static final String COMMAND_WORD = "game";

    /**
     * Parses the given {@code String} of arguments in the context of the GameCommand
     * and returns a GameCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public Command parse(String arguments) throws ParseException {
        String[] userInput = arguments.strip().split(" ");
        if (userInput.length <= 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        // TODO For respective command implementers to uncomment when done:
        switch (userInput[0]) {
        case DeleteGameCommand.COMMAND_WORD:
            return new DeleteGameCommandParser().parse(arguments);
        case AddGameCommand.COMMAND_WORD:
            return new AddGameCommandParser().parse(arguments);
        /*
         case GetGameCommand.COMMAND_WORD:
             return new GetGameCommandParser().parse(arguments);

        case ListGameCommand.COMMAND_WORD:
             return new ListGameCommandParser().parse(arguments);
         */
        default:
            throw new ParseException(MESSAGE_UNKNOWN_GAME_FLAG);

        }
    }
}
