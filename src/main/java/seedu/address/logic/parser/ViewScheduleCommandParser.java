package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.ViewScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

/**
 * Class representing the view schedule command parser.
 */
public class ViewScheduleCommandParser implements Parser<ViewScheduleCommand> {

    @Override
    public ViewScheduleCommand parse(String userInput) throws ParseException {
        //currently defined for name prefix, undefined behaviour
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_NAME);
        if (!argMultimap.getValue(PREFIX_NAME).isPresent()) {
            throw new ParseException(ViewScheduleCommand.HELP_MESSAGE);
        }
        return new ViewScheduleCommand(argMultimap.getValue(PREFIX_NAME).map(Name::new).get());


    }
}
