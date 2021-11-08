package seedu.smartnus.logic.commands.quiz;

import static seedu.smartnus.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.smartnus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.smartnus.testutil.Assert.assertThrows;
import static seedu.smartnus.testutil.TypicalSmartNus.getTypicalSmartNus;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.smartnus.commons.core.Messages;
import seedu.smartnus.commons.core.index.Index;
import seedu.smartnus.logic.commands.exceptions.CommandException;
import seedu.smartnus.model.Model;
import seedu.smartnus.model.ModelManager;
import seedu.smartnus.model.ModelStubTagPanel;
import seedu.smartnus.model.UserPrefs;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.question.comparator.QuestionsDefaultComparator;
import seedu.smartnus.model.question.predicates.ShowAllQuestionsPredicate;
import seedu.smartnus.model.question.predicates.ShowQuestionIndexPredicate;


class QuizCommandTest {

    private Model model;
    private Model expectedModel;
    private ArrayList<Predicate<Question>> filterPredicates;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSmartNus(), new UserPrefs());
        expectedModel = new ModelManager(model.getSmartNus(), new UserPrefs());
        filterPredicates = new ArrayList<>();
    }

    @Test
    void execute_startQuizWithoutArguments_success() {
        filterPredicates.add(new ShowAllQuestionsPredicate());
        assertCommandSuccess(new QuizCommand(filterPredicates, null), model,
                QuizCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    void execute_quizWithComparator() {
        Comparator<Question> defaultComparator = new QuestionsDefaultComparator();
        expectedModel.sortFilteredQuizQuestionList(defaultComparator);
        assertCommandSuccess(new QuizCommand(filterPredicates, defaultComparator), model,
                QuizCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    void execute_quizNoQuestions() {
        filterPredicates.add(new ShowQuestionIndexPredicate(Index.fromOneBased(100)));
        assertCommandFailure(new QuizCommand(filterPredicates, null, true), model,
                QuizCommand.MESSAGE_NO_QUESTIONS);
    }

    @Test
    public void quiz_wrongPanel() {
        ModelStubTagPanel modelStub = new ModelStubTagPanel();
        QuizCommand command = new QuizCommand(filterPredicates, null);
        assertThrows(CommandException.class,
                Messages.MESSAGE_NOT_IN_QUESTION_PANEL, () -> command.execute(modelStub));
    }
}
