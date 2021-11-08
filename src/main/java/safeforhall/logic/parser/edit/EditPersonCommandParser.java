package safeforhall.logic.parser.edit;

import static java.util.Objects.requireNonNull;
import static safeforhall.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;

import safeforhall.commons.core.index.Index;
import safeforhall.logic.commands.edit.EditPersonCommand;
import safeforhall.logic.commands.edit.EditPersonCommand.EditPersonDescriptor;
import safeforhall.logic.parser.ArgumentMultimap;
import safeforhall.logic.parser.ArgumentTokenizer;
import safeforhall.logic.parser.CliSyntax;
import safeforhall.logic.parser.Parser;
import safeforhall.logic.parser.ParserUtil;
import safeforhall.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditPersonCommand object
 */
public class EditPersonCommandParser implements Parser<EditPersonCommand> {

    public static final String MESSAGE_DUPLICATE_NAME = "Name should not be changed for more than one person.";
    public static final String MESSAGE_DUPLICATE_ROOM = "Room should not be changed for more than one person.";

    /**
     * Parses the given {@code String} of arguments in the context of the EditPersonCommand
     * and returns an EditPersonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditPersonCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_PHONE,
                        CliSyntax.PREFIX_EMAIL, CliSyntax.PREFIX_ROOM, CliSyntax.PREFIX_VACCSTATUS,
                        CliSyntax.PREFIX_FACULTY, CliSyntax.PREFIX_FETDATE, CliSyntax.PREFIX_COLLECTIONDATE);
        ArrayList<Index> indexArray;

        try {
            indexArray = ParserUtil.parseIndexes(argMultimap.getPreamble().split(" "));
            boolean isNameChanged = argMultimap.getValue(CliSyntax.PREFIX_NAME).isPresent();
            boolean isRoomChanged = argMultimap.getValue(CliSyntax.PREFIX_ROOM).isPresent();
            boolean isMoreThanOnePerson = indexArray.size() > 1;
            if (isMoreThanOnePerson && isNameChanged) {
                throw new ParseException(MESSAGE_DUPLICATE_NAME);
            }
            if (isMoreThanOnePerson && isRoomChanged) {
                throw new ParseException(MESSAGE_DUPLICATE_ROOM);
            }
        } catch (ParseException pe) {
            String message = pe.getMessage() + "\n" + EditPersonCommand.MESSAGE_USAGE;
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, message), pe);
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
            throw new ParseException(EditPersonCommand.MESSAGE_NOT_EDITED);
        }

        return new EditPersonCommand(indexArray, editPersonDescriptor);
    }

}
