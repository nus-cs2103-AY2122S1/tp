package seedu.address.logic.parser.guest;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSPORT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.guest.EditGuestCommand;
import seedu.address.logic.commands.guest.EditGuestCommand.EditGuestDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.guest.PassportNumber;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditGuestCommand object
 */
public class EditGuestCommandParser implements Parser<EditGuestCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of theEditGuestCommand
     * and returns an EditGuestCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditGuestCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_EMAIL, PREFIX_TAG,
                        PREFIX_PASSPORT_NUMBER, PREFIX_ROOM_NUMBER);

        PassportNumber passportNumber;

        EditGuestDescriptor editGuestDescriptor = new EditGuestDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editGuestDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }

        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editGuestDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editGuestDescriptor::setTags);

        if (argMultimap.getValue(PREFIX_PASSPORT_NUMBER).isPresent()) {
            passportNumber = ParserUtil.parsePassportNumber(argMultimap.getValue(PREFIX_PASSPORT_NUMBER).get());
            editGuestDescriptor.setPassportNumber(passportNumber);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditGuestCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_ROOM_NUMBER).isPresent()) {
            editGuestDescriptor.setRoomNumber(
                    ParserUtil.parseRoomNumber(argMultimap.getValue(PREFIX_ROOM_NUMBER).get()));
        }

        if (!editGuestDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditGuestCommand.MESSAGE_NOT_EDITED);
        }

        return new EditGuestCommand(passportNumber, editGuestDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
