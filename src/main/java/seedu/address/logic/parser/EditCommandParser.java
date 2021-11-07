package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMPLOYMENT_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPECTED_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPERIENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL_OF_EDUCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

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
                        PREFIX_ROLE, PREFIX_EMPLOYMENT_TYPE, PREFIX_EXPECTED_SALARY,
                        PREFIX_LEVEL_OF_EDUCATION, PREFIX_EXPERIENCE, PREFIX_TAG, PREFIX_INTERVIEW,
                        PREFIX_NOTES);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();

        parseNameIfAny(argMultimap, editPersonDescriptor);

        parsePhoneIfAny(argMultimap, editPersonDescriptor);

        parseEmailIfAny(argMultimap, editPersonDescriptor);

        parseRoleIfAny(argMultimap, editPersonDescriptor);

        parseEmploymentTypeIfAny(argMultimap, editPersonDescriptor);

        parseExpectedSalaryIfAny(argMultimap, editPersonDescriptor);

        parseLevelOfEducationIfAny(argMultimap, editPersonDescriptor);

        parseExperienceIfAny(argMultimap, editPersonDescriptor);

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);

        parseInterviewIfAny(argMultimap, editPersonDescriptor);

        parseNotesIfAny(argMultimap, editPersonDescriptor);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPersonDescriptor);
    }

    private void parseNotesIfAny(ArgumentMultimap argMultimap, EditPersonDescriptor editPersonDescriptor)
            throws ParseException {
        if (argMultimap.getValue(PREFIX_NOTES).isPresent()) {
            editPersonDescriptor.setNotes(
                    ParserUtil.parseNotes(argMultimap.getValue(PREFIX_NOTES).orElse("")));
        }
    }

    private void parseInterviewIfAny(ArgumentMultimap argMultimap, EditPersonDescriptor editPersonDescriptor)
            throws ParseException {
        if (argMultimap.getValue(PREFIX_INTERVIEW).isPresent()) {
            editPersonDescriptor.setInterview(
                    ParserUtil.parseInterview(argMultimap.getValue(PREFIX_INTERVIEW).orElse("")));
        }
    }

    private void parseExperienceIfAny(ArgumentMultimap argMultimap, EditPersonDescriptor editPersonDescriptor)
            throws ParseException {
        if (argMultimap.getValue(PREFIX_EXPERIENCE).isPresent()) {
            editPersonDescriptor.setExperience(
                    ParserUtil.parseExperience(argMultimap.getValue(PREFIX_EXPERIENCE).get()));
        }
    }

    private void parseLevelOfEducationIfAny(ArgumentMultimap argMultimap, EditPersonDescriptor editPersonDescriptor)
            throws ParseException {
        if (argMultimap.getValue(PREFIX_LEVEL_OF_EDUCATION).isPresent()) {
            editPersonDescriptor.setLevelOfEducation(
                    ParserUtil.parseLevelOfEducation(argMultimap.getValue(PREFIX_LEVEL_OF_EDUCATION).get()));
        }
    }

    private void parseExpectedSalaryIfAny(ArgumentMultimap argMultimap, EditPersonDescriptor editPersonDescriptor)
            throws ParseException {
        if (argMultimap.getValue(PREFIX_EXPECTED_SALARY).isPresent()) {
            editPersonDescriptor.setExpectedSalary(ParserUtil.parseExpectedSalary(argMultimap
                    .getValue(PREFIX_EXPECTED_SALARY).get()));
        }
    }

    private void parseEmploymentTypeIfAny(ArgumentMultimap argMultimap, EditPersonDescriptor editPersonDescriptor)
            throws ParseException {
        if (argMultimap.getValue(PREFIX_EMPLOYMENT_TYPE).isPresent()) {
            editPersonDescriptor.setEmploymentType(
                    ParserUtil.parseEmploymentType(argMultimap.getValue(PREFIX_EMPLOYMENT_TYPE).get()));
        }
    }

    private void parseRoleIfAny(ArgumentMultimap argMultimap, EditPersonDescriptor editPersonDescriptor)
            throws ParseException {
        if (argMultimap.getValue(PREFIX_ROLE).isPresent()) {
            editPersonDescriptor.setRole(ParserUtil.parseRole(argMultimap.getValue(PREFIX_ROLE).get()));
        }
    }

    private void parseEmailIfAny(ArgumentMultimap argMultimap, EditPersonDescriptor editPersonDescriptor)
            throws ParseException {
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
    }

    private void parsePhoneIfAny(ArgumentMultimap argMultimap, EditPersonDescriptor editPersonDescriptor)
            throws ParseException {
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
    }

    private void parseNameIfAny(ArgumentMultimap argMultimap, EditPersonDescriptor editPersonDescriptor)
            throws ParseException {
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
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
