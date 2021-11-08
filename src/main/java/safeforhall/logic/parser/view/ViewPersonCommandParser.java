package safeforhall.logic.parser.view;

import static safeforhall.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import safeforhall.commons.core.index.Index;
import safeforhall.logic.commands.view.ViewPersonCommand;
import safeforhall.logic.parser.Parser;
import safeforhall.logic.parser.ParserUtil;
import safeforhall.logic.parser.exceptions.ParseException;


public class ViewPersonCommandParser implements Parser<ViewPersonCommand> {

    @Override
    public ViewPersonCommand parse(String args) throws ParseException {
        if (args.isEmpty() || args.equals("")) {
            return new ViewPersonCommand();
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(args.trim());
            return new ViewPersonCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ViewPersonCommand.MESSAGE_USAGE), pe);
        }
    }
}
