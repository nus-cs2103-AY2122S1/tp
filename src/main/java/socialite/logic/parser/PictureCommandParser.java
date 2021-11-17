package socialite.logic.parser;

import static java.util.Objects.requireNonNull;

import socialite.commons.core.Messages;
import socialite.commons.core.index.Index;
import socialite.logic.commands.PictureCommand;
import socialite.logic.parser.exceptions.ParseException;

public class PictureCommandParser implements Parser<PictureCommand> {

    @Override
    public PictureCommand parse(String args) throws ParseException {
        requireNonNull(args);

        Index index;
        try {
            index = ParserUtil.parseIndex(args);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, PictureCommand.MESSAGE_HELP_GUIDE), pe);
        }

        return new PictureCommand(index, true);
    }
}
