package safeforhall.logic.parser.view;

import static safeforhall.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import safeforhall.commons.core.index.Index;
import safeforhall.logic.commands.view.ViewEventCommand;
import safeforhall.logic.parser.Parser;
import safeforhall.logic.parser.ParserUtil;
import safeforhall.logic.parser.exceptions.ParseException;


public class ViewEventCommandParser implements Parser<ViewEventCommand> {

    @Override
    public ViewEventCommand parse(String args) throws ParseException {
        if (args.isEmpty() || args.equals("")) {
            return new ViewEventCommand();
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(args.trim());
            return new ViewEventCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ViewEventCommand.MESSAGE_USAGE), pe);
        }
    }
}
