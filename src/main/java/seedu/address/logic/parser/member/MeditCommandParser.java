package seedu.address.logic.parser.member;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.member.MeditCommand;
import seedu.address.logic.commands.member.MeditCommand.EditMemberDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.member.position.Position;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class MeditCommandParser implements Parser<MeditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MeditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MEMBER_INDEX, PREFIX_NAME,
                        PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_POSITION);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_MEMBER_INDEX)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MeditCommand.MESSAGE_USAGE));
        }
        Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_MEMBER_INDEX).get());

        EditMemberDescriptor editMemberDescriptor = new EditMemberDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editMemberDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editMemberDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editMemberDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editMemberDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        parsePositionsForEdit(argMultimap.getAllValues(PREFIX_POSITION)).ifPresent(editMemberDescriptor::setPositions);

        if (!editMemberDescriptor.isAnyFieldEdited()) {
            throw new ParseException(MeditCommand.MESSAGE_NOT_EDITED);
        }

        return new MeditCommand(index, editMemberDescriptor);
    }

    /**
     * Parses {@code Collection<String> positions} into a {@code Set<Position>} if {@code positions} is non-empty.
     * If {@code positions} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Position>} containing zero positions.
     */
    private Optional<Set<Position>> parsePositionsForEdit(Collection<String> positions) throws ParseException {
        assert positions != null;

        if (positions.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> positionSet =
                positions.size() == 1 && positions.contains("") ? Collections.emptySet() : positions;
        return Optional.of(ParserUtil.parsePositions(positionSet));
    }

}
