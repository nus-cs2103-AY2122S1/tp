package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACAD_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACAD_STREAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARENT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARENT_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.ParserUtil.INDEX_ARGS_COUNT_STUDENT;
import static seedu.address.logic.parser.ParserUtil.STUDENT_INDEX_ZERO_BASED;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_PARENT_PHONE, PREFIX_PARENT_EMAIL, PREFIX_ADDRESS,
                        PREFIX_SCHOOL, PREFIX_ACAD_STREAM, PREFIX_ACAD_LEVEL,
                        PREFIX_REMARK, PREFIX_TAG);

        String[] preamble = ParserUtil.parsePreamble(argMultimap.getPreamble());
        if (preamble.length != INDEX_ARGS_COUNT_STUDENT) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_PARENT_PHONE).isPresent()) {
            editPersonDescriptor.setParentPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PARENT_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_PARENT_EMAIL).isPresent()) {
            editPersonDescriptor.setParentEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_PARENT_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_SCHOOL).isPresent()) {
            editPersonDescriptor.setSchool(ParserUtil.parseSchool(argMultimap.getValue(PREFIX_SCHOOL).get()));
        }
        if (argMultimap.getValue(PREFIX_ACAD_STREAM).isPresent()) {
            editPersonDescriptor.setAcadStream(ParserUtil.parseAcadStream(
                    argMultimap.getValue(PREFIX_ACAD_STREAM).get()));
        }
        if (argMultimap.getValue(PREFIX_ACAD_LEVEL).isPresent()) {
            editPersonDescriptor.setAcadLevel(
                    ParserUtil.parseAcadLevel(argMultimap.getValue(PREFIX_ACAD_LEVEL).get()));
        }
        if (argMultimap.getValue(PREFIX_REMARK).isPresent()) {
            editPersonDescriptor.setRemark(ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(String.format(EditCommand.MESSAGE_NOT_EDITED, EditCommand.MESSAGE_USAGE));
        }

        // index error should come after field errors and not edited error
        Index index = ParserUtil.parseStudentIndex(preamble[STUDENT_INDEX_ZERO_BASED]);

        return new EditCommand(index, editPersonDescriptor);
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
