package seedu.tuitione.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.tuitione.commons.core.Messages.HEADER_ALERT;
import static seedu.tuitione.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_DELETE_REMARK;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_REMARK;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import seedu.tuitione.commons.core.index.Index;
import seedu.tuitione.commons.util.StringUtil;
import seedu.tuitione.logic.commands.EditCommand;
import seedu.tuitione.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.tuitione.logic.parser.exceptions.ParseException;
import seedu.tuitione.model.remark.Remark;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_GRADE, PREFIX_REMARK, PREFIX_DELETE_REMARK);

        Index index;

        String preamble = argMultimap.getPreamble();
        if (!StringUtil.isAStringedNumber(preamble)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }
        index = ParserUtil.parseIndex(preamble);

        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editStudentDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editStudentDescriptor.setPhone(ParserUtil.parseParentContact(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editStudentDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editStudentDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_GRADE).isPresent()) {
            editStudentDescriptor.setGrade(ParserUtil.parseGrade(argMultimap.getValue(PREFIX_GRADE).get()));
        }

        parseRemarksForEdit(argMultimap.getAllValues(PREFIX_REMARK)).ifPresent(editStudentDescriptor::setRemarks);

        parseRemarksForEdit(argMultimap.getAllValues(PREFIX_DELETE_REMARK))
                .ifPresent(editStudentDescriptor::setRemarksToDelete);

        if (!editStudentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editStudentDescriptor);
    }

    /**
     * Parses {@code Collection<String> remarks} into a {@code Set<Remark>} if {@code remarks} is non-empty.
     * If {@code remarks} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Remark>} containing zero remarks.
     */
    private Optional<Set<Remark>> parseRemarksForEdit(Collection<String> remarks) throws ParseException {
        assert remarks != null;

        if (remarks.isEmpty()) {
            return Optional.empty();
        }

        for (String remark : remarks) {
            if (remark.isBlank()) {
                throw new ParseException(HEADER_ALERT + Remark.MESSAGE_CONSTRAINTS);
            }
        }
        return Optional.of(ParserUtil.parseRemarks(remarks));
    }

}
