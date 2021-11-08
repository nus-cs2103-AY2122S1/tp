package seedu.smartnus.logic.commands.quiz;

import static java.util.Objects.requireNonNull;
import static seedu.smartnus.commons.core.Messages.MESSAGE_CONTINUE_QUIZ;
import static seedu.smartnus.commons.core.Messages.MESSAGE_END_OF_QUIZ;

import java.util.ArrayList;

import seedu.smartnus.logic.commands.Command;
import seedu.smartnus.logic.commands.CommandResult;
import seedu.smartnus.model.Model;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.quiz.QuizManager;
import seedu.smartnus.model.quiz.exceptions.QuestionAlreadyAnsweredException;

/**
 * Starts a quiz
 */
public class AnswerMcqCommand extends Command {

    public static final String COMMAND_WORD = "quiz";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Starts a quiz, takes no arguments";


    private final String input;
    private final Question currentQuestion;
    private final QuizManager quizManager;

    /**
     * Creates a AnswerMcqCommand
     */
    public AnswerMcqCommand(String input, QuizManager quizManager) {
        assert(input.matches("^[a-dA-D]$"));
        this.input = input;
        this.currentQuestion = quizManager.currQuestion();
        this.quizManager = quizManager;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        ArrayList<Choice> choices = currentQuestion.getOrderedChoices();
        Choice choice = null;

        switch (input.toLowerCase()) {

        case "a":
            choice = choices.get(0);
            break;

        case "b":
            choice = choices.get(1);
            break;

        case "c":
            choice = choices.get(2);
            break;

        case "d":
            choice = choices.get(3);
            break;

        default:
            break;

        }

        assert choice != null : "Choice should not be null";

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
                || other instanceof AnswerMcqCommand; // instanceof handles nulls
    }

}
