package nustracker.logic.parser;

import static java.util.Objects.requireNonNull;
import static nustracker.commons.core.Messages.MESSAGE_COMMAND_EXTRANEOUS_SLASHES;
import static nustracker.logic.parser.CliSyntax.PREFIX_FILENAME;

import nustracker.commons.core.Messages;
import nustracker.logic.commands.ExportCommand;
import nustracker.logic.parser.exceptions.ExtraSlashException;
import nustracker.logic.parser.exceptions.ParseException;

public class ExportCommandParser implements Parser<ExportCommand> {

    @Override
    public ExportCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap;

        try {
            argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_FILENAME);
        } catch (ExtraSlashException e) {
            throw new ParseException(String.format(MESSAGE_COMMAND_EXTRANEOUS_SLASHES,
                    ExportCommand.MESSAGE_USAGE));
        }

        if (!argMultimap.arePrefixesPresent(PREFIX_FILENAME) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    ExportCommand.MESSAGE_USAGE));
        }

        String fileName = ParserUtil.parseExportFileName(argMultimap.getValue(PREFIX_FILENAME).get());

        return new ExportCommand(fileName);
    }
}


