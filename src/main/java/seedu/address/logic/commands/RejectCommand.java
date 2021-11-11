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
 * Changes the status (outcome or decision of the application provided
 * by the company) of an application in InternSHIP to 'Rejected'.
 */
public class RejectCommand extends Command {
    public static final String COMMAND_WORD = "reject";

    public static final String MESSAGE_USAGE = COMMAND_WORD.toUpperCase()
            + " command: Marks the application at a specified index"
            + " (as identified by the index in the displayed application list) as 'Rejected'\n"
            + "Parameters: "
            + Messages.MESSAGE_INDEX_REQUIREMENT + "\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Marked application as 'Rejected': %1$s";

    public static final String MESSAGE_ALREADY_REJECTED = "Application has already been marked as 'Rejected'!";


    private final Index targetIndex;

    /**
     * Creates a RejectCommand to mark the specified {@code Application} as Rejected.
     *
     * @param targetIndex Index pf the application to be marked as Rejected.
     */
    public RejectCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Application> lastShownList = model.getFilteredApplicationList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INDEX_EXCEEDS_LIST_LENGTH);
        }

        Application applicationToReject = lastShownList.get(targetIndex.getZeroBased());

        if (applicationToReject.isRejected()) {
            throw new CommandException(MESSAGE_ALREADY_REJECTED);
        }

        Company company = applicationToReject.getCompany();
        Position position = applicationToReject.getPosition();
        Deadline deadline = applicationToReject.getDeadline();
        Completion completion = new Completion("Completed");
        Status status = new Status("Rejected");
        Priority priority = applicationToReject.getPriority();
        Set<Requirement> requirementList = applicationToReject.getRequirements();
        Set<InterviewDateAndTime> interviewDateAndTimeList = applicationToReject.getInterviewDateAndTime();

        Application rejectedApplication = new Application(company, position, deadline, completion, status, priority,
                requirementList, interviewDateAndTimeList);

        model.setApplication(applicationToReject, rejectedApplication);
        model.updateFilteredApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);
        model.commitInternship(model.getInternship());

        return new CommandResult(String.format(MESSAGE_SUCCESS, rejectedApplication));

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RejectCommand// instanceof handles nulls
                && targetIndex.equals(((RejectCommand) other).targetIndex)); // state check
    }
}
