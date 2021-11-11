package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPLICATIONS;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.application.Application;
import seedu.address.model.application.Company;
import seedu.address.model.application.Completion;
import seedu.address.model.application.Deadline;
import seedu.address.model.application.InterviewDateAndTime;
import seedu.address.model.application.Position;
import seedu.address.model.application.Priority;
import seedu.address.model.application.Requirement;
import seedu.address.model.application.Status;

/**
 * Sets the Completion of an application to "Completed".
 */
public class CompleteCommand extends Command {
    public static final String COMMAND_WORD = "complete";

    public static final String MESSAGE_USAGE = COMMAND_WORD.toUpperCase()
            + " command: Marks the application at a specified index"
            + " (as identified by the index in the displayed application list) as 'Completed'\n"
            + "Parameters: "
            + Messages.MESSAGE_INDEX_REQUIREMENT + "\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Completed application: %1$s";

    public static final String MESSAGE_ALREADY_COMPLETED = "Application has already been completed!";

    private final Index targetIndex;

    /**
     * Creates an CompleteCommand to mark the specified {@code Application} as completed.
     *
     * @param targetIndex Index of the application to be marked as Completed.
     */
    public CompleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Application> lastShownList = model.getFilteredApplicationList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INDEX_EXCEEDS_LIST_LENGTH);
        }

        Application applicationToComplete = lastShownList.get(targetIndex.getZeroBased());

        if (applicationToComplete.isCompleted()) {
            throw new CommandException(MESSAGE_ALREADY_COMPLETED);
        }

        Company company = applicationToComplete.getCompany();
        Position position = applicationToComplete.getPosition();
        Deadline deadline = applicationToComplete.getDeadline();
        Completion completion = new Completion("Completed");
        Status status = applicationToComplete.getStatus();
        Priority priority = applicationToComplete.getPriority();
        Set<Requirement> requirementList = applicationToComplete.getRequirements();
        Set<InterviewDateAndTime> interviewDateAndTimeList = applicationToComplete.getInterviewDateAndTime();

        Application completedApplication = new Application(company, position, deadline, completion, status, priority,
                requirementList, interviewDateAndTimeList);
        model.setApplication(applicationToComplete, completedApplication);
        model.updateFilteredApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);
        model.commitInternship(model.getInternship());

        return new CommandResult(String.format(MESSAGE_SUCCESS, completedApplication));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CompleteCommand // instanceof handles nulls
                && targetIndex.equals(((CompleteCommand) other).targetIndex)); // state check
    }
}
