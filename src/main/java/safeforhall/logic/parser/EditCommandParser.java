package safeforhall.logic.parser;

import static java.util.Objects.requireNonNull;
import static safeforhall.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;

import safeforhall.commons.core.index.Index;
import safeforhall.logic.commands.EditCommand;
import safeforhall.logic.commands.EditCommand.EditPersonDescriptor;
import safeforhall.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_PHONE,
                        CliSyntax.PREFIX_EMAIL, CliSyntax.PREFIX_ROOM, CliSyntax.PREFIX_VACCSTATUS,
                        CliSyntax.PREFIX_FACULTY, CliSyntax.PREFIX_FETDATE, CliSyntax.PREFIX_COLLECTIONDATE);
        ArrayList<Index> indexArray;

        try {
            indexArray = ParserUtil.parseIndexes(argMultimap.getPreamble().trim().split(" "));
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argMultimap.getValue(CliSyntax.PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(CliSyntax.PREFIX_NAME)
                                .get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(CliSyntax.PREFIX_PHONE)
                                .get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(CliSyntax.PREFIX_EMAIL)
                                .get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_ROOM).isPresent()) {
            editPersonDescriptor.setRoom(ParserUtil.parseRoom(argMultimap.getValue(CliSyntax.PREFIX_ROOM)
                                .get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_VACCSTATUS).isPresent()) {
            editPersonDescriptor.setVaccStatus(ParserUtil.parseVaccStatus(argMultimap
                    .getValue(CliSyntax.PREFIX_VACCSTATUS).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_FACULTY).isPresent()) {
            editPersonDescriptor.setFaculty(ParserUtil.parseFaculty(argMultimap.getValue(CliSyntax.PREFIX_FACULTY)
                    .get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_FETDATE).isPresent()) {
            editPersonDescriptor.setLastFetDate(ParserUtil.parseDate(argMultimap.getValue(CliSyntax.PREFIX_FETDATE)
                    .get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_COLLECTIONDATE).isPresent()) {
            editPersonDescriptor.setLastCollectionDate(ParserUtil.parseDate(argMultimap.getValue(
                    CliSyntax.PREFIX_COLLECTIONDATE).get()));
        }

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(indexArray, editPersonDescriptor);
    }

}
