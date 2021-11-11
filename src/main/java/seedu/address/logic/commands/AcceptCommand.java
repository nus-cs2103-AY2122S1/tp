package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPLICATIONS;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

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
 * Changes the status (outcome or decision of the application
 * provided by the company) of an application in InternSHIP to 'Accepted'.
 */
public class AcceptCommand extends Command {
    public static final String COMMAND_WORD = "accept";

    public static final String MESSAGE_USAGE = COMMAND_WORD.toUpperCase()
            + " command: Marks the application at a specified index"
            + " (as identified by the index in the displayed application list) as 'Accepted'\n"
            + "Parameters: "
            + Messages.MESSAGE_INDEX_REQUIREMENT + "\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Marked application as 'Accepted': %1$s";

    public static final String MESSAGE_ALREADY_ACCEPTED = "Application has already been marked as 'Accepted'!";

    private final Index targetIndex;

    private Logger logger = Logger.getLogger("InfoLogging");

    /**
     * Creates an AcceptCommand to mark the specified {@code Application} as Accepted.
     *
     * @param targetIndex Index of the application to be marked as Accepted.
     */
    public AcceptCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Application> lastShownList = model.getFilteredApplicationList();

        logger.info("Logging an INFO-level message");

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INDEX_EXCEEDS_LIST_LENGTH);
        }

        Application applicationToAccept = lastShownList.get(targetIndex.getZeroBased());

        if (applicationToAccept.isAccepted()) {
            throw new CommandException(MESSAGE_ALREADY_ACCEPTED);
        }

        Company company = applicationToAccept.getCompany();
        Position position = applicationToAccept.getPosition();
        Deadline deadline = applicationToAccept.getDeadline();
        Completion completion = new Completion("Completed");
        Status status = new Status("Accepted");
        Priority priority = applicationToAccept.getPriority();
        Set<Requirement> requirementList = applicationToAccept.getRequirements();
        Set<InterviewDateAndTime> interviewDateAndTimeList = applicationToAccept.getInterviewDateAndTime();

        Application acceptedApplication = new Application(company, position, deadline, completion, status, priority,
                requirementList, interviewDateAndTimeList);
        model.setApplication(applicationToAccept, acceptedApplication);
        model.updateFilteredApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);
        model.commitInternship(model.getInternship());

        return new CommandResult(String.format(MESSAGE_SUCCESS, acceptedApplication));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AcceptCommand// instanceof handles nulls
                && targetIndex.equals(((AcceptCommand) other).targetIndex)); // state check
    }
}
