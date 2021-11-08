package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditMemberCommand;
import seedu.address.logic.commands.EditMemberCommand.EditMemberDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.member.Id;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditMemberCommand object.
 */
public class EditMemberCommandParser extends EditCommandParser implements Parser<EditMemberCommand> {

    private static final int PREFIX_SIZE = 3;

    /**
     * Parses the given {@code String} of arguments in the context of the EditMemberCommand
     * and returns an EditMemberCommand object for execution.
     *
     * @param args the input arguments related edit member command to be parsed.
     * @return EditMemberCommand the class for process input edit member command string.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public EditMemberCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MEMBER, PREFIX_ID, PREFIX_INDEX,
                PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        if (argMultimap.getSize() < PREFIX_SIZE || argMultimap.getValue(PREFIX_MEMBER).isEmpty()
                || (argMultimap.getValue(PREFIX_ID).isEmpty() && argMultimap.getValue(PREFIX_INDEX).isEmpty())
                || (argMultimap.getValue(PREFIX_ID).isPresent() && argMultimap.getValue(PREFIX_INDEX).isPresent())
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditMemberCommand.MESSAGE_USAGE));
        }

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
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editMemberDescriptor::setTags);

        if (!editMemberDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditMemberCommand.MESSAGE_NOT_EDITED);
        }

        if (argMultimap.getValue(PREFIX_ID).isPresent()) {
            Id id = ParserUtil.parseMemberId(argMultimap.getValue(PREFIX_ID).get());
            return new EditMemberCommand(id, editMemberDescriptor);
        }

        if (argMultimap.getValue(PREFIX_INDEX).isPresent()) {
            Index index;
            try {
                index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
            } catch (ParseException pe) {
                throw new ParseException(String.format(pe.getMessage(), EditMemberCommand.MESSAGE_USAGE), pe);
            }
            return new EditMemberCommand(index, editMemberDescriptor);
        }

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditMemberCommand.MESSAGE_USAGE));
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
