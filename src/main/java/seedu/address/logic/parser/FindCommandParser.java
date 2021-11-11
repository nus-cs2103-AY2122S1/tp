package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPLETION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REQUIREMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.Arrays;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.application.Company;
import seedu.address.model.application.Completion;
import seedu.address.model.application.CompletionContainsKeywordsPredicate;
import seedu.address.model.application.NameContainsKeywordsPredicate;
import seedu.address.model.application.Position;
import seedu.address.model.application.PositionContainsKeywordsPredicate;
import seedu.address.model.application.Priority;
import seedu.address.model.application.PriorityContainsKeywordsPredicate;
import seedu.address.model.application.Requirement;
import seedu.address.model.application.RequirementsContainsKeywordsPredicate;
import seedu.address.model.application.Status;
import seedu.address.model.application.StatusContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    public static final String PRIORITY_MESSAGE_CONSTRAINTS =
            "Priority can only be exactly one of 'High', 'Medium' or 'Low' (case-insensitive).";
    public static final String STATUS_MESSAGE_CONSTRAINTS =
            "Status can only be exactly one of 'Pending', 'Accepted' or 'Rejected' (case-insensitive).";
    public static final String COMPLETION_MESSAGE_CONSTRAINTS =
            "Completion can only be exactly one of 'Completed' or 'Uncompleted' (case-insensitive).";

    private static final int PREFIX_AND_KEYWORD_SIZE = 2;

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COMPANY_NAME, PREFIX_INTERNSHIP_POSITION,
                        PREFIX_COMPLETION, PREFIX_STATUS, PREFIX_PRIORITY, PREFIX_REQUIREMENT);

        if (argMultimap.getSize() != PREFIX_AND_KEYWORD_SIZE || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_COMPANY_NAME).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_COMPANY_NAME).get().trim();
            String[] nameKeywords = trimmedArgs.split("\\s+");
            for (String name : nameKeywords) {
                if (!Company.isValidCompanyName(name)) {
                    throw new ParseException(Company.MESSAGE_CONSTRAINTS);
                }
            }
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }

        if (argMultimap.getValue(PREFIX_INTERNSHIP_POSITION).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_INTERNSHIP_POSITION).get().trim();
            String[] positionKeywords = trimmedArgs.split("\\s+");
            for (String position : positionKeywords) {
                if (!Position.isValidPosition(position)) {
                    throw new ParseException(Position.MESSAGE_CONSTRAINTS);
                }
            }
            return new FindCommand(new PositionContainsKeywordsPredicate(Arrays.asList(positionKeywords)));
        }

        if (argMultimap.getValue(PREFIX_COMPLETION).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_COMPLETION).get().trim();
            String[] completionKeywords = trimmedArgs.split("\\s+");
            if (completionKeywords.length != 1) {
                throw new ParseException(COMPLETION_MESSAGE_CONSTRAINTS);
            }
            for (String completion : completionKeywords) {
                if (!Completion.isValidCompletion(completion)) {
                    throw new ParseException(COMPLETION_MESSAGE_CONSTRAINTS);
                }
            }
            return new FindCommand(new CompletionContainsKeywordsPredicate(Arrays.asList(completionKeywords)));
        }

        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_STATUS).get().trim();
            String[] statusKeywords = trimmedArgs.split("\\s+");
            if (statusKeywords.length != 1) {
                throw new ParseException(STATUS_MESSAGE_CONSTRAINTS);
            }
            for (String status : statusKeywords) {
                if (!Status.isValidStatus(status)) {
                    throw new ParseException(STATUS_MESSAGE_CONSTRAINTS);
                }
            }
            return new FindCommand(new StatusContainsKeywordsPredicate(Arrays.asList(statusKeywords)));
        }

        if (argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_PRIORITY).get().trim();
            String[] priorityKeywords = trimmedArgs.split("\\s+");
            if (priorityKeywords.length != 1) {
                throw new ParseException(PRIORITY_MESSAGE_CONSTRAINTS);
            }
            for (String priority : priorityKeywords) {
                if (!Priority.isValidPriority(priority)) {
                    throw new ParseException(PRIORITY_MESSAGE_CONSTRAINTS);
                }
            }
            return new FindCommand(new PriorityContainsKeywordsPredicate(Arrays.asList(priorityKeywords)));
        }

        if (argMultimap.getValue(PREFIX_REQUIREMENT).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_REQUIREMENT).get().trim();
            String[] requirementsKeywords = trimmedArgs.split("\\s+");
            for (String requirement : requirementsKeywords) {
                if (!Requirement.isValidRequirement(requirement)) {
                    throw new ParseException(Requirement.MESSAGE_CONSTRAINTS);
                }
            }
            return new FindCommand(new RequirementsContainsKeywordsPredicate(Arrays.asList(requirementsKeywords)));
        }

        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }
}
