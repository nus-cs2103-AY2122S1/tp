package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEAVES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SHIFT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditEmployeeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.employee.Shift;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditEmployeeCommand object
 */
public class EditEmployeeCommandParser implements Parser<EditEmployeeCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditEmployeeCommand
     * and returns an EditEmployeeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditEmployeeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_LEAVES, PREFIX_SALARY, PREFIX_JOB_TITLE, PREFIX_SHIFT, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditEmployeeCommand.MESSAGE_USAGE),
                    pe);
        }

        EditEmployeeCommand.EditEmployeeDescriptor editEmployeeDescriptor =
                new EditEmployeeCommand.EditEmployeeDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editEmployeeDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editEmployeeDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editEmployeeDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editEmployeeDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_LEAVES).isPresent()) {
            editEmployeeDescriptor.setLeaves(ParserUtil.parseLeaves(argMultimap.getValue(PREFIX_LEAVES).get()));
        }
        if (argMultimap.getValue(PREFIX_SALARY).isPresent()) {
            editEmployeeDescriptor.setSalary(ParserUtil.parseSalary(argMultimap.getValue(PREFIX_SALARY).get()));
        }
        if (argMultimap.getValue(PREFIX_JOB_TITLE).isPresent()) {
            editEmployeeDescriptor.setJobTitle(ParserUtil.parseJobTitle(argMultimap.getValue(PREFIX_JOB_TITLE).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editEmployeeDescriptor::setTags);
        parseShiftsForEdit(argMultimap.getAllValues(PREFIX_SHIFT)).ifPresent(editEmployeeDescriptor::setShifts);

        if (!editEmployeeDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditEmployeeCommand.MESSAGE_NOT_EDITED);
        }
        return new EditEmployeeCommand(index, editEmployeeDescriptor);
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

    /**
     * Parses {@code Collection<String> shifts} into a {@code Set<Shift>} if {@code shifts} is non-empty.
     * If {@code shifts} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Shift>} containing zero tags.
     */
    private Optional<Set<Shift>> parseShiftsForEdit(Collection<String> shifts) throws ParseException {
        assert shifts != null;

        if (shifts.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> shiftSet = shifts.size() == 1 && shifts.contains("") ? Collections.emptySet() : shifts;
        return Optional.of(ParserUtil.parseShifts(shiftSet));
    }
}
