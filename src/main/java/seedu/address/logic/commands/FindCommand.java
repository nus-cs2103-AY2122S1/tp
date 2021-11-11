package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPLETION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REQUIREMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.application.Application;
import seedu.address.model.application.CompletionContainsKeywordsPredicate;
import seedu.address.model.application.NameContainsKeywordsPredicate;
import seedu.address.model.application.PositionContainsKeywordsPredicate;
import seedu.address.model.application.PriorityContainsKeywordsPredicate;
import seedu.address.model.application.RequirementsContainsKeywordsPredicate;
import seedu.address.model.application.StatusContainsKeywordsPredicate;

/**
 * Finds and lists all applications in InternSHIP whose name or fields contain any of the argument keywords.
 * Keyword matching is not case sensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD.toUpperCase()
            + " command: Finds all applications whose names, positions, "
            + "requirements, priority, completion or status contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters:\n"
            + PREFIX_COMPANY_NAME + "COMPANY_NAME "
            + PREFIX_INTERNSHIP_POSITION + "INTERNSHIP_POSITION "
            + PREFIX_REQUIREMENT + "APPLICATION_REQUIREMENTS "
            + PREFIX_PRIORITY + "APPLICATION_PRIORITY "
            + PREFIX_COMPLETION + "APPLICATION_COMPLETION "
            + PREFIX_STATUS + "APPLICATION_STATUS\n"
            + "Only one criteria (prefix) should be supplied each time\n"
            + "Example:\n"
            + "Find by company name: " + COMMAND_WORD + " " + PREFIX_COMPANY_NAME + "Amazon ByteDance Google\n"
            + "Find by position: " + COMMAND_WORD + " " + PREFIX_INTERNSHIP_POSITION + "engineer\n"
            + "Find by requirement: " + COMMAND_WORD + " " + PREFIX_REQUIREMENT + "cv\n"
            + "Find by priority: " + COMMAND_WORD + " " + PREFIX_PRIORITY + "High\n"
            + "Find by completion: " + COMMAND_WORD + " " + PREFIX_COMPLETION + "uncompleted\n"
            + "Find by status: " + COMMAND_WORD + " " + PREFIX_STATUS + "accepted\n";


    public static final String MESSAGE_NO_MATCHING = "No matching result found in your Internship list";

    private final Predicate<Application> predicate;

    /**
     * Creates a FindCommand to find the specified {@code Application} by name
     */
    public FindCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * Creates a FindCommand to find the specified {@code Application} by position
     */
    public FindCommand(PositionContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * Creates a FindCommand to find the specified {@code Application} by completion
     */
    public FindCommand(CompletionContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * Creates a FindCommand to find the specified {@code Application} by status
     */
    public FindCommand(StatusContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * Creates a FindCommand to find the specified {@code Application} by priority
     */
    public FindCommand(PriorityContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * Creates a FindCommand to find the specified {@code Application} by requirement
     */
    public FindCommand(RequirementsContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredApplicationList(predicate);
        int listSize = model.getFilteredApplicationList().size();
        if (listSize == 0) {
            return new CommandResult(MESSAGE_NO_MATCHING);
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_APPLICATION_LISTED_OVERVIEW,
                        listSize, listSize == 1 ? "application" : "applications"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
