package seedu.address.logic.interview;

import static seedu.address.logic.interview.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.interview.CommandTestUtil.assertCommandFailureRepeatedAssign;
import static seedu.address.logic.interview.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INTERVIEW;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_INTERVIEW;
import static seedu.address.testutil.TypicalPersons.getTypicalHrManager;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandResult;
import seedu.address.model.HrManager;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Person;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;
import seedu.address.testutil.InterviewBuilder;
import seedu.address.testutil.PersonBuilder;

public class AssignInterviewCommandTest {

    private Model model = new ModelManager(getTypicalHrManager(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Set<Index> indexes = new HashSet<>();
        indexes.add(INDEX_FIRST_PERSON);
        AssignInterviewCommand assignInterviewCommand = new AssignInterviewCommand(INDEX_FIRST_INTERVIEW,
                indexes);
        Interview assignedInterview = new InterviewBuilder()
                .withPosition(new Position(new Title("Accountant")))
                .withCandidates(new HashSet<>())
                .withDate(LocalDate.of(2021, 10, 15))
                .withStartTime(LocalTime.of(14, 0))
                .withDuration(Duration.ofMinutes(120))
                .withStatus(Interview.InterviewStatus.PENDING).build();

        Person alice = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person copyOfAlice = new PersonBuilder()
                .withName("Alice Pauline")
                .withPositions("Accountant")
                .withAddress("123, Jurong West Ave 6, #08-111")
                .withEmail("alice@example.com")
                .withPhone("94351253")
                .withRemark("She likes aardvarks.")
                .withTags("friends")
                .withStatus("Applied").build();

        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("1. " + alice.getName() + "\n");

        String expectedMessage = String.format(AssignInterviewCommand.MESSAGE_SUCCESS,
                assignedInterview.getDisplayStringWithoutNames(), sb);

        copyOfAlice.addInterview(assignedInterview);
        assignedInterview.addCandidate(copyOfAlice);

        CommandResult expectedCommandResult = new CommandResult(expectedMessage, CommandResult.CommandType.INTERVIEW);

        Model expectedModel = new ModelManager(new HrManager(model.getHrManager()), new UserPrefs());
        assertCommandSuccess(assignInterviewCommand, model, expectedCommandResult, expectedModel);

        alice.deleteInterview(assignedInterview);
        assignedInterview.deleteCandidate(alice);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Set<Index> indexes = new HashSet<>();
        indexes.add(INDEX_FIRST_PERSON);

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInterviewList().size() + 1);
        AssignInterviewCommand assignInterviewCommand = new AssignInterviewCommand(outOfBoundIndex, indexes);

        assertCommandFailure(assignInterviewCommand, model, Messages.MESSAGE_INVALID_INTERVIEW_DISPLAYED_INDEX);
    }

    @Test
    public void execute_candidateUnappliedUnfilteredList_throwsCommandException() {
        Set<Index> indexes = new HashSet<>();
        indexes.add(INDEX_FIRST_PERSON);

        Interview interviewToAssign = model.getFilteredInterviewList().get(INDEX_SECOND_INTERVIEW.getZeroBased());

        Person alice = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        AssignInterviewCommand assignInterviewCommand = new AssignInterviewCommand(INDEX_SECOND_INTERVIEW,
                indexes);

        String expectedMessage = String.format(AssignInterviewCommand.MESSAGE_CANDIDATE_DID_NOT_APPLY,
                INDEX_FIRST_PERSON.getOneBased(), alice.getName(), interviewToAssign.getPositionTitle());

        assertCommandFailure(assignInterviewCommand, model, expectedMessage);
    }

    @Test
    public void execute_candidateHasInterviewUnfilteredList_throwsCommandException() {
        Set<Index> indexes = new HashSet<>();
        indexes.add(INDEX_FIRST_PERSON);
        AssignInterviewCommand assignInterviewCommand = new AssignInterviewCommand(INDEX_FIRST_INTERVIEW,
                indexes);
        Interview assignedInterview = new InterviewBuilder()
                .withPosition(new Position(new Title("Accountant")))
                .withCandidates(new HashSet<>())
                .withDate(LocalDate.of(2021, 10, 15))
                .withStartTime(LocalTime.of(14, 0))
                .withDuration(Duration.ofMinutes(120))
                .withStatus(Interview.InterviewStatus.PENDING).build();

        Person alice = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person copyOfAlice = new PersonBuilder()
                .withName("Alice Pauline")
                .withPositions("Accountant")
                .withAddress("123, Jurong West Ave 6, #08-111")
                .withEmail("alice@example.com")
                .withPhone("94351253")
                .withRemark("She likes aardvarks.")
                .withTags("friends")
                .withStatus("Applied").build();

        String expectedMessage = String.format(AssignInterviewCommand.MESSAGE_CANDIDATE_HAS_INTERVIEW,
                INDEX_FIRST_PERSON.getOneBased(), alice.getName(), assignedInterview.getDisplayStringWithoutNames());

        copyOfAlice.addInterview(assignedInterview);
        assignedInterview.addCandidate(copyOfAlice);

        assertCommandFailureRepeatedAssign(assignInterviewCommand, model, expectedMessage);

        alice.deleteInterview(assignedInterview);
        assignedInterview.deleteCandidate(alice);
    }
}
