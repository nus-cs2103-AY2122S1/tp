package seedu.address.logic.interview;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CANDIDATE_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_INDEX;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Command;
import seedu.address.logic.CommandResult;
import seedu.address.logic.candidate.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Person;

public class UnassignInterviewCommand extends Command {
    public static final String COMMAND_WORD = "unassign";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unassigns the candidates specified by their candidate index from an interview specified by its "
            + "interview index.\n"
            + "Parameters: "
            + PREFIX_INTERVIEW_INDEX + "INTERVIEW_INDEX "
            + PREFIX_CANDIDATE_INDEX + "CANDIDATE_INDEX (must be a positive integer)...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_INTERVIEW_INDEX + "1 "
            + PREFIX_CANDIDATE_INDEX + "1 3 ";

    public static final String MESSAGE_SUCCESS = "Candidates removed from interview %1$s: %2$s";
    public static final String MESSAGE_CANDIDATE_DID_NOT_APPLY = "Candidate %1$s (%2$s) is not scheduled for "
                + "this Interview: %3$s";
    public static final String MESSAGE_ALL_CANDIDATES_REMOVED = "All candidates removed from interview: %1$s";
    public static final String MESSAGE_CANDIDATE_INDEX_NOT_VALID = "The person index %1$s is invalid";

    private Index interviewIndex;

    private Set<Index> candidateIndexes;

    private boolean isTotalWipe;

    /**
     * Creates an AddInterviewCommand to add the specified {@code Interview}
     */
    public UnassignInterviewCommand(Index interview, Set<Index> candidates) {
        requireNonNull(interview);
        requireNonNull(candidates);

        interviewIndex = interview;
        candidateIndexes = candidates;
        isTotalWipe = false;
    }

    /**
     * Creates an AddInterviewCommand to add the specified {@code Interview}
     */
    public UnassignInterviewCommand(Index interview, boolean isTotalWipe) {
        requireNonNull(interview);
        requireNonNull(isTotalWipe);

        interviewIndex = interview;
        candidateIndexes = new HashSet<>();
        this.isTotalWipe = isTotalWipe;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownCandidateList = model.getFilteredPersonList();
        List<Interview> lastShownInterviewList = model.getFilteredInterviewList();
        CommandResult result;

        if (interviewIndex.getZeroBased() >= model.getFilteredInterviewList().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERVIEW_DISPLAYED_INDEX);
        }

        Interview interviewToUnassign = lastShownInterviewList.get(interviewIndex.getZeroBased());

        for (Index candidateIndex : candidateIndexes) {
            if (candidateIndex.getZeroBased() >= lastShownCandidateList.size()) {
                throw new CommandException(String.format(MESSAGE_CANDIDATE_INDEX_NOT_VALID,
                        candidateIndex.getOneBased()));
            }

            Person candidate = lastShownCandidateList.get(candidateIndex.getZeroBased());
            if (!candidate.hasInterview(interviewToUnassign)) {
                throw new CommandException(String.format(MESSAGE_CANDIDATE_DID_NOT_APPLY,
                        candidateIndex.getOneBased(), candidate.getName(), interviewToUnassign.getDisplayString()));
            }
        }

        if (isTotalWipe) {
            Set<Person> emptyCandidatesSet = new HashSet<>();
            interviewToUnassign.setCandidates(emptyCandidatesSet);
            model.deleteInterviewFromPerson(interviewToUnassign);
            result = new CommandResult(String.format(MESSAGE_ALL_CANDIDATES_REMOVED,
                    interviewToUnassign.getDisplayStringWithoutNames()),
                    CommandResult.CommandType.INTERVIEW);
        } else {
            int count = 1;
            StringBuilder removedPersons = new StringBuilder();
            removedPersons.append("\n");

            for (Index candidateIndex : candidateIndexes) {
                Person candidate = lastShownCandidateList.get(candidateIndex.getZeroBased());
                interviewToUnassign.deleteCandidate(candidate);
                candidate.deleteInterview(interviewToUnassign);

                removedPersons.append(count + ". " + candidate.getName() + "\n");
                count++;
            }

            result = new CommandResult(String.format(MESSAGE_SUCCESS,
                    interviewToUnassign.getDisplayStringWithoutNames(), removedPersons),
                    CommandResult.CommandType.INTERVIEW);
        }

        return result;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnassignInterviewCommand// instanceof handles nulls
                && interviewIndex.equals(((UnassignInterviewCommand) other).interviewIndex)) // state check
                && isTotalWipe == ((((UnassignInterviewCommand) other).isTotalWipe));

    }

    /**
     * Returns an unmodifiable candidateIndex set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code candidates} is null.
     */
    public Optional<Set<Index>> getCandidateIndexes() {
        return (candidateIndexes != null)
                ? Optional.of(Collections.unmodifiableSet(candidateIndexes))
                : Optional.empty();
    }
}


