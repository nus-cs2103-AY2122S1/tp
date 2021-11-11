package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE_OF_APPLICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_DATE_AND_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REQUIREMENT;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditApplicationDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.application.InterviewDateAndTime;
import seedu.address.model.application.Requirement;

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
                ArgumentTokenizer.tokenize(args, PREFIX_COMPANY_NAME, PREFIX_INTERNSHIP_POSITION,
                        PREFIX_DEADLINE_OF_APPLICATION, PREFIX_PRIORITY,
                        PREFIX_REQUIREMENT, PREFIX_INTERVIEW_DATE_AND_TIME);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX + "\n%1$s",
                            EditCommand.MESSAGE_USAGE), pe);
        }

        EditApplicationDescriptor editApplicationDescriptor = new EditApplicationDescriptor();
        if (argMultimap.getValue(PREFIX_COMPANY_NAME).isPresent()) {
            editApplicationDescriptor.setCompany(ParserUtil.parseCompany(
                    argMultimap.getValue(PREFIX_COMPANY_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_INTERNSHIP_POSITION).isPresent()) {
            editApplicationDescriptor.setPosition(ParserUtil.parsePosition(
                    argMultimap.getValue(PREFIX_INTERNSHIP_POSITION).get()));
        }
        if (argMultimap.getValue(PREFIX_DEADLINE_OF_APPLICATION).isPresent()) {
            editApplicationDescriptor.setDeadline(ParserUtil.parseDeadline(
                    argMultimap.getValue(PREFIX_DEADLINE_OF_APPLICATION).get()));
        }
        if (argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            editApplicationDescriptor.setPriority(ParserUtil.parsePriority(
                    argMultimap.getValue(PREFIX_PRIORITY).get()));
        }

        parseRequirementsForEdit(argMultimap.getAllValues(PREFIX_REQUIREMENT))
                .ifPresent(editApplicationDescriptor::setRequirements);

        parseInterviewDateAndTimeForEdit(argMultimap.getAllValues(PREFIX_INTERVIEW_DATE_AND_TIME))
                .ifPresent(editApplicationDescriptor::setInterviewDateAndTimes);


        if (!editApplicationDescriptor.isAnyFieldEdited()) {
            throw new ParseException(String.format(EditCommand.MESSAGE_NO_FILED_PROVIDED + "\n%1$s",
                    EditCommand.MESSAGE_USAGE));
        }

        return new EditCommand(index, editApplicationDescriptor);
    }

    /**
     * Parses {@code Collection<String> requirements} into a {@code Set<Tag>} if {@code requirements} is non-empty.
     * If {@code requirements} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero requirements.
     */
    private Optional<Set<Requirement>> parseRequirementsForEdit(Collection<String> requirements) throws ParseException {
        assert requirements != null;

        if (requirements.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> requirementSet = requirements.size() == 1 && requirements.contains("")
                ? Collections.emptySet() : requirements;
        return Optional.of(ParserUtil.parseRequirements(requirementSet));
    }

    /**
     * Parses {@code Collection<String> interviewDateAndTimes} into a {@code Set<InterviewDateAndTime>}
     * if {@code interviewDateAndTimes} is non-empty.
     * If {@code interviewDateAndTimes} contain only one element
     * which is an empty string, it will be parsed into a
     * {@code Set<InterviewDateAndTime>} containing zero requirements.
     */
    private Optional<Set<InterviewDateAndTime>>
        parseInterviewDateAndTimeForEdit(Collection<String> interviewDateAndTimes) throws ParseException {
        assert interviewDateAndTimes != null;

        if (interviewDateAndTimes.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> interviewDateAndTimeSet =
                interviewDateAndTimes.size() == 1 && interviewDateAndTimes.contains("")
                ? Collections.emptySet() : interviewDateAndTimes;
        return Optional.of(ParserUtil.parseInterviewDateAndTimes(interviewDateAndTimeSet));
    }

}
