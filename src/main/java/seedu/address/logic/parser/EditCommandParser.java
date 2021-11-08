package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_INPUT_STUDENT_WITH_QUALIFICATION;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TUTOR_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUALIFICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.address.logic.commands.EditCommand.EditTutorDescriptor;
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
        PersonType personType;
        try {
            personType = ParserUtil.parsePersonType(args);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_GENDER,
                PREFIX_QUALIFICATION, PREFIX_REMARK, PREFIX_TAG);
        switch (personType) {
        case TUTOR:
            EditTutorDescriptor editTutorDescriptor = new EditTutorDescriptor();
            return parsePerson(argMultimap, editTutorDescriptor, PersonType.TUTOR);
            // No break necessary due to return statement
        case STUDENT:
            EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor();
            return parsePerson(argMultimap, editStudentDescriptor, PersonType.STUDENT);
            // No break necessary due to return statement
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }
    }

    private EditCommand parsePerson(ArgumentMultimap argMultimap, EditPersonDescriptor editPersonDescriptor,
                                    PersonType personType) throws ParseException {
        Index personIndex;
        try {
            String trimmed = argMultimap.getPreamble().trim();
            String[] split = trimmed.split(" ");
            // Preamble should only have personType and Index
            if (split.length != 2) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
            }
            // If personType or index not given, it is an invalid command format. ParseException with
            // MESSAGE_INVALID_INDEX will be thrown by the line below, but we catch it and throw a more specific and
            // non-technical ParseException below.
            personIndex = ParserUtil.parseIndex(split[1]);
        } catch (ParseException pe) {
            if (pe.getMessage().equals(MESSAGE_INVALID_INDEX)) {
                switch (personType) {
                case STUDENT:
                    throw new ParseException(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
                case TUTOR:
                    throw new ParseException(MESSAGE_INVALID_TUTOR_DISPLAYED_INDEX);
                default:
                    // Should not reach here: invalid personType should be handled in parse()
                    break;
                }
            }
            throw pe;
        }
        setDescriptorValues(argMultimap, editPersonDescriptor, personType);
        return new EditCommand(personIndex, editPersonDescriptor, personType);
    }

    /**
     * Sets the editPersonDescriptor with values (i.e. {@code Name}, {@code Phone}, {@code Gender},
     * {@code Qualification}, {@code Remark}, {@code Tag}) if present.
     */
    private void setDescriptorValues(ArgumentMultimap argMultimap, EditPersonDescriptor editPersonDescriptor,
                                     PersonType personType) throws ParseException {
        if (argMultimap.getValue(PREFIX_QUALIFICATION).isPresent()) {
            switch (personType) {
            case STUDENT:
                throw new ParseException(MESSAGE_INVALID_INPUT_STUDENT_WITH_QUALIFICATION);
            case TUTOR:
                EditTutorDescriptor editTutorDescriptor = (EditTutorDescriptor) editPersonDescriptor;
                editTutorDescriptor.setQualification(ParserUtil.parseQualification(
                        argMultimap.getValue(PREFIX_QUALIFICATION).get()));
                break;
            default:
                // Should not reach here: invalid personType should be handled in parse()
                break;
            }
        }
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_GENDER).isPresent()) {
            editPersonDescriptor.setGender(ParserUtil.parseGender(argMultimap.getValue(PREFIX_GENDER).get()));
        }
        if (argMultimap.getValue(PREFIX_REMARK).isPresent()) {
            editPersonDescriptor.setRemark(ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).get()));
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);
        }
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags, which will throw a ParseException.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        if (tagSet.isEmpty()) {
            throw new ParseException(Tag.MESSAGE_INVALID_TAG);
        }
        return Optional.of(ParserUtil.parseTags(tagSet));
    }
}
