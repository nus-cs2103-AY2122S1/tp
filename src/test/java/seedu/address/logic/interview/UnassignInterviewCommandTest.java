package seedu.address.logic.interview;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.logic.interview.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.interview.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.interview.UnassignInterviewCommand.MESSAGE_ALL_CANDIDATES_REMOVED;
import static seedu.address.logic.interview.UnassignInterviewCommand.MESSAGE_CANDIDATE_DID_NOT_APPLY;
import static seedu.address.logic.interview.UnassignInterviewCommand.MESSAGE_CANDIDATE_INDEX_NOT_VALID;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INTERVIEW;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.JOHN;
import static seedu.address.testutil.TypicalPersons.getTypicalHrManager;
import static seedu.address.testutil.TypicalPositions.BOOKKEEPER;
import static seedu.address.testutil.TypicalPositions.CLOSED_POSITION_CLERK;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandResult;
import seedu.address.logic.candidate.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Person;
import seedu.address.model.position.Position;
import seedu.address.testutil.InterviewBuilder;
import seedu.address.testutil.ModelStub;

public class UnassignInterviewCommandTest {

    private Model model = new ModelManager(getTypicalHrManager(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Set<Index> indexes = new HashSet<>();
        indexes.add(INDEX_FIRST_PERSON);
        UnassignInterviewCommand unassignInterviewCommand = new UnassignInterviewCommand(INDEX_FIRST_INTERVIEW,
                                                                                                        indexes);
        Interview interviewToUnassign = model.getFilteredInterviewList().get(INDEX_FIRST_INTERVIEW.getZeroBased());


        Person alice = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("1. " + alice.getName() + "\n");

        String expectedMessage = String.format(UnassignInterviewCommand.MESSAGE_SUCCESS,
                interviewToUnassign.getDisplayStringWithoutNames(), sb);

        alice.addInterview(interviewToUnassign);

        Set<Person> candidates = new HashSet<>();
        candidates.add(alice);
        interviewToUnassign.setCandidates(candidates);

        CommandResult expectedCommandResult = new CommandResult(expectedMessage, CommandResult.CommandType.INTERVIEW);

        ModelManager expectedModel = new ModelManager(getTypicalHrManager(), new UserPrefs());

        assertCommandSuccess(unassignInterviewCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Set<Index> indexes = new HashSet<>();
        indexes.add(INDEX_FIRST_PERSON);

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInterviewList().size() + 1);
        UnassignInterviewCommand unassignInterviewCommand = new UnassignInterviewCommand(outOfBoundIndex, indexes);

        assertCommandFailure(unassignInterviewCommand, model, Messages.MESSAGE_INVALID_INTERVIEW_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexUnfilteredListTotalWipe_success() {
        UnassignInterviewCommand unassignInterviewCommand = new UnassignInterviewCommand(INDEX_FIRST_INTERVIEW, true);
        Interview interviewToUnassign = model.getFilteredInterviewList().get(INDEX_FIRST_INTERVIEW.getZeroBased());

        Person alice = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person benson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Set<Person> candidates = new HashSet<>();
        candidates.add(alice);
        candidates.add(benson);

        String expectedMessage = String.format(MESSAGE_ALL_CANDIDATES_REMOVED,
                interviewToUnassign.getDisplayStringWithoutNames());

        alice.addInterview(interviewToUnassign);
        benson.addInterview(interviewToUnassign);
        interviewToUnassign.setCandidates(candidates);

        CommandResult expectedCommandResult = new CommandResult(expectedMessage, CommandResult.CommandType.INTERVIEW);

        ModelManager expectedModel = new ModelManager(getTypicalHrManager(), new UserPrefs());

        assertCommandSuccess(unassignInterviewCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidCandidateIndex_throwsCommandException() {
        ModelStubWithObservable model = new ModelStubWithObservable();
        Index validInterviewIndex = Index.fromZeroBased(model.getFilteredInterviewList().size() - 1);
        Index invalidCandidateIndex = Index.fromZeroBased(model.getFilteredPersonList().size());

        UnassignInterviewCommand unassignInterviewCommand = new UnassignInterviewCommand(validInterviewIndex,
                Set.of(invalidCandidateIndex));

        assertThrows(CommandException.class,
                String.format(MESSAGE_CANDIDATE_INDEX_NOT_VALID, invalidCandidateIndex.getOneBased()), (
                        )-> unassignInterviewCommand.execute(model));
    }

    @Test
    public void execute_candidateNotScheduledInTheFirstPlace_throwsCommandException() {
        ModelStubWithObservable model = new ModelStubWithObservable();
        Index index = Index.fromZeroBased(0);

        UnassignInterviewCommand unassignInterviewCommand = new UnassignInterviewCommand(index, Set.of(index));

        assertThrows(CommandException.class, String.format(MESSAGE_CANDIDATE_DID_NOT_APPLY,
                index.getOneBased(), model.getPerson(index).getName(),
                model.getFilteredInterviewList().get(index.getZeroBased())
                        .getDisplayString()), () -> unassignInterviewCommand.execute(model));
    }

    @Test
    public void getCandidateIndexes_neverReturnsOptionalEmpty() {
        Index validInterviewIndex = Index.fromZeroBased(model.getFilteredInterviewList().size() - 1);
        Index validCandidateIndex = Index.fromZeroBased(model.getFilteredPersonList().size() - 1);

        Index invalidInterviewIndex = Index.fromZeroBased(model.getFilteredInterviewList().size());

        //null is not tested due to use of requireNonNull

        //non-empty candidate index set
        UnassignInterviewCommand unassignInterviewCommand = new UnassignInterviewCommand(validInterviewIndex,
                Set.of(validCandidateIndex));
        assertFalse(unassignInterviewCommand.getCandidateIndexes().equals(Optional.empty()));

        //empty candidate index set
        UnassignInterviewCommand otherUnassignInterviewCommand = new UnassignInterviewCommand(validInterviewIndex,
                new HashSet<>());
        assertFalse(otherUnassignInterviewCommand.getCandidateIndexes().equals(Optional.empty()));

        //invalidInterviewIndex and different constructor
        UnassignInterviewCommand anotherUnassignInterviewCommand = new UnassignInterviewCommand(invalidInterviewIndex,
                false);
        assertFalse(anotherUnassignInterviewCommand.getCandidateIndexes().equals(Optional.empty()));
    }

    /**
     * A Model stub that contains Typical Person John Doe and observable fields.
     */
    private class ModelStubWithObservable extends ModelStub {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Position> positions = FXCollections.observableArrayList();
        private final ObservableList<Interview> interviews = FXCollections.observableArrayList();

        ModelStubWithObservable() {
            persons.add(JOHN);
            persons.add(ALICE);
            positions.add(CLOSED_POSITION_CLERK);
            positions.add(BOOKKEEPER);
            interviews.add(new InterviewBuilder().withCandidates(Set.of(JOHN)).withPosition(BOOKKEEPER).build());
        }

        @Override
        public ObservableList<Interview> getFilteredInterviewList() {
            return interviews;
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return persons;
        }

        @Override
        public Person getPerson(Index index) {
            return persons.get(index.getZeroBased());
        }

        @Override
        public boolean isPositionClosed(Position toCheck) {
            return toCheck.getStatus() == Position.PositionStatus.CLOSED;
        }

        @Override
        public boolean hasInterview(Interview toAdd) {
            return interviews.contains(toAdd);
        }

        @Override
        public void setInterview(Interview target, Interview editedInterview) {
            persons.get(0).deleteInterview(target);
            persons.get(0).addInterview(editedInterview);
        }

        @Override
        public void updateFilteredInterviewList(Predicate<Interview> predicate) {
            //empty method
        }
    }
}
