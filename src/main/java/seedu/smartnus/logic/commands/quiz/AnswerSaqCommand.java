package seedu.smartnus.logic.commands.quiz;

import static java.util.Objects.requireNonNull;
import static seedu.smartnus.commons.core.Messages.MESSAGE_CONTINUE_QUIZ;
import static seedu.smartnus.commons.core.Messages.MESSAGE_END_OF_QUIZ;

import seedu.smartnus.logic.commands.Command;
import seedu.smartnus.logic.commands.CommandResult;
import seedu.smartnus.model.Model;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.quiz.QuizManager;
import seedu.smartnus.model.quiz.exceptions.QuestionAlreadyAnsweredException;

/**
 * Command to answer SAQ
 */
public class AnswerSaqCommand extends Command {

    public static final String COMMAND_WORD = "quiz";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Starts a quiz, takes no arguments";


    private final String input;
    private final Question currentQuestion;
    private final QuizManager quizManager;

    /**
     * Creates a AnswerSaqCommand
     */
    public AnswerSaqCommand(String input, QuizManager quizManager) {
        assert(!input.isBlank());
        this.input = input;
        this.currentQuestion = quizManager.currQuestion();
        this.quizManager = quizManager;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        Choice choice = new Choice(input, true);

        String endMessage = quizManager.isLastQuestion() ? MESSAGE_END_OF_QUIZ : MESSAGE_CONTINUE_QUIZ;

        try {
            if (quizManager.attemptAndCheckAnswer(choice)) {
                return new CommandResult("Correct!\n" + endMessage);
            } else {
                return new CommandResult("Incorrect. The correct answer is: "
                        + currentQuestion.getCorrectChoiceTitle() + "\n" + endMessage);
            }
        } catch (QuestionAlreadyAnsweredException e) {
            return new CommandResult("You have already answered this question.\n" + endMessage);
        }

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof AnswerSaqCommand; // instanceof handles nulls
    }

}
