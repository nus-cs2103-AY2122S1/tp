package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.SCORE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.SCORE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSESSMENT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCORE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddScoreCommand.ScoreDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Assessment;
import seedu.address.model.student.Score;
import seedu.address.model.student.Student;
import seedu.address.testutil.AssessmentBuilder;
import seedu.address.testutil.ScoreDescriptorBuilder;
import seedu.address.testutil.ScoreBuilder;
import seedu.address.testutil.PersonBuilder;

import java.util.Map;

/**
 * Contains integration tests (interaction with Model) and unit tests for AddScoreCommand.
 */
public class AddScoreCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private final Student simpleAmy = new PersonBuilder()
            .withName(VALID_NAME_AMY)
            .withId(VALID_ID_AMY).build();

    private final Assessment simpleAssessment = new AssessmentBuilder()
            .withValue(VALID_ASSESSMENT_AMY).build();

    private final Score simpleScore = new ScoreBuilder()
            .withValue(VALID_SCORE_AMY).build();

    @Test
    public void execute_addScoreByName_success() {
        ScoreDescriptor scoreDescriptor = new ScoreDescriptorBuilder()
                .withAssessment(VALID_ASSESSMENT_AMY)
                .withName(VALID_NAME_AMY)
                .withScore(VALID_SCORE_AMY).build();
        AddScoreCommand addScoreCommand = new AddScoreCommand(scoreDescriptor);

        model.addStudent(simpleAmy);
        model.addAssessment(simpleAssessment);

        Student expectedAmy = new PersonBuilder(simpleAmy)
                .withScores(Map.of(simpleAssessment, simpleScore)).build();

        Assessment expectedAssessment = new AssessmentBuilder(simpleAssessment)
                .withScores(Map.of(VALID_ID_AMY, VALID_SCORE_AMY)).build();

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(simpleAmy, expectedAmy);
        expectedModel.setAssessment(simpleAssessment, expectedAssessment);

        String expectedMessage = String.format(AddScoreCommand.MESSAGE_SUCCESS, expectedAmy);

        assertCommandSuccess(addScoreCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addScoreById_success() {
        ScoreDescriptor scoreDescriptor = new ScoreDescriptorBuilder()
                .withAssessment(VALID_ASSESSMENT_AMY)
                .withId(VALID_ID_AMY)
                .withScore(VALID_SCORE_AMY).build();
        AddScoreCommand addScoreCommand = new AddScoreCommand(scoreDescriptor);

        model.addStudent(simpleAmy);
        model.addAssessment(simpleAssessment);

        Student expectedAmy = new PersonBuilder(simpleAmy)
                .withScores(Map.of(simpleAssessment, simpleScore)).build();

        Assessment expectedAssessment = new AssessmentBuilder(simpleAssessment)
                .withScores(Map.of(VALID_ID_AMY, VALID_SCORE_AMY)).build();

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(simpleAmy, expectedAmy);
        expectedModel.setAssessment(simpleAssessment, expectedAssessment);

        String expectedMessage = String.format(AddScoreCommand.MESSAGE_SUCCESS, expectedAmy);

        assertCommandSuccess(addScoreCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonexistentAssessment_failure() {
        ScoreDescriptor scoreDescriptor = new ScoreDescriptorBuilder()
                .withAssessment(VALID_ASSESSMENT_AMY)
                .withName(VALID_NAME_AMY)
                .withScore(VALID_SCORE_AMY).build();
        AddScoreCommand addScoreCommand = new AddScoreCommand(scoreDescriptor);

        model.addStudent(simpleAmy);

        String expectedMessage = AddScoreCommand.MESSAGE_NONEXISTENT_ASSESSMENT;

        assertCommandFailure(addScoreCommand, model, expectedMessage);
    }

    @Test
    public void execute_nonexistentStudent_failure() {
        ScoreDescriptor scoreDescriptor = new ScoreDescriptorBuilder()
                .withAssessment(VALID_ASSESSMENT_AMY)
                .withName(VALID_NAME_AMY)
                .withScore(VALID_SCORE_AMY).build();
        AddScoreCommand addScoreCommand = new AddScoreCommand(scoreDescriptor);

        model.addAssessment(simpleAssessment);

        String expectedMessage = AddScoreCommand.MESSAGE_NONEXISTENT_STUDENT;

        assertCommandFailure(addScoreCommand, model, expectedMessage);
    }

    @Test
    public void execute_duplicateStudentName_failure() {
        ScoreDescriptor scoreDescriptor = new ScoreDescriptorBuilder()
                .withAssessment(VALID_ASSESSMENT_AMY)
                .withName(VALID_NAME_AMY)
                .withScore(VALID_SCORE_AMY).build();
        AddScoreCommand addScoreCommand = new AddScoreCommand(scoreDescriptor);

        Student duplicateAmy = new PersonBuilder()
                .withName(VALID_NAME_AMY)
                .withId(VALID_ID_BOB).build();

        model.addStudent(simpleAmy);
        model.addStudent(duplicateAmy);
        model.addAssessment(simpleAssessment);

        String expectedMessage = AddScoreCommand.MESSAGE_DUPLICATE_STUDENT_NAME;

        assertCommandFailure(addScoreCommand, model, expectedMessage);
    }

    @Test
    public void execute_existentScore_failure() {
        ScoreDescriptor scoreDescriptor = new ScoreDescriptorBuilder()
                .withAssessment(VALID_ASSESSMENT_AMY)
                .withName(VALID_NAME_AMY)
                .withScore(VALID_SCORE_AMY).build();
        AddScoreCommand addScoreCommand = new AddScoreCommand(scoreDescriptor);

        Assessment assessmentWithAmyGraded = new AssessmentBuilder(simpleAssessment)
                .withScores(Map.of(VALID_ID_AMY, VALID_SCORE_AMY)).build();

        model.addStudent(simpleAmy);
        model.addAssessment(assessmentWithAmyGraded);

        String expectedMessage = AddScoreCommand.MESSAGE_EXISTENT_SCORE;

        assertCommandFailure(addScoreCommand, model, expectedMessage);
    }

    @Test
    public void equal() {
        final AddScoreCommand standardCommand = new AddScoreCommand(SCORE_DESC_AMY);

        // same values -> returns true
        ScoreDescriptor copyDescriptor = new ScoreDescriptor(SCORE_DESC_AMY);
        AddScoreCommand commandWithSameValues = new AddScoreCommand(copyDescriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(standardCommand, null);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new AddScoreCommand(SCORE_DESC_BOB));
    }
}
