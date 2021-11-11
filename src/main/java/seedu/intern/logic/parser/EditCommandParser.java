package seedu.intern.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.intern.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_COURSE;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_GRADUATIONYEARMONTH;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_INSTITUTION;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_JOB;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.intern.commons.core.selection.Selection;
import seedu.intern.logic.commands.EditCommand;
import seedu.intern.logic.commands.EditCommand.EditApplicantDescriptor;
import seedu.intern.logic.parser.exceptions.ParseException;
import seedu.intern.model.skills.Skill;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_GRADE, PREFIX_STATUS,
                        PREFIX_SKILL, PREFIX_INSTITUTION, PREFIX_COURSE, PREFIX_GRADUATIONYEARMONTH, PREFIX_JOB);
        ArgumentTokenizer.tokenize(args,
                PREFIX_NAME,
                PREFIX_PHONE,
                PREFIX_EMAIL,
                PREFIX_SKILL,
                PREFIX_GRADE,
                PREFIX_INSTITUTION,
                PREFIX_COURSE,
                PREFIX_GRADUATIONYEARMONTH,
                PREFIX_JOB,
                PREFIX_STATUS);

        Selection selection;

        try {
            selection = ParserUtil.parseSelection(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }
        if (selection.hasAllSelectFlag() && (argMultimap.getValue(PREFIX_NAME).isPresent()
                || argMultimap.getValue(PREFIX_PHONE).isPresent()
                || argMultimap.getValue(PREFIX_COURSE).isPresent()
                || argMultimap.getValue(PREFIX_EMAIL).isPresent()
                || argMultimap.getValue(PREFIX_GRADE).isPresent()
                || argMultimap.getValue(PREFIX_GRADUATIONYEARMONTH).isPresent()
                || argMultimap.getValue(PREFIX_INSTITUTION).isPresent()
                || argMultimap.getValue(PREFIX_JOB).isPresent()
                || argMultimap.getValue(PREFIX_SKILL).isPresent()
                )) {
            throw new ParseException("ALL FLAG CAN ONLY BE USED WITH:\n"
                    + "[" + PREFIX_STATUS + "APPLICATION_STATUS]\n");
        }

        EditApplicantDescriptor editApplicantDescriptor = new EditApplicantDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editApplicantDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editApplicantDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editApplicantDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_GRADE).isPresent()) {
            editApplicantDescriptor.setGrade(ParserUtil.parseGrade(argMultimap.getValue(PREFIX_GRADE).get()));
        }
        if (argMultimap.getValue(PREFIX_INSTITUTION).isPresent()) {
            editApplicantDescriptor
                    .setInstitution(ParserUtil.parseInstitution(argMultimap.getValue(PREFIX_INSTITUTION).get()));
        }
        if (argMultimap.getValue(PREFIX_GRADUATIONYEARMONTH).isPresent()) {
            editApplicantDescriptor
                    .setGraduationYearMonth(ParserUtil.parseGraduationYearMonth(
                            argMultimap.getValue(PREFIX_GRADUATIONYEARMONTH).get()));
        }
        if (argMultimap.getValue(PREFIX_COURSE).isPresent()) {
            editApplicantDescriptor
                    .setCourse(ParserUtil.parseCourse(argMultimap.getValue(PREFIX_COURSE).get()));
        }
        if (argMultimap.getValue(PREFIX_JOB).isPresent()) {
            editApplicantDescriptor
                    .setJob(ParserUtil.parseJob(argMultimap.getValue(PREFIX_JOB).get()));
        }
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            editApplicantDescriptor
                    .setApplicationStatus(ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get()));
        }
        parseSkillsForEdit(argMultimap.getAllValues(PREFIX_SKILL)).ifPresent(editApplicantDescriptor::setSkills);

        if (!editApplicantDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(selection, editApplicantDescriptor);
    }

    /**
     * Parses {@code Collection<String> skills} into a {@code Set<skill>} if {@code skills} is non-empty.
     * If {@code skills} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<skill>} containing zero skills.
     */
    private Optional<Set<Skill>> parseSkillsForEdit(Collection<String> skills) throws ParseException {
        assert skills != null;

        if (skills.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> skillSet = skills.size() == 1 && skills.contains("") ? Collections.emptySet() : skills;
        return Optional.of(ParserUtil.parseSkills(skillSet));
    }

}
